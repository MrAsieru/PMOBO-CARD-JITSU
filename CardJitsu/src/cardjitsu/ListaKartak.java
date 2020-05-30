package cardjitsu;
import java.util.ArrayList;

public class ListaKartak {
	
	private ArrayList<Karta> lista;
	
	public ListaKartak() {
		this.lista= new ArrayList<Karta>();
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
