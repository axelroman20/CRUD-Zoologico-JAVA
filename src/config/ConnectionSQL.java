package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectionSQL {

    Connection con;

    public ConnectionSQL() {
        con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost/zoologicoapp?useTimezone=true&serverTimezone=UTC";
            String user = "root";
            String password = "6f6d616e";
            con = DriverManager.getConnection(url, user, password);
            if (con != null) {
                System.out.println("Conexion Establecida..");
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(
                    null, "EL CONTROLADOR DE MYSQL NO SE ENCUENTRA INSTALADO.",
                    "ERROR DE CONTROLADOR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null, "NO SE PUDO ESTABLECER LA CONEXIÓN CON LA BASE DE DATOS",
                    "ERROR EN LA CONEXIÓN", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            System.out.println(e);
        } catch (IllegalAccessException | InstantiationException e) {
            JOptionPane.showMessageDialog(
                    null, "" + e.getMessage(),
                    "ERROR!!!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public java.sql.Connection getConection() {
        return con;
    }

}