## 객체지향프로그래밍6
--- 
### 추상클래스(abstract class)
+ 추상클래스란 실체클래스의 공통적인 부분(변수,메서드)을 추출해서 선언한 클래스
+ 인스턴스(객체)는 생성할 수 없으며 상속을 통해서 자손클래스에 의해서만 완성될 수 있다.
+ 'abstract' 키워드를 붙이기만 하면 된다. 클래스의 선언부에 추상메서드가 있으면 상속을 통해서 구현해주어야 한다는 것을 알 수 있다.
+ `abstract class 클래스이름{ ... }`
+ 추상클래스에도 생성자가 있으며 멤버변수와 메서드도 가질 수 있다.
+ 추상클래스 사용이유 : 공통된 기능을 상속받아 사용하므로써 시간이 절약됨

### 추상메서드(abstract method)
+ 선언부만 작성하고 구현부는 작성하지 않은 채로 남겨 둔 것이 추상메서드이다.(즉, 설계만 해놓고 수행될 내용은 작성하지 않은 것)
+ 구현부를 작성하지 않고 남겨둔 이유는 상속받는 클래스에 따라 내용이 달라질 수 있기 때문이다. 
+ `abstract 리턴타입 메서드이름();`
+ 추상클래스로부터 상속받는 자식클래스는 오버라이딩을 통해 조상인 추상클래스의 추상메서드를 모두 구현해주어야 한다. 만일 하나라도 구현하지 않는다면 그 자손클래스 역시 추상클래스로 지정해 주어야한다.
```java
abstract class Player{ //추상 클래스
  abstract void play(int pos);  // 추상메서드
  abstract void stop(); // 추상메서드
}
class Auto extends Player{
// 오버라이딩으로 추상메서드  모두 구현
  void play(int pos){ ...} 
  void stop(){...}  // 
}
abstract class AbstractPlayer extends Player{ // 그 자식클래스도 추상클래스로 지정해 주어야 한다.
  void play(int pos){...} //Player에 있는 메서드 중 하나라도 빠지면 
}
```
### 추상클래스의 작성
+ 추상화 : 기존의 클래스의 공통부분(변수,메서드)을 뽑아내서 조상 클래스를 만드는 것(상속 - 자손 클래스를 만드는데 조상 클래스를 사용하는 것 / 구체화 : 상속을 통해 클래스를 구현)
+ 기능에 맞게 자식클래스에서 구현부를 작성해주는 예제(라디오나 VCR같은 재생 가능한 Player를 클래스로 작성할 때)
```java
abstract class Player{
  boolean pause;  // 일시정지 상태를 저장하기 위한 변수
  int currentPos; // 현재 Play되고 있는 위치를 저장하기 위한 변수
  
  Player(){ // 추상클래스도 생성자가 있어야 한다.
    pause = false;
    currentPos = 0;
  }
  // 지정된 위치에서 재생을 시작하는 기능이 수행하도록 작성
  abstract void play(int pos); //추상 메서드
  // 재생을 즉시 멈추는 기능을 수행하도록 작성
  abstract void stop(); //추상 메서드
  
  void play(){
    play(currentPos); // 추상 메서드 사용 가능
  }
  
  void pause(){
    if(pause){ // pause가 true일 때 -> 정지상태 이때 pause가 호출되면
      pause = false; // pause의 상태를 false로 바꾸고
      play(currentPos); // 현재 위치에서 play한다.
    }else{ // pause가 flase일 때 -> 작동상태 이때 pause가 호출되면
      pause = true; // pause 상태를 true로 바꾸고
      stop(); // play를 멈춘다.
    }
  }
}
// 추상 클래스인 Player를 조상으로 하는 CDPlayer클래스(자식클래스)
class CDPlayer extends Player{
  void play(int currentPos){ // 오버라이딩
    /*조상의 추상메서드를 구현*/
  }
  void stop(){ //오버라이딩
    /*조상의 추상메서드를 구현*/
  }
  //CDPlayer클레스에 추가로 정의된 멤버(CD 플레이어의 기능에 맞게 가능 추가)
  int currentTrack; // 현재 재생중인 트랙
  
  void nextTrack(){
    currentTrack++;
    ...
   }
   
   void preTrack(){
    if(currentTrack > 1){
        currentTrack--;
    }
    ...
   }
}
```
+ 기존의 클래스로부터 공통된 부분을 뽑아내어 추상클래스를 만드는 예제(게임에 나오는 유닛들을 클래스로 정의)
```java
// 위치라는 공통된 기능으로 추상클래스 만듦
abstract class Unit{
  int x,y;
  abstract void move(int x, int y); // 위치 이동기능을 추상메서드로 만듦
  void stop(){ /*현재 위치에 지정*/}
}
class Marine extends Unit{ //보병
  void move(int x, int y){/*지정된 위치로 이동*/} //추상 메서드 구현
  void stimPack(){/*스팀팩 사용*/}
}
class Tank extends Unit{
  void move(int x, int y){/*지정된 위치로 이동*/} //추상 메서드 구현
  void changeMode(){/*공격모드 변환*/}
}
class Dropship extends Unit{
  void move(int x, int y){/*지정된 위치로 이동*/} //추상 메서드 구현
  void load(){/*선택된 대상을 태움*/}
  void unload(){/*선택된 대상을 내림*/}
}

// 다른 방법으로 다형성에 따라 공통된 위치 이동 기능을 Unit클래스 타입읠 참조변수 배열을 통해서 서로 다른 종류의 인스턴스를 하나의 묶음으로 다룰 수 있다.
// 다형성 : 조상 클래스타입의 참조변수로 자손 클래스의 인스턴스를 참조하는 것이 가능하다. 즉, 조상 클래스타입의 배열에 자손 클래스의 인스턴스를 담을 수 있다.
// 메인
Unit[] group = new Unit[4];
group[0] = new Marine();
group[1] = new Tank();
group[2] = new Marine();
group[3] = new Dropship();

for(int i=0; i< group.length; i++){
  group[i].move(100,200); // Unit배열의 모든 유닛을 좌표(100,200)의 위치로 이동시킴
}
```
---

### 인터페이스(interface)
+ 인터페이스는 동일한 목적 하에 동일한 기능을 수행하게끔 강제하는 것이다.  
예를들어, A학생은 PPT로 논문을 11일날 제출했다. B학생은 EXCEL 2016 로 논문을 12일날 제출했다. C학생은 EXCEL 2019 로 자기만의 색깔을 입혀 표 형식으로 12일날 제출했다.  
결과물이 너무 개성이 넘친다..이건 평가할 수 없을 것 같다. 왜 이런일이 일어날까? 가이드 라인 또는 어떠한 규격이 없기 때문에 발생하는 문제이다.  
따라서 교수는 다시 명확한 가이드라인 있는 논문을 쓰라고 지시해야한다. 이런 가이드라인을 제시해 주는 것이 인터페이스의 기능이다.  
+ 인터페이스는 interface 키워드를 통해 선언할 수 있으며 implements 키워드를 통해 일반 클래스에서 인터페이스를 구현할 수 있다.    
🎈 JAVA jdk 1.8 이전까지는 상수, 추상메소드만 선언이 가능하지만, JAVA jdk 1.8 이후부터 디폴트메소드, 정적메소드가 추가되었다. 🎈  
```java
public interface 인터페이스명 {
  //상수
  public static final 타입 상수명 = 값; // 제어자 생략가능 -> final int heart =3;

  //추상 메소드
  public abstract 타입 메소드명(매개변수, ... ); //제어자 생략가능 -> String getCardKind();

  //디폴트 메소드
  default 타입 메소드명(매개변수, ... ){

    //구현부

  }

  //정적 메소드
  static 타입 메소드명(매개변수) {

    //구현부

  }
}
```
🎗 상수 : 인터페이스에서 값을 정해줄테니 함부로 바꾸지 말고 제공해주는 값만 참조해라 (절대적)  

🎗 추상메소드 : 가이드만 줄테니 추상메소드를 오버라이팅해서 재구현해라. (강제적)  

🎗 디폴트메소드 : 인터페이스에서 기본적으로 제공해주지만, 맘에 안들면 각자 구현해서 써라. (선택적)  
   예- 정부에서 모든 은행에게 휴면계좌를 찾아주는 서비스를 도입하라고 한다면 각자의 기술과,기능 등이 다르기 때문에 유지보수가 굉장히 힘들어진다. 이때 쓰이는 것이 다.

🎗 정적메소드 : 인터페이스에서 제공해주는 것으로 무조건 사용 (절대적)  
+ 인터페이스의 상속
  + 인터페이스는 인터페이스로부터만 상속 가능
  + 클래스와 달리 다중상속 가능 
  ```java
  interface Moveable{
    void move(int x, int y);
  }
  interface Attackable{
    void attack(Unit u);
  }
  interface Fightable extends Movable, Attckable{...} //인터페이스로부터만 상속, 다중상속
  ```
+ 인터페이스 구현
  + 추상클래스처럼 상속을 통해 구현부를 만들어주어야 한다.(보통 인터페이스이름은 ~able로 끝나는 것이 많음)
  ```java
  class 클래스이름 implements 인터페이스이름{
    //인터페이스에 정의된 추상메서드를 구현해야 한다.
  }
  
  class Fighter implements Fightable{
    public void move(int x, int y){...}
    public void attack(Unit u){...}
  }
  ```
  + 인터페이스 메서드 중 일부만 구현한다면, abstract을 붙여 추상클래스로 선언해야 한다.
  ```java
  abstract class Fighter implements Fightable{
     public void move(int x, int y){...}
  }
  ```
  + 상속과 구현을 동시에 할 수 있다.
  ```java
  class Fighter extends Unit implements Fightable{
    public void move(int x, int y){...}
    public void attack(Unit u){...}
  }
  ```
+ 인터페이스의 다형성
  + 해당 인터페이스 타입의 참조변수로 이를 구현한 클래스의 인스턴스를 참조할 수 있으며, 인터페이스 타입으로 형변환도 가능하다.
  ```java
  Fightable f = (Fightable)new Fighter(); //  Fightable f = new Fighter();
  // Fightable타입의 참조변수 f로는 인터페이스 Fightable에 정의된 멤버들만 호출이 가능
  ```
  + 리턴타입이 인터페이스라는 것은 메서드가 해당 인터페이스를 구현한 클래스의 인스턴스를 반환한다는 것을 의미한다. 
  ```java
  Fightable method(){// method()의 리턴타입은 Fightable
  // 아래 두 문장을 한 문장으로 return new Fighter();
    Fighter f= new Fighter();
    return f; //그래서 Fightable인터페이스를 구현한 Fighter클래스의 인스턴스를 반환
  }
  ```
+ 인터페이스를 이용한 예제) 은행은 금융결제원에서 정의한 어떠한 가이드를 따라야한다했을 때, Bank 라는 이름으로 인터페이스를 만든다.
```java   
public interface Bank {

	//상수 (최대 고객에게 인출해 줄 수 있는 금액 명시)
	public int MAX_INTEGER = 10000000;
	
	//추상메소드(인출하는 메소드)
	void withDraw(int price);
	
	//추상메소드(입금하는 메소드)
	void deposit(int price);
	
	//JAVA8에서 가능한 defualt 메소드(고객의 휴면계좌 찾아주는 메소드 : 필수구현은 선택사항)
	default String findDormancyAccount(String custId){
		System.out.println("**금융개정법안 00이후 고객의 휴면계좌 찾아주기**");
		System.out.println("**금융결제원에서 제공하는 로직**");
		return "00은행 000-000-0000-00";
	}
	
	//JAVA8에서 가능한 정적 메소드(블록체인 인증을 요청하는 메소드)
	static void BCAuth(String bankName){
		System.out.println(bankName+" 에서 블록체인 인증을 요청합니다.");
		System.out.println("전 금융사 공통 블록체인 로직 수행");
	}
}
```
어느 은행이든 은행 시스템은 운영하려면 Bank라는 인터페이스 가이드에 맞게 구현해야한다. 인출메소드, 입금메소드는 각 은행에서 오버라이딩 해서 재구현을 해야하며 블록체인 인증 메소드는 무조건 금융결제원에서 제공해주는 메소드를 사용해야 한다.    
따라서 정적메소드로 구현하여 오버라이딩을 할 수 없게 만들었다. (그대로 가져다 써라), default메소드는 이미 운영되고 있는 시스템에서 추가 요건으로 인해 불가피하게 반영을 해야할 때 쓰면 효과적!!

Bank 인터페이스를 통해 각자에 맞는 스타일대로 은행 인출/입금 서비스를 제공하는 코드
```java
public class KBBank implements Bank{

	@Override
	public void withDraw(int price) {
		System.out.print("KB은행만의 인출 로직");
		if(price < Bank.MAX_INTEGER){
			System.out.println(price+" 원을 인출한다.");	
		}else{
			System.out.println(price+" 원을 인출실패.");	
		}
	}
	@Override
	public void deposit(int price) {
		System.out.println("KB은행만의 입금 로직: "+price+" 원을 입금한다.");
	
	}
}
```
KB은행은 휴면계좌 찾아주기 메소드를 재구현하지 않았다. 즉, 금융결제원이 제공해주는 메소드를 사용할지 말지 아직 정하지 못한 것.
```java
public class SHBank implements Bank{

	@Override
	public void withDraw(int price) {
		System.out.println("SH은행만의 인출 로직");
		if(price < Bank.MAX_INTEGER){
			System.out.println(price+" 원을 인출한다.");	
		}else{
			System.out.println(price+" 원을 인출실패.");
		}
	}

	@Override
	public void deposit(int price) {
		System.out.println("SH은행만의 입금 로직: "+price+" 원을 입금한다.");
		System.out.println("SH은행은 별도의 후행처리 작업을 따로 한다.");
	
	}
	
	//JAVA8에서 가능한 defualt 메소드(고객의 휴면계좌 찾아주는 메소드)
	@Override
	public String findDormancyAccount(String custId){
		System.out.println("**금융개정법안 00이후 고객의 휴면계좌 찾아주기**");
		System.out.println("*SH은행만의 로직 적용*");
		return "00은행 000-000-0000-00";
	}
}
```
SH은행은 휴면계좌 찾아주기 메소드를 재정의하여 SH은행사 만의 휴면계좌 찾아주기 로직을 재구현했다. 

```java
public class KakaoBank{

	public void kakaoWithDraw(int price) {
		System.out.print("Kakao은행만의 인출 로직...");
		System.out.println(price+" 원을 인출한다.");	
	}

	public void kakaoDeposit(int price) {
		System.out.println("Kakao은행만의 입금 로직..."+price+" 원을 입금한다.");
	}
	
	public void kakaoFindDormancyAccount(){
		System.out.println("kakao은행만의 휴면계좌 찾아주기 로직");
	}
}
```
카카오뱅크는 implements를 하지 않은채 자신의 메소드만을 구현해 놓았다.  
이렇게 되면 메인에서 `bank = new kakaoBank(); 부분에서 type mismatch 에러가 난다.` 인스턴스만 바꾸면 호환성이 보장된 상태에서 동일한 기능을 수행할 수 있다
```java
//호환성 불가
		/*
		bank = new KakaoBank();
		bank.withDraw(100);
		bank.deposit(100);
		bank.findDormancyAccount("4311");
		*/

//호환가능
KaKaoBank kaKaoBank = new KaKaoBank();
kaKaoBank.kakaoWithDraw(100);
kaKaoBank.kakaoDeposit(100);
kaKaoBank.kakaoFindDormancyAccount("1234");
```
메인
```java
public class Main {

	public static void main(String[] args) {
		
		Bank bank = new KBBank();
		bank.withDraw(100);
		bank.deposit(100);
		bank.findDormancyAccount("763231");
		Bank.BCAuth("KBBank");
		
		System.out.println("\n*************인스턴스 교체!!***************\n");
		
		bank = new SHBank();
		bank.withDraw(100);
		bank.deposit(100);
		bank.findDormancyAccount("4311");
		Bank.BCAuth("SHBank");
		
		System.out.println("\n*************카카오은행 연동 실패!!***************\n");
		//호환성 불가
		/*
		bank = new KakaoBank();
		bank.withDraw(100);
		bank.deposit(100);
		bank.findDormancyAccount("4311");
		*/
		
		System.out.println("\n*************대학교 주은행  교체!!***************\n");
		
		bank = new KBBank(); //new KBBank();
		bank.withDraw(20000);
		bank.deposit(1000);
		bank.findDormancyAccount("855512");
		Bank.BCAuth("SHBank");

	}

}
```
---

### 내부 클래스(inner class)
+ 내부 클래스는 클래스 내에 선언된 클래스이다.
+ 내부 클래스의 장점: 내부 클래스에서 외부 클래스의 멤버들을 쉽게 접근할 수 있다. 코드의 복잡성을 줄일 수 있다.(캡슐화)
+ 내부 클래스가 컴파일되면 클래스 파일명이 `외부 클래스명$내부 클래스명.class`형식으로 된다.
+ 내부클래스와 외부 클래스에 선언된 변수의 이름이 같을 때 변수 앞에 `this` 또는 `외부클래스명.this`를 붙여서 구별한다.
```java
class A{ // 외부 클래스
  class B{ // 내부 클래스
  }
}
```
+ 내부 클래스의 종류와 특징
<img width="500" height="300" src="https://t1.daumcdn.net/cfile/tistory/2108254A594B680241">

+ 내부 클래스의 선언
```java
class Outer{
  class InstanceInner{ } // 인스턴스클래스 
  static class StaticInner{ } // static클래스
  
  void method(){
    class LocalInner{ } //지역클래스
  }
}
```

+ 내부 클래스의 제어자와 접근성
```java
class Outer{
//내부클래스에 제어자를 붙일 수 있다.
  private class InstanceInner{ }
  protected static class StaticInner{ }
  
  void method(){
    class LocalInner{ }
  }
}
```
🎗 내부 클래스는 외부 클래스의 멤버와 같이 간주되고, 인스턴스 멤버와 static멤버 간의 규칙이 내부 클래스에도 똑같이 적용된다.  
🎗 내부 클래스도 클래스이기 때문에 abstract, final과 같은 제어자를 사용할 수 있을 뿐만 아니라 멤버 변수들처럼 private,protected와 접근 제어자도 사용 가능하다.  
🎗 내부 클래스 중에서 static class만 static멤버를 가질 수 있다. 드물지만 내부 클래스에 static변수를 선언해야 한다면 static class로 선언해야 한다. 
다만 final과 static이 동시에 붙은 변수는 상수이므로 모든 내부 클래스에서 정의가 가능하다.

```java
class Test{
  class InstanceInner{} // 내부클래스(인스턴스 클래스)
  static class StaticInner{} //내부클래스(static 클래스)
  
  //인스턴스 멤버 간에는 서로 직접접근 가능
  InstanceInner iv = new InstanceInner();
  
  //static 멤버들 간에도 서로 직접접근이 가능
  static StaticInner cv = new StaticInner();
  
  staitc void staticMethod(){
    //static멤버는 인스턴스멤버에 직접 접근할 수 없음
    //InstanceInner obj1 = new InstanceInner(); 이거 안됨
    StaticInner obj2 = new StaticInner();
    
    //접근하려면 아래와 같이 객체를 생성해야 함
    //인스턴스 클래스는 외부 클래스를 먼저 생성해야만 생성할 수 있음.
    Test outer = new Test();
    InstanceInner obj1 = outer.new InstanceInner();
  }
  
  void instanceMethod(){
    // 인스턴스 메서드에서는 인스턴스멤버와 static멤버 모두 접근가능
    InstanceInner obj1 = new InstanceInner();
    StaticInner obj2 = new StaitcInner();
    
    //메서드 내에 지역적으로 선언된 내부클래스는 외부에서 접근할 수 없음
    //LocalInner lv = new LocalInner(); 이거 안됨
  }
  void method(){
  // 메서드 내에 지역적으로 선언된 
    class LocalInner(){}
    LocalInner lv = new LocalInner();
  }
}
```
🎗 인스턴스 클래스는 외부 클래스의 인스턴스 멤버를 객체 생성없이 바로 사용할 수 있지만 staitc클래스는 외부클래스의 인스턴스 멤버를 객체 생성 없이 사용할 수 없다.  
🎗 인스턴스 클래스는 static 클래스의 멤버들을 객체 생성없이 사용할 수 있지만 static 클래스에서는 인스턴스 클래스의 멤버들을 객체 생성 없이 사용할 수 없다.
🎗 jdk 1.8부터 지역 클래스에서 접근하는 지역 변수 앞에 final을 생략할 수 있다.
🎗 내부클래스에서 외부클래스의 private멤버도 접근할 수 있다.


### 익명 클래스(Anonymous class)
+ 익명 클래스는 클래스의 선언과 객체의 생성을 동시에 하기 때문에 단 한 번만 사용될 수 있고 오직 하나의 객체만을 생성할 수 있는 일회용 클래스이다. 
+ 이름이 없기 때문에 생성자도 가질 수 없다.
+  부모 클래스의 이름이나 구현하고자 하는 인터페이스의 이름을 사용해서 정의하기 때문에 하나의 클래스로 상속받는 동시에 인터페이스를 구현하거나 둘 이상의 인터페이스를 구현할 수 없다. 
+  즉, 오로지 단 하나의 클래스를 상속받거나 단 하나의 인터페이스만을 구현할 수 있다.
+  익명클래스는 `외부 클래스명$숫자.class`형식으로 클래스 파일명이 결전된다.
```java
new 부모클래스 이름(){
  //멤버선언
}
//다른 방법
new 구현인터페이스이름(){
  //멤버선언
}

class Test{
  Object iv = new Object(){void method(){}}; //익명 클래스
  static Object cv = new Object(){ void method(){}};//익명 클래스
  
  void myMethod(){
    Object lv = new Object(){ void method(){}};//익명 클래스
  }
}
```
+ 익명클래스로 변환하는 예제
```java
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;

// 화면에 아무것도 나타나지 않은 채 종료된다.
public class InnerTest1 {
    public static void main(String[] args) {
        Button b = new Button("Start");
        b.addActionListener(new EventHandlers());
    }
}

class EventHandlers implements ActionListener{
    public void actionPerformed(ActionEvent e){
        System.out.println("ActionEvent occurred><");
    }
}
```
익명클래스로 변환
```java
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnonymousTest2 {
    public static void main(String[] args) {
        Button b = new Button("Start");
        b.addActionListener(new ActionListener(){ // 구현인터페이스이름으로 익명클래스 생성
            public void actionPerformed(ActionEvent e) {
                System.out.println("ActionEvent occurred><");
            }
        });
    }
}
```















