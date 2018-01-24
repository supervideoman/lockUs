/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;
/**
 *
 * @author Win8s
 */
public class DataBase 
{
    MyTable table;
    private MySqli a;
    public DataBase(MyTable table)
    {
        this.table = table;
        connection();
    }
    public void connection() 
    {
        a = new MySqli("10.0.1.151:3306/garbage","java","$Pa5");
    }
    public void load(String domen)
    {
        String sql;
        sql = "SELECT id, name, ip FROM computers WHERE gp = 3 AND domen = %%s";
        table.init(new Object[] {"id", "name", "ip"}, a.query(sql, new String[] {domen}));
        table.table.getColumn("ip").setPreferredWidth(0);
        table.table.getColumn("ip").setMinWidth(0);
        table.table.getColumn("ip").setMaxWidth(0);
    }
    public void upLoadEvent(String name_compSql, String eventSql, String resultSql,String infoErrorSql)
    {
        String sql;
        sql = "INSERT INTO log_lockus (name_comp, event, result,infoError) VALUES (%%s, %%s, %%s, %%s)";
        a.query(sql, new String[] {name_compSql,eventSql, resultSql,infoErrorSql});  
    }
}
