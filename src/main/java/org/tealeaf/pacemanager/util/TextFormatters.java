package org.tealeaf.pacemanager.util;

import javafx.scene.control.TextFormatter;
import org.tealeaf.pacemanager.constants.ProjectNameRestrictions;

public class TextFormatters {

    public static TextFormatter<?> projectNameFormatter() {
        return new TextFormatter<>(TextFormatters::applyProjectFormatter);
    }

    private static TextFormatter.Change applyProjectFormatter(TextFormatter.Change change) {
        if (!change.isContentChange()) {
            return change;
        }
        String text = change.getControlNewText();
        if (text.length() > ProjectNameRestrictions.MAX_LENGTH) {
            return null;
        }
        for (char c : ProjectNameRestrictions.ILLEGAL_CHARACTERS.toCharArray()) {
            if (text.contains(Character.toString(c))) {
                return null;
            }
        }
        return change;
    }
}
