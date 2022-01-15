public class ObjectOriented12 {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("연습생");
        student.setAge(22);

        String name= student.getName();
        int age = student.getAge();

        System.out.println("이름:"+ name);
        System.out.println("나이:"+ age);
    }
}
class Student{
    //은닉된 멤버변수 --> 현재 블록안에서만 접근 가능
    private String name;
    private int age;

    //은닉된 멤버 변수에 값을 읽는 방법
    public String getName() {
        return name;
    }
    //은닉된 멤버 변수에 값을 넣은 방법 --> 메소드를 사용
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}