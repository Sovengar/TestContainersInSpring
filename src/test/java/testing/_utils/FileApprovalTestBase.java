package testing._utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class FileApprovalTestBase {
    public static List<FileTestCase2> scanForFileTestCases(String locationPattern, Function<String, String> outputFileNameProvider) throws IOException {
        Resource[] inResources = new PathMatchingResourcePatternResolver().getResources(locationPattern);
        return Stream.of(inResources)
                .map(inResource -> constructTestCase(inResource, outputFileNameProvider)) //.map(inResource -> constructArguments(inResource, outputFileNameProvider))
                .toList();

        //return Stream.of(inResources)
          //      .map(inResource -> constructTestCase(inResource, outputFileNameProvider))
            //    .toList();
    }

    @SneakyThrows
    private static FileTestCase constructArguments(Resource inResource, Function<String, String> outputFileNameProvider) {
        String inputFileName = inResource.getFilename();
        String outFileName = outputFileNameProvider.apply(inputFileName);
        Resource outResource = inResource.createRelative(outFileName);
        String commonFileNamePrefix = StringUtils.getCommonPrefix(inputFileName, outFileName);
        String testDisplayName = commonFileNamePrefix.replaceAll("-+", " ");
        if (!outResource.exists()) {
            throw new IllegalArgumentException("No matching file found for " + inResource.getFilename() + ". Expected out filename = " + outResource.getFile().getAbsolutePath());
        }
        return new FileTestCase(testDisplayName, inResource.getFile(), outResource.getFile());
    }

    private static final ObjectMapper mapper = new ObjectMapper();
    private static FileTestCase2 constructTestCase(Resource inResource, Function<String, String> outputFileNameProvider) {
        try {
            String inputFileName = inResource.getFilename();
            String outFileName = outputFileNameProvider.apply(inputFileName);

            // ✅ Uso de createRelative: Mucho más robusto para JARs y diferentes protocolos
            Resource outResource = inResource.createRelative(outFileName);

            if (!outResource.exists()) {
                throw new IllegalArgumentException("No matching output file found for " + inputFileName
                        + ". Expected: " + outResource.getDescription());
            }

            // Generamos un nombre descriptivo basado en el prefijo común (ej: "hello" de
            // "hello.in.json")
            String displayName = inputFileName.substring(0, inputFileName.indexOf(".in.json"))
                    .replace("-", " ")
                    .replace("+", " ")
                    .replace("_", " ");

            // Cargamos los JSONs
            JsonNode input = mapper.readTree(inResource.getInputStream());
            JsonNode output = mapper.readTree(outResource.getInputStream());

            return new FileTestCase2(displayName, input, output);
        } catch (IOException e) {
            throw new RuntimeException("Error loading test case from " + inResource.getFilename(), e);
        }
    }

    public record FileTestCase(String displayName, File inputFile, File expectedOutputFile) {
        @Override
        public String toString() {
            return displayName + " (" + inputFile.getName() + " -> " + expectedOutputFile.getName() + ")";
        }
    }

    /**
     * Record para representar un caso de prueba físico.
     */
    public record FileTestCase2(String displayName, JsonNode input, JsonNode expectedOutput) {
        @Override
        public String toString() {
            return displayName;
        }
    }
}
