import java.util.Random;
import java.util.Scanner;

public class Monster {
    private Scanner sc = new Scanner(System.in);
    private String image = "\uD83E\uDDDF\u200D";
    private int x, y;
    private Random rnd = new Random();

    public Monster(int sizeBoard) {
        this.y = rnd.nextInt(sizeBoard);
        this.x = rnd.nextInt(sizeBoard);
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
            // Монстр исчезает с карты
            board[this.y][this.x] = "  ";
            return true;
        }
    }

    private boolean handleMonsterBattle(Person player) {
        int difficulty = (player.getLives() > 1) ? 2 : 1;

        int num1, num2;
        if (difficulty == 1) { // easy
            num1 = rnd.nextInt(100) + 1;
            num2 = rnd.nextInt(100) + 1;
        } else { // hard
            num1 = rnd.nextInt(300) + 1;
            num2 = rnd.nextInt(300) + 1;
        }

        System.out.println("Чему равно " + num1 + " + " + num2 + "?");
        int answer = sc.nextInt();

        return answer == (num1 + num2);
    }
}