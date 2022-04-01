package adidas.flitetrakr.questions.impl;

import adidas.flitetrakr.graph.Graph;

import java.util.List;
import java.util.Optional;

import static adidas.flitetrakr.graph.search.GraphSearch.searchConnectionsBelowPrice;

public class SearchConnectionBelowPriceQuestion extends GraphQuestion<String, String> {
    private final int threshold;

    public SearchConnectionBelowPriceQuestion(Graph graph, int threshold) {
        super(graph);
        this.threshold = threshold;
    }

    @Override
    public Optional<String> answer(String input) {
        String[] sourceDestination = splitSourceDestination(input);
        return searchConnectionsBelowPrice(sourceDestination[0],
                sourceDestination[1], graph, threshold);
    }

}
