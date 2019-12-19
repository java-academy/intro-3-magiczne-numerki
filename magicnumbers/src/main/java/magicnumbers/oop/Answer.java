package magicnumbers.oop;

import java.util.Objects;

final class Answer {

    private final String file;
    private final String magicNumbers;
    private final String fileExtension;
    private final Extension realExtension;
    private final String result;
    private final String failReason;


    private Answer(Builder builder){
        this.file = builder.file;
        this.magicNumbers = builder.magicNumbers;
        this.fileExtension = builder.fileExtension;
        this.realExtension = builder.realExtension;
        this.result = builder.result;
        this.failReason = builder.failReason;
    }

    String say(){
        return Objects.requireNonNullElseGet(failReason, () -> "There was processed file " + file + " which has magic numbers: " + magicNumbers +
                "and it's extension is: " + fileExtension + ", while the real extension is " + realExtension.toString() +
                ", so the file " + result);
    }

    static class Builder {

        private String file;
        private String magicNumbers;
        private String fileExtension;
        private Extension realExtension;
        private String result;
        private String failReason;


        Builder readingFile(String file) {
            this.file = file;
            return this;
        }

        Builder withMagicNumbers(String magicNumbers) {
            this.magicNumbers = magicNumbers;
            return this;
        }

        Builder withFileExtension(String fileExtension) {
            this.fileExtension = fileExtension;
            return this;
        }

        Builder whenRealExtensionIs(Extension realExtension) {
            this.realExtension = realExtension;
            return this;
        }

        void soValidationResultIs(String result){
            this.result = result;
        }

        void programFailed(String failReason) {
            this.failReason = failReason;
        }

        Answer build(){
            return new Answer(this);
        }

    }
}
