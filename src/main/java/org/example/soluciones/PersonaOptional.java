package org.example.soluciones;

import java.util.Optional;

public class PersonaOptional {
    private String nombre;
    private String apellido1;
    private Optional<String> apellido2;

    public PersonaOptional(String nombre, String apellido1, Optional<String> apellido2) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public Optional<String> getApellido2() {
        return apellido2;
    }

    @Override
    public String toString() {
        return nombre + "," + apellido1 + " " + apellido2.orElse("");
    }
}
