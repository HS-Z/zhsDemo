package Lambda;

import model.Student;
import myInterface.MyFunctionInterface;
import myInterface.MyInterface;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Demo2 {


    List<Student> students = Arrays.asList(new Student("张三",23,"男"),
            new Student("李四",18,"男"),
            new Student("王五",35,"女"),
            new Student("赵六",20,"男")
    );


    @Test
    public void test1(){

        Integer value = getValue(100,200,( (x, y) -> x + y ) );
        System.out.println(value);    // 300

    }

    private Integer getValue(Integer x, Integer y, MyInterface myInterface){
        return myInterface.getValue(x, y);
    }


    /**
     * 排序
     * 年龄相同时按姓名排序，否则按年龄排序
     */
    @Test
    public void test2(){

        Collections.sort(students, (x,y) -> {
            if (x.getAge() == y.getAge()){
                return x.getName().compareTo(y.getName());
            }else {
                return x.getAge().compareTo(y.getAge());
            }
        });

        for (Student student:students){
            /**
             * Student{name='李四', age=18, sex='男'}
             * Student{name='赵六', age=20, sex='男'}
             * Student{name='张三', age=23, sex='男'}
             * Student{name='王五', age=35, sex='女'}
             */
            System.out.println(student);
        }

    }



    @Test
    public void test3(){

        Long value = this.get(100L,200L, (x,y) -> x * y );
        System.out.println(value);  // 20000

        String he = this.get("abcde","ddd", (x,y) -> x.toUpperCase());
        System.out.println(he);    // ABCDE

    }

    private Long get(Long x, Long y, MyFunctionInterface<Long,Long> functionInterface){
        return functionInterface.kidding(x,y);
    }

    private String get(String x, String y, MyFunctionInterface<String,String> functionInterface){
        return functionInterface.kidding(x,y);
    }


}
