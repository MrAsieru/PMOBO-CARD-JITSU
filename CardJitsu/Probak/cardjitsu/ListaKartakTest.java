package cardjitsu;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ListaKartakTest {
	ListaKartak lis1;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		KartaSorta.getKartaSorta().jsonetikKartetara();
	}
	@Before
	public void setUp() throws Exception {
		lis1 = new ListaKartak();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGehituKarta() {
		assertEquals(0,lis1.getTamaina());
		lis1.gehituKarta(KartaSorta.getKartaSorta().getKarta());
		assertEquals(1,lis1.getTamaina());
		lis1.gehituKarta(KartaSorta.getKartaSorta().getKarta());
		lis1.gehituKarta(KartaSorta.getKartaSorta().getKarta());
		lis1.gehituKarta(KartaSorta.getKartaSorta().getKarta());
		assertEquals(4,lis1.getTamaina());
	}

	@Test
	public void testKenduKartaKarta() {
		Karta k1 = KartaSorta.getKartaSorta().getKarta();
		Karta k2 = KartaSorta.getKartaSorta().getKarta();
		Karta k3 = KartaSorta.getKartaSorta().getKarta();
		lis1.gehituKarta(k1);
		lis1.gehituKarta(k2);
		lis1.gehituKarta(k3);
		lis1.gehituKarta(KartaSorta.getKartaSorta().getKarta());
		lis1.gehituKarta(KartaSorta.getKartaSorta().getKarta());
		lis1.gehituKarta(KartaSorta.getKartaSorta().getKarta());
		assertEquals(6,lis1.getTamaina());
		lis1.kenduKarta(k1);
		lis1.kenduKarta(k3);
		assertEquals(4,lis1.getTamaina());
	}

	@Test
	public void testKenduKartaInt() {
		Karta k1 = KartaSorta.getKartaSorta().getKarta();
		Karta k2 = KartaSorta.getKartaSorta().getKarta();
		Karta k3 = KartaSorta.getKartaSorta().getKarta();
		lis1.gehituKarta(k1);
		lis1.gehituKarta(k2);
		lis1.gehituKarta(k3);
		lis1.gehituKarta(KartaSorta.getKartaSorta().getKarta());
		lis1.gehituKarta(KartaSorta.getKartaSorta().getKarta());
		lis1.gehituKarta(KartaSorta.getKartaSorta().getKarta());
		assertEquals(6,lis1.getTamaina());
		
		lis1.kenduKarta(2);
		lis1.kenduKarta(0);
		lis1.kenduKarta(0);
		
		assertEquals(3,lis1.getTamaina());
	}

	@Test
	public void testLortuKartaPosz() {
		Karta k1 = KartaSorta.getKartaSorta().getKarta();
		Karta k2 = KartaSorta.getKartaSorta().getKarta();
		Karta k3 = KartaSorta.getKartaSorta().getKarta();
		lis1.gehituKarta(k1);
		lis1.gehituKarta(KartaSorta.getKartaSorta().getKarta());
		lis1.gehituKarta(KartaSorta.getKartaSorta().getKarta());
		lis1.gehituKarta(k2);
		lis1.gehituKarta(KartaSorta.getKartaSorta().getKarta());
		lis1.gehituKarta(k3);
		
		assertEquals(k1,lis1.lortuKartaPosz(0));
		assertEquals(k2,lis1.lortuKartaPosz(3));
		assertEquals(k3,lis1.lortuKartaPosz(5));

	}

	@Test
	public void testGetTamaina() {
		Karta k1 = KartaSorta.getKartaSorta().getKarta();
		Karta k2 = KartaSorta.getKartaSorta().getKarta();
		assertEquals(0,lis1.getTamaina());
		lis1.gehituKarta(KartaSorta.getKartaSorta().getKarta());
		assertEquals(1,lis1.getTamaina());
		lis1.gehituKarta(KartaSorta.getKartaSorta().getKarta());
		lis1.gehituKarta(k1);
		lis1.gehituKarta(k2);
		assertEquals(4,lis1.getTamaina());
		lis1.kenduKarta(k1);
		lis1.kenduKarta(k2);
		assertEquals(2, lis1.getTamaina());
	}

}
