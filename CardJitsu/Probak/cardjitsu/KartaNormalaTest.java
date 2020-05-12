package cardjitsu;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KartaNormalaTest {
	KartaNormala k1;
	@Before
	public void setUp() throws Exception {
		k1=new KartaNormala (ElementuMota.SUA,10,KoloreMota.GORRIA);
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
	public void testKartaNormala() {
		assertNotNull(k1);
	}

}
