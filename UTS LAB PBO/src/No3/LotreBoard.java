package No3;
import java.util.Random;

public class LotreBoard {
    private char[][] board;
    private boolean[][] revealed;
    private int[][] data;
    private final int rows = 4;
    private final int cols = 5;
    private final int totalBombs = 2;
    private int safeOpened;

    // buat papan lotre
    public LotreBoard() {
        board = new char[rows][cols];
        revealed = new boolean[rows][cols];
        data = new int[rows][cols];
        generateBoard();
    }

    public void generateBoard() {
        // Inisialisasi semua kotak aman
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = '*';
                revealed[i][j] = false;
                data[i][j] = 0; // 0 = aman
            }
        }

        // Menempatkan 2 bom secara acak
        Random rand = new Random();
        int bombPlaced = 0;
        while (bombPlaced < totalBombs) {
            int r = rand.nextInt(rows);
            int c = rand.nextInt(cols);
            if (data[r][c] == 0) {
                data[r][c] = 1; // 1 = bom
                bombPlaced++;
            }
        }
    }

    // menampilkan papan lotre
    public void displayBoard() {
        System.out.println("\nPapan Lotre:");
        System.out.print("   ");
        for (int j = 0; j < cols; j++) {
            System.out.print(j + " ");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(i + "  ");
            for (int j = 0; j < cols; j++) {
                if (revealed[i][j]) {
                    if (data[i][j] == 1) {
                        System.out.print("X ");
                    } else {
                        System.out.print("O ");
                    }
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }

    // merespon tebakan pemain
    public boolean guess(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            System.out.println("Posisi di luar papan! Coba lagi.");
            return true; // Tidak mengakhiri game
        }
        if (revealed[row][col]) {
            System.out.println("Kotak ini sudah dibuka! Pilih kotak lain.");
            return true;
        }
        revealed[row][col] = true;
        if (data[row][col] == 1) {
            // Ketemu bom
            return false;
        } else {
            // Aman
            safeOpened++;
            return true;
        }
    }

    public boolean isGameOver() {
        // Jika sudah buka semua kotak aman
        return safeOpened == (rows * cols - totalBombs);
    }
}
