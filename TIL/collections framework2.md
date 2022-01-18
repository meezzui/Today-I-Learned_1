### 컬렉션 프레임워크2 (Set인터페이스)
---
#### Set인터페이스
+ 중복을 허용하지 않고 저장순서가 유지되지 않는 컬렉션 클래스를 구현하는데 사용된다.
+ Set인터페이스를 구현한 클래스로는 HashSet, TreeSet 등이 있다.

#### HashSet(해시 셋)
+ 중복된 요소를 저장하지 않는다.
+ 새로운 요소를 추가할 때는 add()나 addAll()을 사용하는데 만일 HashSet에 이미 저장되어 있는 요소와 중복된 요소를 추가하고자 한다면 이 메서드들은 false를 반환시킨다.
+ 이러한 특징을 이용하면 컬렉션 내의 중복 요소들을 쉽게 제거할 수 있다.
+ 저장순서를 유지하지 않으므로 저장순서를 유지하고자 한다면 LinkedHashSet을 사용해야 한다.
```java
import java.util.*;

public class Test {
        public static void main(String[] args) {
              Set set = new HashSet();
              set.add(1);
              set.add(2);
              set.add(2);
              set.add(3);
              set.add(1);

             System. out.println(set);
       }
}
```
실행결과 `[1, 2, 3]`  중복된 값은 저장하지 않는다.

+ name, age가 같으면 같은 사람으로 인식하도록하는 예제(하지만 실행결과를 보면 두 인스턴스의 name, age가 같음에도 불구하고 서로 다른것으로 인식하여 'david:10'이 두번 출력되었다.)

```java
import java.util.*;

public class Test {
        public static void main(String[] args) {
             HashSet set = new HashSet();
             set.add("abc");
             set.add("abc");
             set.add(new Person ("david", 10));
             set.add(new Person ("david", 10));

             System.out.println(set);
       }
}

class Person {
       String name;
       int age;

       Person(String name, int age) { // Person클래스는 name, age를 멤버변수로 갖는다. 
           this.name = name;
           this.age = age;
       }

       public String toString() {
           return name+":"+age;
       }
}
```
실행결과 `[abc, david:10, david:10]` 
+ HashSet의 add메서드는 새로운 요소를 추가하기 전에 기존에 저장된 요소와 같은 것인지 판별하기 위해 추가하려는 요소의 equals()와 hashCode()를 호출하기 때문에, equals()와 hashCode()를 목적에 맞게 오버라이딩 해야한다.
+ 그래서 Person클래스 수정
```java
class Person {
    String name;
    int age;

    Person (String name, int age) {
         this.name = name;
         this.age = age;
    }

    public boolean equals(Object obj) {  //equals() 오버라이딩
         if(obj instanceof Person) {
               Person tmp = (Person) obj;
               return name.equals(tmp.name) && age==tmp.age;
         }
         return false;
    }

    public int hashCode() { // jdk1.8부터 추가된 java.util.Objects클래스의 hash() 사용
        return Objects.hash(name,age); // int hash(Object..values)
    }

    public String toString() {
        return name+":"+age;
    }
}
```
+ hashCode()의 조건
  + 오버라이딩을 통해 작성된 hashCode()는 다음의 세 가지 조건을 만족시켜야 한다.
    1. 실행중인 어플리케이션 내의 동일한 객체에 대해서 여러번 hashCode()를 호출해도 동일한 값을 반환해야 한다. 하지만, 실행시마다 동일한 int값을 반환할 필요는 없다.
    2. equals 메서드를 이용한 비교에 의해 ture를 얻은 두 객체에 대해 각각 hashCode()를 호출해서 얻은 결과는 반드시 같아야한다.
    3. equals 메서드를 호출했을 때 false를 반환하는 두 객체는 hasCode() 호출에 대해 같은 int 값을 반환하는 경우가 있어도 괜찮지만, 해싱을 사용하는 컬렉션의 성능을 향상시키기 위해서는 다른 int값을 반환하는 것이 좋다.
```
두 객체에 대해 equals 메서드를 호출한 결과가 true이면, 두 객체의 해시코드는 반드시 같아야하지만,
두 객체의 해시코드가 같다고 해서 equals 메서드의 호출결과가 반드시 true인 것은 아니다.
```

#### TreeSet(트리 셋)
+ 이진검색트리 형태로 데이터를 저장하는 컬렉션 클래스이다.
+ 중복된 데이터의 저장을 허용하지 않으며, 정렬된 위치에 저장하므로 저장순서를 유지하지도 않는다.
+ 이진트리의 노드를 코드로 표현하자면
```java
class TreeNode{
  TreeNode left; // 왼쪽 자식노드
  Object element; // 객체를 저장하기 위한 참조변수
  TreeNode right; // 오른쪽 자식노드
}
```
+ 이진검색트리는 왼쪽 노드의 값 < 부모 노드의 값 < 오른쪽 노드의 값 순서로 저장하는 이진트리이다. 
+ 따라서, 왼쪽 노드 - 부모 노드 - 오른쪽 노드 순으로 읽어오면 오름차순으로 정렬된 순서를 얻을 수 있다.
+ 이처럼 정렬된 상태를 유지하기 때문에 단일값 검색, 범위검색이 매우 빠르다.
+ 문자열의 경우 , 정렬순서는 문자의 코드값이 기준이 된다. '공백 < 숫자 < 대문자 < 소문자'

🎃 컴퓨터는 알아서 값을 비교하지 못한다 🎃  
TreeSet은 데이터를 정렬된 상태로 저장한다. TreeSet을 생성할 때 Comparator를 지정해주지 않으면 저장하는 객체에 구현된 정렬방식에 따라 정렬하여 저장하게 되고, Comparator를 지정해주면 지정된 정렬방식에 떄라 정렬하여 저장한다.
```java
public static void main(String[] args) {
  TreeSet set = new TreeSet(new Comparator() {
     public int compare(Object o1, Object o2) {
       if(o1 instanceof Student && o2 instanceof Student) {
           Student s1 = (Student)o1;
           Student s2 = (Student)o2;
           return (int)(s1.getAverage() - s2.getAverage());
       }
       return -1;
     }
  });
 ```









