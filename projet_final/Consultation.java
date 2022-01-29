package projet_final;

public class Consultation {
	
	String IDConsultation;
	String Rapport = "";
	String IDPatient;
	String date;
	String statutOctroit = "0"; // 0: non demandé. 1 en cours. 2 accepté. 3. refusé
	
	public Consultation(String IDConsultation, String IDPatient) {
		this.IDPatient = IDPatient;
		this.IDConsultation = IDConsultation;
		this.date = java.time.LocalDate.now().toString();
	}
	
	public Consultation(String IDConsultation, String IDPatient, String date, String Rapport, String statutOctroit) {
		this.IDConsultation = IDConsultation;
		this.IDPatient = IDPatient;
		this.date = date;
		this.Rapport = Rapport;
		this.statutOctroit = statutOctroit;
	}
	
	// -------------------------------------------------- 
	// Methode touchant aux champs.
	// --------------------------------------------------
	// Update Rapport. Setter car formatage possible dans le future.
	public void updaterRapport(String rapport) {
		this.Rapport = rapport.replace("\n", "\\n\\");
	}
	

	// -------------------------------------------------- 
	// Methode de conversion pour ajout facile dans le fichier.
	// --------------------------------------------------
	// Convertit un obj consultation en chaine de charactère
	static String convertirEnChaine(Consultation consultation) {
		String chaine = "";
		chaine = consultation.IDConsultation + ";" + consultation.IDPatient + ";" + consultation.date + ";" + consultation.Rapport + ";" + consultation.statutOctroit;
		return chaine;
	}
	// Convertir une chaine en objet. 
	static Consultation convertirEnObject(String ligne) {
		String[] tab;
		tab = ligne.split(";");
		Consultation consultation = new Consultation(tab[0], tab[1], tab[2], tab[3], tab[4]);
		return consultation;
	}
	
}
