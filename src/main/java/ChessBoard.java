import java.util.Arrays;

/*
Desafío:
Imagina a un chico disfrutando mientras colorea su tablero de ajedrez.
Planea llenar cada cuadro con matices rojos o azules.
Con el objetivo de añadir un toque único, desea mantener un balance entre los espacios
rojos y azules, evitando al mismo tiempo que dos filas o columnas tengan la misma cantidad
de espacios rojos. ¿Sería posible colorear el tablero de ajedrez siguiendo estos parámetros?
¿Qué ocurriría si, en lugar de un tablero de ajedrez estándar de 8x8,
poseyera uno colosal de 1000x1000?
 */

/*
Challenge:
Imagine a kid enjoying while coloring his chessboard.
He plans to fill each square with shades of red or blue.
With the goal of adding a unique touch, he wants to maintain a balance
between red and blue spaces, while also avoiding having two rows or columns
with the same number of red spaces. Would it be possible to color the chessboard
following these parameters? What would happen if, instead of a standard 8x8
chessboard, he had a colossal 1000x1000 board?
*/

public class ChessBoard {
    static int rows = 8;  // Default number of rows (Número predeterminado de filas)
    static int cols = 8;  // Default number of columns (Número predeterminado de columnas)
    static String[][] board = new String[rows][cols];
    static int[] redCountInColumns = new int[cols];

    public static void main(String[] args) {
        // Initialize the board with empty spaces (Inicializar el tablero con espacios vacíos)
        for (int i = 0; i < rows; i++) {
            Arrays.fill(board[i], " ");
        }

        Arrays.fill(redCountInColumns, 0);

        if (!colorBoard(0)) {
            System.out.println("No solution found."); // No se encontró una solución.
        } else {
            fillWithBlue();
            printBoard();
        }
    }

    // Main method that tries to color the board (Método principal que intenta colorear el tablero)
    static boolean colorBoard(int row) {
        if (row == rows) {
            return true; // All rows are colored correctly (Todas las filas se colorearon correctamente)
        }

        for (int redCount = 0; redCount <= cols; redCount++) {
            if (isValid(row, redCount)) {
                placeRedsInRow(row, redCount);

                if (colorBoard(row + 1)) {
                    return true;
                }

                // Backtracking: remove colors if the solution is not valid (Backtracking: remover colores si la solución no es válida)
                removeRedsFromRow(row, redCount);
            }
        }
        return false; // No valid coloring found for this row (No se encontró una coloración válida para esta fila)
    }

    // Checks if placing 'redCount' reds in the row is valid (Verifica si colocar 'redCount' rojas en la fila es válido)
    static boolean isValid(int row, int redCount) {
        for (int i = 0; i < row; i++) {
            if (countRedsInRow(i) == redCount) {
                return false; // Another row already has this number of reds (Otra fila ya tiene esta cantidad de rojas)
            }
        }
        return true;
    }

    // Places 'redCount' reds in the specified row (Coloca 'redCount' rojas en la fila especificada)
    static void placeRedsInRow(int row, int redCount) {
        int placed = 0;
        for (int col = 0; col < cols && placed < redCount; col++) {
            if (board[row][col].equals(" ")) {
                board[row][col] = "R";
                redCountInColumns[col]++;
                placed++;
            }
        }
    }

    // Removes reds from the specified row (Remueve las rojas de la fila especificada)
    static void removeRedsFromRow(int row, int redCount) {
        int removed = 0;
        for (int col = 0; col < cols && removed < redCount; col++) {
            if (board[row][col].equals("R")) {
                board[row][col] = " ";
                redCountInColumns[col]--;
                removed++;
            }
        }
    }

    // Fills empty cells with blue (Llena las casillas vacías con azules)
    static void fillWithBlue() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j].equals(" ")) {
                    board[i][j] = "A";
                }
            }
        }
    }

    // Counts how many reds are in a given row (Cuenta cuántas rojas hay en una fila dada)
    static int countRedsInRow(int row) {
        int count = 0;
        for (int col = 0; col < cols; col++) {
            if (board[row][col].equals("R")) {
                count++;
            }
        }
        return count;
    }

    // Prints the board (Imprime el tablero)
    static void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        // Print column letters (Imprimir letras de columnas)
        System.out.print("  ");
        for (int j = 0; j < cols; j++) {
            System.out.print((char) ('a' + j) + " ");
        }
        System.out.println();
    }
}


