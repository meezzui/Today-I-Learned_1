public class StringArray3 {
    public static void main(String[] args) {
        if(args.length !=3){//입력된 값의 개수가 3개가 아니면,
            System.out.println("useage: java StringArray3 num1 op num2");
            System.exit(0); //프로그램 종료
        }
        int num1 = Integer.parseInt(args[0]); // 문자열을 숫자로 변환
        char op = args[1].charAt(0); // 문자열을 문자로 반환
        int num2 = Integer.parseInt(args[2]);
        int result=0;
        switch (op){
            case '+':
            result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                System.out.println("지원되지 않는 연산");
        }
        System.out.println("결과:"+result);
    }
}
