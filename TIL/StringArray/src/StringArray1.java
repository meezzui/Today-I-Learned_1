public class StringArray1 {
    public static void main(String[] args) {
        char[] hex = {'C','A','F','E'}; //16진수에는 A~F까지 6개의 문자가 포함되므로 char[]배열로 처리
        String[] binary = {"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011","1100","1101","1110","1111"};
        // 2진수 16개의 값을 저장
        String result ="";

        for(int i=0; i<hex.length;i++){
            if(hex[i] >='0' && hex[i]<='9'){ //숫자인 경우
                result += binary[hex[i]-'0'];
            }else{ // A ~ F인 경우
                result += binary[hex[i]-'A'+10];
            }
        }
        System.out.println("hex:"+ new String(hex)); // String(char[] value)
        System.out.println("binary:"+ result);
    }
}
