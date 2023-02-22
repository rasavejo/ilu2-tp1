package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum,int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtals);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() throws VillageSansChefException {
		if (chef == null) throw new VillageSansChefException("Le village n'a pas de chef");
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		int numeroEtal = marche.trouverEtalLibre();
		marche.utiliserEtal(numeroEtal,vendeur,produit,nbProduit);
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() 
				+ " cherche un endroit pour vendre "
				+ nbProduit
				+ " "
				+ produit
				+ ". Le vendeur "
				+ vendeur.getNom()
				+ " vend des "
				+ produit
				+ " a l'etal n "
				+ numeroEtal
				+ ".");
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Les vendeurs qui proposent des fleurs sont :\n");
		Etal[] etals = marche.trouverEtal(produit);
		for (int i = 0; i < etals.length; i++) {
			chaine.append("-" + etals[i].getVendeur().getNom() + "\n");
		}
		return chaine.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		return rechercherEtal(vendeur).libererEtal();
	}
	
	public String afficherMarche() {
		return marche.afficherMarche();
	}
	
	private static class Marche {
		private Etal[] etals;
		
		public Marche(int nbEtals) {
			this.etals = new Etal[nbEtals];
			for (int i = 0;i<nbEtals;i++) etals[i] = new Etal();
		}
		
		public void utiliserEtal(int indiceEtal,Gaulois vendeur,String produit,int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		public int trouverEtalLibre() {
			for (int i = 0;i<etals.length;i++) {
				if (!etals[i].isEtalOccupe()) return i;
			}
			return -1;
		}
		
		public Etal[] trouverEtal(String produit) {
			Etal[] retour = new Etal[etals.length];
			int nbOccupe = 0;
			for (int i = 0;i<etals.length;i++)
				if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit))
					retour[nbOccupe++] = etals[i];
			Etal[] retourFinal = new Etal[nbOccupe];
			System.arraycopy(retour, 0, retourFinal, 0, nbOccupe);
			return retourFinal;
		}
		
		public Etal trouverVendeur(Gaulois vendeur) {
			for (int i = 0;i<etals.length;i++) {
				if (etals[i].getVendeur().equals(vendeur)) return etals[i];
			}
			return null;
		}
		
		public String afficherMarche() {
			int nbEtalVide = 0;
			StringBuilder affichage = new StringBuilder();
			for (int i = 0;i<etals.length;i++) 
				if (etals[i].isEtalOccupe()) affichage.append(etals[i].afficherEtal());
				else nbEtalVide ++;
			if (nbEtalVide != 0) { 
				affichage.append( "Il reste "
						  + nbEtalVide
						  + " etals non utilises dans le marche.\n");
			}
			return affichage.toString();
		}
	}
}