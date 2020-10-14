package com.example.reciclon;

import android.widget.EditText;
import android.widget.TextView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectOra {
    public String driver, url, ip, bd, usr, pass;
    public Connection conexion;

    public ConnectOra(String ip, String bd, String usr, String pass) {
        driver = "oracle.jdbc.driver.OracleDriver";

        this.bd = bd;
        this.usr = usr;
        this.pass = pass;

        url = new String("jdbc:oracle:thin:@" + ip + ":1521:" + bd);

        try {
            Class.forName(driver).newInstance();

            conexion = DriverManager.getConnection(url, usr, pass);

            System.out.println("Conexion a Base de Datos " + bd + " Exitosa");

        } catch (Exception exc) {
            System.out.println("Error al tratar de abrir la base de Datos "
                    + bd + " : " + exc);
        }
    }

    public boolean Login(EditText usuario, EditText passwork) {

        try {
            PreparedStatement stmt = conexion
                    .prepareStatement("Select NAME_USER, PASSWORK From login where NAME_USER= ? and PASSWORK= ?", ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);

            stmt.setString(1, String.valueOf(usuario));
            stmt.setString(2, String.valueOf(passwork));
            ResultSet rs= stmt.executeQuery();
            if(rs.absolute(1)){
                
                return true;

            }else {
                return false;
            }

        } catch (SQLException sqle) {
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("SQLErrorCode: " + sqle.getErrorCode());
            sqle.printStackTrace();

        }
        return false;
    }



    public Connection getConexion() {
        return conexion;
    }

    public Connection CerrarConexion() throws SQLException {
        conexion.close();
        conexion = null;
        return conexion;
    }
}
