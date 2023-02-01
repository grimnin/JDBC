import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;


public class wale {
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        String dbUrl = "jdbc:oracle:thin:@155.158.112.45:1521:oltpstud";
        String user = "ziibd17";
        String pass = "haslo2022";
        try {
            Connection con = DriverManager.getConnection(dbUrl, user, pass);
            Statement stmt = con.createStatement();
            con.setAutoCommit(false);
            // niepotrzebne? Savepoint save = con.setSavepoint();
            ResultSet rs=stmt.executeQuery("select * from classes natural join courses");
            System.out.println("1.Wyswietlenie danych z kilku tabel:");
            while(rs.next()) {
                System.out.println("course id: "+rs.getInt(1)+" class id: "+rs.getString(2)+" date of start: "+rs.getDate(3)+" period: "+rs.getString(4)
                    +" frequency: "+rs.getString(5)+" instr_id: "+rs.getInt(6)+" course id: "+rs.getString(7)+" duration: "+rs.getInt(8)
                        +" duration measure: "+rs.getString(9)+" date first offered: "+rs.getDate(10)+" date last offered: "+rs.getInt(11)
                            +" delivery type: "+rs.getString(12)+" section code "+rs.getInt(13));
            }


            System.out.println("2.Insert rekordu do tabeli:");
            rs=stmt.executeQuery("insert into students values('108','Kamil','Dabrowski','514-921-080','k.dabrowski@o365.us.edu.pl',null,'Tysiaclecie','Tysiaclecie','Katowice','Katowice','40-871','Polska')");
            System.out.println("1 row inserted");
            System.out.println("3. Usuń dodany rekord");
            rs=stmt.executeQuery("delete from students where stu_id=108");
            System.out.println("1 row deleted");
            System.out.println("4. Zaktualizuj dane w rekordzie");
            rs=stmt.executeQuery("UPDATE locations SET city='Katowice',state_province='Katowice',postal_code='41-827' WHERE location_id=1800");
            System.out.println("1 row updated");
            System.out.println("5. Zmien nazwe kolumny");
            System.out.println("Ponizej znajduja sie nazwy kolumn z tabeli d_songs wpisz nazwe jednej z nich");
            rs=stmt.executeQuery("select * from d_songs");
            ResultSetMetaData columns = rs.getMetaData();

            int i = 0;
            String tab_name []=new String [columns.getColumnCount()];


            while (i < columns.getColumnCount()) {
                i++;
                System.out.print(columns.getColumnName(i) + "\t");
                tab_name[i-1]=columns.getColumnName(i);

            }

            System.out.println();
            String kolumna=scanner.nextLine().toUpperCase();

            boolean findColumnName=false;
            while(true) {
                for (String helper : tab_name) {
                    if (kolumna.equals(helper)) {
                        findColumnName=true;
                    }
                }
                if(findColumnName){break;}

                else{
                    System.out.println("Nie znaleziono takiej kolumny. Podaj nazwe jeszcze raz");
                    kolumna=scanner.nextLine().toUpperCase();
                }
            }



            System.out.println("Podaj nowa nazwe dla kolumny: ");
            String zmieniona_kolumna=scanner.nextLine().toUpperCase();
            rs= stmt.executeQuery("ALTER TABLE d_songs RENAME COLUMN "+kolumna+" to "+zmieniona_kolumna);
            System.out.println("Table D_SONGS altered");





            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
