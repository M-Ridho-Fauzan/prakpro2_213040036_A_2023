/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author ridho & chatGPT
 */
public class Main extends JFrame {

    boolean firstClick = true;
    DataWindow dataWindow;
    MyTableModel tableModel;
    JTable table;

    public class varJauh {

        public static boolean varKon = false;
    }

    public Main() {
        dataWindow = new DataWindow();
        tableModel = new MyTableModel(dataWindow); // Kirim referensi DataWindow ke MyTableModel
        table = new JTable(tableModel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

//=================== Notifikasi Close
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(Main.this,
                        "Anda yakin ingin keluar?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0); // Tutup aplikasi jika user memilih "Ya"
                }
            }
        });

//=================== Judul Besar
        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Form Biodata");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(headerLabel);
        headerPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centering header

//==================== Panel Button 
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        JButton tambahBtn = new JButton("Tambah Data");
        JButton editBtn = new JButton("Edit");
        JButton simpanBtn = new JButton("Simpan"); // Tombol baru untuk menyimpan perubahan
        JButton hapusBtn = new JButton("Hapus");
        JButton txtBtn = new JButton("Simpan Ke .Txt");

        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        buttonPanel.add(tambahBtn);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        buttonPanel.add(editBtn);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        buttonPanel.add(simpanBtn); // fungsi edit
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        buttonPanel.add(hapusBtn);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        buttonPanel.add(txtBtn);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));

        simpanBtn.setEnabled(false); // aktif oleh fungsi edit

//=================== Membuat JPanel luar untuk menambahkan margin ke tablePanel
        JPanel tableMarginPanelBtn = new JPanel();
        tableMarginPanelBtn.setLayout(new BoxLayout(tableMarginPanelBtn, BoxLayout.PAGE_AXIS));
        tableMarginPanelBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tableMarginPanelBtn.add(buttonPanel);
        tableMarginPanelBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // Centering buttons

//================================ margin
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.PAGE_AXIS));
        table = new JTable(tableModel);
        JScrollPane scrollableTable = new JScrollPane(table);
        tablePanel.add(scrollableTable);

        JPanel tableMarginPanel = new JPanel();
        tableMarginPanel.setLayout(new BoxLayout(tableMarginPanel, BoxLayout.PAGE_AXIS));
        tableMarginPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tableMarginPanel.add(tablePanel);
        tableMarginPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Mengatur posisi tengah untuk tableMarginPanel

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(tableMarginPanelBtn, BorderLayout.CENTER);
        this.add(tableMarginPanel, BorderLayout.SOUTH);
        this.setMaximumSize(new Dimension(700, Integer.MAX_VALUE)); // Set maximum width
        this.setMinimumSize(new Dimension(850, 650)); // Set minimum width
        this.setSize(850, 650);
        this.setLocationRelativeTo(null);

        table.setEnabled(false);  // Diaktifkan oleh fungsi edit

        hapusBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String errorMessage = "Field ";
                if (table.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null,
                            "Data Masih Kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (firstClick) {
                        editBtn.setEnabled(false);
                        table.setEnabled(true);
                        varJauh.varKon = true;
                        firstClick = false;
                    } else {
                        varJauh.varKon = false;

                        int response = JOptionPane.showConfirmDialog(Main.this,
                                "Yakin Di Hapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                        if (response == JOptionPane.YES_OPTION) {
                            table.setEnabled(false);// Tutup aplikasi jika user memilih "Ya"
                            firstClick = true;
                            editBtn.setEnabled(true);
//                        fungsi hapus
                            int[] selectedRows = table.getSelectedRows();
                            DefaultTableModel model = (DefaultTableModel) table.getModel();
                            if (selectedRows.length > 0) {
                                Arrays.sort(selectedRows); // Urutkan indeks baris terpilih
                                for (int i = selectedRows.length - 1; i >= 0; i--) {
                                    int selectedRow = selectedRows[i];
                                    if (selectedRow >= 0 && selectedRow < model.getRowCount()) {
                                        model.removeRow(selectedRow);
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Pilih baris yang ingin dihapus.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                                firstClick = true;
                                editBtn.setEnabled(true);
                            }
                        }
                    }
                }
            }
        });
//        ===============

        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aktifkan mode pengeditan
                table.setEnabled(true);
                editBtn.setEnabled(false);
                hapusBtn.setEnabled(false);
                simpanBtn.setEnabled(true);

                // Setel editor untuk kolom "Nama"
                TableColumn namaColumn = table.getColumnModel().getColumn(0);
                namaColumn.setCellEditor(new DefaultCellEditor(new JTextField()));

                // Setel editor untuk kolom "NIM"
                TableColumn telpColumn = table.getColumnModel().getColumn(1);
                telpColumn.setCellEditor(new DefaultCellEditor(new JTextField()));

                // Setel editor untuk kolom "checkBoxSelected"
                TableColumn checkBoxSelectedColumn = table.getColumnModel().getColumn(2);
                checkBoxSelectedColumn.setCellEditor(new DefaultCellEditor(new JTextField()));

                // Setel editor untuk kolom "selectedRadioButton"
                TableColumn selectedRadioButtonColumn = table.getColumnModel().getColumn(3);
                selectedRadioButtonColumn.setCellEditor(new DefaultCellEditor(new JTextField()));

                // Setel editor untuk kolom "alamat"
                TableColumn alamatColumn = table.getColumnModel().getColumn(4);
                alamatColumn.setCellEditor(new DefaultCellEditor(new JTextField()));

                // Setel editor untuk kolom "pass"
                TableColumn passColumn = table.getColumnModel().getColumn(5);
                passColumn.setCellEditor(new DefaultCellEditor(new JTextField()));
            }
        });
        //        ====================
        simpanBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Menghentikan pengeditan sel aktif
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                    table.setEnabled(false);
                    simpanBtn.setEnabled(false);
                    editBtn.setEnabled(true);
                    hapusBtn.setEnabled(true);
                }
                table.setEnabled(false);
                simpanBtn.setEnabled(false);
                editBtn.setEnabled(true);
                hapusBtn.setEnabled(true);

                // Menyimpan perubahan dari tabel ke objek Data
                int rowCount = table.getRowCount();
                int colCount = table.getColumnCount();

                // Menyimpan perubahan dari tabel ke objek Data
                for (int row = 0; row < rowCount; row++) {
                    for (int col = 0; col < colCount; col++) {
                        Object value = table.getValueAt(row, col);
                        Data rowData = dataWindow.getDataList().get(row);

                        if (col == 0) {
                            rowData.setNama(value.toString());
                        } else if (col == 1) {
                            rowData.setTelp(value.toString());
                        } else if (col == 2) {
                            rowData.setSelectedRadioButton(value.toString());
                        } else if (col == 3) {
                            rowData.setCheckBoxSelected(value.toString());
                        } else if (col == 4) {
                            rowData.setAlamat(value.toString());
                        } else if (col == 5) {
                            rowData.setPass(value.toString());
                        }
                    }
                }
                // Memperbarui model tabel
                tableModel.fireTableDataChanged();
            }
        });

        txtBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSaveFileDialog();
            }
        });

        tambahBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Event handler untuk tombol Tambah Data
                TambahDataForm tambahDataForm = new TambahDataForm(dataWindow, tableModel); // form tambah data
            }
        });
    }

    private void saveDataToTxtFile(String filePath) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println("==============================");
            writer.println("|  Author: M.Ridho Fauzan :)  |");
            writer.println("==============================");
            for (Data rowData : dataWindow.getDataList()) {
//                writer.println(rowData.getNama() + "\t" + rowData.getNim());
                writer.println("Nama: " + rowData.getNama());
                writer.println("Telepon: " + rowData.getTelp());
                writer.println("RadioButton: " + rowData.getSelectedRadioButton());
                writer.println("CheckBoxSelected: " + rowData.getCheckBoxSelected());
                writer.println("Alamat: " + rowData.getAlamat());
                writer.println("Passport: " + rowData.getPass());
                writer.println(); // Add a blank line to separate entries
                writer.println("==============================");
            }
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan ke "
                    + filePath, "Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data ke "
                    + filePath, "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void showSaveFileDialog() {
        FileDialog fileDialog = new FileDialog(new Frame(), "Simpan Data Ke .Txt", FileDialog.SAVE);
        fileDialog.setFile("data Mahasiswa.txt"); // Nama file default
        fileDialog.setFilenameFilter(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });

        fileDialog.setVisible(true);

        String selectedFilePath = fileDialog.getDirectory() + fileDialog.getFile();
        if (fileDialog.getDirectory() != null && fileDialog.getFile() != null) {
            saveDataToTxtFile(selectedFilePath);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main g = new Main();
            g.setVisible(true);
        });
    }
}
