package info.ishared.reading.util;

import info.ishared.reading.AppConfig;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 12-12-24
 * Time: PM5:14
 */
public class BookUtils {

    public static String getNextFileName(String fileName) {
        return getNextFileName(fileName, false);
    }

    public static String getNextFileName(String fileName, boolean nextChapter) {
        Integer chapterNumber = Integer.valueOf(fileName.split("/")[0]);
        if (nextChapter) {
            return (chapterNumber + 1) + "/0.txt";
        } else {
            Integer fileNameNumber = Integer.valueOf(fileName.split("/")[1].split(".txt")[0]);
            return chapterNumber + "/" + (fileNameNumber + 1) + ".txt";
        }
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
}
