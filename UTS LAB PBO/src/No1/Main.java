package No1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Perusahaan perusahaan = new Perusahaan();

        int pilihan;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Tambah No1.Karyawan");
            System.out.println("2. Hapus No1.Karyawan");
            System.out.println("3. Ubah Posisi No1.Karyawan");
            System.out.println("4. Ubah Gaji No1.Karyawan");
            System.out.println("5. Tampilkan Semua No1.Karyawan");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            pilihan = input.nextInt();
            input.nextLine(); // Membersihkan newline

            switch (pilihan) {
                case 1:
                    System.out.print("ID: ");
                    String id = input.nextLine();
                    System.out.print("Nama: ");
                    String nama = input.nextLine();
                    System.out.print("Posisi: ");
                    String posisi = input.nextLine();
                    System.out.print("Gaji: ");
                    double gaji = input.nextDouble();
                    input.nextLine();
                    Karyawan karyawanBaru = new Karyawan(id, nama, posisi, gaji);
                    perusahaan.tambahKaryawan(karyawanBaru);
                    break;
                case 2:
                    System.out.print("Masukkan ID karyawan yang ingin dihapus: ");
                    String idHapus = input.nextLine();
                    perusahaan.hapusKaryawan(idHapus);
                    break;
                case 3:
                    System.out.print("Masukkan ID karyawan yang ingin diubah posisinya: ");
                    String idUbahPosisi = input.nextLine();
                    System.out.print("Posisi baru: ");
                    String posisiBaru = input.nextLine();
                    perusahaan.ubahPosisiKaryawan(idUbahPosisi, posisiBaru);
                    break;
                case 4:
                    System.out.print("Masukkan ID karyawan yang ingin diubah gajinya: ");
                    String idUbahGaji = input.nextLine();
                    System.out.print("Gaji baru: ");
                    double gajiBaru = input.nextDouble();
                    input.nextLine();
                    perusahaan.ubahGajiKaryawan(idUbahGaji, gajiBaru);
                    break;
                case 5:
                    perusahaan.tampilkanSemuaKaryawan();
                    break;
                case 0:
                    System.out.println("Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);

        input.close();
    }
}
