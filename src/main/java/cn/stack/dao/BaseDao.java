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
	 * �滻sql����ռλ����Ϊ������ֵ�ķ���
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
	 * ͨ�õ��õ�entity�ķ���
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
		T entity = (T) clazz.newInstance(); // ͨ�������õ�����
		ResultSetMetaData rsmd = rs.getMetaData(); // �õ�ResultSetMetaData����:����ResultSet��getMetaData()����
		int columnCount = rsmd.getColumnCount();
		// getColumnLabel(int index):��ȡָ���еı���,������1��ʼ
		for (int i = 1; i <= columnCount; i++) {
			String key = rsmd.getColumnLabel(i);
			Object val = rs.getObject(key);
//			Field field = clazz.getDeclaredField(key);
//			field.setAccessible(true); // �������η�����ɿ��Թ������ʵ�
//			field.set(entity, val);
			// ʹ��BeanUtils������ľ�̬����setProperty() ������������Ը�ֵ
			BeanUtils.setProperty(entity, key, val);

			System.out.println(key + "===" + val);
		}
		return entity;
	}

	// ��ʵ�����ݱ��¼��"�� �� ɾ"������iud����
	public int iud(String sql, Object... args) {
		Connection conn = null;
		PreparedStatement stat = null;
		int count = 0;
		try {
			// 1����ȡ���ݿ�����
			conn = MyDBUtils.getInstance().getConnection();
			stat = conn.prepareStatement(sql);
			insteadHolder(stat, args);

			count = stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // �ر�����
			MyDBUtils.close(conn, stat, null);

		}
		return count;
	};
	
	/**
	 * �����iud����
	 * @param conn
	 * @param sql
	 * @param args
	 * @return
	 */
	public int iud(Connection conn, String sql, Object... args) {
		PreparedStatement stat = null;
		int count = 0;
		try {
			// 1����ȡ���ݿ�����
			stat = conn.prepareStatement(sql);
			insteadHolder(stat, args);

			count = stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // �ر�����
			MyDBUtils.close(null, stat, null);

		}
		return count;
	};
	
	/**
	 * �������ݺ��ȡ�������ݵ�����id�ķ���
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
			// 1����ȡ���ݿ�����
			conn = MyDBUtils.getInstance().getConnection();
			stat = conn.prepareStatement(sql,stat.RETURN_GENERATED_KEYS); //stat.RETURN_GENERATED_KEYS����������ʶ
			insteadHolder(stat, args);

			stat.executeUpdate();
			rs = stat.getGeneratedKeys();
			if(rs.next()) {
				id = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // �ر�����
			MyDBUtils.close(conn, stat, null);

		}
		return id;
	}

	// �ڻ�ȡ�����ѯ������һ����¼�Ķ���
	public <T> T getOneData(Class<T> clazz, String sql, Object... args) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		T entity = null;
		try {
			conn = MyDBUtils.getInstance().getConnection();
			stat = conn.prepareStatement(sql);
			insteadHolder(stat, args);

			// ��ȡ���ݽ��
			rs = stat.executeQuery();
			// ȡ������entity��������,ʹ��JDBC��Ԫ���ݣ�����map������ȥ
			// ResultSetMetaData,��������jdbc��Ԫ������,�������Ķ�����Ϳ����õ������rs���ж�����,
			// ������ʲô....�ȵ���Ϣ.
			if (rs.next()) {
				entity = getEntity(clazz, rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyDBUtils.close(conn, stat, rs);
		}
		System.out.println("����������������������������" + entity);
		return entity;
	};

	// �ۻ�ȡ�����ѯ�����Ķ�����¼,����һ����¼����ļ���
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

			// ��ȡ���ݽ��
			rs = stat.executeQuery();
			// ȡ������entity��������,ʹ��JDBC��Ԫ���ݣ�����map������ȥ
			// ResultSetMetaData,��������jdbc��Ԫ������,�������Ķ�����Ϳ����õ������rs���ж�����,
			// ������ʲô....�ȵ���Ϣ.
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

	// �ܻ�ȡ�����ѯ������ĳ���ֶε�ֵ����ĳ��ͳ���ֶ�(count(*))
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
	 * ��ȡblob�Ĳ�ѯ����
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
