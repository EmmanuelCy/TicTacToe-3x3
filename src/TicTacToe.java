import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TicTacToe {

    private static String[] board = new String[9];
    private static boolean isXTurn = true;
    private static Scanner scanner = null;
    static Random rand = new Random();
/*
        Programmed by Group 1
        Emmanuel Cy Coyoca (Leader)
        DUBA, BRIAN ACE
        MONARES,  REYMART
        PRADILLA, ELAVILL
        BATERNA, ZACHARY
 */
    public static void main(String[] args) {
        initializeScanner();
        initializeBoard();
        printInstructions();
        while (!isGameOver() && !checkIfDraw()) {
            getPlayerInput();
            printBoard();
        }
    }

    private static void initializeBoard() {
        Arrays.fill(board, " ");
    }

    private static void printInstructions() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("CC 123/L (Programming 2) Group 1 ");
        System.out.println("---------------------------------------------------------------");
        System.out.println("TIC TAC TOE");
        System.out.println("Placement Numbers:");
        printReferenceBoard();
        System.out.println("---------------------------------------------------------------");
    }

    private static boolean checkRowWin() {
        for (int i = 0; i < board.length; i += 3) {
            if (!board[i].equals(" ") && board[i].equals(board[i + 1]) && board[i].equals(board[i + 2])) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkColumnWin() {
        for (int i = 0; i < 3; i++) {
            if (!board[i].equals(" ") && board[i].equals(board[i + 3]) && board[i].equals(board[i + 6])) {
                return true;
            }
        }
        return false;
    }
    private static boolean checkDiagonalWin() {
        if (!board[0].equals(" ") && board[0].equals(board[4]) && board[0].equals(board[8])
                || !board[2].equals(" ") && board[2].equals(board[4]) && board[2].equals(board[6])) {
            return true;
        }
        return false;
    }
    private static boolean checkIfDraw() {
        if(board[0].equals(" ") & !board[1].equals(" ") & !board[2].equals(" ") & !board[3].equals(" ")  & !board[4].equals(" ")
                & !board[5].equals(" ") & !board[6].equals(" ") & !board[7].equals(" ") & !board[8].equals(" "))
        {
            return true;
        }
        return false;
    }
    private static boolean isGameOver() {
        if (checkRowWin() || checkColumnWin() || checkDiagonalWin()) {
            System.out.println("\n---------------------------------------------------------------");
            printBoard();
            if (isXTurn) {
                System.out.print("\nCPU(O) won the game!");
            } else {
                System.out.print("\nPlayer(X) won the game!");
            }
            System.out.println("\n---------------------------------------------------------------");
            return true;
        }
        return false;
    }

    private static void getPlayerInput() {
        checkIfDraw();
        int playerInput,CPUInput;
        System.out.println();
        try {
            if (isXTurn) {
                do {
                    System.out.print("Player(X), enter your placement: ");
                    playerInput = scanner.nextInt();
                    playerInput = playerInput-1;
                    if(!board[playerInput].contains(" "))
                        System.out.println("\n!-! SPACE IS OCCUPIED !-!");
                }while (!board[playerInput].contains(" "));
                board[playerInput] = "X";

            } else {
                checkIfDraw();
                System.out.println("\nCPU(O) is working on its turn:");
                TimeUnit.SECONDS.sleep(1);
                do {
                    CPUInput = rand.nextInt(8) + 1;
                }while (!board[CPUInput].contains(" "));
                board[CPUInput] = "O";
            }
            setTurn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setTurn() {
        if (isXTurn) {
            isXTurn = false;
        } else {
            isXTurn = true;
        }
    }

    private static void printBoard() {
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            if ((i + 1) % 3 == 0) {
                System.out.println(board[i]);
                if (i != board.length - 1) {
                    System.out.println("---------");
                }
            } else {
                System.out.print(board[i] + " | ");
            }
        }
    }

    private static void printReferenceBoard() {
        System.out.println("\t 1 | 2 | 3");
        System.out.println("\t---------");
        System.out.println("\t 4 | 5 | 6");
        System.out.println("\t---------");
        System.out.println("\t 7 | 8 | 9");
    }

    private static void initializeScanner() {
        try {
            scanner = new Scanner(System.in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}