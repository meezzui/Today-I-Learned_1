### 람다와 스트림
----

#### 스트림이란?
+ 다양한 데이터 소스를 표준화된 방법으로 다루기 위한 라이브러리이다. 
+ 스트림은 데이터 소스를 추상화하고, 데이터를 다루는데 자주 사용되는 메서드들을 정의해놓았다.
+ 스트림을 이용하면, 배열이나 컬렉션 뿐만 아니라 파일에 저장된 데이터도 모두 같은 방식으로 다룰 수 있다.
+ 문자열을 저장하는 List가 있을때 스트림을 생성하는 방법
```java
String[] strArr = {"aaa", "ddd", "ccc"};
List<String> strList = Arrys.asList(strArr);

Stream<String> strStreamArr = Arrays.stream(strArr);
Stream<String> strStreamList = strList.stream();
```
#### 스트림의 연산
+ 스트림이 제공하는 다양한 연산을 이용해서 복잡한 작업들을 간단하게 처리할 수 있다.
+ 중간연산 : 연산결과가 스트림인 연산. 스트림에 연속해서 중간 연산을 할 수 있음
+ 최종연산 : 연산결과가 스트림이 아닌 연산. 스트림의 요소를 소모하므로 단 한번만 가능.
```java
stream.distinct().limit(5).sorted().forEach(System.out::println)
       =========  =======  =======  ============================
       중간연산    중간연산 중간연산          최종연산
```
+ 중간연산은 map()과 flatMap()[스트림의 요소변환]이 핵심이다.
+ 최종연산은 `reduce() : 스트림의 요소를 하나씩 줄여가면서(reducing) 계산한다.` 와 `collect() : 스트림의 요소를 수집한다. 주로 요소를 그룹화하거나 결과를 컬렉션에 담아 반환하는데 사용된다.` 가 핵심이다.

#### 스트림의 특징
+ 스트림은 데이터 소스를 변경하지 않는다.
  + 스트림은 데이터 소스로부터 데이터를 읽기만 할 뿐, 데이터소스를 변경하지 않는다. 정렬된 결과가 필요할 경우, collect를 활용해서 컬렉션이나 배열에 담아 return할 수 있다.
  + `List<String> sortedList = strStreamList.sorted().collect(Collectors.toList());`
+ 스트림은 일회용이다.
  + 스트림은 한번 사용하면 닫혀서 다시 사용할 수 없다. 필요하다면 스트림을 다시 생성해야 한다.
  ```java
  strStreamArr.sorted().forEach(System.out::println);
  long numOfStr = strStreamArr.count(); 
  
  //에러남
  Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
  ```
+ 스트림은 작업을 내부 반복으로 처리한다.
  + 내부 반복이란, 반복문을 메서드의 내부에 숨길 수 있다는 것을 의미한다.
  + forEach() 는 스트림에 정의된 메서드 중의 하나로 매개변수에 대입된 람다식을 데이터소스의 모든 요소에 적용한다. 즉, forEach()는 메서드 안에 for문을 넣어버린 것이다.
  + `strStreamArr.sorted().forEach(System.out::println); // 수행할 작업을 매개변수로 받는다.`
+ 지연된 연산
  + 스트림 연산에서는 최종 연산이 수행되기 전까지는 중간 연산이 수행되지 않는다. 
  + 중간연산을 호출하는 것은 단지 어떤 작업이 수행되어야하는지를 지정해주는 것일 뿐이다.
  + 최종연산이 수행되어서야 스트림의 요소들이 중간연산을 거치고 최종연산에 소모된다.
+ 기본형 스트림
  + 오토박싱, 언박싱으로 인한 비효율을 줄이기 위해 데이터 소스의 요소를 기본형으로 다루는 IntStream, LongStream, DoubleStream 이 제공된다.
  + 일반적으로 Stream< Integer > 대신 IntStream을 사용하는 것이 더 효율적이고, IntStream에는 int타입으로 작업하는데 유용한 메서드들이 포함되어있다.
+ 병렬스트림
  + 스트림은 내부적으로 fork & join framework를 이용해서 연산을 자동적으로 병렬로 수행한다.
  + parallel() 메서드를 호출하면 병렬로 연산이 수행되고, sequential() 메서드를 호출하면 병렬로 처리되지 않게 된다. 
  + 모든 스트림은 기본적으로 병렬 스트림이 아니기 때문에 sequential() 메서드는 parallel()를 취소할 때만 사용한다.
  + `int sum = strStream.parallel().mapToInt(s -> s.length()).sum();`

#### 스트림 만들기
+ 컬렉션
  + 컬렉션 클래스들은 모두 stream()메서드로 스트림을 생성할 수 있다. stream()은 해당 컬렉션을 소스로 하는 스트림을 반환한다.
  + 기본형태 `Stream <T> Collection.stream()`
  + List로부터 스트림을 생성하는 코드
  ```java
  List<Integer> list = Arrays.asList(1,2,3,4,5); //가변인자
  Stream<Integer> intStream = list.stream(); // list를 소스로 하는 컬렉션 생성
  ```

+ 배열
  + 배열을 소스로 스트림을 생성하는 메서드는 Stream과 Arrays에 static메서드로 정의되어 있다.
  ```java
  String[] strArr = {"aaa", "ddd", "ccc"};

  Stream<String> strStream = Stream.of(strArr); 
  Stream<String> strStream = Arrays.stream(strArr);
  ```
  + int, long,double과 같은 기본형 배열을 소스로 하는 스트림 생성 메서드
  + `ntStream IntStream.of(int... values) // Stream이 아니라 IntStream`
+ 특정범위의 정수
  + IntStream과 LongStream은 지정된 범위의 연속된 정수를 스트림으로 생성해서 반환하는 range()와 rangeClosed()를 가지고 있다.
  ```java
  //end가 범위에 포함되지 않음.
  IntStream intStream = IntStream.range(int begin, int end); 
  //end가 범위에 포함 됨.
  IntStream intStream = IntStream.rangeClosed(int begin, int end); 
  
  IntStream intStream = IntStream.range(1, 5); // 1,2,3,4 
  IntStream intStream = IntStream.rangeClosed(1, 5); // 1,2,3,4,5
  ```
  + int보다 큰 범위의 스트림을 생성하려면 LongStream을 사용하면 된다.
  
+ 임의의 수
  + 난수를 생성하는데 사용하는 Random클래스에는 아래와 같은 메서드들이 포함되어있다. 이 메서드들은 해당 타입의 난수들로 이루어진 스트림을 반환한다.
  ```
  IntStream ints()
  LongStream longs()
  DoubleStream doubles()
  ```
  + 이 메서드들이 반환하는 스트림은 크기가 정해지지 않은 '무한 스트림'이므로 limit()도 같이 사용해서 스트림의 크기를 제한해 주어야 한다.(limit()은 스트림의 개수를 지정하는데 사용된다.)
  ```java
  IntStream intStream = new Random().ints(); // 무한 스트림
  intStream.limit(5).forEach(System.out::println); // 5개의 요소만 출력
  IntStream intStream = new Random().ints(5); // 크기가 5인 난수 스트림을 반환
  ```
+ 람다식
  + Stream 클래스의 iterate(), generate()는 람다식을 매개변수로 받아서, 이 람다식에 의해 계산되는 결과값들을 요소로 하는 무한 스트림을 생성한다.
  + iterate() : 이전 결과에 대해 종속적
  + `static <T> Stream<T> iterate(T seed, UnaryOperator<T> f)` seed로 지정된 값부터 시작해서, 람다식 f에 의해 계산된결과를 다시 seed값으로 해서 계산을 반복한다.
  + evenStream이 0부터 시작해서 값이 2씩 계속 증가하는 예제
  + `Stream<Integer> evenStream = Stream.iterate(0, n->n+2); // 0, 2, 4, 6, ...`
  + generate() : 이전 결과에 대해 독립적. 이전 결과를 이용해서 다음 요소를 계산하지 않는다.
  + `static <T> Stream<T> generate(Supplier<T> s)` generate()에 정의된 매개변수의 타입이 Supplier<T>이므로 매개변수가 없는 람다식만 허용된다.
  + 예) `Stream<Double> randomStream = Stream.generate(Math::random);`
  🎈 iterate()와 generate()에 의해 생성된 스트림은 아래와 같이 기본형 스트림 타입의 참조변수로 다룰 수 없다.  
  ```java
  IntStream evenStream = Stream.iterate(0, n->n+2);  //error
  DoubleStream randomStream = Stream.generate(Math::random);  //error
  
  // 굳이 필요하다면, 아래와 같이 mapToInt()와 같은 메서드로 변환을 해야한다. (Stream, IntStream 변환)
  IntStream evenStream = Stream.iterate(0, n->n+2).mapToInt(Integer::valueOf);
  Stream<Integer> stream = evenStream.boxed();
  ```
+ 파일
  + 지정된 디렉토리에 있는 파일의 목록을 소스로 하는 스트림을 생성해서 반환한다.
  ` Stream<Path>     Files.list(Path dir) // Path는 파일 또는 디렉토리`
+ 빈 스트림
  + 요소가 하나도 없는 비어있는 스트림을 생성할 수도 있다. 스트림에 연산을 수행한 결과가 하나도 없을 때 null보다는 빈 스트림을 return하는 것이 낫다.
  + `Stream emptyStream = Stream.empty();` empty()는 빈 스트림을 생성해서 반환한다.
+ 두 스트림 연결
  + Stream의 static메서드인 concat()을 사용하면 두 스트림을 하나로 연결할 수 있다. 단, 연결하려는 두 스트림 요소가 같은 타입이어야 한다.
  ```java
  String[] str1 = {"123", "456" };
  String[] str2 = {"aaa", "bbb", "cc"};

  Stream<String> strs1 = Stream.of(str1);
  Stream<String> strs2 = Stream.of(str2);
  Stream<String> strs3 = Stream.concat(strs1, strs2);
  
  =========================================================
  //결과
  123
  456
  aaa
  bbb
  cc
  ```
#### 스트림의 중간연산
+ 스트림 자르기
  + skip() : 앞에서부터의 요소를 건너뛴다. `Stream<T> skip(long n)` 예) `intStream.skip(3)`: 처음 3개의 요소를 건너띄어라.
  + limit() : 스트림의 요소를 제한 `Stream<T> limit(long maxSize)` 예) `intStream.limit(5)` : 스트림의 요소를 5개로 제한
  
+ 스트림의 요소 걸러내기
  + filter() : 주어진 조건(Predicate)에 안 맞는 요소 제외 `Stream < T > filter(Predicate < T > predicate)`
  + `intStream.filter(i-> i%2==0).forEach(System.out::print);`
  + distinct() : 중복된 요소 제거 `Stream < T > distinct()`
+ 정렬
  + sorted() : 스트림의 요소 정렬. 지정된 Comparator로 스트림을 정렬하는데, int 값을 반환하는 람다식을 사용하는 것도 가능하다.
  ```java
  Stream<String> strStream = Stream.of("dd", "aaa", "CC", "cc", "b");
  strStream.sorted().forEach(System.out::print);
  ```
+ 변환
  + map() :스트림의 요소에 저장된 값 중에서 원하는 필드만 뽑다내거나 특정 형태로 변환해야될 때 사용한다.
  + File의 스트림에서 파일의 확장자만을 뽑은 다음 중복을 제거해서 출력하는 예시
  ```java
  fileStream.map(File::getName) // Stream<File> -> Stream<String> 변환
    .filter(s -> s.indexOf('.')!= -1) // 확장자가 없는 것은 제외
    .map(s -> s.substring(s.indexOf('.')+1)) // Stream<String> -> Stream<String> 변환
    .map(String::toUpperCase) // 모두 대문자로 변환
    .distinct()  // 중복제거
    .forEach(System.out::print);
  ```
+ 조회
  + peek() : 연산과 연산 사이에 올바르게 처리되었는지 확인하고 싶을 때 사용. 연산 사이에 여러번 끼워 넣어도 문제가 되지 않음.
  ```java
  fileStream.map(File::getName).peek(s -> System.out.printf("filename=%s\n",s)) // 파일명 출력
  ```
  + mapToObj() : IntStream을 Stream < T >로 변환할 때
  + boxed() : Stream로 변환할 때는 boxed()를 사용
  + flatMap() : 스트림의 요소가 배열이거나 map의 연산결과가 배열인경우, `Stream < T [] >`를 `Stream < T >`로 다루는 것이 더 편리할 때 flatMap()을 사용한다.
  🎈 map()과 flatMap() 차이  
     map()은 Stream<Stream<String>>으로 변환, flatMap()은 Stream<String>으로 변환.  
  
#### Optional< T >
+ Optional<T> : Optional은 지네릭클래스로, T타입의 객체를 감싸는 래퍼클래스이다. Optional 타입의 객체에는 모든 타입의 참조변수를 담을 수 있다. 
+ 최종 연산의 결과를 그냥 반환하는게 아니라 Optional객체에 담아서 반환을 하면, 반환된 결과가 null인지 매번 if문으로 체크하는 대신 Optional에 정의된 메서드를 통해 간단히 처리할 수 있다.
+ Optional 객체생성
```java
String str = "abc";
Optional <String> optVal = Optional.of(str);
```
+ 참조변수의 값이 null일 가능성이 있으면 of()대신 ofNullable()을 사용해야 한다. of()는 매개변수의 값이 null이면 NullPointerException을 발생시키기 때문이다.
```java
Optional <String> optVal = Optional.of(null);         //Error
Optional <String> optVal = Optional.ofNullable(null); //OK
```
+ Optional < T > 참조변수의 초기화
```java
Optional <String> optVal = null;                     //null로 초기화
Optional <String> optVal = Optional.<String>empty(); //빈 객체로 초기화
```
+ Optional 객체의 값 가져오기
```java
Optional<String> optVal = Optional.of("abc");

String str1 = optVal.get(); 
// optVal에 저장된 값을 반환. null이면 예외발생 

String str2 = optVal.orElse(""); 
// optVal에 저장된 값이 null일 때는, ""를 반환 

String str3 = optVal.orElseGet(String::new); 
// 람다식 사용가능 () -> new String()

String str4 = optVal.orElseThrow(NullPointerException::new); 
// 널이면 예외발생
```
+ isPresent() : Optional 객체의 값이 null이면 false, 아니면 true를 반환한다.
```java
if(Optional.ofNullable(str).isPresent()) { 
  System.out.println(str);
}
```
+ ifPresent() : isPresent()를 더 간단히 만들 수 있다. `Optional.ofNullable(str).ifPresent(System.out::println);`

#### 스트림의 최종연산
+ forEach() : 각 요소에 지정된 작업 수행. 반환타입이 void이므로 스트림의 요소를 출력하는 용도로 많이 사용된다.
+ 조건검사
  + allMatch(): 모두 만족하는지? `boolean allMatch(Pradicate < T > p)`
  + anyMath() : 하나라도 만족하는지? `boolean anyMatch(Pradicate < T > p)`
  + noneMatch() : 모두 만족하지 않는지? `boolean noneMatch(Pradicate < T > p)`
  + findFirst() : 첫번째 요소 `Optional < T > findFirst()`
  + findAny() : 아무거나 하나 `Optional < T > findAny()`
+ 통계
  + count() : 스트림의 요소 개수 `long count()`
  + max() : 스트림의 최댓값  `ptional < T > max (Comparator <? super T> comparator)`
  + min() : 스트림의 최소값  `Optional < T > min (Comparator <? super T> comparator)`
+ 리듀싱
  + reduce() : 스트림의 요소를 줄여나가면서 연산을 수행하고 최종결과를 반환한다.
  + 처음 두 요소를 가지고 연산한 결과를 가지고 그 다음 요소와 연산한다. 
  + `Optional < T > reduce (BinaryOperator < T > accumulator)`
  ```java
  int count = intStream.reduce(0, (a,b) -> a+1); // count()
  int sum = intStream.reduce(0, (a,b) -> a+b); // sum()
  ```
  
#### collect()
+ 스트림의 최종 연산중에 하나이다.
+ 스트림의 요소를 수집한다. 주로 요소를 그룹화하거나 결과를 컬렉션에 담아 반환하는데 사용된다.
+ `List<String> sortedList = strStreamList.sorted().collect(Collectors.toList());`
+ Collect(), Collector, Collectors
  + collect() : 스트림의 최종연산, 매개변수로 컬렉터를 필요로 한다.
  + Collector : 인터페이스. 컬렉터는 이 인터페이스를 구현해야 한다.
  + Collectors : 클래스. static메서드로 미리 작성된 컬렉터를 제공한다.

+ Collectors는 다양한 기능의 메서드를 제공한다.
  + toList(), toSet(), toMap(), toCollection()... //스크림을 컬렉션과 배열로 변환
  ```java
  ArrayList<String> list = names.stream().collect(Collectors.toCollection(ArrayList::new)); 
  // Stream<String> → ArrayList<String>
  ```
  + counting(), summingInt(), averagingInt(), maxBy(), minBy(), summarizingInt()... // 통계
  ```java
  long totalScore = stuStream.mapToInt(Student::getTotalScore).sum(); 
  // IntStream의 sum()
  long totalScore = stuStream.collect(summingInt(Student::getTotalScore));
  // Collectors의 summingInt
  ```
  + reducing() // 리듀싱
  + partitioningBy(), groupingBy(), collectingAndThen() // 그룹화와 분할
  + prtitioningBy() : 스트림의 요소를 2분할 한다.
  ```java
  Map < Boolean, Long > stuNumBySex = stuStream.collect(partitioningBy(Student::isMale)); // 학생들을 성별로 분할

  List<Student> maleStudent = stuBySex.get(true); // Map에서 남학생 목록을 얻는다. List<Student> femaleStudent = stuBySex.get(false); // Map에서 여학생 목록을 얻는다.
  ```
  + groupingBy() : 스트림의 요소를 그룹화한다.
  ```java
  Map<Integer, List<Student>> stuByBan = 
  stuStream.collect(groupingBy(Student::getBan, toList())); // 학생을 반별로 그룹화
  ```

#### Collector구현하기
+ Collector인터페이스 구현 메서드
```java
public interface Collector < T, A, B > {
  //T(요소)를 A에 누적한 다음, 결과를 R로 변환해서 return

  Supplier < A >   supplier();   
  //StringBuilder::new   
  //결과를 저장할 공간(A)을 제공

  BiConsumer < A, T > accumulator();   
  //(sb, s) -> sb.append(s) 
  //스트림의 요소를 수집(collect)할 방법을 제공

  BinaryOperator < A > combiner();   
  //(sb1, sb2) -> sb1.append(sb2) 
  //두 저장공간(A)을 결합할 방법을 제공(병렬 스트림)

  Function < A, R > finisher();   
  //sb -> sb.toString()  
  //최종변환
  //변환할 필요가 없는 경우, x->x

  Set<Characteristics> characteristics;  // characteristics() : 컬렉터가 수행할 작업의 속성정보를 제공한다.
  //컬렉터의 특성이 담긴 Set return
}
```
















