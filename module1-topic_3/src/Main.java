import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static int step = 0;

    public static void main(String[] args) {
        Board brd = new Board();
        String monsterImage = "\uD83E\uDDDF\u200D";
        String castle = "\uD83C\uDFF0";
        int sizeBoard = 5;

        Scanner sc = new Scanner(System.in);
        System.out.println("Привет! Ты готов начать играть в игру? (Напиши: ДА или НЕТ)");

        String answer = sc.nextLine();
        System.out.println("Ваш ответ:\t" + answer);

        switch (answer) {
            case "ДА" -> {
                System.out.println("Выбери сложность игры(от 1 до 5):");
                int difficultGame = sc.nextInt();
                System.out.println("Выбранная сложность:\t" + difficultGame);

                Monster[] monsters = new Monster[sizeBoard - 1];
                Wall[] walls = new Wall[Math.max(1, sizeBoard / 2)];

                Person player = new Person(0, sizeBoard - 1);
                String[][] board = brd.gameMapGenerate(sizeBoard, player.getImage(), monsterImage, castle, monsters, walls, difficultGame);

                board[player.getY()][player.getX()] = player.getImage();

                while (player.isAlive()) {
                    displayBoard(board);

                    System.out.println("Количество жизней:\t" + player.getLives() + "\n");
                    System.out.println("Ход номер: " + (++step) + "\n");

                    boolean moveProcessed = player.processMove(board, castle, monsters, walls);

                    if (player.hasReachedCastle(board, castle)) {
                        System.out.println("🎊 Поздравляем! Вы выиграли!");
                        break;
                    }

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (!player.isAlive()) {
                    System.out.println("💀 Игра окончена! У вас закончились жизни.");
                }
            }
            case "НЕТ" -> System.out.println("Жаль, приходи еще!");
            default -> System.out.println("Данные введены некорректно");
        }
    }

    private static void displayBoard(String[][] board) {
        int size = board.length;
        String wall = "+ —— ".repeat(size) + "+";

        for (String[] raw : board) {
            System.out.println(wall);
            for (String col : raw) {
                String leftBlock = "| ";
                System.out.print(leftBlock + col + " ");
            }
            String rightBlock = "|";
            System.out.println(rightBlock);
        }
        System.out.println(wall);
    }
}