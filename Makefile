all : tables graphs
	latexmk -pdf -pdflatex='pdflatex -interaction=nonstopmode %O %S' \
	    all-different discrepancies parallel-cp parallel-search colour-me

TABLES =

GRAPHS = gen-graph-colour-simple.tex gen-graph-colour-bigger.tex gen-graph-colour-bigger59.tex \
	 gen-graph-speedups-scatter.tex gen-graph-speedups-cumulative.tex

tables : $(TABLES)

graphs : $(GRAPHS)

gen-graph-%.tex : graph-%.gnuplot
	gnuplot $<

