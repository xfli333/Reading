package info.ishared.reading.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import info.ishared.reading.bean.Book;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 13-1-9
 * Time: PM4:56
 */
public class JsonUtils {
    private static Gson gson=new GsonBuilder().create();

    public static Book createBookFromJson(String json){
        return gson.fromJson(json,Book.class);
    }

    public static List<Book> createBooksFromJson(String json){
        return gson.fromJson(json,new TypeToken<List<Book>>(){}.getType());
    }
}
