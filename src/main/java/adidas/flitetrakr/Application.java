package adidas.flitetrakr;

import adidas.flitetrakr.graph.Graph;
import adidas.flitetrakr.graph.search.GraphSearch;
import adidas.flitetrakr.questions.Question;
import adidas.flitetrakr.questions.impl.CheapestConnectionQuestion;
import adidas.flitetrakr.questions.impl.PriceConnectionQuestion;
import adidas.flitetrakr.questions.impl.SearchConnectionBelowPriceQuestion;
import adidas.flitetrakr.questions.impl.SearchConnectionStopsQuestion;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static adidas.flitetrakr.graph.Converter.fromInputString;

public class Application {

    private static final String COMMAND_SEPARATOR = " ";
    private static final String NO_CONNECTION = "No such connection found!";

    public static void main(String[] args) {
        if (args.length == 0) throw new IllegalArgumentException("An input file is required");
        String input = args[0];
        List<String> allLines = readFile(input);
        assert allLines.size() > 1;
        Graph graph = buildGraph(allLines.get(0));
        for (int i = 1; i < allLines.size(); i++) {
            String command = allLines.get(i);
            System.out.println("Running command: " + command);
            try{
                processCommandQuestion(allLines.get(i), graph);
            } catch (RuntimeException ex){
                System.out.println("Problem in executing command: " + command + ". Please refer to README.");
            }
            System.out.println("\n");
        }
    }

    private static void processCommandQuestion(String strCommand, Graph graph) {
        enum Commands {
            CHEAPEST_CONNECTION, PRICE_CONNECTION, SEARCH_STOPS, SEARCH_PRICES
        }
        String[] arguments = strCommand.split(COMMAND_SEPARATOR);
        switch (Commands.valueOf(arguments[0])) {
            case PRICE_CONNECTION -> {
                Question<Integer, String> question = new PriceConnectionQuestion(graph);
                Optional<Integer> answer = question.answer(arguments[1]);
                answer.ifPresentOrElse(System.out::println, () -> System.out.println(NO_CONNECTION));
            }
            case CHEAPEST_CONNECTION -> {
                Question<String, String> question = new CheapestConnectionQuestion(graph);
                Optional<String> answer = question.answer(arguments[1]);
                answer.ifPresentOrElse(System.out::println, () -> System.out.println(NO_CONNECTION));
            }
            case SEARCH_STOPS -> {
                Question<Integer, String> question = new SearchConnectionStopsQuestion(graph,
                        Integer.parseInt(arguments[2]),
                        GraphSearch.FunctionSearch.valueOf(arguments[3]));
                Optional<Integer> answer = question.answer(arguments[1]);
                answer.ifPresentOrElse(System.out::println, () -> System.out.println(NO_CONNECTION));
            }
            case SEARCH_PRICES -> {
                Question<String, String> question = new SearchConnectionBelowPriceQuestion(graph,
                        Integer.parseInt(arguments[2]));
                Optional<String> answer = question.answer(arguments[1]);
                answer.ifPresentOrElse(System.out::println, () -> System.out.println(NO_CONNECTION));

            }
        }
    }

    private static List<String> readFile(String strPath) {
        try {
            return Files.readAllLines(Paths.get(strPath), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            throw new IllegalArgumentException("Could not find input file");
        }
    }

    private static Graph buildGraph(String data) {
        try {
            return new Graph(fromInputString(data));
        } catch (Throwable ex) {
            throw new IllegalArgumentException("Error in parsing data to build de graph");
        }
    }
}
