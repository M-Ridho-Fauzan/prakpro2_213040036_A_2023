/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

/**
 *
 * @author ridho
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TambahDataForm extends JFrame {

    private DataWindow dataWindow;
    private MyTableModel tableModel;
    private JTextField namaField;
    private JTextField telpField;
    private JTextArea alamatField;
    private JTextField passField;
    Boolean checker = true;

//    private JFrame frame;
    public TambahDataForm(DataWindow dataWindow, MyTableModel tableModel) {
        this.dataWindow = dataWindow;
        this.tableModel = new MyTableModel(dataWindow); // Kirim referensi DataWindow ke MyTableModel

        this.setTitle("Tambah Data"); // Mengatur judul frame

//        ==============
        namaField = new JTextField();
        namaField.setBounds(15, 60, 300, 30);

        JLabel namaLabel = new JLabel("Nama:");
        namaLabel.setBounds(15, 40, 300, 10);

        JLabel labelRequired1 = new JLabel("*Nama Wajib Diisi");
        labelRequired1.setBounds(315, 60, 450, 15);
        labelRequired1.setForeground(Color.RED);
        labelRequired1.setVisible(false); // Set label awalnya tidak terlihat

        namaField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (namaField.getText().isEmpty()) {
                    labelRequired1.setVisible(true);
                } else {
                    labelRequired1.setVisible(false);
                }
            }
        });

//        ===============
        telpField = new JTextField();
        telpField.setBounds(15, 120, 200, 30);

        JLabel nimLabel = new JLabel("No Telp:");
        nimLabel.setBounds(15, 100, 200, 15);

        JLabel labelRequired2 = new JLabel("*No Telp Wajib Diisi");
        labelRequired2.setBounds(215, 120, 450, 15);
        labelRequired2.setForeground(Color.RED);
        labelRequired2.setVisible(false); // Set label awalnya tidak terlihat

        telpField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (telpField.getText().isEmpty()) {
                    labelRequired2.setVisible(true);
                } else {
                    labelRequired2.setVisible(false);
                }
            }
        });

        //        =================
        JLabel labelRadio = new JLabel("Jenis Kelamin: ");
        labelRadio.setBounds(15, 160, 400, 10);

        JRadioButton radioButton1 = new JRadioButton("Laki-Laki");
        radioButton1.setBounds(15, 185, 400, 15);

        JRadioButton radioButton2 = new JRadioButton("Wanita");
        radioButton2.setBounds(15, 210, 400, 15);

        JRadioButton radioButton3 = new JRadioButton("Custom", true);
        radioButton3.setBounds(15, 235, 400, 15);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);
        bg.add(radioButton3);

//       ==========================
        JCheckBox checkBox = new JCheckBox("WNA (Warga Negara Asing)");
        checkBox.setBounds(15, 265, 400, 15);
        checkBox.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Jika tombol Enter ditekan, simulasi klik tombol Simpan
                    checkBox.doClick();
                }
            }
        });

        alamatField = new JTextArea();
        alamatField.setBounds(15, 325, 500, 50);

        JLabel alamatLabel = new JLabel("Alamat: ");
        alamatLabel.setBounds(15, 300, 500, 20);

        passField = new JTextField();
        passField.setBounds(15, 325, 200, 30);

        JLabel passLabel = new JLabel("No Passport:");
        passLabel.setBounds(15, 300, 300, 20);

        passField.setVisible(false);
        passLabel.setVisible(false);

        checkBox.addItemListener(e -> {
            if (checkBox.isSelected()) {
                checker = false;
                alamatField.setText("");
                passField.setVisible(true);
                passLabel.setVisible(true);
                alamatField.setVisible(false);
                alamatLabel.setVisible(false);
            } else {
                checker = true;
                passField.setText("");
                alamatField.setVisible(true);
                alamatLabel.setVisible(true);
                passField.setVisible(false);
                passLabel.setVisible(false);
            }
        });

        this.add(new JSeparator(JSeparator.HORIZONTAL)); // kunci

        JButton tambahButton = new JButton("Simpan");
        tambahButton.setBounds(15, 450, 100, 40);
        tambahButton.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Jika tombol Enter ditekan, simulasi klik tombol Simpan
                    tambahButton.doClick();
                }
            }
        });

//        JButton tambahButton = new JButton("Tambah Data");
        tambahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = namaField.getText();
                String telp = telpField.getText();
                String alamatValue = alamatField.getText();
                String passValue = passField.getText();
                String errorMessage = "Field ";

                if (checker == true && alamatValue.isEmpty()) {
                    errorMessage += "Alamat wajib diisi";

                    JOptionPane.showMessageDialog(TambahDataForm.this,
                            errorMessage, "Kesalahan", JOptionPane.ERROR_MESSAGE);
                } else if (checker == false && passValue.isEmpty()) {
                    errorMessage += "Passport wajib diisi";

                    JOptionPane.showMessageDialog(TambahDataForm.this,
                            errorMessage, "Kesalahan", JOptionPane.ERROR_MESSAGE);
                } else {

                    if (nama.isEmpty() && telp.isEmpty()) {

                        errorMessage += "Nama & No Telp wajib diisi";

                        JOptionPane.showMessageDialog(TambahDataForm.this,
                                errorMessage, "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    } else if (nama.isEmpty()) {
                        errorMessage += "Nama wajib diisi";
                        JOptionPane.showMessageDialog(TambahDataForm.this,
                                errorMessage, "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    } else if (telp.isEmpty()) {
                        errorMessage += "No Telp wajib diisi";
                        JOptionPane.showMessageDialog(TambahDataForm.this,
                                errorMessage, "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Mendapatkan nilai radio button yang dipilih
                        String selectedRadioButton = "";
                        if (radioButton1.isSelected()) {
                            selectedRadioButton = radioButton1.getText();
                        }
                        if (radioButton2.isSelected()) {
                            selectedRadioButton = radioButton2.getText();
                        }
                        if (radioButton3.isSelected()) {
                            selectedRadioButton = radioButton3.getText();
                        }
                        if (radioButton1.isSelected()) {
                            selectedRadioButton = "Laki-Laki";
                        } else if (radioButton2.isSelected()) {
                            selectedRadioButton = "Wanita";
                        } else if (radioButton3.isSelected()) {
                            selectedRadioButton = "Custom";
                        }

                        // Mendapatkan status checkbox
                        boolean isWNA = checkBox.isSelected();
                        String wnaLabel = isWNA ? "WNA" : "Bukan WNA";

                        Data newData = new Data(nama, telp);
                        newData.setSelectedRadioButton(selectedRadioButton);
                        newData.setCheckBoxSelected(wnaLabel);

// Set atribut alamat dan pass pada objek newData
                        if (alamatValue != null && !alamatValue.isEmpty()) {
                            newData.setAlamat(alamatValue);
                        } else {
                            newData.setAlamat("-");
                        }

                        if (passValue != null && !passValue.isEmpty()) {
                            newData.setPass(passValue);
                        } else {
                            newData.setPass("-");
                        }

                        dataWindow.addData(newData);
                        tableModel.addData(newData);

                        // Kosongkan field input
                        namaField.setText("");
                        telpField.setText("");
                        alamatField.setText("");
                        passField.setText("");
                        radioButton1.setSelected(false);
                        radioButton2.setSelected(false);
                        radioButton3.setSelected(true);
                        checkBox.setSelected(false);
                    }
                }
            }
        });
        this.add(checkBox);
        this.add(tambahButton);
        this.add(namaLabel);
        this.add(nimLabel);
        this.add(namaField);
        this.add(telpField);
        this.add(labelRequired1);
        this.add(labelRequired2);
        this.add(labelRadio); // jangan di pindahin
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(radioButton3);
        this.add(alamatLabel);
        this.add(alamatField);
        this.add(passLabel);
        this.add(passField);
        this.add(new JSeparator(JSeparator.HORIZONTAL));

        // Atur dimensi frame
        this.setSize(650, 650); // Sesuaikan lebar dan tinggi sesuai kebutuhan
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void close() {
        this.dispose();
    }
}
