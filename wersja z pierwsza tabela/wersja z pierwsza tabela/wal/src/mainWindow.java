import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class mainWindow {
    private JPanel panel1;
    private JButton updateButton;
    private JButton changeButton;
    private JButton koniecButton;
    private JButton joinButton;
    private JLabel welcomeLabel;


    public mainWindow() {
        Connection con = JDBC.getInstance().getConnection();
        JFrame main=new JFrame();
        sqlKomendy komendy=new sqlKomendy();
        secondPanel panel=new secondPanel();



        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.dispose();
panel.addFrame();
panel.addPanel();
panel.getTable().setModel(komendy.pokazTabele());

            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        koniecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.dispose();
                JDBC.close();
            }

        });



        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(300,300);
        main.setVisible(true);
        main.setTitle("JDBC Projekt");
        main.add(panel1);
        welcomeLabel.setFont(new Font("MV Boli", Font.PLAIN,12));


       // main.add();
    }

    public static void main(String[] args) {
       new mainWindow();




    }


}
