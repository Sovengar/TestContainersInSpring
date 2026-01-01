package testing.junit5.parameterized;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.StringUtils;
import testing._config.BasicTestTags;
import testing._utils.FileApprovalTestBase;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static testing._utils.FileApprovalTestBase.scanForFileTestCases;

/**
 * 🧪 Test Parametrizado desde Fichero (Refactorizado)
 * 
 * Este test demuestra cómo cargar dinámicamente casos de prueba desde el
 * sistema de archivos
 * o el classpath, emparejando ficheros de entrada (.in.json) con los de salida
 * esperada (.out.json).
 */
@BasicTestTags
@DisplayName("JUnit 5: Tests Parametrizados desde Fichero")
class FileParameterizedTest {

    @ParameterizedTest(name = "Caso: {0}")
    @MethodSource("testData")
    void fileBasedTest(FileApprovalTestBase.FileTestCase2 testCase) throws IOException {
        // En un caso real aquí llamaríamos al servicio bajo test con
        // testCase.getInput()

        //Input input = jackson.readValue(testCase.inputFile(), Input.class);
        //Output expectedOutput = jackson.readValue(testCase.expectedOutputFile(), Output.class);
        String inputMessage = testCase.input().get("message").asText();
        String processedResult = inputMessage.toUpperCase();

        String actualResponse = """
                {
                    "status": "PROCESSED",
                    "result": "%s"
                }
                """.formatted(processedResult);

        // Validamos contra el contenido del fichero .out.json
        assertThatJson(actualResponse)
                .isEqualTo(testCase.expectedOutput());
    }

    public static List<FileApprovalTestBase.FileTestCase2> testData() throws IOException {
        Function<String, String> outputFileNameProvider = input -> input.replace(".in.json", ".out.json");
        return scanForFileTestCases("classpath:/testing/junit5/parameterized/message/*.in.json", outputFileNameProvider);
    }
}
