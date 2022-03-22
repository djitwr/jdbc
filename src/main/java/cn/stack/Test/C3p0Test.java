package cn.stack.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import cn.stack.jdbc.MyDBUtils;

public class C3p0Test {
	
	@Test
	 public void c3p0Test() throws PropertyVetoException, SQLException {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass( "com.mysql.cj.jdbc.Driver" ); //loads the jdbc driver            
<<<<<<< HEAD
		cpds.setJdbcUrl( "jdbc:mysql://127.0.0.1:3306/jdbc_01" );
=======
		cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/jdbc_01" );
>>>>>>> 147a4ac (01)
		cpds.setUser("root");                                  
		cpds.setPassword("123456");
		
		Connection conn = cpds.getConnection();
		System.out.println(conn);
	 }
	
	@Test
	public void c3p0XmlTest() throws SQLException {
		DataSource dataSource = new ComboPooledDataSource("mysql");
		Connection conn = dataSource.getConnection();
		System.out.println(conn);
	}
	
}
