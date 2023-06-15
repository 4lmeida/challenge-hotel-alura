package controllers;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reserva;

public class ReservasController {

	private ReservaDAO reservaDAO;
	
	public ReservasController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.reservaDAO = new ReservaDAO(connection);
	}
	
	public void inserir(Reserva reserva) {
		this.reservaDAO.insertReserva(reserva);
	}
	
	public List<Reserva> buscar()  {
		return this.reservaDAO.buscar();
	}
	
	public List<Reserva> buscarReservaId(Long id)  {
		return this.reservaDAO.buscarPorId(id);
	}

	public void atualizar(Date dataEntrada, Date dataSaida, BigDecimal valor, String formaPagamento, Long id) {
		this.reservaDAO.atrualizar(dataEntrada, dataSaida, valor, formaPagamento, id);
		
	}

	public void deletar(Long id) {
		this.reservaDAO.deletar(id);
		
	}
	
}
