package info.ishared.reading.util;


import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;


/**
 * Java utils 实现的Zip工具
 */
public class ZipUtils {


    public static void unzip(String zipFileName, String outputDir) throws IOException {

        File zipFile = new File(zipFileName);
        if (zipFile.exists()) {
            outputDir = outputDir + File.separator;
            FileUtils.forceMkdir(new File(outputDir));

            ZipFile zf = new ZipFile(zipFile, "GBK");
            Enumeration zipArchiveEntrys = zf.getEntries();
            while (zipArchiveEntrys.hasMoreElements()) {
                ZipArchiveEntry zipArchiveEntry = (ZipArchiveEntry) zipArchiveEntrys.nextElement();
                if (zipArchiveEntry.isDirectory()) {
                    FileUtils.forceMkdir(new File(outputDir + zipArchiveEntry.getName() + File.separator));
                } else {
                    IOUtils.copy(zf.getInputStream(zipArchiveEntry), FileUtils.openOutputStream(new File(outputDir + zipArchiveEntry.getName())));
                }
            }
        } else {
            throw new IOException("指定的解压文件不存在：\t" + zipFileName);
        }
    }

}