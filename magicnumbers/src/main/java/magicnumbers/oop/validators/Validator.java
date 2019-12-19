package magicnumbers.oop.validators;

import magicnumbers.oop.FileReader;
import magicnumbers.oop.exceptions.FileTooSmallException;
import magicnumbers.oop.exceptions.UnsupportedExtensionException;

import java.io.IOException;

@FunctionalInterface
public interface Validator {
    String validate(FileReader fileReader) throws UnsupportedExtensionException, IOException, FileTooSmallException;
}
