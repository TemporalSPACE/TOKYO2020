package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import juegosOlimpicos.Disciplina;

public class DisciplinaDeportistaDAOjdbc implements DisciplinaDeportistaDAO {

	@Override
	public Disciplina getDisciplina(int iddeportista) {
		try {
			Connection con = MiConnection.getCon();
			Statement sent = con.createStatement();
			ResultSet result = sent
					.executeQuery("Select * FROM deportista_en_disciplina where id_deportista=" + iddeportista);
			Disciplina p =null;
			if (result.next()) {
				p = new DisciplinaDAOjdbc().getDisciplina(result.getInt("id_disciplina"));
			}
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void guardar(int idDeportista, int idDisciplina) {
		try {

			Connection con = MiConnection.getCon();
			Statement sent = con.createStatement();
			// System.out.print("el id es: "+ idDeportista);
			sent.executeUpdate("INSERT INTO `deportista_en_disciplina` (`id_deportista` , `id_disciplina`) VALUES ('"
					+ idDeportista + " ' , ' " + idDisciplina + "')");
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void editar(int idDeportista, int idDisciplina) {
		try {
			Connection con = MiConnection.getCon();
			Statement sent = con.createStatement();
			sent.execute("Update `deportista_en_disciplina` set `id_deportista` = '"+idDeportista+"', `id_disciplina` = '" +
					idDisciplina + "' WHERE (`id_deportista` = '" + idDeportista + "')" ); 
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	// elimina todas las relaciones de un deportista
	public void eliminarDeportista(int idDeportista) {
		try {
			Connection con = MiConnection.getCon();
			Statement sent = con.createStatement();
			sent.execute("DELETE FROM deportista_en_disciplina where id_deportista=" + idDeportista);

			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
