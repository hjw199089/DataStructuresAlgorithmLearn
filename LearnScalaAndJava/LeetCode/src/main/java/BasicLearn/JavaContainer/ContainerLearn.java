package com.scalalearn.java.main.JavaContainer;

/**
 * Created by hjw on 17/5/19.
 */
import java.lang.String;
import java.util.*;

//http://www.cnblogs.com/zhenjing/archive/2013/04/25/java_Container.html
//http://blog.csdn.net/mad1989/article/details/26389541
public class ContainerLearn {

    public static void main(String[] args) throws Exception {

        System.out.println("\n============StringBuffer====StringBuilder============");
        {
            //StringBuffer常用方法（StringBuffer和StringBuilder在使用上几乎一样）
            //------初始化-----
            StringBuilder s = new StringBuilder();
            //分配了长度512字节的字符缓冲区。
            StringBuilder sb1 = new StringBuilder(512);
            //创建带有内容的StringBuilder对象，在字符缓冲区中存放字符串“how are you?”
            StringBuilder sb2 = new StringBuilder("how are you?");


            //-------append方法
            //该方法的作用是追加内容到当前StringBuilder对象的末尾，类似于字符串的连接，调用该方法以后，StringBuilder对象的内容也发生改 变，例如：
            StringBuilder sb3 = new StringBuilder("abc");
            //则对象sb3的值将变成”abcappend”
            sb3.append("append");
            //装成最后所用的字符串
            sb3.toString();

            // ------deleteCharAt方法
            //该方法的作用是删除指定位置的字符，然后将剩余的内容形成新的字符串。例如：
            StringBuilder sb = new StringBuilder("KMing");
            sb. deleteCharAt(1);
            //该代码的作用删除字符串对象sb中索引值为1的字符，也就是删除第二个字符，剩余的内容组成一个新的字符串。所以对象sb的值变 为”King”。
            System.out.println(sb);


            //-------delete方法：
            //public StringBuffer delete(int start,int end)
            //该方法的作用是删除指定区间以内的所有字符，包含start，不包含end索引值的区间。例如：
            StringBuilder sb4 = new StringBuilder("TestString");
            //该代码的作用是删除索引值1(包括)到索引值4(不包括)之间的所有字符，剩余的字符形成新的字符串。则对象sb的值是”TString”。
             System.out.println(sb4.delete (1,4));


            // ----insert方法
            //public StringBuffer insert(int offset, boolean b),
            // 该方法的作用是在StringBuilder对象中插入内容，然后形成新的字符串。例如：
            StringBuilder sb5 = new StringBuilder("TestString");
            // 该示例代码的作用是在对象sb的索引值4的位置插入false值，形成新的字符串，则执行以后对象sb的值是”TestfalseString”。
            System.out.println(sb5.insert(4,false));


            //---reverse方法

            //该方法的作用是将StringBuilder对象中的内容反转，然后形成新的字符串。例如：
            StringBuilder sb6 = new StringBuilder("abc");
            //经过反转以后，对象sb中的内容将变为”cba”。
            System.out.println(sb6.reverse());;


            //-----setCharAt方法
            //该方法的作用是修改对象中索引值为index位置的字符为新的字符ch,返回值为void。例如：
            StringBuilder sb7 = new StringBuilder("abc");
            //则对象sb的值将变成”aDc”。
            sb7.setCharAt(1,'D');
            System.out.println(sb7);


            // -----trimToSize方法
            //    public void trimToSize()
            //    该方法的作用是将StringBuffer对象的中存储空间缩小到和字符串长度一样的长度，减少空间的浪费，和String的trim()是一样的作用
            //   sb.capacity方法
            //   该方法的作用是获取字符串的容量。
            StringBuilder sb8 = new StringBuilder(8);
            sb8.append("abc");
            System.out.println("sb8.capacity:" + sb8.capacity() + "\tsb8.length:" + sb8.length());
            //sb8.capacity:8	sb8.length:3
            sb8.trimToSize();
            System.out.println("sb8.capacity:" + sb8.length());
            //sb8.capacity:3


           // -----setlength方法
           //    该方法的作用是设置字符串缓冲区大小。
           //    sb.ensureCapacity(32); //预先设置sb的容量为32,该方法的作用是重新设置字符串容量的大小。
            StringBuilder sb9=new StringBuilder("abcd");
            // 如果用小于当前字符串长度的值调用setlength()方法，则新长度后面的字符将丢失。
            sb9.setLength(3);
            System.out.println(sb9);
            //abc

            // ----getChars方法
            //    该方法的作用是将字符串的子字符串复制给数组。
            //    getChars(int start,int end,char chars[],int charStart);
             StringBuffer sb10 = new StringBuffer("I love You");
            int begin = 0;
            int end = 5;
            //    //注意ch字符数组的长度一定要大于等于begin到end之间字符的长度
            //    //小于的话会报ArrayIndexOutOfBoundsException
            //    //如果大于的话，大于的字符会以空格补齐
            char[] ch  = new char[end-begin];
            sb10.getChars(begin, end, ch, 0);
            System.out.println(ch);
            //I lov
        }


        // Interfaces:
        // Interface Iterator: hasNext(),  next(), remove() ---- 所有容器通用的遍历方式。
        // Interface Collection：add(E e)，remove(Object o), clear(), isEmpty(), size(), iterator(), toArray() ---- 所有单值容器(map除外)的公共接口。
        // Interface Map: put(K key, V value), get(Object key), remove(Object key), clear()，isEmpty()，size(), keySet(), entrySet(), values()  ---- 所有K-V容器的公共接口，常见class: HashMap, Hashtable, IdentityHashMap, LinkedHashMap, ConcurrentHashMap
        // Interface Map.Entry: getKey(), getValue(), setValue(V value) ---- 用于遍历Map容器。

        // ============Arrays============
        {
            System.out.println("\n============Arrays============");
            String[] array = new String[] { "a", "b", "c" };
            //-----转List
            List list = Arrays.asList(array);

            //List.size
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i) + ";");
            }

            //foreach
            System.out.println();
            for (Object val : array) {
                System.out.print( val + ";");
            }
        }


        // ============ArrayList============
        {
            System.out.println("============ArrayList============");
            ArrayList arraylist = new ArrayList();

            /*
            add()直接添加到最后
            add(index,value)在index位置添加元素,该位置及之后的原值依次后移,当index>目前的length抛出异常IndexOutOfBoundsException
            set(index,value)更改index位置处的元素
             */
            arraylist.add(0, "end");//指定索引加入值

            for (int i = 0; i < 2; i++) {
                arraylist.add(i, String.valueOf(i));
            }
            System.out.println(arraylist);
            //[0, 1, end]

            System.out.println("ArrayList's lastIndexOf(\"end\") is " + arraylist.lastIndexOf("end"));
            System.out.println("ArrayList's lastIndexOf(\"0\") is " + arraylist.lastIndexOf("0"));
            System.out.println("ArrayList's lastIndexOf(\"1\") is " + arraylist.lastIndexOf("1"));
            System.out.println();
            //  ArrayList's lastIndexOf("end") is 2
            //  ArrayList's lastIndexOf("0") is 0
            //  ArrayList's lastIndexOf("1") is 1

            arraylist.add("2");
            System.out.println(arraylist);
            //  [0, 1, end, 2]
            System.out.println("ArrayList's lastIndexOf(\"2\") is " + arraylist.lastIndexOf("2"));
            //  ArrayList's lastIndexOf("2") is 3
            //  arraylist.add(5,"3");
            //  java.lang.IndexOutOfBoundsException: Index: 5, Size: 4
            arraylist.add(3,"3");
            // [0, 1, end, 3, 2]
            System.out.println(arraylist);
            arraylist.set(3,"4");
            System.out.println(arraylist);
            // [0, 1, end, 4, 2]

            System.out.print("ArrayList遍历1:");
            for (Object val : arraylist) {
                System.out.print( val + ";");
            }
            System.out.println();
            System.out.print("ArrayList遍历2:");
            for (int i = 0; i < arraylist.size(); i++) {
                System.out.print(arraylist.get(i) + ";");
            }
        }
         // ============Vector============
        {
            System.out.println("\n============Vector============");
            Vector vector = new Vector();
            vector.add(0, "b");
            vector.add("a");
            vector.addElement("d");
            vector.add("c");
            System.out.println(vector);

            vector.set(2, "h");//替换掉指定索引的元素
            System.out.println(vector);

            Object[] str = vector.toArray();
            for (int i = 0; i < str.length; i++) {
                System.out.print(str[i] + ";");
            }
            vector.setSize(2);//重新设置大小为2
            System.out.println(" " + vector);
        }

        // ============LinkedList============
        {
            System.out.println("\n============LinkedList============");
            LinkedList linkedlist = new LinkedList();//自由使用是它的特色
            linkedlist.add("a");
            linkedlist.add(1, "c");
            linkedlist.addLast("b");
            linkedlist.addFirst("d");
            System.out.println(" LinkedList:");
            System.out.println(linkedlist);
            // linkedlist.clear();//该方法清空容器
            // linkedlist.remove(0);//删除索引为0的元素
            // linkedlist.remove("d");//删除值为d的元素
            // linkedlist.removeFirst();//删除第一个元素
            // linkedlist.removeLast();//删除最后一个元素
            for (int i = 0; i < linkedlist.size(); i++) {
                System.out.print(linkedlist.get(i) + ";");
            }
            System.out.println("\n");
        }

        // ============Stack============
        {
            System.out.println("\n============Stack============");
            Stack stack = new Stack();//堆栈
            stack.add("b");
            stack.add(0, "c");
            stack.push("d");
            stack.add("e");
            stack.push("a");
            System.out.println(stack);
            //[c, b, d, e, a]
            System.out.println("peek:" + stack.peek());
            //peek:a

            System.out.print("遍历(是从底开始的):");
            Enumeration enumeration = stack.elements();
            while (enumeration.hasMoreElements()) {
                System.out.print(enumeration.nextElement() + ";");
            }
            //遍历(是从底开始的):c;b;d;e;a;

            // 后进先出
            System.out.println("\npop:" + stack.pop());
            //pop:a

            //是否包含该元素
            System.out.println(stack.contains("d") + ";" + stack.contains("a"));

            //检索是从栈顶开始(栈顶index=0)
            System.out.println(stack.search("c"));
            System.out.println("\n");
        }



        // ============Collections============
        {
            System.out.println("\n============Collections============");
            String[] array = new String[] { "a", "b", "c" };
            List list = Arrays.asList(array);

            //Collections.fill
            Collections.fill(list, "Fill");//用Fill填充全部元素
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i) + ";");
            }

            //Collections.copy
            array = new String[] { "1", "2", "3" };
            List list2 = Arrays.asList(array);
            Collections.copy(list, list2);//拷贝list2的数据进list
            System.out.println(" " + list);

            //Collections.swap
            Collections.swap(list, 2, 1);//调换索引为1和2的元素的位置
            System.out.println(list);
            //============Collections============
            //Fill;Fill;Fill; [1, 2, 3]
            //[1, 3, 2]
        }


          // ============HashMap=====LinkedHashMap====TreeMap===
        {
            System.out.println("\n============HashMap============");
            HashMap hashmap = new HashMap();//一个速度最快的容器
            hashmap.put("0", "c");
            hashmap.put("1", "a");
            System.out.println(hashmap);//该容器有其内部的排序方式


            System.out.println(hashmap.containsKey("1"));//是否包含这个键
            System.out.println(hashmap.containsValue("c"));//是否包含值

            Set set = hashmap.keySet();//获取全部键
            System.out.println("\nHashMap: K-V");
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                Object key = iterator.next();
                System.out.print(key+":"+hashmap.get(key) + ";");
            }

            System.out.println("\nHashMap(Entry): K-V");
            Set<Map.Entry> kvSet = hashmap.entrySet();
            Iterator<Map.Entry> it = kvSet.iterator();
            while (it.hasNext()){
                Map.Entry entry = it.next();
                System.out.print( entry.getKey()+":" + entry.getValue() +";");
            }
            System.out.println();

            for (Map.Entry entry:(Set<Map.Entry>)hashmap.entrySet()){
                System.out.print("键："+entry.getKey());
                System.out.print("-值："+entry.getValue() + " ;");
            }
            System.out.println("\n");
        }

        // ============HashSet====LinkedHashSet=====TreeSet===
        {
            System.out.println("\n============HashSet============");
            HashSet hashset = new HashSet();//一个绝对不能重复的类型
            hashset.add("c");
            hashset.add("b");
            hashset.add("a");
            hashset.add("a");
            hashset.add("b");
            System.out.println(hashset);

            Iterator iterator = hashset.iterator();//取出元素
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + ";");
            }
            System.out.println("\n");
        }


        // Hashtable
        {
            System.out.println("\n============Hashtable============");
            Hashtable hashtable = new Hashtable();//线程同步的
            hashtable.put("0", "c");
            hashtable.put("1", "a");
            hashtable.put("3", "c");
            hashtable.put("2", "b");
            Enumeration enumeration = hashtable.elements();//获取元素，Enumeration已经不是主流，Iterator是它的下一代替代品
            while (enumeration.hasMoreElements()) {
                System.out.print(enumeration.nextElement() + ";");
            }
            System.out.println("\n");
        }
    }
}