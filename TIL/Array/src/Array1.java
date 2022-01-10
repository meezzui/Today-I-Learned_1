public class Array1 {
    public static void main(String[] args) {
        int[] score = new int[5];
        int k =1;
        score[0] = 10;
        score[1] = 20;
        score[k+1] = 30; // score[2] = 30;
        score[3] = 40;
        score[4] = 50;

        int tmp = score[k+2] + score[4]; // int tmp = score[3] + score[4]

        //for문으로 배열의 모든 요소 출력
        for(int i=0; i<5; i++){
            System.out.printf("score[%d]:%d\n",i,score[i]);
        }
        System.out.printf("tmp:%d",tmp);
//        System.out.printf("score[%d]:%d\n",7,score[7]); //index의 범위를 벗어난 값
    }
}
