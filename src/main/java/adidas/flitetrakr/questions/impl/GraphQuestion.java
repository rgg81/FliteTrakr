package adidas.flitetrakr.questions.impl;

import adidas.flitetrakr.graph.Graph;
import adidas.flitetrakr.questions.Question;

abstract class GraphQuestion<T, S> implements Question<T, S> {
    private static final String SEPARATOR_SOURCE_DESTINATION = "-";

    public final Graph graph;
    public GraphQuestion(Graph graph){
        this.graph = graph;
    }

    protected String[] splitSourceDestination(String input){
        return input.split(SEPARATOR_SOURCE_DESTINATION);
    }

}
