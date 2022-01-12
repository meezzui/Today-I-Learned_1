public class ObjectOriented8 {
    public static void main(String[] args) {
        Cars cars = new Cars("pink");
        System.out.println("cars의 색깔:"+cars.color + " 기어타입:"+cars.gearType+" 문 개수:"+cars.door);
    }
}
class Cars{
    String color;
    String gearType; // 변속기 종류 = auto, manual
    int door; // 문의 개수

    Cars(String color){
        this(color,"manual",3); //기본지정
    }
    Cars(String color, String gearType, int door){
        this.color = color;
        this.gearType =gearType;
        this.door = door;
    }
}