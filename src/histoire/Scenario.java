package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;
import villagegaulois.VillageSansChefException;

public class Scenario {

	public static void main(String[] args) {
		Village village = new Village("le village des irréductibles", 10, 5);
		Chef abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		Druide druide = new Druide("Panoramix", 2, 5, 10);
		Gaulois obelix = new Gaulois("Obélix", 25);
		Gaulois asterix = new Gaulois("Astérix", 8);
		Gaulois assurancetourix = new Gaulois("Assurancetourix", 2);
		Gaulois bonemine = new Gaulois("Bonemine", 7);
		
		village.ajouterHabitant(bonemine);
		village.ajouterHabitant(assurancetourix);
		village.ajouterHabitant(asterix);
		village.ajouterHabitant(obelix);
		village.ajouterHabitant(druide);
		village.ajouterHabitant(abraracourcix);
		village.afficherVillageois();

		System.out.println(village.installerVendeur(bonemine, "fleurs", 20));
		System.out.println(village.installerVendeur(assurancetourix, "lyres", 5));
		System.out.println(village.installerVendeur(obelix, "menhirs", 2));
		System.out.println(village.installerVendeur(druide, "fleurs", 10));

		System.out.println(village.rechercherVendeursProduit("fleurs"));
		Etal etalFleur = village.rechercherEtal(bonemine);
		
		System.out.println(etalFleur.acheterProduit(10, abraracourcix));
		System.out.println(etalFleur.acheterProduit(15, obelix));
		System.out.println(etalFleur.acheterProduit(15, assurancetourix));
		
		try {
			etalFleur.acheterProduit(-5, obelix);
		} catch (IllegalArgumentException e) {
			System.out.println("Nombre d'achat négatif");
		}
		
		Etal etal = new Etal();
		try {
			etal.acheterProduit(5, obelix);
		} catch (IllegalStateException e) {
			System.out.println("Etal vide");
		}
		
		
		System.out.println(village.partirVendeur(bonemine));
		System.out.println(village.afficherMarche());
		
		try {
		System.out.println(village.afficherVillageois());
		} catch (VillageSansChefException e) {
			System.out.println("Le village n'a pas de chef");
		}
		
		Village villageTest = new Village("village",50,10);
		try {
		System.out.println(villageTest.afficherVillageois());
		} catch (VillageSansChefException e) {
			System.out.println("Le village n'a pas de chef");
		}
	}
}
