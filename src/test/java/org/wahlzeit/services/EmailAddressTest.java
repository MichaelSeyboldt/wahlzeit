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

package org.wahlzeit.services;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Test cases for the EmailAddress class.
 */
public class EmailAddressTest extends TestCase {

	/**
	 *
	 */
	public EmailAddressTest(String name) {
		super(name);
	}

	/**
	 *
	 */
	@Test
	public void testGetEmailAddressFromString() {
		// invalid email addresses are allowed for local testing and online avoided by Google

		assertTrue(createEmailAddressIgnoreException("bingo@bongo"));
		assertTrue(createEmailAddressIgnoreException("bingo@bongo.com"));
		assertTrue(createEmailAddressIgnoreException("bingo.bongo@bongo.com"));
		assertTrue(createEmailAddressIgnoreException("bingo+bongo@bango"));
	}

	@Test
	public void testEmailAddresEquals(){
		EmailAddress eq1,eq2,neq;
		try{
			eq1 = EmailAddress.getFromString("bingo@bongo.com");
			eq2 = EmailAddress.getFromString("bingo@bongo.com");
			neq = EmailAddress.getFromString("bingo.bongo@bongo.com");
		} catch (IllegalArgumentException ex){
			fail();
			return;
		}
		assertTrue(eq1.isEqual(eq2));
		assertTrue(eq2.isEqual(eq1));
		assertTrue(eq1.isEqual(eq1));
		assertFalse(eq1.isEqual(neq));

	}

	/**
	 *
	 */
	protected boolean createEmailAddressIgnoreException(String ea) {
		try {
			EmailAddress.getFromString(ea);
			return true;
		} catch (IllegalArgumentException ex) {
			// creation failed
			return false;
		}
	}

	/**
	 *
	 */
	@Test
	public void testEmptyEmailAddress() {
		assertFalse(EmailAddress.EMPTY.isValid());
	}

}

