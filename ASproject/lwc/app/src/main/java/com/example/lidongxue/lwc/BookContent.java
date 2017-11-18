package com.example.lidongxue.lwc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lidongxue on 17-9-20.
 */

public class BookContent {
    public static class Book{
        public  Integer id;
        public String title;
        public String desc;
        public Book(Integer id ,String title ,String desc){
            this.id=id;
            this.title=title;
            this.desc=desc;
        }

        @Override
        public String toString() {
            return title;
        }

    }
    public static List<Book> ITEMS =new ArrayList<Book>();
    public static Map<Integer,Book> ITEM_MAP=new HashMap<Integer, Book>();
    static {
        addItem(new Book(1,"疯狂讲义1","ahhhhhh"));
        addItem(new Book(2,"疯狂讲义2","bhhhhhh"));
        addItem(new Book(3,"疯狂讲义3","chhhhhh"));
        addItem(new Book(4,"疯狂讲义4","dhhhhhh"));
    }
    private static void addItem(Book book){
        ITEMS.add(book);
        ITEM_MAP.put(book.id,book);
    }
}
