package edu.esprit.tn;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Pidev.Service.ClientServiceRemote;
import Pidev.entite.Client;
import Pidev.entite.Trader;

public class ClientTest {
	ClientServiceRemote clientSR;

	@Before
	public void setUp() throws Exception {
		InitialContext init = new InitialContext();
		clientSR = (ClientServiceRemote) init
				.lookup("Pidev/ClientService!Pidev.Service.ClientServiceRemote");

	}

	@Test
	public void testAdd() {
		Client client = new Client();
		client.setLibelle("adminTest");
		client.setLogin("admin");
		client.setPass("admin");
		client.setAddress("tunis");
		client.setTel((long) 22407620);
		Trader trader = new Trader();
		trader.setLibelle("ali");
		trader.setLogin("ali");
		Set<Trader> traders = new HashSet<Trader>();
		traders.add(trader);
		client.setTrader(traders);
		clientSR.add(client);
	}

	@Test
	public void testUpdate() {
		Client client = clientSR.findAll().get(0);
		client.setLibelle("testADMIN");
		clientSR.update(client);

	}

	@Test
	public void testFindByID() {
		Client client = clientSR.findByID(clientSR.findAll().get(0)
				.getIdClient());
		Assert.assertEquals(clientSR.findAll().get(0).getIdClient(),
				client.getIdClient());

	}

	@Test
	public void testFindAll() {
		List<Client> clients = clientSR.findAll();
		Assert.assertEquals(clientSR.findAll().size(), clients.size());
	}

	@Test
	public void testDelete() {
		clientSR.delete(clientSR.findAll().get(0));
	}

}
