import java.util.Scanner;
import java.util.Arrays;

class Driver {
    public static void main(String[] args) {
        double[][] matrix = { {0.396796, -0.214938, 0.276735}, {5.19655, -2.06983, -0.388886}, {-3.3797, 1.50219, 0.159794}};
        SquareMatrix m = new SquareMatrix(3, matrix);
        System.out.println("Determinant of m: " + m.determinant);
        Matrix minors = m.getInverse();
        for (double[] row : minors.matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}

class Matrix {
    protected int numRows;
    protected int numCols;
    protected double[][] matrix;

    public Matrix(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.matrix = new double[numRows][numCols];
    }

    public Matrix(int numRows, int numCols, double[][] matrix) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.matrix = matrix;
    }

    public void setMatrixValue(double value, int rowPos, int colPos) {
        this.matrix[rowPos][colPos] = value;
    }

    public void fillMatrixWithInput() {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numCols; j++) {
                this.setMatrixValue(scan.nextDouble(), i, j);
            }
        }
    }

    public Matrix multiplyByScalar(double scalar)  {
        Matrix result = new Matrix(this.numRows, this.numCols);
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                result.setMatrixValue(this.matrix[i][j] * scalar, i, j);
            }
        }
        return result;
    }

    // Might not work
    public Matrix transposeMainDiagonal() {
        Matrix result = new Matrix(this.numCols, this.numRows);
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                result.matrix[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    public Matrix transposeSideDiagonal() {
        Matrix result = new Matrix(this.numCols, this.numRows);
        for (int i = 0, k1 = this.matrix.length - 1; i < this.matrix.length; i++, k1--) {
            for (int j = 0, k2 = this.matrix[i].length - 1; j < this.matrix[i].length; j++, k2--) {
                result.matrix[j][i] = this.matrix[k1][k2];
            }
        }
        return result;
    }

    public Matrix transposeVertical() {
        Matrix result = new Matrix(this.numRows, this.numCols);
        for (int i = 0; i < this.matrix.length; i++) {

            for (int j = 0, k = this.matrix[i].length - 1; j < this.matrix[i].length; j++, k--) {
                result.matrix[i][j] = this.matrix[i][k];
            }
        }

        return result;
    }

    public Matrix transposeHorizontal() {
        Matrix result = new Matrix(this.numRows, this.numCols);
        for (int i = 0, k = this.matrix.length - 1; i < this.matrix.length; i++, k--) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                result.matrix[i][j] = this.matrix[k][j];
            }
        }
        return result;
    }

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

    public void printMatrix() {
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numCols; j++) {
                System.out.print(this.matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

class SquareMatrix extends Matrix {

    protected double determinant;

    public SquareMatrix(int size) {
        super(size, size);
    }

    public SquareMatrix(int size, double[][] matrix) {
        super(size, size, matrix);
        this.determinant = findDeterminant();
    }

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

    public void updateDet() {
        this.determinant = findDeterminant();
    }

    public double[][] matrixOfMinors() {
        SquareMatrix minors = new SquareMatrix(this.matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                minors.matrix[i][j] = matrixOfMinorsHelper(i, j, this);
            }
        }
        return minors.matrix;
    }

    public double[][] getCofactorMatrix() {
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

    public Matrix getInverse() {
        double[][] cofactors = getCofactorMatrix();
        Matrix temp = new Matrix(cofactors.length, cofactors.length, cofactors).transposeMainDiagonal();
        return temp.multiplyByScalar(1 / determinant);
    }

    private static double matrixOfMinorsHelper(int row, int col, SquareMatrix m) {
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


class IdentityMatrix extends Matrix {

    public IdentityMatrix(int numRows, int numCols) throws InvalidMatrixDimensions {
        super(numRows, numCols);
        if (numRows != numCols) {
            throw new InvalidMatrixDimensions();
        }
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (i == j) {
                    super.setMatrixValue(1, i, j);
                } else {
                    super.setMatrixValue(0, i, j);
                }
            }
        }

    }
}
class InvalidMatrixDimensions extends Exception {
    public InvalidMatrixDimensions() {
        super("Matrices cannot be multiplied, inner dimensions don't line up");
    }
}

