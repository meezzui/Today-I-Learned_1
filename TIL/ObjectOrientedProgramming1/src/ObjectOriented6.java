public class ObjectOriented6 {
    public static void main(String[] args) {
        //클래스매서드 호출. 인스턴스 생성없이 호출가능
        System.out.println(MyMath2.add(50,45));
        System.out.println(MyMath2.subtract(50,45));
        System.out.println(MyMath2.multiply(50,45));
        System.out.println(MyMath2.divide(50.0,45.0));

        MyMath2 myMath2 = new MyMath2(); // 인스턴스 생성
        myMath2.a = 30;
        myMath2.b = 20;
        // 인스턴스메서드는 객체생성 후에만 호출이 가능
        System.out.println(myMath2.add());
        System.out.println(myMath2.subtract());
        System.out.println(myMath2.multiply());
        System.out.println(myMath2.divide());
    }
}
class MyMath2{
    long a,b;

    //인스턴스변수 a,b만을 이용해서 작업하므로 매개변수가 필요없다.
    long add(){return a+b;} //a,b는 인스턴스변수
    long subtract(){return a-b;}
    long multiply(){return a*b;}
    double divide(){return a/b;}

    //인스턴스변수와 관계없이 매개변수만으로 작업 가능
    static long add(long a,long b){return a+b;} //a,b는 지역변수
    static long subtract(long a,long b){return a-b;}
    static long multiply(long a,long b){return a*b;}
    static double divide(double a, double b){return a/b;}
}
