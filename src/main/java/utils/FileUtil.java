package utils;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

public class FileUtil {

    public static String getBaseFilePathFromClassLoader(){
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL url = classLoader.getResource(".");
        try{
            return URLDecoder.decode(url.getFile().replaceFirst("/", "").replace("/","\\"), "UTF-8");
        }catch (UnsupportedEncodingException e){
            throw new RuntimeException(e);
        }
    }

    public static String getCucumberReportsDirectory(){
        String baseDir = getBaseFilePathFromClassLoader();
        return baseDir.substring(0,baseDir.lastIndexOf("\\target")).concat("\\reports\\Cucumber");
    }

}
