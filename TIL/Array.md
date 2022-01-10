### Array(배열)
---
## 1-1 Array(배열)이란
+ 배열: 같은 타입의 여러 변수들의 하나의 묶음
+ 여기서 중요한 점!! 같은 타입의 변수들로만 배열을 만들 수 있다.
+ 변수와 달리 배열은 각 저장공간이 연속적으로 배치되어 있다.
## 1-2 배열의 선언과 생성
 + 선언
   + `타입[] 변수이름; -> int[] score;`
   + `타입 변수이름[]; -> String name[];`
 + 생성
   + `타입[] 변수이름; //배열선언 -> int[] score;   
     변수이름 = new 타입[길이]; //배열 생성(실제 저장공간 생성) -> score new int[5];`

## 1-3 배열의 길이와 인덱스
+ 인덱스의 범위는 0부터 '배열길이-1'까지  
`score[3] = 100; // 배열 score의 4번째 요소에 100을 저장`    
`int value = score[3]; //배열 score의 4번째 요소에 저장된 값을 읽어서 value에 저장`
+ 배열의 상수 대신 변수나 수식도 사용할 수 있어 for문을 이용해서 간단히 할 수 있다.  
```java
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
        System.out.printf("score[%d]:%d\n",7,score[7]); //index의 범위를 벗어난 값
    }
}
```
⁕ index의 범위를 벗어났을 경우, 컴파일 시에는 아무런 문제가 없지만 실행 시에는 `Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException`에러발생

+ 배열의 길이는 양이 정수이여야 하며 최대값은 int타입의 최대값이다. 여기서 중요한 점!! 길이가 0인 배열도 생성이 가능하다. 
+ 배열은 값을 읽을 수만 있을 뿐 변경할 수 없다. -> 배열의 길이를 변결하는 방법: 더 큰 배열을 새로 생성, 기존 배열의 내용을 새로운 배열에 복사
+ 배열의 길이 -> 배열이름.length 
## 1-4 배열의 초기화
+ 배열은 생성과 동시에 자동적으로 자신의 타입에 해당하는 기본값으로 초기화되므로 따로 초기화를 해주지 않아도 되지만, 원하는 값을 저장하려면 각 요소마다 값을 지정해 줘야한다.  
  `int[] score = new int[]{40,30,20,50,10}; // 배열의 생성과 초기화를 동시에`  
  `int[] score = {40,30,20,50,10}; // new int[]는 생략 가능. 단, 메서드를 호출해야 할 때는 생략 불가능.`
 + 배열 출력시 print를 사용하지 않고 줄바꿈하지 않는 방벙은 "Arrays.toString(배열이름)"메서드를 사용하는 것이다. 이 메서드는 배열의 모든 요소를 [첫번째 요소, 두번째 요소,...]와 같은 형식의 문자열로 반환한다. 이 메서드를 사용하기 위해서는 `import.java.util.*;`를 추가해야 한다.
## 1-5 배열의 복사
+ for문을 이용한 배열 복사
  + `int[] temp = new int[arr.length*2];`기존 배열보다 길이가 2배인 배열을 생성
```java
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
        int[] temp = new int[arr.length*2]; //배열의 길이가 10인 int배열 temp가 생성되고, 배열 temp의 각 요소는 int의 기본값인 0으로 초기화 된다.

        //배열 arr에 저장된 값들을 배열 temp에 복사
        for(int i=0; i<arr.length;i++){
            temp[i] = arr[i];
        }
         arr = temp; //temp에 저장된 값을 arr에 저장 => 배열 arr과 배열 temp는 이름만 다를뿐 동일한 배열, 배열은 참조변수를 통해서만 접근할 수 있기 때문에 자신을 가리키는 참조변수가 없는 배열은 더 이상 사용할 수 없다. 따라서 arr이 가리키던 배열은 더 이상 사용할 수 없음.
        System.out.println("[변경후]");
        System.out.println("arr 배열의 길이:"+ arr.length);
        for(int i=0; i<arr.length;i++){
            System.out.println("arr["+i+"]:"+arr[i]);
        }
    }
}

```  

실행결과
``` java
[변경전]
arr 배열의 길이:5
arr[0]:1
arr[1]:2
arr[2]:3
arr[3]:4
arr[4]:5
[변경후]
arr 배열의 길이:10
arr[0]:1
arr[1]:2
arr[2]:3
arr[3]:4
arr[4]:5
arr[5]:0
arr[6]:0
arr[7]:0
arr[8]:0
arr[9]:0
```
+ `System.arraycopy()`를 이용한 배열의 복사
  + for문은 배열의 요소 하나하나에 접근해서 복사하지만, arraycopy()는 지정된 범위의 값들을 한 번에 통째로 복사한다. 즉, 배열의 복사는 for문보다 System.arraycopy()를 사용하는 것이 효율적이다.
```java
  public class Array3 {
    public static void main(String[] args) {
        char[] abc = {'A','B','C','D'};
        char[] num = {'0','1','2','3','4','5','6','7','8','9'};
        System.out.println(abc);
        System.out.println(num);

        //배열 abd의 num을 붙여서 하나의 배열(result)로 만든다.
        char[] result = new char[abc.length+num.length];
        System.arraycopy(abc,0,result,0,abc.length); // 배열 abc의 첫번째 요소부터 시작해서 abc.length개의 데이터를 배열 result의 첫번째 요소에 복사
        System.arraycopy(num, 0, result ,abc.length, num.length); // 배열 num의 첫번째 요소부터 시작해서 num.length개의 데이터를 배열 result의 abc.length길이 만큼 복사
        System.out.println(result);

        //배열 abc을  배열 num의 첫번째 위치부터 배열 abc의 길이만큼 복사
        System.arraycopy(abc,0,num,0,abc.length);
        System.out.println(num);

        //num의 인덱스6 위치에 3개를 복사
        System.arraycopy(abc, 0 , num, 6, 3);
        System.out.println(num);
    }
}

```
실행결과
```java
ABCD
0123456789
ABCD0123456789
ABCD456789
ABCD45ABC9

```
---


#### 🎁배열의 장점🎁
- 인덱스를 가지고 있어 바로 접근이 가능하다.
- 연속된 메모리공간에 존재하기 때문에 관리가 편하다.

#### 🎃배열의 단점🎃
- 삽입과 삭제가 어렵고 오래걸린다.
  > 원소를 삽입하거나 삭제할 경우, 연속된 메모리공간에 저장되기에 해당 원소 이후의 모든 원소들을 한칸씩 밀거나 당겨야한다.
- 배열의 크기를 수정 할 수 없다.
  > 배열은 처음 생성 할 때 크기를 지정하여 설정하기때문에 유연하게 변경하기에 어렵다.
  > 크기를 변경하기 위해서는 원하는 크기의 새로운 배열을 선언한 뒤 값을 복사해야한다.
- 연속된 메모리라서 중간에 데이터가 삭제되면 빈공간이 생기며 공간낭비가 발생한다.
  > 처음 배열을 선언할때 확실한 크기를 몰라 100으로 어림짐작하고 크기를 정하면 나중에 사용할때는 10을 사용해 공간 낭비가 발생하고, 또는 100이상을 필요로해하면 수정하는데에 불편함을 겪는다.

#### 🎈언제 사용하면 좋을까

데이터의 개수가 확실하고 데이터의 삭제,삽입이 적을때. 또는 검색을 자주 다루어야할때

