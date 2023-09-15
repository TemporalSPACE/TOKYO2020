package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MiConnection {
	private static Connection con = null;
	private static boolean seHizoConexion = false;

	// ESTABLECE LA CONEXION A LA BASE DE DATOS
	public static void realizarConexion(String usuario, String contraseña) {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tokyo2021_e3", usuario, contraseña);
			con.setAutoCommit(false);
			seHizoConexion = true;
		} catch (SQLException e) {
			System.out.println("Error SQL");
			System.out.println(e.getMessage());
		}
	}

	public static Connection getCon() {
		return con;
	}

	public static boolean isSeHizoConexion() {
		return seHizoConexion;
	}

}
