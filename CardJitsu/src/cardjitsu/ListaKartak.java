package cardjitsu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class ListaKartak {
	
	//atributuak
	private ArrayList<Karta> lista;
	
	//metodo eraikitzailea
	public ListaKartak() {
		this.lista= new ArrayList<Karta>();
	}
	
	//gainerako metodoak
	private Iterator<Karta> getIteradorea(){
		return this.lista.iterator();
	}
	
	public void  gehituKarta (Karta pKarta) {
		this.lista.add(pKarta);
	}
	
	public void kenduKarta (Karta pKarta) {
		this.lista.remove(pKarta);
	}

	public void kenduKarta (int pPos) throws TartetikKanpoException {
		this.lista.remove(this.lortuKartaPosz(pPos));
	}
	
	public Karta lortuKartaPosz (int pPos) throws TartetikKanpoException {
		if(pPos >= this.getTamaina()) {
			throw new TartetikKanpoException();
		}
		
		Karta k1=null;
		int lag=0;
		boolean aurkitua=false;
		Iterator<Karta> itr=this.getIteradorea();
		while (itr.hasNext() && !aurkitua) {
			k1=itr.next();
			if (lag==pPos) {
				aurkitua=true;
			}
			lag=lag+1;
		} 
		if (!aurkitua) {k1=null;}
		return k1;
	}
	
	public int getTamaina() {
		return lista.size();
	}
}
