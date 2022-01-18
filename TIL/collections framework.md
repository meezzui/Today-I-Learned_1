### 컬렉션 프레임워크1(List인터페이스)
---
#### 컬렉션 프레임웍이란
+ 데이터 군을 저장하는 클래스들을 표준화한 설계. JDK1.2 부터 등장
#### 컬렉션 프레임워크의 핵심 인터페이스
<img src="http://www.gnujava.com/upload/images/1/1411828352969.jpg">

#### 컬렉션 프레임워크 상속 계층도
<img width="400" height="400" src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FAGpq3%2FbtqI07wkE1A%2FyX10IjGgt6N3G6rkT1Ievk%2Fimg.png">

### List인터페이스
+ List인터페이스는 중복을 허용하면서 저장순서가 유지되는 컬렉션을 구현하는데 사용된다.
+ 배열처럼 선형 자료구조를 가진다.
+ 선언한 배열의 공간만을 사용할 수 있다는 배열의 단점을 보완하여 '동적 크기'를 가지며 배열처럼 사용할 수 있다.

#### List인터페이스를 구현하는 클래스
+ ArrayList
 + 데이터의 저장순서가 유지되고 중복을 허용한다.
 + 선언 `ArrayLIst<타입> list = new ArrayList<타입>();` 
 + 간단한 예제
 ```java
//값 추가 
ArrayList<Integer> list = new ArrayList<Integer>();
list.add(3); //값 추가
list.add(null); //null값도 add가능
list.add(1,10); //index 1에 10 삽입

==========================================================

ArrayList<Student> members = new ArrayList<Student>();
Student student = new Student(name,age);
members.add(student);
members.add(new Member("홍길동",15));
```
```java
// 삭제
ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1,2,3));
list.remove(1);  //index 1 제거
list.clear();  //모든 값 제거
```
```java
// 출력
ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1,2,3));

System.out.println(list.get(0));//0번째 index 출력
		
for(Integer i : list) { //for문을 통한 전체출력
    System.out.println(i);
}

Iterator iter = list.iterator(); //Iterator 선언 
while(iter.hasNext()){//다음값이 있는지 체크
    System.out.println(iter.next()); //값 출력
}
```
```java
// 긴 문자열 데이터를 원하는 길이로 잘라서 ArrayList에 담은 다음 출력하는 예제
import java.util.ArrayList;
import java.util.List;

public class ArrayList1 {
    public static void main(String[] args) {
        final int Limit = 10; // 자르고자 하는 글자의 개수
        String source = "012334586sdfdkjkg@#$%";

        List list = new ArrayList(source.length()/Limit + 10); // 크기를 여유롭게 잡는다.

        for(int i=0; i< source.length(); i+=Limit){
            if(i+Limit < source.length()){
                list.add(source.substring(i,i+Limit));
            }else {
                list.add(source.substring(i));
            }
        }
        for(int i=0; i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
}
```
실행결과
```java
012334586s
dfdkjkg@#$
%
```
+ LinkedList
 + 주소값과 데이터로 이루어진 클래스를 만들어 서로 연결하는 방식이다. 
 + 데이터와 주소로 이루어진 클래스를 Node(노드)라고 하는데, 각 노드는 이전의 노드와 다음 노드를 연결하는 방식인 것이다.(이중 연결 리스트라고도 한다.)
 + 즉, 객체끼리 연결한 방식이다. 
 + 요소를 검색해야 할 경우 처음 노드부터 찾으려는 노드가 나올 때 까지 연결된 노드들을 모두 방문해야한다는 점에서 성능이 떨어지나, 해당 노드를 삭제, 삽입해야 할 경우 해당 노드의 링크를 끊거나 연결만 해주면 되기 때문에 삽입, 삭제에서는 매우 좋은 효율을 보인다.
 + 장점: 구조가 간단하며 사용하기 쉽다. 데이터를 읽어오는데 걸리는 시간이 가장 빠르다.
 + 단점: 크기를 변경할 수 없다. 비순차적인 데이터의 추가/삭제에 시간이 많이 걸린다.
```java
class Node{
  Node next; // 다음 요소의 주소를 저장
  Object obj; // 데이터를 저장
}
```
+ doubly LinkedList(이중 연결리스트)
 + LinkedList는 단방향이기 때문에 이전요소에 접근은 어렵다. 이 점을 보완한 것이다.
 + LinkedList에 참조변수를 하나 더 추가하여 다음 요소와 이전 요소에 대한 참조가 가능하도록 했다.
```java 
class Node{
  Node next; //다음 요소의 주소를 저장
  Node previous; //이전 요소의 주소를 저장
  Object obj; // 데이터를 저장
}
```
🎁 ArrayList와 LinkedList의 차이 🎁  
```
+ ArrayList
  + 접근시간(읽기) : 빠르다
  + 추가/삭제 : 순차적으로 추가/삭제하는 경우에는 ArrayList가 빠르다.  
  + 비효율적인 메모리사용
  + 데이터의 개수가 변하지 않을 경우라면, ArrayList가 최상의 선택
+ LinkedList
  + 접근시간(읽기) : 느리다
  + 추가/삭제 : 중간에 데이터를 추가/삭제하는 경우에는 LinkedList가 빠르다.
  + 데이터가 많을수록 접근성이 떨어짐
  + 데이터 개수의 변경이 잦다면 LinkedList를 사용하는 것이 더 나은 선택
``` 
+ Vector
  + Vector의 경우 항상 '동기화'를 지원한다. (쉽게 말하면 여러 쓰레드가 동시에 데이터에 접근하려하면 순차적으로 처리하도록 한다.) 
  + 그렇다보니 멀티 쓰레드에서는 안전하지만, 단일 쓰레드에서도 동기화를 하기 때문에 ArrayList에 비해 성능이 약간 느리다.
  + Vector 생성 
  ```java
  Vector<Integer> vector1 = new Vector<Integer>(); // 타입 지정
  Vector<Integer> vector2 = new Vector<>(); // 타입 생략 가능
  Vector<Integer> vector3 = new Vector<>(10); // 초기 용량(Capacity) 설정
  Vector<Integer> vector4 = new Vector<>(10, 10); // 초기 용량, 증가량 설정
  Vector<Integer> vector5 = new Vector<>(vector1); // 다른 Collection값으로 초기화
  Vector<Integer> vector6 = new Vector<>(Arrays.asList(1, 2, 3, 4, 5)); // Arrays.asList()
  ```
  + Vector 엘리먼트 추가(add())/변경(set())
  ```java
  import java.util.Vector;

  public class Main {
      public static void main(String[] args) {
          Vector<String> colors = new Vector<>();
          // add() method
          colors.add("Black");
          colors.add("White");
          colors.add(0, "Green");
          colors.add("Red");

          // set() method
          colors.set(0, "Blue");

          System.out.println(colors);
      }
  }
  ```

+ Stack(스택)
  + 마지막에 저장한 데이터를 가장 먼저 꺼내게 되는 LIFO(Last In First Out)
  + 가장 대표적인 예시로는 수식계산, 수식괄호검사. 워드프로세서의 undo/redo, 웹페이지 '뒤로가기/앞으로가기'가 있다.
  + 우리가 새로운 페이지로 넘어갈 때마다 넘어가기 전 페이지를 스텍에 쌓고, 만약 뒤로가기를 누른다면 가장 위에 있는 페이지부터 꺼내오는 방식이다.
  +  ArrayList와 같은 배열기반의 컬렉션 클래스가 적합
  + Stack의 생성 `Stack<T> stack = new Stack<>();`
  + Stack은 Vector를 상속하기 때문에 `Vector<T> stack = new Stack<>();` 이렇게도 생성할 수 있다.
+ Queue(큐)
 + 처음에 저장한 데이터를 가장 먼저 꺼내게 되는 FIFO(First In First Out)
 + 10, 20, 30, 40 순으로 데이터를 넣고, 데이터를 꺼낼 때(poll) 넣은 순서 그대로 10, 20, 30, 40이 나오는 구조라는 것이다. 
 + 이 때 가장 앞쪽에 있는 위치를 head(헤드)라고 부르고, 가장 뒤에 있는 위치를 tail(꼬리)라고 부른다.
 + Queue 인터페이스를 구현한 클래스들 : PriorityQueue , Deque(Double-Ended Queue)  
 🎈 PriorityQueue : 저장한 순서에 관계없이 우선순위가 높은 것부터 꺼내게 된다는 특징이 있다. null은 저장할 수 없다. null을 저장하면 NullPointerException이 발생한다. 저장공간으로는 배열을 사용하며, 각 요소를 힙(heap)에 저장한다.  
 🎈 Deque(Double-Ended Queue) : Queue의 변형으로, 한 쪽 끝으로만 추가/삭제할 수 있는 Queue와 달리, Deque(덱 또는 디큐라고 읽음)은 양쪽 끝에 추가/삭제가 가능하다. 덱은 스택과 큐를 하나로 합쳐놓은 것과 같으며 스택으로 사용할 수도 있고, 큐로 사용할 수도 있다.    
 
#### Iterator, ListIterator, Enumeration
+ Iterator, ListIterator, Enumeration은 모두 컬렉션에 저장된 요소를 접근하는데 사용되는 인터페이스이다. 
+ Enumeration은 Iterator의 구버전이며, ListIterator는 Iterator의 기능을 향상 시킨 것이다.
+ Iterator
  + 컬렉션에 저장된 각 요소에 접근하는 기능을 가진 Iterator인터페이스를 정의하고, Collection인터페이스에는 Iterator(Iterator를 구현한 클래스의 인스턴스)를 반환하는 iterator()를 정의하고 있다.
  + Iterator는 반복문, 주로 while문을 사용해서 컬렉션 클래스의 요소들을 읽어 올 때 사용된다.
  + 코드의 재사용성을 높이는데 사용된다.
  ```java
    public interface Iterator {
    dboolean hasNext();
    Object next();
    voud remove();
  }

  public interface Collection {
    ...
    public Iterator iterator();
    ...
  }
  ```
  + ArrayList에 저장된 요소들을 출력하기 위한 코드
  ```java
  Collection c = new ArrayList(); // 다른 컬렉션으로 변경시 'new ArrayList();' 이 부분만 고치면 된다.
  Iterator it = c.iterator();
  
  while(it.hashNext){
    System.out.println(it.next());
  }
  ```
  🎉 참조변수 타입을 ArrayList타입이 아니라 Collection타입으로 한 이유는??? 🎉   
     Collection인터페이스를 구현한 다른 클래스, 예를들어 LinkedList로 바꿔야 한다면 선언문 하나만 변경하면 나머지 코드는 검토하지 않아도 된다. 참조변수 타입이 Collection이므로 Collection에 정의되지 않은 메서드는 사용되지 않았을 것이 확실하기 때문이다.  
+ ListIterator와 Enumeration
  + Enumeration은 컬렉션 프레임웍이 만들어지기 이전에 사용하던 것으로 Iterator의 구버전이라고 생각하면 된다. 
  + 이전 버전으로 작성된 소스와의 호환을 위해서 남겨두고 있을 뿐이므로 가능하면 Enumeration대신 Iterator를 사용하자. 
  + Enumeration - Iterator의 구버전
  + ListIterator - Iterator에 양방향 조회기능추가

#### Arrays
+ Arrays클래스에는 배열을 다루는데 유용한 메서드가 정의되어 있다.
+ 배열의 복사 - copuOf(), copyOfRange()
  + copyOf() :  배열 전체를 복사해서 새로운 배열을 만들어 반환한다.
  + copyOfRange() : 배열의 일부를 복사해서 새로운 배열을 만들어 반환한다.
+ 배열 채우기 - fill(), setAll()
  + fill() : 배열의 모든 요소를 지정된 값으로 채운다. 
  + setAll()은 배열을 채우는데 사용할 함수형 인터페이스를 매개변수로 받는다.
  ```java
  int[] arr = new int[5];
  Arrays.fill(arr,9); // arr=[9,9,9,9,9]
  Arrays.setAll(arr, () -> (int)(Math.random()*5)+1); // arr=[1,5,2,1,1]
  ```
+ 배열의 정렬과 검색 - sort(), binarySearch()
  + sort() : 배열을 정렬할 때 사용
  + binarySearch() : 배열에 저장된 요소를 검색할 때 사용. 배열에서 지정된 값이 저장된 위치(index)를 찾아서 반환하는데, 반드시 배열이 정렬된 상태이어야 올바른 결과를 얻는다.(그니까 sort()를 먼저 해줘야 한다는거) 만일 검색한 값과 일치하는 요소들이 여러 개 있다면, 이 중에서 어떤 것의 위치가 반환될지는 알 수 없다.
+ 문자열의 비교와 출력 - equals(), toString()
  + toString() : 모든 요소를 문자열로 출력하는데 일차원 배열에만 사용할 수 있으므로, 다차원 배열에는 deepToString()을 사용해야 한다.
  + equals() : 두 배열에 저장된 모든 요소를 비교해서 같으면 true, 다르면 false를 반환한다. equals()도 일차원 배열에서만 사용가능하므로, 다차원 배열의 비교에는 deepToEquals()를 사용해야 한다.
+ 배열을 List로 변환 - asList(Object... a)
  + asList() : 배열을 List에 담아서 반환한다. 단, 추가/삭제 불가능. 저장내용 변경은 가능.
  ```java
  List list = Arrays.asList(new Integer[]{1,2,3,4,5}); // list = [1,2,3,4,5]
  List list = Arrays.asList(1,2,3,4,5); // list = [1,2,3,4,5]
  list.add(6); // 예외발생(추가불가)
  
  //변경할려면
  List list = new ArrayList(Arrays.asList(1,2,3,4,5));
  ```
#### Comparator와 Comparable
```java
public interface Comparator { // 기본 정렬기준(오름차순) 외에 다른 기준으로 정렬하고자 할 때 사용
  int compare(Object o1, Object o2);
  boolean equals(Object obj);
}

public interface Comparable { //기본 정렬기준(오름차순)을 구현하는데 사용
  public int compareTo(Object o);
}
```
```java
import java.util.*;
class ComparatorEx {
  public static void main(String[] args) {
    String[] strArr = {"cat", "Dog", "lion", tiger"};
    Arrays.sort(strArr);
    System.out.println(Arrays.toString(strArr));
    // Dog, cat, lion, tiger

    Arrays.sort(strArr, String.CASE_INSENSITIVE_ORDER);
    System.out.println(Arrays.toString(strArr));
    // cat, Dog, lion, tiger

    Arrays.sort(strArr, new Descending());
    System.out.println(Arrays.toString(strArr));
    // tiger, lion, cat, Dog
  }
}

class Descending implements Comparator {
  public int compare(Object o1, Object o2) {
      if(o1 instanceof Comparable && o2 instanceof Comparable) {
        Comparable c1 = (Comparable)o1;
        Comparable c2 = (Comparable) o2;
        return c1.compareTo(c2) * -1; // -1을 곱해서 기본 정렬방식의 역으로 변경
      }
      return -1;
  }
}
```

+ compare()와 compareTo()는 선언형태와 이름이 약간 다를 뿐 두 객체를 비교한다는 같은 목적으로 고안된 것이다.
+ compareTo()의 반환값은 int지만 실제로 두 객체가 같으면 0, 비교하는 값보다 작으면 음수, 크면 양수를 반환하도록 구현해야 한다. 
+ 이와 마찬가지로 compare()도 객체를 비교해서 음수, 0, 양수 중의 하나를 반환하도록 구현해야 한다.
```java
public final class Integer extends Number implements Comparable {
  ...
  public int compareTo(Object o) {
    return compareTo((Integer)o);
  }
  public int compareTo(Integer anotherInteger) {
    int thisVal = this.value;
    int anotherVal = anotherInteger.value;
    // 비교하는 값이 크면 -1, 같으면 0, 작으면 1을 반환한다.
    return (thisVal<anotherVal ? -1 : (thisVal==anotherVal ? 0 : 1));
  }
```










