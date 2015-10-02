/* vim: set sw=4 sts=4 et foldmethod=syntax : */

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.exception.ContradictionException;
import static org.chocosolver.solver.variables.VF.enumeratedMatrix;
import static org.chocosolver.solver.constraints.ICF.alldifferent;
import static org.chocosolver.solver.constraints.ICF.arithm;

public class Sudoku
{
    private static void alldiff(Solver solver, IntVar[] array, boolean decompose)
    {
        if (decompose) {
            for (int i = 0 ; i < array.length - 1 ; ++i)
                for (int j = i + 1 ; j < array.length ; ++j)
                    solver.post(arithm(array[i], "!=", array[j]));
        }
        else
            solver.post(alldifferent(array));
    }

    public static void main(String args[]) throws ContradictionException, FileNotFoundException
    {
        int n = 3;
        int nn = n * n;
        int[][] predef = new int[nn][nn];

        boolean decompose = args.length > 1 && args[1].equals("neq");

        try (Scanner sc = new Scanner(new File(args[0]))) {
            for (int i = 0 ; i < nn ; i++)
                for (int j = 0 ; j < nn ; j++)
                    predef[i][j] = sc.nextInt();
        }

        Solver solver = new Solver("sudoku");

        IntVar[][] grid = enumeratedMatrix("grid", nn, nn, IntStream.rangeClosed(1, nn).toArray(), solver);

        // Rows
        for (int i = 0 ; i < nn ; ++i)
            alldiff(solver, grid[i], decompose);

        // Columns
        for (int i = 0 ; i < nn ; ++i) {
            IntVar[] column = new IntVar[nn];
            for (int j = 0 ; j < nn ; ++j)
                column[j] = grid[j][i];
            alldiff(solver, column, decompose);
        }

        // Squares
        for (int i = 0 ; i < nn ; i += n)
            for (int j = 0 ; j < nn ; j += n) {
                IntVar[] square = new IntVar[nn];
                for (int x = 0 ; x < n ; ++x)
                    for (int y = 0 ; y < n ; ++y)
                        square[n * x + y] = grid[i + x][j + y];
                alldiff(solver, square, decompose);
            }

        // Predefined values
        for (int i = 0 ; i < nn ; i++)
            for (int j = 0 ; j < nn ; j++)
                if (0 != predef[i][j])
                    solver.post(arithm(grid[i][j], "=", predef[i][j]));

        System.out.println(solver.findSolution());
        for (int i = 0 ; i < nn ; i++) {
            for (int j = 0 ; j < nn ; j++)
                System.out.print(grid[i][j].getValue() + " ");
            System.out.println();
        }

        System.out.println("\n" + solver.getMeasures());
    }
}
