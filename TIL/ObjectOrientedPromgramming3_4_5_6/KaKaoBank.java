public class KaKaoBank{

    public void kakaoWithDraw(int price) {
        System.out.print("Kakao은행만의 인출 로직...");
        System.out.println(price+" 원을 인출한다.");
    }

    public void kakaoDeposit(int price) {
        System.out.println("Kakao은행만의 입금 로직..."+price+" 원을 입금한다.");
    }

    public void kakaoFindDormancyAccount(String custId){
        System.out.println("kakao은행만의 휴면계좌 찾아주기 로직");
    }

}
