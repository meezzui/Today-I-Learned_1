### 제네릭스(Generics)
---
#### 제네릭스란
+ 다양한 타입의 객체들을 다루는 메서드나 컬렉션 클래스에 컴파일 시의 타입체크(compile-time type check)를 해주는 기능
+ 객체의 타입 안정성을 제공한다.(의도하지 않은 타입의 객체가 저장되는 것을 막아 잘못 형변환 되는 오류를 줄여준다.)
+ 형변환의 번거로움을 줄여준다. (다룰 객체의 타입을 미리 명시해 줌 -> 코드 간결)
+ 제네릭 클래스 선언1 ( Box<T>클래스: 어떤 타입이든 한가지 타입을 정해서 넣을 수 있다. )
```java
/* Box<T>클래스 선언 */

// 지네릭 타입 T를 선언
class Box<T> { // class Box {
  // Object item;
  T item;

  void setItem(T item) { // void setItem(Object item) {
    this.item = item;
  }

  T getItem() { // Object getItem() {
    return item;
  }
}

/* Box<T>클래스 사용 */ 

Box<String> b = new Box<String>(); // T 대신 실제 타입 지정

b.setItem(new Object()); // ERROR: String타입 이외의 타입은 지정불가
b.setItem("ABC"); // OK: String타입만 가능

// String item = (String)b.getItem(); 
String item = b.getItem(); // 형변환 필요없음
```
+ 제네릭 클래스 선언2 (Box<String>클래스: String타입만 담을 수 있다.)
```java
class Box<String> { // 지네릭 타입을 String으로 지정
  String item;
  void setItem(String item) {
    this.item = item;
  }
  String getItem() {
    return item;
  }
}
```
+ 지네릭스의 제한
  + 모든 객체에 동일하게 동작해야 하는 static멤버에는 타입변수 T를 사용할 수 없다.(T는 인스턴스변수로 간주되기 때문이다. static에는 인스턴스변수를 참조할 수 없다.)
  + 지네릭 타입의 배열을 (선언은 가능하지만) 생성하는 것은 불가능하다.(new연산자, instacneof연산자는 컴파일 시점에 타입T가 뭔지 정확하게 알아야 하기 때문에 T를 피연산자로 사용할 수 없다.)
  + 지네릭 배열을 꼭 생성해야 할 경우 방법 : new연산자 대신 'Reflection API'의 newInstance()와 같이 동적으로 객체를 생성하는 메서드로 배열 생성 -> Object배열을 생성해서 복사한 다음에 T[]로 형변환
  ```java
  /* 제네릭스는 인스턴스별로 다르게 동작하려고 만든 기능 */
  Box<Apple> appleBox = new Box<Apple>(); // OK: Apple객체만 저장가능
  Box<Grape> grapeBox = new Box<Grape>(); // OK: Grape객체만 저장가능

  /* 타입변수는 static에는 사용불가 */
  class Box<T> {
    static T item; // ERROR
    static int compare(T t1, T t2) { ... } // ERROR
  }

  /* 제네릭타입배열 생성불가 */
  class Box<T> {
    T[] itemArr; // OK: T타입의 배열을 위한 참조변수
    ...
    T[] toArray() {
      T[] tmpArr = new T[itemArr.length]; // ERROR: 제네릭 배열 생성 불가
      ...
      return tmpArr;
    }
  }
  ```
#### 지네릭 클래스의 객체 생성과 사용
```java
Box<Apple> appleBox = new Box<Apple>(); // OK
// ERROR: 참조변수와 생성자에 대입된 타입이 일치해야 한다.
Box<Apple> appleBox = new Box<Grape>(); 

// 타입 상속관계: Apple이 Fruit의 자손일 경우
// ERROR: 상속관계여도 일치된 타입이여야 한다.
Box<Fruit> appleBox = new Box<Apple>(); 

// 두 제네릭 클래스의 타입이 상속관계에 있고, 대입된 타입이 같은 것은 괜찮다.
// 지네릭 클래스 상속관계: FruitBox<T>가 Box<T>의 자손인 경우
Box<Apple> appleBox = new FruitBox<Apple>(); // OK: 다형성

// 추정가능한 타입 생략 가능 (JDK1.7부터)
Box<Apple> appleBox = new Box<Apple>(); // OK
Box<Apple> appleBox = new Box<>(); // JDK1.7부터 OK
```
+ Box<T>.add() 사용
```java
 // 생성시 대입된 타입과 다른 타입의 객체 추가 불가
Box<Apple> appleBox = new Box<Apple>();
appleBox.add(new Apple()); // OK
appleBox.add(new Grape()); // ERROR

// 타입 상속관계: Apple이 Fruit의 자손
Box<Fruit> fruitBox = new Box<Fruit>(); //Fruit의 자손들은 메서드의 매개변수가 될 수 있다.
fruitBox.add(new Fruit()); // OK
fruitBox.add(new Apple()); // ERROR: Fruit만 가능
```

#### 제한된 제네릭 클래스
+ `extends`를 사용하면 특정 타입의 자손들만 대입할 수 있게 타입을 제한할 수 있다.
```java
FruitBox<Toy> fruitBox = new FruitBox<Toy>();
fruitBox.add(new Toy()); // OK

// Fruit의 자손만 타입으로 지정
class FruitBox<T extends Fruit> {
    ArrayList<T> list = new ArrayList<T>();
    ...
}

class Apple extends Fruit { ... }
class Grape extends Fruit { ... }
class Toy { ... }

FruitBox<Apple> appleBox = new FruitBox<Apple>(); // OK: Apple은 Fruit의 자손
FruitBox<Toy> toyBox = new FruitBox<Toy>(); // ERROR: Toy는 Fruit의 자손이 아님

// 다형성: 매개변수의 타입 T도 Fruit과 그 자손타입이 가능해짐
// (따라서, T에 Object를 대입하면 모든 종류의 객체를 저장할 수 있게 됨)
FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
fruitBox.add(new Apple()); // OK: Apple이 Fruit의 자손
fruitBox.add(new Grape()); // OK: Grape가 Fruit의 자손

// 특정 인터페이스를 구현해야 한다는 제약 (인터페이스도 implements가 아니라 extends)
interface Eatable { }
class FruitBox<T extends Eatable> { ... }

// Fruit의 자손이면서 Eatable 인터페이스도 구현해야 한다는 제약
class FruitBox<T extends Fruit & Eatable> { ... }
```
+ 특정 인터페이스를 구현해야 할 때 인터페이스도 implements가 아니라 `extends`를 사용해야 한다!!!!

#### 와일드 카드 <?>
+ static 메서드에는 타입 매개변수 T를 매개변수에 사용할 수 없다. 특정타입을 고정하면 여러가지 타입의 매개변수를 갖도록 오버로딩해야 한다. 이를 위해 나온것이 와일드 카드!!!
+ 와일드카드는 어떠한 타입도 될 수 있다.
```java
<? extends T> 와일드 카드의 상한 제한(upper bound) - T와 그 자손들을 구현한 객체들만 매개변수로 가능
<? super T> 와일드 카드의 하한 제한(lower bound) -T와 그 조상들을 구현한 객체들만 매개변수로 가능
<?> 제한 없음
```
+ 예) <? extends Object> => <?>와 같다
```java
class Juicer {
  static Juice makeJuice(FruitBox<? extends Object> box) {
    String tmp = "";
    for(Fruit f: box.getList()) tmp += f + " ";
    return new Juice(tmp);
  }
}
```
🎈 makeJuice()의 매개변수로 모든 타입의 FruitBox가 올 수 있지만, for문에서는 Fruit만을 받기때문에 Fruit타입만을 받을 수 있다. 그러나, FruitBox는 지네릭클래스로 Fruit을 제한하고 있기 때문에 컴파일에 문제가 생기지 않는다.(컴파일러는 FruitBox의 요소가 모두 Fruit의 자손이라는 것을 알기 때문)  

#### 제네릭 메서드
+ 메서드의 선언부에 제네릭 타입이 선언된 메서드를 제네릭 메서드라고 한다. ex) Collections.sort()
+ 형태 `접근제어자 <타입 파라미터,...> 리턴타입 메소드명(매개변수,...){...}`
+ 제네릭 타입의 선언 위치는 반환 타입 바로 앞이다. `static <T> void sort(List<T> list, Compatator<? super T> c)` -> static멤버에는 타입 매개변수를 사용할 수 없지만, 메서드에 제네릭 타입을 선언하고 사용하는 것은 가능.
+ 지네릭 클래스의 타입매개변수 T와 지네릭 메서드의 타입매개변수 T는 별개의 것(서로 같은문자를 쓰더라도)
+ 지네릭 메서드는 지네릭 클래스가 아닌 클래스에도 정의 가능
+ 제네릭 메서드 호출
  + `리턴타입 변수 = <구체적인 타입> 메소드명(매개 값);`과 `리턴타입 변수 = 메소드명(매개 값);` 두 가지가 있다.
  ```java
  public class Util {

      //제네릭 메소드 선언방법: 리턴 타입 앞에 타입파라미터 기술 후에 리턴 타입과, 매개타입으로 타입 파라미터를 사용하면 된다.
      public static <T> Box<T> boxing(T t){
          Box<T> box = new Box<>();
          box.set(t);
          return box;
      }
  }

  public class BoxingMethodExample{
      public static void main(String[] args){
          // 매개 값의 타입으로 자바 컴파일러에서 타입을 추정
          Box<Integer> box = Util.boxing(100);
          int initValue = box.get();
          System.out.println(initValue);
      }
  }
  ```

#### 지네릭 타입의 형변환
+ 제네릭 타입과 넌제네릭 타입간의 형변환은 가능하다. 다만 경고가 발생할 뿐
+ 그러나 대입된 타입이 다른 제네릭 타입 간에는 형변환이 불가능하다.
```java
Box<Object> objBox = null;
Box<String> strBox = null;

// ERROR: Box<String> -> Box<Object>
objBox = (Box<Object>)strBox; 
// ERROR: Box<Object> -> Box<String>
strBox = (Box<String>)objBox; 
```
+ 와일드 카드가 포함된 제네릭 타입으로 형변환하는 것은 가능하다. 
```java
Optional<Object> -> Optional<T> // 형변환 불가능
Optional<Object> -> Optional<?> -> Optional<T> // 형변환 가능. 경고 발생
```
+ 와일드 카드가 사용된 제네릭 타입끼리도 형변환도 가능(컴파일러는 미확정 타입으로 형변환 하는 것이라고 경고한다.)
```java
FruitBox<? extends Object> objBox = null;
FruitBox<? extends String> strBox = null;

strBox = (FruitBox<? extends String>)objBox; 
objBox = (FruitBox<? extends Object)strBox;
```

#### 제네릭 타입의 제거
+ 컴파일러는 지네릭 타입을 이용해서 소스 파일을 체크하고, 필요한 곳에 형변환을 넣어준 뒤 지네릭 타입을 제거한다.
+ 즉, 컴파일된 파일(*.class)에는 지네릭 타입에 대한 정보가 없다. 이전의 소스 코드와의 호환성을 유지하기 위한 것이다.
1. 제네릭 타입의 경계를 제거한다.  
```java
class Box<T extends Fruit> {
  void add(T t) {
  ....
}

=================================================================================
// <T extends Fruit> -> T는 Fruit으로 치환 됨. // <T>인 경우는 T는 Object로 치환 됨.
// 그리고 클래스 옆의 선언은 제거된다.

class Box{
  void add(Fruit t){
    ....
  }
}
```
2. 지네릭 타입을 제거한 후에 타입이 일치하지 않으면 형변환 추가  
```java
T get(int i){
  return list.get(i);
}

============================================================
//List의 get()은 Object타입을 반환하므로 형변환이 필요하다.

Fruit get(int i){
  return (Fruit)list.get(i);
}
```
3. 와일드 카드가 포함되어 있는 경우에는 적절한 타입으로 형변환 해주면 된다.(1,2번 합친거랑 비슷한 맥락으로)  

#### 멀티 타입 파라미터 - class<K,V,...>, interface<K,V,...>
+ 멀티 타입 파라미터를 가진 제네릭 타입을 정의하고 호출하는 예제
```java
public class Product<T, M> {

    private T t;
    private M m;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public M getM() {
        return m;
    }

    public void setM(M m) {
        this.m = m;
    }
}

public class GenericExample {
    public static void main(String[] args) {
        Product<Tv, String> product1 = new Product<>();

        product1.setT(new Tv("삼성전자Tv"));
        product1.setM("디젤");

        Tv tv = product1.getT();
        String name = product1.getM();

        System.out.println(tv.getName() +" " + name);
    }
}
```







