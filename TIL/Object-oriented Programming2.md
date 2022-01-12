### 객체지향프로그래밍2
---
+ 오버로딩(Overloding)과 오버라이딩(Overriding)
  + 오버로딩(Overloding)    
    ♪ 한 클래스 내에 같은 이름의 메서드들을 여러 개 정의 하는 것  
    ♪ 같은 이름의 메소드를 여러개 정의하는 것  
    ♪ 오버로딩의 조건  
      ⁎ 메서드 이름이 같아야 한다.  
      ⁎ 매개변수의 개수 또는 타입이 달라야 한다.
      ```java
      // 오버로딩 가능
      long add(int a, long b){return a+b}
      long add(long a, int b){return a+b}
      ```
    ♪ 중요한 포인트는 오버로딩은 하나의 클래스에서 같은 이름의 메소드들을 여러 개 가질 수 있다는 점이다. 대표적인 예는 바로 자바의 System.out.println()문같은 출력문이다. 
    이런 프린트문들은 기본적으로 언어에서 클래스를 내장해놓았다. 이 프린트문에 매개변수의 타입이 어떤걸 넣더라도 다 출력 값을 콘솔에 띄울 수 있다.  
    예를 들면 System.out.println(1+1) 이나 String a = "아무거나"라고 변수를 정의하고 이 a를 출력하면 콘솔에서는 2 나 아무거나 가 출력된다. 그 매게변수가 문자형이든 정수형이든 같은 이름의 메소드를 안에 여러개 정의해놨기 때문에 매게 변수의 타입에 따라 "어? 정수형이 들어왔네? 그럼 이 메소드","어? 문자형이 들어왔네? 그럼 이 메소드"이런식으로 다르게 출력할 수 있는 것이다.  
    ♪ 오버로딩의 장점
      1. 같은 기능을 하는 메소드를 하나의 이름으로 사용할 수 있다.
      2. 메소드의 이름을 절약할 수 있다.
  + 오버라이딩(Overriding)  
    ♪ 상속에서 나온 개념으로 상위 클래스(부모 클래스)의 메소드를 하위 클래스(자식 클래스)에서 재정의 하는 것  
    ♪ 상속받은 메소드를 그대로 사용할 수도 있지만, 자식 클래스에서 상황에 맞게 변경해야하는 경우 오버라이딩할 필요가 생긴다.  
    ♪ 오버라이딩 조건  
      ⁎ 자식 클래스에서는 오버라이딩하고자 하는 메소드의 이름, 매개변수, 리턴 값이 모두 같아야 한다.  
    ♪ 오버라이딩에서 접근 제어자를 설정하는 규칙  
    1. 자식 클래스에서 오버라이딩하는 메소드의 접근 제어자는 부모 클래스보다 더 좁게 설정할 수 없다.  
      ⁎ 부모클래스의 접근제어자는 default로 설정되어 있다. 여기서 자식 클래스들은 default보다 같거나 더 넓은 범위의 접근제어자만 설정할 수 있으므로 default, protected, public 이 세 개의 접근 제어자는 사용이 가능하다.
    2. 예외(Exception)는 부모 클래스의 메소드 보다 많이 선언할 수 없다.  
      ⁎ 부모 클래스에서 어떤 예외를 throws 한다고 하면, 자식 클래스에서는 그 예외보다 더 큰 범위의 예외를 throws할 수 없다는 것이다.
    3. static메소드를 인스턴스의 메소드로 또는 그 반대로 바꿀 수 없다.  
      ⁎ 부모 클래스의 static메소드를 자식에서 같은 이름으로 정의할 수 있지만 이것은 다시 정의하는 것이 아니라 같은 이름의 static메소드를 새로 정의하는 것이다.  
  🎁 오버로딩(Overloding) vs 오버라이딩(Overriding) 차이점🎁  
  🎗 접근제어자  
  오버라이딩 :부모 클래스의 메소드의 접근 제어자보다 더 넓은 범위의 접근 제어자를 자식 클래스의 메소드에서 설정할 수 있다.  
  오버로딩 : 모든 접근 제어자를 사용할 수 있다.  
  🎗 리턴형  
  오버라이딩 : 동일해야 한다.   
  오버로딩 : 달라도 된다.  
  🎗 메소드명    
  오버라이딩 : 동일해야 한다.   
  오버로딩 : 동일해야 한다.   
  🎗 매개변수  
  오버라이딩 : 동일해야 한다.   
  오버로딩 : 달라야만 한다.   
  🎗 적용범위   
  오버라이딩 : 상속관계에서 적용   
  오버로딩 : 같은 클래스 내에서 적용 
+ 생성자(Constructor)
  + 인스턴스가 생성될 때 호출되는 '인스턴스 초기화 메서드'이다.
  + 클래스 내에서 선언된다. 
  + 생성자도 오버로딩이 가능하므로 하나의 클래스에 여러개의 생성자가 존재할 수 있다.
  + 🎈메서드와 생성자의 차이점🎈 생성자는 리턴값이 없다. void를 적는 것이 아니라 아무것도 안 적는다.
  + 생성자의 조건
    ♪ 생성자의 이름은 클래스의 이름과 같아야 한다.
    ♪ 셍성자는 리턴 값이 없다.
  + 생성자 기본형태 `클래스이름(){}`
  + 매개변수가 있는 생성자
  ```java
      public class ObjectOriented7 {
        public static void main(String[] args) {
            Car car = new Car("blue","auto",4);
            System.out.println("car의 색깔:"+car.color + " 기어타입:"+car.gearType+" 문 개수:"+car.door);
        }
    }
    class Car{
        String color;
        String gearType; // 변속기 종류 = auto, manual
        int door; // 문의 개수

        Car(String c, String g, int d){
            color = c;
            gearType =g;
            door = d;
        }
    }
  ```
   실행결과 `car의 색깔:blue 기어타입:auto 문 개수:4`
   + 생성자에서 다른 생성자 호출하기
     ♪ 생성자의 이름으로 클래스이름 대신 this를 사용한다.
     ♪ 한 생성자에서 다른 생성자를 호출할 때는 반드시 첫 줄에서만 호출이 가능하다.
     ```java
           public class ObjectOriented8 {
          public static void main(String[] args) {
              Cars cars = new Cars("pink");
              System.out.println("car의 색깔:"+cars.color + " 기어타입:"+cars.gearType+" 문 개수:"+cars.door);
          }
      }
      class Cars{
          String color;
          String gearType; // 변속기 종류 = auto, manual
          int door; // 문의 개수

          Cars(String color){
              this(color,"manual",3); //기본지정
          }
          Cars(String color, String gearType, int door){
              this.color = color;
              this.gearType =gearType;
              this.door = door;
          }
      }
     ```
      실행결과 `cars의 색깔:pink 기어타입:manual 문 개수:3`
      
     🎈 this : 인스턴스 자신을 가리키는 참조변수, 인스턴스의 주소가 저장되어 있다. 모든 인스턴스메서드에 지역변수로 숨겨진 채로 존재한다.  
     🎈 this(), this(매개변수) : 생성자, 같은 클래스의 다른 생성자를 호출할 때 사용한다.
    
      
  
