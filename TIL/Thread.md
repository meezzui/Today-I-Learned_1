### 쓰레드(Thread)
---
#### 프로세스와 쓰레드
+ 프로세스(process) : 실행 중인 프로그램(program)으로 프로그램을 수행하는데 필요한 데이터와 메모리 등의 자원 그리고 쓰레드로 구성되어 있다.
+ 쓰레드(thread) : 프로세스의 자원을 이용해서 실제로 작업을 수행하는 것.
+ 프로세스의 메모리 한계에 따라 생성할 수 있는 쓰레드의 수가 결정된다.
+ 멀티캐스팅 : 여러 개의 프로세스가 동시에 실행되는 것.
+ 멀티쓰레딩 : 하나의 프로세스 내에서 여러 쓰레드가 동시에 작업을 수행하는 것.
+ 멀티쓰레딩 장점
  + CPU의 사용률을 향상 시킨다.
  + 자원을 보다 효율적으로 사용할 수 있다.
  + 사용자에 대한 응답성이 향상된다.
  + 작업이 분리되어 코드가 간결해진다.
+ 멀티쓰레딩 단점
  + 여러 쓰레드가 같은 프로세스 내에서 자원을 공유하면서 작업을 하기 때문에 동기화(synchronization), 교착상태(deadlock) 같은 문제들을 고려해서 프로그래밍 해야 한다.

#### 쓰레드의 구현과 실행
+ 쓰레드의 구현방법 두 가지
  + Thread클래스를 상속받는 방법 
  ```java
  class MyThread extends Thread{
    public void run(){ // Thread클래스에 run()을 오버라이딩
      ....
    }
  }
  ```
  + Runnable인터페이스를 구현하는 방법(재사용성이 높고 코드의 일관성을 유지할 수 있기 때문에 객체지향적인 방법)
  ```java
  class MyThread implements Runnable{
     public void run(){ // Runnable인터페이스에 run() 구현
      ....
    }
  }
  ```
+ 쓰레드를 구현하는 2가지 방법 차이
```java
public class ThreadEx1 {
    public static void main(String[] args) {
        ThreadEx1_1 t1 = new ThreadEx1_1(); // Thread의 자손 클래스의 인스턴스를 생성

        Runnable r = new ThreadEx1_2(); // Runnable을 구현한 클래스의 인스턴스를 생성
        Thread t2 = new Thread(r); // 생성자 Thread(Runnable target)

      //쓰레드의 실행 - start() : 하나의 쓰레드에 대해 start()가 한 번만 호출될 수 있다. 다시 실행하려면 인스턴스를 다시 생성해주고 실행해줘야 함.
        t1.start(); //쓰레드 t1을 실행시킨다.
        t2.start(); //쓰레드 t2을 실행시킨다.
    }
}

class ThreadEx1_1 extends Thread {
    public void run() {
        for(int i = 0; i < 5; i++) {
            // 조상인 Thread 클래스 getName() 메소드 직접 호출 가능
            System.out.println(getName());
        }
    }
}

class ThreadEx1_2 implements Runnable {
    @Override
    public void run() {
        for(int i = 0; i < 5; i++) {
            // Runnable을 구현하면 Thread클래스의 currentThread()를 호출하여 
            // 쓰레드에 대한 참조를 얻어와서 호출 해야 함
            System.out.println(Thread.currentThread().getName());
        }
    }
}
```

#### start()와 run() 차이
+ run(): main메서드에서 run()을 호출하는 것은 생성된 쓰레드를 실행시키는 것이 아니라 단순히 클래스에 선언된 메서드를 호출하는 것 뿐이다.
+ start() : 새로운 쓰레드가 작업을 실행하는데 필요한 호출스택을 생성한 다음에 run()을 호출해서 생성ㄷ된 호출스택에 run()이 첫 번째로 올라가게 한다.
```java
main 메서드에서 run()을 호출하는 경우의 call stack
  |        |
  ----------
  |run     |
  ----------
  |main    |
  ----------

 main 매서드에서 start()를 호출하는 경우
 #step 1. main메서드에서 쓰레드의 start()을 호출한다.
  |        |
  ----------
  |start   |
  ----------
  |main    |
  ----------
 #step 2. start()는 새로운 쓰레드를 생성하고, 쓰레드가 작업하는데 사용될 호출스택을 생성한다.
  |        |     |        | 
  ----------     |        |
  |start   |     |        |
  ----------     |        |
  |main    |     |        |
  ----------     ----------  
 #step 3. 새로 생성된 호출스택에 run()이 호출되어, 쓰레드가 독립된 공간에서 작업을 수행한다.
  |        |     |        | 
  ----------     |        |
  |start   |     |        |
  ----------     ----------
  |main    |     |run     |
  ----------     ----------
 #step 4. 스케줄러가 정한 순서에 의해서 호출스택이 번갈아 가며 실행된다.
  |        |     |        |
  |        |     |        |
  ----------     ----------
  |main    |     |run     |
  ----------     ----------
 #step 5. mian메서드가 종료되면 호출스택이 비워지면서 프로그램도 종료된다. 단, 실행 중인 사용자 쓰래드가 하나도 없을 시
  |        |     |        |
  |        |     |        |
  |        |     ----------
  |        |     |run     |
  ----------     ----------
```
🎁 main메서드의 작업을 수향하는 것도 쓰레드이며, 'main쓰레드'라고 한다.  

#### 쓰레드의 종류
+ 사용자 쓰레드(user thread) : 사용자 레벨의 라이브러리를 통해 구현되며, 라이브러리는 스레드의 생성 및 스케줄링 등에 관한 관리 기능을 제공한다.
  + 장점: 속도가 빠르다.
  + 단점: 여러 개의 사용자 스레드 중 하나의 스레드가 시스템 호출 등으로 중단되면 나머지 모든 스레드 역시 중단된다.
+ 데몬 쓰레드(deamon thread) : 주 스레드의 작업을 돕는 보조적인 역할을 수행하는 스레드. 주 스레드가 종료되면 데몬 스레드는 강제적으로 자동 종료된다.
  + 예) 워드프로세서의 자동 저장, 미디어 플레이어의 동영상 및 음악 재생, 가비지컬렉터, 워드프로세서의 자동저장, 화면 자동 갱신 
  + 데몬 쓰레드는 무한 루프와 조건문을 이용해서 실행 후 대기하고 있다가 특정 조건이 만족되면 작업이 수행되고 다시 대기하도록 작성한다.
  + 데몬 쓰레드는 일반 쓰레드이 작성방법과 실행방법이 같으며 다만 쓰레드를 생성한 다음 실행하기 전에 `setDaemon(true)`를 호출하기만 하면 된다.
  ```java
  boolean isDaemon() // 쓰레드가 데몬 쓰레드인지 확인한다. 데몬 쓰레드이면 true를 반환.
  void setDaemon(boolean on) // 쓰레드를 데몬 쓰레드로 또는 사용자 쓰레드로 변경한다. 매개변수 on의 값을 ture로 지정하면 데몬 쓰레드가 된다.
  ```
  + 데몬 쓰레드 예제(3초마다 변수 autoSave의 값을 확인해서 그 값이 true이면, autoSave()를 호출하는 일을 무한반복하는 쓰레드. 만일 데몬 쓰레드로 설정하지 않았다면 이 프로그램은 강제종료하지 않는 한 영원히 종료되지 않을 것이다.)
  ```java
    public class ThreadEx10 implements Runnable {
      static boolean autoSave = false;

      public static void main(String[] args) {
          Thread t = new Thread(new ThreadEx10());
          t.setDaemon(true); // **이 부분이 없으면 종료되지 않는다.**
          t.start();

          for(int i = 1; i <= 10; i++) {
              try {
                  Thread.sleep(1000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }

              System.out.println(i);
              if(i==5) {
                  autoSave = true;                
              }
          }

          System.out.println("프로그램을 종료합니다.");
      }

      @Override
      public void run() {
          // **데몬쓰레드 실행을 위한 무한루프 실행 및 특정 조건 일때 실행**
          while(true) {
              try {
                  Thread.sleep(3000);
              } catch (InterruptedException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
              }

              if(autoSave) {
                  autoSave();
              }
          }
      }

      public void autoSave() {
          System.out.println("작업파일이 자동저장되었습니다.");
      }

  }
  ```
+ 커널 스레드(Kernel thread) :  운영체제가 지원하는 스레드 기능으로 구현되며, 커널이 스레드의 생성 및 스케줄링 등을 관리한다.
  + 장점: 스레드가 시스템 호출 등으로 중단되더라도, 커널은 프로세스 내의 다른 스레드를 중단시키지 않고 계속 실행시켜준다.
  + 단점: 사용자 스레드에 비해 생성 및 관리하는 것이 느리다.

#### 싱글 쓰레드와 멀티 쓰레드의 차이 
+ 하나의 쓰레드로 두개의 작업을 수행하는 경우(싱글 쓰레드)
```java
 |  A 작업    |   B 작업   |
```
+ 두개의 쓰레드로 두개의 작업을 수행하는 경우(멀티 쓰레드)
```java
|A|B|A|B|A|B|A|B|A|B|A|B|
```
+ 병렬과 병행
  + 병행(concurrent): 싱글 코어로 두개의 쓰레드를 실행 및 처리하는 경우
  + 병렬(parallel): 멀티 코어로 두개의 쓰레드를 실행 및 처리하는 경우  
+ 두 쓰레드가 서로 다른 자원을 사용하는 작업의 경우에는 싱글쓰레드 프로세스보다 멀티 쓰레드 프로세스가 더 효율적이다. 
+ 예를 들면, 사용자로부터 데이터를 입력받는 작업, 네트워크로 파일을 주고받는 작업, 프린터로 파일을 출력하는 작업과 같이 외부기기와의 입출력이 필요로 하는 경우가 이에 해당된다.


#### 쓰레드의 우선순위
+ 쓰레드가 수행하는 작업의 중요도에 따라 쓰레드의 우선순위를 서로 다르게 지정하여 특정 쓰레드가 더 많은 작업시간을 갖도록 할 수 있다.
+ 예를들어, 파일전송기능이 있는 메신저의 경우, 파일다운로드를 처리하는 쓰레드보다 채팅내용을 전송하는 쓰레드의 우선순위가 높아야 사용자가 채팅하는데 불편함이 없을 것이다. 대신 파일다운로드 작업에 걸리는 시간이 더 길어질 것이다.
+ 쓰레드의 우선순위 지정하기(쓰레드의 우선순위는 쓰레드를 생성한 쓰레드로부터 상속받는다. 예- main메서드를 수행하는 쓰레드는 우선순위가 5이므로 main메서드 내에서 생성하는 쓰레드의 우선순위는 자동적으로 5가 된다.)
```java
 void setPriority(int newPriority) // Thread 우선순위를 지정한 값으로 변경한다.
  int getPriority() // Thread의 우선순위를 반환한다.

   //우선순위 범위는 1~10, 숫자가 높을수록 우선순위가 높다
  public static final int MAX_PRIORITY = 10; //최대 우선순위
  public static final int MIN_PRIORITY = 1; // 최소우선순위
  public static final int NORM_PRIORITY = 5; // 보통 우선 순위
```


#### 쓰레드 그룹(thread group)
+ 쓰레드를 폴더처럼 그룹으로 생성해서 그룹으로 묶어서 관리할 수 있다.
+ 쓰레드 그룹은 보안상의 이유로 도입된 개념으로, 자신이 속한 쓰레드 그룹이나 하위 쓰레드 그룹은 변경할 수 있지만 다른 쓰레드 그룹의 쓰레드를 변경할 수 없다.
+ 쓰레드를 쓰레드 그룹에 포함시키려면 Thread의 생성자를 이용해야 한다.
```java
Thread(ThreadGroup group, String name);
Thread(ThreadGroup group, Runnable target);
Thread(ThreadGroup group, Runnable target, String name);
Thread(ThreadGroup group, Runnable target ,String name, long stackSize);
```

#### 쓰레드의 실행제어
+ 쓰레드의 스케줄링과 관련된 메서드(resume(), stop(), suspend()는 쓰레드를 교착상태(deadlock)로 만들기 쉽기 때문에 중요도가 떨어지게 되었다.)
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbACsNm%2FbtquWNSVCHi%2FF01WgItkSfuM4gk190i3K1%2Fimg.png">

+ 쓰레드의 상태(getState()메서드를 호출해서 확인할 수 있다. JDK1.5부터 추가됨)
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FkF5LY%2FbtquX6cJy0m%2Fzq6Z1ca0QSWwk4kSH87Zv1%2Fimg.png">

+ 쓰레드의 상태 변화
<img width="500" height="400" src="https://rebeccacho.gitbooks.io/java-study-group/content/Thread_state.png">

+ sleep(long millis) - 일정시간동안 쓰레드를 멈추게 한다.
  + sleep()을 호출할 때는 항상 try-catch문으로 예외처리를 해줘야 한다.
```java
class ThreadEx15{
    public static void main(String args[]) {
        A th1 = new A();
        B th2 = new B();

        th1.start();
        th2.start();

        try {
            th1.sleep(5000);    
            // sleep(): 작업 흐름 대기시간 설정한다. 
            // 5초동안 대기시간 갖은 후에 다음 문자의 실행흐름을 이어 나간다.
        } catch(InterruptedException e) {}

        System.out.print("<<main 종료>>");
    } // main
}

class A extends Thread {
    public void run() {
        for(int i=0; i < 300; i++) {
            System.out.print("-");
        }
        System.out.print("<<th1 종료>>");
    } // run()
}

class B extends Thread {
    public void run() {
        for(int i=0; i < 300; i++) {
            System.out.print("|");
        }
        System.out.print("<<th2 종료>>");
    } // run()
}
```
```
결과 : 'th1 종료'가 가장 늦게 종료 될 것으로 예상. 하지만 'th1 종료 -> th2 종료 -> main 종료' 순으로 종료됨.
이유 : sleep()이 항상 실행 중인 쓰레드에 대해 작동하기 때문에 실제 영향을 받는 것은 main 메서드 이기 때문이다. sleep()은 static 으로 선언되어 있으며, 참조 변수를 이용해서 실행하는 것보다 `Thread.sleep(2000);` 과 같이 호출해야 한다.
```

+ interrupt()와 interrupted() - 쓰레드의 작업을 취소한다.
  + 진행중인 쓰레드의 작업이 끝나기 전에 취소시켜야 할 때가 있다. 예를들어, 큰 파일을 다운로드 받을 때 시간이 오래걸려 중단할 때
  + interrupt() : 쓰레드에게 작업을 멈추라고 요청. 단지 요청만 할 뿐 강제종료를 시키지는 못한다. 그저 쓰레드의 interrupted상태(인스턴스 변수)를 바꾸는 것일 뿐이다.
  + interrupted() : interrupt()가 호출되었는지 알려준다. 호출되었다면 true를 호출되지 않았다면 false를 반환한다.
  + interrupt()가 호출 -> interrupted()에서 결과가 false에서 true로 바뀜 -> while문 벗어남
  + 카운트다운 도중 사용자의 입력이 들어오면 카운트다운이 종료되지 않고 끝까지 실행되는 예제(`Thread.sleep(1000);` 이거 때매 안 멈춤)
  ```java
  public class ThreadEx11{
      public static void main(String[] args) {
          ThreadEx11_1 t1 = new ThreadEx11_1();
          t1.start();

          String input = JOptionPane.showInputDialog("아무값이나 입력하시오.");
          System.out.println("input value is " + input);
          t1.interrupt(); //interrupt()를 호출하면 interrupted상태가 true가 된다.

          System.out.println("isInterrupted(): " + t1.isInterrupted()); //true
      }
  }

  class ThreadEx11_1 extends Thread {
      public void run() {
          int i = 10;
          while(i!=0 && !isInterrupted()) {
              System.out.println(i--);

              try {
                  Thread.sleep(1000); // 여기에서 InterruptedException발생 쓰레드의 interrupted상태가 false로 자동 초기화 됨
              } catch (InterruptedException e) {
                  // interrupted(); // 이럴때는 catch블럭에 이렇게 추가해주어서 다시 true로 바꿔주어야 함.
              }
          }
          System.out.println("카운트가 종료되었습니다.")
      }
  }
  ```
  실행결과
  ```java
  10
  9
  8
  input value is TTT
  inInterrupted(): false
  7
  6
  5
  4
  3
  2
  1
  카운트가 종료되었습니다.
  ```
🎉 아래 메소드들은 쓰레드의 실행을 제어하는 가장 손쉬운 방법이지만, suspend()와 stop()이 교착상태(deadlock)을 일으키기 쉽게 작성되어 있으므로 사용이 권장되지는 않는다. (Deprecated) 🎉  
+ suspend(): sleep() 처럼 쓰레드를 멈추게 한다.
+ resume(): suspend() 에 의해 정지된 쓰레드는 resume()을 호출해야 다시 실행 대기 상태가 된다.
+ stop(): 호출되는 즉시 쓰레드가 종료된다.

---
+ yeild() : 다른 쓰레드에게 양보한다. 예를 들어, 스케쥴러에 의해 1초 실행 시간을 할당받는 쓰레드가 0.5초의 시간동안 작업한 상태에서 yeild를 호출되면, 나머지 0.5초는 포기하고 다시 실행 대기 상태가 된다.
+ join() : 쓰레드 자신이 하던 작업을 멈추고 다른 쓰레드가 지정된 시간 동안 작업을 수행하도록 할 때 join()을 사용한다.
  + 예)  `th1.join` : 현재실행중인 쓰레드가 쓰래드 th1의 작업이 끝날때까지 기다린다.
  + join()도 sleep()처럼 interrupt()에 의해 대기상태에서 벗어날 수 있다.
  + try-catch문으로 감싸야 한다. (InterruptedException)
  + sleep()과 다른 점은 join()은 특정 쓰레드에 대해 동작하므로 static 메서드가 아니라는 점이다.
```java
public class ThreadEx19{
    public static void main(String args[]) {
        ThreadEx19_1 gc = new ThreadEx19_1();//가비지 컬렉션역할의 스레드 생성
        gc.setDaemon(true);//GargabeCollection 스레드를 데몬 스레드로 설정
        gc.start();//가비지 컬렉션 스레드 시작
        int requiredMemory = 0;//필요한 메모리의 크기.
        for(int i=0; i < 20; i++) {
            requiredMemory = (int)(Math.random() * 10) * 20;
            //필요한 메모리의 크기를 난수를 사용해서 설정한다. 
            //즉, 컴퓨터에서 동작하는 프로그램에서 사용하려는 메모리의 크기를 흉내낸 것이다. 
            // 필요한 메모리가 사용할 수 있는 양보다 크거나 전체 메모리의 60%이상을
            // 사용했을 경우 gc를 깨운다.
            if(gc.freeMemory() < requiredMemory ||
                gc.freeMemory() < gc.totalMemory() * 0.4) {
                gc.interrupt();//잠자고 있는 데몬 스레드를 깨운다.
            }
            gc.usedMemory += requiredMemory;
            //사용 중인 메모리 크기가 프로그램에서 사용하는 메모리 크기 만큼 증가한다.
            System.out.println("usedMemory:"+gc.usedMemory);
            //사용중인 메모리 크기를 출력
        }
    }
}

class ThreadEx19_1 extends Thread {

    final static int MAX_MEMORY = 1000;
    //메모리의 최대 크기, 즉 메모리 크기가 1000 이상이 될 수 없슴
    int usedMemory = 0;//사용 중인 메모리 크기

    public void run() {
        while(true) {
            try {
                Thread.sleep(10 * 1000);//10초를 자고 일어나서 가비지 컬렉션을 수행한다.
            } catch(InterruptedException e) {
                System.out.println("Awaken by interrupt().");
                //gc.interrupt();에 의해서 스레드가 잠에서 깰 때 이 예외가 발생한다.
            }
            gc();//10초 동안 자고 일어나서 garbage collection을 수행한다. 
            System.out.println("Garbage Collected. Free Memory :" + freeMemory());
            //가비지 컬렉션을 수행한 후의 메모리 크기를 출력한다.
        }
    }

    public void gc() {//가비지 컬렉션을 수행하는 메소드
        usedMemory -= 300;//사용중인 메모리 크기를 줄인다.
        if(usedMemory < 0) usedMemory = 0;//사용중인 메모리 값이 마이너스가 되지 않도록한다.
    }

    public int totalMemory() {//전체 메모리 크기를 알려주는 메소드
        return MAX_MEMORY;
    }

    public int freeMemory() {//사용가능한 메모리 크기를 알려주는 메소드
        return MAX_MEMORY - usedMemory;
    }
}
```
```java
결과: 위의 스레드 프로그램은 메모리의 최대 크기가 1000으로 설정되었기 때문에, 어떠한 경우에도 출력된 메모리의 크기가 1000을 넘을 수 없다. 그러나, 반복해서 실행하다 보면 사용 중인 메모리의 크기가 1000을 넘어가는 현상을 발견할 수 있다.
이유: main 쓰레드가 interrupt() 호출 후에 자신의 작업을 진행하기 때문이다.
해결책: join() 메서드로 main 쓰레드는 잠시 대기

try {
   gc.join(100);//가비지 컬렉션이 0.1초 동안 일을 할 동안 기다린다.
} catch (InterruptedException e) { }
```

#### 쓰레드의 동기화
+ 싱글 쓰레드 프로세스의 경우 프로세스 내에서 단 하나의 스레드만 작업하기 때문에 프로세스의 자원을 가지고 작업하는데 별 문제가 없다.
+ 그러나 멀티 쓰레드 프로세스의 경우 여러 쓰레드가 같은 프로세스 내의 자원을 공유하게 되므로 쓰레드의 동기화 개념이 필요하게 되었다.
+ 쓰레드의 동기화란 '한 쓰레드가 진행 중인 작업을 다른 쓰레드가 간섭하지 못하도록 막는 것'이다.
+ 자바에서는 synchronized 블럭을 이용해서 동기화를 지원한다.(JDK1.5부터는 java.util.concurrent.locks와 java.util.concurrent.atomic 패키지를 통해서 다양한 방식으로 동기화를 구현할 수 있도록 지원함)

#### synchronized를 이용한 동기화
+ synchronized를 이용한 동기화 방법(가능하면 메서드에 synchronized를 사용하는 메서드 단위의 동기화를 권장)
```java
// 1. 특정한 객체에 lock을 걸고자 할 때
synchronized(객체의 참조변수){
    // ...
}

// 2. 메서드에 lock을 걸고자할 때
public synchronized void calcSum(){
    // ...
}
```
+ 은행계좌(account)에서 잔고(balance)를 확인하고 임의의 금액을 출금(withdraw)하는 예제
```java
class example {
    public static void main(String[] args) {
        MyThread r = new MyThread();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();
    }
}

class Account{
    //**balance에 synchronized 를 위한 private 멤버 변수로 정의**
    private int balance = 1000;
    public int getBalance() {
      return balance;
    }

    // **동기화 적용 (메서드 단위의 동기화를 추천한다.)**
    public synchronized void withDraw(int money){
        /* 객체에 lock을 걸 경우
        synchronized (this) {
            여기에 소스 코드를 집어 넣어도 된다.
        }
        */
        if(balance >= money){
            try{
                Thread.sleep(1000);
            }catch (Exception e) {}
            balance -= money;
        }
    }
}

class MyThread implements Runnable{
    Account acc = new Account();

    @Override
    public void run() {
        while(acc.getBalance > 0){
            // 100, 200, 300 중의 한 값을 임의로 선택해서 출금(withDraw)
            int money = (int)(Math.random() * 3 + 1) * 100;
            acc.withDraw(money);
            System.out.println("balance:" + acc.getBalance);
        }
    }
}
```

#### wait()과 notify()
+ 쓰레드를 동기화할 때 동기화의 효율을 높이기 위해 wait()와 notify()를 함께 사용할 수 있다.
+ wait(): 동기회된 임께영역의 코드를 수행하다가 작업을 더 이상 진행할 상황이 아니면 wati()을 호출하여 쓰레드가 락을 반납하고 기다리게 한다.
  + 매개변수가 없는 wait() : notify(), notifyAll()이 호출될 때까지 기다린다.
  + 매개변수가 있는 wait() : 지정된 시간동안만 기다린다. 즉, 지정된 시간이 지난 후에 자동적으로 notify()가 호출된다.
+ notify(): 작업을 중단했던 쓰레드가 다시 락을 얻어 작업을 진행할 수 있게 한다.
+ notifyAll() : 기다리고 있는 모든 쓰레드에게 통보를 하지만 lock을 얻을 수 있는 것은 하나의 쓰레드일 뿐이고 나머지 쓰레드는 통보를 받긴 했지만, lock을 얻지 못하면 다시 lock을 기다려야 한다.

```<wait(), notify(), notifyAll()>
- Object로 정의되어 있다.
- 동기화 블록(synchronized 블록) 내에서만 사용할 수 있다.
- 보다 효율적인 동기화를 가능하게 한다.
```
#### Lock과 Condition을 이용한 동기화
+ 동기화할 수 있는 방법은 synchronized 블럭 외에도 'java.util.concurrent.locks' 패키지가 제공하는 lock 클래스들을 이용하는 방법이 있다.
(synchronized블럭으로 동가화를 하면 자동적으로 lock이 잠기고 풀리기 때문에 편리하다.그러나 메서드 내에서만 lock을 걸 수 있다는 단점이 있다.)  
+ lock클래스의 종류
```
ReentrantLock
ReentrantReadWriteLock
StampedLock
```
+ ReentrantLock
  + 가장 일반적인 lock이다.
  + 'reentrant(재진입할 수 있는)'이라는 단어가 앞에 붙은 이유는 특정 조건에서 lock을 풀고 나중에 다시 lock을 얻고 임계영역으로 들어와서 이후의 작업을 수행할 수 있기 때문이다.
  + 수동으로 lock을 잠그고 해제해야한다. `locck() // lock을 잠근다.`, `unlock()// 잠금을 해제한다.` `isLocked()// 잠겼는지 확인`
  + unlock()은 return문으로 빠져 나가게 되면 lock이 풀리지 않을 수 있으므로 `try-finally`문으로 감싸야 한다.
  ```java
  lock.lock();
  try{
      // 임계영역, 이렇게 하면 임계영역에서 예외나 에러가 발생해도 finally에서 unlock 시켜서 안전
  } finally {
      lock.unlock();
  }
  ```
+ ReentrantReadWriteLock
  + 읽기를 위한 lock과 쓰기를 위한 lock을 제공한다. 
  + 여러 쓰레드가 읽어도 문제가 되지 않는다.
  + 그러나 읽기 lock이 걸린 상태에서 쓰기 lock을 거는 것은 안 된다. 즉, 읽기 lock과 쓰기 lock을 동시에 사용할 수 없다.
 
+  StampedLock
  + lock을 걸거나 해지할 때 '스탭프(long타입의 정수값)'를 사용하며, 읽기와 쓰기를 위한 lock외에 낙관적 읽기 lock이 추가된 것이다. 
  +  '낙관적 읽기 lock'은 쓰기 lock에 의해 바로 풀린다. 
  +  즉, 무조건 읽기 lock을 걸지 않고 쓰기와 읽기가 충돌할 때만 쓰기가 끝난 후에 읽기 lock을 거는 것이다.
```java
int getBalance() {
    long stamp = lock.tryOptimisticRead(); // 낙관적 읽기 lock을 건다.

    int curBalance = this.balance; //공유 데이터인 balance를 읽어온다.

    if(lock.validate(stamp)) { //쓰기 lock에 의해 낙관적인 읽기 lock이 풀렸는지 확인
        stamp = lock.readLock(); // lock이 풀렸으면 읽기 lock을 얻으려고 기다린다.

        try {
            curBalance = this.balance; // 공유 데이터를 다시 읽어온다.
        } finally {
            lock.unlockRead(stamp); // 읽기 lock을 푼다.
        }
    }
    return curBalance; // 낙관적 읽기 lock이 풀리지 않았으면 곧바로 읽어온 값을 반환한다.
}
```

#### volatile(멀티 코어 프로세스가 장착된 컴퓨터의 믄제점 해결)
+ 코어에는 메모리에서 읽어온 값을 캐시에 저장하고 캐시에서 값을 읽어서 작업한다. 다시 같은 값을 읽어올 때는 먼저 캐시에 있는지 확인하고 없을 때만 메모리에서 읽어온다.
+ 이러다보니 도중에 메모리에 저장된 변수의 값이 변경되었는데도 캐시에 저장된 값이 갱신되지 않아서 메모리에 저장된 값이 다른 경우가 발생한다. 그래서 변수의 값이 바뀌었는데도 쓰레드가 멈추지 않고 계속 실행되는 것이다.
+ 변수 앞에 `volatile`을 붙이면 코어가 변수의 값을 읽어올 떄 캐시가 아닌 메모리에서 읽어오기 때문에 캐시와 메모리간의 값의 불일치가 해결된다.
```java
boolean suspended = false;
boolean stopped = false;

==============================
volatile boolean suspended = false;
volatile boolean stopped = false;
````
+ 변수에 volatile을 붙이는 대신에 synchronized 블럭을 사용해도 같은 효과를 얻을 수 있다. (쓰레드가 synchronized블럭으로 들어갈 때와 나올 때 캐시와 메모리간의 동기화가 이루어지기 때문에 값의 불일치가 해소되기 때문이다.)
```java
public void stop(){
  stopped = true;
}
========================
public synchronized void stop(){
  stopped = true;
}
```
+ long, double은 여러 개의 4바이트 블록을 쓰기 때문에 여러 쓰레드가 하나의 변수의 블록을 접근할 수 있다.

#### fork & join 프레임워크
+ 하나의 작업을 작은 단위로 나눠서 여러 쓰레드가 동시에 처리하는 것을 쉽게 만들어준다.( JDK1.7부터 추가 됨)
+ 수행할 작업에 따라 RecursiveAction과 RecursiveTask, 두 클래스 중에 하나를 상속받아 구현해야 한다.
```
RecursiveAction // 반환값이 없는 작업을 구현할 때 사용
RecursiveTask // 반환값이 있는 작업을 구현할 때 사용
```
+ 두 클래스 모두 compute() 라는 추상 메서드를 가지고 있는데, 우리는 상속을 통해 이 추상 메서드를 구현하기만 하면 된다.
```java
//RecursiveAction
public abstract class RecursiveAction extends ForkJoinAction<Void>()
{
    protected abstract void compute();
}

======================================================================
//RecursiveTask
public abstract class RecursiveTask extends ForkJoinTask<V>()
{
    protected abstract V compute();
}
```

+ 1부터 n까지의 합 구하는 예제(fork&join 프레임워크로 수행할 작업은 invoke()로 시작)   ** 쓰레드는 start()를 호출하여 시작
```java
class SumTask extends RecursiveTask<Long>{ // RecursiveTask를 상속받는다.
    long form;
    long to;

    SumTask(long from, long to){
        this.from = from;
        this.to = to;
    }

    public Long compute(){
      // 처리할 작업을 수행하기 위한 문장을 넣는다.
    }
}
=================================================== main
ForkJoinPool pool = new ForkJoinPool(); //쓰레드 풀 생성
SumTask task = new SumTask(from, to); // 수행할 작업 생성

Long result = pool.invoke(task); // invoke()를 호출해서 작업을 시작
```
+ fork() : 해당 작업을 쓰레드 풀의 작업 큐에 넣는다. 작업 큐에 들어간 작업은 더 이상 수행되지 않을 때까지 작업한다.(비동기 메서드-결과를 기다려주지 않는다.)
  + fork()를 호출하면 결과를 기다리지 않고 다음 문장으로 넘어간다.
+ join() : 해당 작업의 수행이 끝날 때까지 기다렸다가 수행이 끝나면 그 결과를 반환한다.(동기 메서드 - 호출 결과를 기다린다.)











