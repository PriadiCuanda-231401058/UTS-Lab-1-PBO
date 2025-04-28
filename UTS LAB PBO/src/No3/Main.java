package No3;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        LotreBoard lotre = new LotreBoard();
        boolean running = true;

        System.out.println("Selamat datang di Lotre Gosok Bang Pawwry!");

        while (running) {
            lotre.displayBoard();
            System.out.print("\nMasukkan baris (0-3): ");
            int row = Integer.parseInt(input.nextLine());
            System.out.print("Masukkan kolom (0-4): ");
            int col = Integer.parseInt(input.nextLine());

            boolean hasil = lotre.guess(row, col);

            if (!hasil) {
                // Ketemu bom
                lotre.displayBoard();
                System.out.println("\nKamu menemukan BOM! Game Over!");
                running = false;
            } else if (lotre.isGameOver()) {
                // Menang
                lotre.displayBoard();
                System.out.println("\nSelamat! Kamu berhasil membuka semua kotak aman!");
                running = false;
            }
        }

        System.out.println("\nTerima kasih telah bermain Lotre Bang Pawwry!");
        input.close();
    }
}
