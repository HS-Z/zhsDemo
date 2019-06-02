package Lambda;

import model.Student;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;


/**
 * 一、方法引用：若 Lambda 体中的内容有方法已经实现了，我们可以使用“方法引用”（可以理解为方法引用是 Lambda 表达式的另外一种表现形式）
 * 主要有三种语法格式：
 * 1、对象::实例方法名
 * 2、类::静态方法名
 * 3、类::实例方法名
 *
 * 注意：
 * 1、Lambda 体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致！
 * 2、若 Lambda 参数列表中的第一参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用 类::实例方法名
 *
 * 二、构造器引用
 * 格式： ClassName::new
 * 注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致！
 *
 * 三、数组引用
 * 格式：Type::new
 *
 */
public class Demo4 {


    // 对象::实例方法名
    @Test
    public void test1(){

        Consumer<String> consumer = (x) -> System.out.println(x);  //普通用法

        PrintStream printStream = System.out;

        // Lambda 体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致！
        // Consumer 中的抽象方法，参数为 T，返回值为 void；System.out.println(x)中，参数为 x，返回值为 void
        Consumer<String> consumer1 = printStream::println;  // 对象::实例方法名

        Consumer<String> consumer2 = System.out::println;   // hello
        consumer2.accept("hello");

    }


    // 对象::实例方法名
    @Test
    public void test2(){

        Student student = new Student("张三",18,"男");

        Supplier<String> supplier = () -> student.getName();
        String name = supplier.get();
        System.out.println(name);    // 张三

        //Lambda 体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致！
        Supplier<String> supplier1 = student::getName;
        String name1 = supplier1.get();
        System.out.println(name1);    // 张三

    }


    // 类::实例方法名
    @Test
    public void test3(){

        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);

        //Lambda 体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致！
        Comparator<Integer> comparator1 = Integer::compare;    // 类::静态方法名

    }


    // 类::静态方法名
    @Test
    public void test4(){

        // 普通用法
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);

        // 若 Lambda 参数列表中的第一参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用 类::实例方法名
        BiPredicate<String, String> biPredicate1 = String::equals;

    }


    //构造器引用
    @Test
    public void test5(){

        Supplier<Student> studentSupplier = () -> new Student();

        //构造器引用方式
        Supplier<Student> studentSupplier1 = Student::new;   //调用无参构造器
        Student student = studentSupplier1.get();
        System.out.println(student);    // Student{name='null', age=null, sex='null'}

    }


    //构造器引用
    @Test
    public void test6(){

        Supplier<Student> studentSupplier = () -> new Student();

        //构造器引用方式
        Supplier<Student> studentSupplier1 = Student::new;   //调用无参构造器
        Student student = studentSupplier1.get();
        System.out.println(student);    // Student{name='null', age=null, sex='null'}



        Function<String,Student> function = (x) -> new Student(x);

        Function<String,Student> function1 = Student::new;   //一个参数的构造器
        Student student1 = function1.apply("张三");
        System.out.println(student1);    //Student{name='张三', age=null, sex='null'}


        BiFunction<String,Integer,Student> biFunction = (x,y) -> new Student(x,y);

        BiFunction<String,Integer,Student> biFunction1 = Student::new;    //两个参数的构造器
        Student student2 = biFunction1.apply("李四",28);
        System.out.println(student2);    // Student{name='李四', age=28, sex='null'}

    }



    //数组引用
    @Test
    public void test7(){

       Function<Integer,String[]> function = (x) -> new String[x];
       String[] strings = function.apply(10);
       System.out.println(strings.length);    // 10

       Function<Integer,String[]> function1 = String[]::new;
       String[] strings1 = function1.apply(20);
       System.out.println(strings1.length);    // 20

    }

}
