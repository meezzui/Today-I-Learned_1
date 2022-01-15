public class ObjectOriented13 {
    public static void main(String[] args) {
        Grandpa p = new Children(); // 1.타입은 다르지만 참조변수 p와
        Children  c = new Children(); //c모두 Children인스턴스를 참조하고 있다.

        System.out.println("p.x="+p.x); // 3. 인스턴스변수인 x는 참조변수의 타입에 따라 달라진다.
        p.method(); // 4.메서드의 경우 참조변수의 타입에 관계없이 항사 실제 인스턴스의 타입인 Children클래스에 정의된 메서드가 호출 됨

        System.out.println("c.x="+c.x); //인스턴스변수인 x는 참조변수의 타입에 따라 달라진다.
        c.method(); // 메서드의 경우 참조변수의 타입에 관계없이 항사 실제 인스턴스의 타입인 Children클래스에 정의된 메서드가 호출 됨


    }
}
class Grandpa{
    int x = 100; // 2.그리고 Grandpa클래스와

    void method(){
        System.out.println("조상 메소드");
    }
}
class Children extends Grandpa{
    int x = 200; // Children클래스는 서로 같은 멤버들을 정의하고 있다.

    void method(){
        System.out.println("자식 메소드");
    }
}