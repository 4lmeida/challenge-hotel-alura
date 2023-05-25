package dao;

import java.sql.Connection;
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
	
	public Reserva buscarPorId(String id) {
		Reserva reserva = new Reserva();
		
		String sql = "SELECT id, data_entrada, data_saida,  valor, forma_pagamento FROM reservas WHERE id = ?";
		
		try(PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.setString(1, id);
			pstm.execute();
			
			try {
				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {
						reserva = new Reserva(rst.getLong(1), rst.getDate(3), rst.getDate(4),  rst.getBigDecimal(5), rst.getString(6));

					}
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return reserva;
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
