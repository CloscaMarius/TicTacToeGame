import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

class TicTacToeGame {

    private static final String[] remainingCellsArray = {"A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3"};
    private static List<String> remainingCells = new LinkedList<>(Arrays.asList(remainingCellsArray));
    private static int totalGames;
    private static int firstPlayerScore;
    private static int secondPlayerScore;

    public static void main(String[] args) {

        multipleGames();

    }

    private static void multipleGames() {
        Scanner scanner = new Scanner(System.in);
        String firstPlayerName;
        String secondPlayerName;
        while (true) {
            System.out.println("First player name: ");
            firstPlayerName = scanner.nextLine().trim();
            if (!firstPlayerName.equals("")) {
                break;
            } else {
                System.out.println("Please enter a name! ");
            }
        }
        while (true) {
            System.out.println("Second player name(if you want to play with the computer enter 'CPU'): ");
            secondPlayerName = scanner.nextLine().trim();
            if (!secondPlayerName.equals("")) {
                break;
            } else {
                System.out.println("Please enter a name! ");
            }
        }

        do {
            if (totalGames % 2 == 0) {
                remainingCells = new LinkedList<>(Arrays.asList(remainingCellsArray));
                playOneGameUX(firstPlayerName, secondPlayerName);
            } else {
                remainingCells = new LinkedList<>(Arrays.asList(remainingCellsArray));
                playOneGameAltUX(firstPlayerName, secondPlayerName);
            }

            totalGames++;
            System.out.println("Total games played: " + totalGames);
            System.out.println(firstPlayerName + "'s score: " + firstPlayerScore);
            System.out.println(secondPlayerName + "'s score: " + secondPlayerScore);


        } while (askUserToContinue());

        totalGames = 0;
        firstPlayerScore = 0;
        secondPlayerScore = 0;
    }

    private static void playOneGameUX(String firstPlayerName, String secondPlayerName) {

        char[][] board = new char[][]{{'_', '_', '_'}, {'_', '_', '_'}, {'_', '_', '_'}};

        TicTacToeImageUX(board);
        while (stillPlayingUX(board, firstPlayerName, secondPlayerName)) {
            if (Arrays.deepToString(board).contains("_")) {
                markXUX(board, firstPlayerName);
                if (!stillPlayingUX(board, firstPlayerName, secondPlayerName)) {
                    break;
                }

            } else {
                System.out.println("It's a tie!");
                break;
            }
            if (Arrays.deepToString(board).contains("_")) {

                markOUXWithCPU(board, secondPlayerName);
                if (!stillPlayingUX(board, firstPlayerName, secondPlayerName)) {
                    break;
                }

            } else {
                System.out.println("It's a tie!");
                break;
            }

        }
    }

    private static void playOneGameAltUX(String firstPlayerName, String secondPlayerName) {

        char[][] board = new char[][]{{'_', '_', '_'}, {'_', '_', '_'}, {'_', '_', '_'}};

        TicTacToeImageUX(board);
        while (stillPlayingUX(board, firstPlayerName, secondPlayerName)) {
            if (Arrays.deepToString(board).contains("_")) {
                markOUXWithCPU(board, secondPlayerName);
                if (!stillPlayingUX(board, firstPlayerName, secondPlayerName)) {
                    break;
                }

            } else {
                System.out.println("It's a tie!");
                break;
            }
            if (Arrays.deepToString(board).contains("_")) {
                markXUX(board, firstPlayerName);
                if (!stillPlayingUX(board, firstPlayerName, secondPlayerName)) {
                    break;
                }

            } else {
                System.out.println("It's a tie!");
                break;
            }

        }
    }

    private static void markXUX(char[][] board, String firstPlayerName) {

        System.out.println("-- " + firstPlayerName + "'s turn --");
        System.out.println("Cell: ");


        while (true) {
            String number = validPlayerCell(board);
            int row = Integer.parseInt(number.substring(0, 1));
            int column = Integer.parseInt(number.substring(1));

            if (board[row][column] == '_') {
                board[row][column] = 'X';
                TicTacToeImageUX(board);
                break;
            } else {
                TicTacToeImageUX(board);
                System.out.println("Please enter a free position: ");
            }
        }
    }

    private static void markOUXWithCPU(char[][] board, String secondPlayerName) {

        System.out.println("-- " + secondPlayerName + "'s turn --");
        System.out.println("Cell: ");
        if (secondPlayerName.equalsIgnoreCase("CPU")) {
            while (true) {

                String number = validCPUCell(board, secondPlayerName);
                int row = Integer.parseInt(number.substring(0, 1));
                int column = Integer.parseInt(number.substring(1));


                if (board[row][column] == '_') {
                    board[row][column] = 'O';
                    TicTacToeImageUX(board);
                    break;
                } else {
                    TicTacToeImageUX(board);
                    System.out.println("Please enter a free position: ");
                }
            }
        } else {
            while (true) {
                String number = validPlayerCell(board);
                int row = Integer.parseInt(number.substring(0, 1));
                int column = Integer.parseInt(number.substring(1));


                if (board[row][column] == '_') {
                    board[row][column] = 'O';
                    TicTacToeImageUX(board);
                    break;
                } else {
                    TicTacToeImageUX(board);
                    System.out.println("Please enter a free position: ");

                }
            }
        }
    }

    private static void TicTacToeImageUX(char[][] board) {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8));

        //System.out.println("┏━┳━┓\n┃ ┃ ┃\n┣━╋━┫\n┗━┻━┛\n╔═╦═╗\n║ ║ ║\n╠═╬═╣\n╚═╩═╝\n┌─┬─┐\n│ │ │\n├─┼─┤\n└─┴─┘");

        System.out.println("     1     2     3   ");
        System.out.println("  ┌─────┬─────┬─────┒");
        System.out.println("A │  " + board[0][0] + "  │  " + board[0][1] + "  │  " + board[0][2] + "  │");
        System.out.println("  ├─────┼─────┼─────┤");
        System.out.println("B │  " + board[1][0] + "  │  " + board[1][1] + "  │  " + board[1][2] + "  │");
        System.out.println("  ├─────┼─────┼─────┤");
        System.out.println("C │  " + board[2][0] + "  │  " + board[2][1] + "  │  " + board[2][2] + "  │");
        System.out.println("  └─────┴─────┴─────┘");
    }

    private static String validPlayerCell(char[][] board) {


        int row;
        int column;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String cell = scanner.next().toUpperCase();
            switch (cell) {

                case "A1":
                    row = 0;
                    column = 0;
                    remainingCells.remove("A1");
                    return String.valueOf(row) + String.valueOf(column);

                case "A2":
                    row = 0;
                    column = 1;
                    remainingCells.remove("A2");
                    return String.valueOf(row) + String.valueOf(column);

                case "A3":
                    row = 0;
                    column = 2;
                    remainingCells.remove("A3");
                    return String.valueOf(row) + String.valueOf(column);

                case "B1":
                    row = 1;
                    column = 0;
                    remainingCells.remove("B1");
                    return String.valueOf(row) + String.valueOf(column);

                case "B2":
                    row = 1;
                    column = 1;
                    remainingCells.remove("B2");
                    return String.valueOf(row) + String.valueOf(column);

                case "B3":
                    row = 1;
                    column = 2;
                    remainingCells.remove("B3");
                    return String.valueOf(row) + String.valueOf(column);

                case "C1":
                    row = 2;
                    column = 0;
                    remainingCells.remove("C1");
                    return String.valueOf(row) + String.valueOf(column);

                case "C2":
                    row = 2;
                    column = 1;
                    remainingCells.remove("C2");
                    return String.valueOf(row) + String.valueOf(column);

                case "C3":
                    row = 2;
                    column = 2;
                    remainingCells.remove("C3");
                    return String.valueOf(row) + String.valueOf(column);

                default:
                    TicTacToeImageUX(board);
                    System.out.println("Please enter a valid cell!");

            }

        }

    }

    private static String validCPUCell(char[][] board, String secondPlayerName) {

        if (secondPlayerName.equalsIgnoreCase("CPU")) {
            int row;
            int column;
            String cell = offensiveCPU(board);
            while (true) {

                switch (cell) {

                    case "A1":
                        row = 0;
                        column = 0;
                        remainingCells.remove("A1");
                        return String.valueOf(row) + String.valueOf(column);

                    case "A2":
                        row = 0;
                        column = 1;
                        remainingCells.remove("A2");
                        return String.valueOf(row) + String.valueOf(column);

                    case "A3":
                        row = 0;
                        column = 2;
                        remainingCells.remove("A3");
                        return String.valueOf(row) + String.valueOf(column);

                    case "B1":
                        row = 1;
                        column = 0;
                        remainingCells.remove("B1");
                        return String.valueOf(row) + String.valueOf(column);

                    case "B2":
                        row = 1;
                        column = 1;
                        remainingCells.remove("B2");
                        return String.valueOf(row) + String.valueOf(column);

                    case "B3":
                        row = 1;
                        column = 2;
                        remainingCells.remove("B3");
                        return String.valueOf(row) + String.valueOf(column);

                    case "C1":
                        row = 2;
                        column = 0;
                        remainingCells.remove("C1");
                        return String.valueOf(row) + String.valueOf(column);

                    case "C2":
                        row = 2;
                        column = 1;
                        remainingCells.remove("C2");
                        return String.valueOf(row) + String.valueOf(column);

                    case "C3":
                        row = 2;
                        column = 2;
                        remainingCells.remove("C3");
                        return String.valueOf(row) + String.valueOf(column);

                    default:
                        TicTacToeImageUX(board);
                        System.out.println("Please enter a valid cell!");

                }
            }

        } else {
            int row;
            int column;
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String cell = scanner.next().toUpperCase();
                switch (cell) {

                    case "A1":
                        row = 0;
                        column = 0;
                        remainingCells.remove("A1");
                        return String.valueOf(row) + String.valueOf(column);

                    case "A2":
                        row = 0;
                        column = 1;
                        remainingCells.remove("A2");
                        return String.valueOf(row) + String.valueOf(column);

                    case "A3":
                        row = 0;
                        column = 2;
                        remainingCells.remove("A3");
                        return String.valueOf(row) + String.valueOf(column);

                    case "B1":
                        row = 1;
                        column = 0;
                        remainingCells.remove("B1");
                        return String.valueOf(row) + String.valueOf(column);

                    case "B2":
                        row = 1;
                        column = 1;
                        remainingCells.remove("B2");
                        return String.valueOf(row) + String.valueOf(column);

                    case "B3":
                        row = 1;
                        column = 2;
                        remainingCells.remove("B3");
                        return String.valueOf(row) + String.valueOf(column);

                    case "C1":
                        row = 2;
                        column = 0;
                        remainingCells.remove("C1");
                        return String.valueOf(row) + String.valueOf(column);

                    case "C2":
                        row = 2;
                        column = 1;
                        remainingCells.remove("C2");
                        return String.valueOf(row) + String.valueOf(column);

                    case "C3":
                        row = 2;
                        column = 2;
                        remainingCells.remove("C3");
                        return String.valueOf(row) + String.valueOf(column);

                    default:
                        TicTacToeImageUX(board);
                        System.out.println("Please enter a valid cell!");

                }

            }
        }
    }

    private static String offensiveCPU(char[][] board) {
        Random rand = new Random();
        String cell;
        if (board[0][0] == board[0][1] && board[0][0] == 'O' && board[0][2] == '_') {
            cell = "A3";
        } else if (board[0][2] == board[0][1] && board[0][2] == 'O' && board[0][0] == '_') {
            cell = "A1";
        } else if (board[0][0] == board[0][2] && board[0][0] == 'O' && board[0][1] == '_') {
            cell = "A2";
        } else if (board[1][0] == board[1][1] && board[1][0] == 'O' && board[1][2] == '_') {
            cell = "B3";
        } else if (board[1][2] == board[1][1] && board[1][2] == 'O' && board[1][0] == '_') {
            cell = "B1";
        } else if (board[1][0] == board[1][2] && board[1][0] == 'O' && board[1][1] == '_') {
            cell = "B2";
        } else if (board[2][0] == board[2][1] && board[2][0] == 'O' && board[2][2] == '_') {
            cell = "C3";
        } else if (board[2][2] == board[2][1] && board[2][2] == 'O' && board[2][0] == '_') {
            cell = "C1";
        } else if (board[2][0] == board[2][2] && board[2][0] == 'O' && board[2][1] == '_') {
            cell = "C2";
        } else if (board[0][0] == board[1][0] && board[0][0] == 'O' && board[2][0] == '_') {
            cell = "C1";
        } else if (board[1][0] == board[2][0] && board[1][0] == 'O' && board[0][0] == '_') {
            cell = "A1";
        } else if (board[0][0] == board[2][0] && board[0][0] == 'O' && board[1][0] == '_') {
            cell = "B1";
        } else if (board[0][1] == board[1][1] && board[0][1] == 'O' && board[2][1] == '_') {
            cell = "C2";
        } else if (board[2][1] == board[1][1] && board[2][1] == 'O' && board[0][1] == '_') {
            cell = "A2";
        } else if (board[0][1] == board[2][1] && board[0][1] == 'O' && board[1][1] == '_') {
            cell = "B2";
        } else if (board[0][2] == board[1][2] && board[0][2] == 'O' && board[2][2] == '_') {
            cell = "C3";
        } else if (board[2][2] == board[1][2] && board[2][2] == 'O' && board[0][2] == '_') {
            cell = "A3";
        } else if (board[0][2] == board[2][2] && board[0][2] == 'O' && board[1][2] == '_') {
            cell = "B3";
        } else if (board[0][0] == board[1][1] && board[0][0] == 'O' && board[2][2] == '_') {
            cell = "C3";
        } else if (board[2][2] == board[1][1] && board[2][2] == 'O' && board[0][0] == '_') {
            cell = "A1";
        } else if (board[0][0] == board[2][2] && board[0][0] == 'O' && board[1][1] == '_') {
            cell = "B2";
        } else if (board[0][2] == board[1][1] && board[0][2] == 'O' && board[2][0] == '_') {
            cell = "C1";
        } else if (board[2][0] == board[1][1] && board[2][0] == 'O' && board[0][2] == '_') {
            cell = "A3";
        } else if (board[0][2] == board[2][0] && board[0][2] == 'O' && board[1][1] == '_') {
            cell = "B2";
        } else {
            if (board[0][0] == board[0][1] && board[0][0] == 'X' && board[0][2] == '_') {
                cell = "A3";
            } else if (board[0][2] == board[0][1] && board[0][2] == 'X' && board[0][0] == '_') {
                cell = "A1";
            } else if (board[0][0] == board[0][2] && board[0][0] == 'X' && board[0][1] == '_') {
                cell = "A2";
            } else if (board[1][0] == board[1][1] && board[1][0] == 'X' && board[1][2] == '_') {
                cell = "B3";
            } else if (board[1][2] == board[1][1] && board[1][2] == 'X' && board[1][0] == '_') {
                cell = "B1";
            } else if (board[1][0] == board[1][2] && board[1][0] == 'X' && board[1][1] == '_') {
                cell = "B2";
            } else if (board[2][0] == board[2][1] && board[2][0] == 'X' && board[2][2] == '_') {
                cell = "C3";
            } else if (board[2][2] == board[2][1] && board[2][2] == 'X' && board[2][0] == '_') {
                cell = "C1";
            } else if (board[2][0] == board[2][2] && board[2][0] == 'X' && board[2][1] == '_') {
                cell = "C2";
            } else if (board[0][0] == board[1][0] && board[0][0] == 'X' && board[2][0] == '_') {
                cell = "C1";
            } else if (board[1][0] == board[2][0] && board[1][0] == 'X' && board[0][0] == '_') {
                cell = "A1";
            } else if (board[0][0] == board[2][0] && board[0][0] == 'X' && board[1][0] == '_') {
                cell = "B1";
            } else if (board[0][1] == board[1][1] && board[0][1] == 'X' && board[2][1] == '_') {
                cell = "C2";
            } else if (board[2][1] == board[1][1] && board[2][1] == 'X' && board[0][1] == '_') {
                cell = "A2";
            } else if (board[0][1] == board[2][1] && board[0][1] == 'X' && board[1][1] == '_') {
                cell = "B2";
            } else if (board[0][2] == board[1][2] && board[0][2] == 'X' && board[2][2] == '_') {
                cell = "C3";
            } else if (board[2][2] == board[1][2] && board[2][2] == 'X' && board[0][2] == '_') {
                cell = "A3";
            } else if (board[0][2] == board[2][2] && board[0][2] == 'X' && board[1][2] == '_') {
                cell = "B3";
            } else if (board[0][0] == board[1][1] && board[0][0] == 'X' && board[2][2] == '_') {
                cell = "C3";
            } else if (board[2][2] == board[1][1] && board[2][2] == 'X' && board[0][0] == '_') {
                cell = "A1";
            } else if (board[0][0] == board[2][2] && board[0][0] == 'X' && board[1][1] == '_') {
                cell = "B2";
            } else if (board[0][2] == board[1][1] && board[0][2] == 'X' && board[2][0] == '_') {
                cell = "C1";
            } else if (board[2][0] == board[1][1] && board[2][0] == 'X' && board[0][2] == '_') {
                cell = "A3";
            } else if (board[0][2] == board[2][0] && board[0][2] == 'X' && board[1][1] == '_') {
                cell = "B2";
            } else {
                if (board[1][1] == '_') {
                    cell = "B2";
                } else if (board[0][0] == '_' && board[0][2] == '_' && board[2][0] == '_' && board[2][2] == '_') {
                    List<String> crossBoard = Arrays.asList("A1", "A3", "C1", "C3");
                    cell = crossBoard.get(rand.nextInt(crossBoard.size()));
                } else {
                    cell = remainingCells.get(rand.nextInt(remainingCells.size()));
                }
            }
        }
        return cell;
    }

    private static boolean stillPlayingUX(char[][] board, String firstPlayerName, String secondPlayerName) {
        //char[][] board = new char[][]{{'_', '_', '_'}, {'_', '_', '_'}, {'_', '_', '_'}};


        if (board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][1] != '_') {
            if (board[0][1] == 'X') {
                System.out.println(firstPlayerName + " has won!");
                firstPlayerScore++;

            } else {
                System.out.println(secondPlayerName + " has won!");
                secondPlayerScore++;
            }
            return false;
        } else if (board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][1] != '_') {
            if (board[1][1] == 'X') {
                System.out.println(firstPlayerName + " has won!");
                firstPlayerScore++;
            } else {
                System.out.println(secondPlayerName + " has won!");
                secondPlayerScore++;
            }
            return false;
        } else if (board[2][0] == board[2][1] && board[2][1] == board[2][2] && board[2][1] != '_') {
            if (board[2][1] == 'X') {
                System.out.println(firstPlayerName + " has won!");
                firstPlayerScore++;
            } else {
                System.out.println(secondPlayerName + " has won!");
                secondPlayerScore++;
            }
            return false;
        } else if (board[0][0] == board[1][0] && board[1][0] == board[2][0] && board[1][0] != '_') {
            if (board[1][0] == 'X') {
                System.out.println(firstPlayerName + " has won!");
                firstPlayerScore++;
            } else {
                System.out.println(secondPlayerName + " has won!");
                secondPlayerScore++;
            }
            return false;
        } else if (board[0][1] == board[1][1] && board[1][1] == board[2][1] && board[1][1] != '_') {
            if (board[1][1] == 'X') {
                System.out.println(firstPlayerName + " has won!");
                firstPlayerScore++;
            } else {
                System.out.println(secondPlayerName + " has won!");
                secondPlayerScore++;
            }
            return false;
        } else if (board[0][2] == board[1][2] && board[1][2] == board[2][2] && board[1][2] != '_') {
            if (board[1][2] == 'X') {
                System.out.println(firstPlayerName + " has won!");
                firstPlayerScore++;
            } else {
                System.out.println(secondPlayerName + " has won!");
                secondPlayerScore++;
            }
            return false;
        } else if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[1][1] != '_') {
            if (board[1][1] == 'X') {
                System.out.println(firstPlayerName + " has won!");
                firstPlayerScore++;
            } else {
                System.out.println(secondPlayerName + " has won!");
                secondPlayerScore++;
            }
            return false;
        } else if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[1][1] != '_') {
            if (board[1][1] == 'X') {
                System.out.println(firstPlayerName + " has won!");
                firstPlayerScore++;
            } else {
                System.out.println(secondPlayerName + " has won!");
                secondPlayerScore++;
            }
            return false;
        }
        return true;
    }

    private static boolean askUserToContinue() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Play another one? (y/n): ");
        String answer = sc.next();
        return answer.equalsIgnoreCase("y");
    }

}
