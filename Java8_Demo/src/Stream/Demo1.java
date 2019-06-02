package Stream;

import model.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一、Stream 的三个操作步骤
 * 1、创建 Stream
 * 2、中间操作
 * 3、终止操作（终端操作）
 */
public class Demo1 {

    //创建 Stream
    @Test
    public void demo1(){
        //1、可以通过 Collection 系列集合提供的 stream() 或 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();   // 串行流
        Stream<String> stream1 = list.parallelStream();  //并行流

        //2、通过 Arrays 中的静态方法 stream() 获取数组流
        Student[] students = new Student[10];
        Stream<Student> stream2 = Arrays.stream(students);

        //3、通过 Stream 类中的静态方法 of()
        Stream<String> stream3 = Stream.of("aa","bb","cc");

        //4、创建无限流
        //迭代
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x+2);
        stream4.limit(20).forEach(System.out::println);

        //生成
        Stream.generate( () -> Math.random()).limit(10).forEach(System.out::println);
    }
}
