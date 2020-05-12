package cardjitsu;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KartaBereziaTest {
	KartaBerezia k1;
	@Before
	public void setUp() throws Exception {
		k1=new KartaBerezia (ElementuMota.URA,5,KoloreMota.HORIA,EfektuMota.KENDUGORRIBAT,"Deskribapena");
	}

	@After
	public void tearDown() throws Exception {
		k1=null;
	}

	@Test
	public void testImprimatu() {
		k1.inprimatu();
	}

	@Test
	public void testKartaBerezia() {
		assertNotNull(k1);
	}

	@Test
	public void testGetEfektua() {
		assertEquals(k1.getEfektua(),EfektuMota.KENDUGORRIBAT);
	}

	@Test
	public void testGetDeskripzioa() {
		assertEquals(k1.getDeskripzioa(),"Deskribapena");
	}

}
