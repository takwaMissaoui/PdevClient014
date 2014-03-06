package edu.esprit.tn;

import java.util.List;

import javax.naming.InitialContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Pidev.Service.CurrencyAccountBanqueServiceRemote;
import Pidev.Service.CurrencyServiceRemote;
import Pidev.entite.Currency;
import Pidev.entite.CurrencyAccountBanque;

public class CurrencyAccountBanqueTest {
	CurrencyAccountBanqueServiceRemote currencyAccountBanqueSR;
	CurrencyAccountBanque currencyAccountBanque = new CurrencyAccountBanque();
	CurrencyServiceRemote currencySR;
	Currency currency = new Currency();


	@Before
	public void setUp() throws Exception {
		InitialContext init = new InitialContext();
		currencyAccountBanqueSR = (CurrencyAccountBanqueServiceRemote) init
				.lookup("Pidev/CurrencyAccountBanqueService!Pidev.Service.CurrencyAccountBanqueServiceRemote");
		currencySR = (CurrencyServiceRemote) init
				.lookup("Pidev/CurrencyService!Pidev.Service.CurrencyServiceRemote");
		
	}

	@Test
	public void testAdd() {
		currencyAccountBanque.setAmount((float) 300.0);

        currency = currencySR.findBy("€");

		currencyAccountBanque.setCurrency(currency);
		
		currencyAccountBanqueSR.add(currencyAccountBanque);
	}

	@Test
	public void testUpdate() {
		currencyAccountBanque = currencyAccountBanqueSR.findAll().get(0);
		currencyAccountBanque.setAmount((float) 300.0);
		currencyAccountBanqueSR.update(currencyAccountBanque);

	}

	@Test
	public void testFindByID() {
		currencyAccountBanque = currencyAccountBanqueSR
				.findByID(currencyAccountBanqueSR.findAll().get(0)
						.getIdAccountBanque());

		Assert.assertEquals(currencyAccountBanque, currencyAccountBanqueSR
				.findAll().get(0));
	}

	@Test
	public void testFindAll() {
		List<CurrencyAccountBanque> listTr = currencyAccountBanqueSR.findAll();
		Assert.assertEquals(currencyAccountBanqueSR.findAll().size(),
				listTr.size());

	}

	@Test
	public void testDelete() {
		currencyAccountBanqueSR
				.delete(currencyAccountBanqueSR.findAll().get(0));
	}
}
