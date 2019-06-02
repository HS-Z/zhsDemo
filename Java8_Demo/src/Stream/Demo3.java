package Stream;

import model.Student;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 中间操作
 */
public class Demo3 {


    List<String> list = Arrays.asList("aaa","ccc","bbb","aaaaaa","ddd","bbb","eee");

    List<Student> students = Arrays.asList(new Student("张三",23,"男"),
            new Student("李四",18,"男"),
            new Student("王五",35,"女"),
            new Student("赵六",20,"男")
    );


    //排序
    @Test
    public void demo1(){

        //自然排序 sorted() ，类似集合中的 Comparable
        Stream<String> stream = list.stream().sorted();
        stream.forEach(System.out::println);


        //定制排序  sorted(Comparator<T> comparator) ，类似集合中的 Comparator
        Stream<Student> stream1 = students.stream().sorted( (x,y) -> {
            //按年龄排序，如果年龄相同，按姓名排序
            if (x.getAge() == y.getAge()){
                return x.getName().compareTo(y.getName());
            }else {
                return x.getAge().compareTo(y.getAge());
            }
        });
        stream1.forEach(System.out::println);

    }

}
