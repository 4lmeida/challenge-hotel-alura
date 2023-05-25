package controllers;

import java.sql.Connection;
import java.util.List;

import dao.HospedeDAO;
import factory.ConnectionFactory;
import modelo.Hospede;

public class HospedeController {

	private HospedeDAO hospedeDAO;
	
	public HospedeController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.hospedeDAO = new HospedeDAO(connection);
	}
	
	public void inserir(Hospede hospede) {
		this.hospedeDAO.insertHospede(hospede);
	}
	
	public List<Hospede> buscarTodos() {
		return this.hospedeDAO.buscar();
	}
	
}
