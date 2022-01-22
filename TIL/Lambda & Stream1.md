### 람다와 스트림
---
#### 람다식(Lamda expression)
+ 람다식이란
  + 람다식은 메서드를 하나의 식(expression)으로 표현한 것.
  + 객체 지향 언어보다는 함수 지향 언어에 가깝다.
  + 함수를 간략하면서도 명확한 식으로 표현할 수 있도록 해준다.
  + 메서드를 람다식으로 표현하면 메서드의 이름 및 반환 값이 없어지므로 익명 함수 라고도 한다.
  + 람다식의 형태는 매개 변수를 가진 코드 블록이지만 런타임 시에는 익명 구현 객체를 생성한다.
+ 람다식 작성하기
  + `(타입 매개변수) -> { 실행문; ... }` 형태
  + 예) 
  ```java
  int max(int a, int b){
    return a> b ? a: b;
  }
  ===========================
   // 람다식으로 변형
    (int a, int b) -> {
    return a> b ? a : b;
  }
  ```
  + return문 대신 식으로 표현할 수 있다. 식이므로 끝에 ';'을 붙이지 않는다.
  ```java
  (int a, int b) -> { retrun a > b ? a : b }
  ==========================================
  (int a, int b) -> a > b ? a : b
  ```
  + 선언된 매개변수의 타입은 추론이 가능한 경우 생략 가능
  ```java
  (int a, int b) -> a > b ? a : b
  =========================================
  (a,b) -> a > b ? a : b
  ```
#### 함수형 인터페이스(Functional Interface)
+ 람다식은 익명클래스의 객체와 동등하다.
+ 람다식으로 정의된 익명 객체의 메서드를 어떻게 호출할 수 있을까??
  + 참조변수가 있어야 한다. `타입 f = (int a, int b) -> a > b ? a : b;`
  + 참조변수의 타입은 클래스 또는 인터페이스가 가능하다.
  + 그리고 람다식과 동등한 메서드가 정의되어 있는 것이어야 한다. -> 그래야 참조변수로 익명 객체(람다식)의 메서드를 호출할 수 있기 때문이다.
```java
// max()라는 메서드가 정의된 MyFunction인터페이스가 정의되어 있다고 가정
interface MyFunction{
  public abstract int max(int a, int b); // MyFunction인터페이스에 정의된 메서드 max()는 
}

// 이 인터페이스를 구현한 익명 클래스의 객체 생성
MyFunction f = new MyFunction(){
  public int max(int a, int b){ // 람다식 `(int a, int b) ->  a > b ? a : b`와 메서드 선언부가 일치한다. 
    return a > b ? a : b;
  }
};
int big = f.max(5,3); // 익명 객체의 메서드 호출
```
+ 이처럼 람다식을 다루기 위한 인터페이스를 '함수형 인터페이스'라고 한다. 단, 함수형 인터페이스에는 오직 하나의 추상 메서드만 정의되어 있어야 한다.
+ 그러나 static메서드와 default메서드의 개수에는 제약이 없다.
+ @FunctionalInterface를 붙이면 컴파일러가 함수형 인터페이스를 올바르게 정의하였는지 확인해주므로 꼭 붙여라.
+ 매개변수 타입은 함수형 인터페이스로 지정
+ 참조변수 없이 직접 람다식을 매개변수로 지정 `aMethod(() -> System.out.println("myMethod()")); // 람다식을 매개변수로 지정`

#### java.util.function패키지
+ 대부분의 메서드는 타입이 비슷하고 지네릭 메서드로 정의하면 반환 타입이 달라도 문제가 되지 않는다. 
+ java.util.function 패키지에는 일반적으로 자주 쓰이는 형식의 메서드를 함수형 인터페이스로 미리 정의해 놓았다.
+ java.util.function패키지의 주요 함수형 인터페이스
  + java.lang.Runnable : 매개변수도 없고, 반환값도 없다.
  + Supplier<T> : 매개변수는 없고, 반환값만 있다.
  + Consumer<T> : Supplier와 반대로 매개변수만 있고, 반환값이 없다
  + Function<T,R> : 일반적인 함수. 하나의 매개변수를 받아서 결과를 반환
  + Predicate<T> : 조건식을 표현하는데 사용됨. 매개변수는 하나. 반환 타입은 boolean (Function의 변형으로 반환타입이 boolean이라는 것만 다르다.)
  + 매개변수가 두 개인 함수형 인터페이스는 이름 앞에 접두사 'Bi'가 붙는다.
  
  
  
#### Function의 합성과 Predicate의 결합
+ Function의 합성
  + andThen : a함수 적용 후 b함수 적용 `a.andThen(b)`
  + compose : b함수 적용 후 a함수 적용 ` a.compose(b)`
  + identity : 항등 함수 (잘 사용되지 않는 편이나 map()으로 변환 작업할 때 변환없이 그대로 처리하고자할 때 사용)
+ Predicate의 결합
  + and() : &&
  + or() : ||
  + negate() : not
  + isEqual() : 두 대상 비교
```java
class LambdaEx7{
  public static void main(String[] args){
    Function<String, Integer> f = (s) -> Integer.parseInt(s,16);
    Function<Integer, String> g = (i) -> Integer.toBinaryString(i);
    
    Function<String, String> h = f.andThen(g);
    Function<Integer, Integer> h2 = f.compose(g);
  
    System.out.println(h.apply("FF")); //"FF" -> 255 -> "11111111"
    System.out.println(h.apply(2)); // 2 -> "10" -> 16
    
    Function<String, String> f2 = x -> x; //항등함수
    System.out.println(f2.apply("AAA")); // AAA가 그대로 출력됨
    
    Predicate<Integer> all = notP.and(q.or(r));
    System.out.println(all.test(150)); //true
    
    String str1 = "abc";
    String str2 = "abc";
    //str1과 str2가 같은지 비교한뒤 결과 반환
    Predicate<String> p2 = Predicate.isEqual(str1);
    boolean result = p2.test(str2);
    System.out.println(result);
  }  
}
```
실행결과
```
11111111
16
AAA
true
true
```

#### 메서드 참조
+ static메서드 참조 : 람다식 `(x) -> ClassName.method(x)` 메서드 참조 `ClassName::method`
+ 인스턴스메서드 참조 : 람다식 `(obj, x) -> obj.method(x)` 메서드 참조 'ClassName::method`
+ 특정 객체 인스턴스메서드 참조 : 람다식 `(x) -> obj.method(x)` 메서드 참조 `obj::method`
+ 하나의 메서드만 호출하는 람다식은 '클래스이름::메서드이름' 또는 '참조변수::메서드이름'으로 바꿀 수 있다.

#### 생성자의 메서드 참조
+ 생성자를 호출하는 람다식도 메서드 참조로 변환할 수 있다.
```java
Supplier<MyClass> s = () -> new MyClass(); // 람다식
Supplier<MyClass> s = MyClass::new; // 메서드 참조

// 매개변수가 있는 생성자라면 매개변수의 개수에 따라 알맞은 함수형 인터페이스를 사용하면 된다.
Function<Integer, MyClass> f = (i) -> new MyClass(i); // 람다식
Function<Integer, MyClass> f2 = MyClass::new; // 메서드 참조

 //배열 생성할 때
Function<Integer, int[]> f = x -> new int[x]; // 람다식
Function<Integer, int[]> f2 = int[]::new; // 메서드 참조
```
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
