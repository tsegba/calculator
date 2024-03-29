package fr.canal.canal.domain.error;

public class InvalidOperandException extends RuntimeException{
    public InvalidOperandException(final String errorMessage) {
        super(errorMessage);
    }
}
