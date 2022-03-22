package cn.stack.dao;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		int[] arr = {2,5,9,1,38,19,6};
		int temp = 0;
		for(int i = 0; i < arr.length;i++) {
			for(int j =1;j < arr.length;j++) {
				if(arr[j-1]>arr[j]) {
					temp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = temp;
				}
			}	
		}
		System.out.println(Arrays.toString(arr));

	
	}
}
