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

solver.post(alldifferent(row));
solver.propagate();
System.out.println("After: " + Arrays.toString(row));
