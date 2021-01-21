package matricies;

public class SquareMatrix extends Matrix {

    protected double determinant;

    /**
     * Creates SquareMatrix with specified size
     *
     * @param size - The size of the matrix where, rows == cols
     */

    public SquareMatrix(int size) {
        super(size, size);
    }

    /**
     * Create SquareMatrix from a given 2D array
     *
     * @param size - Takes the size of the matrix, NOT NECESSARY SHOULD CHANGE
     *
     * @param matrix - The 2D array used to construct the new matrix object
     */

    public SquareMatrix(int size, double[][] matrix) {
        super(size, size, matrix);
        this.determinant = findDeterminant();
    }

    /**
     * Calculates the determinant of the matrix via converting the matrix to
     * upper-triangular form and multiplying the values along the diagonal
     *
     * @return - Returns the determinant of the matrix, or the product of the values
     * along the main diagonal of the SquareMatrix in upper-triangular form
     */

    public double findDeterminant() {
        double num, factor, determinant = 1;
        double[][] temp = new double[this.matrix.length][this.matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                temp[i][j] = this.matrix[i][j];
            }
        }

        for (int i = 0; i < this.matrix.length; i++) {
            double pivot = temp[i][i];

            for (int j = i + 1; j < this.matrix.length; j++) {
                num = temp[j][i];
                factor = (-num / pivot);
                for (int k = 0; k < this.matrix.length; k++) {
                    temp[j][k] += temp[i][k] * factor;
                }
            }
        }
        for (int i = 0; i < this.matrix.length; i++) {
            determinant *= temp[i][i];
        }

        return determinant;
    }

    /**
     * Updates the current value of the determinant of the SquareMatrix
     */

    public double getDeterminant() {
        return this.determinant;
    }

    public void updateDet() {
        this.determinant = findDeterminant();
    }

    private double[][] matrixOfMinors() {
        SquareMatrix minors = new SquareMatrix(this.matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                minors.matrix[i][j] = matrixOfMinorsHelper(i, j, this);
            }
        }
        return minors.matrix;
    }

    private double[][] getCofactorMatrix() {
        double[][] cofactors = this.matrixOfMinors();
        for (int i = 0; i < cofactors.length; i++) {
            for (int j = 0; j < cofactors.length; j++) {
                if (!(i % 2 == 0 && j % 2 == 0) && !(i % 2 != 0 && j % 2 != 0)) {
                    cofactors[i][j] *= -1;
                }
            }
        }
        return cofactors;
    }

    /**
     * Calculates the inverse of the matrix by calculating by the following method:
     *
     * 1.) Calculate the matrix of minors
     * 2.) Calculate the cofactor matrix from the matrix of minors
     * 3.) Transpose along main diagonal of the cofactor matrix
     * 4.) Multiply by (1 / determinant)
     *
     * Relies on following methods:
     *
     *      - getCofactorMatrix()
     *      - matrixOfMinors()
     *      - matrixOfMinorsHelper(int row, int col, SquareMatrix m)
     *      - multiplyByScalar(double scale)
     *
     * @return - Returns a new Matrix object that contains the inverse of the SquareMatrix
     */

    public Matrix getInverse() {
        double[][] cofactors = getCofactorMatrix();
        Matrix temp = new Matrix(cofactors.length, cofactors.length, cofactors).transposeMainDiagonal();
        return temp.multiplyByScalar(1 / determinant);
    }

    private double matrixOfMinorsHelper(int row, int col, SquareMatrix m) {
        double[][] temp = new double[m.matrix.length - 1][m.matrix.length - 1];
        for (int i = 0, r = 0; i < m.matrix.length; i++) {
            for (int j = 0, c = 0; j < m.matrix.length; j++) {
                if (row != i && col != j) {
                    temp[r][c] = m.matrix[i][j];
                    c++;
                }
            }
            if (row != i) {
                r++;
            }
        }
        return new SquareMatrix(temp.length, temp).determinant;
    }
}
