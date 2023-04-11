package fr.isika.cda24.TPAnnuaire.program;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import fr.isika.cda24.TPAnnuaire.entitees.*;

public class Lanceur {

	public static void main(String[] args) {

		List<Stagiaire> stagiaires = new ArrayList<>();

		stagiaires.add(new Stagiaire("AITCHEOU", "David", "91", "CDA24", "2023"));
		stagiaires.add(new Stagiaire("AW", "Cheikh", "91", "CDA23", "2022"));
		stagiaires.add(new Stagiaire("TAMADAHO", "Carolina", "75", "CDA24", "2023"));
		stagiaires.add(new Stagiaire("BASTOS", "André", "49", "CDA24", "2023"));
		stagiaires.add(new Stagiaire("RORONOA", "Zoro", "91", "CDA24", "2023"));
		stagiaires.add(new Stagiaire("ACKERMAN", "Livai", "69", "CDA23", "2023"));
		stagiaires.add(new Stagiaire("MONKEY", "Luffy", "91", "CDA24", "2023"));
		stagiaires.add(new Stagiaire("SUKUNA", "Ryomen", "91", "CDA24", "2023"));
		stagiaires.add(new Stagiaire("SON", "GOKU", "91", "CDA24", "2023"));
		stagiaires.add(new Stagiaire("LESTIEUX", "Florian", "91", "CDA24", "2023"));

		try {
			RandomAccessFile test = new RandomAccessFile("src/main/java/fr/isika/cda24/TPAnnuaire/doc/test.bin", "rw");
			for (Stagiaire stagiaire : stagiaires) {
				test.writeChars(stagiaire.getNomLong());
				test.writeChars(stagiaire.getPrenomLong());
				test.writeChars(stagiaire.getDptLong());
				test.writeChars(stagiaire.getPromoLong());
				test.writeChars(stagiaire.getAnneeLong());
			}

			test.seek(0);

			int nbStagiaires = (int) test.length() / Stagiaire.TAILLE_Stagiaires_Octets;

			for (int i = 0; i < nbStagiaires; i++) {

				String nom = "";
				String prenom = "";
				String departement = "";
				String promo = "";
				String annee = "";

				for (int j = 0; j < Stagiaire.TAILLE_NOM_Caracteres; j++) {
					nom += test.readChar();
				}
				System.out.println("nom : " + nom);
				for (int k = 0; k < Stagiaire.TAILLE_Prenom_Caracteres; k++) {
					prenom += test.readChar();
				}
				System.out.println("prenom: " + prenom);
				for (int l = 0; l < Stagiaire.TAILLE_Dpt_Caracteres; l++) {
					departement += test.readChar();
				}
				System.out.println("departement: " + departement);
				for (int m = 0; m < Stagiaire.TAILLE_promo_Caracteres; m++) {
					promo += test.readChar();
				}
				System.out.println("promo: " + promo);
				for (int n = 0; n < Stagiaire.TAILLE_annee_Caracteres; n++) {
					annee += test.readChar();
				}
				System.out.println("annee: " + annee);
				System.out.println("-----------------------------------");

				// pour lire la 3e tarte
				// je deplace le curseur à la fin de la 2e tarte

				// test.seek(2*Stagiaire.TAILLE_Stagiaires_Octets);

				// je peux lire la 3e tarte.
			}
			test.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
