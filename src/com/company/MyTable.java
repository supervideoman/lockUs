package com.company;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MyTable 
{
    DefaultTableModel modelTable;
    Vec2d oldPosition = new Vec2d(-1, -1);
    JTable table;
    Object[][] Data;
    Object[] header;
    public MyTable(JTable table)
    {
        this.table = table;
    }
    public void init(Object[] header, Object[][] Data)
    {
        this.Data = Data;
        this.header = header.clone();
        modelTable = new DefaultTableModel(this.Data, this.header)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        table.setModel(modelTable);
        table.setRowHeight(30);
    }
    public void update(Object[][] Data)
    {
        modelTable = new DefaultTableModel(Data, header)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        table.setModel(modelTable);
    }
    public void add(Object[] values)
    {
        int num = modelTable.getRowCount();
        modelTable.insertRow(num, values);
    }
}
