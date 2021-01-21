package matricies;

import exceptions.InvalidMatrixDimensions;

import java.util.Scanner;

/**
 * Matrix class, can be used to create matrix of m*n dimensions where,
 * m == n OR m != n.
 */

public class Matrix {
    protected int numRows;
    protected int numCols;
    protected double[][] matrix;

    /**
     * Constructs a Matrix object with given number of rows and columns
     * @param numRows - Number of rows in the matrix
     * @param numCols - Number of columns in the matrix
     */

    public Matrix(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.matrix = new double[numRows][numCols];
    }

    /**
     * Constructs a Matrix object derived from the given 2D array
     * @param numRows - Number of rows in matrix -> NOT NECCESSARY SHOULD REMOVE
     * @param numCols - Number of columns in matrix -> NOT NECCESSARY SHOULD REMOVE
     * @param matrix - 2D array that will be used as the matrix
     */

    public Matrix(int numRows, int numCols, double[][] matrix) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.matrix = matrix;
    }

    /**
     * Sets a specified position in a matrix to a given value.
     * @param value - Value to be added to the matrix
     * @param rowPos - Row position (starts at 0)
     * @param colPos - Column position (starts at 0)
     */

    public void setMatrixValue(double value, int rowPos, int colPos) {
        this.matrix[rowPos][colPos] = value;
    }

    /**
     * Fills the matrix with input from the user by scanning in values of
     * the matirx
     */

    public void fillMatrixWithInput() {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numCols; j++) {
                this.setMatrixValue(scan.nextDouble(), i, j);
            }
        }
    }

    /**
     * Multiplies each value in matrix by a scalar value
     * @param scalar - Scalar value to multiply matrix by
     * @return - Returns a new matrix object, with a copy of the old matrix,
     * multiplied by the scalar
     */

    public Matrix multiplyByScalar(double scalar)  {
        Matrix result = new Matrix(this.numRows, this.numCols);
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                result.setMatrixValue(this.matrix[i][j] * scalar, i, j);
            }
        }
        return result;
    }

    /**
     * Transposes the matrix along it's main diagonal and returns a new matrix
     * object, with the transposed matrix
     *
     * Main.Main diagonal starts at upper-left most position in the matrix
     *
     * @return - Returns a new matrix object, where the matrix value has been
     * transposed along the main diagonal of the original matrix
     */

    public Matrix transposeMainDiagonal() {
        Matrix result = new Matrix(this.numCols, this.numRows);
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                result.matrix[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    /**
     * Transposes matrix along it's side diagonal
     *
     * Side diagonal starts at the bottom-left most position in the matrix
     *
     * @return - Returns a new matrix object, where the matrix value has been
     * transposed along the side diagonal of the original matrix
     */

    public Matrix transposeSideDiagonal() {
        Matrix result = new Matrix(this.numCols, this.numRows);
        for (int i = 0, k1 = this.matrix.length - 1; i < this.matrix.length; i++, k1--) {
            for (int j = 0, k2 = this.matrix[i].length - 1; j < this.matrix[i].length; j++, k2--) {
                result.matrix[j][i] = this.matrix[k1][k2];
            }
        }
        return result;
    }

    /**
     * Transposes matrix along it's vertical
     *
     * @return - Returns a new matrix object that contains the result of transposing along
     * the vertical of the original
     */

    public Matrix transposeVertical() {
        Matrix result = new Matrix(this.numRows, this.numCols);
        for (int i = 0; i < this.matrix.length; i++) {

            for (int j = 0, k = this.matrix[i].length - 1; j < this.matrix[i].length; j++, k--) {
                result.matrix[i][j] = this.matrix[i][k];
            }
        }

        return result;
    }

    /**
     * Transposes the matrix along it's horizontal
     * @return - Returns a new matrix object that contains the result of transposing along
     * the horizontal of the original
     */

    public Matrix transposeHorizontal() {
        Matrix result = new Matrix(this.numRows, this.numCols);
        for (int i = 0, k = this.matrix.length - 1; i < this.matrix.length; i++, k--) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                result.matrix[i][j] = this.matrix[k][j];
            }
        }
        return result;
    }

    /**
     * Multiplies two matrices
     *
     * @param m1 - The matrix that the current matrix object is being multiplied by
     *
     * @return - Returns a new matrix object that contains the result of the
     * multiplication of the two matrices
     *
     * @throws InvalidMatrixDimensions - Occurs when the inner dimensions of the two
     * matrices don't match up, and therefore cannot be multiplied
     */

    public Matrix multiply2Matrices(Matrix m1) throws InvalidMatrixDimensions{
        Matrix result = new Matrix(this.numRows, m1.numCols);
        double numToAdd;
        if (this.numCols != m1.numRows) {
            throw new InvalidMatrixDimensions();
        } else {

            for (int i = 0; i < this.numRows; i++) {

                for (int j = 0; j < m1.numCols; j++) {

                    numToAdd = 0;
                    for (int k = 0; k < this.numCols; k++) {
                        numToAdd += this.matrix[i][k] * m1.matrix[k][j];
                    }

                    result.setMatrixValue(numToAdd, i, j);
                }
            }
        }
        return result;
    }

    /**
     * Sums the values of two matrices
     *
     * @param m1 - The matrix to be added to the original
     *
     * @return - Returns a new matrix object that contains the result of the summation
     * of the original matrix and m1
     *
     * @throws InvalidMatrixDimensions - Throws an error if the matrices don't have the same dimensions
     */

    public Matrix sum2Matrices(Matrix m1) throws InvalidMatrixDimensions {
        if (this.numRows != m1.numRows || this.numCols != m1.numCols) {
            throw new InvalidMatrixDimensions();
        }
        Matrix result = new Matrix(this.numRows, this.numCols);
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix.length; j++) {
                result.setMatrixValue(this.matrix[i][j] + m1.matrix[i][j], i, j);
            }
        }
        return result;
    }

    /**
     * Prints the contents of a given matrix, might be easier or simpler to
     * create a toString() method here instead
     */

    public void printMatrix() {
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numCols; j++) {
                System.out.print(this.matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
