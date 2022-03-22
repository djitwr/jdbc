package cn.stack.Test;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import cn.stack.model.User;

public class BeanUtilsTest {
	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
		User u = new User();
		BeanUtils.setProperty(u, "userName", "qq");
		System.out.println(u);
	}

}
