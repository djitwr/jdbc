package cn.stack.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import cn.stack.jdbc.MyDBUtils;

public class BaseDao {

	/**
	 * 替换sql语句的占位符？为真正的值的方法
	 * 
	 * @param stat
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	private PreparedStatement insteadHolder(PreparedStatement stat, Object... args) throws SQLException {
		for (int i = 0; i < args.length; i++) {
			stat.setObject(i + 1, args[i]);
		}
		return stat;
	}
	
	/**
	 * 通用的拿到entity的方法
	 * @param <T>
	 * @param clazz
	 * @param rs
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 * @throws InvocationTargetException
	 */
	private <T> T getEntity(Class<?> clazz, ResultSet rs)
			throws InstantiationException, IllegalAccessException, SQLException, InvocationTargetException {
		T entity = (T) clazz.newInstance(); // 通过反射拿到对象
		ResultSetMetaData rsmd = rs.getMetaData(); // 得到ResultSetMetaData对象:调用ResultSet的getMetaData()方法
		int columnCount = rsmd.getColumnCount();
		// getColumnLabel(int index):获取指定列的别名,索引从1开始
		for (int i = 1; i <= columnCount; i++) {
			String key = rsmd.getColumnLabel(i);
			Object val = rs.getObject(key);
//			Field field = clazz.getDeclaredField(key);
//			field.setAccessible(true); // 忽略修饰符，变成可以公共访问的
//			field.set(entity, val);
			// 使用BeanUtils工具类的静态方法setProperty() 给对象里的属性赋值
			BeanUtils.setProperty(entity, key, val);

			System.out.println(key + "===" + val);
		}
		return entity;
	}

	// ①实现数据表记录的"增 改 删"操作的iud方法
	public int iud(String sql, Object... args) {
		Connection conn = null;
		PreparedStatement stat = null;
		int count = 0;
		try {
			// 1、获取数据库连接
			conn = MyDBUtils.getInstance().getConnection();
			stat = conn.prepareStatement(sql);
			insteadHolder(stat, args);

			count = stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 关闭连接
			MyDBUtils.close(conn, stat, null);

		}
		return count;
	};
	
	/**
	 * 事物的iud方法
	 * @param conn
	 * @param sql
	 * @param args
	 * @return
	 */
	public int iud(Connection conn, String sql, Object... args) {
		PreparedStatement stat = null;
		int count = 0;
		try {
			// 1、获取数据库连接
			stat = conn.prepareStatement(sql);
			insteadHolder(stat, args);

			count = stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 关闭连接
			MyDBUtils.close(null, stat, null);

		}
		return count;
	};
	
	/**
	 * 插入数据后获取插入数据的主键id的方法
	 * @param sql
	 * @param args
	 * @return
	 */
	public int insetrReturnId(String sql, Object... args) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		int id = 0;
		try {
			// 1、获取数据库连接
			conn = MyDBUtils.getInstance().getConnection();
			stat = conn.prepareStatement(sql,stat.RETURN_GENERATED_KEYS); //stat.RETURN_GENERATED_KEYS插入主键标识
			insteadHolder(stat, args);

			stat.executeUpdate();
			rs = stat.getGeneratedKeys();
			if(rs.next()) {
				id = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 关闭连接
			MyDBUtils.close(conn, stat, null);

		}
		return id;
	}

	// ②获取满足查询条件的一条记录的对象
	public <T> T getOneData(Class<T> clazz, String sql, Object... args) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		T entity = null;
		try {
			conn = MyDBUtils.getInstance().getConnection();
			stat = conn.prepareStatement(sql);
			insteadHolder(stat, args);

			// 获取数据结果
			rs = stat.executeQuery();
			// 取出具体entity具体属性,使用JDBC的元数据，放入map集合中去
			// ResultSetMetaData,这个类就是jdbc的元数据类,从这个类的对象里就可以拿到结果集rs里有多少列,
			// 列名是什么....等等信息.
			if (rs.next()) {
				entity = getEntity(clazz, rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyDBUtils.close(conn, stat, rs);
		}
		System.out.println("。。。。。。。。。。。。。。" + entity);
		return entity;
	};

	// ③获取满足查询条件的多条记录,返回一个记录对象的集合
	public <T> List<T> getListData(Class<T> clazz, String sql, Object... args) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		T entity = null;
		List<T> list = new ArrayList<>();
		try {
			conn = MyDBUtils.getInstance().getConnection();
			stat = conn.prepareStatement(sql);
			insteadHolder(stat, args);

			// 获取数据结果
			rs = stat.executeQuery();
			// 取出具体entity具体属性,使用JDBC的元数据，放入map集合中去
			// ResultSetMetaData,这个类就是jdbc的元数据类,从这个类的对象里就可以拿到结果集rs里有多少列,
			// 列名是什么....等等信息.
			while (rs.next()) {
				entity = getEntity(clazz, rs);
				list.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyDBUtils.close(conn, stat, rs);
		}
		return list;
	}

	// ④获取满足查询条件的某个字段的值或者某个统计字段(count(*))
	public Object getOneColumn(String sql, Object... args) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;

		try {
			conn = MyDBUtils.getInstance().getConnection();
			stat = conn.prepareStatement(sql);
			insteadHolder(stat, args);

			rs = stat.executeQuery();
			if (rs.next()) {
				return rs.getObject(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyDBUtils.close(conn, stat, rs);
		}
		return null;
	};
	
	
	/**
	 * 获取blob的查询方法
	 * @param sql
	 * @param args
	 * @return
	 */
	public Blob getOneColumnBlob(String sql, Object... args) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;

		try {
			conn = MyDBUtils.getInstance().getConnection();
			stat = conn.prepareStatement(sql);
			insteadHolder(stat, args);

			rs = stat.executeQuery();
			if (rs.next()) {
				return rs.getBlob(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyDBUtils.close(conn, stat, rs);
		}
		return null;
	};
}
