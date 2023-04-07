package com.check;


import com.Exception1.Exception1;

public class Check {

	// main에서 사용자의 입력을 받은 값을 Controller에게 넘기기전에 검사 (String)
	public String checkString(String value) throws Exception1 {

		//만약 main에서 파라미터로 전달받은 값이 null값이거나, 공백이 있으면
		if(value==null || "".equals(value)) {

			// Exception1에게 예외를 던진다.
			throw new Exception1();
		}
		return value;
	}

	// main에서 사용자의 입력을 받은 값을 Controller에게 넘기기전에 검사 (int)
	public int checkInt(int value) throws Exception1 {

		// 만약 main에서 파라미터로 전달받은 값이 0보다 작다면
		if(value < 0 || value == 0) {

			//Exception1에게 예외를 던진다 
			throw new Exception1();
		}

		return value;
	}
}
