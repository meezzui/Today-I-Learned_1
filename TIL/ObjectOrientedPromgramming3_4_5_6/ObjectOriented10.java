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