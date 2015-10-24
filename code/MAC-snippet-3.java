    /* try giving it each value, in turn */
    DisposableValueIterator values = ((IntVar) solver.getVar(
                branchVarNum)).getValueIterator(true);

    while (values.hasNext()) {
        int v = values.next();
        solver.getEnvironment().worldPush();
        try {
            /* try the assignment (hack!), propagate, recurse */
            IntVar var = (IntVar) solver.getVar(branchVarNum);
            var.instantiateTo(v, null);
            solver.propagate();
            if (mac(solver))
                return true;
        }
        catch (ContradictionException e) { }
        solver.getEnvironment().worldPop();
    }
    values.dispose();

