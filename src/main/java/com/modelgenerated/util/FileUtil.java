/*
 * StringUtil.java
 *
 * Created on October 19, 2002, 6:17 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.util;


import com.modelgenerated.foundation.logging.Logger;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;

/**
 *
 * @author  kevind
 */
public class FileUtil {
    
    /** Creates a new instance of StringUtil */
    private FileUtil() {
    }

    public static void copyFile(String dest, String source) {
        copyFile(dest, source, 4096);
    }
        
    public static void copyFile(String dest, String source, int bufferSize) {
        try {
            BufferedReader sourceReader = new BufferedReader(new FileReader(source));
            BufferedWriter destWriter = new BufferedWriter(new FileWriter(dest));

            char[] buffer = new char[bufferSize];
            
            int charsRead = sourceReader.read(buffer);
            while (charsRead != -1) {
                destWriter.write(buffer, 0, charsRead);
                charsRead = sourceReader.read(buffer);
            }                
            
            destWriter.flush();
            destWriter.close();
            sourceReader.close();
            
        } catch (IOException e) {
            //e.printStackTrace();
            throw new RuntimeException("error copying file : " + source + " to " + dest, e);
        }
        
    }
    
    public static String getTempDir() {
        // this should work on windows by creating a directory on the default drive.
        String tempDirName = "temp";
        File tempDir = new File(tempDirName);
        tempDir.mkdir();

        return tempDirName;        
    }
    

    public static void writeFile(String fileName, String content) {
        try {        
            FileWriter fw = new FileWriter(fileName);
            fw.write(content);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            //e.printStackTrace();
            throw new RuntimeException("error writing file: " + fileName, e);
        }
    }

    public static void writeFile(String fileName, byte [] data) {
        try {        
            FileOutputStream fos = new FileOutputStream(fileName);
            fos.write(data);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            //e.printStackTrace();
            throw new RuntimeException("error writing file: " + fileName, e);
        }
    }

    /*
     * @deprecated - user apache jakart commons
     */
    public static String readStringFromFile(String filePath) {
        FileInputStream is = null;
        
        try {        
            File file = new File(filePath);
            is = new FileInputStream(file);
            
            ByteArrayOutputStream baos = readInStreamToOutputStream(is);
            
            if (baos != null) {
                return baos.toString();
            }                
                
        } catch (java.io.IOException e) { 
            throw new RuntimeException("Error reading file: " + filePath, e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (java.io.IOException e2) {
                // just log and continue
                Logger.error(FileUtil.class.getName(), "error closing inputstream");
            }
        }
        return null;        
    }

    public static ByteArrayOutputStream readInStreamToOutputStream(InputStream is) {
        try {        
            ByteArrayOutputStream data = new ByteArrayOutputStream();
            byte[] buffer = new byte[8192];
            
            int bytesRead = is.read(buffer);
            while (bytesRead != -1) {
                data.write(buffer, 0, bytesRead);
                bytesRead = is.read(buffer);
            }
            return data;

        } catch (java.io.IOException e) { 
            throw new RuntimeException("Error reading input stream", e);
        }
        
    }
    
    
    /* Does this need to take a "size" parameter?
     * todo: This should IOException. Give the user ability to handle exception.
     */
    
    public static byte[] readDataFromFile(String filePath, int size) {
        FileInputStream is = null;
        
        try {        
            File file = new File(filePath);
            is = new FileInputStream(file);
            //FileDescriptor fd = is.
            
            byte[] data = new byte[size];
            is.read(data, 0, size);
            return data;

        } catch (java.io.IOException e) { 
            throw new RuntimeException("Error reading file: " + filePath, e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (java.io.IOException e2) {
                // just log and continue
                Logger.error(FileUtil.class.getName(), "error closing inputstream for filePath: " + filePath);
            }
        }
        
    }
    
    public static byte[] readDataFromFile(String filePath) throws java.io.IOException {
        FileInputStream is = null;
        
        try {        
            File file = new File(filePath);
            is = new FileInputStream(file);
            //FileDescriptor fd = is.
            
            ByteArrayOutputStream baos = readInStreamToOutputStream(is);
            
            byte[] data = baos.toByteArray();
            return data;

        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (java.io.IOException e2) {
                // just log and continue
                Logger.error(FileUtil.class.getName(), "error closing inputstream for filePath: " + filePath);
            }
        }
        
    }
    
    
    public static boolean createDirectory(String directorPath) {
        File directory = new File(directorPath);
        return directory.mkdirs();        
    }
    
    public static String createValidFileName(String input) {
    	int len = input.length();
    	StringBuilder returnValue = new StringBuilder(len);
    	for (int i = 0; i < len; i++) {
    	    char ch = input.charAt(i);
    	    if (!(ch < ' ' 
    	    	|| ch >= 0x7F 
    	    	|| ch == '*' 
    	    	|| ch == '/' 
    	    	|| ch == '\\' 
    	    	|| ch == '?' 
    	    	|| ch == '|'
    	    	|| ch == ':'
    	    	|| ch == '"'
    	    	|| ch == '`'
    	    	|| ch == '\''
    	    	|| ch == '<' 
    	    	|| ch == '>'
    	    	|| ch == ','
    	        )) {
    	        returnValue.append(ch);
    	    }
    	}    	
    	return returnValue.toString();    	
    }
    
    
    

    
}
