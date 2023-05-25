package modelo;

import java.math.BigDecimal;
import java.sql.Date;

public class Reserva {
	
	private Long id;
	private Date dataEntrada;
	private Date dataSaida;
	private BigDecimal valor;
	private String formaPagamento;
	
	public Reserva() {
	}
	
	public Reserva(Long id, Date dataEntrada, Date dataSaida, BigDecimal valor, String formaPagamento) {
		this.id = id;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
	}

	public Reserva(Date dataEntrada, Date dataSaida, BigDecimal valor, String formaPagamento) {
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long l) {
		this.id = l;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	@Override
	public String toString() {
		return String.format("O produto criado foi: %d, %s, %s, %.2f, %s", id, dataEntrada, dataSaida, valor, formaPagamento);
	}	
	
	

}
