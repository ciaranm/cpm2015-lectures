// Predefined values
for (int i = 0 ; i < nn ; i++)
    for (int j = 0 ; j < nn ; j++)
        if (0 != predef[i][j])
            solver.post(arithm(grid[i][j], "=", predef[i][j]));
