package factory;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class ConnectionFactory {
	
	public DataSource dataSource;

	public ConnectionFactory() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3307/hotel?useTimezone=true&serverTimezone=UTC");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("my-secret-pw");
		
		this.dataSource = comboPooledDataSource;
	}
	
	public Connection recuperarConexao() {
		try {
			return this.dataSource.getConnection();
		}
		catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		
	}
}
