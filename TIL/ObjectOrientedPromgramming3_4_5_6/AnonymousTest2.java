import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnonymousTest2 {
    public static void main(String[] args) {
        Button b = new Button("Start");
        b.addActionListener(new ActionListener(){ // 구현인터페이스이름으로 익명클래스 생성
            public void actionPerformed(ActionEvent e) {
                System.out.println("ActionEvent occurred><");
            }
        });
    }
}
