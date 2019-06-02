package Stream;


import model.Student;
import org.junit.Test;

import java.util.*;


/**
 * 测试
 */
public class Demo7 {

    List<Student> students = Arrays.asList(new Student("张三",23,"男"),
            new Student("李四",18,"男"),
            new Student("李四",28,"女")
    );




    // 给定一个数字列表，返回一个由每个数的平方构成的列表
    @Test
    public void demo1(){

        Integer[] nums = new Integer[]{1,2,3,4,5};

        Arrays.stream(nums).map( (x) -> x*x ).forEach(System.out::print);

    }


    // 怎么用 map 和 reduce 方法数一数流中又多少个 Student
    @Test
    public void demo2(){

        Integer count = students.stream().map( (x) -> 1).reduce(0,Integer::sum);
        System.out.println(count);    // 3

    }







}
