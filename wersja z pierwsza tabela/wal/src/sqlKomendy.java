import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;
public class sqlKomendy {
    Connection con = JDBC.getInstance().getConnection();
    Statement stmt = null;
    secondPanel tabele=new secondPanel();String nwm="";
    String [] wiersz=new String[42];
    String [] nazwytabel=new String[tabele.getTable().getRowCount()];
    DefaultTableModel model= (DefaultTableModel) tabele.getTable().getModel();
    String zapis="";
    public void pokazTabele(){
        tabele.addFrame();
        tabele.addPanel();


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
            String kolumny;

            while(rs.next()){

                kolumny=rs.getString(1);nwm+=kolumny+" ";
                String [] row={kolumny};
                wiersz=row;
                model.addRow(row);



            }

            //for(int i=0;i<nazwytabel.length)
            nazwytabel=nwm.split(" ");









            stmt.close();

        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(model.getValueAt(1,0));
    }
    public void enterTable(int selectedRow){


        for(String helper:nazwytabel){
            System.out.println(helper);
        }

        try {
            stmt = con.createStatement();

            int nazwa =selectedRow;



            /*
            ResultSet rs=stmt.executeQuery("SELECT * FROM "+tablename);
            ResultSetMetaData metadata = rs.getMetaData();
            int cols= metadata.getColumnCount();

            String colName []=new String[cols];

            for(int i=0;i<cols;i++){
                colName[i]=metadata.getColumnName(i+1);
                model.setColumnIdentifiers(colName);

            }
            String kolumny;
            while(rs.next()){
                kolumny=rs.getString(1);
                String [] row={kolumny};
                wiersz=row;
                model.addRow(row);

            }

*/
            stmt.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



}
