import java.util.Random;
import java.util.Scanner;

public class Monster {
    protected Scanner sc = new Scanner(System.in);
    protected String image = "\uD83E\uDDDF\u200D";
    protected int x, y;
    protected Random rnd = new Random();
    protected int difficultyLevel;

    public Monster(int sizeBoard, int difficultyLevel) {
        this.y = rnd.nextInt(sizeBoard);
        this.x = rnd.nextInt(sizeBoard);
        this.difficultyLevel = difficultyLevel;
    }

    public String getImage() {
        return image;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean conflictPerson(int perX, int perY) {
        return perY == this.y && perX == this.x;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean monsterHere(Person player, String[][] board) {
        System.out.println("👾 Встретился монстр! Решите пример чтобы продолжить:");
        boolean battleResult = handleMonsterBattle(player);

        if (!battleResult) {
            player.decreaseLives();
            System.out.println("💔 Неправильный ответ! Потеряна жизнь.");
            return false;
        } else {
            System.out.println("✅ Правильно! Монстр побежден!");
            board[this.y][this.x] = "  ";
            return true;
        }
    }

    protected boolean handleMonsterBattle(Person player) {
        int num1, num2;
        int maxNumber = getMaxNumberForDifficulty();

        num1 = rnd.nextInt(maxNumber) + 1;
        num2 = rnd.nextInt(maxNumber) + 1;

        System.out.println("Чему равно " + num1 + " + " + num2 + "?");
        int answer = sc.nextInt();

        return answer == (num1 + num2);
    }

    protected int getMaxNumberForDifficulty() {
        switch (difficultyLevel) {
            case 1: return 50;   // Легко
            case 2: return 100;  // Средне
            case 3: return 200;  // Сложно
            case 4: return 300;  // Очень сложно
            case 5: return 500;  // Эксперт
            default: return 100;
        }
    }
}