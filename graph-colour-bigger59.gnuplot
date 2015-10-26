# vim: set et ft=gnuplot sw=4 :

set terminal tikz color size 4.6in,2.6in font '\tiny'
set output "gen-graph-colour-bigger59.tex"
set xlabel "Number of Colours"
set ylabel "Runtime (s)"
set key top right

set border 0
set ytics nomirror border scale 0
set xtics (1, 5, 10, 15, 20, 25, 30) nomirror border rangelimited
set grid ytics

set boxwidth 0.5
set style fill solid
set xrange [0:31]
plot \
    "code/bigger59.data" using (strcol(2) eq "false" ? $1 : NaN):($3) with boxes notitle lc '#b30c00', \
    "code/bigger59.data" using (strcol(2) eq "true" ? $1 : NaN):($3) with boxes notitle lc '#009dec'

