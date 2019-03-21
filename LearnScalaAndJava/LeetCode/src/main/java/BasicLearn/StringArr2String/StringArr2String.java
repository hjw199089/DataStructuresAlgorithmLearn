package BasicLearn.StringArr2String;

import java.util.HashSet;
import java.util.Set;

public class StringArr2String {
//  如果是 “字符串数组” 转 “字符串”，只能通过循环，没有其它方法
//  String[] str = {"abc", "bcd", "def"};
//  StringBuffer sb = new StringBuffer();
//for(int i = 0; i < str.length; i++){
//    sb. append(str[i]);
//  }
//  String s = sb.toString();
//
//  如果是 “字符数组” 转 “字符串” 可以通过下边的方法
//  char[]   data={'a','b','c'};
//  String  s=new   String(data);
public static void main(String[] args) {
  String[] str = {"abc", "bcd", "def"};
  Set<String> tableNameSet = new HashSet<String>();
  tableNameSet.add("ddddd");
  tableNameSet.add("dddddddddd");
  Object[] arr =  tableNameSet.toArray();

  String s = null;
  if (null != arr && arr.length >= 1 ) {
    StringBuffer sb = new StringBuffer();
    sb.append("[");
    for(int i = 0; i < arr.length; i++){
      sb. append(arr[i].toString() + ",");
    }
    sb.delete(sb.length()-1,sb.length());
    sb.append("]");
    s = sb.toString();
  }
  System.out.println(s);
}
}
