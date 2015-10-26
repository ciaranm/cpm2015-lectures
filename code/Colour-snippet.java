Solver solver = new Solver("colouring");

try (Scanner sc = new Scanner(new File(args[0]))) {
    int nVertices = sc.nextInt();
    int nColours  = Integer.parseInt(args[1]);

    IntVar[] v = enumeratedArray(
            "vertex", nVertices, 0, nColours - 1, solver);

    while (sc.hasNext()) {
        int from = sc.nextInt();
        int to = sc.nextInt();
        solver.post(arithm(v[from - 1], "!=", v[to - 1]));
    }
}

System.out.println(solver.findSolution());
System.out.println(solver.getMeasures().getTimeCount());
