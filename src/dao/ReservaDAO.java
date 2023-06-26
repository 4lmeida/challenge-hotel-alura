package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Reserva;

public class ReservaDAO {
	
	private Connection connection;
	
	public ReservaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void insertReserva(Reserva reserva) {
		
		String sql = "INSERT INTO reservas (data_entrada, data_saida, valor, forma_pagamento) VALUES (?, ?, ?, ?)";
		
		try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			pstm.setDate(1, reserva.getDataEntrada());
			pstm.setDate(2, reserva.getDataSaida());
			pstm.setBigDecimal(3, reserva.getValor());
			pstm.setString(4, reserva.getFormaPagamento());
			
			pstm.execute();
			
			try(ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					reserva.setId(rst.getLong(1));
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public List<Reserva> buscar() {
		List<Reserva> reservas = new ArrayList<>();
		
		String sql = "SELECT id, data_entrada, data_saida,  valor, forma_pagamento FROM reservas";
		
		try(PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();
			
			tranformaResultSetEmReserva(reservas, pstm);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return reservas;
	}
	
	public List<Reserva> buscarPorId(Long id) {
		List<Reserva> reserva = new ArrayList<>();
		
		String sql = "SELECT id, data_entrada, data_saida,  valor, forma_pagamento FROM reservas WHERE id = ?;";
		
		try(PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.setLong(1, id);
			pstm.execute();
			
			tranformaResultSetEmReserva(reserva, pstm);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return reserva;
	}



	public void atrualizar(Date dataEntrada, Date dataSaida, BigDecimal valor, String formaPagamento, Long id) {
		try(PreparedStatement pstm = connection
				.prepareStatement("UPDATE reservas SET data_entrada = ?, data_saida = ?, valor = ?, forma_pagamento = ? WHERE id = ?")) {
			
			pstm.setDate(1, dataEntrada);
			pstm.setDate(2, dataSaida);
			pstm.setBigDecimal(3, valor);
			pstm.setString(4, formaPagamento);
			pstm.setLong(5, id);
			pstm.execute();
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public void deletar(Long id) {
		try(PreparedStatement pstm = connection.prepareStatement("DELETE FROM reservas WHERE id = ? ")) {
			
			pstm.setLong(1, id);
			pstm.execute();
			
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	private void tranformaResultSetEmReserva(List<Reserva> reservas, PreparedStatement pstm) {
		
		try(ResultSet rst = pstm.getResultSet()) {
			while(rst.next()) {
				Reserva reserva = new Reserva(rst.getLong(1), rst.getDate(2), rst.getDate(3), rst.getBigDecimal(4), rst.getString(5));
				
				reservas.add(reserva);
			}
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
}
