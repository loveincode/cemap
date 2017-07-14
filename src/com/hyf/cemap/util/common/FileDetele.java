package com.hyf.cemap.util.common;

import java.io.File;

import org.springframework.stereotype.Component;


@Component
public class FileDetele {
    
    public Boolean delele(String filePath){
        File file = new File(filePath);
        if(!file.exists()){
            return false;
        }
        else{
            if(file.delete()){
                return true;
            }
            else{
                return false;
            }
        }
    }
 
}
