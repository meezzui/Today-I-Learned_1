#### 상속(inheritance)
+ 상속의 정의와 장점
  + 상속 : 기존의 클래스를 재사용하여 새로운 클래스를 작성하는 것 
  + 상속의 장점
    🎁 코드의 재사용성을 높인다.    
    🎁 코드의 중복을 제거하여 프로그램의 생산성과 유지보수가 쉽다.  
    (적은 양의 코드로 새로운 클래스를 작성할 수 있고 코드를 공통적으로 관리할 수 있기 때문에 코들의 추가 및 변경이 매우 용이하다.)
 + 작성방법 : `class 새로운클래스 extends 상속받을클래스{ }`
 
 + 단일상속(single inheritance)
   + 자바에서는 오직 단일상속만 허용(c++에서는 다중상속도 허용)
   + 장점: 클래스 간의 관계가 보다 명확해지고 코드를 더욱 신뢰할 수 있게 만들어준다.
   
 + Object클래스 
   + 모든 클래스의 조상클래스
   + 다른 클래스로부터 상속받지 않는 모든 클래스들은 자동적으로 Object클래스로부터 상속받게 함.
   `class Tv{  }` 이를 컴파일 하면 컴파일러가 자동적으로 'extends Object'를 추가하여 Object클래스로부터 상속받도록 함. `class Tv extends Object{   }`
   🎈 toString()이나 equals()같은 메서드를 따로 정읳하지 않고도 사용할 수 있었던 이유도 Object클래스에 정의된 것들이기 때문이다.
   
 + super
   + 부모 클래스에서 상속받은 멤버를 자식 클래스에서 참조하는데 사용되는 참조변수.
   + 상속받은 멤버와 자신의 멤버와 이름이 같을 때는 super를 붙여서 구별할 수 있음.(중복 정의되지 않는 경우에는 this를 쓰는게 좋음)
   + static메서드에서는 사용할 수 없음.
   + super vs this  
     🎗공통점  
     - static메서드에서는 사용할 수 없고 인스턴스메서드에서만 사용할 수 있다. 
     - 모든 인스턴스메서드에는 자신이 속한 인스턴스의 주소가 지역변수로 저장되는데, 이것이 참조변수인 this와 super의 값이 된다.  
     🎗차이점 
     - super는 부모클래스로부터 상속받은 멤버변수를 뜻하고 this는 자식 클래스에 선언된 멤버변수를 뜻한다.
     ```java
          public class ObjectOriented9 {
          public static void main(String[] args) {
              Child child = new Child(); //인스턴스메서드니까 객체생성 해줘야 함
              child.method();
          }
      }
      class Parent{
          int x=10;
      }

      class Child extends Parent{
          int x =20;
          void method(){
              System.out.println("child 클래스 x값:"+x);
              System.out.println("this.x="+this.x); // 자식 클래스의 멤버변수
              System.out.println("super.x="+super.x); // 부모 클래스의 멤버변수
          }
      }
     ```
     실헹결과
     ```java
     child 클래스 x값:20
     this.x=20
     super.x=10
     ```
     + 부모 클래스의 메서드를 자식 클래스에서 오버라이딩한 경우에 super를 사용한다.
     ```java
     class Point{
        int x
        int y;
        
        String getLocation(){
          return "x:" + x+",y:"+y;
        }
     }
     class Point3D extends Point{
        int z;
        String getLocation(){ //오버라이딩
          //return "x:" + x+",y:"+y+",z:"+z;
          return super.getLocation()+",z:"+z; //부모의 메서드 호출
        }
     }
     ```
 + super() vs this() 생성자
   + this() : 같은 클래스의 다른 생성자를 호출하는 데 사용 / super() : 부모 클래스의 생성자를 호출하는데 사용
   + Object클래스를 제외한 모든 클래스의 생성자 첫 줄에 생성자 this() 또는 super()를 호출해야 한다. 즉, Object클래스를 제외한 모든 클래스의 생성자는 첫 줄에 반드시 자신의 다른 생성자 또는 조상의 생성자를 호출해야 한다.
   + 생성자 첫 줄에 super() 생성자를 호출해야 하는 이유는 자식 클래스의 멤버가 조상 클래스의 멤버를 사용할 수도 있으므로 조상의 멤버들이 먼저 초기화 되어 있어야 하기 때문이다.
   ```java
     public class ObjectOriented10 {
     public static void main(String[] args) {
           Point3D point3D = new Point3D(1,2,3);
           System.out.println(point3D.x + point3D.y + point3D.z);
        }
    }
    class Point{
        int x,y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        String getLocation(){
            return "x:"+ x + ",y:"+ y;
        }
    }

    class Point3D extends Point{
        int z;
        Point3D(int x, int y, int z){
            // Point클래스에 생성자 Point()가 정의되어 있지 않기 때문에 Point클래스에 생성자 Point를 추가해주던가,
            // 생성자 Point3D(int x, int y, int z)의 첫줄에 Point(int x, int y)를 호출하도록 해야한다.
            super(x,y); // 조상클래스의 생성자 Point(int x, int y)를 호출
    //        this.x = x; // x,y를 쓸 필요가 없어짐.
    //        this.y = y;
            this.z = z;
        }

        String getLocation(){ //오버라이딩
            return super.getLocation()+", z:"+z;
        }
    }
   ```
   
     
     
     
     
     
     
     
