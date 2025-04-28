package No1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainGUI extends JFrame {
    private Perusahaan perusahaan;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField tfId, tfNama, tfPosisi, tfGaji, tfCari;


    public MainGUI() {
        perusahaan = new Perusahaan(); // buat objek perusahaan
        initUI();
    }

    private void initUI() {
        //atur layout
        setTitle("Manajemen No1.Karyawan");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel input
        JPanel panelInput = new JPanel(new GridLayout(4, 2, 10, 10));
        panelInput.add(new JLabel("ID:"));
        tfId = new JTextField();
        panelInput.add(tfId);

        panelInput.add(new JLabel("Nama:"));
        tfNama = new JTextField();
        panelInput.add(tfNama);

        panelInput.add(new JLabel("Posisi:"));
        tfPosisi = new JTextField();
        panelInput.add(tfPosisi);

        panelInput.add(new JLabel("Gaji:"));
        tfGaji = new JTextField();
        panelInput.add(tfGaji);

        // Panel tabel
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nama", "Posisi", "Gaji"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Panel cari
        JPanel panelCari = new JPanel();
        tfCari = new JTextField(15);
        JButton btnCari = new JButton("Cari ID");
        JButton btnRefresh = new JButton("Tampilkan Semua");
        panelCari.add(new JLabel("Cari:"));
        panelCari.add(tfCari);
        panelCari.add(btnCari);
        panelCari.add(btnRefresh);
        JButton btnTambah = new JButton("Tambah");
        panelCari.add(btnTambah);

        JButton btnEdit = new JButton("Edit");
        panelCari.add(btnEdit);

        JButton btnHapus = new JButton("Hapus");
        panelCari.add(btnHapus);

        // Layout utama
        setLayout(new BorderLayout());
        add(panelInput, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelCari, BorderLayout.SOUTH);


        // Event tombol tambah
        btnTambah.addActionListener(e -> tambahKaryawan());
        // Event tombol edit
        btnEdit.addActionListener(e -> editKaryawan());
        btnHapus.addActionListener(e -> hapusKaryawan());
        // Event cari
        btnCari.addActionListener(e -> cariKaryawan());
        btnRefresh.addActionListener(e -> refreshTabel());

        setVisible(true);
    }

    //untuk method agar bisa menambah karyawan
    private void tambahKaryawan() {
        try {
            String id = tfId.getText().trim();
            String nama = tfNama.getText().trim();
            String posisi = tfPosisi.getText().trim();
            double gaji = Double.parseDouble(tfGaji.getText().trim());

            if (gaji < 0) {
                JOptionPane.showMessageDialog(this, "Gaji tidak boleh negatif.");
                return;
            }

            Karyawan karyawan = new Karyawan(id, nama, posisi, gaji);
            if (!perusahaan.tambahKaryawan(karyawan)) {
                JOptionPane.showMessageDialog(this, "ID sudah digunakan!");
                return;
            }

            refreshTabel();
            kosongkanInput();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Gaji harus angka!");
        }
    }

    // untuk method agar bisa menghapus karyawan
    private void hapusKaryawan() {
        // Ambil baris yang dipilih dari JTable
        int selectedRow = table.getSelectedRow();

        // Validasi jika tidak ada baris yang dipilih
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Silakan pilih karyawan yang akan dihapus.");
            return;
        }

        // Ambil ID karyawan yang dipilih
        String id = tableModel.getValueAt(selectedRow, 0).toString();

        // Hapus karyawan dari perusahaan
        boolean berhasilHapus = perusahaan.hapusKaryawan(id);

        if (berhasilHapus) {
            // Jika berhasil, refresh tabel dan tampilkan pesan sukses
            JOptionPane.showMessageDialog(this, "No1.Karyawan berhasil dihapus.");
            refreshTabel();  // Memanggil refreshTabel untuk memperbarui data
        } else {
            // Jika gagal, tampilkan pesan kesalahan
            JOptionPane.showMessageDialog(this, "Gagal menghapus karyawan. ID tidak ditemukan.");
        }
    }

    // untuk edit data karyawan
    private void editKaryawan() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih karyawan yang akan diedit!");
            return;
        }

        try {
            String id = tableModel.getValueAt(selectedRow, 0).toString();
            String nama = tfNama.getText().trim();
            String posisi = tfPosisi.getText().trim();
            double gaji = Double.parseDouble(tfGaji.getText().trim());

            if (gaji < 0) {
                JOptionPane.showMessageDialog(this, "Gaji tidak boleh negatif.");
                return;
            }

            Karyawan karyawan = perusahaan.cariKaryawan(id);
            if (karyawan != null) {
                karyawan.setNama(nama);
                karyawan.setPosisi(posisi);
                karyawan.setGaji(gaji);
                refreshTabel();
                kosongkanInput();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Gaji harus angka!");
        }
    }

    // untuk fitur search karywan
    private void cariKaryawan() {
        String id = tfCari.getText().trim();
        Karyawan karyawan = perusahaan.cariKaryawan(id);

        tableModel.setRowCount(0);
        if (karyawan != null) {
            tableModel.addRow(new Object[]{karyawan.getId(), karyawan.getNama(), karyawan.getPosisi(), karyawan.getGaji()});
        } else {
            JOptionPane.showMessageDialog(this, "Karyawan tidak ditemukan!");
        }
    }

    //untuk load ulang tabel
    private void refreshTabel() {
        tableModel.setRowCount(0);
        for (Karyawan k : perusahaan.getDaftarKaryawan()) {
            tableModel.addRow(new Object[]{k.getId(), k.getNama(), k.getPosisi(), k.getGaji()});
        }
    }

    //untuk mengosongkan tf stelah melakukan sesuatu
    private void kosongkanInput() {
        tfId.setText("");
        tfNama.setText("");
        tfPosisi.setText("");
        tfGaji.setText("");
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}
