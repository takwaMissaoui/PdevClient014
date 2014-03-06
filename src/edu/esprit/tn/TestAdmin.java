package edu.esprit.tn;

import javax.naming.InitialContext;

import org.junit.Before;
import org.junit.Test;

import Pidev.Service.AdminServiceRemote;
import Pidev.entite.Admin;

public class TestAdmin {

	AdminServiceRemote adminSremote;
	
	@Before
	public void setUp() throws Exception {
		InitialContext init = new InitialContext();
		adminSremote=(AdminServiceRemote)init.lookup("Pidev/AdminService!Pidev.Service.AdminServiceRemote");
	}




	@Test
	public void testAdd() {
		Admin admin = new Admin();
		admin.setLogin("admin");
		admin.setPass("admin");
		admin.setMail("admin@esprit.tn");
	    adminSremote.add(admin);
	    
	}

	@Test
	public void testUpdate() {
		Admin admin = adminSremote.findByID(adminSremote.findAll().get(0).getId());
		admin.setLogin("login");
		adminSremote.update(admin);
	}

	@Test
	public void testRemove() {
		Admin admin = adminSremote.findByID(adminSremote.findAll().get(0).getId());
		adminSremote.remove(admin);
		
	}


}
