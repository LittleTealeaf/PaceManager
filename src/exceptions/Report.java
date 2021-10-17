package exceptions;

import java.util.Arrays;

public class Report {


    public Report() {
    }

    public Report(Exception exception) {
        System.out.println(exception.getMessage());
        System.out.println(Arrays.toString(exception.getStackTrace()));
    }
}
