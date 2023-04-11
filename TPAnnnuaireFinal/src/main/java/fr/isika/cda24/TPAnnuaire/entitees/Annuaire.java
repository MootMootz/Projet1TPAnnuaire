package fr.isika.cda24.TPAnnuaire.entitees;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Annuaire {

	public static void main(String[] args) {

		List<Stagiaire> laListeDeStagiaire = new ArrayList<>();

		try {
			File myObj = new File("src/main/java/fr/isika/cda24/TPAnnuaire/doc/STAGIAIRES.DON");
			Scanner myReader = new Scanner(myObj); // lecture fichier STAGIARE.DON

			int ligneIteration = 1; // itération
			String nom = null, prenom = null, departement = null, promo = null, annee = null;

			/*
			 * tant qu'il y a une ligne, lis le doc quand il y a une étoile, nouveau cycle
			 */
			while (myReader.hasNextLine()) {

				String data = myReader.nextLine();

				switch (ligneIteration) {
				case 1:
					nom = data;
					ligneIteration++;
					break;
				case 2:
					prenom = data;
					ligneIteration++;
					break;
				case 3:
					departement = data;
					ligneIteration++;
					break;
				case 4:
					promo = data;
					ligneIteration++;
					break;
				case 5:
					annee = data;
					laListeDeStagiaire.add(new Stagiaire(nom, prenom, departement, promo, annee));
					ligneIteration++;
					break;
				case 6:
					data = "*";
					ligneIteration = 1;
					break;
				}
			}
			for (Stagiaire stag : laListeDeStagiaire) {
				System.out.println(stag);
			}

			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Aïe, y'a un problème quelque part !");
			e.printStackTrace();
		}

		// Export .csv
		// ----------------------------------------------------------------------------------------------------------------------

		final String DELIMITEREXPORT = ",";
		final String SEPARATOREXPORT = "\n";
		final String HEADEREXPORT = "Nom,Prenom,Promo,Departement,Annee";

		FileWriter file = null;

		try {
			file = new FileWriter("ListeStagiaireCSV.csv");

			// Ajouter l'en-tête
			file.append(HEADEREXPORT);

			// Ajouter une nouvelle ligne après l'en-tête
			file.append(SEPARATOREXPORT);

			// Itéreration
			Iterator<Stagiaire> it = laListeDeStagiaire.iterator();
			while (it.hasNext()) {
				Stagiaire stagiaireExport = (Stagiaire) it.next();

				file.append(stagiaireExport.getNom());
				file.append(DELIMITEREXPORT);

				file.append(stagiaireExport.getPrenom());
				file.append(DELIMITEREXPORT);

				file.append(stagiaireExport.getPromo());
				file.append(DELIMITEREXPORT);

				file.append(stagiaireExport.getDepartement());
				file.append(DELIMITEREXPORT);

				file.append(stagiaireExport.getAnnee());
				file.append(SEPARATOREXPORT);
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
