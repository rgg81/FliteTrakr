package adidas.flitetrakr.questions.impl;

import adidas.flitetrakr.graph.Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;

import static adidas.flitetrakr.graph.search.GraphSearch.sumWeightPath;

public class PriceConnectionQuestion extends GraphQuestion<Integer, String> {
    private static final String CHAR_SEPARATOR = "-";
    public PriceConnectionQuestion(Graph graph) {
        super(graph);
    }

    @Override
    public Optional<Integer> answer(String input) {
        return sumWeightPath(parseInput(input), graph);
    }

    private LinkedList<String> parseInput(String input){
        return new LinkedList<>(Arrays.asList(input.split(CHAR_SEPARATOR)));
    }
}
