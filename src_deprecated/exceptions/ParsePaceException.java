package exceptions;

import java.io.File;

public class ParsePaceException extends Exception {

    public ParsePaceException() {
        super("JSON could not be parsed into a Pace object");
    }

    public ParsePaceException(File file) {
        super("File could not be parsed: " + file.getPath());
    }
}
