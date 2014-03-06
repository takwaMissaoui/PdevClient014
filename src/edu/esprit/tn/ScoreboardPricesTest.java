package edu.esprit.tn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Pidev.Service.CurrencyServiceRemote;
import Pidev.Service.ScoreboardServiceRemote;
import Pidev.entite.AskBid;
import Pidev.entite.Currency;
import Pidev.entite.ScoreboardPrices;

public class ScoreboardPricesTest {
	ScoreboardServiceRemote scoreboardSR;
	ScoreboardPrices scoreboard = new ScoreboardPrices();
	CurrencyServiceRemote currencySR;
	Currency currency = new Currency();

	@Before
	public void setUp() throws Exception {
		InitialContext init = new InitialContext();
		scoreboardSR = (ScoreboardServiceRemote) init
				.lookup("Pidev/ScoreboardService!Pidev.Service.ScoreboardServiceRemote");
		currencySR = (CurrencyServiceRemote) init
				.lookup("Pidev/CurrencyService!Pidev.Service.CurrencyServiceRemote");


	}

	@Test
	public void testAdd() {
        Map<Currency, AskBid> price = new HashMap<Currency, AskBid>();
        currency = currencySR.findBy("€");
        AskBid ab = new AskBid();
        ab.setAsk(2.0);ab.setBid(2.1);
        price.put(currency,ab);
		scoreboard.setPrice(price);
        scoreboardSR.add(scoreboard);
	}

	@Test
	public void testUpdate() {
		scoreboard = scoreboardSR.findByID(scoreboardSR.findAll().get(0).getId());
		AskBid ab = new AskBid();
        ab.setAsk(1.0);ab.setBid(1.1);
		scoreboard.update(currencySR.findBy("ADP"), ab);
		scoreboardSR.update(scoreboard);

	}

	@Test
	public void testFindByID() {
		scoreboard = scoreboardSR.findByID(scoreboardSR.findAll().get(0).getId());

		Assert.assertEquals(scoreboard, scoreboardSR.findAll().get(0));
	}

	@Test
	public void testFindAll() {
		List<ScoreboardPrices> listTr = scoreboardSR.findAll();
		Assert.assertEquals(scoreboardSR.findAll().size(), listTr.size());

	}

	@Test
	public void testDelete() {
		scoreboardSR.delete(scoreboardSR.findAll().get(0));
	}
}
