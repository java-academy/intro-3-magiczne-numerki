package magicnumbers.oop;

import magicnumbers.oop.exceptions.FileTooSmallException;
import magicnumbers.oop.exceptions.UnsupportedExtensionException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static magicnumbers.oop.MagicNumbersProperties.NUMBER_OF_BYTES;

public class FileReader {
    private String filePath;
    private Extension extension;
    private List<Integer> decimalFileContent;
    private String hexFileContent;

    public FileReader(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Missing file path.");
        } else {
            this.filePath = args[0];
        }
    }

    public Extension getExtension() {
        return extension;
    }

    public List<Integer> getDecimalFileContent() {
        return decimalFileContent;
    }

    public String getHexFileContent() {
        return hexFileContent;
    }

    String readFile() throws FileTooSmallException, IOException {
        this.decimalFileContent = readFileInDecimal();
        this.hexFileContent = readFileInHex();
        return Paths.get(filePath).getFileName().toString();
    }

    String readExtension() throws UnsupportedExtensionException {
        String ext = filePath.substring(filePath.lastIndexOf('.') + 1).toUpperCase();
        try {
            extension = Extension.valueOf(ext);
            return ext;
        } catch (IllegalArgumentException e) {
            throw new UnsupportedExtensionException("Extension " + ext + " is unsupported");
        }
    }

    private List<Integer> readFileInDecimal() throws FileTooSmallException, IOException {
        File file = new File(filePath);
        List<Integer> fileContent = new ArrayList<>();
        try (FileInputStream in = new FileInputStream(file)) {
            int currentByte;
            int i = 0;
            while ((currentByte = in.read()) != -1 && i < NUMBER_OF_BYTES) {
                fileContent.add(currentByte);
                i++;
            }
            if (i < NUMBER_OF_BYTES) {
                throw new FileTooSmallException("File is too small and does not contain enough amount of bytes to validate it");
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File cannot be found.");
        } catch (IOException e) {
            throw new IOException("There was a problem with file. I couldn't open and read it.");
        }
        return fileContent;
    }

    private String readFileInHex() {
        StringBuilder hexRepresentation = new StringBuilder();
        for (Integer value : decimalFileContent) {
            hexRepresentation.append(String.format("%02X ", value));
        }
        return hexRepresentation.toString();
    }
}
