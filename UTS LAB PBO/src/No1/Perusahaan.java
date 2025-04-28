package No1;

import java.util.ArrayList;
import java.util.List;

public class Perusahaan {
    private List<Karyawan> daftarKaryawan = new ArrayList<>();

    // Menambahkan karyawan baru
    public boolean tambahKaryawan(Karyawan karyawan) {
        for (Karyawan k : daftarKaryawan) {
            if (k.getId().equals(karyawan.getId())) {
                return false; // ID sudah ada
            }
        }
        daftarKaryawan.add(karyawan);
        return true;
    }

    // Menghapus karyawan berdasarkan ID
    public boolean hapusKaryawan(String id) {
        Karyawan karyawan = cariKaryawan(id);
        if (karyawan != null) {
            daftarKaryawan.remove(karyawan);
            return true;
        }
        return false;
    }

    // Mencari karyawan berdasarkan ID
    public Karyawan cariKaryawan(String id) {
        for (Karyawan k : daftarKaryawan) {
            if (k.getId().equals(id)) {
                return k;
            }
        }
        return null;
    }

    // Mengubah posisi karyawan
    public boolean ubahPosisiKaryawan(String id, String posisiBaru) {
        Karyawan karyawan = cariKaryawan(id);
        if (karyawan != null) {
            karyawan.setPosisi(posisiBaru);
            return true;
        }
        return false;
    }

    // Mengubah gaji karyawan
    public boolean ubahGajiKaryawan(String id, double gajiBaru) {
        if (gajiBaru < 0) {
            return false; // Validasi gaji tidak boleh negatif
        }
        Karyawan karyawan = cariKaryawan(id);
        if (karyawan != null) {
            karyawan.setGaji(gajiBaru);
            return true;
        }
        return false;
    }

    public List<Karyawan> getDaftarKaryawan() {
        return daftarKaryawan;
    }

    // Menampilkan semua karyawan
    public List<Karyawan> tampilkanSemuaKaryawan() {
        return daftarKaryawan;
    }
}
