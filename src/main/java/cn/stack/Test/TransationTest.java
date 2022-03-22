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
			conn.setAutoCommit(false); //开启事务
			String sql1 = "update account set balance=500 where username='A';";
			baseDao.iud(conn,sql1);
			
			int i = 10/0;
			
			String sql2 = "update account set balance=1500 where username='B';";
			baseDao.iud(conn,sql2);
			
			conn.commit(); //提交事务
			
		} catch (Exception e) {
			e.printStackTrace();
			//异常后事务回滚
			conn.rollback();
		}finally {
			MyDBUtils.close(conn, null, null); //最后关闭conn
		}
		

		
	}
}
