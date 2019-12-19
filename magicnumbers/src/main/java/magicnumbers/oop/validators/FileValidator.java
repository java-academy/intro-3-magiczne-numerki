package magicnumbers.oop.validators;

import magicnumbers.oop.Extension;
import magicnumbers.oop.FileReader;
import magicnumbers.oop.exceptions.FileTooSmallException;
import magicnumbers.oop.exceptions.UnsupportedExtensionException;

import java.io.IOException;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static magicnumbers.oop.Extension.*;
import static magicnumbers.oop.MagicNumbersProperties.*;

public class FileValidator {


    private static final Map<Extension, Validator> VALIDATORS = new EnumMap<>(Extension.class) {{
        put(GIF, fileReader ->
                validate(
                        MAGIC_NUMBERS.keySet().stream().filter(mn -> MAGIC_NUMBERS.get(mn).equals(GIF)).collect(Collectors.toUnmodifiableList()),
                        fileReader.getHexFileContent()));
        put(JPG, fileReader ->
                validate(
                        MAGIC_NUMBERS.keySet().stream().filter(mn -> MAGIC_NUMBERS.get(mn).equals(JPG)).collect(Collectors.toUnmodifiableList()),
                        fileReader.getHexFileContent()));
        put(TXT, fileReader ->
                validateTXTFile(PLAIN_TEXT_REGEX, fileReader.getDecimalFileContent()));
    }};


    public static Extension getRealExtension(FileReader fileReader) {
        String hexRepresentation = fileReader.getHexFileContent();

        String magicNumber = MAGIC_NUMBERS.keySet()
                .stream()
                .filter(hexRepresentation::matches)
                .findFirst().orElse(null);

        return MAGIC_NUMBERS.getOrDefault(magicNumber, UNKNOWN);
    }

    public static String validateFile(FileReader fileReader) throws UnsupportedExtensionException, FileTooSmallException, IOException {
        return VALIDATORS.get(fileReader.getExtension()).validate(fileReader);
    }

    private static String validate(List<String> magicNumbers, String fileContent) {
        return magicNumbers.stream().anyMatch(fileContent::matches) ? SUCCESS_VALIDATION_STATEMENT : FAILED_VALIDATION_STATEMENT;
    }

    private static String validateTXTFile(String plainTextRegex, List<Integer> fileContent) {
        return fileContent.stream().allMatch(x -> x.toString().matches(plainTextRegex)) ? SUCCESS_TXT_VALIDATION_STATEMENT : FAILED_VALIDATION_STATEMENT;
    }
}
