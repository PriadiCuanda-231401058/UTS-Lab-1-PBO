package No2;

public class Kendaraan {
    private String jenis;
    private int lamaParkir;
    private static final int TARIF_MOTOR = 2000;
    private static final int TARIF_MOBIL = 5000;
    private static final int TARIF_TRUK = 9000;

    public Kendaraan(String jenis) {
        this.jenis = jenis;
    }

    // Overload 1: input langsung lama parkir
    public int hitungBiaya(int jam) {
        this.lamaParkir = jam;
        return hitungTotal();
    }

    // Overload 2: input jam masuk dan keluar
    public int hitungBiaya(int jamMasuk, int jamKeluar) {
        this.lamaParkir = jamKeluar - jamMasuk;
        if (this.lamaParkir < 0) {
            this.lamaParkir = 0; // menghindari durasi negatif
        }
        return hitungTotal();
    }

    private int hitungTotal() {
        int tarif = 0;
        switch (jenis.toLowerCase()) {
            case "motor":
                tarif = TARIF_MOTOR;
                break;
            case "mobil":
                tarif = TARIF_MOBIL;
                break;
            case "truk":
                tarif = TARIF_TRUK;
                break;
            default:
                tarif = 0;
                break;
        }
        int total = lamaParkir * tarif;

        if (lamaParkir > 5) {
            total = (int) (total * 0.9); // Diskon 10%
        }
        return total;
    }

    public void tampilkanRingkasan() {
        int totalBiaya = hitungTotal();
        System.out.println("Jenis Kendaraan : " + jenis);
        System.out.println("Lama Parkir     : " + lamaParkir + " jam");
        System.out.println("Total Biaya     : Rp" + totalBiaya);
        System.out.println("-----------------------------------");
    }

    public int getTotalBiaya() {
        return hitungTotal();
    }
}
