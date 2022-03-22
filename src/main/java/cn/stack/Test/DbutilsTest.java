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
 * dbutils工具类的使用测试
 * @author admin
 *
 */
public class DbutilsTest {
	
	@Test
	void dbutilsupdateTest() {
		//获取DBUtils的关键类QueryRunner的对象
		QueryRunner queryRunner = new QueryRunner();
		Connection conn = null;
		try {
			conn = MyDBUtils.getInstance().getConnection();
			String sql = "insert into users (username,password,phone_no,sex,reg_date) VALUES (?,?,?,?,?);";
			queryRunner.update(conn, sql, "张三","111111","5628761","男",new java.sql.Date(new java.util.Date().getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MyDBUtils.close(conn, null, null);
		}
	}
	
	/**
	 * dbutils工具类中查询方法的用法,ResultSetHandler接口:
	 * query方法的返回值就是ResultSetHandler接口里handle方法的返回值
	 * 
	 * BeanHandler类的使用:
	 * 把结果集中第一条数据封装成BeanHandler对象指定的Class类的对象返回
	 * 查询一条记录
	 */
	@Test
	void dbutilsQueryTest1() {
		//获取DBUtils的关键类QueryRunner的对象
		QueryRunner queryRunner = new QueryRunner();
		Connection conn = null;
		try {
			conn = MyDBUtils.getInstance().getConnection(); //获取数据库连接
			String sql = "select id,username,password,phone_no phoneNo,sex,reg_date regDate from users where id=1";
			User u = queryRunner.query(conn, sql, new BeanHandler<>(User.class));
			System.out.println("查询结果：" + u);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MyDBUtils.close(conn, null, null);
		}
	}
	
	/**
	 * dbutils工具类中查询方法的用法,ResultSetHandler接口:
	 * query方法的返回值就是ResultSetHandler接口里handle方法的返回值
	 * 
	 * BeanListHandler类的使用:
	 * 把结果集转换为List集合返回,一定有List返回,哪怕list集合里没有元素
	 * 查询多条记录
	 */
	@Test
	void dbutilsQueryTest2() {
		//获取DBUtils的关键类QueryRunner的对象
		QueryRunner queryRunner = new QueryRunner();
		Connection conn = null;
		try {
			conn = MyDBUtils.getInstance().getConnection(); //获取数据库连接
			String sql = "select id,username,password,phone_no phoneNo,sex,reg_date regDate from users";
			List<User> list = queryRunner.query(conn, sql, new BeanListHandler<>(User.class));
			System.out.println("查询结果：" + list);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MyDBUtils.close(conn, null, null);
		}
	}
	
	/**
	 * dbutils工具类中查询方法的用法,ResultSetHandler接口:
	 * query方法的返回值就是ResultSetHandler接口里handle方法的返回值
	 * 
	 * ScalarHandler类的使用:
	 * 返回一个数值的sql查询getOneCloumn, count(id),2,username
	 * 查询记录数
	 */
	@Test
	void dbutilsQueryTest3() {
		//获取DBUtils的关键类QueryRunner的对象
		QueryRunner queryRunner = new QueryRunner();
		Connection conn = null;
		try {
			conn = MyDBUtils.getInstance().getConnection(); //获取数据库连接
			String sql = "select count(*) from users";
			@SuppressWarnings("unchecked")
			Object obj = queryRunner.query(conn, sql, new ScalarHandler());
			System.out.println("查询结果：" + obj);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MyDBUtils.close(conn, null, null);
		}
	}
	
	

}
