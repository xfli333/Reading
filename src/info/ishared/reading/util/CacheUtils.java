package info.ishared.reading.util;

import info.ishared.reading.AppConfig;
import info.ishared.reading.cache.SimplyCache;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 13-1-4
 * Time: PM1:35
 */
public class CacheUtils {

    public static void initCache(String bookNumber, String fileName) {
        if (SimplyCache.menuCache.isEmpty()) {
            String chapterNumber = BookUtils.getChapterByFileName(fileName);
            try {
                List<String> lines = FileUtils.readLines(new File(AppConfig.BOOK_DIRECTORY + "/" + bookNumber + "/" + chapterNumber + "/menu.txt"));
                for (String line : lines) {
                    SimplyCache.menuCache.put(line.split("=")[0], line.split("=")[1]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (SimplyCache.chapterSizeCache.isEmpty()) {
            String chapterNumber = BookUtils.getChapterByFileName(fileName);
            Integer chapterSize = BookUtils.getPageSize(AppConfig.BOOK_DIRECTORY + "/" + bookNumber + "/"+chapterNumber);
            SimplyCache.chapterSizeCache.put(chapterNumber, chapterSize);
        }
    }

    public static void clearCache(){
        SimplyCache.chapterSizeCache.clear();
        SimplyCache.menuCache.clear();
    }
}
