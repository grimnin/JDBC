import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class secondPanel  {

    private  JTable listOfTables;
    private JPanel tablePanel;
    private JButton backButton;
    private JButton showButton;
    private int selectedRaw;
    JFrame secondFrame=new JFrame();
    sqlKomendy komendy=new sqlKomendy();


    public JTable getTable(){
        return listOfTables;
    }

    public void addPanel(){
        secondFrame.add(tablePanel);
    }

    public void addFrame(){
        secondFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        secondFrame.setSize(600,600);
        secondFrame.setVisible(true);
        secondFrame.setTitle("Lista tabeli");

    }
    public secondPanel(){



        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondFrame.dispose();
                mainWindow window=new mainWindow();
            }
        });

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                listOfTables.setModel(komendy.enterTable(selectedRaw));

            }
        });
        listOfTables.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                selectedRaw=listOfTables.getSelectedRow();
                System.out.println(selectedRaw);


            }
        });

    }

    public JPanel getPanel(){
        return tablePanel;
    }



}
