package cn.stack.Test;

import org.junit.jupiter.api.Test;

public class IndexOfTest {
	
	@Test
	public void indexOf() {
		int count = 0;
		for(int i = 0;i <=200;i++) {
			String str = String.valueOf(i);
			if(str.indexOf("9")!= -1) {
				System.out.println(str);
				count++;
			}
		}
		System.out.println(count);
	}
	
	
	

}
