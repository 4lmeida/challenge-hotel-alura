package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Hospede;

public class HospedeDAO {

	private Connection connection;

	public HospedeDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvar(Hospede hospede) {

		String sql = "INSERT INTO hospedes (nome, sobrenome, data_nascimento, nacionalidade, telefone, id_reserva) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstm.setString(1, hospede.getNome());
			pstm.setString(2, hospede.getSobrenome());
			pstm.setDate(3, hospede.getDataNascimento());
			pstm.setString(4, hospede.getNacionalidade());
			pstm.setString(5, hospede.getTelefone());
			pstm.setLong(6, hospede.getIdReserva());

			pstm.execute();
			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					hospede.setId(rst.getLong(1));
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Hospede> listar() {
		List<Hospede> hospedes = new ArrayList<>();

		String sql = "SELECT id, nome, sobrenome, data_nascimento, nacionalidade, telefone, id_reserva FROM hospedes";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {

			pstm.execute();

			tranformaResultSetEmHospede(hospedes, pstm);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return hospedes;
	}
	
	public List<Hospede> listarSobrenome(String sobrenome) {
		List<Hospede> hospedes = new ArrayList<>();

		String sql = "SELECT id, nome, sobrenome, data_nascimento, nacionalidade, telefone, id_reserva FROM hospedes "
				+ "WHERE sobrenome LIKE '%"+sobrenome+"%';";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {

			pstm.execute();


			tranformaResultSetEmHospede(hospedes, pstm);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return hospedes;
	}
	
	public List<Hospede> atualizar(String nome, String sobrenome, Date dataNascimento, String nascionalidade, String telefone, 
			Long idReserva, Long id) { 
	
		List<Hospede> hospedes = new ArrayList<>();

		String sql = "UPDATE hospedes SET nome = ?,  sobrenome = ?, data_nascimento = ?, nacionalidade = ?, telefone = ?, "
				+ "id_reserva = ? WHERE id = ?";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.setString(1, nome);
			pstm.setString(2, sobrenome);
			pstm.setDate(3, dataNascimento);
			pstm.setString(4, nascionalidade);
			pstm.setString(5, telefone);
			pstm.setLong(6, idReserva);
			pstm.setLong(7, id);
			
			pstm.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return hospedes;
	}
	
	public void deletar(Long id) {
		try(PreparedStatement pstm =  connection.prepareStatement("DELETE FROM hospedes WHERE id = ?")) {
			
			pstm.setLong(1, id);
			pstm.execute();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void tranformaResultSetEmHospede(List<Hospede> hospedes, PreparedStatement pstm) {

		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Hospede hospede = new Hospede(rst.getLong(1), rst.getString(2), rst.getString(3), rst.getDate(4),
						rst.getString(5), rst.getString(6), rst.getLong(7));

				hospedes.add(hospede);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
