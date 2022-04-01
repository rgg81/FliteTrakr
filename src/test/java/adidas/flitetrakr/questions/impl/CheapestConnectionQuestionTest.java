package adidas.flitetrakr.questions.impl;

import adidas.flitetrakr.graph.Graph;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

import static adidas.flitetrakr.graph.Converter.fromInputString;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CheapestConnectionQuestionTest {

    @ParameterizedTest(name = "cheapest price of the connection {0} = {1}")
    @CsvSource({
            "NUE-AMS,     NUE-FRA-AMS-60",
            "AMS-FRA,     NONE",
            "LHR-LHR,     LHR-NUE-FRA-LHR-93"
    })
    void calc(String path, String expectedResult) {
        String data = "Connections: NUE-FRA-43, NUE-AMS-67, FRA-AMS-17, FRA-LHR-27,LHR-NUE-23";
        Graph graph = new Graph(fromInputString(data));
        CheapestConnectionQuestion cheapestConnectionQuestion = new CheapestConnectionQuestion(graph);
        Optional<String> expectedResultOpt = expectedResult.equals("NONE") ? Optional.empty() : Optional.of(expectedResult);
        assertEquals(expectedResultOpt, cheapestConnectionQuestion.answer(path),
                () -> path + " cheapest should equal " + expectedResult);
    }
}
