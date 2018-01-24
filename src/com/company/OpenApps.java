package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class OpenApps {
    
    public String lineOut = "";

    public String Run(String[] appname)
    {   
        //System.out.println(Arrays.toString(appname));
        //  ### Запуск приложения ###
                   
        ProcessBuilder procBuilder = new ProcessBuilder(appname);
        try 
        {   
            Process proc = procBuilder.start();      
            //System.out.println("Открываю: " + Arrays.toString(appname));
            proc.waitFor();//  
            //System.out.println("Закрыл");
            String line;
            BufferedReader inStr = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            lineOut = "";
            while ((( line = inStr.readLine()) != null) )  
            {
//for Misha                System.out.println(line); 
                lineOut += line;
            } 
            return lineOut;
        }
        catch(Exception e)
        {
            System.out.println("Не смог открыть программу "+ Arrays.toString(appname) +": "+e);
        }
        return lineOut;
    }
}
    
