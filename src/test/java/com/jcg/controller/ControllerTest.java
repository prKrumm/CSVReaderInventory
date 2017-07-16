package com.jcg.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jcg.model.Artikel;

public class ControllerTest {
	List<Artikel> artikeliste=new ArrayList<>();
	List<Artikel> artikelisteFALSCH=new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		List<Artikel> artikeliste=new ArrayList<>();
		List<Artikel> artikelisteFALSCH=new ArrayList<>();

		//druck pseudonym,lagerfach
		artikeliste.add(new Artikel("A15000","K03-3-5")); //OK
		artikeliste.add(new Artikel("a15000","k03-3-5")); //OK
		artikeliste.add(new Artikel("S15000","k03-3-5")); //OK
		artikeliste.add(new Artikel("s15000","k03-3-5")); //OK
		
		artikelisteFALSCH.add(new Artikel("G15000","K03-3-5")); 
		artikelisteFALSCH.add(new Artikel("A1500","K03-3-5")); 
		artikelisteFALSCH.add(new Artikel("AA5000","K03-3-5")); 
		artikelisteFALSCH.add(new Artikel("A15000","KK03-3-5")); 
		artikelisteFALSCH.add(new Artikel("A15000","K03-3-5")); 
		artikelisteFALSCH.add(new Artikel("A15000",null)); 
		artikelisteFALSCH.add(new Artikel("A15000","")); 


		
		
		
	}

	@Test
	public void testCheckArtikel() {
		Controller controller=new Controller();
		for(Artikel a:artikeliste)
		assertTrue(controller.checkArtikel(a));
		
		
		for(Artikel a:artikelisteFALSCH)
		assertFalse(controller.checkArtikel(a));
	}

}
