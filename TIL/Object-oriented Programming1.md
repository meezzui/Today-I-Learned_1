### 객체지향프로그래밍1
---
#### 객체지행언어의 장점
  + 코드의 재사용성이 높다. 새로운 코드를 작성할 때 기존의 코드를 이용하여 쉽게 작성할 수 있다.
  + 코드의 관리가 용이하다. 코드간의 관계를 이용해서 적은 노력으로 쉽게 코드를 변경할 수 있다.
  + 신뢰성이 높은 프로그래밍을 가능하게 한다. 제어자와 메서드를 이용하여 데이터를 보호, 코드 중복을 제거하여 오작동 방지
#### 클래스와 객체(객체지향이론 관점)
  + 클래스의 정의: 클래스란 객체를 정의해 놓은 것
  + 클래스의 용도: 클래스는 객체를 생성하는데 사용됨
  + 객체의 정의: 실제로 존재하는 것. 사물 또는 개념(책상, 의자, 자동차, 수학공식, 프로그램 에러)
  + 객체의 용도: 객체가 가지고 있는 기능과 속성에 따라 다름
+ 객체와 인스턴스
  + 클래스의 인스턴스화: 클래스로부터 객체를 만드는 과정
  + 클래스의 인스턴스: 어떤 클래스로부터 만들어진 객체(TV클래스로부터 만들어진 객체를 TV클래스의 인스턴스)
+ 객체의 구성요소 - 속성과 기능
  + 객체 = 속성+기능, 객체가 가지고 있는 속성과 기능 -> 그 객체의 멤버(member) 즉, 클래스로부터 객체를 생성하면, 클래스에 정의된 속성과 기능을 가진 객체가 만들어지는 것이다.
  + 속성(property): 멤버변수(member variable), 특성(attribute), 필드(field), 상태(state) -> 크기,길이,높이,볼륨,채널
  + 기능(function): 메서드(method), 함수(function), 행위(behavior) -> 켜기, 끄기, 볼륨 높/낮추기, 채널 변경
  ```java
  class Tv{
    String color;
    boolean power;
    int channel;
    
    void power() {power = !power;} //power의 값이 true면 false로 false면 true로 변경하는 역할
    void channelUp() {channel++;}
    void channelDown() {channel--;}
  }
  ```
  + 인스턴스의 생성과 사용
  ```java
  클래스명 변수명; // 클래스의 객체를 참조하기 위한 참조변수 선언
  변수명 = new 클래스명(); // 클래스의 객체를 생성 후, 객체의 주서를 참조변수에 저장
  Tv t;
  t = new Tv();
  ```
  🎁인스턴스는 참조변수를 통해서만 다룰 수 있으며, 참조벼수의 타입은 인스턴스의 타입과 일치해야 한다.   
  🎁같은 클래스로부터 생성되었을지라도 각 인스턴스의 속성(멤버변수)은 서로 다른 값을 유지할 수 있으며, 메서드의 내용은 모든 인스턴스에 대해 동일하다.    
  🎁참조변수에는 하나의 값(주소)만이 저장될 수 있으므로 둘 이상의 참조변수가 하나의 인스턴스를 가리키는(참조하는) 것은 가능하지만 하나의 참조변수로 여러 개의 인스턴스를 가리키는 것은 가능하지 않다.
+ 객체 배열
  + 객체 배열은 참조변수들을 하나로 묶은 참조 변수 배열이며 객체 배열 안에 객체가 저장되는 것이 아니라 객체의 주소가 저장된다.(참조변수 기본값 null로 초기화)
  ```java
    public class ObjectOriented1 {
      public static void main(String[] args) {
          Tv[] tvArr = new Tv[3]; // 길이가 3인 Tv객체 배열

          //Tv객체를 생성해서 Tv객체 배얄의 각 요소에 저장
          for(int i=0; i< tvArr.length;i++){
              tvArr[i] = new Tv();
              tvArr[i].channel = i+10; // tvArr[i]의 channel에 i+10을 저장
          }
          for(int i=0; i< tvArr.length;i++){
              tvArr[i].channelUp(); // tvArr[i]의 메서드를 호출, 채널 1증가
              System.out.printf("tvArr[%d].channel=%d\n",i,tvArr[i].channel);
          }
      }
  }
  class Tv{
      String color;
      boolean power; //전원상태
      int channel;

      void power(){power = !power;} //power의 값이 true면 false로 false면 true로 변경하는 역할
      void channelUp() {++channel;}
      void channelDown() {--channel;}
  }
```
  실행결과
  ```java
    tvArr[0].channel=11
    tvArr[1].channel=12
    tvArr[2].channel=13
  ```
+ 클래스의 또 다른 정의(프로그래밍적 관점)
  + 변수: 하나의 데이터를 저장할 수 있는 공간
  + 배열: 같은 종류의 여러 데이터를 하나의 집합으로 저장할 수 있는 공간
  + 구조체: 서로 관련된 여러 데이터를 종류에 관계없이 하나의 집합으로 저장할 수 있는 공간
  + 클래스: 데이터와 함수의 결합(구조체+함수)
#### 변수와 메서드
+ 선언위치에 따른 변수의 종류
  + 클래스 변수, 지역변수, 인스턴스 변수가 있다.
  ```java
  class Variable{
  //클래스 영역
    int iv; // 인스턴스 변수
    static int cv; // 클래스 변수(static 변수)
    
    void method(){
     //메서드 영역
        int lv =0; //지역변수
    }
  }
  ```
  + 지역 변수  
   ♪ 멤버변수를 제외한 나머지 변수들    
   ♪ 메서드 내에 선언되어 메서드 내에서만 사용 가능  
   ♪ 메서드가 종료되면 소멸되어 사용 할 수 없음  
   ♪ for문 또는 while문의 블럭 내에 선언된 지역변수는 그 블럭 내에서만 사용가능  
  + 클래스 변수  
   ♪ 멤버변수 중 static이 붙은 것  
   ♪ 인스턴스마다 독립적인 저장공간을 갖는 인스턴스변수와 달리, 클래스변수는 모든 인스턴스가 공통된저장공간(변수)을 공유하게 됨  
   ♪ 즉, 한 클래스의 모든 인스턴스들이 공통적인 값을 유지해야하는 속성의 경우 클래스변수로 선언해야 함  
   ♪ 인스턴스를 생성하지 않고도 바로 사용 가능  
   ♪ '클래스이름.클래스변수' 형식으로 사용  
   ♪ public을 앞에 붙이면 전역변수(같은 프로그램 내에서 어디서나 접근 가능)의 성격을 지님  
  + 인스턴스 변수  
   ♪ 멤버변수 중 static이 붙지 않은 것  
   ♪ 클래스 영역에 선언  
   ♪ 인스턴스 변수의 값을 읽어 오거나 저장하기 위해서는 먼저 인스턴스를 생성해야 함
   ♪ 독립적인 공간을 가지므로 서로 다른 값을 가질 수 있음  
   ♪ 고유한 상태를 유지해야하는 경우 인스턴스 변수로 선언
  + 클래스변수와 인스턴스 변수(카드게임 예시)
  ```java
  class Card{
    //카드 인스턴스는 자신만의 무늬와 숫자를 유지하고 있어야 하므로 인스턴스 변수로 선언
    String kind; // 무늬
    int number; // 숫자
    // 카드의 폭가 높이는 모든 인스턴스가 공통적으로 같은 값을 유지해야 하므로 클래스 변수로 선언
    static int width = 100; // 폭
    static int height = 250; // 높이
  }
  ```
  🎈 인스턴스변수는 인스턴스가 생성될 때 마다 생성되므로 인스턴스마다 각기 다른 값을 유지할 수 있지만, 클래스 변수는 모든 인스턴스가 하나의 저장공간을 공유하므로 항상 공통된 값을 갖는다.
+ 메서드
  + 특정 작업을 수행하는 일련의 문장들을 하나로 묶은 것
  + 메서드를 사용하는 이유  
    ♪ 높은 재사용성
    ♪ 중복된 코드 제거
    ♪ 프로그램의 구조화 : 문장들을 작업단위로 나눠서 여러 개의 메서드에 담아 프로그램의 구조를 단순화 시킨다.
+ 메서드의 선언과 구현
  + 메서드 선언부: 메서드가 작업을 수행하기 위해 어떤 값들을 필요로 하고 작업의 결과로 어떤 타입의 값을 반환하는지에 대한 정보 제공
  ```java
  반환타입(출력) 메서드이름 (타입 변수명, 타입 변수명,...) // (타입 변수명, 타입 변수명,...) : 매개변수 선언(입력)
  int add(int x, int y){
  //구현부
    int result = x+y;
    return result;
  }
  ```
  + 매개변수 선언: 메서드가 작업을 수행하는데 필요한 값들(입력)을 제공받기 위한 것
  + 매서드의 이름: add처럼 동사인 경우가 많다
  + 반환타입: 반환값이 없는 경우 반환타입을 void로 적어야 한다. 반환타입이 void가 아닌 경우 구현부 안에 'return 반환값'이 반드시 포함되어야 한다.
+ 메서드의 호출
```java
메서드이름(값1.값2,...); // 메서드 호출 방법
int result = add(3,5);// int add(int x, int y)를 호출하고 result에 저장
```
+ 메서드의 실행흐름: 같은 클래스 내의 메서드끼리는 참조변수를 사용하지 않고도 서로 호출이 가능하지만 static메서드는 같은 클래스 내의 인스턴스 메서드를 호출할 수 없다.
```java
public class ObjectOriented2 {
    public static void main(String[] args) {
        MyMath mm = new MyMath();
        long result1 = mm.add(5L, 3L);
        long result2 = mm.subtract(5L, 3L);
        long result3 = mm.multiply(5L, 3L);
        double result4 = mm.divide(5L, 3L);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
    }
}
class MyMath{
    long add(long a, long b){
        long result = a+b;
        return result;
    }
    long subtract(long a,long b){
        return a-b;
    }
    long multiply(long a, long b){
        return a * b;
    }
    double divide(double a, double b){
        return a/b;
    }
}

```
실행결과
```java
8
2
15
1.6666666666666667
```
#### JVM의 메모리 구조
+ 메서드 영역(method area) : 프로그럄 실행 중 어떤 클래스가 사용되면 클래스파일(*.class)을 읽어서 클래스에 대한 정보를 저장. 이때  그 클래스의 클래스변수도 이 영역에 함께 생성됨
+ 힙(heap) : 인스턴스가 생성되는 공간. 프로그램 실행 중 생성되는 인스턴스는 모두 이곳에 생성됨
+ 호출스택(call stack 또는 execution stack) : 메서드의 작업에 필요한 메모리 공간을 제공. 메서드가 호출되면 호출스택에 호출된 메서드를 위한 메로리가 할당됨 -> 메모리는 메서드가 작업을 수행하는 동안 지역변수(매개변수 포함)들과 연산의 중간결과를 저장 -> 메서드가 작업을 마치면 사용했던 메모리를 반환하고 스택에서 제거됨
#### 기본형 매개변수와 참조형 매개변수
+ 기본형 매개변수 : 변수의 값을 읽기만 할 수 있다.
```java
static void change(int x){//기본형 매개변수
  x = 1000;
  System.out.println("change x= "+x);
}
```
+ 참조형 매개변수: 변수의 값을 읽고 변경할 수 있다.
```java
public class ObjectOriented3 {
    public static void main(String[] args) {
        Data d = new Data();
        d.x=10;
        System.out.println("main 함수: x = "+d.x);

        change(d);
        System.out.println("바뀐 main 함수 : x = "+d.x);
    }

    static void change(Data d){
        d.x = 1000;
        System.out.println("change(): x="+d.x);
    }
}
class Data{int x;}
```
실행결과
```java
main 함수: x = 10
change(): x=1000
바뀐 main 함수 : x = 1000
```
+ 매개변수 타입이 배열인 경우도 참조형 매개변수이다.
```java
public class ObjectOriented4 {
    public static void main(String[] args) {
        int[] arr = new int[] {3,4,2,5,6,7};

        printArr(arr);// 배열의 모든 요소 출력
        sortArr(arr); //배열 정렬
        printArr(arr); // 정렬 후 결과 출력
        System.out.println("sum="+ sumArr(arr));//배열의 총합 출력
    }
    static void printArr(int[] arr){//배열의 모든 요소 출력
        System.out.print("[");
        for(int i=0; i<arr.length; i++){
            System.out.print(i+",");
        }
        System.out.println("]");
    }
    static int sumArr(int[] arr) {//배열의 모든 요소의 합 반환
        int sum = 0;
        for(int i=0; i<arr.length;i++){
            sum += arr[i];
        }
        return sum;
    }
    static void sortArr(int[] arr){// 배열을 오름차순으로 정렬
        for(int i=0; i<arr.length-1;i++){
            for(int j=0; j<arr.length-1-i; j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] =temp;
                }
            }
        }
    }
}
```
실행결과
```java
[0,1,2,3,4,5,]
[0,1,2,3,4,5,]
sum=27
```
+ 반환값이 있는 메서드를 반환값이 없는 메서드로 바꾸는 방법
```java
int add(int a, int b){            void add(int a, int b, int[] result){
  return a+b;           <---->        result[0] = a+b; //매개변수로 넘겨받은 배열에 연산결과를 저장
 }                                 }  
```
#### 재귀호출(recursive cell)
+ 재귀호출 : 메서드의 내부에서 메서드 자신을 다시 호출하는 것. 재귀호출은 무한히 자신을 호출하기 때문에 조건문과 함께 사용되어야 한다.
```java
//n을 1씩 감소시키면서 n=0일떼 메서드 종료
void method(int n){
  if(n==0) return; //n=0일때 메서드 종료
  Sysytem.out.println(n);
  method(--n); // 재귀호춯
}
```
+ 재귀호출의 대표적인 예- 팩토리얼
```java
public class ObjectOriented5 {
    public static void main(String[] args) {
        int result = factorial(4);
        System.out.println(result);
    }

    static int factorial(int n){
        int result =0;
        if(n==1){
            result =1;
        }else {
            result = n * factorial(n-1);//다시 메서드 자신을 호출
        }
        return result;
    }
}
```
실행결과
`24`
+ 클래스 메서드(static 메서드)와 인스턴스 메서드
  + 클래스 영역에 선언된 변수를 멤버 변수. 멤버변수 = 클래스변수(static변수) + 인스턴스변수(static이 안 붙는 것)
  + 클래스를 설계할 때, 멤버 변수 중 모든 인스턴스에 공통으로 사용하는 것에 static을 붙인다.
  + 클래스 변수(static)는 인스턴스를 셍성하지 않아도 사용할 수 있다.
  + 클래스 메서드(static 메서드)는 인스턴스 변수를 사용할 수 없다.
  + 메서드 내에서 인스턴스 변수를 사용하지 않는다면, static을 붙이는 것을 고려한다.(작업중에 인스턴스변수가 필요하다면 static을 붙일 수 없는데 필요없다면 static을 붙일 수 있어)
  ```java
      public class ObjectOriented6 {
        public static void main(String[] args) {
            //클래스매서드 호출. 인스턴스 생성없이 호출가능
            System.out.println(MyMath2.add(50,45));
            System.out.println(MyMath2.subtract(50,45));
            System.out.println(MyMath2.multiply(50,45));
            System.out.println(MyMath2.divide(50.0,45.0));

            MyMath2 myMath2 = new MyMath2(); // 인스턴스 생성
            myMath2.a = 30;
            myMath2.b = 20;
            // 인스턴스메서드는 객체생성 후에만 호출이 가능
            System.out.println(myMath2.add());
            System.out.println(myMath2.subtract());
            System.out.println(myMath2.multiply());
            System.out.println(myMath2.divide());
        }
    }
    class MyMath2{
        long a,b;

        //인스턴스변수 a,b만을 이용해서 작업하므로 매개변수가 필요없다.
        long add(){return a+b;} //a,b는 인스턴스변수
        long subtract(){return a-b;}
        long multiply(){return a*b;}
        double divide(){return a/b;}

        //인스턴스변수와 관계없이 매개변수만으로 작업 가능
        static long add(long a,long b){return a+b;} //a,b는 지역변수
        static long subtract(long a,long b){return a-b;}
        static long multiply(long a,long b){return a*b;}
        static double divide(double a, double b){return a/b;}
    }
  ```
   실행결과
  ```java
    95
    5
    2250
    1.1111111111111112
    50
    10
    600
    1.0
  ```
  #### 클래스 멤버와 인스턴스 멤버간의 참조와 호출
  🎁 static메서드는 인스턴스 메서드를 호출할 수 없다. 변수도 마찬가지로 static메서드에서는 인스턴스변수를 사용할 수 없다.
  ```java
  class TestClass{
    void instanceMethod1(){} // 인스턴스메서드1
    static void staticMethod(){} // static메서드
    
    void instanceMethod2(){//인스턴스메서드2
      instanceMethod1(); // 인스턴스메서드1 호출
      staticMethod(); //static 메서드 호출
     } 
      staitc void staticMethod2(){ //static 메서드 
      instanceMethod1(); // 에러!!! 인스턴스메서드는 호출 불가
      staticMethod(); // static메서드는 호출 가능
      }
  }
  ```
