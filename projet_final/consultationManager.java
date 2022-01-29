package projet_final;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class consultationManager {

	private String DBPath;
	static ArrayList<Consultation> listConsultation = new ArrayList<Consultation>();
	
	
	public consultationManager(String DBPath) {
		this.DBPath = DBPath;
		remplirListeConsultation();
	};
	
	

	// -------------------------------------------------- 
	// Methode touchant aux objets consultation & actualisant la BDD
	// --------------------------------------------------
	// Création consultation
	public String creerConsultation(String patientID) {
		String IDConsultation = Integer.toString(listConsultation.size());
		Consultation consultation = new Consultation(IDConsultation, patientID);
		
		listConsultation.add(consultation);
		actualiserFichierConsultation();
		return consultation.IDConsultation;
	}
	// Edition consultation
	public void editerConsultation(Consultation consultation, String Rapport) {
		
		consultation.updaterRapport(Rapport);
		actualiserFichierConsultation();
	}
	// Suppression consultation
	public void supprimerConsultation(Consultation consultation) {
		listConsultation.remove(consultation);
		actualiserFichierConsultation();
	}
	
	// -------------------------------------------------- 
	// Chargement des données & écriture dans la BDD
	// --------------------------------------------------
	// Charge les consultations à partir du fichier txt
	public void remplirListeConsultation() {
		String ligne;
		
		try {
			Scanner scanner = new Scanner(new File(this.DBPath));
			while (scanner.hasNextLine()) {
				ligne = scanner.nextLine();
				listConsultation.add(Consultation.convertirEnObject(ligne));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		}
	}
	// Ré-écrit le fichier consultation dans le fichier txt
	public void actualiserFichierConsultation() {
		String ligne;
		
	    try {
	    	PrintWriter fichierPatient = new PrintWriter(DBPath);
	        for(int i = 0; i < listConsultation.size(); i++) {
	        	ligne = Consultation.convertirEnChaine(listConsultation.get(i));
				fichierPatient.println(ligne);
			}
	        fichierPatient.close();
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	}
	
	
	// -------------------------------------------------- 
	// Methode de recherche
	// --------------------------------------------------
	// Filtre les consultations liées à un ID
	public ArrayList<Consultation> filtrerConsultationIDPatient(String IDPatient) {
		ArrayList<Consultation> resultat = new ArrayList<Consultation>();
		
		for(int i = 0; i < listConsultation.size() ;i++) {
			if(listConsultation.get(i).IDPatient.equals(IDPatient)) {
				resultat.add(listConsultation.get(i));
			}
		}
		
		return resultat;
	}
	// Renvoie la liste d'object consultation
	public ArrayList<Consultation> retournerListeConsultation() {
		return listConsultation;
	}
	// Transforme un liste d'object consultation en chaine de charactère 
	// facilement lisible par un human, afin des les identifier.
	public Object[]	consulatationSummery(ArrayList<Consultation> liste) {
		ArrayList<String> summury = new ArrayList<String>();
		
		for(int i = 0; i < liste.size() ;i++) {
			summury.add(" " + i + " - " + "Consultation du " + liste.get(i).date);
		}
		
		if(liste.isEmpty()) {
			summury.add("Pas de consultation à ce jour.");
		}
		
		return summury.toArray();
	}
	

}
