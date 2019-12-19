package magicnumbers.imper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class MagicNumbers {

    public static void main(String[] args) throws IOException {

        final Map<String, List<String>> magicNumbers = new HashMap<>();
        magicNumbers.put("jpg", Arrays.asList("^FF D8 FF DB.+",
                "^FF D8 FF E0 00 10 4A 46 49 46 00 01.+",
                "^FF D8 FF EE.+",
                "^FF D8 FF E1 ..... 45 78 69 66 00 00.+"));
        magicNumbers.put("gif", Arrays.asList("^47 49 46 38 37 61.+",
                "^47 49 46 38 39 61.+"));
        magicNumbers.put("txt", Collections.singletonList("^(([0-9])|([0-9][0-9])|([0-1][0-1][0-9])|([0-1][0-2][0-7]))$"));
        magicNumbers.put("png", Collections.singletonList("^89 50 4E 47 0D 0A 1A 0A.+"));


        String filePath;
        if (args.length < 1) {
            throw new IllegalArgumentException("Missing file path");
        } else {
            filePath = args[0];
        }

        final String extension = filePath.substring(filePath.lastIndexOf('.') + 1);
        if (magicNumbers.containsKey(extension)) {
            File file = new File(filePath);
            try (FileInputStream in = new FileInputStream(file)) {

                int currentByte;
                List<Integer> fileContent = new ArrayList<>();
                while ((currentByte = in.read()) != -1) {
                    fileContent.add(currentByte);
                }

                if (extension.equals("txt")) {
                    if (fileContent.stream().allMatch(x -> x.toString().matches(magicNumbers.get(extension).get(0)))) {
                        System.out.println("Given file with " + extension + " is proper txt file");
                    } else {
                        System.out.println("Given file with " + extension + " is not a txt file.");
                    }
                } else {
                    StringBuilder hexRepresentation = new StringBuilder();
                    for (Integer value : fileContent) {
                        hexRepresentation.append(String.format("%02X ", value));
                    }
                    if (magicNumbers.get(extension).stream().anyMatch(hexRepresentation.toString()::matches)) {
                        System.out.println("Given file with " + extension + " is a real file.");
                    } else {
                        System.out.println("Wrong file. Given " + extension + " is fake one.");
                    }
                }
            }
        } else {
            System.err.println("Unsupported file format");
        }
    }
}
