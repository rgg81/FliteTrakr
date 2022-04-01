package adidas.flitetrakr.questions.impl;

import adidas.flitetrakr.graph.Graph;
import adidas.flitetrakr.graph.search.GraphSearch;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static adidas.flitetrakr.graph.Converter.fromInputString;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchConnectionBelowPriceQuestionTest {

    @ParameterizedTest(name = "search prices of the connection {0} below price {1} = {2}")
    @MethodSource("generateData")
    void calc(String path, int threshold, String expectedResult) {
        String data = "Connections: NUE-FRA-43, NUE-AMS-67, FRA-AMS-17, FRA-LHR-27,LHR-NUE-23";
        Graph graph = new Graph(fromInputString(data));
        SearchConnectionBelowPriceQuestion searchConnectionBelowPriceQuestion = new SearchConnectionBelowPriceQuestion(graph, threshold);
        assertEquals(Optional.of(expectedResult), searchConnectionBelowPriceQuestion.answer(path),
                () -> path + " search should equal " + expectedResult);
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of("NUE-LHR", 170, "NUE-FRA-LHR-70, NUE-FRA-LHR-NUE-FRA-LHR-163"),
                Arguments.of("NUE-LHR", 80, "NUE-FRA-LHR-70")
        );
    }
}
