package com.hyf.cemap.test;

import java.io.IOException;

import org.junit.Test;

public class MysqlDump {

    @Test
    public void testdump(){
        String mysqlDumpPath = "/Applications/XAMPP/xamppfiles/bin/mysqldump";
        String username = "root";
        String password = "root";
        String dataSource = "cemap";
        String exportPath = "/Users/huyifan/project/cemap/mydb.sql";
        String cmdString= mysqlDumpPath+" -u" + username + " -p" + password + " " + dataSource + 
                " > "+exportPath;
        String[] cmd = new String[]{"/bin/sh","-c",cmdString};
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("异常"+e.getMessage());
        }
        
    }
    
    
}
