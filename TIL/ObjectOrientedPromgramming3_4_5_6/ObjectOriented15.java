public class ObjetOriented15 {
    public static void main(String[] args) {
        Buyer buyer = new Buyer();

        buyer.buy(new Tv());
        buyer.buy(new Computer());

        System.out.println("현재 남은 돈:"+ buyer.money);
        System.out.println("현재 보너스 점수:"+ buyer.bonusPoint);
    }
}
class Product{
    int price; // 제품 가격
    int bonusPoint; // 제품 구매시 제공하는 보너스 점수

    Product(int price){
        this.price = price;
        bonusPoint = (int)(price/10.0); //보너스점수는 제품가격의 10%
    }
}
class Tv extends Product{
    Tv(){
        //조상클래스의 생성자 Product(int price)를 호출
        super((100)); // Tv 가격을 100만원으로 지정
    }
    //Object클래스의 toString을 오버라이딩한다.
    public String toString(){
        return "Tv";
    }
}
class Computer extends Product{
    Computer(){
        super(200);
    }
    public String toString(){
        return "Computer";
    }
}
class Buyer{ // 고객
    int money = 1000; //고객이 가지고 있는 돈
    int bonusPoint = 0; //보너스 점수

    void buy(Product p){ //매개변수가 Product타입의 참조변수라는 것은 메서드의 매개변수로 Product클래스의 자손타입의 참조변수면 어느 거싱나 매개변수로 받아들일 수 있다는 뜻
        // Product클래스에 price,bonusPoint가 선언되어 있기 때문에 참조변수 p로 인스턴스의 price와 bonusPoint를 사용할 수 있다.
        if(money < p.price){
            System.out.println("잔액이 부족합니다.");
            return;
        }
        money -= p.price; // 가진 돈에서 구입한 제품의 가격을 뺌
        bonusPoint += p.bonusPoint; // 제품의 보너스 점수 추가
        System.out.println(p+"을 구입했습니다.");
    }
}