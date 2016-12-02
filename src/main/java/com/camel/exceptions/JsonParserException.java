package com.camel.exceptions;

/**
 * Created by Mateusz Dobrowolski on 02.12.2016.
 */
public class JsonParserException extends RuntimeException {

    public JsonParserException() {
        super("Incorrect Json Data Format");
    }
}
