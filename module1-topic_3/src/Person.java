import java.util.Scanner;

public class Person {
    private int x;
    private int y;
    private String image = "☕";
    private int lives = 3;
    private final int maxLives = 3;
    private Scanner scanner = new Scanner(System.in);

    public Person(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public boolean processMove(String[][] board, String castle, Monster[] monsters, Wall[] walls) {
        System.out.println("Введите куда будет ходить персонаж (ход возможен только по вертикали и горизонтали на одну клетку)");
        System.out.println("Координаты персонажа - (x: " + (x + 1) + ", y: " + (y + 1) + ")");

        int targetX = scanner.nextInt() - 1;
        int targetY = scanner.nextInt() - 1;
        System.out.println("Выбранные координаты: " + (targetX + 1) + ", " + (targetY + 1));

        if (!isMoveCorrect(targetX, targetY)) {
            System.out.println("Некорректный ход! Можно ходить только на одну клетку по вертикали или горизонтали.");
            return false;
        }

        if (isWallAtPosition(walls, targetX, targetY)) {
            System.out.println("🧱 Здесь стена! Вы не можете пройти.");
            return false;
        }

        String targetCell = board[targetY][targetX];

        if (targetCell.equals("  ")) {
            board[y][x] = "  ";
            this.x = targetX;
            this.y = targetY;
            board[y][x] = image;
            System.out.println("Ход корректный; Новые координаты: " + (x + 1) + ", " + (y + 1));
            return true;
        } else if (targetCell.equals(castle)) {
            System.out.println("🎉 Вы достигли замка! Победа!");
            return true;
        } else {
            for (Monster monster : monsters) {
                if (monster != null && monster.conflictPerson(targetX, targetY)) {
                    return monster.monsterHere(this, board);
                }
            }
            System.out.println("Эта клетка занята!");
            return false;
        }
    }

    private boolean isWallAtPosition(Wall[] walls, int targetX, int targetY) {
        for (Wall wall : walls) {
            if (wall != null && wall.isWallAt(targetX, targetY)) {
                return true;
            }
        }
        return false;
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
        return board[y][x].equals(castle);
    }

    public void updateBoardPosition(String[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals(image)) {
                    board[i][j] = "  ";
                }
            }
        }
        board[y][x] = image;
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

    public void printStatus() {
        System.out.println("=== СТАТУС ПЕРСОНАЖА ===");
        System.out.println("Персонаж: " + image);
        System.out.println("Позиция: (" + (x + 1) + ", " + (y + 1) + ")");
        System.out.println("Жизни: " + lives + "/" + maxLives);
        System.out.println("Статус: " + (isAlive() ? "❤️ Жив" : "💀 Мёртв"));
        System.out.println("========================");
    }
}