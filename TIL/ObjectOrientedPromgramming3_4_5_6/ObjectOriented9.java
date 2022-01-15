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
