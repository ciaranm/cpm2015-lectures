        /* If we're below the cutoff, don't consider
         * any remaining values */
        if (depth >= cutoff)
            break;

        first = false;
    }
    values.dispose();

    /* we ran out of values */
    return false;
}
