### String배열
---
+ 문자열의 배열 선언과 생성은 int배열과 다르지 않다.
+ 16진수를 2진수로 바꾸기
```java
public class StringArray1 {
    public static void main(String[] args) {
        char[] hex = {'C','A','F','E'}; //16진수에는 A~F까지 6개의 문자가 포함되므로 char[]배열로 처리
        String[] binary = {"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011","1100","1101","1110","1111"};
        // 2진수 16개의 값을 저장
        String result ="";

        for(int i=0; i<hex.length;i++){
            if(hex[i] >='0' && hex[i]<='9'){ //숫자인 경우
                result += binary[hex[i]-'0'];
            }else{ // A ~ F인 경우
                result += binary[hex[i]-'A'+10];
            }
        }
        System.out.println("hex:"+ new String(hex)); // String(char[] value)
        System.out.println("binary:"+ result);
    }
}
```
실행결과
```java
hex:CAFE
binary:1100101011111110
```
+ char배열과 String클래스
  + String클래스는 char배열에 기능(메서드)을 추가한 것이다.
  + C언어에서는 문자열을 char배열로 다루지만 객체지향언어인 자바에서는 char배열과 그에 관련된 기능을 함께 묶어서 클래스에 정의한다.
  + 🎁char배열과 String클래스의 차이🎁  
  String객체(문자열)는 읽을 수 있을 뿐 내용을 변경할 수 없다. 변경 가능한 문자열을 다루려면 StringBuffer클래스를 사용하면 된다. 
+ char배열과 String클래스의 변환
```java
public class StringArray2 {
    public static void main(String[] args) {
        String src ="abcde";
        for(int i=0; i< src.length(); i++){
            char ch = src.charAt(i); // src의 i번째 문자를 ch에 저장
            System.out.println("src.charAt("+i+"):"+ch);

        }
        //String을 char[]로 변환
        char[] chArr = src.toCharArray(); //문자열을 문자배열로 변환하여 반환

        //char배열(char[])을 출력
        System.out.println(chArr);
    }
}
```
실행결과
```java
src.charAt(0):a
src.charAt(1):b
src.charAt(2):c
src.charAt(3):d
src.charAt(4):e
abcde
```
+ 커맨드 라인을 통해 입력받기
  + Scanner클래스의 nextLine() 이외에도 화면을 통해 사용자로부터 값을 입력받을 수 있는 간단한 방법이 있다.
 ```java
 public class StringArray3 {
    public static void main(String[] args) {
        if(args.length !=3){//입력된 값의 개수가 3개가 아니면,
            System.out.println("useage: java StringArray3 num1 op num2");
            System.exit(0); //프로그램 종료
        }
        int num1 = Integer.parseInt(args[0]); // 문자열을 숫자로 변환
        char op = args[1].charAt(0); // 문자열을 문자로 반환
        int num2 = Integer.parseInt(args[2]);
        int result=0;
        switch (op){
            case '+':
            result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                System.out.println("지원되지 않는 연산");
        }
        System.out.println("결과:"+result);
    }
}
```
실행결과(터미널에서 실행)
```java
PS C:\JAVA_pr\StringArray> 10 + 4
14
PS C:\JAVA_pr\StringArray> 10 * 5
50
```
