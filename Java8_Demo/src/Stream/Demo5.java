package Stream;


import model.Student;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 终止操作
 */
public class Demo5 {

    List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

    List<Student> students = Arrays.asList(new Student("张三",23,"男"),
            new Student("李四",18,"男"),
            new Student("李四",28,"女")
    );


    //查找与匹配

    @Test
    public void demo1(){

        //归约： reduce() 可以将流中元素反复结合起来，得到一个值

        //将值累加
        Integer sum = list.stream().reduce(0, (x,y) -> x+y);
        System.out.println(sum);  // 55

        //将值累加（从第一个参数开始累加，保证了结果不会为空）
        Integer sum1 = list.stream().reduce(1, (x,y) -> x+y);
        System.out.println(sum1);  // 56


        //计算所有学生的年龄总数
        Optional<Integer> sumAge = students.stream()
                .map(Student::getAge)
                .reduce(Integer::sum);
        System.out.println(sumAge.get());    // 69
    }


    /**
     * 收集
     * collect  将流转化为其他形式。接收一个 Collector 接口的实现，用于给 Stream 中元素做汇总的方法
     */
    @Test
    public void demo2(){

        // 收集所有名称
        List<String> names1 = students.stream()
                .map(Student::getName)
                .collect(Collectors.toList());

        names1.stream().forEach(System.out::println);   // 张三、李四、李四

        System.out.println("----------------------------");


        // 收集所有名称，去掉重复
        Set<String> names2 = students.stream()
                .map(Student::getName)
                .collect(Collectors.toSet());

        names2.forEach(System.out::println);    // 李四、张三



        System.out.println("----------------------------");


        // 收集所有名称，去掉重复
        HashSet<String> names3 = students.stream()
                .map(Student::getName)
                .collect(Collectors.toCollection( HashSet::new ));

        names3.forEach(System.out::println);    // 李四、张三


    }


    /**
     * collect
     */
    @Test
    public void demo3(){

        //总数
        Long count = students.stream().collect(Collectors.counting());
        System.out.println(count);   // 3

        //平均值
        Double avg = students.stream().collect(Collectors.averagingLong( Student::getAge ) );
        System.out.println(avg);    // 23.0

        //求和
        Integer sumAge = students.stream().collect(Collectors.summingInt(Student::getAge));
        System.out.println(sumAge);    // 69

        //最大值
        Optional<Student> optionalStudent = students.stream()
                .collect(Collectors.maxBy( (x,y) -> Integer.compare(x.getAge(),y.getAge())));
        System.out.println(optionalStudent.get().getAge());    // 28

        //最小值
        Optional<Integer> minAge = students.stream()
                .map(Student::getAge)
                .collect(Collectors.minBy(Integer::compare));
        System.out.println(minAge.get());    // 18

    }



    @Test
    public void demo4(){

        // 分组
        Map<String,List<Student>> listMap = students.stream()
                .collect(Collectors.groupingBy(Student::getSex));
        System.out.println(listMap);    // {女=[Student{name='李四', age=28, sex='女'}], 男=[Student{name='张三', age=23, sex='男'}, Student{name='李四', age=18, sex='男'}]}

        // 多级分组（先按性别分，性别相同时，按年龄分）
        Map<String,Map<Integer,List<Student>>> mapMap = students.stream()
                .collect(Collectors.groupingBy(Student::getSex,Collectors.groupingBy( Student::getAge )));
        System.out.println(mapMap);    // {女={28=[Student{name='李四', age=28, sex='女'}]}, 男={18=[Student{name='李四', age=18, sex='男'}], 23=[Student{name='张三', age=23, sex='男'}]}}

        //分区（满足条件一个区，不满足条件一个区）
        Map<Boolean,List<Student>> listMap1 = students.stream()
                .collect(Collectors.partitioningBy( (e) -> e.getSex().equals("男")));
        System.out.println(listMap1);    // {false=[Student{name='李四', age=28, sex='女'}], true=[Student{name='张三', age=23, sex='男'}, Student{name='李四', age=18, sex='男'}]}

    }


    @Test
    public void demo5(){

        //拼接字符串
        String name1 = students.stream().map(Student::getName).collect(Collectors.joining());
        System.out.println(name1);    // 张三李四李四

        String name2 = students.stream().map(Student::getName).collect(Collectors.joining(","));
        System.out.println(name2);    // 张三,李四,李四

        String name3 = students.stream().map(Student::getName).collect(Collectors.joining(",","--->","<---"));
        System.out.println(name3);    // --->张三,李四,李四<---
    }



    @Test
    public void demo6(){

        IntSummaryStatistics statistics = students.stream()
                .collect(Collectors.summarizingInt(Student::getAge));

        System.out.println(statistics.getAverage());   // 平均值  23.0
        System.out.println(statistics.getCount());   //数量   3
        System.out.println(statistics.getMax());    // 最大值   28
        System.out.println(statistics.getMin());    // 最小值   18
        System.out.println(statistics.getSum());    // 和   69
    }






}
