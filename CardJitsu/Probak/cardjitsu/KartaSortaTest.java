package cardjitsu;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KartaSortaTest {

	KartaSorta kSorta;
	@Before
	public void setUp() throws Exception {
		kSorta = KartaSorta.getKartaSorta();
		kSorta.jsonetikKartetara();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testJsonetikKartetara() {
		
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
				break;
			}
			switch(karta.getKolorea()) {
			case GORRIA:
				gorria++;
				break;
			case URDINA:
				urdina++;
				break;
			case HORIA:
				horia++;
				break;
			case BERDEA:
				berdea++;
				break;
			case LARANJA:
				laranja++;
				break;
			case MOREA:
				morea++;
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

	}

	@Test
	public void testGetKarta() {
		Karta karta = kSorta.getKarta(122); //ID 123
		assertTrue(karta instanceof KartaNormala);
		assertEquals(ElementuMota.ELURRA, karta.getElementua());
		assertEquals(KoloreMota.LARANJA,karta.getKolorea());
		assertEquals(4, karta.getBalioa());
	}

}
