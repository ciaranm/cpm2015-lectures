all : tables graphs
	latexmk -pdf -pdflatex='pdflatex -interaction=nonstopmode %O %S' \
	    all-different discrepancies parallel-cp parallel-search

TABLES =

GRAPHS = gen-graph-colour-simple.tex gen-graph-colour-bigger.tex gen-graph-colour-bigger59.tex

tables : $(TABLES)

graphs : $(GRAPHS)

gen-graph-%.tex : graph-%.gnuplot
	gnuplot $<

