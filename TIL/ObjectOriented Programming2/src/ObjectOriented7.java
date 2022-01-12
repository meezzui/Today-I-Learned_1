public class ObjectOriented7 {
    public static void main(String[] args) {
        Car car = new Car("blue","auto",4);
        System.out.println("car의 색깔:"+car.color + " 기어타입:"+car.gearType+" 문 개수:"+car.door);
    }
}
class Car{
    String color;
    String gearType; // 변속기 종류 = auto, manual
    int door; // 문의 개수

    Car(String c, String g, int d){
        color = c;
        gearType =g;
        door = d;
    }
}