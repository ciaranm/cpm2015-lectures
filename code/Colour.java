/* vim: set sw=4 sts=4 et foldmethod=syntax : */

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.exception.ContradictionException;
import static org.chocosolver.solver.variables.VF.enumeratedArray;
import static org.chocosolver.solver.constraints.ICF.arithm;

public class Colour
{
    public static void main(String args[]) throws ContradictionException, FileNotFoundException
    {
        Solver solver = new Solver("colouring");

        try (Scanner sc = new Scanner(new File(args[0]))) {
            int nVertices = sc.nextInt();
            int nColours  = Integer.parseInt(args[1]);

            IntVar[] vertices = enumeratedArray("vertex", nVertices, 0, nColours - 1, solver);

            while (sc.hasNext()) {
                int from = sc.nextInt();
                int to = sc.nextInt();
                solver.post(arithm(vertices[from - 1], "!=", vertices[to - 1]));
            }
        }

        System.out.println(solver.findSolution());
        System.out.println(solver.getMeasures().getTimeCount());
    }
}

