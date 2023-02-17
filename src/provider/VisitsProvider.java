/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package provider;

import config.ConnectionSQL;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VisitsProvider {

    private static Connection conn;
    private static ConnectionSQL con;
    private static PreparedStatement ps;
    private static ResultSet rs;

    public Connection setConnection() {
        conn = main.Main.conn.getConection();
        return conn;
    }

    public void update(int id, String date, String time, String school, int visits) {
        try {
            conn = setConnection();
            String SQL = "UPDATE visitas SET fecha = ?,  hora = ?, escuela = ?, visitantes = ? WHERE id = ?";
            ps = conn.prepareStatement(SQL);
            ps.setString(1, date);
            ps.setString(2, time);
            ps.setString(3, school);
            ps.setInt(4, visits);
            ps.setInt(5, id);
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

    public boolean register(String date, String time, String school, int visits) {
        try {
            conn = setConnection();
            String SQL = "INSERT INTO visitas (fecha, hora, escuela, visitantes) VALUES (?,?,?,?)";
            ps = conn.prepareStatement(SQL);
            ps.setString(1, date);
            ps.setString(2, time);
            ps.setString(3, school);
            ps.setInt(4, visits);
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

    public DefaultTableModel fetchVisits() {
        DefaultTableModel model;
        String com[] = {"ID", "FECHA", "HORA", "ESCUELA", "VISITANTES"};
        String row[] = new String[5];
        model = new DefaultTableModel(null, com);
        try {
            conn = setConnection();
            ps = conn.prepareCall("SELECT * FROM visitas LIMIT 100");
            rs = ps.executeQuery();
            while (rs.next()) {
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                model.addRow(row);
            }
        } catch (SQLException e) {
            System.err.print(e);
        }
        return model;
    }
}
