package com.frank.shop.test;

import org.junit.Test;

import com.frank.shop.utils.MailUtils;

public class MailTest {
	@Test
	public void testSendMain(){
		MailUtils.sendMail("Frank@shop.com", "002");
	}
}
