        /* if we're one before the cutoff, don't consider the
         * first value */
        if (depth != (cutoff - 1) || ! first) {
            solver.getEnvironment().worldPush();

            try {
                /* try the assignment, propagate, recurse */
                IntVar var = (IntVar) solver.getVar(branchVarNum);
                var.instantiateTo(v, null);
                solver.propagate();

                if (dds(solver, depth + 1, cutoff))
                    return true;
            }
            catch (ContradictionException e) { }

            solver.getEnvironment().worldPop();
        }
