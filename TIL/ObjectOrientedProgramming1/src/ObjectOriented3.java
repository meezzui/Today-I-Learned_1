public class ObjectOriented3 {
    public static void main(String[] args) {
        Data d = new Data();
        d.x=10;
        System.out.println("main 함수: x = "+d.x);

        change(d);
        System.out.println("바뀐 main 함수 : x = "+d.x);
    }

    static void change(Data d){
        d.x = 1000;
        System.out.println("change(): x="+d.x);
    }
}
class Data{int x;}
