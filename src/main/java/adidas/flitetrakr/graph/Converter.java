package adidas.flitetrakr.graph;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {
    private static final String STARTING_INPUT_TEST = "Connections:";
    private static final String SEPARATOR_EDGES = ",";
    private static final String SEPARATOR_EDGE = "-";

    public static List<Graph.Edge> fromInputString(String input){
        String cleanStartPrefix = input.replace(STARTING_INPUT_TEST, "");
        return Arrays.stream(cleanStartPrefix.split(SEPARATOR_EDGES)).map(edgeString -> {
            String[] edgeArraySplit = edgeString.split(SEPARATOR_EDGE);
            return new Graph.Edge(edgeArraySplit[0].trim(),
                    edgeArraySplit[1].trim(),
                    Integer.parseInt(edgeArraySplit[2]));
        }).collect(Collectors.toList());
    }
}
