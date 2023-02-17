package main;

import config.ConnectionSQL;
import java.sql.Connection;

public class Main {

    public static ConnectionSQL conn;
    public static Connection reg;

    public static void main(String[] args) {
        Menu.main(args);
        conn = new ConnectionSQL();
        reg = conn.getConection();
    }

}
