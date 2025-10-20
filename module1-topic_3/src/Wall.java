public class Wall {
    private int x;
    private int y;
    private String image = "🧱";

    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
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

    public boolean isWallAt(int targetX, int targetY) {
        return targetX == this.x && targetY == this.y;
    }
}