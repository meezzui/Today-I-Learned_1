public class ArrayUse5 {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for(int i=0; i<arr.length;i++){
            System.out.print(arr[i] = (int)(Math.random()*10));
        }
        System.out.println();

        for(int i=0; i<arr.length-1;i++){
            boolean changed = false; //자리바꿈이 있는지 체크
            for(int j=0; j<arr.length-1-i;j++){//<arr.length-1-i -> 바로 옆에 요소랑 비교하기 위해
                if(arr[j] > arr[j+1]){//옆의 값이 작으면 서로 바꿈
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    changed = true;//자리바꿈이 발생했으니 changed를 true로
                }
            }
            if(!changed) break; // 자리바꿈이 없으면 반복문 벗어남
            for(int k=0; k<arr.length;k++){
                System.out.print(arr[k]); // 정렬된 결과 출력
            }
            System.out.println();
        }
    }
}
