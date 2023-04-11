package fr.isika.cda24.TPAnnuaire.entitees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ArbreBinaireDeRechercheAnnuaire {

	private RandomAccessFile raf;

	private Noeud racine;

	public ArbreBinaireDeRechercheAnnuaire() throws IOException {

		this.racine = new Noeud();

		try {
			this.raf = new RandomAccessFile("src/main/java/STAGIAIRES.bin", "rw");

			if (raf.length() == 0) {

				ArrayList<Stagiaire> lesStagiaires = new ArrayList<Stagiaire>();

				// Lecture du fichier .DON et création des objets Stagiaire

				String donFilePath = "src/main/java/STAGIAIRES.DON";

				// Création d'un FileReader et d'un BufferedReader pour lire le fichier

				FileReader fr = new FileReader(donFilePath);
				BufferedReader br = new BufferedReader(fr);

				// Lecture des lignes du fichier et création des objets Stagiaire
				while (br.ready()) {

					Stagiaire stagiaire = new Stagiaire(br.readLine(), br.readLine(), br.readLine(), br.readLine(),
							br.readLine());

					br.readLine();
					lesStagiaires.add(stagiaire);

				}
				for (Stagiaire stagiaire : lesStagiaires) {

					this.ajouterDansArbre(stagiaire, this.raf);

					// System.out.println(stagiaire);
				}

				// Fermeture des flux de lecture
				fr.close();
				br.close();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArbreBinaireDeRechercheAnnuaire(Noeud racine) {
		this.racine = racine;
	}

	public RandomAccessFile getRaf() {
		return raf;
	}

	public void setRaf(RandomAccessFile raf) {
		this.raf = raf;
	}

	public void ajouterDansArbre(Stagiaire nomAjout, RandomAccessFile raf) throws IOException {
		// si arbre vide
		if (raf.length() == 0) {
			raf.seek(0);
			racine.ecrireStagiaire(nomAjout, raf);
		} else {
			raf.seek(0);
			Noeud noeudCourant = racine.lireStagiaire(raf);
			noeudCourant.ajouterNoeud(nomAjout, raf);
		}
	}

	public void affichageInfixe() throws IOException { // doit être public arraylist
		if (raf.length() == 0) {
			System.out.println("arbre vide");
		} else {
			raf.seek(0);
			Noeud noeudCourant = racine.lireStagiaire(raf);
			noeudCourant.affichageInfixeNoeud(raf);
		}
	}

	public void affichageInfixe(List<Stagiaire> lesStagiaires) throws IOException { // doit être public arraylist
		if (raf.length() == 0) {
			System.out.println("arbre vide");
		} else {
			raf.seek(0);
			Noeud noeudCourant = racine.lireStagiaire(raf);
			noeudCourant.affichageInfixeNoeud(raf, lesStagiaires);
		}
	}

	public Noeud rechercher(String stagiaireRecherche, RandomAccessFile raf) throws IOException {
		Noeud noeudCourant = null;
		if (raf.length() == 0) {
			System.out.println("il n'y a rien à afficher");
		} else {
			raf.seek(0);
			noeudCourant = racine.lireStagiaire(raf);
			noeudCourant = noeudCourant.rechercherStagiaire(stagiaireRecherche, raf);
		}
		return noeudCourant;
	}

	public void modifier(Noeud stagiaireModif, Stagiaire stagiaireMaj, RandomAccessFile raf) throws IOException {

		if (raf.length() != 0) {
			racine.supprimerNoeud(stagiaireModif, raf, 0);
			racine.ajouterNoeud(stagiaireMaj, raf);
		}
	}

	public void supprimer(Noeud stagiaireSuppr, RandomAccessFile raf) throws IOException {

		if (raf.length() == 0) {
			System.out.println("Il n'y a rien à supprimer");
		} else {
			raf.seek(0);
			racine = racine.lireStagiaire(raf);
			// System.out.println(racine);
			racine.rechercherStagiaireSuppr(stagiaireSuppr.getCle().getNom(), 0, raf);
		}

	}

}
