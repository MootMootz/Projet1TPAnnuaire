package fr.isika.cda24.TPAnnuaire.entitees;

import javafx.scene.control.TextField;

public class Stagiaire {

	// attributs
	public final static int TAILLE_NOM_Caracteres = 21;
	public final static int TAILLE_Prenom_Caracteres = 20;
	public final static int TAILLE_Dpt_Caracteres = 2;
	public final static int TAILLE_promo_Caracteres = 11;
	public final static int TAILLE_annee_Caracteres = 4;

	public final static int TAILLE_NOM_OCTETS = TAILLE_NOM_Caracteres * 2;
	public final static int TAILLE_Prenom_OCTETS = TAILLE_Prenom_Caracteres * 2;
	public final static int TAILLE_Dpt_OCTETS = TAILLE_Dpt_Caracteres * 2;
	public final static int TAILLE_promo_OCTETS = TAILLE_promo_Caracteres * 2;
	public final static int TAILLE_annee_OCTETS = TAILLE_annee_Caracteres * 2;

	public final static int TAILLE_Stagiaires_Octets = (TAILLE_NOM_Caracteres + TAILLE_Prenom_Caracteres
			+ TAILLE_Dpt_Caracteres + TAILLE_promo_Caracteres + TAILLE_annee_Caracteres) * 2;

	public String nom;
	public String prenom;
	public String departement;
	public String promo;
	public String annee;

	public String getNomLong() {
		String NomLong = this.nom;
		for (int i = this.nom.length(); i < TAILLE_NOM_Caracteres; i++) {
			NomLong += " ";
		}
		return NomLong;
	}

	public String getPrenomLong() {
		String PrenomLong = this.prenom;
		for (int i = this.prenom.length(); i < TAILLE_Prenom_Caracteres; i++) {
			PrenomLong += " ";
		}
		return PrenomLong;
	}

	public String getPromoLong() {
		String PromoLong = this.promo;
		for (int i = this.promo.length(); i < TAILLE_promo_Caracteres; i++) {
			PromoLong += " ";
		}
		return PromoLong;
	}

	public String getDptLong() {
		String DptLong = this.departement;
		for (int i = this.departement.length(); i < TAILLE_Dpt_Caracteres; i++) {
			DptLong += " ";
		}
		return DptLong;
	}

	public String getAnneeLong() {
		String anneeLong = this.annee;
		for (int i = this.annee.length(); i < TAILLE_annee_Caracteres; i++) {
			anneeLong += " ";
		}
		return anneeLong;
	}

	// constructeur surchargé
	public Stagiaire(String nom, String prenom, String departement, String promo, String annee) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.promo = promo;
		this.annee = annee;
	}

	// getter et setter

	public Stagiaire() {
		super();
	}

	@Override
	public String toString() {
		return "Stagiaire [nom=" + nom + ", prenom=" + prenom + ", departement=" + departement + ", promo=" + promo
				+ ", annee=" + annee + "]";
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public boolean verifTailleChamp(TextField tf1, TextField tf2, TextField tf3, TextField tf4, TextField tf5) {

		boolean res = true;

		if (tf1.getText().length() > 21) {

			tf1.setText("veuillez réécrire");
			res = false;

		}
		if (tf2.getText().length() > 20) {
			tf2.setText("veuillez réécrire");
			res = false;

		}
		if (tf3.getText().length() > 2) {
			tf3.setText("veuillez réécrire");
			res = false;

		}
		if (tf4.getText().length() > 11) {

			tf4.setText("veuillez réécrire");

			res = false;
		}
		if (tf5.getText().length() > 4) {

			tf5.setText("veuillez réécrire");
			res = false;
		}
		return res;

		// return false;
	}
}
