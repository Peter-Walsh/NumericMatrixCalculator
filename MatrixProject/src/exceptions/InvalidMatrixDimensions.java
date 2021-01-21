package exceptions;

/**
 * Thrown when matrix operation cannot be performed when the inner matrix dimensions don't line
 * up
 */

public class InvalidMatrixDimensions extends Exception {
    public InvalidMatrixDimensions() {
        super("Matrices cannot be multiplied, inner dimensions don't line up");
    }
}
