import java.util.*;

public class ArrayUse4 {
    public static void main(String[] args) {
        int[] code = {-4, -1, 3, 6, 22}; //불연속적인 값들로 구성
        int[] arr = new int [10];

        for(int i=0; i<arr.length; i++){
            int temp = (int)(Math.random() * code.length);
            arr[i] = code[temp];
        }
        System.out.println(Arrays.toString(arr));
    }
}
