package org.example.streams;

import org.example.functions.Persona;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StreamsTest {

    @Test
    @DisplayName("Creating Stream with several initializer")
    void testingStreams() throws IOException {
        Persona jotxee = new Persona ("Jotxee","apellido1","apellido2");
        Persona miriam = new Persona ("Miriam", "apellido1", "apellido2");

        // Collection stream()
        List<Persona> personas = Arrays.asList(jotxee, miriam);
        Stream<Persona> personaStream = personas.stream();

        // Arrays.asStream()
        Persona[] personasArray = {jotxee, miriam};
        Stream<Persona> personaStream1 = Arrays.stream(personasArray);

        // Utilidades de stream
        Stream<Persona> personaStream2 = Stream.of(jotxee, miriam);

        // otras APIs
        Stream<String> persoStream3 = Files.lines(Path.of("./src/test/java/resources/fichero.txt"));
        persoStream3.forEach(System.out::println);
    }

    @Test
    @DisplayName("Final operations")
    void testingStreams1() {
        final String expected = """
                
                Jotxee
                Miriam""";
        String todos = Stream.of("Jotxee" , "Miriam")
                .reduce("", (a, b) -> a + "\n" + b);

        System.out.println("TODOS: " + todos);

        assertNotNull(todos);
        assertEquals(expected, todos);
    }

    @Test
    @DisplayName("Combine intermediate operations with final operations")
    void testingStreams2() {
        final String expected = """
                
                'Jotxee'
                'Miriam'""";
        final List<Persona> personaStream = Arrays.asList(
            new Persona ("Jotxee","apellido1","apellido2"),
            new Persona ("Miriam", "apellido1", "apellido2")
        );

        String todos = personaStream.stream()
                .map(Persona::getNombre)
                .sorted((name1, name2) -> name1.length() - name2.length()) // Comparator.comparingInt(String::length)
                .map(name -> "'" + name + "'")
                .reduce("", (a, b) -> a + "\n" + b); // final operation

        System.out.println("TODOS: " + todos);
        assertNotNull(todos);
        assertEquals(expected, todos);
    }

    @Test
    @DisplayName("Another test with intermediate operation")
    void testingStreams3() {
        final Stream<String> strings = Stream.of("b", "c", "a");

        strings.sorted();

        assertThrows(IllegalStateException.class, () -> { // throw exception porque cuando se invoka el método sorted() este devuelve un nuevo stream y el primero queda invalidado
            String res = strings.reduce("", String::concat);
        });

        assertDoesNotThrow(() -> {
            // Al ejecutar la invocación del método sorted() en la misma pipeline del Stream no se produce Exception
            // esto pasa porque se sólo se invoka el método cuando se realiza un método final como "reduce"
            final var res = Stream.of("b", "c", "a").sorted().reduce("", String::concat);
            assertEquals("abc", res);
        });


        // En el caso anterior a pesar de que no se produce excepción en realidad es algo muy poco eficiente
        // a partir de aquí veremos como realizar la misma iteración de forma eficiente con método mutables:
        // Como el uso de String.builder
        final Stream<String> stream = Stream.of("b", "c", "a");

        StringBuilder sb = new StringBuilder();
        stream.forEach(sb::append); // Método ForEach recibe un Consumer que no devuelve nada que realiza una mutación sobre una estructura mutable


    }

    @Test
    @DisplayName("Spliterator")
    void testingStreams4() {
        final Stream<String> strings = Stream.of("b", "c", "a");

        strings.sorted();

        // removeIf -> se eliminal los elementos que cumplan el Predicado
        // java.util.list tiene:
            // - Replace All
            // - sort (es la vesión sencilla para no tener que usar Collections)

        // java.util.map tiene otras interfaces  como :
            // defautl V replace (K key, V value)
            // default boolean replace (K key, V oldValue, V newValue)
            // default V putIfAbsent(K key, V value)
            // default boolean remove (Object key, Object value)
            // default void replaceAll(Bifunction<? super K, ? super V, ? extends V> function)  -> function(key, value)-> new value
            // default V compute(K key, Bifunction<? super K, ? super V, ? extends V> remappingFunction)
            // default V computeIfPresent(K key, Bifunction<? super K, ? super V, ? extends V> remappingFunction)
            // default V computeIfAbsent(K key, Function<? super K, ? super V, ? extends V> mappingFunction)
            // default V merge(K key, V value, BiFunction<? super K, ? super V, ? extends V> remappingFunction)
    }

    @Test
    @DisplayName("Comparator")
    void testingStreams5() {
        final Stream<String> strings = Stream.of("b", "c", "a");

        strings.sorted();

        // public static <T> Comparator<T> comparingInt (ToIntFunction<? super T> keyExtractor)
        // ejemplo: lista sort(Comparator.comparingInt(Persona::getNacimiento));


    }
}
