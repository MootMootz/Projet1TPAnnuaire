package fr.isika.cda24.TPAnnuaire.entitees;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import fr.isika.cda24.TPAnnuaire.entitees.Noeud;

public class Noeud {

	protected static final int FILS_NUL = -1;
	private Stagiaire cle;
	private int filsGauche;
	private int filsDroit;
	private int doublon;

	public static final int TAILLE_TOTALE_NOEUD_OCTETS = Stagiaire.TAILLE_Stagiaires_Octets + 12;

	// constructeur d'un noeud

	public Noeud(Stagiaire cle, int filsGauche, int filsDroit, int doublon) {
		super();
		this.cle = cle;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
		this.doublon = doublon;
	}

	public Noeud() {

	}

	public Stagiaire getCle() {
		return cle;
	}

	public void setCle(Stagiaire cle) {
		this.cle = cle;
	}

	public int getFilsGauche() {
		return filsGauche;
	}

	public void setFilsGauche(int filsGauche) {
		this.filsGauche = filsGauche;
	}

	public int getFilsDroit() {
		return filsDroit;
	}

	public void setFilsDroit(int filsDroit) {
		this.filsDroit = filsDroit;
	}

	public void ecrireStagiaire(Stagiaire stagiaire, RandomAccessFile raf) {

		try {
			//raf = new RandomAccessFile("src/main/java/fr/isika/cd24/annuaire/annuaire/test.bin", "rw");

			raf.seek(raf.length());

			raf.writeChars(stagiaire.getNomLong());
			//System.out.println("--------------------------------------------------------"+ stagiaire.getNomLong()+ "--------------------------------------------------------");

			raf.writeChars(stagiaire.getPrenomLong());

			raf.writeChars(stagiaire.getDptLong());
			raf.writeChars(stagiaire.getPromoLong());
			raf.writeChars(stagiaire.getAnneeLong());

			raf.writeInt(Noeud.FILS_NUL);
			raf.writeInt(Noeud.FILS_NUL);
			raf.writeInt(Noeud.FILS_NUL);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Noeud lireStagiaire(RandomAccessFile raf) {

		String nom = "";
		String prenom = "";
		String departement = "";
		String promo = "";
		String annee = "";
		int filsGauche = -1;
		int filsDroit = -1;
		int doublon = -1;

		try {
			// Lit bes variables de l'objet stagiaire

			for (int i = 0; i < Stagiaire.TAILLE_NOM_Caracteres; i++) {
				//System.out.println(i);
				nom += raf.readChar();
			}

			for (int i = 0; i < Stagiaire.TAILLE_Prenom_Caracteres; i++) {
				prenom += raf.readChar();

			}

			for (int i = 0; i < Stagiaire.TAILLE_Dpt_Caracteres; i++) {

				departement += raf.readChar();

			}
			for (int i = 0; i < Stagiaire.TAILLE_promo_Caracteres; i++) {
				promo += raf.readChar();
			}

			for (int i = 0; i < Stagiaire.TAILLE_annee_Caracteres; i++) {
				annee += raf.readChar();
			}

			filsGauche = raf.readInt();
			filsDroit = raf.readInt();
			doublon = raf.readInt();

			Stagiaire stagiaire = new Stagiaire(nom.trim(), prenom.trim(), departement.trim(), promo.trim(), annee.trim());
			
			return new Noeud(stagiaire, filsGauche, filsDroit, doublon);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public int getDoublon() {
		return doublon;
	}

	public void setDoublon(int doublon) {
		this.doublon = doublon;
	}

	public void affichageInfixeNoeud(RandomAccessFile raf) throws IOException {

		if (this.getFilsGauche() != -1) { // si fils gauche n'est pas vide
		
			raf.seek(this.getFilsGauche() * (TAILLE_TOTALE_NOEUD_OCTETS)); // déplacer le curseur au niveau du fils gauche
			
			Noeud noeudCourant = lireStagiaire(raf); // lire le fils gauche du noeud
			
			noeudCourant.affichageInfixeNoeud(raf); // appel récursif de la méthode
		}
		System.out.println(this.cle);

		if (this.getFilsDroit() != -1) {
		
			raf.seek(this.getFilsDroit() * (TAILLE_TOTALE_NOEUD_OCTETS));
			
			Noeud noeudCourant = lireStagiaire(raf);
			
			noeudCourant.affichageInfixeNoeud(raf);
		}
	}

	public void affichageInfixeNoeud(RandomAccessFile raf, List<Stagiaire> maListeInfixe) throws IOException {

		if (this.getFilsGauche() != -1) { // si fils gauche n'est pas vide
		
			raf.seek(this.getFilsGauche() * (TAILLE_TOTALE_NOEUD_OCTETS)); // déplacer le curseur au niveau du fils gauche
			
			Noeud noeudCourant = lireStagiaire(raf); // lire le fils gauche du noeud
			
			noeudCourant.affichageInfixeNoeud(raf,maListeInfixe); // appel récursif de la méthode
		}
		
		maListeInfixe.add(this.cle);
	
		if (this.getFilsDroit() != -1) {
		
			raf.seek(this.getFilsDroit() * (TAILLE_TOTALE_NOEUD_OCTETS));
			Noeud noeudCourant = lireStagiaire(raf);

			noeudCourant.affichageInfixeNoeud(raf,maListeInfixe);
		}
	}
	
	public Noeud rechercherStagiaire(String stagiaireRecherche, RandomAccessFile raf) throws IOException {
		
		System.out.println("Je recherche le stagiaire " + stagiaireRecherche + " dans le noeud " + this.getCle().getNom());

		if (this.getCle().getNom().trim().equalsIgnoreCase(stagiaireRecherche)) { // lorsque la valeur du nom recherché correspond au premier nom on l'affiche avec ses doublons

			return this;
		} else if (stagiaireRecherche.compareToIgnoreCase(this.getCle().getNom().trim()) < 0) { // si la valeur est inférieure on va au niveau du fils gauche

			System.out.println("Je pars à gauche");
		
			if (this.filsGauche == FILS_NUL) { // si la valeur du fils gauche est nulle ça signifie que le nom n'est pas disponible
	
				System.out.println("Ce stagiaire n'est pas présent dans l'annuaire");
	
				return null;
			} else { // sinon
	
				raf.seek(this.filsGauche * Noeud.TAILLE_TOTALE_NOEUD_OCTETS); // on se positionne au niveau du fils gauche
	
				Noeud filsGauche = lireStagiaire(raf); // on le lit
	
				return filsGauche.rechercherStagiaire(stagiaireRecherche, raf); // et on applique de façon récursive la méthode de recheche
			}
		} else {
			System.out.println("Je pars à droite");
	
			if (this.filsDroit == FILS_NUL) { // même principe avec le fils droit
	
				System.out.println("Ce stagiaire n'est pas présent dans l'annuaire");
	
				return null;
			} else {
				
				raf.seek(this.filsDroit * Noeud.TAILLE_TOTALE_NOEUD_OCTETS);
	
				Noeud filsDroit = lireStagiaire(raf);
		
				return filsDroit.rechercherStagiaire(stagiaireRecherche, raf);
			}
		}
	}

	@Override
	public String toString() {
		return "Noeud [cle=" + cle + ", filsGauche=" + filsGauche + ", filsDroit=" + filsDroit + ", doublon=" + doublon
				+ "]";
	}

	public void ajouterNoeud(Stagiaire stagiaireNouveau, RandomAccessFile raf) throws IOException { 
	
		int comparaison = this.getCle().getNom().compareTo(stagiaireNouveau.getNom()); // comparer le statgiaire à ajouter avec les stagiaires déjà présents
	
		if (comparaison > 0) {
			if (this.getFilsGauche() == FILS_NUL) {
	
				raf.seek(raf.getFilePointer() - 12); // déplace le curseur de 12 octets et se positionne sur la case du fils gauche
	
				raf.writeInt((int) (raf.length() / (TAILLE_TOTALE_NOEUD_OCTETS))); // calcul de l'index du nouveau noeud et l'écrit
	
				raf.seek(raf.length());
	
				ecrireStagiaire(stagiaireNouveau, raf); // écriture du nouveau noeud dans le fichier
			} else {

				raf.seek(this.getFilsGauche() * (TAILLE_TOTALE_NOEUD_OCTETS));// déplace le curseur au niveau du fils gauche
	
				Noeud noeudFilsGauche = lireStagiaire(raf);// lit le fils gauche
	
				noeudFilsGauche.ajouterNoeud(stagiaireNouveau, raf); // appel récursif de la méthode afin de trouver un moyen d'insérer le noeud
			}
		} else if (comparaison < 0) {
			if (this.getFilsDroit() == FILS_NUL) {
	
				raf.seek(raf.getFilePointer() - 8); // se déplace de 8 octets pour aller au niveau du fils droit
	
				raf.writeInt((int) (raf.length() / (TAILLE_TOTALE_NOEUD_OCTETS)));
	
				raf.seek(raf.length());
	
				ecrireStagiaire(stagiaireNouveau, raf);
			} else {
	
				raf.seek(this.getFilsDroit() * (TAILLE_TOTALE_NOEUD_OCTETS));
	
				Noeud noeudFilsDroit = lireStagiaire(raf);
	
				noeudFilsDroit.ajouterNoeud(stagiaireNouveau, raf);
			}
		} else if (comparaison == 0) {
	
			if (this.getDoublon() == FILS_NUL)
				;
			{
	
				raf.seek(raf.getFilePointer() - 4);
	
				raf.writeInt((int) (raf.length() / (TAILLE_TOTALE_NOEUD_OCTETS)));
	
				raf.seek(raf.length());

				ecrireStagiaire(stagiaireNouveau, raf);
			}
		} else {
			raf.seek(this.getDoublon() * (TAILLE_TOTALE_NOEUD_OCTETS));
			Noeud noeudDoublon = lireStagiaire(raf);
			noeudDoublon.ajouterNoeud(stagiaireNouveau, raf);
		}

	}
	public Noeud rechercherStagiaireSuppr(String stagiaireRechercheSuppr , int indexParent, RandomAccessFile raf) throws IOException {  
		
	int position = (int) (raf.getFilePointer() / Noeud.TAILLE_TOTALE_NOEUD_OCTETS) -1;
	
		System.out.println(stagiaireRechercheSuppr+" ********"+ this);
	
		if (this.getCle().getNom().trim().equalsIgnoreCase (stagiaireRechercheSuppr)) { // lorsque la valeur du nom recherché correspond au premier nom on l'affiche avec ses doublons 

			supprimerNoeud(this, raf, indexParent);
	
			return this; 
	
		} else if (stagiaireRechercheSuppr.compareToIgnoreCase(this.getCle().getNom().trim()) < 0) {  // si la valeur est inférieure on va au niveau du fils gauche 
	
			if (this.filsGauche == FILS_NUL) { // si la valeur du fils gauche est nulle ça signifie que le nom n'est pas disponible
	
				System.out .println("Ce stagiaire n'est pas présent dans l'annuaire");
	
				return null;
	
			} else { // sinon 
	
				raf.seek(this.filsGauche * Noeud.TAILLE_TOTALE_NOEUD_OCTETS) ; // on se positionne au niveau du fils gauche 
	
				Noeud filsGauche = lireStagiaire(raf); // on le lit 
	
				return filsGauche.rechercherStagiaireSuppr(stagiaireRechercheSuppr,position, raf); // et on applique de façon récursive la méthode de recheche 
			}
			} else {
	
				if (this.filsDroit== FILS_NUL) { // même principe avec le fils droit 
	
					System.out .println("Ce stagiaire n'est pas présent dans l'annuaire");
	
					return null;
				} else {
	
					raf. seek(this.filsDroit * Noeud.TAILLE_TOTALE_NOEUD_OCTETS);
	
					Noeud filsDroit = lireStagiaire(raf);
	
					return filsDroit.rechercherStagiaireSuppr(stagiaireRechercheSuppr, position, raf);
				}
			}
	}

	public Noeud supprimerNoeud(Noeud stagiaireSuppr, RandomAccessFile raf, int indexParent) throws IOException {
	
		System.out.println("-----------------FG-FD-D"+stagiaireSuppr.getFilsGauche()+ " "+ stagiaireSuppr.getFilsDroit()+ stagiaireSuppr.getDoublon());
		try {
			if (stagiaireSuppr.getFilsGauche() == FILS_NUL && stagiaireSuppr.getFilsDroit()== FILS_NUL) { // s'il s'agit d'une feuille 
	
				raf.seek(indexParent*TAILLE_TOTALE_NOEUD_OCTETS); // chercher l'index du parent ????
	
				Noeud noeudParent = lireStagiaire (raf); // on lit le noeud parent après s'être déplacé au niveau de son index
	
				if (noeudParent.getCle().getNom().trim().compareTo(stagiaireSuppr.getCle().getNom().trim())>0){  // on compare les valeurs des noms 
	
					raf.seek(raf.getFilePointer()-12); // se déplacer au niveau du fils gauche 
	
					raf.writeInt (-1);// déclarer le fils gauche comme nul 
	
				} else if (noeudParent.getCle().getNom().trim().compareTo(stagiaireSuppr.getCle().getNom().trim()) < 0){
	
					raf.seek(raf.getFilePointer()-8); // se déplacer au niveau du fils droit 
	
					raf.writeInt (-1);// le déclarer nul
				}	
			} else if ((stagiaireSuppr.getFilsGauche () == FILS_NUL) || (stagiaireSuppr.getFilsDroit() == FILS_NUL)){

				int index = (int) raf.getFilePointer(); // sauvegarde du curseur

				if (stagiaireSuppr.getFilsGauche()!=1){ //si c'est le fils gauche qui est different de -1

					raf.seek(indexParent*TAILLE_TOTALE_NOEUD_OCTETS);//tu te déplace à l'index du parent 

						Noeud noeudParent = lireStagiaire (raf); //tu lis le parent

						if (noeudParent.getCle().getNom().trim().compareTo(stagiaireSuppr.getCle().getNom().trim())>0) { //tu regarde si le noeud à supp était à gauche

							raf.seek(raf.getFilePointer()-12);
	
							raf.writeInt(stagiaireSuppr.filsGauche);  //tu écris l'index filsGauche du noeud à supp dans l'index gauche du parent
	
						} else if(noeudParent.getCle().getNom().trim().compareToIgnoreCase(stagiaireSuppr.getCle().getNom().trim()) < 0) {
	
							raf.seek(raf.getFilePointer() - 8);
	
							raf.writeInt(stagiaireSuppr.filsGauche);
						}
					} else {
						raf.seek(indexParent*TAILLE_TOTALE_NOEUD_OCTETS);
						
						Noeud noeudParent = lireStagiaire(raf);
						
						if(noeudParent.getCle().getNom().trim().compareToIgnoreCase(stagiaireSuppr.getCle().getNom().trim()) > 0) {
	
							raf.seek(raf.getFilePointer() - 12);

							raf.writeInt(stagiaireSuppr.filsDroit);
	
						} else if (noeudParent.getCle().getNom().trim().compareToIgnoreCase(stagiaireSuppr.getCle().getNom().trim()) < 0){
	
							raf.seek(raf.getFilePointer() - 8);
					
							raf.writeInt(stagiaireSuppr.filsDroit);
						}

				
				     //si c'est le fils droit qui est different de -1 
					//tu écris l'index fils droit du noeud à supp soit en fils gauche parent soir en fils droit
			
					
				}
			} else if (stagiaireSuppr.getFilsGauche()!=FILS_NUL && stagiaireSuppr.getFilsDroit()!=FILS_NUL){ 
				
				int position = (int) raf.getFilePointer();
	
				raf.seek(stagiaireSuppr.getFilsGauche()*TAILLE_TOTALE_NOEUD_OCTETS);

				Noeud noeudSupprimerFilsGauche = lireStagiaire (raf);
				
				Noeud descendant = chercherDescendant(noeudSupprimerFilsGauche, raf);
				
				System.out.println(descendant);
				System.out.println(position);
				
				raf.seek(position-TAILLE_TOTALE_NOEUD_OCTETS); // on revient à la position sauvegacdée precedemnent
				raf.writeChars(descendant.getCle().getNomLong());
				raf.writeChars(descendant.getCle().getPrenomLong());
				raf.writeChars(descendant.getCle().getDptLong());
				raf.writeChars(descendant.getCle().getPromoLong());
				raf.writeChars(descendant.getCle().getAnneeLong());
				
				raf.seek(raf.getFilePointer() + 8);
				raf.writeInt(descendant.doublon);
				
				raf.seek(position);
				
				Noeud ecrit = lireStagiaire(raf);
				System.out.println("//////////////////////"+ecrit+"--------------->"+descendant);
				raf.seek(stagiaireSuppr.filsGauche * Noeud.TAILLE_TOTALE_NOEUD_OCTETS);
				
				rechercherStagiaireSuppr(descendant.getCle().getNom(), stagiaireSuppr.getFilsGauche(), raf);
				
					// on va chercher le descendant => le plus grand noeud du sous arbre gauche
					
				//tu crée une variable descendant et stocker le retour de ta methode chercherdescendant (filsGauche, raf)
					
				//tu te places au début du noeud à supprimer (indexNoeudSuppr)
					
					
				 //tu réécris les information du stagiaire avec celles du descendant 
				 //tu ecris le doublon du descendant dnas le douvlon  du noeud à suppr
					
					//tu te deplace dnas le fils gauche du noeud suppr
							
					 //tu appelles la méthodes filsgauche.rechercherSuppr(descendant, indexNoeudSuppr,raf)
				}
		} catch (Exception e) {
			e.printStackTrace();
			}
		return null;
}
	
	public Noeud chercherDescendant(Noeud noeudCourant, RandomAccessFile raf) throws IOException {
		
		if (noeudCourant.getFilsDroit() == FILS_NUL) {

			System.out.println("j'ai trouvé le prédecesseur"+ noeudCourant);

			return noeudCourant; 

		} else {
			System.out.println("Mon fils droit "+ noeudCourant.filsDroit);

			raf.seek(noeudCourant.filsDroit*TAILLE_TOTALE_NOEUD_OCTETS);
	
			Noeud noeudDescendant = lireStagiaire(raf);

			return chercherDescendant(noeudDescendant, raf);
		}
		
	}
	
	//méthode Npeud chercherDescendant(Noeud courant, raf) 
		//si fils droit == -1 
			//return this
		//sinon 
			//tu deplaces à l'index fils droit
			//tu lis le noeud
			//tu rappelles la méthode sur le noeud
}


//	public Noeud supprimerNoeud(Noeud stagiaireSuppr, RandomAccessFile raf, int indexParent) throws IOException {
//
//		try {
//			if (stagiaireSuppr.getFilsGauche() == FILS_NUL && stagiaireSuppr.getFilsDroit()== FILS_NUL) { // s'il s'agit d'une feuille 
//				 raf.seek(indexParent*TAILLE_TOTALE_NOEUD_OCTETS); // chercher l'index du parent ????
//				Noeud noeudParent = lireStagiaire (raf); // on lit le noeud parent après s'être déplacé au niveau de son index
//				if (noeudParent.getCle().getNom().trim().compareTo(stagiaireSuppr.getCle().getNom().trim())>0){  // on compare les valeurs des noms 
//					raf.seek(raf.getFilePointer()-12); // se déplacer au niveau du fils gauche 
//					raf.writeInt (-1);// déclarer le fils gauche comme nul 
//				} else if (noeudParent.getCle().getNom().trim().compareTo(stagiaireSuppr.getCle().getNom().trim()) < 0){
//					raf.seek(raf.getFilePointer()-8); // se déplacer au niveau du fils droit 
//					raf.writeInt (-1);// le déclarer nul
//				} 
//			} else if ((stagiaireSuppr.getFilsGauche () == FILS_NUL) && (stagiaireSuppr.getFilsDroit() != FILS_NUL)){
//				}
//		}
//	}
//}
