package fr.isika.cda24.TPAnnuaire.entitees;

import java.util.ArrayList;
import java.util.List;


public class RechercheMultiCritere {

		// METHODE COURTE ! ;)
		private List<Stagiaire> laListeDeStagiaire;

		public List<Stagiaire> listeStagiaireMulti(String nom, String prenom, String departement, String promo, String annee) {
			
		    List<Stagiaire> listeStagiaireMulti = new ArrayList<>();

		    for (Stagiaire stagiaire : laListeDeStagiaire) {
		        if ((nom == null || stagiaire.getNom().equalsIgnoreCase(nom))
		                && (prenom == null || stagiaire.getPrenom().equalsIgnoreCase(prenom))
		                && (departement == null || stagiaire.getDepartement().equalsIgnoreCase(departement))
		                && (promo == null || stagiaire.getPromo().equalsIgnoreCase(promo))
		                && (annee == null || stagiaire.getAnnee().equalsIgnoreCase(annee))) {
		        	listeStagiaireMulti.add(stagiaire);
		        }
		    }

		    return listeStagiaireMulti;
		}

		public List<Stagiaire> getLaListeDeStagiaire() {
			return laListeDeStagiaire;
		}

		public void setLaListeDeStagiaire(List<Stagiaire> laListeDeStagiaire) {
			this.laListeDeStagiaire = laListeDeStagiaire;
		}
		
//		public void rechercheMulticriteres(String nomStagiaireRecherche, String prenomStagiaireRecherche,
//				String departementStagiaireRecherche, String promoStagiaireRecherche, RandomAccessFile raf)
//				throws IOException {
//
//			Stagiaire stagiaireRecherche = new Stagiaire();
//			List<Stagiaire> listeMultiCriteres = new ArrayList<>();
//			Scanner scNom = new Scanner(System.in);
//			Scanner scPrenom = new Scanner(System.in);
//			Scanner scDepartement = new Scanner(System.in);
//			Scanner scPromo = new Scanner(System.in);
//			Scanner scAnnee = new Scanner(System.in);
//
//			String searchFieldNom = scNom.nextLine();
//			String searchFieldPrenom = scPrenom.nextLine();
//			String searchFieldDepartement = scDepartement.nextLine();
//			String searchFieldPromo = scPromo.nextLine();
//			String searchFieldAnnee = scAnnee.nextLine();

			
			/*
			 * si rien de rentré >> affiche toute la liste si un seul critère de rentré,
			 * fait une recherche si un deuxième, fait une nouvelle recherche à l'intérieur
			 * de cette liste
			 */

			// valueOf(List<Stagiaire> stagiaire);

			// Lire les caractères rentrés dans les blocs recherche

//			if (searchFieldPrenom != null) {
//				if (stagiaireRecherche.nom.equals(searchFieldNom)) }
//				}

			
			

//			searchFieldNom.nextChar().isBlank();
//			searchFieldPrenom.next
//			searchFieldDepartement.next
//			searchFieldPromo
//			searchFiedAnnee
		//	

//				if (searchFieldNom != null) {
		//
//					if (sc.next().chars().getClass().getField(Stagiaire) = Stagiaire.get)
//						System.out.println();

			// TABLEAU
//			for (Stagiaire stag : laListeDeStagiaire) {
//				System.out.println(stag);

//			if(sc.next().chars()=Sagiaire.getPrenom)System.out.println(Annuaire);if(sc.next().chars().raf=Sagiaire.getDepartement)System.out.println(Annuaire);if(sc.next().chars().raf=Sagiaire.getPromo)System.out.println(Annuaire);if(sc.next().chars().raf=Sagiaire.getA)System.out.println(Annuaire);
		//}}}

			
		}
