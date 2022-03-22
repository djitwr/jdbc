package cn.stack.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * �������ݿ�Ĺ�����
 * 
 * @author admin
 *
 */
public class MyDBUtils {
	// �ѹ������ɵ���ģʽ
	private MyDBUtils() {
	};

	private static MyDBUtils dbu = null;

	public static MyDBUtils getInstance() {
		if (dbu == null) {
			dbu = new MyDBUtils();
		}
		return dbu;
	}

	private static DataSource dataSource = null;
	static { // ��̬����죬���췽����ֻ��ִ��һ��
		dataSource = new ComboPooledDataSource("mysql");
	}

	/**
	 * ��ȡ���ݿ����ӵķ���
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {

		return dataSource.getConnection();

		/*
		 * // �õ������������ļ� Properties pop = new Properties(); //
		 * ����Properties���������õ�jdbc.properties // ʹ���ļ���������ȡ�����ļ� InputStream in =
		 * this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		 * pop.load(in); // ���õ��������ļ����ݷ���Properties�����pop�� String driverClass =
		 * pop.getProperty("driverClass"); //
		 * ͨ��pop�����getProperty�����õ�jdbc.properties�����ֵ�� String url =
		 * pop.getProperty("url"); String user = pop.getProperty("user"); String
		 * password = pop.getProperty("password");
		 * 
		 * // �����ݿ⣬ʹ��DriverManager��ȡ���ݿ����� //
		 * ��һ����Class.forName(driverClass):�������������ڲ���������ע����Driver��ʵ���ࣩ //
		 * �ŵ㣺�ٴ�����򵥣��������������DriverManager����ͬʱ�������������� Class.forName(driverClass); //
		 * ͨ������������ݿ�������DriverManager // Class.forName(�������ݿ�����);
		 * 
		 * // �ڶ�����ʹ��DriverManager.getConnection(url, user, password)��ȡ���ݿ����� Connection
		 * conn = DriverManager.getConnection(url, user, password); return conn;
		 */
	}

	/**
	 * ͨ�õĹر����ݿ������Դ���ӵķ���
	 * 
	 * @param conn
	 * @param stat
	 * @param res
	 */
	public static void close(Connection conn, Statement stat, ResultSet res) {

		if (res != null) {
			try {
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
