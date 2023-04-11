module fr.isika.cda24.TPAnnnuaire {
    requires javafx.controls;
    requires javafx.base;
	requires javafx.graphics;
	requires java.desktop;
	opens fr.isika.cda24.TPAnnuaire.program to javafx.controls, javafx.base, javafx.graphics;
    exports fr.isika.cda24.TPAnnuaire;
    exports fr.isika.cda24.TPAnnuaire.entitees;
    exports fr.isika.cda24.TPAnnuaire.program;
    exports fr.isika.cda24.TPAnnuaire.doc;
}




