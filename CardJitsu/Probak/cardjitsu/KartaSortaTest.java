package cardjitsu;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import cardjitsu.*;

public class KartaSortaTest {

	KartaSorta kSorta;
	@Before
	public void setUp() throws Exception {
		kSorta = KartaSorta.getKartaSorta();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetKartaSorta() {
		fail("Not yet implemented");
	}

	@Test
	public void testJsonetikKartetara() {
		kSorta.jsonetikKartetara();
		
		int normala = 0;
		int berezia = 0;
		
		int sua = 0;
		int ura = 0;
		int elurra = 0;
		
		int gorria = 0;
		int urdina = 0;
		int horia = 0;
		int berdea = 0;
		int laranja = 0;
		int morea = 0;
		
		int set1 = 0;
		int set2 = 0;
		int set3 = 0;
		int set4 = 0;
		int set5 = 0;
		int set6 = 0;
		int set7 = 0;
		int set8 = 0;
		
		//
		for (int i = 0; i<kSorta.getTamaina(); i++) {
			Karta karta = kSorta.getKarta(i);
			if(karta instanceof KartaNormala) {
				normala++;
			} else {
				berezia++;
			}
			switch (karta.getElementua()) {
			case SUA:
				sua++;
				break;
			case URA:
				ura++;
				break;
			case ELURRA:
				elurra++;
			default:
				break;
			}
		}
		
		assertEquals(405, normala);
		assertEquals(104, berezia);
		assertEquals(170, sua);
		assertEquals(183, ura);
		assertEquals(156, elurra);
		assertEquals(80, gorria);
		assertEquals(99, urdina);
		assertEquals(73, horia);
		assertEquals(86, berdea);
		assertEquals(84, laranja);
		assertEquals(87, morea);
		assertEquals(94, set1);
		assertEquals(19, set2);
		assertEquals(60, set3);
		assertEquals(60, set4);
		assertEquals(27, set5);
		assertEquals(95, set6);
		assertEquals(150, set7);
		assertEquals(4, set8);
	}

	@Test
	public void testGetKarta() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTamaina() {
		fail("Not yet implemented");
	}

}
