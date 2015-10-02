Matrix<IntVarArray> grid(x, nn, nn);

// Rows and columns
for (int i = 0; i < nn ; i++) {
    distinct(*this, grid.row(i));
    distinct(*this, grid.col(i));
}

// Squares
for (int i = 0 ; i < nn ; i += n)
    for (int j = 0 ; j < nn ; j += n)
        distinct(*this, grid.slice(i, i + n, j, j + n));

// Fill-in predefined fields
for (int i = 0 ; i < nn ; i++)
    for (int j = 0 ; j < nn ; j++)
        if (0 != predef[i][j])
            rel(*this, grid(i, j) == predef[i][j]);

