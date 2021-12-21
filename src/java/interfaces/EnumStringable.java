package interfaces;

import java.util.Arrays;

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
