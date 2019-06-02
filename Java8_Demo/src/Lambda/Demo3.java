package Lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


/**
 * Java8 内置的四大核心函数式接口
 *
 * Consumer<T> ：消费型接口
 * void accept(T t);
 *
 * Supplier<T> ：供给型接口
 * T get();
 *
 * Function<T,R> ：函数型接口
 * R apply(T t);
 *
 * Predicate<T> ：断言型接口
 * boolean test(T t);
 */
public class Demo3 {


    // Consumer<T>  消费型接口
    @Test
    public void test1(){
        happy(1000, (x) -> System.out.println("每次出去玩，花费"+ x + "元") );    // 每次出去玩，花费1000.0元
    }

    private void happy(double money, Consumer<Double> consumer){
        consumer.accept(money);
    }




    // Supplier<T>  消费型接口
    @Test
    public void test2(){
        List<Integer> list = getNumberList(10, () -> (int)(Math.random() * 100));
        System.out.println(list);   // [56, 4, 71, 67, 68, 75, 30, 60, 94, 53]
    }

    // 需求：产生指定个数的整数，并放入集合中
    private List<Integer> getNumberList(int num, Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num ; i++) {
            Integer a = supplier.get();
            list.add(a);
        }
        return list;
    }


    // Function<T, R>  函数型接口
    @Test
    public void test3(){
        String string = stringHandler("abcde", (x) -> x.toUpperCase() );
        System.out.println(string);    // ABCDE
    }

    //字符串处理
    private String stringHandler(String string, Function<String, String> function){
        return function.apply(string);
    }



    // Function<T, R>  断言型接口
    @Test
    public void test4(){
        List<String> stringList = Arrays.asList("hello","ko","china");
        List<String> list = filterString(stringList, (x) -> x.length() > 3);
        System.out.println(list);    // [hello, china]
    }

    //需求：将满足条件的字符串放入集合中
    private List<String> filterString(List<String> list, Predicate<String> predicate){
        List<String> stringList = new ArrayList<>();
        for (String s:list){
            if (predicate.test(s)){
                stringList.add(s);
            }
        }
        return stringList;
    }

}
