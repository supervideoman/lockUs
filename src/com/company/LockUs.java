package com.company;

import java.util.Properties;
import java.lang.System;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LockUs 
{
    public static String userName;
    public static String computerName;
    public static String lunacyPath = "C:\\Lunacy\\bin";
    public static void main(String[] args) 
    {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) 
            {
                if ("Nimbus".equals(info.getName())) 
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(Visual.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) 
        {
            Logger.getLogger(Visual.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) 
        {
            Logger.getLogger(Visual.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) 
        {
            Logger.getLogger(Visual.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            computerName = InetAddress.getLocalHost().toString();
        } catch (UnknownHostException ex) {
            computerName = "!noNamenoIP!";
            Logger.getLogger(LockUs.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
        Properties properties = System.getProperties();
        userName = properties.getProperty("user.name");
        
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                Visual morda = new Visual();
                if(!userName.toUpperCase().equals("SECURITY2"))
                {
                    morda.setUserNameLabel("* запуск от "+userName);
                    morda.setVisibleUserNameLabel(true);
                }
                morda.setResizable(false);
                morda.setVisible(true);
                
                
            }
        });
    }
    public static void trace(String str)
    {
        System.out.println(str);
    }
}
