package interfaces;

import java.util.Arrays;

/**
 * Interface to allow for easy creation of a string builder. Add the following code snippets:
 *
 * <pre>
 * private String string;
 *
 * Enum() {
 *     string = asString();
 * }
 *
 * public String toString() {
 *     return string == null ? super.toString() : string;
 * }
 * </pre>
 */
public interface EnumStringable {

    default String asString() {
        StringBuilder builder = new StringBuilder();
        Arrays.asList(toString().split(" ")).forEach(string -> {
            builder.append(" ");
            builder.append(string.toUpperCase().charAt(0));
            builder.append(string.toLowerCase().substring(1));
        });
        return builder.substring(1);
    }
}
