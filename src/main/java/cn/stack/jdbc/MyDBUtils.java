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
 * 连接数据库的工具类
 * 
 * @author admin
 *
 */
public class MyDBUtils {
	// 把工具类变成单例模式
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
	static { // 静态代码快，构造方法，只会执行一次
		dataSource = new ComboPooledDataSource("mysql");
	}

	/**
	 * 获取数据库连接的方法
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {

		return dataSource.getConnection();

		/*
		 * // 拿到并加载配置文件 Properties pop = new Properties(); //
		 * 创建Properties对象，用于拿到jdbc.properties // 使用文件输入流获取配置文件 InputStream in =
		 * this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		 * pop.load(in); // 将拿到的配置文件内容放入Properties对象的pop中 String driverClass =
		 * pop.getProperty("driverClass"); //
		 * 通过pop对象的getProperty方法拿到jdbc.properties具体键值对 String url =
		 * pop.getProperty("url"); String user = pop.getProperty("user"); String
		 * password = pop.getProperty("password");
		 * 
		 * // 连数据库，使用DriverManager获取数据库连接 //
		 * 第一步：Class.forName(driverClass):加载驱动程序（内部帮助我们注册了Driver的实现类） //
		 * 优点：①代码更简单，用起来更方便②DriverManager可以同时管理多个驱动程序 Class.forName(driverClass); //
		 * 通过反射加载数据库驱动到DriverManager // Class.forName(其他数据库驱动);
		 * 
		 * // 第二步：使用DriverManager.getConnection(url, user, password)获取数据库连接 Connection
		 * conn = DriverManager.getConnection(url, user, password); return conn;
		 */
	}

	/**
	 * 通用的关闭数据库各项资源连接的方法
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
