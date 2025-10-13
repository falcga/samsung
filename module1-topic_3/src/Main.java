import java.util.Scanner;

public class Main {
    private static int step = 0;

    public static void main(String[] args) {
        Board brd = new Board();
        String monster = "\uD83E\uDDDF\u200D";
        String castle = "\uD83C\uDFF0";
        int sizeBoard = 3;

        Person player = new Person(1, 3);
        String[][] board = brd.gameMapGenerate(sizeBoard, player.getImage(), monster, castle);

        System.out.println("Привет! Ты готов начать играть в игру? (Напиши: ДА или НЕТ)");

        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        System.out.println("Ваш ответ:\t" + answer);

        switch (answer) {
            case "ДА" -> {
                System.out.println("Выбери сложность игры(от 1 до 5):");
                int difficultGame = sc.nextInt();
                System.out.println("Выбранная сложность:\t" + difficultGame);

                while (player.isAlive()) {
                    player.updateBoardPosition(board);

                    displayBoard(board);

                    System.out.println("Количество жизней:\t" + player.getLives() + "\n");
                    System.out.println("Ход номер: " + (++step) + "\n");

                    boolean moveProcessed = player.processMove(board, castle, monster);

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
        String wall = "+ —— + —— + —— +";
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