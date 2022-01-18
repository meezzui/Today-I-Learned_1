### 컬렉션 프레임워크3 (Map인터페이스)
---

#### Map인터페이스
+ 키(key)와 값(value)을 하나의 쌍으로 묶어서 저장
+ 키는 중복될 수 없지만 값은 중복을 허용
+ 중복된 키와 값을 저장하면 기존의 값은 없어지고 마지막에 저장된 값이 남음
+ Map인터페이스에서 값(value)은 중복을 허용하기 때문에 Collection타입으로 반환하고 키(key)는 중복을 허용하지 않기 때문에 Set타입으로 반환
+ Map인터페이스를 구현하는 클래스들: HashMap, TreeMap, Hashtable, Properties

#### Map.Entry인터페이스
+ Map 인터페이스의 내부 인터페이스로 맵에 저장되는 엔트리의 조작을 위한 메소드가 정의되어 있다.
+ 예1) `Map.Entry<K, V> ceilingEntry(K key)` : 해당 맵에서 전달된 키와 같거나, 전달된 키보다 큰 키 중에서 가장 작은 키와 그에 대응하는 값의 엔트리를 반환함. 만약 해당하는 키가 없으면 null을 반환함.
+ 예2) `Map.Entry<K, V> higherEntry(K key)` : 해당 맵에서 전달된 키보다 작은 키 중에서 가장 큰 키와 그에 대응하는 값의 엔트리를 반환함. 만약 해당하는 키가 없으면 null을 반환함.

#### HashMap과 Hashtable
+ HashMap
  + Key, Value를 하나의 데이터로 저장한다.
  + 해싱기법을 사용하기 때문에, 많은 양의 데이터를 검색하는 데 뛰어난 성능을 보인다.
  + HashMap은 null을 허용한다. 그래서 HashMap에서는 map.put(null,null) 과 같이 할 수 있다.
  +  key는 주로 String을 대문자 또는 소문자로 통일해서 사용하곤 한다.
  +  key는 컬렉션 내에서 유일해야 하고(저장된 value를 찾는데 사용되는 것이기 때문에), value는 데이터의 중복을 허용한다.
```java
HashMap map = new HashMap();
map.put("myId", "1234");
map.put("aaa", "1111");
map.put("aaa", "2222");
```
결과(3개의 데이터 쌍을 저장했지만 실제로는 2개의 데이터 쌍만 저장된다. 세번째로 저장한 "aaa" key는 이미 존재하기 때문에, 새로 추가되지 않고 기존의 value 값을 덮어썼다.)
```
key  	value
myId	1234
aaa   2222
```
🎈 HashMap, HashTable의 관계 🎈  
해싱을 구현한 컬렉션 클래스는 HashSet, HashMap, HashTable이 있는데, 컬렉션 프레임웍이 도입되면서 HashTable이 HashMap으로 대체되었다.  
HashTable은 호환성을 위해 남겨두고 있는 것이기 때문에, HashMap을 사용할 것을 권장한다.  
Hashtable은 key, value로 null을 허용하지 않지만, HashMap은 null을 허용한다.  

#### 해싱과 해시함수
+ 해싱 : 해시함수를 이용해서 데이터를 해시테이블에 저장하고 검색하는 기법을 말한다.
+ 해싱을 구현한 컬렉션 클래스 : HashSet, HashMap, Hashtable 등이 있다. 
+ 해싱에서 사용하는 자료구조
  + 배열과 LinkedList 조합으로 되어있다. 저장할 데이터의 키를 해시함수에 넣으면 배열의 한 요소를 얻게 되고, 다시 그 곳에 연결된 LinkedList에 저장하게 된다.
  <img width="500" height="400" src="https://rebeccacho.gitbooks.io/java-study-group/content/KakaoTalk_Photo_2016-03-14-10-02-04_86.jpeg">
  
  + 예를 들어, 병원에서 수많은 환자를 분류할 때 생년을 기준으로 같은 연대생끼리 같은 서랍에 분류할 수 있다.
  + 서랍은 해싱에서 사용되는 자료구조 중 배열의 각 요소를 의미하며, 배열의 각 요소에는 LinkedList가 저장되어 있어서 실제 저장한 데이터는 LinkedList에 담겨지게 된다.
  + HashMap에 저장된 데이터를 찾는 과정
    1. 검색하고자 하는 값의 키로 해시함수(hash function)를 호출한다. -> 키(key) 79xxxxx-xxxxxx
    2. 해시함수의 계산결과인 해시코드(hash code)를 이용해서, 해당 값이 저장되어있는 LinkedList를 찾는다. -> 7 => data[7]
    3. LinkedList에서 검색한 키와 일치하는 데이터를 찾는다. -> data[7] => 79xxxx-xxxxxxx
 🎈 링크드리스트는 검색에 불리한 자료구조이기 때문에 크기가 커질수록 검색속도가 떨어진다. 반면에 배열은 크기가 커져도 원하는 요소가 몇번째에 있는지만 알면 빠르게 원하는 값을 찾을 수 있다.(`배열의 n번째 요소의 주소 = 배열의 시작주소 + type의 size * n`)  
+ HashMap에서 빠른 검색시간을 얻을 수 있는 방법
  + 하나의 LinkedList에 최소한의 데이터만 저장되려면, 저장될 데이터의 크기를 고려해서 HashMap의 크기를 적절하게 지정해주어야 한다.
  + 그리고 해시함수가 서로 다른 키(주민번호)에 대해서 중복된 해시코드(서랍위치)의 반환을 최소화해야 한다.
+ 해시함수 : 데이터가 저장되어 있는 곳을 알려주기 때문에 다량의 데이터 중에서도 원하는 데이터를 빠르게 찾을 수 있다.

#### TreeMap(트리 맵)
+ 이진검색트리의 형태로 key, value 데이터를 저장한다.
+ 이진검색트리를 사용하기 때문에 검색과 정렬에 적합한 컬렉션 클래스이다.
+ HashMap과 TreeMap 중 일반적으로 HashMap이 더 뛰어나지만, 범위검색이나 정렬이 필요한 경우 TreeMap을 사용한다. TreeMap은 key 값들에 대한 정렬이 이루어지기 때문이다.
+ HashMap과 TreeMap의 차이 : TreeMap은 key의 순서가 오름차순으로 유지되는 반면 HashMap은 예측할 수 없는 순서를 가지고 있다.
```java
//TreeMap
public void treeMapTest() {
    TreeMap<Integer, String> map = new TreeMap<>();
    map.put(3, "val");
    map.put(7, "val");
    map.put(8, "val");
    map.put(9, "val");
    map.put(10, "val");
    map.put(11, "val");
    map.put(12, "val");
    map.put(155, "val");
    map.put(168, "val");
    map.put(15976, "val");

    System.out.println("TreeMap.keySet().toString() = " + map.keySet().toString());
    // 결과 : TreeMap.keySet().toString() = [3, 7, 8, 9, 10, 11, 12, 155, 168, 15976]
}

=============================================================================================

//HashMap
public void hashMapTest() {
    HashMap<Integer, String> map = new HashMap<>();
    map.put(3, "val");
    map.put(7, "val");
    map.put(8, "val");
    map.put(9, "val");
    map.put(10, "val");
    map.put(11, "val");
    map.put(12, "val");
    map.put(155, "val");
    map.put(168, "val");
    map.put(15976, "val");

    System.out.println("HashMap.keySet().toString() = " + map.keySet().toString());
    // 결과 : HashMap.keySet().toString() = [3, 7, 8, 168, 15976, 9, 10, 11, 155, 12]
}
```

#### Properties
+ HashMap의 구버전인 Hashtable을 상속받아 구현한 것이다.
+ Hashtable은 키와 값의 형태(Hashtable(Object, Object))로 저장하는 반면, Properties(String, String)
+ 주로 애플리케이션의 환경설정과 관련된 속성을 저장하는데 사용된다.
+ 데이터를 파일로부터 읽고 쓰는 편리한 기능을 제공한다.
```java
Properties prop = new Properties();

prop.setProperty("timeout", "30");
prop.setProperty("language", "kr");
prop.setProperty("size", "10");
prop.setProperty("capacity", "10");

System.out.println(prop);
prop.list(System.out);
//Properties에 저장된 모든 데이터를 화면 또는 파일에 출력
```
+ setProperty()
  + `Object setProperty(String key, String value)`
  + 단순히 Hashtable의 put메서드를 호출할 뿐이다. 그리고 setProperty()는 기존에 같은 키로 저장된 값이 있는 경우 그 값을 Object 타입으로 반환하며, 그렇지 않을 때는 null을 반환한다.
+  getProperty()
  + `Stirng getProperty(String key)` 또는 `Stirng getProperty(String key, String defaultValue)`
  + Properties에 저장된 값을 읽어오는 역할을 하는데, 읽어오려는 키가 존재하지 않으면 지정된 기본값을 반환한다.

#### Collections
+ Collections는 컬렉션과 관련된 메서드를 제공한다. -> fill(), copy(), sort(), binarySearch() 등
+ Collection은 인터페이스이고, Collections는 클래스이다.
+ 컬렉션의 동기화
  + 멀티스레드 프로그래밍에서는 하나의 객체를 여러 스레드가 공유하기 때문에, 데이터 일관성을 유지하기 위해 공유되는 객체에 대한 동기화가 필요하다.
  + 원래 Vector, Hashtable의 클래스들은 자체적으로 동기화 처리가 되어있는데, 멀티스레드 프로그래밍이 아닌 경우에는 불필요한 기능이 되어 오히려 성능을 떨어뜨리는 요인이 되었다.
  + 그래서 ArrayList, HashMap 컬렉션은 동기화를 자체적으로 처리하지 않고, 필요한 경우에만 java.util.collections 의 동기화 메서드를 이용해서 처리하도록 되어있다.
  + Collections 클래스에는 다음과 같은 동기화 메서드를 제공하고 있다.(골라서 쓰면 됨)
  ```java
  static Collection syncronizedCollection(Collection c)
  static List syncronizedList(List l)
  static Set syncronizedSet(Set s)
  static Map syncronizedMap(Map m)
  static SortedSet syncronizedSortedSet(SortedSet s)
  static SotredMap syncronizedSortedMap(SotredMap m)
  ```
+ 변경불가 컬렉션 만들기
  + 컬렉션에 저장된 데이터를 보호하기 위해 읽기전용으로 만들어야 할 때 사용한다. 
  + 주로 멀티스레드 프로그래밍에서 여러 스레드가 하나의 컬렉션을 공유하다보면 데이터가 손상될 수 있는데 이를 방지하기 위해 아래의 메서드를 사용한다.
  ```java
  static Collection unmodifiableCollection(Collection c)
  static List unmodifiableList(List l)
  static Set unmodifiableSet(Set s)
  static Map unmodifiableMap(Map m)
  static SortedSet unmodifiableSortedSet(SortedSet s)
  static NavigableSet unmodifiableNavigableSet(Set s)
  static SotredMap unmodifiableSortedMap(SotredMap m)
  static NavigableMap unmodifiableNavigableMap(Map m)
  ```
  
+ 싱글톤 컬렉션 만들기
  + 인스턴스를 new연산자가 아닌 메서드를 통해서만 생성할 수 있게 함으로써 생성할 수 있는 인스턴스의 개수를 제한하는 방법이다.
  + 매개변수로 저장할 요소를 지정하면, 해당 요소를 저장하는 컬렉션을 반환한다. 그리고 반환된 컬렉션은 변경할 수 없다.
  ```java
  static List singletonList(Object o)
  static Set singleton(Object o)
  static Map singletonMap(Object key, Object value)
  ```
+ 한 종류의 객체만 저장하는 컬렉션 만들기
  + 컬렉션에 모든 객체를 저장할 수 있다는 것은 장점이면서 단점이다. 컬렉션에 지정된 종류의 객체만 저장할 수 있도록 제한하고 싶을 때는 아래의 메서드를 사용한다.
  ```java
  taic Collection checkedCollection (Collection c, Class type)
  staic List checkedList(List list, Class type)
  static Set checkedSet(Set s, Class type)
  static Map checkedMap(Map m, Class keyType, Class valueType)
  ...
  ```
+ Collections 클래스의 메서드들은 static 메서드이기 때문에, `Collections.sort(computers)` 이런식으로 바로 사용할 수 있다.

#### 컬렉션 클래스 정리 도표
<img width="550" height="450" src="https://rebeccacho.gitbooks.io/java-study-group/content/KakaoTalk_Photo_2016-03-14-11-45-56_42.png">



