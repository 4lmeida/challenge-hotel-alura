package controllers;

import java.sql.Connection;
import java.sql.Date;
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
		this.hospedeDAO.salvar(hospede);
	}
	
	public List<Hospede> buscarTodos() {
		return this.hospedeDAO.listar();
	}
	
	public List<Hospede> listarSobrenome(String sobrenome) {
		return this.hospedeDAO.listarSobrenome(sobrenome);
	}
	
	public void atualizar(String  nome, String sobrenome, Date dataNascimento, String nascionalidade, String telefone, 
			Long idReserva, Long id) {
		this.hospedeDAO.atualizar(nome, sobrenome, dataNascimento, nascionalidade, telefone, idReserva, id);
	}
	
	public void deletar(Long id ) {
		this.hospedeDAO.deletar(id);
	}
	
	
	
}
