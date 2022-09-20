package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Testing ComparatorTest")
public class ComparatorTest {

    private final Logger logger = Logger.getLogger(ComparatorTest.class.getName());

    @Test
    @DisplayName("Ordenando por un Comparator custom")
    public void test_ordenacion_inversa() {

        List<String> nombres = Arrays.asList("Juan", "Antonia", "Pedro");

        // asigna a comparadorLongitud un comparador que ordene los strings
        // segun la longitud de MAYOR a MENOR (es decir, al contrario que el ejemplo de
        // la presentacion.
        // * solo debes modificar la siguiente linea, el resto de codigo debe quedar igual *
        Comparator<String> comparadorLongitud = (o1, o2) -> o2.length() - o1.length();

        assertNotNull(comparadorLongitud, "No has creado aun el comparador");

        logger.info("Resultado previo: "+ nombres);
        Collections.sort(nombres,comparadorLongitud);
        logger.info("Resultado con short custom: "+ nombres);

        assertEquals("Antonia",  nombres.get(0), "El primer elemento deberia ser Antonia");
        assertEquals("Pedro", nombres.get(1), "El segundo elemnento deberia ser Pedro");
        assertEquals("Juan", nombres.get(2), "El tercer elemento debeia ser Juan");
    }
}
