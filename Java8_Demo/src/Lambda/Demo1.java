package Lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 *  Lambda 表达式的基础语法：Java8 中引入了一个新的操作符 "->"，该操作符称为箭头操作符或 Lambda 操作符，箭头操作符将 Lambda 表达式拆分成两部分。
 *  左侧：Lambda 表达式的参数列表。
 *  右侧：Lambda 表达式中所需执行的功能，即 Lambda 体。
 *
 *  Lambda 表达式需要“函数式接口”的支持。
 *  函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。
 *  函数式接口可以使用注解 @FunctionalInterface 修饰，该注解可以检查当前接口是否是函数式接口。
 */
public class Demo1 {

    /**
     * 语法格式一：无参数，无返回值
     */
    @Test
    public void demo1(){

        // 在局部内部类中用到同级别的局部变量时，JDK1.7 之前，修饰符必须为 final，在 JDK1.8 之后无需再加 final，但本质仍是 final
        int i = 0;

        // 传统格式
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!" + i );    // Hello World!0
            }
        };

        runnable1.run();

        System.out.println("-----------------------------------");

        // Lambda 格式
        Runnable runnable2 = () -> System.out.println("Hello World");    // Hello World
        runnable2.run();

    }


    /**
     * 语法格式二：有一个参数，无返回值
     */
    @Test
    public void demo2(){

        Consumer<String> consumer = (x) -> System.out.println(x);    // I like money!
        consumer.accept("I like money!");

        // 若只有一个参数，小括号可以省略不写
        Consumer<String> stringConsumer = x -> System.out.println(x);    // 小括号可以省略不写..........
        stringConsumer.accept("小括号可以省略不写..........");

    }


    /**
     * 语法格式三：有两个以上的参数，有返回值，并且 Lambda 体中有多条语句，当有多条语句时，必须使用大括号{}
     */
    @Test
    public void demo3(){

        // lambda 表达式的参数列表的数据类型可以省略不写，因为 JVM 编译器可以通过上下文推断出数据类型，即“类型推断”。
        Comparator<Integer> comparator = (x,y) -> {
            System.out.println("比较两个数字的大小");
            return Integer.compare(x,y);
        };

        Integer s = comparator.compare(3,5);
        System.out.println(s);  // -1

    }


    /**
     * 语法格式四：当 Lambda 体中只有一条语句，return 和大括号都可以省略不写
     */
    @Test
    public void demo4(){
        // Lambda 表达式的参数列表的数据类型可以省略不写，因为 JVM 编译器可以通过上下文推断出数据类型，即“类型推断”。
        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);

        Integer s = comparator.compare(5,3);
        System.out.println(s);  // 1
    }

}
