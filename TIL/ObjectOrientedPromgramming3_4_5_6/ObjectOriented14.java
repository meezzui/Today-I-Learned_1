public class ObjectOriented14 {
    public static void main(String[] args) {
        Grandma grandma = new Son();
        Son son = new Son();

        System.out.println("grandma.x = "+ grandma.x);
        grandma.method();
        System.out.println();
        System.out.println("son.x="+son.x);
        son.method();
    }
}
class Grandma {
    int x=100;

    void method(){
        System.out.println("조상 메소드");
    }
}
class Son extends Grandma{
    int x = 200;

    void method(){
        System.out.println("x="+x); // Son클래스의 인스턴스변수 x를 뜻한다.
        System.out.println("super.x="+ super.x); //조상 클래스인 Grandma클래스에 선언된 인스턴스변수 x를 뜻한다
        System.out.println("this.x="+ this.x); // Son클래스의 인스턴스변수 x를 뜻한다.
    }
}