/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.util.Objects;

/**
 *
 * @author ridho
 */
public class Data {

    private String nama;
    private String telp;
    private String alamat;
    private String pass;
    private String checkBoxSelected; // Mengubah tipe data atribut menjadi String
    private String selectedRadioButton;

    public Data(String nama, String telp) {
        this.nama = nama;
        this.telp = telp;
        this.checkBoxSelected = ""; // Default: checkBox tidak terpilih
        this.selectedRadioButton = ""; // Default: radioButton yang dipilih adalah Option 1
        this.alamat = "-";
        this.pass = "-";
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
//    ===============

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }
//    ===============

    public String getCheckBoxSelected() {
        return checkBoxSelected;
    }

    public void setCheckBoxSelected(String selected) {
        this.checkBoxSelected = selected;
    }
//    ===============

    public String getSelectedRadioButton() {
        return selectedRadioButton;
    }

    public void setSelectedRadioButton(String selectedOption) {
        this.selectedRadioButton = selectedOption;
    }
//    ===============

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
//    ===============

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Data data = (Data) obj;
        return Objects.equals(nama, data.nama)
                && Objects.equals(telp, data.telp)
                && Objects.equals(alamat, data.alamat)
                && Objects.equals(pass, data.pass)
                && Objects.equals(checkBoxSelected, data.checkBoxSelected)
                && Objects.equals(selectedRadioButton, data.selectedRadioButton);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nama, telp, alamat, pass, checkBoxSelected, selectedRadioButton);
    }
}
