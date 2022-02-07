package app;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        File file = new File("docs");
        System.out.println(file.getPath());
    }
}