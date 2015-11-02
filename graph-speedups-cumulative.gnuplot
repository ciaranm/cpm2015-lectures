# vim: set et ft=gnuplot sw=4 :

set terminal tikz color size 4in,2.8in font '\tiny'

set output "gen-graph-speedups-cumulative.tex"

set key off
set border 3

set xlabel "Sequential Runtime (ms)"
set ylabel "Instances solved"
set logscale x
set xtics nomirror
set ytics nomirror
set grid
set xrange [1:1e8]
set format x '$10^{%T}$'

plot \
    "sip-runtimes.data" u ($7):($7>=1e8?1e-8:1) smooth cumulative w l lc "#009dec" ti '\raisebox{-1mm}{Sequential}' at end, \
    "sip-runtimes.data" u ($12):($12>=1e8?1e-8:1) smooth cumulative w l lc "#84bd00" ti '\raisebox{1mm}{Threaded}' at end

