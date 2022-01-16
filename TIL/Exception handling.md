## 예외처리(exception handling)
---
+ 프로그램 오류
  + 컴파일 에러: 컴파일 시에 발생하는 에러
  + 런타임 에러: 실행 시에 발생하는 에러  
  (예- ArrayIndexOutOfBoundsException : 배열의 범위를 벗어났을 경우, NullPointerException : 값이 null인 참조변수의 멤버를 호출하려 했을 경우, ClassCastException : 클래스간에 형변환을 잘못했을 경우, ArithmeticException : 정수를 0으로 나누려고 하는 경우)
  + 논리적 에러: 실행은 되지만 의도와 다르게 동작하는 것
+ 예외처리하기(try-catch-finally)
```java
try{
///예외가 발생할 가능성이 있는 코드
.... ......... ;
.............. .. ; << 1. 여기서 예외가 발생했을 때
.............. .... ; << 2. 예외 발생한 곳 아래는 실행하지 않고,
}
catch(예외클래스 e) {
   예외 처리; << 3. catch문에서 예외처리를 한다.
}
finally {
  //finally는 생략 가능
///무슨 일이 있든 항상 실행
}
```
+ finally블럭
  + try 블럭이 끝날 때 항상 수행되는 블럭
  + Exception 이 발생하더라도 또는 return, continue, 또는 break 등을 만나더라도
  
+ 다중 catch문
```java
try{
// 예외 1 발생위치
// 예외 2 발생위치
}
catch(예외1을 잡는 곳){
}
catch(예외2를 잡는 곳){
}
```
+ printStackTrace()와 getMessage()
  + catch블럭의 괄호에 선언된 참조변수를 통해 이 인스턴스에 접근할 수 있다.(이 참조변수는 catch문 내에서만 사용가능)
  + printStackTrace() : 예외발생 당시의 호출스택(Call Stack)에 있었던 메서드의 정보와 예외 메시지를 화면에 출력
  + getMessage() : 발생한 예외클래스의 인스턴스에 저장된 메시지를 얻을 수 있음
  ```java
      public class Exception1 {
        public static void main(String[] args) {
            System.out.println(1);
            System.out.println(2);
            try{
                System.out.println(3);
                System.out.println(0/0); //예외 발생
                System.out.println(4); // 실행 안됨
            }catch (ArithmeticException ae){
                ae.printStackTrace(); // 참조변수 ae를 통해 생성된 ArithmeticException인스턴스에 접근 가능
                System.out.println("예외처리 메시지: "+ ae.getMessage());
            }
            System.out.println(6);
        }
    }
  ```
 실행결과
 ```java
  1
  2
  3
  java.lang.ArithmeticException: / by zero
    at Exception1.main(Exception1.java:7)
  예외처리 메시지: / by zero
  6
  ```
+ 멀티 catch 블럭
  + jdk1.7부터 '|'기호를 이용해서 여러 catch블럭을 하나의 catch블럭으로 합칠 수 있게 되었다.
  + 단, 멀티 catch블럭에 '|'기호로 연결된 예외 클래스들의 공통 분모인 조상 예외 클래스에 선언된 멤버만 사용할 수 있다.
  ```java
   try{
    ...
   }catch(Exception1 | Exception2 e){
    e.printStackTrace();
   } 
  ```
+ 예외 떠넘기기(throw)
  + 메소드 내부에서 예외가 발생할 수 있는 코드를 작성할 때, try-catch 블록으로 예외 처리 하는 것이 기본이지만, 경우에 따라서는 메소드를 호출한 곳으로 예외 처리를 떠넘길 수도 있다.
  + 방법: 1) 연산자 new를 이용해서 발생시키려는 예외 클래스의 객체를 만든다. `Exception e =new Exception("고의로 발생시킴");`  2) 키워드 throw를 이용해서 예외를 발생시킴 `throw e;`
  ```java
     public class Exception2 {
      public static void main(String[] args) {
          try{
              // 두개 합침 => throw new Exception("고의로 발생시킴");
              Exception e = new Exception("고의로 발생시킴");
              throw e; // 예외 발생시킴
          }catch (Exception e){
              System.out.println("에러 메시지:"+ e.getMessage());
              e.printStackTrace();
          }
          System.out.println("프로그램 정상 종료");
      }
  }

  ```
+ 메서드에 예외 선언하기(throws)
 ```java
 void method() throws Exception1, Exception2, ...{
   // 메서드 내용
 }
 ```
+ Exception을 throw 한 곳이 있으면 반드시 어디선가에서는 try-catch문으로 예외처리를 해야 한다.
```java
class ExceptionEx15 {
    public static void main(String[] args) {
        // command line에서 입력받은 값을 이름으로 갖는 파일을 생성한다.
        File f = createFile(args[0]);
        System.out.println( f.getName() + " 파일이 성공적으로 생성되었습니다.");
    }

    static File createFile(String fileName) {
        try {
            if (fileName==null || fileName.equals(""))
                throw new Exception("파일이름이 유효하지 않습니다.");
        } catch (Exception e) {
             // fileName이 부적절한 경우, 파일 이름을 '제목없음.txt'로 한다.
            fileName = "제목없음.txt";
        } finally {
            File f = new File(fileName); // File클래스의 객체를 만든다.
            createNewFile(f);           // 생성된 객체를 이용해서 파일을 생성한다.
            return f;
        }
    }   // createFile메서드의 끝

    static void createNewFile(File f) {
        try {
            f.createNewFile();      // 파일을 생성한다.
        } catch(Exception e){ }
    }
}
```

🎉 `try-catch`와 `throws`의 차이점 🎉  
1. throws는 예외를 넘기고 try~catch는 예외를 처리한다.  
2. throws는 예외 발생 이후의 코드는 실행하지 않고, try~catch는 예외 발생 이후의 코드를 실행한다.

+ 자동 자원 반환 - try-with-resources문
  + jdk1.7부터 추가된 try-catch문 변형
  + java.lang.AutoCloseable
  + Suppressed Exception : try-catch 절에서 예외가 발생하면 AutoCloseable.close() 에서 발생할 수도 있는 예외는 억제된 예외로 처리된다.
  + 따로 메소드를 호출해주지 않아도 try블럭을 벗어나는 순간 자동으로 close()메소드 호출됨(예제)
```java
try (FileWriter f = new FileWriter("OutFile.txt");
     PrintWriter out = new PrintWriter(f)) { //괄호 안에 두 문장이상을 넣을 시 ';'로 구분
    ...
} catch (IOException e) {
    ...
}
```
+ 사용자정의 예외 만들기
  + 프로그래머가 필요에 따라 새로운 예외 클래스를 정의할 수 있다.
  ```java class MyException extends Exception{
  MyException(String msg){ //문자열을 매개변수로 받는 생성자
    super(msg); // 조상인 Exception클래스의 생성자를 호출한다.
    }
  }
  ```
  + 필요에 따라 변수나 메서드를 추가할 수 있다.(String을 매개변수로 받는 생성자를 추가해주어야 한다.)
  ```java
    class MyException extends Exception {
      private final int ERR_CODE; // 생성자를 통해 초기화

      MyException(String msg, int errCode) { // 생성자
          super(msg);
          ERR_CODE = errCode;
      }

      MyException(String msg) { // 생성자
          this(msg, 100); // ERR_CODE를 100(기본값)으로 초기화
      }

      public int getErrCode() { // 에러 코드를 얻을 수 있는 메서드 추가
          return ERR_CODE; // 이 메서드는 주로 getMessage()와 함께 사용해서 메시지를 얻을 수 있다.
      }
  }
  ```











