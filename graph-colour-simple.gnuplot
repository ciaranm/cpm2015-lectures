# vim: set et ft=gnuplot sw=4 :

set terminal tikz color size 4.6in,2.6in font '\tiny'
set output "gen-graph-colour-simple.tex"
set xlabel "Number of Colours"
set ylabel "Runtime (ms)"
set key top right

set border 0
set ytics nomirror border scale 0
set xtics (1, 5, 10) nomirror border rangelimited
set grid ytics

set boxwidth 0.5
set style fill solid
set xrange [0:11]
plot \
    "code/simple.data" using (strcol(2) eq "false" ? $1 : NaN):($3*1000) with boxes notitle lc '#b30c00', \
    "code/simple.data" using (strcol(2) eq "true" ? $1 : NaN):($3*1000) with boxes notitle lc '#009dec'

