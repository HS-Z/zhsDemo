package Stream;


import model.Student;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * 终止操作
 */
public class Demo4 {

    List<String> list = Arrays.asList("aaa","aaa","aaa");

    List<Student> students = Arrays.asList(new Student("张三",23,"男"),
            new Student("李四",18,"男"),
            new Student("王五",35,"女"),
            new Student("赵六",20,"男")
    );


    //查找与匹配
    @Test
    public void demo1(){

        // allMatch() 检查是否匹配所有元素
        Boolean b1 = students.stream().allMatch( (e) -> e.getSex().equals("男"));
        System.out.println(b1);    // false

        Boolean b2 = list.stream().allMatch( (e) -> e.equals("aaa"));
        System.out.println(b2);    // true

        // anyMatch() 检查是否至少匹配一个元素
        Boolean b3 = students.stream().anyMatch( (e) -> e.getSex().equals("男"));
        System.out.println(b3);    // true

        Boolean b4 = list.stream().anyMatch( (e) -> e.equals("bbb"));
        System.out.println(b4);    // false

        // noneMatch() 检查是否没有匹配所有元素
        Boolean b5 = students.stream().noneMatch( (e) -> e.getSex().equals("女"));
        System.out.println(b5);   // false  （有匹配的元素）

        Boolean b6 = list.stream().noneMatch( (e) -> e.equals("bbb"));
        System.out.println(b6);  // true   （没有匹配的元素）

        Boolean b7 = list.stream().noneMatch( (e) -> e.equals("aaa"));
        System.out.println(b7);  // false  （有匹配的元素）

    }


    @Test
    public void demo2(){

        // findFirst() 返回第一个元素
        Optional<Student> optionalStudent1 = students.stream().findFirst();
        Student student1 = optionalStudent1.get();
        System.out.println(student1.getName());    // 张三

        // findAny() 返回流中任意一个元素
        Optional<Student> optionalStudent2 = students.stream().findAny();
        Student student2 = optionalStudent2.get();
        System.out.println(student2.getName());    // 值不确定
    }


    @Test
    public void demo3(){

        // count() 返回流中元素的总个数
        Long count = students.stream().count();
        System.out.println(count);    // 4

        // max() 返回流中最大值
        Optional<Student> optionalStudent3 = students.stream().max( (x,y) -> Integer.compare(x.getAge(),y.getAge()));
        Student student3 = optionalStudent3.get();
        System.out.println(student3.getAge());    // 35

        // min() 返回流中元素的总个数
        Optional<Integer> optionalStudent4 = students.stream()
                .map(Student::getAge)
                .min( Integer::compareTo);
        Integer minAge = optionalStudent4.get();
        System.out.println(minAge);    // 18

    }

}
