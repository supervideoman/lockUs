/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author luser
 */

public class MySqli
{
    public int a;
    int b;
    private Connection connSQL;
    public int countE;
    public String connectionE, queryE, Error;
    public MySqli(String hostPortDB, String user, String password)
    {
        countE = 0;
        Error = "";
        try
        {
            connSQL = DriverManager.getConnection("jdbc:mysql://" + hostPortDB, user,  password);
            if(connSQL == null)
            {
                System.out.println("SQL problem");
                System.out.println("Ошибка соединения с БД: ");
                System.exit(0);
            }
            //else
            //    System.out.println("SQL Ok....");
        }
        catch(Exception e)
        { 
            countE++;
            connectionE = "Ошибка конструктора MySqli: "+e;
            System.out.println(connectionE);
        }
    }
    public String[][] query(String sql, String[] values)
    {           
        String [][] result = null;  
        if (sql.length() < 6)
        {
            countE ++;
            Error = "Запрос слишком короткий";
            queryE = Error;
            System.out.println(Error);
            return null;
        }
        ResultSet rs = null;
        //----------------------------------     Разбор строки   ------------------------------------
        char[] sqlChar = sql.toCharArray();
        int sqlLen = sqlChar.length;
        int i = 0;
        int oldL = 0;
        String typesValue = "";
        String sqli = "";
        while ((i < sqlLen))  // sqlLen -2 + 1 -1 
        {
            if((i < sqlLen - 2) && (sqlChar[i]=='%') && (sqlChar[i+1]=='%'))
                switch(sqlChar[i+2])
                {
                    case 'i':
                        typesValue += "i";
                        break;
                        
                    case 'd':
                        typesValue += "d";
                        break;  
                        
                    case 's':
                        typesValue += "s";
                        break; 
                }
            if(oldL != typesValue.length())                                     //            if(oldL != parProc.length()) {sqli += '?'; i += 3;}
            {                                                                   //            else sqli += sqlChar[i++];
                sqli = sqli + "?";
                i += 3;
            }
            else
            {
                sqli = sqli + sqlChar[i];
                i++;
            }       
            oldL = typesValue.length();
        }  
//        System.out.println("new Sqli: \"" + sqli +"\" \nparProc = " + typesValue + " size = " + typesValue.length() + " all! \n"); 
        if (typesValue.length() != values.length)
        {   
            countE++;
            Error = "Ошибка количества передаваемых параметров";
            queryE = Error;
            System.out.println(Error);
            return null;            
        }
        //----------------------     Формирование параметров запроса   ------------------------------
        PreparedStatement stmt = null;        
        try 
        {            
            stmt = connSQL.prepareStatement(sqli);    
        }
        catch(Exception e)
        {
            System.out.println("Ошибка формирования SQL строки"+e);
            return null;
        }
        if (stmt != null)
        {
            try
            {
                for(i = 0; i < values.length; i++)
                {
                    switch (typesValue.charAt(i)) 
                    {
                        case 'i':
                            stmt.setInt(i+1,Integer.valueOf(values[i]));
                            break;
                        
                        case 'd':
                            stmt.setDouble(i+1,Double.valueOf(values[i]));
                            //stmt.setInt(1,  Integer.valueOf(values[0]));
                            break;                            
                            
                        case 's':
                            stmt.setString(i+1,values[i]);
                            break;
                    }                 
                }
            }
            catch(Exception e)
            {
                System.out.println("Ошибка подстановки переменных: "+e);
            }
            //------------------------------     Отправка запроса   --------------------------------
            try 
            {   
                if(sqli.substring(0, 6).equals("SELECT"))
                    rs = stmt.executeQuery();                    
                else
                      stmt.executeUpdate();
            } 
            catch (Exception e) 
            {
                System.out.println("Ошибка отправки запроса: "+e);
            }
        }
        //----------------------------------     Вывод ответа    ----------------------------------
        try 
        {   
            int rsSize = 0;
            Integer row = 0, column = 0;
            // Узнаем размерность таблицы
            if (rs != null) 
            {
                rs.last();
                row = rs.getRow();
                column = rs.getMetaData().getColumnCount();
                result = new String[row][column];
                rs.beforeFirst();                
            }
            
            i = 0; 
            int j;
            while ((rs != null) && rs.next()) 
            {                 
                j = 1;
                while (j <= column) 
                {                    
                    result[i][j-1]  = rs.getString(j);
                    j++;
                }
                i++; 
            }
        } 
        catch (Exception e) 
        {
        //    System.out.println(rs+" " + result);
            System.out.println("Ошибка формирования ответа: "+e);            
        }        
        return result;
//        if (result != null) return result;
//        else
//            return new String [][] {{"1","2"},{"3"}};
    }
    public void close()
    {
        try 
        {
            connSQL.close();
        } 
        catch (Exception e) 
        {
            System.out.println("[temp]: Ошибка закрытия SQL соединения: " +e);
        }
    }
}