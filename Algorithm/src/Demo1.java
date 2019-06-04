import org.junit.Test;

/**
 * https://mp.weixin.qq.com/s/8-9OBmrZhlyphEo15IU-8g
 *
 * 问题描述：给定一个整型有序数组，如何找出某一整数是否在这个有序数组中，以及该整数对应的下标
 * 问题补充：该有序数组升序排列
 * 有序数组示例：{1,3,4,5,8,9,16,18,36}
 *
 * 使用 二分查找 算法
 */
public class Demo1 {


    @Test
    public void test(){
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i;
        }

        int index = searchIndex(array,67);
        System.out.println(index);   //67

        int[] arr = new int[]{1,3,4,5,8,9,16,18,36};
        int index2 = searchIndex(arr,16);
        System.out.println(index2);    //6
    }


    private int searchIndex(int[] array, int value){
        int start = 0;    //查找范围起点
        int end = array.length - 1;    //查找范围终点
        int middle;    //查找范围的中间点
        while (start <= end){
            //不直接使用 (start + end)/2 的原因是为了防止 start 和 end 的值都很大时，两者相加时出现溢出
            middle = start + (end - start)/2;
            if (array[middle] == value){
                return middle;
            }else if (array[middle] > value){
                end = middle - 1;
            }else {
                start = middle + 1;
            }
        }
        return -1;
    }

}
