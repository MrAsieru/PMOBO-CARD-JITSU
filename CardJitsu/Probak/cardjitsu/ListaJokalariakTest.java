package cardjitsu;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ListaJokalariakTest {
	ListaJokalariak listaJ;
	Jokalaria jok;

	@BeforeClass
	public static void setUpClass() throws Exception {
		KartaSorta.getKartaSorta().jsonetikKartetara();
	}
	@Before
	public void setUp() {
		listaJ = ListaJokalariak.getNireListaJokalariak();
		jok = new JokalariaLokala("Izena");
	}

	@After
	public void tearDown() throws Exception {
		gordetakoKartakEzabatu();
	}

	@Test
	public void testPartidarenIrabazleaKonprobatu() {
		//Elementu berdina
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  |     |        |     |
+---------+-----+--------+-----+
| Horia   |     |        |     |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 1   |        |     |
+---------+-----+--------+-----+
| Horia   |     |        |     |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(0));//SUA Urdina
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 1   |        |     |
+---------+-----+--------+-----+
| Horia   | 1   |        |     |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(8));//SUA Horia
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 1   |        |     |
+---------+-----+--------+-----+
| Horia   | 1   |        |     |
+---------+-----+--------+-----+
| Berdea  | 1   |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(2));//SUA Berdea
		assertTrue(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		gordetakoKartakEzabatu();
		
		//Elementu desberdinak
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  |     |        |     |
+---------+-----+--------+-----+
| Horia   |     |        |     |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 1   |        |     |
+---------+-----+--------+-----+
| Horia   |     |        |     |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(0));//SUA Urdina
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 1   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 1      |     |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(167));//ELURRA Horia
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 1   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 1      |     |
+---------+-----+--------+-----+
| Berdea  |     |        | 1   |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(504));//URA Berdea
		imprimatuKartak("Elementu desberdinak");
		assertTrue(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		gordetakoKartakEzabatu();
		
		//1.Era
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  |     |        |     |
+---------+-----+--------+-----+
| Horia   |     |        |     |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 1   |        |     |
+---------+-----+--------+-----+
| Horia   |     |        |     |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(0));//SUA Urdina
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 1   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 1      |     |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(167));//ELURRA Horia
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 1   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 1      | 1   |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(485));//URA Horia
		imprimatuKartak("Elementu desberdinak");
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 1   |        |     |
+---------+-----+--------+-----+
| Horia   | 1   | 1      | 1   |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(500));//SUA Horia
		imprimatuKartak("Elementu desberdinak");
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 1   |        |     |
+---------+-----+--------+-----+
| Horia   | 1   | 1      | 1   |
+---------+-----+--------+-----+
| Berdea  | 1   |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(75));//SUA Berdea
		imprimatuKartak("1.Era");
		assertTrue(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		gordetakoKartakEzabatu();
		
		//2.Era
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  |     |        |     |
+---------+-----+--------+-----+
| Horia   |     |        |     |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 1   |        |     |
+---------+-----+--------+-----+
| Horia   |     |        |     |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(0));//SUA Urdina
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 1   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 1      |     |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(167));//ELURRA Horia
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 1   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 1      | 1   |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(485));//URA Horia
		imprimatuKartak("Elementu desberdinak");
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 1   |        |     |
+---------+-----+--------+-----+
| Horia   | 1   | 1      | 1   |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(500));//SUA Horia
		imprimatuKartak("Elementu desberdinak");
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 1   |        |     |
+---------+-----+--------+-----+
| Horia   | 1   | 1      | 1   |
+---------+-----+--------+-----+
| Berdea  |     | 1      |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(80));//ELURRA Berdea
		imprimatuKartak("2.Era");
		assertTrue(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		gordetakoKartakEzabatu();
		
		//3.Era
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  |     |        |     |
+---------+-----+--------+-----+
| Horia   |     |        |     |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 1   |        |     |
+---------+-----+--------+-----+
| Horia   |     |        |     |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(0));//SUA Urdina
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 2   |        |     |
+---------+-----+--------+-----+
| Horia   |     |        |     |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(0));//SUA Urdina
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 2   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 1      |     |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(167));//ELURRA Horia
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 2   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 1      |     |
+---------+-----+--------+-----+
| Berdea  | 1   |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(75));//SUA Berdea
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 2   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 1      |     |
+---------+-----+--------+-----+
| Berdea  | 2   |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(75));//SUA Berdea
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 2   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 1      |     |
+---------+-----+--------+-----+
| Berdea  | 3   |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(75));//SUA Berdea
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 2   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 1      |     |
+---------+-----+--------+-----+
| Berdea  | 3   |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   | 1   |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(163));//SUA Morea
		imprimatuKartak("3.Era");
		assertTrue(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		gordetakoKartakEzabatu();
		
		//3.Era
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  |     |        |     |
+---------+-----+--------+-----+
| Horia   |     |        |     |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 1   |        |     |
+---------+-----+--------+-----+
| Horia   |     |        |     |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(0));//SUA Urdina
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 2   |        |     |
+---------+-----+--------+-----+
| Horia   |     |        |     |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(0));//SUA Urdina
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 2   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 1      |     |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(167));//ELURRA Horia
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 2   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 1      |     |
+---------+-----+--------+-----+
| Berdea  | 1   |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(75));//SUA Berdea
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 2   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 1      |     |
+---------+-----+--------+-----+
| Berdea  | 2   |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(75));//SUA Berdea
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 2   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 1      |     |
+---------+-----+--------+-----+
| Berdea  | 3   |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(75));//SUA Berdea
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 2   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 1      |     |
+---------+-----+--------+-----+
| Berdea  | 3   |        |     |
+---------+-----+--------+-----+
| Laranja |     | 1      |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(492));//ELURRA Laranja
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 2   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 2      |     |
+---------+-----+--------+-----+
| Berdea  | 3   |        |     |
+---------+-----+--------+-----+
| Laranja |     | 1      |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(75));//ELURRA Horia
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 2   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 2      |     |
+---------+-----+--------+-----+
| Berdea  | 3   |        |     |
+---------+-----+--------+-----+
| Laranja |     | 1      |     |
+---------+-----+--------+-----+
| Morea   |     | 1      |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(82));//ELURRA Morea
		imprimatuKartak("3.Era");
		assertTrue(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		gordetakoKartakEzabatu();
		
		//4.Era
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  |     |        |     |
+---------+-----+--------+-----+
| Horia   |     |        |     |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 1   |        |     |
+---------+-----+--------+-----+
| Horia   |     |        |     |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(0));//SUA Urdina
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 2   |        |     |
+---------+-----+--------+-----+
| Horia   |     |        |     |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(0));//SUA Urdina
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 2   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 1      |     |
+---------+-----+--------+-----+
| Berdea  |     |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(167));//ELURRA Horia
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 2   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 1      |     |
+---------+-----+--------+-----+
| Berdea  | 1   |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(75));//SUA Berdea
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 2   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 1      |     |
+---------+-----+--------+-----+
| Berdea  | 2   |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(75));//SUA Berdea
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 2   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 1      |     |
+---------+-----+--------+-----+
| Berdea  | 3   |        |     |
+---------+-----+--------+-----+
| Laranja |     |        |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(75));//SUA Berdea
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 2   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 1      |     |
+---------+-----+--------+-----+
| Berdea  | 3   |        |     |
+---------+-----+--------+-----+
| Laranja |     | 1      |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(492));//ELURRA Laranja
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 2   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 2      |     |
+---------+-----+--------+-----+
| Berdea  | 3   |        |     |
+---------+-----+--------+-----+
| Laranja |     | 1      |     |
+---------+-----+--------+-----+
| Morea   |     |        |     |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(75));//ELURRA Horia
		assertFalse(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		/*
+=========+=====+========+=====+
|   K\E   | SUA | ELURRA | URA |
+=========+=====+========+=====+
| Gorria  |     |        |     |
+---------+-----+--------+-----+
| Urdina  | 2   |        |     |
+---------+-----+--------+-----+
| Horia   |     | 2      |     |
+---------+-----+--------+-----+
| Berdea  | 3   |        |     |
+---------+-----+--------+-----+
| Laranja |     | 1      |     |
+---------+-----+--------+-----+
| Morea   |     |        | 1   |
+---------+-----+--------+-----+
		 */
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(90));//URA Morea
		imprimatuKartak("4.Era");
		assertTrue(listaJ.partidarenIrabazleaKonprobatu(jok));
		
		
		gordetakoKartakEzabatu();

	}

	
	private void gordetakoKartakEzabatu() {		
		while(jok.gordetakoKartenKantitatea() != 0) {
			jok.kenduGordetakoKarta(jok.lortuGordetakoKartaPosz(0));
		}
	}
	
	private void jolastekoKartakEzabatu() {
		while(jok.jolastekoKartenKantitatea()!=0) {
			jok.kenduJolastekoKarta(jok.lortuJolastekoKartaPosz(0));
		}		
	}
	private void imprimatuKartak(String pTitulua) {
		System.out.println(pTitulua);
		for(int i = 0; i < jok.gordetakoKartenKantitatea(); i++) {
			System.out.println((i+1)+".Karta:");
			System.out.println(jok.lortuGordetakoKartaPosz(i).getElementua());
			System.out.println(jok.lortuGordetakoKartaPosz(i).getKolorea());
		}
	}
	
	@Test
	public void testAplikatuAurrekoEfektua() 
	{
		//Elementu bat duen karta bat kendu
		listaJ.aldatuEfektua(EfektuMota.KENDUELURRA);
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(11));
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(1));
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(2));
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(3));
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(10));
		listaJ.aplikatuAurrekoEfektua(jok);
		
		assertNotEquals(KartaSorta.getKartaSorta().getKarta(11),jok.lortuGordetakoKartaPosz(0));
		assertEquals(KartaSorta.getKartaSorta().getKarta(10),jok.lortuGordetakoKartaPosz(3));
		assertEquals(4,jok.gordetakoKartenKantitatea());
		
		gordetakoKartakEzabatu();
		
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(7));
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(1));
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(2));
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(3));
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(10));
		listaJ.aplikatuAurrekoEfektua(jok);
		
		assertNull(jok.lortuGordetakoKartaPosz(10));
		assertEquals(4,jok.gordetakoKartenKantitatea());
		
		gordetakoKartakEzabatu();
		
		//Kolore bat duen karta bat kendu
		listaJ.aldatuEfektua(EfektuMota.KENDUBERDEBAT);
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(7));
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(1));
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(2));
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(3));
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(10));
		listaJ.aplikatuAurrekoEfektua(jok);
		
		assertEquals(KartaSorta.getKartaSorta().getKarta(7),jok.lortuGordetakoKartaPosz(0));
		assertNotEquals(KartaSorta.getKartaSorta().getKarta(1),jok.lortuGordetakoKartaPosz(1));
		assertEquals(KartaSorta.getKartaSorta().getKarta(2),jok.lortuGordetakoKartaPosz(1));
		assertEquals(4,jok.gordetakoKartenKantitatea());
		
		gordetakoKartakEzabatu();
		
		//Kolore bateko karta guztiak kendu
		listaJ.aldatuEfektua(EfektuMota.KENDUBERDEGUZTIAK);
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(0));
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(1));
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(2));
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(3));
		listaJ.aplikatuAurrekoEfektua(jok);
		
		assertEquals(2,jok.gordetakoKartenKantitatea());
		assertEquals(KartaSorta.getKartaSorta().getKarta(0),jok.lortuGordetakoKartaPosz(0));
		assertEquals(KartaSorta.getKartaSorta().getKarta(3),jok.lortuGordetakoKartaPosz(1));
		assertNotEquals(KartaSorta.getKartaSorta().getKarta(1),jok.lortuGordetakoKartaPosz(1));
		
		gordetakoKartakEzabatu();
		jolastekoKartakEzabatu();
		
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(1));
		jok.gehituGordetakoKarta(KartaSorta.getKartaSorta().getKarta(2));
		listaJ.aplikatuAurrekoEfektua(jok);
		
		assertNotEquals(KartaSorta.getKartaSorta().getKarta(1),jok.lortuGordetakoKartaPosz(0));
		assertNotEquals(KartaSorta.getKartaSorta().getKarta(2),jok.lortuGordetakoKartaPosz(1));
		assertEquals(0,jok.gordetakoKartenKantitatea());
		
		gordetakoKartakEzabatu();
		
		//Elementu bat blokeatu
		listaJ.aldatuEfektua(EfektuMota.SUABLOKEATU);
		jok.gehituJolastekoKarta(KartaSorta.getKartaSorta().getKarta(1));
		jok.gehituJolastekoKarta(KartaSorta.getKartaSorta().getKarta(2));
		jok.gehituJolastekoKarta(KartaSorta.getKartaSorta().getKarta(10));
		listaJ.aplikatuAurrekoEfektua(jok);
		
		assertFalse(jok.lortuJolastekoKartaPosz(0).getErabilgarria());
		assertFalse(jok.lortuJolastekoKartaPosz(1).getErabilgarria());
		assertTrue(jok.lortuJolastekoKartaPosz(2).getErabilgarria());	
		
		gordetakoKartakEzabatu();
		jolastekoKartakEzabatu();
		
		listaJ.aldatuEfektua(EfektuMota.SUABLOKEATU);
		jok.gehituJolastekoKarta(KartaSorta.getKartaSorta().getKarta(1));
		jok.gehituJolastekoKarta(KartaSorta.getKartaSorta().getKarta(2));
		jok.gehituJolastekoKarta(KartaSorta.getKartaSorta().getKarta(3));
		jok.gehituJolastekoKarta(KartaSorta.getKartaSorta().getKarta(4));
		jok.gehituJolastekoKarta(KartaSorta.getKartaSorta().getKarta(5));
		listaJ.aplikatuAurrekoEfektua(jok);
		
		assertFalse(jok.lortuJolastekoKartaPosz(0).getErabilgarria());
		assertFalse(jok.lortuJolastekoKartaPosz(1).getErabilgarria());
		assertFalse(jok.lortuJolastekoKartaPosz(2).getErabilgarria());

	}
	
	@Test
	public void testKartakKonprobatu() 
	{
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(1),KartaSorta.getKartaSorta().getKarta(1));
		assertEquals(JokalariMota.BERDINKETA,listaJ.kartakKonprobatu());
		
		//Irabazlea LOKALA
		
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(1),KartaSorta.getKartaSorta().getKarta(10));
		assertEquals(JokalariMota.LOKALA,listaJ.kartakKonprobatu());
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(10),KartaSorta.getKartaSorta().getKarta(20));
		assertEquals(JokalariMota.LOKALA,listaJ.kartakKonprobatu());
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(20),KartaSorta.getKartaSorta().getKarta(1));
		assertEquals(JokalariMota.LOKALA,listaJ.kartakKonprobatu());
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(0),KartaSorta.getKartaSorta().getKarta(1));
		assertEquals(JokalariMota.LOKALA,listaJ.kartakKonprobatu());
		
		//Irabazlea BOT
		
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(10),KartaSorta.getKartaSorta().getKarta(1));
		assertEquals(JokalariMota.BOT,listaJ.kartakKonprobatu());
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(20),KartaSorta.getKartaSorta().getKarta(10));
		assertEquals(JokalariMota.BOT,listaJ.kartakKonprobatu());
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(1),KartaSorta.getKartaSorta().getKarta(20));
		assertEquals(JokalariMota.BOT,listaJ.kartakKonprobatu());
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(1),KartaSorta.getKartaSorta().getKarta(0));
		assertEquals(JokalariMota.BOT,listaJ.kartakKonprobatu());
		
		//Irabazleak bikendu/gehitu efektuaren arabera
		
		listaJ.aldatuAurrekoIrabazlea(0);
		listaJ.aldatuEfektua(EfektuMota.BIGEHITU);
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(1),KartaSorta.getKartaSorta().getKarta(0));
		assertEquals(JokalariMota.LOKALA,listaJ.kartakKonprobatu());
		listaJ.aldatuEfektua(EfektuMota.BIKENDU);
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(1),KartaSorta.getKartaSorta().getKarta(0));
		assertEquals(JokalariMota.LOKALA,listaJ.kartakKonprobatu());
		listaJ.aldatuAurrekoIrabazlea(1);
		listaJ.aldatuEfektua(EfektuMota.BIGEHITU);
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(1),KartaSorta.getKartaSorta().getKarta(0));
		assertEquals(JokalariMota.BOT,listaJ.kartakKonprobatu());
		listaJ.aldatuEfektua(EfektuMota.BIKENDU);
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(1),KartaSorta.getKartaSorta().getKarta(0));
		assertEquals(JokalariMota.BOT,listaJ.kartakKonprobatu());

		//Zenbaki txikiena irabazten du
		
		listaJ.aldatuEfektua(null);
		listaJ.aldatuZenbakiTxikiarenEfektua(true);
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(1),KartaSorta.getKartaSorta().getKarta(1));
		assertEquals(JokalariMota.BERDINKETA,listaJ.kartakKonprobatu());
		listaJ.aldatuZenbakiTxikiarenEfektua(true);
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(1),KartaSorta.getKartaSorta().getKarta(2));
		assertEquals(JokalariMota.LOKALA,listaJ.kartakKonprobatu());
		listaJ.aldatuZenbakiTxikiarenEfektua(true);
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(2),KartaSorta.getKartaSorta().getKarta(1));
		assertEquals(JokalariMota.BOT,listaJ.kartakKonprobatu());
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(1),KartaSorta.getKartaSorta().getKarta(2));
		assertEquals(JokalariMota.BOT,listaJ.kartakKonprobatu()); //Efektua berriro aplikatzen ez badugu kendu egingo da
				
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(72),KartaSorta.getKartaSorta().getKarta(2));
		assertEquals(JokalariMota.LOKALA,listaJ.kartakKonprobatu()); //Efektua karta baten bidez aplikatu
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(1),KartaSorta.getKartaSorta().getKarta(2));
		assertEquals(JokalariMota.LOKALA,listaJ.kartakKonprobatu());
		
		//Irabazleak elementu aldaketa efektuaren arabera
		
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(354),KartaSorta.getKartaSorta().getKarta(10));
		assertEquals(JokalariMota.LOKALA,listaJ.kartakKonprobatu()); //Elurretik urara
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(343),KartaSorta.getKartaSorta().getKarta(20));
		assertEquals(JokalariMota.LOKALA,listaJ.kartakKonprobatu()); //Uratik sura
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(),KartaSorta.getKartaSorta().getKarta());
		//TODO
		//assertEquals(JokalariMota.LOKALA,listaJ.kartakKonprobatu()); //Sutik elurrera (kartak ez daude ipinita)
		
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(10),KartaSorta.getKartaSorta().getKarta(354));
		assertEquals(JokalariMota.BOT,listaJ.kartakKonprobatu()); //Elurretik urara
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(20),KartaSorta.getKartaSorta().getKarta(343));
		assertEquals(JokalariMota.BOT,listaJ.kartakKonprobatu()); //Uratik sura
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(),KartaSorta.getKartaSorta().getKarta());
		//TODO
		//assertEquals(JokalariMota.LOKALA,listaJ.kartakKonprobatu()); //Sutik elurrera (kartak ez daude ipinita)
		
		//Irabazleak elementu aldaketa eta bigehitu/kendu arabera eta zenbaki txikiena efektua
		
		listaJ.aldatuAurrekoIrabazlea(0);
		listaJ.aldatuEfektua(EfektuMota.BIGEHITU);
		listaJ.aldatuZenbakiTxikiarenEfektua(true);
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(354),KartaSorta.getKartaSorta().getKarta(10));
		assertEquals(JokalariMota.BOT,listaJ.kartakKonprobatu());
		
		listaJ.aldatuAurrekoIrabazlea(0);
		listaJ.aldatuEfektua(EfektuMota.BIGEHITU);
		listaJ.aldatuZenbakiTxikiarenEfektua(true);
		listaJ.kartakAldatu(KartaSorta.getKartaSorta().getKarta(10),KartaSorta.getKartaSorta().getKarta(354));
		assertEquals(JokalariMota.LOKALA,listaJ.kartakKonprobatu());
	}
	
	@Test
	public void testElementuaIrabazi() {
		assertFalse(listaJ.elementuaIrabazi(ElementuMota.SUA, ElementuMota.SUA));
		assertTrue(listaJ.elementuaIrabazi(ElementuMota.SUA, ElementuMota.ELURRA));
		assertFalse(listaJ.elementuaIrabazi(ElementuMota.SUA, ElementuMota.URA));
		assertFalse(listaJ.elementuaIrabazi(ElementuMota.ELURRA, ElementuMota.SUA));
		assertFalse(listaJ.elementuaIrabazi(ElementuMota.ELURRA, ElementuMota.ELURRA));
		assertTrue(listaJ.elementuaIrabazi(ElementuMota.ELURRA, ElementuMota.URA));
		assertTrue(listaJ.elementuaIrabazi(ElementuMota.URA, ElementuMota.SUA));
		assertFalse(listaJ.elementuaIrabazi(ElementuMota.URA, ElementuMota.ELURRA));
		assertFalse(listaJ.elementuaIrabazi(ElementuMota.URA, ElementuMota.URA));
	}
}
