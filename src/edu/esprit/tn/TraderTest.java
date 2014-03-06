package edu.esprit.tn;

import java.util.List;

import javax.naming.InitialContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Pidev.Service.TraderServiceRemote;
import Pidev.entite.Trader;

public class TraderTest {
	TraderServiceRemote traderSR;
	Trader trader = new Trader();
	@Before
	public void setUp() throws Exception {
		InitialContext init = new InitialContext();
		traderSR = (TraderServiceRemote) init
				.lookup("Pidev/TraderService!Pidev.Service.TraderServiceRemote");

	}
    
	@Test
	public void testAdd() {
		
		trader.setLibelle("TraderTest");
		trader.setLogin("admin");
		trader.setPassword("admin");
		traderSR.add(trader);
	}

	@Test
	public void testUpdate() {
		trader = traderSR.findAll().get(0);
		trader.setLibelle("TraderUpdate");
		traderSR.update(trader);

	}

	@Test
	public void testFindByID() {
		trader = traderSR.findByID(traderSR.findAll().get(0).getIdTradeur());
		
		Assert.assertEquals(trader,traderSR.findAll().get(0));
	}

	@Test
	public void testFindAll() {
		List<Trader> listTr = traderSR.findAll();
		Assert.assertEquals(traderSR.findAll().size(),listTr.size());
		
	}

	@Test
	public void testDelete() {
		traderSR.delete(traderSR.findAll().get(0));
	}
}
