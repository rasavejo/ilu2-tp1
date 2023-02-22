package histoire;

import personnages.*;
import villagegaulois.*;

public class ScenarioCasDegrade {
	
	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois obelix = new Gaulois("Obélix", 25);
		Village village = new Village("test",0,0);
		
		try {
			etal.acheterProduit(-5, obelix);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			System.out.println("Erreur attrapee");
		}
		
		try {
			village.afficherVillageois();
		} catch (VillageSansChefException e) {
			e.printStackTrace();
			System.out.println("Erreur attrapee");
		}
		
		System.out.println("Fin du test");
		}

}
