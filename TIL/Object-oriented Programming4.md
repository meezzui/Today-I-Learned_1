### 객체지향프로그래밍4
---
#### 제어자(modifier)
+ 클래스나 멤버변수와 메서드에 주로 사용되며, 하나의 대상에 대해서 여러 제어자를 조합하여 사용하는 것이 가능
+ 단, 접근 제어자는 한 번에 네가지 중 하나만 선택해서 사용 할 수 있다.(예-하나의 대상에 대해서 public과 private을 함께 사용 할 수 없다.)
+ 접근 제어자 종류: public, protected, default, private
+ 그 외 제어자 종류: static, final, abstract, native, transient, synchronized, volatile, strictfp

#### 1. static - 공통적인
+ 클래스변수(static멤버변수)는 인스턴스에 관계없이 같은 값을 갖는다. why? 하나의 변수를 모든 인스턴스가 공유하기 때문에
+ static이 붙은 멤버변수와 메서드, 초기화 블럭(static{ })은 인스턴스를 생성하지 않고도 사용할 수 있다.
+ static이 사용될 수 있는 곳 - 멤버변수, 메서드 초기화 블럭
+ static + 멤버변수
  + 모든 인스턴스에 공통적으로 사용되는 클래스변수가 된다.
  + 클래스변수는 인스턴스를 생성하지 않고도 사용 가능하다.
  + 클래스가 메모리에 로드될 때 생성된다.
+ static + 메서드
  + 인스턴스를 생성하지 않고도 호출이 가능한 static 메서드가 된다.
  + static메서드 내에서는 인스턴스멤버들을 직접 사용할 수 없다.
```java
class StaticTest{
    static int width = 200; // 클래스변수(static 변수)
    static int height = 300; // 클래스변수(static 변수)
    
    static {
        // static변수의 초기화 수행, 클래스가 메모리에 로드될 때 단 한번만 수행됨.
    }
    static int max(int a, int b){ // 클래스 메서드(static 메서드)
        return a > b ? a : b ;
    }
}
```

#### 2. final - 마지막의, 변경될 수 없는
+ 거의 모든 대상에 사용될 수 있다.
+ 대표적인 final클래스로는 String과 Math가 있다.
+ final이 사용될 수 있는 곳 - 클래스, 메서드, 멤버변수, 지역변수
+ final + 클래스 
  + 변경될 수 없는 클래스, 확장될 수 없는 클래스가 된다. 그래서 final로 지정된 클래스는 다른 클래스의 조상이 될 수 없다.
+ fianl + 메서드
  + 변경될 수 없는 메서드, final로 지정된 메섣느느 오버라이딩을 통해 재정의 될 수 없다.
 + final + 멤버변수/자역변수
   + 변수 앞에 final이 붙으면, 값을 변경할 수 없는 상수가 된다.
 ```java
  final class FinalTest{ // 조상이 될 수 없는 클래스
      final int max_size = 10; // 값을 변경할 수 없는 멤버변수(상수)
      
      final void getMaxSize(){// 오버라이딩할 수 없는 메서드(변경불가)
        final int lv = max_size; // 값을 변경할 수 없는 지역변수(상수)
        return max_size;
      }
  }
  ```
+ 생성자를 이용한 final멤버 변수의 초기화
  + final이 붙은 변수는 상수이므로 일반적으로 선언과 초기화를 동시에 하지만, 인스턴스변수의 경우 생성자에서 초기화 되도록 할 수 있다.
  + 클래스 내에 매개변수를 갖는 생성자를 선언하여, 인스턴스를 생성할 떼 final이 붙은 멤버변수를 초기화하는데 필요한 값을 생성자의 매개변수로부터 제공받는 것이다.
  + 이를 통해 각 인스턴스마다 final이 붙은 멤버변수가 다른 값을 갖도록 하는 것이 가능하다.
  ```java
      public class ObjectOriented11 {
      public static void main(String[] args) {
            Card c = new Card("Orange",10);
            System.out.println(c.KIND);
            System.out.println(c.number);
            System.out.println(c); // System.out.println(c.toString());
        }
    }
    class Card{
        final int number; //상수지만 선언과 함께 초기화 하지 않고
        final String KIND; // 생성자에서 단 한번만 초기화할 수 있다.
        static int width = 100;
        static int  height = 200;

        Card(String kind, int num){ //매개변수로 넘겨받은 값으로 KIND와 number를 초기화한다.
            KIND = kind;
            number = num;
        }
        Card(){ //활성화 안 됐음
            this("HEART",1);
        }
        public String toString(){
            return KIND + " "+ number;
        }
    }
    ```
    실행결과
    ```java
    Orange
    10
    Orange 10
    ```
    
#### 3. abstract- 미완성의
+ 메서드의 선언부만 작성하고 실제 수행내용은 구현하지 않은 추상 메서드를 선언하는데 사용된다.
+ 클래스에 사용되어 클래스 내에 추상메서드가 존재한다는 것을 쉽게 알 수 있게한다.
+ abstract이 사용될 수 있는 곳: 클래스, 메서드
+ abstract + 클래스 
  + 클래스 내에 추상 메서드가 선언되어 있음을 의미
+ abstract + 메서드
  + 선언부만 작성하고 구현부는 작성하지 않은 추상 메서드임을 알린다.
```java
abstract class AbstractTest{//추상 클래스(추상 메서드를 포함한 클래스)
  abstract void move(); // 추상 메서드(구현부가 없는 메서드)
}
```
+ 추상 메서드가 없는 클래스, 즉 완성된 클래스도 abstract을 붙여서 추상클래스를 만드는 경우도 있다. 인스턴스를 생성해봐야 할 수 있는 것이 없는 클래스 앞에 abstract을 붙여 인스턴스를 생성하지 못하게 한다.

#### [접근 제어자]
+ 멤버 또는 클래스에 사용되어, 해당하는 멤버 또는 클래스를 외불에서 접근하지 못하도록 제한하는 역할을 한다.
+ 클래스나 멤버변수, 메서드, 생성자에 접근 제어자가 있지 않다면, 접근 제어자가 default임을 뜻한다.
+ 접근 제어자 종류(접근 범위 넓은 순)
  + public : 접근 제한이 전혀 없다. 
  + protected : 같은 패키지 내에서, 그리고 다른 패키지의 자손클래스에서 접근이 가능하다.
  + default : 같은 패키지 내에서만 접근이 가능하다.
  + private : 같은 클래스 내에서만 접근이 가능하다.
  
  # <img src="https://t1.daumcdn.net/cfile/tistory/22703F5057E60F262E">
  
+ 대상에 따라 사용 가능한 접근 제어자
  + 클래스 : public, (default)
  + 메서드/멤버변수 : public, protected, (default), private
  + 지역변수 : 없음
+ 접근 제어자를 사용하는 이유는 클래스 내부에 선언된 데이터를 보호하기 위해 즉, "캡슐화"를 위함이다.
+ 외부에서 접근할 필요가 없는 멤버들을 private으로 지정하여 외부에 노출시키지 않음으로써 복잡성을 줄일 수 있다.
+ 'get멤버변수이름': 멤버변수의 값을 가져오는 일을 함.(값 읽음)
+ 'set멤버변수이름': 조건에 맞는 값일 때만 멤버변수의 값을 변경함.(값 변경)
```java
public class ObjectOriented12 {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("연습생");
        student.setAge(22);

        String name= student.getName();
        int age = student.getAge();

        System.out.println("이름:"+ name);
        System.out.println("나이:"+ age);
    }
}
class Student{
    //은닉된 멤버변수 --> 현재 블록안에서만 접근 가능
    private String name;
    private int age;

    //은닉된 멤버 변수에 값을 읽는 방법
    public String getName() {
        return name;
    }
    //은닉된 멤버 변수에 값을 넣은 방법 --> 메소드를 사용
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```
실행결과
```java
이름:연습생
나이:22
```
+ 생성자의 접근 제어자
  + 생성자의 접근 제어자를 private으로 지정하면 외부에서 생성자에 접근할 수 없으르므로 인스턴스를 생성할 수 없게 된다.
  +  그래도 클래스 내부에서는 인스턴스를 생성할 수 있다. 대신 인스턴스를 생성해서 반환해주는 public메서드를 제공함으로써 외부에서 이 클래스의 인스턴스를 사용하도록 할 수 있다.
  +  여기서 중요한 것!!!! 이 메서드는 public인 동시에 static이어야 한다.
  ```class Singleton{
      private static Singleton = s = new Singleton(); // getInstance()에서 사용될 수 있도록 인스턴스가 미리 생성되어야 하므로 static이어야 함.
      private Singleton(){
        ....
      }
      //인스턴스를 생성하지 않고도 호출할 수 있어야 하므로 static이어야 한다.
      public static Singleton getInstance(){
          return s;
      }
      ...
  }
  ```
  🎈 이처럼 생성자를 통해 직접 인스턴스를 생성하지 못하게 하고 public메서드를 통해 인스턴스에 접근하게 함으로써 사용할 수 있는 인스턴스의 개수를 제한할 수 있다.  
   
### 🎁 사용가능한 제어자의 조합 정리 🎁
  + 클래스 : public, (default), final, abstract 
  + 메서드 : 모든 접근 제어자, final, abstract, static
  + 멤버변수 : 모든 접근 제어자, final, static
  + 지역변수 : final
### 🎁 제어자 조합시 주의사항 🎁
  + 메서드에 static과 abstract을 함께 사용할 수 없다. - static메서드는 몸통이 있는 메서드에만 사용할 수 있기 때문이다.
  + 클래스에 abstract와 final을 동시에 사용할 수 없다. - 클래스에 사용되는 final은 클래스를 확장할 수 없다는 의미이고 abstract은 상속을 통해서 완성되어야 한다는 의미이므로 서로 모순된다.
  + abstract메서드의 접근 제어자가 private일 수 없다. - abstract메서드는 자손클래스에서 구현해주어야 하는데 접근 제어자가 private이며느 자손클래스에서 접근할 수 없기 때문이다.
  + 메서드에 private과 final을 같이 사용할 필요는 없다. - 접근 제어자가 private인 메서드는 오버라이딩될 수 없기 때문에 둘 중 하나만 사용해도 의미가 충분하다.







