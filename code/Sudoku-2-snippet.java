Solver solver = new Solver("sudoku");

IntVar[][] grid = enumeratedMatrix("grid", nn, nn,
        IntStream.rangeClosed(1, nn).toArray(), solver);
