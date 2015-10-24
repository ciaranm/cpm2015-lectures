Solver solver = new Solver("MAC");

/* variables and constraints go here */

solver.propagate();

if (mac(solver)) {
    System.out.println("satisfiable");
    for (int i = 0 ; i < solver.getNbVars() ; ++i)
        System.out.println(solver.getVar(i));
}
else
    System.out.println("unsatisfiable");
