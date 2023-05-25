package controllers;

import java.sql.Connection;
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
	
	public Reserva buscarReservaId(String id)  {
		return this.reservaDAO.buscarPorId(id);
	}
	
}
