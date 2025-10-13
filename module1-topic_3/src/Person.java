import java.util.Random;
import java.util.Scanner;

public class Person {
    private int x;
    private int y;
    private String image = "\uD83E\uDDD9\u200D";
    private int lives = 3;
    private final int maxLives = 3;
    private Scanner scanner = new Scanner(System.in);

    public Person(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public boolean processMove(String[][] board, String castle, String monster) {
        System.out.println("Введите куда будет ходить персонаж (ход возможен только по вертикали и горизонтали на одну клетку)");
        System.out.println("Координаты персонажа - (x: " + x + ", y: " + y + ")");

        int targetX = scanner.nextInt();
        int targetY = scanner.nextInt();
        System.out.println("Выбранные координаты: " + targetX + ", " + targetY);

        if (!isMoveCorrect(targetX, targetY)) {
            System.out.println("Некорректный ход! Можно ходить только на одну клетку по вертикали или горизонтали.");
            return false;
        }

        String targetCell = board[targetY - 1][targetX - 1];

        if (targetCell.equals("  ")) {
            board[y - 1][x - 1] = "  ";
            this.x = targetX;
            this.y = targetY;
            board[y - 1][x - 1] = image;
            System.out.println("Ход корректный; Новые координаты: " + x + ", " + y);
            return true;
        }
        else if (targetCell.equals(castle)) {
            System.out.println("🎉 Вы достигли замка! Победа!");
            return true;
        }
        else if (targetCell.equals(monster)) {
            System.out.println("👾 Встретился монстр! Решите пример чтобы продолжить:");
            boolean battleResult = handleMonsterBattle();

            if (!battleResult) {
                decreaseLives();
                System.out.println("💔 Неправильный ответ! Потеряна жизнь.");
            } else {
                System.out.println("✅ Правильно! Монстр побежден!");
                board[y - 1][x - 1] = "  ";
                this.x = targetX;
                this.y = targetY;
                board[y - 1][x - 1] = image;
            }
            return true;
        }
        else {
            System.out.println("Эта клетка занята!");
            return false;
        }
    }

    private boolean handleMonsterBattle() {
        Random rnd = new Random();
        int difficulty = (lives == 0) ? 1 : 2;

        int num1, num2;
        if (difficulty == 1) { // easy
            num1 = rnd.nextInt(100) + 1;
            num2 = rnd.nextInt(100) + 1;
        } else { // hard
            num1 = rnd.nextInt(300) + 1;
            num2 = rnd.nextInt(300) + 1;
        }

        System.out.println("Чему равно " + num1 + " + " + num2 + "?");
        int answer = scanner.nextInt();

        return answer == (num1 + num2);
    }

    public boolean isMoveCorrect(int targetX, int targetY) {
        boolean horizontalMove = (y == targetY) && (Math.abs(x - targetX) == 1);
        boolean verticalMove = (x == targetX) && (Math.abs(y - targetY) == 1);

        return horizontalMove || verticalMove;
    }

    public void decreaseLives() {
        if (lives > 0) {
            lives--;
            System.out.println("❌ Потеряна жизнь! Осталось жизней: " + lives);

            if (!isAlive()) {
                System.out.println("💀 Игра окончена! Персонаж погиб.");
            }
        }
    }

    public void increaseLives() {
        if (lives < maxLives) {
            lives++;
            System.out.println("❤️ Получена жизнь! Теперь жизней: " + lives);
        } else {
            System.out.println("💚 У вас максимальное количество жизней!");
        }
    }

    public void restoreAllLives() {
        lives = maxLives;
        System.out.println("✨ Все жизни восстановлены!");
    }

    public boolean isAlive() {
        return lives > 0;
    }

    public boolean hasReachedCastle(String[][] board, String castle) {
        return board[y - 1][x - 1].equals(castle);
    }

    public void updateBoardPosition(String[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals(image)) {
                    board[i][j] = "  ";
                }
            }
        }
        board[y - 1][x - 1] = image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getImage() {
        return image;
    }

    public int getLives() {
        return lives;
    }

    public int getMaxLives() {
        return maxLives;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Информация о персонаже
    public void printStatus() {
        System.out.println("=== СТАТУС ПЕРСОНАЖА ===");
        System.out.println("Персонаж: " + image);
        System.out.println("Позиция: (" + x + ", " + y + ")");
        System.out.println("Жизни: " + lives + "/" + maxLives);
        System.out.println("Статус: " + (isAlive() ? "❤️ Жив" : "💀 Мёртв"));
        System.out.println("========================");
    }
}