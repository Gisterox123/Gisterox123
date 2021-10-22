package com.msb.dongbao.ums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author 马士兵教育:chaopengfei
 * @date 2020/12/29
 */
public class DemoTests {

//	@BeforeAll
//	void testBeforeAll(){
//		System.out.println("test before all ");
//	}
//
//	@BeforeEach
//	void testBefore(){
//		System.out.println("test before");
//	}

	@Test
	void test1(){
		System.out.println("test1");
	}

	@Test
	void test2(){
		System.out.println("test2");
	}

	@Test
	void test(){
		System.out.println("test");
		int a = 1+1;
//		Assertions.assertEquals(2,a);

		Assertions.assertTrue(2 == a);
	}

	@Test
	void testMock(){

	}

}
