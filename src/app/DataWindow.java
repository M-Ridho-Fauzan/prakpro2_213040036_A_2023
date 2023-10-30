/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

/**
 *
 * @author ridho
 */
import java.util.ArrayList;
import java.util.List;

public class DataWindow {

    private ArrayList<Data> dataList;

    public DataWindow() {
        dataList = new ArrayList<>();
    }

    public void addData(Data data) {
        dataList.add(data);
    }

    public List<Data> getDataList() {
        return dataList;
    }

    public void removeData(Data data) {
        dataList.remove(data);
    }

    // Tambahkan metode untuk menghapus data berdasarkan indeks
    public void removeDataByIndex(int index) {
        if (index >= 0 && index < dataList.size()) {
            dataList.remove(index);
        }
    }
}
