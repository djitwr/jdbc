package cn.stack.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import cn.stack.dao.BaseDao;
import cn.stack.jdbc.MyDBUtils;

public class TransationTest {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		BaseDao baseDao = new BaseDao();
		Connection conn = MyDBUtils.getInstance().getConnection();
		try {
			conn.setAutoCommit(false); //��������
			String sql1 = "update account set balance=500 where username='A';";
			baseDao.iud(conn,sql1);
			
			int i = 10/0;
			
			String sql2 = "update account set balance=1500 where username='B';";
			baseDao.iud(conn,sql2);
			
			conn.commit(); //�ύ����
			
		} catch (Exception e) {
			e.printStackTrace();
			//�쳣������ع�
			conn.rollback();
		}finally {
			MyDBUtils.close(conn, null, null); //���ر�conn
		}
		

		
	}
}
