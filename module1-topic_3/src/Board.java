import java.util.Random;

public class Board {
    public String[][] gameMapGenerate(int sizeBoard, String person, String monsterImage,
                                      String castle, Monster[] monsters, Wall[] walls, int difficultyLevel) {
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

        int heroX = rnd.nextInt(sizeBoard);
        int heroY = sizeBoard - 1;
        while (heroX == castleX && heroY == castleY) {
            heroX = rnd.nextInt(sizeBoard);
        }

        int countMonster = Math.max(1, sizeBoard - 1);
        for (int i = 0; i < countMonster; i++) {
            int randomX, randomY;
            do {
                randomX = rnd.nextInt(sizeBoard);
                randomY = rnd.nextInt(sizeBoard);
            } while (board[randomY][randomX].equals(castle) ||
                    (randomX == heroX && randomY == heroY) ||
                    isPositionOccupiedByMonster(monsters, randomX, randomY, i));

            if (rnd.nextDouble() < 0.3 && difficultyLevel >= 3) {
                monsters[i] = new BigMonster(sizeBoard, difficultyLevel);
            } else {
                monsters[i] = new Monster(sizeBoard, difficultyLevel);
            }
            monsters[i].setPosition(randomX, randomY);
            board[randomY][randomX] = monsters[i].getImage();
        }

        // Генерация стен
        int wallCount = Math.max(1, sizeBoard / 2);
        for (int i = 0; i < wallCount; i++) {
            int wallX, wallY;
            do {
                wallX = rnd.nextInt(sizeBoard);
                wallY = rnd.nextInt(sizeBoard);
            } while (board[wallY][wallX].equals(castle) ||
                    (wallX == heroX && wallY == heroY) ||
                    isPositionOccupiedByMonster(monsters, wallX, wallY, countMonster) ||
                    isPositionOccupiedByWall(walls, wallX, wallY, i));

            walls[i] = new Wall(wallX, wallY);
            board[wallY][wallX] = walls[i].getImage();
        }

        return board;
    }

    private boolean isPositionOccupiedByMonster(Monster[] monsters, int x, int y, int currentCount) {
        for (int i = 0; i < currentCount; i++) {
            if (monsters[i] != null && monsters[i].conflictPerson(x, y)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPositionOccupiedByWall(Wall[] walls, int x, int y, int currentCount) {
        for (int i = 0; i < currentCount; i++) {
            if (walls[i] != null && walls[i].isWallAt(x, y)) {
                return true;
            }
        }
        return false;
    }

    public static void gameBoardUpdate(String[][] board, int x, int y, String symbol) {
        if (x >= 0 && x < board[0].length && y >= 0 && y < board.length) {
            board[y][x] = symbol;
        }
    }
}