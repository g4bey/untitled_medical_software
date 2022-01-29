package projet_final;

import java.util.ArrayList;


//
// Il faut penser la classe Patient comme un type de donnée.
//
public class Patient {
	
	String ID; // on utilise un UUID dont le risque de conflit est infiniment faible, et même nul dans notre cas.
	String Nom;
	String Prenom;
	String dateNaissance;
	String numeroSecu;
	String numeroTelephone;
	ArrayList<String> pathologies = new ArrayList<String>();
	ArrayList<String> antecedents = new ArrayList<String>();
	
	
	
	public Patient(String ID, String Nom, String Prenom, String dateNaissance, String numeroSecu, String numeroTelephone) {
		this.ID = ID;
		this.Nom = Nom;
		this.Prenom = Prenom;
		this.dateNaissance = dateNaissance; 
		this.numeroSecu = numeroSecu;
		this.numeroTelephone = numeroTelephone;
	}
	
	
	// -------------------------------------------------- 
	// Methode de conversion pour ajout & retrait facile dans les fichiers
	// --------------------------------------------------
	// Convertit un obj patient en chaine de charactère pour la base de donnée.
	public static String convertirEnChaine(Patient patient) {
		String string = patient.ID+";"+patient.Nom+";"+patient.Prenom+";"+patient.dateNaissance+";"+patient.numeroSecu+";"+patient.numeroTelephone;
		return string;
	}
	// Convertit une chaine en object patient.
	public static Patient convertirEnObject(String chaine) {
		String tabChaine[];
		tabChaine = chaine.split(";");
		Patient patient = new Patient(
				tabChaine[0],
				tabChaine[1],
				tabChaine[2],
				tabChaine[3],
				tabChaine[4],
				tabChaine[5]
				);
		
		return patient;
	}	
	// Transforme le tableau pathologie en chaine de charactère
	public String chaineTabPathologies() {
		String str = "";
		
		for(int i = 0; i < pathologies.size(); i++) {
			if(!str.isBlank()) {
				str = str + "," + pathologies.get(i);
			} else {
				str = str + pathologies.get(i);	
			}
		}
		
		if(str.isBlank()) {
			str = " ";
		}
		
		return str;
	}
	// Transforme le tableau antécédents en chaine de charactère
	public String chaineTabAntecedent() {
		String str = "";
		
		for(int i = 0; i < antecedents.size(); i++) {
			if(!str.isBlank()) {
				str = str + "," + antecedents.get(i);
			} else {
				str = str + antecedents.get(i);
			}
		}
		
		if(str.isBlank()) {
			str = " ";
		}
		
		return str;
	}
	
}
