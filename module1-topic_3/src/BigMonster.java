import java.util.Random;

public class BigMonster extends Monster {
    private String image = "👹";

    public BigMonster(int sizeBoard, int difficultyLevel) {
        super(sizeBoard, difficultyLevel);
        this.image = "👹";
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public boolean monsterHere(Person player, String[][] board) {
        System.out.println("👹 Встретился БОЛЬШОЙ монстр! Решите сложный пример!");
        boolean battleResult = handleMonsterBattle(player);

        if (!battleResult) {
            player.decreaseLives();
            System.out.println("💔 Неправильный ответ! Потеряна жизнь.");
            return false;
        } else {
            System.out.println("✅ Правильно! Большой монстр побежден!");
            board[this.y][this.x] = "  ";
            return true;
        }
    }

    @Override
    protected boolean handleMonsterBattle(Person player) {
        int operation = rnd.nextInt(3);
        int num1, num2;
        int maxNumber = getMaxNumberForDifficulty() * 2;
        num1 = rnd.nextInt(maxNumber) + 1;
        num2 = rnd.nextInt(maxNumber) + 1;

        int correctAnswer;
        String operationSymbol;

        switch (operation) {
            case 0:
                correctAnswer = num1 + num2;
                operationSymbol = "+";
                break;
            case 1:
                correctAnswer = num1 - num2;
                operationSymbol = "-";
                break;
            case 2:
                num1 = rnd.nextInt(20) + 1;
                num2 = rnd.nextInt(10) + 1;
                correctAnswer = num1 * num2;
                operationSymbol = "*";
                break;
            default:
                correctAnswer = num1 + num2;
                operationSymbol = "+";
        }

        System.out.println("Чему равно " + num1 + " " + operationSymbol + " " + num2 + "?");
        int answer = sc.nextInt();

        return answer == correctAnswer;
    }

    @Override
    protected int getMaxNumberForDifficulty() {
        return super.getMaxNumberForDifficulty() * 2;
    }
}