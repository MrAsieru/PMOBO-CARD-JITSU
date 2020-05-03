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

	public void kenduKarta (int pPos) {
		this.lista.remove(this.lortuKartaPosz(pPos));
	}
	
	public Karta lortuKartaPosz (int pPos) {
		Karta emaitza = null;
		if(0<=pPos && pPos <= this.getTamaina()-1) {
			emaitza = this.lista.get(pPos);
		}
		return emaitza;
	}
	
	public int getTamaina() {
		return lista.size();
	}
}
