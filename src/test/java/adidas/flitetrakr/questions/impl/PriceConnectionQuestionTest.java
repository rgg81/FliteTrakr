package adidas.flitetrakr.questions.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import adidas.flitetrakr.graph.Graph;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

import static adidas.flitetrakr.graph.Converter.fromInputString;

class PriceConnectionQuestionTest {

    @ParameterizedTest(name = "price of the connection {0} = {1}")
    @CsvSource({
            "NUE-FRA-LHR,     70",
            "NUE-AMS-LHR,     -1",
            "NUE-FRA-LHR-NUE, 93"
    })
    void calc(String path, int expectedResult) {
        String data = "Connections: NUE-FRA-43, NUE-AMS-67, FRA-AMS-17, FRA-LHR-27,LHR-NUE-23";
        Graph graph = new Graph(fromInputString(data));
        PriceConnectionQuestion priceConnectionQuestion = new PriceConnectionQuestion(graph);
        Optional<Integer> expectedResultOpt = expectedResult < 0 ? Optional.empty() : Optional.of(expectedResult);
        assertEquals(expectedResultOpt, priceConnectionQuestion.answer(path),
                () -> path + " cost should equal " + expectedResult);
    }
}
