/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package org.wahlzeit.services.mailing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.services.EmailAddress;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class EmailServiceTest {

	EmailService emailService = null;
	EmailAddress validAddress = null;

	@Before
	public void setup() throws Exception {
		emailService = EmailServiceManager.getDefaultService();
		validAddress = EmailAddress.getFromString("test@test.de");
	}

	@Test
	public void testSendInvalidEmail() {
		try {
			assertFalse(emailService.sendEmailIgnoreException(validAddress, null, "lol", "hi"));
			assertFalse(emailService.sendEmailIgnoreException(null, validAddress, null, "body"));
			assertFalse(emailService.sendEmailIgnoreException(validAddress, null, "hi", "       "));
		} catch (Exception ex) {
			Assert.fail("Silent mode does not allow exceptions");
		}
	}

	@Test
	public void testSendValidEmail() {
		try {
			assertTrue(emailService.sendEmailIgnoreException(validAddress, validAddress, "hi", "test"));
		} catch (Exception ex) {
			Assert.fail("Silent mode does not allow exceptions");
		}
	}

	@Test
	public void testSendInValidBcc(){
		try {
			assertFalse(emailService.sendEmailIgnoreException(validAddress,null,validAddress,"subject","hi"));
			assertFalse(emailService.sendEmailIgnoreException(null,validAddress,validAddress,null,"body"));
			assertFalse(emailService.sendEmailIgnoreException(validAddress,validAddress,validAddress,"subject",null));

		}catch (Exception ex) {
			fail();
		}
	}

	@Test
	public void testSendValidBcc(){
		try {
			assertTrue(emailService.sendEmailIgnoreException(validAddress,validAddress,validAddress,"subject","body"));
		}catch (Exception e){
			fail();
		}

	}
	@Test(expected = MailingException.class)
	public void sendWithException() throws MailingException {
			 emailService.sendEmail(null,validAddress,"subject","body");
	}

	@Test(expected = MailingException.class)
	public void sendWithExceptionBcc() throws MailingException {
		emailService.sendEmail(null, validAddress, validAddress,"subject","body");
	}
	@Test
	public void sentVaildwithExeption(){
		try {
			emailService.sendEmail(validAddress,validAddress,"subject", "body");
			emailService.sendEmail(validAddress,validAddress,validAddress,"subject","bodey");
		} catch (MailingException mx){
			fail();
		}

	}
}