import java.util.Random;

public class Board {
    public String[][] gameMapGenerate(int sizeBoard, String person, String monster, String castle) {
        String[][] board = new String[sizeBoard][sizeBoard];
        for (int y = 0; y < sizeBoard; y++) {
            for (int x = 0; x < sizeBoard; x++) {
                board[y][x] = "  ";
            }
        }

        int countMonster = sizeBoard * sizeBoard - sizeBoard - 1;
        Random rnd = new Random();
        for (int i = 0; i <= countMonster; i++) {
            int randomX = rnd.nextInt(sizeBoard);
            int randomY = rnd.nextInt(sizeBoard);
            if (!(randomX == 0 && randomY == sizeBoard - 1) && !board[randomY][randomX].equals(castle)) {
                board[randomY][randomX] = monster;
            }
        }

        int castleX = rnd.nextInt(sizeBoard);
        int castleY = 0;

        board[castleY][castleX] = castle;
        return board;
    }

    public static void gameBoardUpdate(String[][] board, int x, int y, String symbol) {
        if (x >= 0 && x < board[0].length && y >= 0 && y < board.length) {
            board[y][x] = symbol;
        }
    }
}