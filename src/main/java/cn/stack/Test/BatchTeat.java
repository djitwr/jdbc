package cn.stack.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import cn.stack.jdbc.MyDBUtils;

public class BatchTeat {

	@Test
	public void insertStatement() throws SQLException {
		Connection conn = null;
		Statement stat = null;
		String sql = null;
		try {
			conn = MyDBUtils.getInstance().getConnection();
			stat = conn.createStatement();
			conn.setAutoCommit(false); //��������
			long start = System.currentTimeMillis();
			for (int i = 1; i <= 100000; i++) {
				sql = "insert into student (username,stu_id) values ('name"+i+"',"+i+");";
				stat.executeUpdate(sql);
			}
			long end = System.currentTimeMillis();
			
			conn.commit(); //��������
			
			System.out.println("�������ݺ�ʱ��"+(end-start)+"����");
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback(); //�쳣�ع�
		} finally {
			MyDBUtils.close(conn, stat, null);
		}
	}
	
	@Test
	public void insertPreparedStatement() throws SQLException {
		Connection conn = null;
		PreparedStatement stat = null;
		String sql = null;
		try {
			conn = MyDBUtils.getInstance().getConnection();
			conn.setAutoCommit(false); //��������
			long start = System.currentTimeMillis();
			for (int i = 1; i <= 100000; i++) {
				sql = "insert into student (username,stu_id) values (?,?);";
				stat = conn.prepareStatement(sql);
				stat.setString(1, "name"+i);
				stat.setInt(2, i);
				stat.executeUpdate();
			}
			long end = System.currentTimeMillis();
			
			conn.commit(); //��������
			
			System.out.println("�������ݺ�ʱ��"+(end-start)+"����");
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback(); //�쳣�ع�
		} finally {
			MyDBUtils.close(conn, stat, null);
		}
	}
	
	@Test
	public void insertBatch() throws SQLException {
		Connection conn = null;
		PreparedStatement stat = null;
		String sql = null;
		try {
			conn = MyDBUtils.getInstance().getConnection();
			conn.setAutoCommit(false); //��������
			sql = "insert into student (username,stu_id) values (?,?);";
			stat = conn.prepareStatement(sql);
			long start = System.currentTimeMillis();
			for (int i = 1; i <= 100000; i++) {
				stat.setString(1, "name"+i);
				stat.setInt(2, i);
				stat.addBatch();//��sql����������������
				if(i%300==0) {
					stat.executeBatch();
					stat.clearBatch();
				}	
			}
			if(100000%300!=0) {
				stat.executeBatch();
				stat.clearBatch();
			}
			
			long end = System.currentTimeMillis();
			conn.commit(); //��������
			System.out.println("�������ݺ�ʱ��"+(end-start)+"����");
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback(); //�쳣�ع�
		} finally {
			MyDBUtils.close(conn, stat, null);
		}
	}
	
	
}
