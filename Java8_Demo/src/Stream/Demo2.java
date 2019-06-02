package Stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 中间操作
 */
public class Demo2 {

    List<String> list = Arrays.asList("aaa","ccc","bbb","aaaaaa","ddd","bbb","eee");


    //筛选与切片
    @Test
    public void demo1(){

        // filter 接收 Lambda ，从流中排除某些元素
        //中间操作：不会执行任何操作
        Stream<String> stream = list.stream().filter( (x) -> x.length() > 3);
        //终止操作：一次性执行全部内容，即“惰性操作”
        stream.forEach(System.out::println);    // aaaaaa

        System.out.println("---------------------------------------");


        // limit 截断流，使其元素不超过给定数量
        Stream<String> stream1 = list.stream();
        stream1.limit(2).forEach(System.out::println);   // aaa  ccc

        System.out.println("---------------------------------------");

        //skip 跳过元素，返回一个扔掉了前 n 个元素的流，若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
        Stream<String> stream2 = list.stream();
        stream2.skip(5).forEach(System.out::println);   // bbb  eee


        System.out.println("---------------------------------------");


        //distinct 筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
        Stream<String> stream3 = list.stream();
        stream3.distinct().forEach(System.out::println);   // 只会显示一个“bbb”


    }


    //外部迭代
    @Test
    public void demo2(){

        Iterator<String> studentIterator =list.iterator();
        while (studentIterator.hasNext()){
            System.out.println(studentIterator.next());
        }

    }



    //映射
    @Test
    public void demo3(){

        // map 接收 Lambda ，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        Stream<String> stream = list.stream().map( (x) -> x.toUpperCase());
        stream.forEach(System.out::println);   //全部转化成大写

        System.out.println("---------------------------------------");

    }


    @Test
    public void demo4(){

        Stream<Stream<Character>> stream = list.stream().map( Demo2::getCharacter );
        stream.forEach( (x) -> x.forEach(System.out::println) );

    }


    //获取单个字符
    private static Stream<Character> getCharacter(String str){
        List<Character> list = new ArrayList<>();
        for (Character character:str.toCharArray()){
            list.add(character);
        }
        Stream<Character> stream = list.stream();
        return stream;
    }



    @Test
    public void demo5(){

        // flatMap 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
        Stream<Character> stream = list.stream().flatMap( Demo2::getCharacter );
        stream.forEach( System.out::println );

    }



    @Test
    public void demo6(){

        // map 和 flatMap 参考 List 中的 add() 和 addAll()
        List data = Arrays.asList("aaa","bbb");
        List list = new ArrayList();
        list.add(111);
        list.add(222);

//        list.add(data);   // [111, 222, [aaa, bbb]]
        list.addAll(data);  // [111, 222, aaa, bbb]

        System.out.println(list);

    }




}
