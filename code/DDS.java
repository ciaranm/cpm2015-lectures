/* vim: set sw=4 sts=4 et foldmethod=syntax : */

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.Variable;
import org.chocosolver.solver.exception.ContradictionException;
import org.chocosolver.util.iterators.DisposableValueIterator;
import static org.chocosolver.solver.variables.VF.enumeratedArray;
import static org.chocosolver.solver.variables.VF.fixed;
import static org.chocosolver.solver.constraints.ICF.alldifferent;
import static org.chocosolver.solver.constraints.ICF.distance;

public class DDS
{
    static int propagations = 0;

    static boolean dds(Solver solver, int depth, int cutoff) throws ContradictionException
    {
        /* find an uninstantiated variable */
        int branchVarNum = -1;
        for (int v = 0 ; v < solver.getNbVars() && -1 == branchVarNum ; ++v)
            if (! solver.getVar(v).isInstantiated())
                branchVarNum = v;

        /* every variable has domain size 1? */
        if (branchVarNum == -1)
            return true;

        /* try giving it each value, in turn */
        DisposableValueIterator values = ((IntVar) solver.getVar(branchVarNum)).getValueIterator(true);
        boolean first = true;

        while (values.hasNext()) {
            int v = values.next();

            if (depth != (cutoff - 1) || ! first) {
                solver.getEnvironment().worldPush();

                try {
                    System.out.printf("%" + (depth + 1) * 2 + "sGuessing %s is %d\n",
                            "", solver.getVar(branchVarNum), v);
                    ((IntVar) solver.getVar(branchVarNum)).instantiateTo(v, null);

                    ++propagations;
                    solver.propagate();

                    if (dds(solver, depth + 1, cutoff))
                        return true;
                }
                catch (ContradictionException e) {
                }

                solver.getEnvironment().worldPop();
            }

            /* If we're below the cutoff, don't consider any remaining values */
            if (depth >= cutoff)
                break;

            first = false;
        }
        values.dispose();

        /* we ran out of values */
        return false;
    }

    public static void main(String args[]) throws ContradictionException
    {
        Solver solver = new Solver("DDSQueens");

        int nQueens = Integer.parseInt(args[0]);
        IntVar[] queens = enumeratedArray("queens", nQueens, 0, nQueens - 1, solver);
        solver.post(alldifferent(queens));

        ++propagations;
        solver.propagate();

        for (int i = 0 ; i < nQueens ; ++i)
            for (int j = i + 1 ; j < nQueens ; ++j)
                solver.post(distance(queens[i], queens[j], "!=", j - i));

        boolean success = false;
        for (int cutoff = 0 ; cutoff < nQueens && ! success ; ++cutoff) {
            System.out.println("cutoff = " + cutoff);
            if (dds(solver, 0, cutoff)) {
                success = true;

                System.out.println("");
                for (int i = 0 ; i < queens.length ; ++i)
                    System.out.println(queens[i]);
            }
        }

        if (! success)
            System.out.println("unsatisfiable");

        System.out.println("propagations = " + propagations);
    }
}
/* vim: set sw=4 sts=4 et foldmethod=syntax : */


