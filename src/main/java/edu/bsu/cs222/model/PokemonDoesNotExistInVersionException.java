package edu.bsu.cs222.model;

public class PokemonDoesNotExistInVersionException extends Exception {
    public PokemonDoesNotExistInVersionException(String errorMessage) {
        super(errorMessage);
    }
}
