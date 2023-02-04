import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class sqlKomendy {

    Connection con = JDBC.getInstance().getConnection();
    Statement stmt = null;
    DefaultTableModel model= new DefaultTableModel();
    DefaultTableModel dane= new DefaultTableModel();
    ArrayList<String> nazwytabel=new ArrayList<>();
    boolean added=false;

    String kolumny;
    public sqlKomendy(){
        pokazTabele();

    }

    public DefaultTableModel  pokazTabele(){



        try {
            stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT table_name FROM user_tables");
            ResultSetMetaData metadata = rs.getMetaData();
            int cols= metadata.getColumnCount();

            String colName []=new String[cols];

            for(int i=0;i<cols;i++){
                colName[i]=metadata.getColumnName(i+1);
                model.setColumnIdentifiers(colName);

            }
            model.setRowCount(0);

            while(rs.next()){

                kolumny=rs.getString(1);
                String [] row={kolumny};

                model.addRow(row);



            }

            stmt.close();

        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
return model;
    }

    public DefaultTableModel enterTable(int selectedRow){
        String nazwaTabeli= (String) model.getValueAt(selectedRow,0);

        try {
            stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM "+nazwaTabeli);
            ResultSetMetaData metadata = rs.getMetaData();
            int cols= metadata.getColumnCount();
            String colName []=new String[cols];
            dane.setRowCount(selectedRow);

            for(int i=0;i<cols;i++){
                colName[i]=metadata.getColumnName(i+1);
                dane.setColumnIdentifiers(colName);

            }

            dane.setRowCount(0);
            String wartosci="";
            ArrayList<String> kontenerNaDane=new ArrayList<>();

            while(rs.next()){
                for(int j=0;j<dane.getColumnCount();j++) {

                    wartosci=rs.getString(j+1);
                    kontenerNaDane.add(wartosci);


                }

                kontenerNaDane.toArray();
                String [] row=kontenerNaDane.toArray(new String[kontenerNaDane.size()]);
                dane.addRow(row);
                kontenerNaDane.clear();

            }

            stmt.close();


        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return dane;
    }
public DefaultTableModel insert(int selectedRow){
        DefaultTableModel insert= new DefaultTableModel();
        insert=dane;
    String nazwaTabeli= (String) model.getValueAt(selectedRow,0);
    System.out.println(nazwaTabeli);
    try {
        stmt = con.createStatement();
        int nazwa =selectedRow;
        ResultSet rs=stmt.executeQuery("SELECT * FROM "+nazwaTabeli);
        ResultSetMetaData metadata = rs.getMetaData();
        int cols= metadata.getColumnCount();
        String colName []=new String[cols];



        for(int i=0;i<cols;i++){
            colName[i]=metadata.getColumnName(i+1);



        }
        for(String a:colName){
            System.out.println(a);
        }
        JFrame frame = new JFrame("Dodawanie wierszy");
        JTextField[] tfs = new JTextField[cols];
        JLabel[] etykieta = new JLabel[cols];
        int y=0;
        int x=0;
        for(int j=0;j<colName.length;j++){
            if(j%10==0&&j>1){
                x=x+130;
                y=0;
            }
            etykieta[j] = new JLabel(colName[j]);
            tfs[j] = new JTextField();
            tfs[j].setBounds(x, y + 20, 120, 25);
            etykieta[j].setBounds(x, y, 120, 25);
            y += 40;
            frame.add(etykieta[j]);
            frame.add(tfs[j]);

        }
        JButton dodajButton = new JButton("Dodaj wiersz");
        dodajButton.setBounds(x, y+20, 125, 40);
        frame.add(dodajButton);
        frame.setSize(400, 450);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuffer dodajButtonData = new StringBuffer();
                String data;
                for (int i = 0; i < tfs.length - 1; i++) {
                    dodajButtonData.append("'" + tfs[i].getText() + "', ");
                    tfs[i].setText("");
                }
                dodajButtonData.append("'" + (tfs[tfs.length - 1].getText()) + "'");
                tfs[tfs.length - 1].setText("");
                data = dodajButtonData.toString();
                JFrame komunikat = new JFrame("Wynik operacji");

                JOptionPane confirm=new JOptionPane("Wynik operacji");

                komunikat.setLocationRelativeTo(frame);

                komunikat.setVisible(true);

                komunikat.setSize(300,100);
                komunikat.add(confirm);

                try {

                    Statement stmt = con.createStatement();
                    String sql = "INSERT INTO " + nazwaTabeli + " VALUES (" + data + ")";
                    stmt.executeUpdate(sql);
                    confirm.setMessage("Dodano dane");
                    con.close();
                } catch (SQLException a) {
                    System.out.println(a);
                    confirm.setMessage("Blad");
                }

            }
        });

        stmt.close();


    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }


        added=true;
        return insert;
}
public boolean getadeed(){
        return added;
}


}
