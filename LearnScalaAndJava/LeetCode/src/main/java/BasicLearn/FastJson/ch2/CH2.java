package BasicLearn.FastJson.ch2;

import com.alibaba.fastjson.JSONObject;

public class CH2 {
  public static void main(String[] args) {
    JSONObject extend_info = new JSONObject();

    extend_info.put("id",1);
    extend_info.put("name","hjw");
    String extendinfo = extend_info.toJSONString();
    System.out.println(extendinfo);

  }
}
