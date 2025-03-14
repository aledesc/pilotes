package com.tui.pilotes.client;

public class InvalidEmailException extends Exception {
    public InvalidEmailException() {
        super("Email dir is not valid!");
    }
}
