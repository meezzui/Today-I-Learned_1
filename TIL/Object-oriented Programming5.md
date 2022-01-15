### 객체지향프로그래밍5
--- 
#### 다형성(polymorphism)
+ 부모클래스 타입의 참조변수로 자식클래스의 인스턴스를 참조할 수 있도록 하는 것
```java
class Tv{
 boolean power;
 int channel;
 
 void power(){ power = !power;}
 void channelUp{ ++channel;}
 void channelDown{--channel;}
}
class CaptionTv extends Tv{
 String text;
 void caption(){ }
}
```
+ Tv클래스와 CaptionTv클래스가 위와 같이 정의되어 있을 때, 이 두 클래스의 인스턴스를 생성하고 사용하기 위해서는 아래와 같이 해야한다.
```java
Tv t = new Tv();
CaptionTv c = new CaptionTv();
```
+ 이처럼 Tv인스턴스를 다루기 위해서는 Tv타입의 참조변수를 사용하고, CaptionTv인스턴스를 다루기 위해서는 CaptionTv타입의 참조변수를 사용했다. 
+ 그러나 상속관계에 있을 경우, 부모 클래스 타입의 참조변수로 자손 클래스의 인스턴스를 참조하도록 하는 것도 가능하다.  
`Tv t = new CaptionTv(); // 부모 타입의 참조변수로 자손 인스턴스 참조`
+ `CaptionTv c = new CaptionTv();` 와 ` Tv t = new CaptionTv();` 차이점: ` Tv t = new CaptionTv();` 이렇게 참조변수 t로는 CaptionTv인스턴스의 모든 멤버를 사용할 수 없다. 즉, Tv클래스의 멤버들만 사용할 수 있다.(t.text 또는 t.caption() 사용 불가)  
   🎈 결론적으로 둘 다 같은 타입의 인스턴스지만 참조변수의 타입에 따라 사용할 수 있는 멤버의 개수가 달라진다.  
   여기서 중요한 점!!! 반대로 자식타입의 참조변수로 부모타입의 인스턴스를 참조하는 것은 불가능하다.

#### 참조변수의 형변환
+ 서로 상속관계에 있는 클래스 사이에서만 형변환이 가능하다.
+ `자식타입-> 조상타입(업케스팅): 형변환 생략가능` `자식타입 -> 조상타입(다운캐스팅): 형볌환 생략불가`
```java
class Car{
  String color;
  int door;
  void drive(){//운전기능
    System.out.println("drive, Brrr");
  }
  void stop(){//멈추는 기능
    System.out.println("stop it!!");
  }
}
class FireEngine extends Car{//소방차
  void water(){//물 뿌리는 기능
    System.out.println("water");
  }
}
class Ambulance extends Car{//엠뷸런스
  void water(){//사이렌 기능
    System.out.println("사이렌~~");
  }
}
```
+ Car타입 참조변수와 FireEngine타입 참조변수 간의 형변환
```java
Car car = null;
FireEngine fe = new FireEngine();
FireEngine fe2 = null;

car = fe; // car = (Car)fe;에서 형변환 생략됨. 업캐스팅
fe2 = (FireEngine)car; // 형변환 생략불가. 다운캐스팅
```
+ 형변환은 타입을 변환하는 것이지 인스턴스를 변환하는 것은 아니기 때문에 참조변수의 형변환은 인스턴스에 아무런 영향을 미치지 않는다.

#### instanceof 연산자
+ 참조변수가 참조하고 있는 인스턴스의 실제 타입을 알아보기 위해 instanceof연산자를 사용한다.
+ 주로 조건문에 사용되며 instanceof 왼쪽에는 참조변수, 오른쪽에는 타입(클래스명)이 피연산자로 위치한다.
+ 연산의 결과로 true/false 중의 하나를 반환한다.
```java
void doWork(Car c){ //Car타입의 참조변수 c를 매개변수로 하는 메서드
  if(c instanceof FireEngine){ // 이 메서드가 호출될 때 매개변수로 Car클래스 또는 그 자손 클래스의 인스턴스를 넘겨받겠지만 메서드 내에서는 어떤 인스턴스인지 알 수 없다.
  // instanceof연산자를 이용해서 참조변수 c가 가리키고 있는 인스턴스의 타입을 체크하고
    FireEngine fe = (FireEngine)c; // 형변환
    fe.water();
    ...
  }else if(c instanceof Ambulance){
    Ambulance a = (Ambulance) c;
    a.siren();
    ....
  }
}
```
* 참고: '참조변수.getClass().getName()'은 참조변수가 가리키고 있는 인스턴스의 클래스 이름을 문자열로 반환
* 어떤 타입에 대한 instanceof연산의 결과가 true라는 것은 검사한 타입으로 형변환이 가능하다는 의미이다.
+ 참조변수와 인스턴스의 연결
  + 멤버변수가 조상 클래스와 자손 클래스에 중복으로 정의된 경우, 조상타입의 참조변수를 사용했을 때는 조상 클래스에 선언된 멤버변수가 사용되고, 자손타입의 참조변수를 사용했을 때는 자손 클래스에 선언된 멤버변수가 사용된다.  
  🎈 static 메서드는 static변수처럼 참조변수 타입에 영향을 받는다.(영향 안 받는 것은 인스턴스뿐) 그래서 static메서드는 반드시 참조변수가 아닌 '클래스이름.메서드()'로 호출해야 한다.
  + 멤버변수가 중복의 경우, 메서드는 실제 인스턴스의 타입을 따르고 인스턴스변수는 참조변수의 타입에 따라 달라진다.
  ```java
    public class ObjectOriented13 {
    public static void main(String[] args) {
        Grandpa p = new Children(); // 1.타입은 다르지만 참조변수 p와
        Children  c = new Children(); //c모두 Children인스턴스를 참조하고 있다.

        System.out.println("p.x="+p.x); // 3. 인스턴스변수인 x는 참조변수의 타입에 따라 달라진다.
        p.method(); // 4.메서드의 경우 참조변수의 타입에 관계없이 항사 실제 인스턴스의 타입인 Children클래스에 정의된 메서드가 호출 됨

        System.out.println("c.x="+c.x); //인스턴스변수인 x는 참조변수의 타입에 따라 달라진다.
        c.method(); // 메서드의 경우 참조변수의 타입에 관계없이 항사 실제 인스턴스의 타입인 Children클래스에 정의된 메서드가 호출 됨


      }
  }
  class Grandpa{
      int x = 100; // 2.그리고 Grandpa클래스와

      void method(){
          System.out.println("조상 메소드");
      }
  }
  class Children extends Grandpa{
      int x = 200; // Children클래스는 서로 같은 멤버들을 정의하고 있다.

      void method(){
          System.out.println("자식 메소드");
      }
  }
  ```
  실행결과
  ```java
  p.x=100
  자식 메소드
  c.x=200
  자식 메소드
  ```
  + 자식클래스에 선언된 인스턴스변수와 부모클래스로부터 상속받은 인스턴스변수를 구부하는데 참조변수 super와 this가 사용된다.
  ```java
    public class ObjectOriented14 {
      public static void main(String[] args) {
          Grandma grandma = new Son();
          Son son = new Son();

          System.out.println("grandma.x = "+ grandma.x);
          grandma.method();
          System.out.println();
          System.out.println("son.x="+son.x);
          son.method();
      }
  }
  class Grandma {
      int x=100;

      void method(){
          System.out.println("조상 메소드");
      }
  }
  class Son extends Grandma{
      int x = 200;

      void method(){
          System.out.println("x="+x); // Son클래스의 인스턴스변수 x를 뜻한다.
          System.out.println("super.x="+ super.x); //조상 클래스인 Grandma클래스에 선언된 인스턴스변수 x를 뜻한다
          System.out.println("this.x="+ this.x); // Son클래스의 인스턴스변수 x를 뜻한다.
      }
  }
  ```
  실행결과
  ```java
  grandma.x = 100
  x=200
  super.x=100
  this.x=200

  son.x=200
  x=200
  super.x=100
  this.x=200
  ```
+ 매개변수의 다형성
  + 매개변수의 다형성을 적용하여 하나의 메서드로 간단히 처리가능
  + 고객의 잔고와 보너스점수를 출력하는 예제
  ```java
      public class ObjetOriented15 {
        public static void main(String[] args) {
            Buyer buyer = new Buyer();

            buyer.buy(new Tv());
            buyer.buy(new Computer());

            System.out.println("현재 남은 돈:"+ buyer.money);
            System.out.println("현재 보너스 점수:"+ buyer.bonusPoint);
        }
    }
    class Product{
        int price; // 제품 가격
        int bonusPoint; // 제품 구매시 제공하는 보너스 점수

        Product(int price){
            this.price = price;
            bonusPoint = (int)(price/10.0); //보너스점수는 제품가격의 10%
        }
    }
    class Tv extends Product{
        Tv(){
            //조상클래스의 생성자 Product(int price)를 호출
            super((100)); // Tv 가격을 100만원으로 지정
        }
        //Object클래스의 toString을 오버라이딩한다.
        public String toString(){
            return "Tv";
        }
    }
    class Computer extends Product{
        Computer(){
            super(200);
        }
        public String toString(){
            return "Computer";
        }
    }
    class Buyer{ // 고객
        int money = 1000; //고객이 가지고 있는 돈
        int bonusPoint = 0; //보너스 점수

        void buy(Product p){ //매개변수가 Product타입의 참조변수라는 것은 메서드의 매개변수로 Product클래스의 자손타입의 참조변수면 
                            //어느 것이나 매개변수로 받아들일 수 있다는 뜻
                            
            // Product클래스에 price,bonusPoint가 선언되어 있기 때문에 참조변수 p로 인스턴스의 price와 bonusPoint를 사용할 수 있다.
            if(money < p.price){
                System.out.println("잔액이 부족합니다.");
                return;
            }
            money -= p.price; // 가진 돈에서 구입한 제품의 가격을 뺌
            bonusPoint += p.bonusPoint; // 제품의 보너스 점수 추가
            System.out.println(p+"을 구입했습니다.");
        }
    }
    ```
   실헹결과
   ```java
  Tv을 구입했습니다.
  Computer을 구입했습니다.
  현재 남은 돈:700
  현재 보너스 점수:30
  ```













