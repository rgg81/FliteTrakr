package adidas.flitetrakr.questions.impl;

import adidas.flitetrakr.graph.Graph;
import adidas.flitetrakr.graph.search.GraphSearch;

import java.util.Optional;

import static adidas.flitetrakr.graph.search.GraphSearch.searchConnectionsStop;

public class SearchConnectionStopsQuestion extends GraphQuestion<Integer, String> {
    private final int threshold;
    private final GraphSearch.FunctionSearch functionSearch;

    public SearchConnectionStopsQuestion(Graph graph, int threshold, GraphSearch.FunctionSearch functionSearch) {
        super(graph);
        this.threshold = threshold;
        this.functionSearch = functionSearch;
    }

    @Override
    public Optional<Integer> answer(String input) {
        String[] sourceDestination = splitSourceDestination(input);
        return Optional.of(searchConnectionsStop(sourceDestination[0],
                sourceDestination[1], graph, threshold, functionSearch));
    }

}
