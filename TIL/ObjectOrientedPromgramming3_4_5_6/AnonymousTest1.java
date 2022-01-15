import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AnonymousTest1 {
    public static void main(String[] args) {
        Button b = new Button("Start");
        b.addActionListener(new EventHandlers());
    }
}
class EventHandlers implements ActionListener{
    public void actionPerformed(ActionEvent e){
        System.out.println("ActionEvent occurred><");
    }
}
