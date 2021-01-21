package Main;

import exceptions.InvalidMatrixDimensions;
import matricies.Matrix;
import matricies.SquareMatrix;

import java.util.Scanner;

public class Menu {

    private boolean run = true;

    public void run() {
        Scanner scan = new Scanner(System.in);
        do {
            printMenuOptions();
            int choice = scan.nextInt();
            int rows1, cols1, rows2, cols2, rows, cols;
            Matrix m1, m2, res;
            switch (choice) {
                case 1:
                    case1();
                    break;
                case 2:
                    case2();
                    break;
                case 3:
                    case3();
                    break;
                case 4:
                    case4();
                    break;
                case 5:
                    case5();
                    break;
                case 6:
                    case6();
                    break;
                case 0:
                    run = false;
                    break;
            }

        } while (run);
    }

    public static void printMenuOptions() {
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix to a constant");
        System.out.println("3. Multiply matricies");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Find determinant");
        System.out.println("6. Find inverse");
        System.out.println("0. Exit");
        System.out.print("Your choice: ");
    }

    public static void printCase4Options() {
        System.out.println("1. Main.Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.print("Your choice: ");
    }

    public static void case6() {
        Scanner scan = new Scanner(System.in);
        int rows, cols;
        SquareMatrix m;
        System.out.print("Enter size of matrix: ");
        rows = scan.nextInt();
        cols = scan.nextInt();
        m = new SquareMatrix(rows);
        System.out.print("Enter the matrix: ");
        m.fillMatrixWithInput();
        m.updateDet();
        Matrix temp = m.getInverse();
        System.out.println("The result is: ");
        temp.printMatrix();
    }

    public static void case5() {
        Scanner scan = new Scanner(System.in);
        SquareMatrix m;
        int rows, cols;
        System.out.println("Enter matrix size: ");
        rows = scan.nextInt();
        cols = scan.nextInt();
        m = new SquareMatrix(rows);
        System.out.print("Enter matrix: ");
        m.fillMatrixWithInput();
        m.updateDet();
        System.out.println("The result is: ");
        System.out.println(m.getDeterminant());
    }

    public static void case4() {
        Scanner scan = new Scanner(System.in);
        int numRows, numCols;
        Matrix m1, res;
        printCase4Options();
        int choice = scan.nextInt();

        System.out.print("Enter matrix size: ");
        numRows = scan.nextInt();
        numCols = scan.nextInt();
        m1 = new Matrix(numRows, numCols);
        System.out.print("Enter matrix: ");
        m1.fillMatrixWithInput();
        System.out.println("The result is: ");

        switch (choice) {
            case 2:
                m1.transposeSideDiagonal().printMatrix();
                break;
            case 3:
                m1.transposeVertical().printMatrix();
                break;
            case 4:
                m1.transposeHorizontal().printMatrix();
                break;
            default:
                m1.transposeMainDiagonal().printMatrix();
        }
    }

    public static void case1() {
        int rows1, rows2, cols1, cols2;
        Matrix m1, m2, res;
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter size of first matrix: ");
        rows1 = scan.nextInt();
        cols1 = scan.nextInt();
        m1 = new Matrix(rows1, cols1);
        System.out.print("Enter first matrix: ");
        m1.fillMatrixWithInput();
        System.out.print("Enter size of second matrix: ");
        rows2 = scan.nextInt();
        cols2 = scan.nextInt();
        m2 = new Matrix(rows2, cols2);
        System.out.print("Enter the second matrix: ");
        m2.fillMatrixWithInput();
        System.out.println("The addition result is: ");
        try {
            res = m1.sum2Matrices(m2);
            res.printMatrix();
        } catch (InvalidMatrixDimensions e) {
            System.out.println(e.getMessage());
        }
    }

    public static void case2() {
        Scanner scan = new Scanner(System.in);
        int rows, cols;
        double k;
        Matrix m1, res;

        System.out.print("Enter size of matrix: ");
        rows = scan.nextInt();
        cols = scan.nextInt();
        m1 = new Matrix(rows, cols);
        System.out.print("Enter the matrix");
        m1.fillMatrixWithInput();
        System.out.print("Enter the constant");
        k = scan.nextDouble();
        System.out.println("Result of scaling: ");
        res = m1.multiplyByScalar(k);
        res.printMatrix();
    }

    public static void case3() {
        Scanner scan = new Scanner(System.in);
        int rows1, cols1, rows2, cols2;
        Matrix m1, m2, res;

        System.out.print("Enter the size of first matrix: ");
        rows1 = scan.nextInt();
        cols1 = scan.nextInt();
        m1 = new Matrix(rows1, cols1);
        System.out.print("Enter the first matrix: ");
        m1.fillMatrixWithInput();
        System.out.print("Enter size of second matrix: ");
        rows2 = scan.nextInt();
        cols2 = scan.nextInt();
        m2 = new Matrix(rows2, cols2);
        System.out.print("Enter the second matrix: ");
        m2.fillMatrixWithInput();
        System.out.println("The multiplication result is: ");
        try {
            res = m1.multiply2Matrices(m2);
            res.printMatrix();
        } catch (InvalidMatrixDimensions e) {
            System.out.print(e.getMessage());
        }
    }


}
