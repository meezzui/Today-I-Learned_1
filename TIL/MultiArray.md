### 다차원배열
---
+ 2차원 배열
  + 선언방법  
  타입[][] 변수이름; -> `int[][] score;`    
  타입 변수이름[][]; -> `int score[][];`  
  타입[] 변수이름[]; -> ` int[] score[];
  + 2처원 배열은 행과 열로 구성되어 있다. '배열이름[행][열]' * 행이 세로, 열이 가로
  + 학생의 총점과 평균 계산, 과목별 총점 계산
 ```java
  public class MultiArray1 {
    public static void main(String[] args) {
        int [][] score = {
                {100,100,100}, {20,30,40},{50,60,40},{90,100,50},{30,50,60}
        };
        //과목별 총점
        int kor=0, eng=0, math=0;
        System.out.println("번호  국어  영어  수학  총점  평균");
        System.out.println("==============================");
        for(int i=0; i<score.length;i++){
            int sum=0; //개인별 총점
            float avg = 0.0f; // 개인별 평균

            kor += score[i][0];
            eng += score[i][1];
            math += score[i][2];
            System.out.printf("%3d",i+1); // 번호 매기기

            // 과목별 점수 보여주기
            for(int j=0; j<score[i].length;j++){
                sum += score[i][j];
                System.out.printf("%5d",score[i][j]);
            }
            avg = sum / (float)score[i].length; //평균계산
            System.out.printf("%5d %5.1f\n", sum,avg);
        }
        System.out.println("==============================");
        System.out.printf("총점:%3d %4d %4d\n",kor, eng, math);
    }
}
```
실행결과
```java
번호  국어  영어  수학  총점  평균
==============================
  1  100  100  100  300 100.0
  2   20   30   40   90  30.0
  3   50   60   40  150  50.0
  4   90  100   50  240  80.0
  5   30   50   60  140  46.7
==============================
총점:290  340  290
```
+ 가변배열
  + 두번째 차원의 길이는 지정하지 않는다. `int[][] score = new int[5][];`
+ 다차원 배열의 활용
  + 빙고
  ```java
      import java.util.Scanner;

    public class MultiArray2 {
        public static void main(String[] args) {
            final int size = 5;
            int x = 0, y = 0, num = 0;

            int bingo[][] = new int[size][size];
            Scanner scanner = new Scanner(System.in);

            //배열의 모든 요소를 1부터 size*size까지의 숫자로 초기화
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    bingo[i][j] = i * size + j + 1;
                }
            }
            // 배열에 저장된 값을 섞는다.
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    x = (int) (Math.random() * size);
                    y = (int) (Math.random() * size);

                    // bingo[i][j]와 임의로 선택된 값(bingo[x][y])을 바꾼다.
                    int temp = bingo[i][j];
                    bingo[i][j] = bingo[x][y];
                    bingo[x][y] = temp;
                }
            }
            do {
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        System.out.printf("%2d", bingo[i][j]);

                    }
                    System.out.println();
                }
                System.out.println();

                System.out.printf("1~%d의 숫자를 입력하세요.(종료를 하고 싶으면 0을 입력하세요)",size*size);
                String temp = scanner.nextLine(); // 화면에 입력받은 내용을 temp에 저장
                num = Integer.parseInt(temp); // 입력받은 문자열(temp)을 숫자로 변환


                //입력받은 숫자와 같은 숫자가 지정된 요소를 찾아서 0을 저장 (일치하면 0으로 바꾼다)
                outer:
                for(int i=0; i<size; i++){
                    for(int j=0; j<size; j++){
                        if(bingo[i][j]==num) {
                            bingo[i][j] = 0;
                            break outer;
                        }
                    }
                }
            }while (num!=0);
        }
    }
  ```
  실행결과
  ```java
  1~25의 숫자를 입력하세요.(종료를 하고 싶으면 0을 입력하세요)7
  22 12 24 16 0
  20 31 51 0 21
  23 18 11 8 13
  19  4 14 9 1
  25  2 6 17 5
  ```
  
