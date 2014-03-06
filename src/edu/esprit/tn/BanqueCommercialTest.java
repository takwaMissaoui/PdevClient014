package edu.esprit.tn;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Pidev.Service.BanqueCommercialServiceRemote;
import Pidev.Service.CurrencyAccountBanqueServiceRemote;
import Pidev.Service.ScoreboardServiceRemote;
import Pidev.entite.BanqueCommercial;
import Pidev.entite.CurrencyAccountBanque;
import Pidev.entite.ScoreboardPrices;
import Pidev.entite.Trader;

public class BanqueCommercialTest {
	BanqueCommercialServiceRemote banqueCommercialSR;
	BanqueCommercial banque = new BanqueCommercial();
	CurrencyAccountBanqueServiceRemote currencyAccountBanqueSR;
	CurrencyAccountBanque currencyAccountBanque = new CurrencyAccountBanque();
	ScoreboardServiceRemote scoreboardSR;
	ScoreboardPrices scoreboard = new ScoreboardPrices();

	@Before
	public void setUp() throws Exception {
		InitialContext init = new InitialContext();
		banqueCommercialSR = (BanqueCommercialServiceRemote) init
				.lookup("Pidev/BanqueCommercialService!Pidev.Service.BanqueCommercialServiceRemote");
		currencyAccountBanqueSR = (CurrencyAccountBanqueServiceRemote) init
				.lookup("Pidev/CurrencyAccountBanqueService!Pidev.Service.CurrencyAccountBanqueServiceRemote");
		scoreboardSR = (ScoreboardServiceRemote) init
				.lookup("Pidev/ScoreboardService!Pidev.Service.ScoreboardServiceRemote");
		
	}

	@Test
	public void testAdd() {

		banque.setLibelle("BKTestAccount");
		banque.setLogin("banqueCommercial1");
		banque.setPass("banqueCommercial1");
		banque.setFondPropre(1000.0);
		banque.setAddress("ariana");
		banque.setMail("BKcommercial1@esprit.tn");
		banque.setTel((long) 71230250);
		banque.setReqConfirmed(false);
		banque.setScoreboard(scoreboardSR.findAll().get(0));
		Set<CurrencyAccountBanque> listAcount = new HashSet<CurrencyAccountBanque>();
		listAcount.add(currencyAccountBanqueSR.findAll().get(0));
		banque.setAccount(listAcount);
		Set<Trader> traders = new HashSet<Trader>();
		Trader trader = new Trader();
		trader.setLibelle("BKtrader1");
		trader.setLogin("BKtrader");
		trader.setPassword("BKtrader");
		trader.setMail("BKtrader@esprit.tn");
		traders.add(trader);
		banque.setTrader(traders);
		banqueCommercialSR.add(banque);
	}

	@Test
	public void testUpdate() {
		banque = banqueCommercialSR.findAll().get(0);
		banque.setLibelle("BKUpdate");
		banqueCommercialSR.update(banque);

	}

	@Test
	public void testFindByID() {
		banque = banqueCommercialSR.findByID(banqueCommercialSR.findAll()
				.get(0).getIdClient());
		Assert.assertEquals(banque, banqueCommercialSR.findAll().get(0));
	}

	@Test
	public void testFindAll() {
		List<BanqueCommercial> listBK = banqueCommercialSR.findAll();
		Assert.assertEquals(banqueCommercialSR.findAll().size(), listBK.size());

	}

	@Test
	public void testDelete() {
		banqueCommercialSR.delete(banqueCommercialSR.findAll().get(0));
	}
}
