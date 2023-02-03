import javax.swing.table.DefaultTableModel;
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
            int nazwa =selectedRow;
            ResultSet rs=stmt.executeQuery("SELECT * FROM "+nazwaTabeli);
            ResultSetMetaData metadata = rs.getMetaData();
            int cols= metadata.getColumnCount();

            String colName []=new String[cols];
            dane.setRowCount(selectedRow);
            for(int i=0;i<cols;i++){
                colName[i]=metadata.getColumnName(i+1);
                dane.setColumnIdentifiers(colName);

            }
            int i=0;
            dane.setRowCount(0);

            String trzecia="";

            ArrayList<String> mork=new ArrayList<>();
            String k[];
            while(rs.next()){
                for(int j=0;j<dane.getColumnCount();j++) {

                    trzecia=rs.getString(j+1);
                mork.add(trzecia);
                    k = mork.toArray(new String[mork.size()]);

                }

                mork.toArray();
                String [] row=mork.toArray(new String[mork.size()]);

                dane.addRow(row);
                mork.clear();

            }

            stmt.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dane;
    }



}
