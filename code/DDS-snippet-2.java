    /* try giving it each value, in turn */
    DisposableValueIterator values = ((IntVar) solver.getVar(
                branchVarNum)).getValueIterator(true);

    boolean first = true;
    while (values.hasNext()) {
        int v = values.next();
