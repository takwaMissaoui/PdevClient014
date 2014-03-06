package edu.esprit.tn;

import java.util.List;

import javax.naming.InitialContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Pidev.Service.CurrencyAccountCorporateServiceRemote;
import Pidev.Service.CurrencyServiceRemote;
import Pidev.entite.Currency;
import Pidev.entite.CurrencyAccountCorporate;

public class CurrencyAccountCorporateTest {
	CurrencyAccountCorporateServiceRemote currencyAccountCorporateSR;
	CurrencyAccountCorporate currencyAccountCorporate = new CurrencyAccountCorporate();
	CurrencyServiceRemote currencySR;
	Currency currency = new Currency();

	@Before
	public void setUp() throws Exception {
		InitialContext init = new InitialContext();
		currencyAccountCorporateSR = (CurrencyAccountCorporateServiceRemote) init
				.lookup("Pidev/CurrencyAccountCorporateService!Pidev.Service.CurrencyAccountCorporateServiceRemote");
		currencySR = (CurrencyServiceRemote) init
				.lookup("Pidev/CurrencyService!Pidev.Service.CurrencyServiceRemote");
	}

	@Test
	public void testAdd() {
		currencyAccountCorporate.setAmount((float) 500.0);
		currency = currencySR.findBy("€");
		currencyAccountCorporate.setCurrency(currency);
		currencyAccountCorporateSR.add(currencyAccountCorporate);
	}

	@Test
	public void testUpdate() {
		currencyAccountCorporate = currencyAccountCorporateSR.findAll().get(0);
		currencyAccountCorporate.setAmount((float) 300.0);
		currencyAccountCorporateSR.update(currencyAccountCorporate);

	}

	@Test
	public void testFindByID() {
		currencyAccountCorporate = currencyAccountCorporateSR
				.findByID(currencyAccountCorporateSR.findAll().get(0)
						.getIdAccountCorporate());

		Assert.assertEquals(currencyAccountCorporate,
				currencyAccountCorporateSR.findAll().get(0));
	}

	@Test
	public void testFindAll() {
		List<CurrencyAccountCorporate> listTr = currencyAccountCorporateSR
				.findAll();
		Assert.assertEquals(currencyAccountCorporateSR.findAll().size(),
				listTr.size());

	}

	@Test
	public void testDelete() {
		currencyAccountCorporateSR.delete(currencyAccountCorporateSR.findAll()
				.get(0));
	}

}
