package db;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String sqlQuery = "select zakazi.id as 'id zakaza',rinki.name as'imya rinka',baza.adress as'adress bazi' from (zakazi join rinki on zakazi.id_rinka= rinki.id join baza on rinki.id_bazi=baza.id) ;";
        ResultSet result = statement.executeQuery(sqlQuery);
        print(result);
    }

    private static void print(ResultSet set) throws SQLException {
        System.out.println("Printing results:");
        ResultSetMetaData rsmd = set.getMetaData();

        int columnsNumber = rsmd.getColumnCount();
        for (int i = 1; i <= columnsNumber; i++) {
            System.out.print("\t" + rsmd.getColumnName(i));
        }
        System.out.println();
        while (set.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i == 3 || i == 4) System.out.print("\t");
                String columnValue = set.getString(i);
                System.out.print("\t" + columnValue );
            }
            System.out.println("");
        }
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/grushi", "root", "1111");
        System.out.println("Connected.");
        return c;
    }
}
