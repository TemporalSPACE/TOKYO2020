package daos;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import juegosOlimpicos.Deportista;
import juegosOlimpicos.Disciplina;
import juegosOlimpicos.Pais;

public class DeportistaDAOjdbc implements DeportistaDAO {

	@Override // TE DEVUELVE UNA LISTA DE TODOS LOS DEPORTISTAS
	public List<Deportista> getList() {
		try {

			Connection con = MiConnection.getCon();
			Statement sent = con.createStatement();

			Pais p;
			Disciplina disciplina;
			PaisDAO conPais = new PaisDAOjdbc();
			DisciplinaDeportistaDAO conDisciplinaDeportista = new DisciplinaDeportistaDAOjdbc();
			int idDeportista;

			List<Deportista> l = new LinkedList<Deportista>();
			ResultSet result = sent.executeQuery("select * from deportista");
			while (result.next()) {

				idDeportista = result.getInt("id");
				p = conPais.getPais(result.getInt("id_pais"));
				disciplina = conDisciplinaDeportista.getDisciplina(idDeportista);

				l.add(new Deportista(idDeportista, result.getString("nombres"), result.getString("apellidos"),
						result.getString("email"), result.getLong("telefono"), p, disciplina));
			}
			return l;

		} catch (SQLException e) {
			System.out.println("Error SQL");
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void eliminar(int id) {
		try {
			new DisciplinaDeportistaDAOjdbc().eliminarDeportista(id);

			Connection con = MiConnection.getCon();
			Statement sent = con.createStatement();
			sent.execute("DELETE FROM deportista where id=" + id);
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getFilas() {
		try {
			Connection con = MiConnection.getCon();
			Statement sent = con.createStatement();
			ResultSet result = sent.executeQuery("select count(1) from deportista");
			result.next();
			return result.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	@Override // TE GUARDA UN NUEVO DEPORTISTA EN LA BASE DE DATOS
	public int guardar(Deportista d) {
		try {

			Connection con = MiConnection.getCon();
			Statement sent = con.createStatement();
			sent.executeUpdate("INSERT INTO `deportista` (apellidos, nombres, email, telefono,id_pais) VALUES ('"
					+ d.getApellido() + "', '" + d.getNombre() + "','" + d.getEmail() + "', '" + d.getTelefono()
					+ "' , '" + d.getPais().getId() + "')");

			ResultSet result = sent.executeQuery("select max(id) from deportista");
			result.next();
			int id = result.getInt(1);

			con.commit();

			new DisciplinaDeportistaDAOjdbc().guardar(id, d.getDisciplina().getId());
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public void editar(Deportista d) {
		try {
			Connection con = MiConnection.getCon();
			Statement sent = con.createStatement();
			sent.execute("Update `deportista` set `apellidos` = '" + d.getApellido() + "',`nombres` = '" + d.getNombre()
					+ "',`email` = '" + d.getEmail() + "' , `telefono` = '" + d.getTelefono() + "',`id_pais` = '"
					+ d.getPais().getId() + "' WHERE (`id` = '" + d.getId() + "')");
			
			DisciplinaDeportistaDAO conDiscDep = new DisciplinaDeportistaDAOjdbc();
			conDiscDep.editar(d.getId(), d.getDisciplina().getId());

			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Deportista getDeportista(int id) {
		try {
			Connection con = MiConnection.getCon();
			Statement sent = con.createStatement();
			ResultSet result = sent.executeQuery("Select * FROM deportista where id=" + id);
			Deportista d = null;

			if (result.next()) {
				int idPais = result.getInt("id_pais");
				Pais pais = new PaisDAOjdbc().getPais(idPais);
				Disciplina disciplina = new DisciplinaDeportistaDAOjdbc().getDisciplina(id);
				d = new Deportista(result.getInt("id"), result.getString("nombres"), result.getString("apellidos"),
						result.getString("email"), result.getLong("telefono"), pais, disciplina);
			}
			return d;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isDeportista(Deportista d) {

		try {
			Connection con = MiConnection.getCon();
			Statement sent = con.createStatement();

			ResultSet result = sent.executeQuery("Select * from deportista where email='" + d.getEmail() + "'");
			while (result.next()) {

				if (d.getNombre().equals(result.getString("nombres"))
						&& d.getApellido().equals(result.getString("apellidos"))
						&& d.getTelefono() == result.getLong("telefono")
						&& d.getPais().getId() == result.getInt("id_pais")) {

					int idBase = result.getInt("id");
					Disciplina disciplina;
					DisciplinaDeportistaDAO conDisciplinaDeportista = new DisciplinaDeportistaDAOjdbc();
					disciplina = conDisciplinaDeportista.getDisciplina(idBase);
					if (d.getDisciplina().getId() == disciplina.getId()) {
						return true;
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Error SQL");
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public void exportCSV() {
		List<Deportista> lista = getList();

		try {
			FileWriter csvWriter = new FileWriter("Deportistas.csv");
			csvWriter.append("Nombre");
			csvWriter.append(";");
			csvWriter.append("Pais");
			csvWriter.append(";");
			csvWriter.append("Disciplina");
			csvWriter.append(";");
			csvWriter.append('\n');
			for (Deportista d : lista) {
				csvWriter.append(
						d.getNombre() + ";" + d.getPais().getNombre() + ";" + d.getDisciplina().getNombre() + ";");
				csvWriter.append('\n');
			}
			csvWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}