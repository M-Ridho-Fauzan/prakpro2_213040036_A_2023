/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

/**
 *
 * @author ridho
 */
import app.Main.varJauh;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {

    // Deklarasi atribut dataWindow
    private DataWindow dataWindow;

    private String[] columnNames = {"Nama", "No Telp", "Jenis Kelamin", "WNA", "alamat", "Passport"};
    private ArrayList<Data> data = new ArrayList<>();

    public MyTableModel(DataWindow dataWindow) {
        super(new Object[]{"Nama", "No Telp", "Jenis Kelamin", "WNA", "alamat", "Passport"}, 0); // Menambahkan kolom ke model tabel
        this.dataWindow = dataWindow; // Simpan referensi dataWindow
    }

    public Object getValueAt(int row, int col) {
        if (row >= 0 && row < data.size()) {
            Data rowData = data.get(row);
            if (col == 0) {
                return rowData.getNama();
            } else if (col == 1) {
                return rowData.getTelp();
            } else if (col == 2) {
                return rowData.getSelectedRadioButton();
            } else if (col == 3) {
                return rowData.getCheckBoxSelected();
            } else if (col == 4) {
                return rowData.getAlamat();
            } else if (col == 5) {
                return rowData.getPass();
            }
        }
        return null;
    }

//    public void removeData(int rowIndex) {
//        if (rowIndex >= 0 && rowIndex < data.size()) {
//            data.remove(rowIndex);
//            fireTableRowsDeleted(rowIndex, rowIndex);
//        }
//    }
    public void removeData(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < data.size()) {
            data.remove(rowIndex);
            dataWindow.removeDataByIndex(rowIndex); // Hapus data dari DataWindow
            fireTableRowsDeleted(rowIndex, rowIndex);
        }
    }

    public void addData(Data newData) {
        data.add(newData);
        addRow(new Object[]{newData.getNama(), newData.getTelp(), newData.getCheckBoxSelected(),
            newData.getSelectedRadioButton(), newData.getAlamat(), newData.getPass()});
    }

    public boolean isCellEditable(int row, int col) {
        if (varJauh.varKon == true) {
            return false;
        } else {
            return col == 0 || col == 1 || col == 2 || col == 3 || col == 4 || col == 5; // Kolom "Nama" (0) dan "NIM" (1) dapat diedit
        }
    }

    public void setValueAt(Object value, int row, int col) {
        Data rowData = data.get(row);
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
        fireTableCellUpdated(row, col); // Memperbarui tampilan tabel
    }
}
