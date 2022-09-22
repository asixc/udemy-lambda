package org.example.interfaces;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Testing Interface")
public class ExampleInterfaceTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void attachLogCapturer() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("Testing default method")
    void when_invoke_default_method_show_logs() {

        final var listNames = List.of("Jotxee", "Jhon");
        final var example = new ExampleInterfaceImpl();

        example.showString(listNames);

        assertEquals("default showStringinfo: [Jotxee]default showStringinfo: [Jhon]", outputStreamCaptor.toString()
                .replaceAll("\n", "")
                .replaceAll("\r", ""));
        //replaceAll("\\s+","")); whitespaces
    }

    @Test
    @DisplayName("Testing static method")
    void when_invoke_static_method_show_logs(){
        final var listNames = List.of("Jotxee", "Jhon");

        listNames.stream().forEach(ExampleInterface::showString);

        assertEquals("JotxeeJhon", outputStreamCaptor.toString()
                .replaceAll("\n", "")
                .replaceAll("\r", ""));
    }
}