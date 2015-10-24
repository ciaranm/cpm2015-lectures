static boolean mac(Solver solver)
{
    /* find an uninstantiated variable */
    int branchVarNum = -1;
    for (int v = 0 ; v < solver.getNbVars() ; ++v)
        if (! solver.getVar(v).isInstantiated()) {
            branchVarNum = v;
            break;
        }

    /* every variable has domain size 1? */
    if (branchVarNum == -1)
        return true;
