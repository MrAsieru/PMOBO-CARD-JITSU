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
		jok = JokalariaLokala.getNireJokalaria("Izena");
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
	private void imprimatuKartak(String pTitulua) {
		System.out.println(pTitulua);
		for(int i = 0; i < jok.gordetakoKartenKantitatea(); i++) {
			System.out.println((i+1)+".Karta:");
			System.out.println(jok.lortuGordetakoKartaPosz(i).getElementua());
			System.out.println(jok.lortuGordetakoKartaPosz(i).getKolorea());
		}
	}
}
