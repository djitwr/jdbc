package cn.stack.Test;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Test;

import cn.stack.jdbc.MyDBUtils;
import cn.stack.model.User;

/**
 * dbutils�������ʹ�ò���
 * @author admin
 *
 */
public class DbutilsTest {
	
	@Test
	void dbutilsupdateTest() {
		//��ȡDBUtils�Ĺؼ���QueryRunner�Ķ���
		QueryRunner queryRunner = new QueryRunner();
		Connection conn = null;
		try {
			conn = MyDBUtils.getInstance().getConnection();
			String sql = "insert into users (username,password,phone_no,sex,reg_date) VALUES (?,?,?,?,?);";
			queryRunner.update(conn, sql, "����","111111","5628761","��",new java.sql.Date(new java.util.Date().getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MyDBUtils.close(conn, null, null);
		}
	}
	
	/**
	 * dbutils�������в�ѯ�������÷�,ResultSetHandler�ӿ�:
	 * query�����ķ���ֵ����ResultSetHandler�ӿ���handle�����ķ���ֵ
	 * 
	 * BeanHandler���ʹ��:
	 * �ѽ�����е�һ�����ݷ�װ��BeanHandler����ָ����Class��Ķ��󷵻�
	 * ��ѯһ����¼
	 */
	@Test
	void dbutilsQueryTest1() {
		//��ȡDBUtils�Ĺؼ���QueryRunner�Ķ���
		QueryRunner queryRunner = new QueryRunner();
		Connection conn = null;
		try {
			conn = MyDBUtils.getInstance().getConnection(); //��ȡ���ݿ�����
			String sql = "select id,username,password,phone_no phoneNo,sex,reg_date regDate from users where id=1";
			User u = queryRunner.query(conn, sql, new BeanHandler<>(User.class));
			System.out.println("��ѯ�����" + u);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MyDBUtils.close(conn, null, null);
		}
	}
	
	/**
	 * dbutils�������в�ѯ�������÷�,ResultSetHandler�ӿ�:
	 * query�����ķ���ֵ����ResultSetHandler�ӿ���handle�����ķ���ֵ
	 * 
	 * BeanListHandler���ʹ��:
	 * �ѽ����ת��ΪList���Ϸ���,һ����List����,����list������û��Ԫ��
	 * ��ѯ������¼
	 */
	@Test
	void dbutilsQueryTest2() {
		//��ȡDBUtils�Ĺؼ���QueryRunner�Ķ���
		QueryRunner queryRunner = new QueryRunner();
		Connection conn = null;
		try {
			conn = MyDBUtils.getInstance().getConnection(); //��ȡ���ݿ�����
			String sql = "select id,username,password,phone_no phoneNo,sex,reg_date regDate from users";
			List<User> list = queryRunner.query(conn, sql, new BeanListHandler<>(User.class));
			System.out.println("��ѯ�����" + list);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MyDBUtils.close(conn, null, null);
		}
	}
	
	/**
	 * dbutils�������в�ѯ�������÷�,ResultSetHandler�ӿ�:
	 * query�����ķ���ֵ����ResultSetHandler�ӿ���handle�����ķ���ֵ
	 * 
	 * ScalarHandler���ʹ��:
	 * ����һ����ֵ��sql��ѯgetOneCloumn, count(id),2,username
	 * ��ѯ��¼��
	 */
	@Test
	void dbutilsQueryTest3() {
		//��ȡDBUtils�Ĺؼ���QueryRunner�Ķ���
		QueryRunner queryRunner = new QueryRunner();
		Connection conn = null;
		try {
			conn = MyDBUtils.getInstance().getConnection(); //��ȡ���ݿ�����
			String sql = "select count(*) from users";
			@SuppressWarnings("unchecked")
			Object obj = queryRunner.query(conn, sql, new ScalarHandler());
			System.out.println("��ѯ�����" + obj);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MyDBUtils.close(conn, null, null);
		}
	}
	
	

}
