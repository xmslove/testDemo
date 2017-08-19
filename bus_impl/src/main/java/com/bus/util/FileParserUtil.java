package com.bus.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileParserUtil {
	
	public static void writeToFile(InputStream ins, String path) {  
        try {  
            OutputStream out = new FileOutputStream(new File(path));  
            int read = 0;  
            byte[] bytes = new byte[1024];  
  
            while ((read = ins.read(bytes)) != -1) {  
                out.write(bytes, 0, read);  
            }  
            out.flush();  
            out.close();  
        } catch (FileNotFoundException e){  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
	public static String writeToString(InputStream ins) throws Exception {  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        byte[] b = new byte[1024];  
        int i = -1;  
        while ((i = ins.read(b)) != -1) {  
            out.write(b, 0, i);  
        }  
        ins.close();  
        return new String(out.toByteArray(), "UTF-8");  
    }
	
}
