package adidas.flitetrakr.questions.impl;

import adidas.flitetrakr.graph.Graph;
import adidas.flitetrakr.graph.search.GraphSearch;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

import static adidas.flitetrakr.graph.Converter.fromInputString;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchConnectionStopsQuestionTest {

    @ParameterizedTest(name = "search stops of the connection {0} using func {1} = {2}")
    @CsvSource({
            "NUE-FRA, 3,   MAX,    2",
            "LHR-AMS, 1,   EQUAL,  1",
            "NUE-LHR, 4,   MAX,    2"
    })
    void calc(String path, int threshold, GraphSearch.FunctionSearch functionSearch, int expectedResult) {
        String data = "Connections: NUE-FRA-43, NUE-AMS-67, FRA-AMS-17, FRA-LHR-27,LHR-NUE-23";
        Graph graph = new Graph(fromInputString(data));
        SearchConnectionStopsQuestion searchConnectionStopsQuestion = new SearchConnectionStopsQuestion(graph, threshold, functionSearch);
        assertEquals(Optional.of(expectedResult), searchConnectionStopsQuestion.answer(path),
                () -> path + " search should equal " + expectedResult);
    }
}
