### 열거형(enums)
---
#### 열거형이란?
+ JDK 1.5부터 새로 추가 됨
+ C언어의 열거형보다 향상된 기능 : 값 뿐만 아니라 타입까지 관리 (논리적인 오류를 줄임)
+ 상수를 사용할 경우, 상수를 사용하면 해당 상수를 참조하는 모든 소스를 다시 컴파일 해야한다. 하지만 열거형 상수를 사용하면 기존 소스를 다시 컴파일 하지 않아도 된다.
```java
public class Card {
    enum Kind { CLOVER, HEART, DIAMOND, SPADE }
    enum Value {TWO, THREE, FOUR }

    final Kind kind = Kind.CLOVER;
    final Value value = Value.TWO;
}
```

#### 열거형의 정의와 사용
+ 열거형 정의
```java
 // enum 열거형 이름 { 상수명1, 상수명2, ...} 
  enum Direction { EAST, WEST, SOUTH, NORTH }
```
+ 열거형 사용
  + `열거형 이름.상수명`
  + 비교 연산 : `"==" 사용 (equals(o))` / `"compareTo()" 사용` : ">", "<" 사용 불가, 왼쪽이 크면 양수, 오른쪽이 크면 음수
  ```java
  // 열거형 이름.상수명
  Direction direction = Direction.EAST;

  // 비교 연산
  if( direction == Direction.EAST )
    System.out.println("Test 1");
  else if (direction.compareTo(Direction.WEST) > 0)
    System.out.println("Test 2");
  // 에러!! (비교연산자 사용 불가)
  //else if (direction > Direction.WEST) 
  //  System.out.println("Test 3");
  ```
+ switch 조건식 : 열거형 사용
  + case문은 열거형 이름은 적지 않고 상수이름만 적어야 함(case Direction.EAST: // 이렇게 쓰면 에러!!)
  ```java
  void move(Direction direction) {
      switch (direction) {
      case EAST:
          x++;
          break;
      case WEST:
          y++;
          break;
      case SOUTH:
          x--;
          break;
      case NORTH:
          y--;
          break;
      }
  }
  ```
+ 모든 열거형의 조상 - java.lang.Enum (클래스)
  + 다음과 같은 메서드가 정의되어 있음
  ```java
  Class<E> getDeclaringClass() // 열거형의 클래스 객체를 반환
  String name() // 열거형 상수의 이름을 문자열로 반환
  int ordinal() // 열거형 상수가 정의된 순서를 반환 (0부터 시작)
  T valueOf(Class<T> enumType, String name) // 지정된 열거형에서 name 과 일치하는 열거형 상수를 반환
  values() // 열거형의 모든 상수를 배열에 담아 반환
  ```
  ```java
  // 열거형에 모든 상수를 출력하려면
  Direction[] directionArr = Direction.values();
   for(Direction direction : directionArr) {
     System.out.println("%s=%d\n", direction.name(), direction.ordinal());
   }
  ```
#### 열거형에 멤버 추가하기
+ 열거형 상수의 값이 불연속적인 경우에는 열거형 상수의 이름 옆에 원하는 값을 괄호()와 함께 적어주면 된다.
+ `enum Direction{ EAST(1), SOUTH(5), WEST(-1), NORTH(10); }`
+  그리고 지정되 값을 저장할 수 있는 인스턴스 변수와 생성자를 추가해 주어야한다. 주의!!!! 먼저 열거형 상수를 모두 정의한 다음에 다른 멤버들을 추가해야 한다.
+  열거형 상수의 마지막에 ';' 붙이는거 잊지말기!!!
```java
// 열거형 상수에 여러 값 지정하기
enum Direction{
  EAST(1,">"), SOUTH(2,"V"), WEST(3,"<"), NORTH(4,"^"); // 끝에 ';' 추가해주기
  
  //열거형의 인스턴스 변수는 반드시 final이어야 한다는 제약은 없지만, value와 symbol은 열거형 상수의 값을 저장하기 위한 것이므로 final을 붙임.
  private final int value; // 정수를 저장할 필드(인스턴스 변수) 추가
  private final String symbol; // 문자열을 저장할 필드(인스턴스 변수) 추가
  
  // 생성자는 추가되었지만 객체를 생성할 수는 없다.(Direction d = new Direction(1); // 에러!! 열거형의 생성자는 외부에서 호출불가)
  // why?? 열거형의 생성자는 제어자가 묵시적으로 'private'이기 때문이다. `private Direction(int value, String symbol)`와 동일
  Direction(int value, String symbol){ // 생성자 추가, 접근 제어자 private 생략됨.
    this.value = value;
    this.symbol = symbol;
  }
  public int getValue(){ //외부애서 이 값을 얻을 수 있게 추가
    return value;
  }
  public String getSymbol(){ //외부애서 이 값을 얻을 수 있게 추가
    return symbol;
  }
  
}
```
  
  
  
  
  
  
  
  
