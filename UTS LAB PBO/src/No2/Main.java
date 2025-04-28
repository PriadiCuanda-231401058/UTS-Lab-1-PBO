package No2;
//import No2.Kendaraan;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Kendaraan> daftarKendaraan = new ArrayList<>();
        boolean lanjut = true;

        //perulangan input kendaraan dan atribut lainnya
        while (lanjut) {
            System.out.println("=== Sistem ParkirChan ===");
            System.out.print("Enter Vehicle Type (Motor/Mobil/Truk): ");// pilih jenis kendaraan
            String jenis = input.nextLine();

            Kendaraan kendaraan = new Kendaraan(jenis);// buat objek kendaraan baru
            // pilih cara memasukan durasi
            System.out.print("Enter Duration (Manual/Time): ");
            String pilihan = input.nextLine();
            if (pilihan.equalsIgnoreCase("Manual")) {
                System.out.print("Enter Duration (in Hour): ");
                int jam = Integer.parseInt(input.nextLine());
                kendaraan.hitungBiaya(jam);
            } else if (pilihan.equalsIgnoreCase("Time")) {
                System.out.print("Enter Entry Time: ");
                int jamMasuk = Integer.parseInt(input.nextLine());
                System.out.print("Enter Exit Time: ");
                int jamKeluar = Integer.parseInt(input.nextLine());
                kendaraan.hitungBiaya(jamMasuk, jamKeluar);
            } else {
                System.out.println("Invalid.");
                continue; // kembali ke awal
            }

            // tampilkan hasil
            kendaraan.tampilkanRingkasan();
            daftarKendaraan.add(kendaraan);

            // perulangan jika memilih "y"
            System.out.print("Add Another Vehicle? (y/n): ");
            String jawaban = input.nextLine();
            if (!jawaban.equalsIgnoreCase("y")) {
                lanjut = false;
            }
            System.out.println();
        }

        // tampilkan Rekap Akhir
        int totalBiayaSemua = 0;
        System.out.println("\n=== Ringkasan Akhir ===");
        for (Kendaraan k : daftarKendaraan) {
            k.tampilkanRingkasan();
            totalBiayaSemua += k.getTotalBiaya();
        }
        System.out.println("Jumlah Total Kendaraan : " + daftarKendaraan.size());
        System.out.println("Total Semua Biaya Parkir : Rp" + totalBiayaSemua);
    }
}