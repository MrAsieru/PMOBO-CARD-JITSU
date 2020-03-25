package CardJitsu;

public class KontsolaKontrolatzailea {
	public static KontsolaKontrolatzailea nireKontsolaKontrolagailua;
	
	public KontsolaKontrolatzailea(){}
	
	public String irakurri() 
	{
		
	}
	
	public void imprimatu(String pTestua) 
	{
		System.out.println(pTestua);
	} 
	
	public KontsolaKontrolatzailea getNireKontsolaKontrolatzailea() 
	{
		
	}
	
	public void kontsolaGarbitu() 
	{
		System.out.print("\033[H\033[2J");
		System.out.flush();
		//Es lo primero que he encontrado pero igual no esta bien
	}

}
