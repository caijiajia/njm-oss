package com.yaoyaohao.test.demo;

import org.junit.Test;

import com.njm.oss.common.util.EhcacheUtil;

public class EhcacheTest {



	@Test
	public void test1() throws Exception {
		EhcacheUtil.set("11", "xxxdafsdf{/123123123}d");
		
		System.out.println(EhcacheUtil.get("11"));
	}
}
