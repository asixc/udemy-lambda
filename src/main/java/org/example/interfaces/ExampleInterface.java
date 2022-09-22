package org.example.interfaces;

import java.util.List;
import java.util.logging.Logger;

public interface ExampleInterface {

    Logger logger = Logger.getLogger(ExampleInterface.class.getName());

    // Default Methods:
    default void showString(final List<String> cadenas) {
        cadenas.stream().forEach((cadena) -> System.out.println("default showStringinfo: [" + cadena +"]"));
    }

    static void showString(String s) {
        System.out.println(s);
    }
}
