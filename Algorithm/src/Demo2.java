import org.junit.Test;

/**
 * https://mp.weixin.qq.com/s/33aOwDGFQ5omE3sHmVLXVA
 * 旋转数组中的“二分查找”
 * 示例：{7,8,9,1,2,3,4}
 *
 */
public class Demo2 {


    @Test
    public void test(){
        int[] array1 = new int[]{7,8,9,1,2,3,4};
        int[] array2 = new int[]{6,7,1,2,3,4,5};

        int index1 = searchIndex(array1,3);
        System.out.println(index1);   //5

        int index2 = searchIndex(array2,3);
        System.out.println(index2);   //4

    }


    private int searchIndex(int[] array, int value){
        int start = 0;
        int end = array.length - 1;
        int middle;
        while (start <= end){
            middle = start + (end-start)/2;
            if (array[middle] == value){
                return middle;
            }

            //情况一：旋转点在中间点右侧
            //这种情况下： 1、中间点以及它左侧的元素，全部是升序的；2、最左侧元素，必定小于等于中间点的值。
            if (array[middle] > array[start]){
                if (value >= array[start] && value <= array[middle]){    //要查询的数据在中间点左侧
                    end = middle - 1;
                }else {    //要查询的数据在中间点右侧
                    start = middle + 1;
                }
            }
            //情况二：旋转点在中间点左侧，或与中间点重合
            //这种情况下：1、中间点以及它右侧的元素，全部是升序的；2、最左侧元素，必定大于中间点的值。
            else {
                if (value > array[middle] && value < array[end]){    //要查询的数据在中间点右侧
                    start = middle + 1;
                }else {
                    end = middle - 1;
                }
            }
        }
        return -1;
    }
}
