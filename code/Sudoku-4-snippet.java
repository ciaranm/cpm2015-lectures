// Columns
for (int i = 0 ; i < nn ; ++i) {
    IntVar[] column = new IntVar[nn];
    for (int j = 0 ; j < nn ; ++j)
        column[j] = grid[j][i];
    solver.post(alldifferent(column));
}
