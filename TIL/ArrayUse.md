### 배열의 활용
+ 총점과 평균 구하기
```java
public class ArrayUse1 {
    public static void main(String[] args) {
        //총합과 평균 구하기
        int sum = 0; // 총점을 저장하기 위한 변수
        float avg = 0f; //평균을 저장하기 위한 변수

        int[] score = {100, 88,100,100,90};

        for(int i=0; i<score.length;i++){
            sum += score[i];
        }
        avg = sum / (float)score.length; // 계산결과를 float으로 얻기위한 형변환
        System.out.println("총점:"+ sum);
        System.out.println("평균:"+ avg);

    }
}
```
실행결과
```java
총점:478
평균:95.6
```
+ 최대값과 최소값 구하기
```java
public class ArrayUse2 {
    public static void main(String[] args) {
        int[] score ={79,88,80,23,100,50,60};
        int max = score[0]; //배열의 첫번째 값으로 최대값을 초기화
        int min = score[0]; // 배열의 첫번째 값으로 최소값을 초기화

        for(int i=1; i<score.length;i++){//배열의 두번째 요소부터 읽기 위해서 변수 i의 값을 1로 초기화
            if(score[i]>max){
                max = score[i];
            }else if(score[i]<min){
                min = score[i];
            }
        }
        System.out.println("최대값:"+max);
        System.out.println("최소값:"+min);
    }
}
```
실행결과
```java
최대값:100
최소값:23
```
+ Math.random() 이용하기
```java
public class ArrayUse3 {
    public static void main(String[] args) {
        int[] numArr = new int[10];
        for(int i=0; i<numArr.length;i++){
            numArr[i] = i; // 배열을 0~9의 숫자로 초기화
            System.out.print(numArr[i]);
        }
        System.out.println();
        for(int i=0; i<100; i++){
            int n = (int)(Math.random() * 10); // 0~9 중 랜덤으로 한 값을 얻는다.
            //numArr[0]과 numArr[n]의 값을 서로 바꾼다.
            int temp = numArr[0];
            numArr[0] = numArr[n];
            numArr[n] = temp;
        }
        for(int i=0; i<numArr.length;i++){
            System.out.print(numArr[i]);
        }
    }
}
```
실행결과
```java
0123456789
7130862594
```
+ 임의의 값으로 배열 채우기
```java
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
```
실행결과(실행할 때마다 바뀜)
```java
[3, -1, 6, -4, 3, 22, 6, 3, 22, -4]
```
+ 버블정렬하기
```java
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
```
실행결과
```java
3615871906
3156718069
1356170689
1351606789
1315066789
1130566789
1103566789
1013566789
0113566789
```
+ 빈도수 구하기
```java
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
```
실행결과
```java
2040249764
0의 개수:2
1의 개수:0
2의 개수:2
3의 개수:0
4의 개수:3
5의 개수:0
6의 개수:1
7의 개수:1
8의 개수:0
9의 개수:1
```
