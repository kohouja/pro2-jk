package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

    List<ToDoItem> items;
    String[] columns = new String[]{"Content", "Date", "Done"};

    public TableModel(){
        items = new ArrayList<>();
    }

    public void add(ToDoItem item){
        items.add(item);
        fireTableDataChanged();
    }

    public List<ToDoItem> getItems() {
        return items;
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ToDoItem item = items.get(rowIndex);
        switch (columnIndex){
            case 0: return item.getContent();
            case 1: return item.getDate();
            case 2: return item.isDone();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0: case 1: return String.class;
            case 2: return Boolean.class;
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0 || columnIndex == 2;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ToDoItem item = items.get(rowIndex);
        if (columnIndex == 0){
            item.setContent((String) aValue);
        }
        if (columnIndex == 2){
            item.setDone((boolean) aValue);
        }
    }

    public void setItems(List<ToDoItem> items) {
        this.items = items;
    }
}
