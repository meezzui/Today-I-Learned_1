### java.lang패키지와 유용한 클래스
---
## java.lang패키지
+ 자바프로그래밍에 가장 기본이 되는 클래스들을 포함하고 있기 때문에 이 클래스들은 import문 없이도 사용할 수 있다.
#### java.lang패키지의 종류
+ Object클래스
  + 모든 클래스의 최고 조상이며 Object클래스의 멤버들은 모든 클래스에서 사용 가능
  + 멤버변수는 없고 11개의 메서드만 가짐 (인스턴스가 가져야 할 기본적인 것들)
<img src="https://kookyungmin.github.io/image/java_image/java_image_144.png">

### equals()
+ equals메서드는 주소값으로 비교하기 때문에 두 Value인스턴스의 멤버변수 value값이 10으로 같더라도 equals로 비교한 결과는 false이다.
```java
  class Value {
  int value;

  Value(int value) {
    this.value = value;
  }
}

class EqualsEx1 {
  public static void main(String[] args) {
    Value v1 = new Value(10);
    Value v2 = new Value(10);

    if(v1.equals(v2)) {
      System.out.println("eqauls == true");
    } else {
      System.out.println("eqauls == false"); // 서로 다른 주소 참조
    }

    v2 = v1; // 서로 같은 주소 참조

    if(v1.equals(v2)) {
      System.out.println("eqauls == true"); // 서로 같은 주소 참조
    } else {
      System.out.println("eqauls == false");
    }
  }
}
```
실행결과
```java
eqauls == false
eqauls == true
```
+ equals메서드로 Value인스턴스의 멤버변수 value값 비교방법(오버라이딩하기)
```java
class Person {
  long id;

  // 오버라이딩
  public boolean equals(Object obj) {
    if(obj != null && obj instanceof Person) {
      return id == ((Person)obj).id; // obj가 Object타입이므로 id값을 참조하기 위해서는 Person타입으로 형변환이 필요하다.
    } else {
      return false; // 타입이 Person이 아니면 값을 비교할 필요도 없다.
    }
  }
  Person(long id) {
    this.id = id;
  }
}

class EqualsEx2 {
  public static void main(String[] args) {
    Person p1 = new Pserson(8602192222111L);
    Person p2 = new Pserson(8602192222111L);

    if(p1 == p2) {//주소비교
      System.out.println("eqauls == true");
    } else {
      System.out.println("eqauls == false"); // 서로다른 주소 참조
    }

    if(p1.equals(p2)) { // id 비교
      System.out.println("eqauls == true"); // 서로 같은 값
    } else {
      System.out.println("eqauls == false");
    }
  }
}
```
실행결과
```java
eqauls == false
eqauls == true
```
🎉 equals메서드를 오버라이딩하고 있는 클래스들 🎉  
String, Date, File, Wrapper클래스(Integer, Double 등) 단, StringBuffer클래스는 오버라이딩 되어있지 않다.  

### hasCode()
+ 해싱(hashing)기법에 사용되는 해시함수(hash function)를 구현한 것
+ 객체의 주소값을 이용해서 해시코드를 만들어 반환한다. 따라서, 서로 다른 두 객체는 결코 같은 해시코드를 가질 수 없다.(32 bit JVM) -> 64 bit JVM은 해시코드 중복가능
+ 오버라이딩: 클래스의 인스턴스변수값으로 객체의 같고 다름을 판단해야할 경우
+ 해싱: 데이터관리기법 중의 하나로 다량의 데이터를 저장하고 검색하는데 유용
+ 해시함수: 찾고자하는 값을 입력하면, 그 값이 저장된 위치를 알려주는 해시코드를 반환
+ 해싱기법을 사용하는 HashMap이나 HashSet과 같은 클래스에 저장할 객체라면 반드시 hashCode메서드를 오버라이딩해야 한다. 
+ String클래스를 이용한 hashCode() 예제
```java
class HashCodeEx1 {
  public static void main(String[] args) {
    String str1 = new String("abc");
    String str2 = new String("abc");

    System.out.println(str1.equals(str2)); // true

    System.out.println(str1.hashCode()); // 96354
    System.out.println(str2.hashCode()); // 96354

    System.out.println(System.identityHashCode(str1)); // 27134973
    System.out.println(System.identityHashCode(str2)); // 1284693
  }
}
```
String클래스는 문자열의 내용이 같으면, 동일한 해시코드를 반환하도록 hashCode()가 오버라이딩되어 있기 때문에, 같은 문자열의 내용을 hashcode()로 호출하면 동일한 해시코드값을 얻는다.  
반면, System.identityHashCode(Object x)는 항상 다른 해시코드값을 반환하기 때문에 str1과 str2가 해시코드는 같지만 서로 다른 객체라는 것을 알 수 있다.(실행 할 때마다 달라짐)

### toString()
+ 이 메서드는 인스턴스 변수에 저장된 값들을 문자열로 표현한다.
+ 오버라이딩을 하지 않는다면 클래스이름에 16진수의 해시코드의 값이 출력된다.
`System.out.println(c1.toString());` 의 결과는 `Card@19e0bfd` (클래스이름은 Card)
+ toString() 오버라이딩 예제
```java
import java.util.Date;

public class ToStringTest {
    public static void main(String[] args) {
        String str = new String("Korea"); //오버라이딩
        Date today = new Date(); // 오버라이딩

        System.out.println(str);
        System.out.println(str.toString());
        System.out.println(today);
        System.out.println(today.toString());
    }
}
```
실행결과(쓸모 있는 정보를 제공)
```java
Korea
Korea
Mon Jan 17 15:25:06 KST 2022
Mon Jan 17 15:25:06 KST 2022
```

### clone()
+ 자신을 복제하여 샐운 인스턴스를 생성
+ 원래의 인스턴스는 보존하고 clone메서드를 이용해서 새로운 인스턴스를 생성하여 작업을 하면 작업이전의 값 보존 가능
+ 단순히 인스턴스 변수의 값만 복사 따라서, 참조변수 타입의 인스턴스 변수가 정의되어 있는 경우 완전한 복제가 아님(clone메서드를 오버라딩해야 함)
+ 공변 반환타입(covariant return type) : 오버라이딩할 때 조상 메서드의 반환타입을 자손 클래스 타입으로 변경을 허용 (jdk1.5부터)
```java
public Point clone() { // 1. 반환타입을 Object -> Point (조상->자손타입으로) 변경
  Object obj = null;
  try {
    obj = super.clone();
  } catch(CloneNotSupportedException e) { }
  return (Point)obj; // 2. Point타입으로 형변환
}


// Point copy = (Point)original.clone();
Point copy = original.clone(); // 번거로운 형변환이 줄어든다.
```

### String클래스
+ 인스턴스변수로 문자형 배열 변수에 문자열을 저장한다.
+ 클래스앞에 final이 붙으므로 다른 클래스의 조상이 될 수 없다.
+ 한번 생성된 String 인스턴스가 갖고 있는 문자열은 읽어올 수만 있고, 변경할 수는 없다.
+ '+' 연산자를 이용해서 문자열을 결합하는 경우 인스턴스내의 문자열이 바뀌는 것이 아니라 새로운 문자열이 담긴 String 인스턴스가 생성되는 것이다. (결합시마다 인스턴스가 생성되어 메모리 공간을 차지하므로 횟수를 줄이는 것이 좋다.)
+ 문자열간의 결합이나 추출 등의 작업은 StringBuffer클래스를 사용하는 것이 좋다.
+ 문자열 비교
  + `String str1 = "abc"; // 문자열 리터럴 "abc"의 주소가 str1에 저장됨`
  + `String str2 = new String("abc"); // 새로운 String인스턴스를 생성`
+ 빈 문자열(empty string)
  + 길이가 0인 배열은 존재한다. 
  + 이 배열을 내부적으로 가지고 있는 문자열이 빈 문자열이다.
  + `String s = "";` -> char[0] //길이가 0인 char형 배열을 저장하고 있다.
  + 초기화 방법 -> String은 빈 문자열로 초기화 `String s = "";` char는 공백으로 초기화 `char c = ' ';`
+ join()과 StringJoiner (jdk1.8부터 추가 됨)
  + join(): 여러 문자열 사이에 구분자를 넣어서 결합한다.
  + split(): 구분자로 문자열을 자른다.
  + java.util.StringJoiner클래스로 문자열을 결합할 수도 있다.
  ```java
  String animals = "dog,cat,bear";
  String[] arr = animals.split(",");
  String str = String.join("-", arr);
  System.out.println(str); // dog-cat-bear

  StringJoiner sj = new StringJoiner(",", "[", "]");
  String[] strArr = { "aaa", "bbb", "ccc" };
  for(String s : strArr)
    sj.add(s.toUpperCase());
  System.out.println(sj.toString()); // [AAA,BBB,CCC]
  ```
+ String.format()
  + 형식화된 문자열을 만들어낸다.
  ```java
  String str = String.format("%d 더하기 %d는 %d입니다.", 3,5,3+5);
  System.out.println(str); // 3 더하기 5는 8입니다.
  ```
+ 기본형 값을 String으로 변환하는 방법
  + valueOf() 사용
  ```java
  int i = 100;
  String str1 = i + ""; // 100 -> "100"
  String str2 = String.valueOf(i); // 100 -> "100" (성능향상이 필요한 경우)
  ```
+ String을 기본형 값으로 변환
  + parseInt()나 valueOf() 사용
  ```java
  int i = Integer.paeseInt("100"); // "100"을 100으로 변환1
  int i = Integer.valueOf("100"); //  "100"을 100으로 변환2
  ```

### StringBuffer클래스와 StringBuilder클래스
+ StringBuffer
  + 인스턴스를 생성할 때 지정된 문자열 변경가능(String클래스는 불가)
  + StringBuffer인스턴스를 생성할 때 그 크기를 지정할 수 있다.(편집할 문자열의 길이를 고려하여 버퍼의 길이를 충분히 잡아주는 것이 좋다.)
  + 문자열이 버퍼의 길이를 넘어서게되면 길이를 늘려주는 작업이 수행되어야 한다.
  + String클래스와 같이 문자열을 저장하기 위한 char형 배열 참조변수를 인스턴스로 갖는다.
  ```java
  public StringBuffer(int length) {
  value = new char[length];
  shared = false;
  }

  public StringBuffer() {
    this(16); // 버퍼의 크기를 정하지 않으면 16으로 잡는다.
  }

  public StringBuffer(String str) {
    this(str.length() + 16); // 버퍼의 크기를 지정한 문자열의 길이보다 16 크게 잡는다.
    append(str); 
  }

  /* 버퍼의 크기를 늘리는 작업 코드 */

  // 새로운 길이의 배열을 생성 (newCapacity는 정수값)
  char newValue[] = new char[newCapacity];

  // 기존 내용 복사
  System.arraycopy(value, 0, newValue, 0, count); // count = 문자열 길이
  value = newValue; // 새로 생성된 배열의 주소를 참조변수 value에 저장
  ```
  + StringBuffer 변경(append()이용)
  ```java
  StringBuffer sb = new StringBuffer("abc");
  sb.append("123"); // sb의 내용 뒤에 123을 추가하고 자신의 주소를 반환한다.
  StringBuffer sb2 = sb.append("ZZ"); // sb의 내용 뒤에 ZZ를 추가하고 자신의 주소를 반환한다.
  System.out.println(sb); // abc123ZZ
  System.out.println(sb2); // abc123ZZ
  ```
  + StringBuffer의 비교(String클래스와 달리 equals()가 오버라이딩 되어 있지 않음 ->toString()을 호출해서 String인스턴스를 얻은 다음 equals()를 사용해야 함.)
  ```java
  // equals()는 오버라이딩 되어있지 않음.
  StringBuffer sb = new StringBuffer("abc");
  StringBuffer sb2 = new StringBuffer("abc");
  System.out.println(sb == sb2); // false
  System.out.println(sb.equals(sb2)); // false

  // toString()은 오버라이딩 되어 있음.
  String s = sb.toString();
  String s2 = sb2.toString();
  System.out.println(s.equals(s2)); // true
  ```
+ StringBuilder
  + StringBuffer는 멀티쓰레드에 안전(thread safe)하도록 동기화 되어 있다. (성능을 떨어뜨림)
  + StringBuilder: StringBuffer에서 쓰레드의 동기화만 뺀 클래스 (그외에는 완전 똑같은 기능)
  + 멀티쓰레드 환경이 아니라면 StringBuilder를 쓰면된다.
  + StringBuffer도 충분히 성능이 좋기 때문에 성능향상이 반드시 필요한 경우에만 쓴다.

### Math클래스
+ 기본적인 수학계산에 유용한 메서드로 구성된 클래스
+ 생성자가 private이므로 다른 클래스에서 인스턴스를 생성할 수 없다. (인스턴스변수가 하나도 없어서 인스턴스를 생성할 필요가 없다.)
+ 메서드는 모두 static이다.
+ 메서드 이름에 'Exact'가 포함된 메서들은 jdk1.8부터 새로 추가된 것들이다.
+ Math클래스의 대표적인 메서드는 random()과 round() 등이 있다.
  + random() : 메소드는 0.0~1.0 사이의 임의의 double형 데이터를 생성하여 반환합니다.(1.0은 범위에 포함되지 않는다.)
  ```java
  import java.lang.Math;
 
  public class Sample
  {
      public static void main(String[] args)
      {
          System.out.println((int)(Math.random() * 10));     // 0~9 사이 난수 발생
          System.out.println((int)(Math.random() * 100));  // 0~99 사이 난수 발생   
          System.out.println((int)(Math.random() * 1000)); // 0~999 사이 난수 발생    
      }
  }
  ```
  + round() : 항상 소수점 첫째자리에서 반올림을 해서 정수값(long)을 결과로 돌려준다.
  `Math.round(9075.52) -> 9076`

### 래퍼(wrapper)클래스
+ 기본형(primitive type)을 클래스로 다루어야 할 경우를 위한 클래스
+ 래퍼클래스들은 모두 equals(), toString()이 오버라이딩 되어 있다.
+ 비교연산자를 사용할 수 없는 대신 compareTo()를 제공한다.
+ 기본형이 int ->  래퍼클래스는 Integer 예) `Integer i = new Integer(100);`
+ Number클래스
  + 상클래스로 내부적으로 숫자를 멤버변수로 갖는 래퍼클래스들의 조상
  + 객체가 가지고 있는 값을 숫자와 관련된 기본형으로 변환하여 반환하는 메서드들을 정의하고 있다.
  + BigInteger: long으로도 다룰 수 없는 큰 범위의 정수
  + BigDecimal: double로도 다룰 수 없는 큰 범위의 부동 소수점수

## 유용한 클래스
### java.util.Objects클래스
+ Object 클래스의 보조 클래스로 Math 클래스처럼 모든 메서드가 'static'이다. 객체의 비교나 널 체크에 유용하다.
+ Object 클래스에는 두 객체의 등가비교를 위한 equals()만 있고, 대소비교를 위한 compare()가 없다. 그래서 Objects에는 compare()가 추가 되었다.
`static int compare(Object a, Object b, Comparator c)` -> 두 비교대상이 같으면 0, 크면 양수, 작으면 음수를 반환
+ Objects 클래스의 equals()는 매개변수의 값이 null인지 확인할 필요가 없다.
```java
// Object 클래스
if(a!=null && a.equals(b)){ ...} // a가 null인지 반드시 확인해야 함
// Objects 클래스
if(Objects.equals(a,b){...} 
```

### java.util.Random 클래스
+ 사실 Math.random()은 내부적으로 Random 클래스의 인스턴스를 생성해서 사용하는 것이므로 둘 중에서 편한 것을 사용하면 된다.
```java
double rNum = Math.random();
double rNum = new Random.nextDouble(); // 위 문장과 동일
```

### 정규식(Regular Expression) - java.util.regex 패키지
+ 정규식이란 텍스트 데이터 중에서 원하는 조건(패턴, pattern)과 일치하는 문자열을 찾아내기 위해 사용하는 것으로 미리 정의된 기호와 문자를 이용해서 작성한 문자열을 말한다.
+ 비밀번호 유효성 체크/이메일 유효성 체크/전화번호 추출/html 에서 텍스트 마이닝에 사용
```java
import java.util.regex.*; // Pattern과 Matcher가 속한 패키지

class RegularEx1 {
  public static void main(String[] args) {
    String[] data = {"bat", "baby", "bonus", "cA"};
    Pattern p = Pattern.compile("c[a-z]*"); // 정규식을 매개변수로 Pattern클래스의 static 메서드인 Pattern compile(String regex)을 호출하여 Pattern 인스턴스를 얻는다.
                                            // c로 시작하는 소문자영어
    for(int i = 0;i<data.length; i++){
      Matcher m = p.matcher(data[i]); // 정규식으로 비교할 대상을 매개변수로 Pattern클래스의 Matcher matcher (CharSequence input)를 호출해서 Matcher인스턴스를 얻는다.
      if(m.matches()) // Matcher 인스턴스에 boolean matches()를 호출해서 정규식에 부합하는 지 확인한다.
        System.out.print(data[i] + ",");
    }
  }
}
```

### java.util.Scanner 클래스
+ Scanner는 화면, 파일, 문자열과 같은 입력소스로부터 문자데이터를 읽어오는데 도움을 줄 목적으로 JDK 1.5부터 추가되었다.
+ JDK 1.5이후
```java
Scanner s = new Scanner(System.in);
String input = s.nextLine();
```
+ JDK 1.6이후(화면 입출력만 전문으로 담당하는 java.io.Console이 새로 추가 됨-이클립스에선 동작하지 않음)
```java
Console console = System.console();
String input = console.readLine();
```

### java.util.StringTokenizer 클래스
+  문자열을 지정된 구분자를 기준으로 토큰(token)이라는 여러개의 문자열로 잘라내는 데 사용된다. 
+  예를 들어 "100,200,300,400"라는 문자열이 있을 때 ','를 구분자로 잘라내면 "100", "200", "300", "400" 이라는 4개의 문자열을 얻을 수 있다.
```java
import java.util.*;

class StringTokenizerEx1 {
  public static void main(String[] args) {
    String source = "100,200,300,400";
    StringTokenizer st = new StringTokenizer(source, ",");
    while(st.hasMoreTokens())
    {
      System.out.println(st.nextToken());
    }
  }
}
```
실행결과
```java
100
200
300
400
```

###  java.math.BigInteger 클래스
+ 가장 큰 정수형 타입인 long으로 표현할 수 있는 값은 10진수로 19자리 정도이다. 이 값보다 더 큰 숫자를 다룰때 사용된다.
+ BigInteger의 생성
  ```java
  BigInteger val;
  val = new BigInteger("12345678901234"); //문자열로 생성
  val = BigInteger.valueOf(12345789L);
  ...
  ```
### java.math.BigDecimal 클래스
+ double타입으로 표현할 수 없는 더 큰 수를 표현
+ BigDecimal은 실수형과 달리 정수를 이용해서 실수를 표현한다. `정수 x 10 ^ scale`
+ BigDecimal의 생성
  ```java
  BigDecimal val;
  val = new BigDecimal("123.4567890"); //문자열로 생성
  val = new BigDecimal(123.4567); // double 타입으로 생성
  ...
  ```
  



