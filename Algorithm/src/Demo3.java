import org.junit.Test;

/**
 * 排序
 */
public class Demo3 {


    @Test
    public void test(){
        int[] array = new int[]{3,6,7,9,1,5,8,2,4};


        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i-1]){
                for (int j = i; j > 0; j--) {
                    if (array[j]<array[j-1]){
                        int temp = array[j-1];
                        array[j-1] = array[j];
                        array[j] = temp;
                    }
                }
            }
        }

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

    }
}
