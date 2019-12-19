package magicnumbers.oop;

import magicnumbers.oop.exceptions.FileTooSmallException;
import magicnumbers.oop.exceptions.UnsupportedExtensionException;
import magicnumbers.oop.validators.FileValidator;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Answer.Builder answer = new Answer.Builder();

        try {
            FileReader fileReader = new FileReader(args);

            answer
                    .readingFile(fileReader.readFile())
                    .withMagicNumbers(fileReader.getHexFileContent())
                    .withFileExtension(fileReader.readExtension())
                    .whenRealExtensionIs(FileValidator.getRealExtension(fileReader))
                    .soValidationResultIs(FileValidator.validateFile(fileReader));

        } catch (IOException | FileTooSmallException | UnsupportedExtensionException e) {
            answer.programFailed(e.getMessage());
        }

        System.out.println(answer.build().say());

    }
}
