package com.scalalearn.java.main.FastJson.quickStart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hjw on 17/3/19.
 */
/*
源码地址：https://github.com/alibaba/fastjson
via Maven:replace VERSION_CODE with real version name such as 1.2.21
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>VERSION_CODE</version>
</dependency>

public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray
*public static final JSONObject parseObject(String text)； // 把JSON文本parse成JSONObject
*public static final  T parseObject(String text, Class clazz); // 把JSON文本parse为JavaBean(按照一个类Decode)
*public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray
*public static final  List parseArray(String text, Class clazz); //把JSON文本parse成JavaBean集合

*public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本
public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本
public static final Object toJSON(Object javaObject); 将JavaBean转换为JSONObject或者JSONArray。
以上加*的是常用方法。
*/

/*
单用户:
id
name
 */
 class User{
    private Long id;
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User() {

    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Group{
    private Long id;
    private String name;
    private List<User> users = new ArrayList<User>();

    //id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    //name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //user
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    public void addUser(User user){
        users.add(user);
    }
}


public class FastJsonDemo{

    public static void main(String[] args) {
        //============== 对象序序列化为JSON与Decode
        Group group = new Group();
        group.setId(0L);
        group.setName("admin");

        User rootUser = new User();
        rootUser.setId(2L);
        rootUser.setName("root");

        User guestUser = new User();
        guestUser.setId(3L);
        guestUser.setName("guest");

        group.addUser(rootUser);
        group.addUser(guestUser);

        //code
        String jsonString = JSON.toJSONString(group);
        System.out.println(jsonString);
        //{"id":0,"name":"admin","users":[{"id":2,"name":"root"},{"id":3,"name":"guest"}]}

        //Decode
        String jsonStringGroup = jsonString;
        Group paresGroup = JSON.parseObject(jsonString, Group.class);

        //============== JSONArray
        List<User> userList = new ArrayList<User>();
        userList.add(new User(0L,"李四"));
        userList.add(new User(1L,"张三"));

        System.out.println("=======public static final String toJSONString(Object object);");
        System.out.println(JSON.toJSONString(userList));
        //[{"id":0,"name":"李四"},{"id":1,"name":"张三"}]

        System.out.println("======public static final Object toJSON(Object javaObject); 将JavaBean转换为JSONObject或者JSONArray。");
        JSONArray jsonArray = (JSONArray) JSON.toJSON(userList);
        System.out.println(jsonArray);
        //[{"id":0,"name":"李四"},{"id":1,"name":"张三"}]
        Iterator<Object> iter = jsonArray.iterator();
        while(iter.hasNext()){
            JSONObject ob = (JSONObject) iter.next();
            System.out.println("id = " + ob.getInteger("id") + "  name = " + ob.getString("name"));
        }
        // id = 0  name = 李四
        // id = 1  name = 张三


        //==========parseArray
        System.out.println("======*public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray");
        String str = "[{\"id\":0,\"name\":\"王五\"},{\"id\":1,\"name\":\"赵四\"}]";
        JSONArray jsArr = JSON.parseArray(str);
        Iterator<Object> iter2 = jsArr.iterator();
        while(iter2.hasNext()){
            JSONObject ob = (JSONObject) iter2.next();
            System.out.println("id = " + ob.getInteger("id") + "  name = " + ob.getString("name"));
        }
        //id = 0  name = 王五
        //id = 1  name = 赵四

        System.out.println("======public static final  List parseArray(String text, Class clazz); //把JSON文本parse成JavaBean集合");
        List<User> userLIst2 = JSON.parseArray(str,User.class);
        for(User usr : userLIst2){
            System.out.print("id = " + usr.getId());
            System.out.println(" name = " + usr.getName());
        }

        //=====pareObject
        System.out.println("=======public static final JSONObject parseObject(String text)； // 把JSON文本parse成JSONObject");
        String str3 = "{\"id\":0,\"name\":\"TOM\"}";
        JSONObject job = JSON.parseObject(str3);
        System.out.println("id = " + job.getInteger("id") + " name = " + job.getString("name"));
        //id = 0 name = TOM


        //根据(key, value)动态构建JSONObject
        //{"total":12,"List":[{"id":0,"name":"李四"},{"id":1,"name":"张三"}]}
        System.out.println("=======根据(key, value)动态构建JSONObject");
        List<User>  ls = new ArrayList<User>(Arrays.asList(new User(0L,"李四"), new User(1L,"张三")));
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("total",12);

        JSONArray jsonArr = new JSONArray();
        for (User us : ls){
            JSONObject jobj = new JSONObject();
            jobj.put("id",us.getId());
            jobj.put("name",us.getName());
            jsonArr.add(jobj);
        }
        jsonObj.put("List",jsonArr);
        System.out.println(jsonObj);
        //{"List":[{"id":0,"name":"李四"},{"id":1,"name":"张三"}],"total":12}
    }
}
