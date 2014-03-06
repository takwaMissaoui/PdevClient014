package edu.esprit.tn;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Pidev.Service.CorporateServiceRemote;
import Pidev.Service.CurrencyAccountCorporateServiceRemote;
import Pidev.entite.Corporate;
import Pidev.entite.CurrencyAccountCorporate;
import Pidev.entite.Trader;

public class CorporateTest {
	CorporateServiceRemote corporateSR;
	Corporate corporate = new Corporate();
	CurrencyAccountCorporateServiceRemote currencyAccountCorporateSR;
	CurrencyAccountCorporate currencyAccountCorporate = new CurrencyAccountCorporate();

	@Before
	public void setUp() throws Exception {
		InitialContext init = new InitialContext();
		corporateSR = (CorporateServiceRemote) init
				.lookup("Pidev/CorporateService!Pidev.Service.CorporateServiceRemote");
		currencyAccountCorporateSR = (CurrencyAccountCorporateServiceRemote) init
				.lookup("Pidev/CurrencyAccountCorporateService!Pidev.Service.CurrencyAccountCorporateServiceRemote");


	}

	@Test
	public void testAdd() {

		corporate.setLibelle("CorporateTest");
		corporate.setLogin("corporate1");
		corporate.setPass("corporate1");
		corporate.setCapitale(2000.0);
		corporate.setAddress("sfax");
		corporate.setMail("corporate1@esprit.tn");
		corporate.setTel((long) 22480620);

		Set<CurrencyAccountCorporate> listAcount = new HashSet<CurrencyAccountCorporate>();
		listAcount.add(currencyAccountCorporateSR.findAll().get(0));
		corporate.setAccount(listAcount);
		Set<Trader> traders = new HashSet<Trader>();
		Trader trader = new Trader();
		trader.setLibelle("corporatetrader1");
		trader.setLogin("corporatetrader1");
		trader.setPassword("corporatetrader1");
		trader.setMail("corporatetrader1@esprit.tn");
		traders.add(trader);
		corporate.setTrader(traders);
		corporateSR.add(corporate);
	}

	@Test
	public void testUpdate() {
		corporate = corporateSR.findAll().get(0);
		corporate.setLibelle("BKUpdate");
		corporateSR.update(corporate);

	}

	@Test
	public void testFindByID() {
		corporate = corporateSR.findByID(corporateSR.findAll().get(0)
				.getIdClient());
		Assert.assertEquals(corporate, corporateSR.findAll().get(0));
	}

	@Test
	public void testFindAll() {
		List<Corporate> listBK = corporateSR.findAll();
		Assert.assertEquals(corporateSR.findAll().size(), listBK.size());

	}

	@Test
	public void testDelete() {
		corporateSR.delete(corporateSR.findAll().get(0));
	}
}
