package adidas.flitetrakr.questions.impl;

import adidas.flitetrakr.graph.Graph;
import static adidas.flitetrakr.graph.search.GraphSearch.cheapestConnection;

import java.util.List;
import java.util.Optional;

public class CheapestConnectionQuestion extends GraphQuestion<String, String> {

    public CheapestConnectionQuestion(Graph graph) {
        super(graph);
    }

    @Override
    public Optional<String> answer(String input) {
        String[] sourceDestination = splitSourceDestination(input);
        return cheapestConnection(sourceDestination[0], sourceDestination[1], graph);
    }


}
