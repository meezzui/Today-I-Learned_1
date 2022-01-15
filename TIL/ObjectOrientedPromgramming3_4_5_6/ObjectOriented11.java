public class ObjectOriented11 {
    public static void main(String[] args) {
        Card c = new Card("Orange",10);
        System.out.println(c.KIND);
        System.out.println(c.number);
        System.out.println(c); // System.out.println(c.toString());
    }
}
class Card{
    final int number; //상수지만 선언과 함께 초기화 하지 않고
    final String KIND; // 생성자에서 단 한번만 초기화할 수 있다.
    static int width = 100;
    static int  height = 200;

    Card(String kind, int num){ //매개변수로 넘겨받은 값으로 KIND와 number를 초기화한다.
        KIND = kind;
        number = num;
    }

    public String toString(){
        return KIND + " "+ number;
    }
}
