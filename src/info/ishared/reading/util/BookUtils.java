package info.ishared.reading.util;

import info.ishared.reading.AppConfig;
import info.ishared.reading.cache.SimplyCache;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 12-12-24
 * Time: PM5:14
 */
public class BookUtils {

    public static final String NONE = "NONE";

    public static String getNextFileName(String bookNumber,String fileName) {
        Integer chapterNumber = Integer.valueOf(fileName.split("/")[0]);
        Integer fileNameNumber = Integer.valueOf(fileName.split("/")[1].split(".txt")[0]);
        int nextNumber = fileNameNumber + 1;
        int chapterCount = 0;
        if (!SimplyCache.chapterSizeCache.isEmpty()) {
            chapterCount = SimplyCache.chapterSizeCache.get(getChapterByFileName(fileName));
        }
        if (chapterCount == 0) {
            chapterCount = getPageSize(AppConfig.BOOK_DIRECTORY + "/" + bookNumber + "/"+getChapterByFileName(fileName));
        }
        if (nextNumber >= chapterCount) {
            return NONE;
        }
        return chapterNumber + "/" + (fileNameNumber + 1) + ".txt";

    }

    public static String getLastFileName(String bookNumber,String fileName) {
        Integer chapterNumber = Integer.valueOf(fileName.split("/")[0]);
        Integer fileNameNumber = Integer.valueOf(fileName.split("/")[1].split(".txt")[0]);
        int lastNumber = fileNameNumber - 1;
        if (lastNumber < 0) {
            return NONE;
        }
        return chapterNumber + "/" + (fileNameNumber - 1) + ".txt";
    }

    public static boolean nextChapterExists(String bookNumber, String fileName) {
        Integer chapterNumber = Integer.valueOf(fileName.split("/")[0]) + 1;
        File file = new File(AppConfig.BOOK_DIRECTORY + "/" + bookNumber + "/" + chapterNumber);
        return file.exists();
    }

    public static int getPageSize(String chapterPath) {
        int size = 0;
        File dir = new File(chapterPath);
        for (File file : dir.listFiles()) {
            if (file.getName().endsWith(".txt")) {
                size++;
            }
        }
        return size - 1;
    }

    public static int getCurrentPageByFileName(String fileName) {
        return Integer.valueOf(fileName.substring(fileName.indexOf("/") + 1, fileName.indexOf(".txt")));
    }

    public static String getChapterByFileName(String fileName) {
        return fileName.substring(0, fileName.indexOf("/"));
    }


    public static String getFileContent(String bookNumber,String fileName){
        try {
            return FileUtils.readFileToString(new File(AppConfig.BOOK_DIRECTORY + "/" + bookNumber + "/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
