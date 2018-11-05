package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableFeedModel extends AbstractTableModel {
    List<UrlItem> urls;
    String[] columns = new String[]{"URL", "Added millis", "Alias", "Should show"};

    public TableFeedModel(){
        urls = new ArrayList<>();
    }

    public void add(UrlItem urlItem){
        urls.add(urlItem);
        fireTableDataChanged();
    }

    public List<UrlItem> getItems() {
        return urls;
    }

    @Override
    public int getRowCount() {
        return urls.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        UrlItem urlItem = urls.get(rowIndex);
        switch (columnIndex){
            case 0: return urlItem.getUrl();
            case 1: return urlItem.getAddedMilis();
            case 2: return urlItem.getAlias();
            case 3: return urlItem.isShouldShow();
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
            case 0: return String.class;
            case 1: return Long.class;
            case 2: return String.class;
            case 3: return Boolean.class;
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return columnIndex == 0 || columnIndex == 2 || columnIndex == 3;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        UrlItem urlItem = urls.get(rowIndex);
        if (columnIndex == 0){
            urlItem.setUrl((String) aValue);
        }
        if (columnIndex == 3){
            urlItem.setShouldShow((boolean) aValue);
        }
    }

    public void setItems(List<UrlItem> urls) {
        this.urls = urls;
    }
}
