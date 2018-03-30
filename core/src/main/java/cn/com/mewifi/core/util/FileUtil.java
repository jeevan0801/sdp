package cn.com.mewifi.core.util;

import java.io.File;
import java.io.FileOutputStream;

/**
 * description:
 * author: Administrator
 * date: 2017-09-26 17:22
 */
public class FileUtil {
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
