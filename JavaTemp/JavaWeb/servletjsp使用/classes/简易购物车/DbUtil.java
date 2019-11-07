package cn.jhmarryme.entity;

import java.util.HashMap;
import java.util.Map;

public class DbUtil {
    private static Map<String, Book> books = new HashMap<String, Book>();
    static{
        books.put("1", new Book("1", "java", 44.44, "j"));
        books.put("2", new Book("2", "python", 34.44, "h"));
        books.put("3", new Book("3", "c", 24.44, "w"));
        books.put("4", new Book("4", "c++", 14.44, "z"));
    }

    public static Map<String, Book> findAllBooks(){
        return books;
    }

    public static Book findBookById(String id){
        return books.get(id);
    }

}
