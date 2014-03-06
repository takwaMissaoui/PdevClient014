package edu.esprit.tn;

import javax.naming.InitialContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Pidev.Service.CurrencyServiceRemote;
import Pidev.entite.Currency;

public class CurrencyTest {
	CurrencyServiceRemote currencySR;
	Currency currency = new Currency();
	@Before
	public void setUp() throws Exception {
		InitialContext init = new InitialContext();
		currencySR = (CurrencyServiceRemote) init
				.lookup("Pidev/CurrencyService!Pidev.Service.CurrencyServiceRemote");

	}

	@Test
	public void testAdd() {
		currency.setId_currency("deviseTEST");
		currency.setName("Test");
		currencySR.add(currency);
	}

	@Test
	public void testUpdate() {
		currency.setId_currency("DEVISE");
		currency.setName("testDEVISE");
        currencySR.update(currency);
		
	}

	@Test
	public void testFindBy() {
		currency = currencySR.findBy("DEVISE");
		Assert.assertEquals("DEVISE",currency.getId_currency());
 
	}

	@Test
	public void testFindall() {
		currencySR.findall();
	}

	@Test
	public void testDelete() {
		currency.setId_currency("DEVISE");
		currencySR.delete(currency);
	}

	@Test
	public void testFindAvailables() {
		currencySR.findAvailables();
	}

	
}
