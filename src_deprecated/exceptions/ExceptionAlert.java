package exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class ExceptionAlert extends Alert {

    public ExceptionAlert(Exception exception) {
        super(AlertType.ERROR, exception.getLocalizedMessage(), ButtonType.CANCEL, ButtonType.YES);
        setHeaderText("Do you want to report this incident");
        setTitle("Exception Found");
        Optional<ButtonType> optional = showAndWait();
        if (optional.isPresent() && optional.get() == ButtonType.YES) {
            new Report(exception);
        }
    }
}
