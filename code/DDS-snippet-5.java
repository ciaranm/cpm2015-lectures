boolean success = false;
for (int cutoff = 0 ; cutoff < solver.getNbVars() ; ++cutoff) {
    if (dds(solver, 0, cutoff)) {
        success = true;

        System.out.println("satisfiable");
        for (int i = 0 ; i < solver.getNbVars() ; ++i)
            System.out.println(solver.getVar(i));

        break;
    }
}

if (! success)
    System.out.println("unsatisfiable");
