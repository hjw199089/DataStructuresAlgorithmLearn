package BasicLearn.FastJson.ch1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by hjw on 17/6/8.
 */

//=========
class Book{
    public String title;
    public String author;
    public Map<String,String> chapter;

    public Map<String, String> getChapter() {
        return chapter;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setChapter(Map<String, String> chapter) {
        this.chapter = chapter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", chapter=" + chapter +
                '}';
    }
}
public class Test {

    public  void arrayListJson2List() {
        InputStream confStream = this.getClass().getClassLoader().getResourceAsStream("arrayList");

        String conf = null;
        try {
            conf = org.apache.commons.io.IOUtils.toString(confStream, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(conf);
        JSONObject jsonStr = JSON.parseObject(conf);
        List<String> blackList = JSON.parseArray(jsonStr.getString("uuidTable"), String.class);
        System.out.println(blackList);
        //        {
        //            "uuidTable":[
        //            "1",
        //            "2"
        //            ]
        //        }
        //        [1, 2]

    }

    public  void mapArrayJson2Map() {
        InputStream confStream = this.getClass().getClassLoader().getResourceAsStream("mapArray");

        String conf = null;
        Map<String,String> table = null;
        Set<String> keySet = null;
        try {
            conf = org.apache.commons.io.IOUtils.toString(confStream, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(conf);
        JSONObject jsonStr = JSON.parseObject(conf);
        System.out.println(jsonStr.getString("uuidTable"));
        table = (Map<String, String>) JSON.parseObject(jsonStr.getString("uuidTable"), Map.class);
        System.out.println(table);
        keySet = table.keySet();
        System.out.println(keySet);
        //        {
        //            "uuidTable":{
        //            "1":"hjw","2":"hjw2"
        //        }
        //        }
        //        {"1":"hjw","2":"hjw2"}
        //        {1=hjw, 2=hjw2}
    }

    public  void testTpAppConf() {
        InputStream confStream = this.getClass().getClassLoader().getResourceAsStream("book");
        //读取resource文件夹下的文件 book
        //        {
        //            "title": "Think",
        //                "author":"jk",
        //                "chapter": {
        //                    "chapter1": "intruduct",
        //                    "chapter2": "ch1"
        //        }
        //        }
        String conf = null;
        try {
             conf = org.apache.commons.io.IOUtils.toString(confStream, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(conf);
        Book book = JSON.parseObject(conf, Book.class);

        System.out.println(book);
        System.out.println("Author: " + book.getAuthor());
    }




    public static void main(String[] args)  {
        Test test = new Test();
        test.testTpAppConf();

    //        String book = " { \"tl\": \"Think\",\n" +  //=======多可以
    //               "\"ttle\": \"Think\",\n" +      //======命名 和类中的字段 不一致也可以但是要匹配的足够的多
    //                "  \"author\":\"jk\",\n" +
    //                "  \"chapter\": {\n" +
    //                "    \"chapter1\": \"intruduct\",\n" +
    //                "    \"chapter2\": \"ch1\"\n" +
    //                "  }}";
    //        Book book_parse = JSON.parseObject(book,Book.class);
    //        System.out.println(book_parse);

       //test.mapArrayJson2Map();



    }
}
