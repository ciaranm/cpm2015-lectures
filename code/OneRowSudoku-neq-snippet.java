// solver.post(alldifferent(row));

for (int i = 0 ; i < 8 ; ++i)
    for (int j = i + 1 ; j < 9 ; ++j)
        solver.post(arithm(row[i], "!=", row[j]));
