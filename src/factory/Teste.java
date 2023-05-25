package factory;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Reserva;

public class Teste {

	public static void main(String[] args) throws SQLException {
		
		
		
		Reserva reserva = new Reserva(java.sql.Date.valueOf("2023-08-25"), java.sql.Date.valueOf("2023-09-05"), 
				new BigDecimal("800.0") , "Boleto");

		try (Connection connection = new ConnectionFactory().recuperarConexao()) {
			
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
			}
			
			System.out.println(reserva);
		}

	}

}
