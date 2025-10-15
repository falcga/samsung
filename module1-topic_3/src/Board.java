import java.util.Random;

public class Board {
    public String[][] gameMapGenerate(int sizeBoard, String person, String monsterImage, String castle, Monster[] monsters) {
        String[][] board = new String[sizeBoard][sizeBoard];

        for (int y = 0; y < sizeBoard; y++) {
            for (int x = 0; x < sizeBoard; x++) {
                board[y][x] = "  ";
            }
        }

        Random rnd = new Random();

        int castleX = rnd.nextInt(sizeBoard);
        int castleY = 0;
        board[castleY][castleX] = castle;

        int countMonster = Math.max(1, sizeBoard - 1);
        for (int i = 0; i < countMonster; i++) {
            int randomX, randomY;
            do {
                randomX = rnd.nextInt(sizeBoard);
                randomY = rnd.nextInt(sizeBoard);
            } while (board[randomY][randomX].equals(castle) || (randomX == 0 && randomY == sizeBoard - 1));

            board[randomY][randomX] = monsterImage;
            monsters[i].setPosition(randomX, randomY);
        }

        return board;
    }

    public static void gameBoardUpdate(String[][] board, int x, int y, String symbol) {
        if (x >= 0 && x < board[0].length && y >= 0 && y < board.length) {
            board[y][x] = symbol;
        }
    }
}