package provider;

import config.ConnectionSQL;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class UsersProvider {

    private static Connection conn;
    private static ConnectionSQL con;
    private static PreparedStatement ps;
    private static ResultSet rs;

    public Connection setConnection() {
        conn = main.Main.conn.getConection();
        return conn;
    }

    public void update(int id, String name, String location) {
        try {
            conn = setConnection();
            String SQL = "UPDATE empleados SET nombre = ?,  ubicacion_zoo= ? WHERE id = ?";
            ps = conn.prepareStatement(SQL);
            ps.setString(1, name);
            ps.setString(2, location);
            ps.setInt(3, id);
            int i = ps.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(
                        null, "Datos Guardados.",
                        "", JOptionPane.DEFAULT_OPTION);
            } else {
                JOptionPane.showMessageDialog(
                        null, "Error al guardar los datos.",
                        "", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException | SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public boolean register(int id, String name, String location) {
        try {
            conn = setConnection();
            String SQL = "INSERT INTO empleados (id, nombre, ubicacion_zoo) VALUES (?,?,?)";
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, location);
            int i = ps.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(
                        null, "Datos Guardados.",
                        "", JOptionPane.DEFAULT_OPTION);
                return true;
            } else {
                JOptionPane.showMessageDialog(
                        null, "Error al guardar los datos.",
                        "", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (HeadlessException | SQLException e) {
            System.out.println("Error: " + e);
            return false;

        }
    }

    public DefaultTableModel fetchUsers() {
        DefaultTableModel model;
        String com[] = {"ID", "NOMBRE", "SECCION DEL ZOO"};
        String row[] = new String[3];
        model = new DefaultTableModel(null, com);
        try {
            conn = setConnection();
            ps = conn.prepareCall("SELECT * FROM empleados LIMIT 100");
            rs = ps.executeQuery();
            while (rs.next()) {
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                model.addRow(row);
            }
        } catch (SQLException e) {
            System.err.print(e);
        }
        return model;
    }

}
