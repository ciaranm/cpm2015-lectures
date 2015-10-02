/* vim: set sw=4 sts=4 et foldmethod=syntax : */

import java.util.Arrays;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.exception.ContradictionException;
import static org.chocosolver.solver.variables.VF.enumeratedArray;
import static org.chocosolver.solver.constraints.ICF.alldifferent;
import static org.chocosolver.solver.constraints.ICF.member;
import static org.chocosolver.solver.constraints.ICF.arithm;

public class OneRowSudoku
{
    public static void main(String args[]) throws ContradictionException
    {
        Solver solver = new Solver("one row sudoku");
        IntVar[] row = enumeratedArray("row", 9, 1, 9, solver);

        solver.post(member(row[0], new int[] { 1, 8 }));
        solver.post(member(row[1], new int[] { 2, 3 }));
        solver.post(member(row[2], new int[] { 2, 3 }));
        solver.post(member(row[3], new int[] { 2, 4, 5 }));
        solver.post(member(row[4], new int[] { 4, 5, 6 }));
        solver.post(member(row[5], new int[] { 4, 5, 6 }));
        solver.post(member(row[6], new int[] { 2, 7, 9 }));
        solver.post(member(row[7], new int[] { 3, 7, 8 }));
        solver.post(member(row[8], new int[] { 2, 3, 5, 8, 9 }));

        solver.propagate();
        System.out.println("Before: " + Arrays.toString(row));

        if (args.length > 0 && args[0].equals("neq")) {
            for (int i = 0 ; i < 8 ; ++i)
                for (int j = i + 1 ; j < 9 ; ++j)
                    solver.post(arithm(row[i], "!=", row[j]));
        }
        else
            solver.post(alldifferent(row));

        solver.propagate();
        System.out.println("After: " + Arrays.toString(row));
    }
}

