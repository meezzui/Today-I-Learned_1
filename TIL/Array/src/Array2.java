public class Array2 {
    public static void main(String[] args) {
        int[] arr = new int[5];

        //배열 arr에 1~5를 저장
        for(int i=0; i<arr.length; i++){
            arr[i] = i+1;
        }
        System.out.println("[변경전]");
        System.out.println("arr 배열의 길이:"+ arr.length);
        for(int i=0; i<arr.length;i++){
            System.out.println("arr["+i+"]:"+arr[i]);
        }
        int[] temp = new int[arr.length*2];

        //배열 arr에 저장된 값들을 배열 temp에 복사
        for(int i=0; i<arr.length;i++){
            temp[i] = arr[i];
        }
         arr = temp; //temp에 저장된 값을 arr에 저장
        System.out.println("[변경후]");
        System.out.println("arr 배열의 길이:"+ arr.length);
        for(int i=0; i<arr.length;i++){
            System.out.println("arr["+i+"]:"+arr[i]);
        }
    }
}
