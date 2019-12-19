package magicnumbers.oop;

import java.util.HashMap;
import java.util.Map;

import static magicnumbers.oop.Extension.GIF;
import static magicnumbers.oop.Extension.JPG;

public final class MagicNumbersProperties {

    public static final int NUMBER_OF_BYTES = 18;
    public static final String PLAIN_TEXT_REGEX = "^(([0-9])|([0-9][0-9])|([0-1][0-1][0-9])|([0-1][0-2][0-7]))$";
    public static final String SUCCESS_VALIDATION_STATEMENT = "is real file.";
    public static final String FAILED_VALIDATION_STATEMENT = "is fake file.";
    public static final String SUCCESS_TXT_VALIDATION_STATEMENT = "might be real TXT file.";

    public static final Map<String, Extension> MAGIC_NUMBERS = new HashMap<>() {{
        put("^47 49 46 38 37 61.+", GIF);
        put("^47 49 46 38 39 61.+", GIF);
        put("^FF D8 FF DB.+", JPG);
        put("^FF D8 FF EE.+", JPG);
        put("^FF D8 FF E0 00 10 4A 46 49 46 00 01.+", JPG);
        put("^FF D8 FF E1 ..... 45 78 69 66 00 00.+", JPG);
    }};
}
