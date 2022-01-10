public class Array3 {
    public static void main(String[] args) {
        char[] abc = {'A','B','C','D'};
        char[] num = {'0','1','2','3','4','5','6','7','8','9'};
        System.out.println(abc);
        System.out.println(num);

        //배열 abd의 num을 붙여서 하나의 배열(result)로 만든다.
        char[] result = new char[abc.length+num.length];
        System.arraycopy(abc,0,result,0,abc.length); // 배열 abc의 첫번째 요소부터 시작해서 abc.length개의 데이터를 배열 result의 첫번째 요소에 복사
        System.arraycopy(num, 0, result ,abc.length, num.length); // 배열 num의 첫번째 요소부터 시작해서 num.length개의 데이터를 배열 result의 abc.length길이 만큼 복사
        System.out.println(result);

        //배열 abc을  배열 num의 첫번째 위치부터 배열 abc의 길이만큼 복사
        System.arraycopy(abc,0,num,0,abc.length);
        System.out.println(num);

        //num의 인덱스6 위치에 3개를 복사
        System.arraycopy(abc, 0 , num, 6, 3);
        System.out.println(num);
    }
}
