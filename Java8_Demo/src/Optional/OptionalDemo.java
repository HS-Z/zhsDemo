package Optional;

import model.Goddess;
import model.NewStudent;
import model.Student;
import org.junit.Test;

import java.util.Optional;

/**
 * Optional<T> 类（java.util.Optional）是一个容器类，代表一个值存在或不存在，
 * 原来用 null 表示一个值不存在，现在 Optional 可以更好的表达这个概念。
 * 并且可以避免空指针异常。
 */
public class OptionalDemo {


    @Test
    public void demo1(){
        // Optional.of(T t)  创建一个 Optional 示例
        Optional<Student> optionalStudent = Optional.of(new Student());
        Student student = optionalStudent.get();

        System.out.println(student);   // Student{name='null', age=null, sex='null'}
        System.out.println(student.getName());  // null


        //当使用 Optional 时，发生 NullPointerException ，可以快速定位到出现错误的语句是下面一行
        Optional<Student> optional = Optional.of(null);   // java.lang.NullPointerException
        Student student1 = optional.get();
        System.out.println(student1);

    }


    @Test
    public void demo2(){
        // Optional.empty() 创建一个空的 Optional 实例
        Optional<Student> optionalStudent = Optional.empty();
        Student student = optionalStudent.get();   // java.util.NoSuchElementException: No value present
        System.out.println(student);
        System.out.println(student.getName());
    }


    @Test
    public void demo3(){
        // Optional.ofNullable(T t)， 若 t 不为 null，创建 Optional 实例，否则创建空实例
        Optional<Student> optionalStudent = Optional.ofNullable(new Student());
        Student student = optionalStudent.get();
        System.out.println(student);    // Student{name='null', age=null, sex='null'}
        System.out.println(student.getName());    // null

        // 若 t 为 null，等同于 Optional.of(T t) 或 Optional.empty()
        Optional<Student> optionalStudent1 = Optional.ofNullable(null);
        Student student1 = optionalStudent1.get();   // java.util.NoSuchElementException: No value present
        System.out.println(student1);
        System.out.println(student1.getName());
    }


    @Test
    public void demo4(){
        // Optional.isPresent() 判断是否包含值
        Optional<Student> optionalStudent = Optional.empty();
        System.out.println(optionalStudent.isPresent());    // false

        Optional<Student> optionalStudent1 = Optional.of(new Student());
        System.out.println(optionalStudent1.isPresent());   //true
    }


    @Test
    public void demo5(){
        // Optional.orElse() 如果调用对象包含值，则返回该值，否则返回 t
        Optional<Student> optionalStudent = Optional.of(new Student("张三",28,"男"));
        Student student = optionalStudent.orElse(new Student("李四",18,"男"));
        System.out.println(student);   // Student{name='张三', age=28, sex='男'}

        Optional<Student> optionalStudent1 = Optional.empty();
        Student student1 = optionalStudent1.orElse(new Student("王五",18,"男"));
        System.out.println(student1);   // Student{name='王五', age=18, sex='男'}
    }


    @Test
    public void demo6 (){
        // Optional.orElseGet(Supplier s) 如果调用对象包含值，则返回该值，否则返回 s 获取的值
        // orElseGet 与 orElse 的主要差别体现在 orElseGet 实现了 Supplier 函数式接口，
        // 可以根据自己的需求写功能
        Optional<Student> optionalStudent = Optional.of(new Student("张三",28,"男"));
        Student student = optionalStudent.orElseGet( () -> {int a = 1;
                                    if (a == 1){
                                        return new Student("六六",18,"男");
                                    }else {
                                        return new Student("田七",18,"男");
                                    }
        });
        System.out.println(student);   // Student{name='张三', age=28, sex='男'}


        Optional<Student> optionalStudent1 = Optional.empty();
        Student student1 = optionalStudent1.orElseGet( () -> {int a = 1;
                                    if (a == 1){
                                        return new Student("六六",18,"男");
                                    }else {
                                        return new Student("田七",18,"男");
                                    }
        });
        System.out.println(student1);   // Student{name='六六', age=18, sex='男'}

    }


    @Test
    public void demo7(){
        // Optional.map(Function f) 如果有值对其处理，并返回处理后的 Optional，否则返回 Optional.empty()
        Optional<Student> optionalStudent = Optional.of(new Student("张三",28,"男"));
        Optional<String> stringOption = optionalStudent.map( (e) -> e.getName() );
        System.out.println(stringOption.get());    // 张三
    }


    @Test
    public void demo8(){
        // Optional.flatMap(Function f) 与 map 类似，要求返回值必须是 Optional
        Optional<Student> optionalStudent = Optional.of(new Student("张三",28,"男"));
        Optional<String> stringOption = optionalStudent.flatMap( (e) -> Optional.of(e.getName()) );
        System.out.println(stringOption.get());    // 张三
    }


    /**
     * Optional 使用示例
     */
    @Test
    public void demo9(){

        Optional<NewStudent> student = Optional.ofNullable(null);
        String goddessName = this.getGoddessName(student);
        System.out.println(goddessName);   // 苍老师

        Optional<Goddess> goddess = Optional.ofNullable(new Goddess("波多野老师"));
        Optional<NewStudent> newStudent = Optional.ofNullable(new NewStudent(goddess));
        String name = this.getGoddessName(newStudent);
        System.out.println(name);    // 波多野老师
    }


    public String getGoddessName(Optional<NewStudent> student){
        return student.orElse(new NewStudent()).getGoddess().orElse(new Goddess("苍老师")).getName();
    }


}
