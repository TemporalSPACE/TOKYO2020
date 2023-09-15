package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import exceptions.ExceptionBaseDatos;
import juegosOlimpicos.Pais;

public class PaisDAOjdbc implements PaisDAO {

	@Override // TE DEVUELVE UNA LISTA DE TODOS LOS PAISES
	public List<Pais> getList() {
		try {

			Connection con = MiConnection.getCon();
			Statement sent = con.createStatement();

			List<Pais> l = new LinkedList<Pais>();
			ResultSet result = sent.executeQuery("select * from pais order by nombre");
			while (result.next()) {
				l.add(new Pais(result.getString("nombre"), result.getInt("id")));
			}
			return l;

		} catch (SQLException e) {
			System.out.println("Error SQL");
			return null;
		}
	}

	@Override // TE GUARDA UN NUEVO PAIS EN LA BASE DE DATOS
	public void guardar(Pais d) {
		try {
			Connection con = MiConnection.getCon();
			Statement sent = con.createStatement();

			sent.executeUpdate("INSERT INTO `pais` (nombre) VALUES ('" + d.getNombre() + "')");
			con.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getFilas() {
		try {
			Connection con = MiConnection.getCon();
			Statement sent = con.createStatement();
			ResultSet result = sent.executeQuery("select count(1) from pais");
			result.next();
			return result.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	public void eliminar(int id) throws ExceptionBaseDatos {
		try {
			Connection con = MiConnection.getCon();
			Statement sent = con.createStatement();
			sent.execute("DELETE FROM pais where id=" + id);
			con.commit();
		} catch (SQLException e) {
			// e.printStackTrace();
			// System.out.print(e.getErrorCode());
			new ExceptionBaseDatos("Uno o mas deportistas pertenece a este pais");
			if (e.getErrorCode() == 1451) {
				throw new ExceptionBaseDatos("Uno o mas deportistas pertenece a este pais");
			}
		}
	}

	public Pais getPais(int id) {
		try {
			Connection con = MiConnection.getCon();
			Statement sent = con.createStatement();
			ResultSet result = sent.executeQuery("Select * FROM pais where id=" + id);
			Pais p = null;
			if (result.next()) {
				p = new Pais(result.getString("nombre"), result.getInt("id"));
			}
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void editar(Pais p) {
		try {
			Connection con = MiConnection.getCon();
			Statement sent = con.createStatement();
			sent.execute("Update pais set nombre= '" + p.getNombre() + "' where id= '" + p.getId() + "'");
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isPais(Pais p) {

		try {
			Connection con = MiConnection.getCon();
			Statement sent = con.createStatement();

			ResultSet result = sent.executeQuery("Select * from pais where nombre='" + p.getNombre() + "'");
			while (result.next()) {
				if (p.getNombre().equals(result.getString("nombre"))) {
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println("Error SQL");
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return false;
	}

}
