package edu.esprit.tn;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Pidev.Service.BanqueCentraleServiceRemote;
import Pidev.entite.BanqueCentrale;
import Pidev.entite.Trader;

public class BanqueCentraleTest {
	BanqueCentraleServiceRemote banqueCentraleSR;
	BanqueCentrale bkcentrale = new BanqueCentrale();

	@Before
	public void setUp() throws Exception {
		InitialContext init = new InitialContext();
		banqueCentraleSR = (BanqueCentraleServiceRemote) init
				.lookup("Pidev/BanqueCentraleService!Pidev.Service.BanqueCentraleServiceRemote");

	}

	@Test
	public void testAdd() {

		bkcentrale.setLibelle("BKcentrale");
		bkcentrale.setLogin("banqueCentrale");
		bkcentrale.setPass("banque");
		bkcentrale.setAddress("tunis");
		bkcentrale.setMail("bkcentrale@esprit.tn");
		bkcentrale.setTel((long) 71536840);
		Set<Trader> traders = new HashSet<Trader>();
		Trader trader = new Trader();
		trader.setLibelle("trader1");
		trader.setLogin("trader");
		trader.setPassword("trader");
		trader.setMail("trader@esprit.tn");
		traders.add(trader);
		bkcentrale.setTrader(traders);
		banqueCentraleSR.add(bkcentrale);
	}

	@Test
	public void testUpdate() {
		bkcentrale = banqueCentraleSR.findAll().get(0);
		bkcentrale.setLibelle("BKcentraleUpdate");
		banqueCentraleSR.update(bkcentrale);

	}

	@Test
	public void testFindByID() {
		bkcentrale = banqueCentraleSR.findByID(banqueCentraleSR.findAll()
				.get(0).getIdClient());
		Assert.assertEquals(bkcentrale, banqueCentraleSR.findAll().get(0));
	}

	@Test
	public void testFindAll() {
		List<BanqueCentrale> listBK = banqueCentraleSR.findAll();
		Assert.assertEquals(banqueCentraleSR.findAll().size(), listBK.size());

	}

	@Test
	public void testDelete() {
		banqueCentraleSR.delete(banqueCentraleSR.findAll().get(0));
	}
}
