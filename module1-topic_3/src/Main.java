import java.util.Random;
import java.util.Scanner;

public class Main {

    public static boolean check(int diff) {
        Random rnd = new Random();
        Scanner sc = new Scanner(System.in);
        int x, y;
        if (diff == 1) { // easy
            x = rnd.nextInt(1, 100);
            y = rnd.nextInt(1, 100);
        } else if (diff == 2) { // HARD
            x = rnd.nextInt(1, 300);
            y = rnd.nextInt(1, 300);
        }
        else {
            x = rnd.nextInt(1, 100);
            y = rnd.nextInt(1, 100);
        }
        System.out.println("Чему равно x + y, если x =" + x + ", а y =" + y);
        int answer = sc.nextInt();
        if (answer == x+y) {
            System.out.println("ВЕРНООО!! ты победил его!!");
            return true;
        }
        else {
            System.out.println("KYS");
            return false;
        }
    }
    public static void main(String[] args) {
        String person = "\uD83E\uDDD9\u200D";
        int personLive = 3;

        String monster = "\uD83E\uDDDF\u200D";
        String castle = "\uD83C\uDFF0";
        int sizeBoard = 3;
        int personX = 1;
        int personY = sizeBoard;



        int step = 0;
        String leftBlock = "| ";
        String rightBlock = "|";
        String wall = "+ —— + —— + —— +";
        String gamingField = "+ —— + —— + —— +\n"
                + "|    |    | \uD83C\uDFE0 |\n"
                + "+ —— + —— + —— +\n"
                + "|    | " + monster + " |    |\n"
                + "+ —— + —— + —— +\n"
                + "| " + person + " |    |    |\n"
                + "+ —— + —— + —— +";
        String[][] board = new String[sizeBoard][sizeBoard];
        for (int y = 0; y < sizeBoard; y++) {
            for (int x = 0; x < sizeBoard; x++) {
                board[y][x] = "  ";
            }
        }


        int countMonster = sizeBoard * sizeBoard - sizeBoard - 1;
        Random rnd = new Random();
        for (int i = 0; i <= countMonster; i++) {
            board[rnd.nextInt(sizeBoard - 1)][rnd.nextInt(sizeBoard)] = monster;
        }

        int castleX = rnd.nextInt(sizeBoard);
        int castleY = 0;


        board[castleY][castleX] = castle;


        System.out.println("Привет! Ты готов начать играть в игру? (Напиши: ДА или НЕТ)");

        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        System.out.println("Ваш ответ:\t" + answer);


        switch (answer) {
            case "ДА" -> {

                System.out.println("Выбери сложность игры(от 1 до 5):");
                int difficultGame = sc.nextInt();
                System.out.println("Выбранная сложность:\t" + difficultGame);

                int maxStep = 2;

                while (true) {
                    board[personY - 1][personX - 1] = person;
                    for (String[] raw : board) {
                        System.out.println(wall);
                        for (String col : raw) {
                            System.out.print(leftBlock + col + " ");
                        }
                        System.out.println(rightBlock);
                    }
                    System.out.println(wall);


                    System.out.println("Количество жизней:\t" + personLive + "\n");

                    System.out.println("Введите куда будет ходить персонаж(ход возможен только по вертикали и горизонтали на одну клетку;" +
                            "\nКоординаты персонажа - (x: " + personX + ", y: " + personY + "))");
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    System.out.println(x + ", " + y);

                    // проверка
                    if (x != personX && y != personY) {
                        System.out.println("Неккоректный ход");
                    } else if (Math.abs(x - personX) == 1 || Math.abs(y - personY) == 1) {
                        if (board[y - 1][x - 1].equals("  ")) {
                            board[personY - 1][personX - 1] = "  ";
                            personX = x;
                            personY = y;
                            step++;
                            System.out.println("Ход корректный; Новые координаты: " + personX + ", " + personY +
                                    "\nХод номер: " + step);
                        }else if (board[y - 1][x - 1].equals(castle)) {
                            System.out.println("Вы прошли игру!");
                            break;
                        } else {
                            boolean flag;
                            if (personLive == 0) {
                                flag = check(1);
                            } else {
                                flag = check(2);
                            }

                            if (!flag) {
                                personLive--;
                            }
                            else personLive++;
                        }
                    } else {
                        System.out.println("Координаты не изменены");
                    }

                    if (personLive < 0) {
                        break;
                    }
                }

                System.out.println("Закончились жизни. Итог: ...");
            }
            case "НЕТ" -> System.out.println("Жаль, приходи еще!");
            default -> System.out.println("Данные введены неккоректно");
        }

    }
}