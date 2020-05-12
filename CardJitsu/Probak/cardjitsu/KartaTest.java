package cardjitsu;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KartaTest {
	Karta k1,k2;
	
	@Before
	public void setUp() throws Exception {
		k1=new KartaNormala (ElementuMota.SUA,10,KoloreMota.GORRIA);
		k2= new KartaBerezia (ElementuMota.URA,5,KoloreMota.HORIA,EfektuMota.KENDUGORRIBAT,"Deskribapena");
	}

	@After
	public void tearDown() throws Exception {
		k1=null;
		k2=null;
	}

	@Test
	public void testKarta() {
		assertNotNull(k1);
		assertNotNull(k2);
	}

	@Test
	public void testGetElementua() {
		assertEquals(k1.getElementua(),ElementuMota.SUA);
		assertEquals(k2.getElementua(),ElementuMota.URA);
	}

	@Test
	public void testGetBalioa() {
		assertEquals(k1.getBalioa(),10);
		assertEquals(k2.getBalioa(),5);
	}

	@Test
	public void testGetKolorea() {
		assertEquals(k1.getKolorea(),KoloreMota.GORRIA);
		assertEquals(k2.getKolorea(),KoloreMota.HORIA);
	}

	@Test
	public void testSetErabilgarria() {
		k1.setErabilgarria(false);
	}

	@Test
	public void testGetErabilgarria() {
		k1.setErabilgarria(false);
		assertFalse(k1.getErabilgarria());
		assertTrue(k2.getErabilgarria());
	}

	@Test
	public void testImprimatu() {
		k1.inprimatu();
		k2.inprimatu();
	}

}
