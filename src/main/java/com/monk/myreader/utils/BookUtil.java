package com.monk.myreader.utils;

import com.monk.myreader.bean.Book;
import com.monk.myreader.bean.Cache;
import com.monk.myreader.bean.Catalogue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class BookUtil {
    private static final String cachedPath = "D:/ProjectTest/myReader/";
    //存储的字符数
    public static final int cachedSize = 10000;
//    protected final ArrayList<WeakReference<char[]>> myArray = new ArrayList<>();


    private List<Cache> myArray = new ArrayList<>();
    //目录
    private List<Catalogue> directoryList = new ArrayList<>();

    private String bookPath;
    private long bookLen;
    private String charset;
    //第二页在全书中的位置?
    private Book shelfBook;


    public void openBook(Book shelfBook) throws IOException {
        this.shelfBook = shelfBook;
        bookPath = cachedPath+shelfBook.getPath();
        cacheBook();
    }

    //缓存书本
    private void cacheBook() throws IOException {
        // 更新charset
        charset = FileUtils.getCharset(bookPath);
        if (charset == null) {
            charset = "utf-8";
        }
        shelfBook.setCharSet(charset);

        File file = new File(bookPath);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file), charset);
        int index = 0;
        bookLen = 0;
        directoryList.clear();
        myArray.clear();
        while (true) {
            char[] buf = new char[cachedSize];
            int result = reader.read(buf);
            if (result == -1) {
                reader.close();
                break;
            }

            String bufStr = new String(buf);
            bufStr = bufStr.replaceAll("\r\n+\\s*", "\r\n\u3000\u3000");
            bufStr = bufStr.replaceAll("\u0000", "");

            bookLen += bufStr.length();

            Cache cache = new Cache();
            cache.setDataIndex(index);
            cache.setDataSize(bufStr.length());
            cache.setBookData(bufStr);
            System.out.println(bufStr);
//            cache.setBookId();

            myArray.add(cache);

            index++;
        }

    }

    //获取章节
    public synchronized void getChapter() {
        try {
            long size = 0;
            for (int i = 0; i < myArray.size(); i++) {
                char[] buf = block(i);
                String bufStr = new String(buf);
                String[] paragraphs = bufStr.split("\r\n");
                for (String str : paragraphs) {
                    if (str.length() <= 30 && (str.matches("[\\s\\pZ]*第.{1,8}章.*") || str.matches("[\\s\\pZ]*第.{1,8}节.*"))) {
                        Catalogue bookCatalogue = new Catalogue();
                        bookCatalogue.setStart(size);
                        bookCatalogue.setName(str);
                        bookCatalogue.setBookId(shelfBook.getId());
                        directoryList.add(bookCatalogue);
                    }
                    if (str.contains("\u3000\u3000")) {
                        size += str.length() + 2;
                    } else if (str.contains("\u3000")) {
                        size += str.length() + 1;
                    } else {
                        size += str.length();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public char[] block(int index) {
        if (myArray.size() == 0) {
            return new char[1];
        }
        char[] block = myArray.get(index).getBookData().toCharArray();
        /*if (block == null) {
            try {
                File file = new File(fileName(index));
                int size = (int) file.length();
                if (size < 0) {
                    throw new RuntimeException("Error during reading " + fileName(index));
                }
                block = new char[size / 2];
                InputStreamReader reader =
                        new InputStreamReader(
                                new FileInputStream(file),
                                "UTF-16LE"
                        );
                if (reader.read(block) != block.length) {
                    throw new RuntimeException("Error during reading " + fileName(index));
                }
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException("Error during reading " + fileName(index));
            }
            Cache cache = myArray.get(index);
            cache.setData(new WeakReference<char[]>(block));
        }*/
        return block;
    }
    public List<Catalogue> getDirectoryList() {
        return directoryList;
    }

    public long getBookLen() {
        return bookLen;
    }

    public String getCharset() {
        return charset;
    }


    public List<Cache> getMyArray() {
        return myArray;
    }

}
