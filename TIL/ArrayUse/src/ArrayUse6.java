public class ArrayUse6 {
    public static void main(String[] args) {
        int[] arr = new int[10];
        int[] counter = new int[10];

        for(int i=0; i<arr.length; i++){
            arr[i] = (int)(Math.random()*10);
                System.out.print(arr[i]);
        }
        System.out.println();
        for(int i=0; i< arr.length;i++){
            counter[arr[i]]++; // 해당 counter배열의 요소의 값을 1증가 시킨다.
        }
        for(int i=0; i<arr.length;i++){
            System.out.println(i+"의 개수:"+counter[i]);
        }
    }
}
