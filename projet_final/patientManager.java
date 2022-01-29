package projet_final;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;



//
// Cette classe permet de gérer la liste d'object patient
// qu'il faudra penser comme un type de donnée.
//
public class patientManager  {
	
	private String patientDB;
	private String pathologieDB;
	
	static ArrayList<Patient> listePatient = new ArrayList<Patient>();
	
	public patientManager(String PatientDBFilePath, String pathologieDB) {
		
		this.patientDB = PatientDBFilePath;
		this.pathologieDB = pathologieDB;
		remplirListePatient();
	}
	
	
	// -------------------------------------------------- 
	// Fonctions touchant à la liste de patients.
	// --------------------------------------------------
	// Transfert les données .txt en object patient puis charge listePatient.
	public void remplirListePatient() {
		String ligne;
		
		try {
			Scanner scanner = new Scanner(new File(this.patientDB));
			while (scanner.hasNextLine()) {
				ligne = scanner.nextLine();
				listePatient.add(Patient.convertirEnObject(ligne));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		}
	}
	// Ajoute un object patient à liste patient, puis actualise la base de donnée.
	public String ajouterPatient(String Nom, String Prenom, String dateNaissance, String numeroSecu, String numeroTelephone) {
		UUID IDunique = UUID.randomUUID(); //Clef primaire unique... risque de collison virutellement nul.
		
		Patient nouveauPatient = new Patient(IDunique.toString(), Nom, Prenom, dateNaissance, numeroSecu, numeroTelephone);
		listePatient.add(nouveauPatient);
		
		actualiserFichierPatient();
		ajouterFichePathologie(nouveauPatient.ID);
		return nouveauPatient.ID;
	}
	// Suprime un object patient de la liste patient, puis actualise la base de donnée.
	public void supprimerPatient(Patient patient) {
		
		listePatient.remove(patient);
		actualiserFichierPatient();
	}
	// Cherche un object patient, met à jour les champs puis actualiser le fichier.
	public void editerPatient(Patient patient, String Nom, String Prenom, String dateNaissance, String numeroSecu, String numeroTelephone) {
		
		int index = listePatient.indexOf(patient);
		listePatient.get(index).Nom = Nom;
		listePatient.get(index).Prenom = Prenom;
		listePatient.get(index).dateNaissance = dateNaissance;
		listePatient.get(index).numeroSecu = numeroSecu;
		listePatient.get(index).numeroTelephone = numeroTelephone;
		
		actualiserFichierPatient();
	}
	
	
	// -------------------------------------------------- 
	// Retourne liste de patient sous differente forme.
	// --------------------------------------------------
	// Retourne la liste une liste d'object patient.
	public ArrayList<Patient> retournerListePatient() { 
		return listePatient;
	}
	// Transforme listePatient en Object[][] exploitable par JTable
	public Object[][] retournerArrayString() { 
		Object[][] result = new Object[listePatient.size()][];
		
		for (int i = 0; i < listePatient.size(); i++){
			result[i] = new Object[6];
			result[i][0] = listePatient.get(i).ID;
			result[i][1] = listePatient.get(i).Nom;
			result[i][2] = listePatient.get(i).Prenom;
			result[i][3] = listePatient.get(i).dateNaissance;
		    result[i][4] = listePatient.get(i).numeroSecu;
		    result[i][5] = listePatient.get(i).numeroTelephone;
		}
		
		return result;
	}
	// Transforme un ArrayList<Patient> quelquonque Object[][] exploitable par JTable
	public Object[][] retournerArrayString(ArrayList<Patient> patients) { 
		Object[][] result = new Object[patients.size()][];
		
		for (int i = 0; i < patients.size(); i++){
			result[i] = new Object[6];
			result[i][0] = patients.get(i).ID;
			result[i][1] = patients.get(i).Nom;
			result[i][2] = patients.get(i).Prenom;
			result[i][3] = patients.get(i).dateNaissance;
		    result[i][4] = patients.get(i).numeroSecu;
		    result[i][5] = patients.get(i).numeroTelephone;
		}
		
		return result;
	}

	
	// -------------------------------------------------- 
	// Fonctions touchant à la base de données.
	// --------------------------------------------------
	// Réécrit tout le contenu de listePatient dans le fichier base de donnée.
	public void actualiserFichierPatient() {
		String ligne;
		
	    try {
	    	PrintWriter fichierPatient = new PrintWriter(patientDB);
	        for(int i = 0; i < listePatient.size(); i++) {
	        	ligne = Patient.convertirEnChaine(listePatient.get(i));
				fichierPatient.println(ligne);
			}
	        fichierPatient.close();
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	}
	// Ajouter Entrée Fichier Pathologie
	public void ajouterFichePathologie(String ID) {
		String ligne;
		
	    try {
	    	FileWriter fileWriter = new FileWriter(this.pathologieDB, true); // ouvre le fichier en mode append 
	    	PrintWriter fichierPathologies = new PrintWriter(fileWriter); //Pr
	        ligne = ID +"; ; ";
	        fichierPathologies.println(ligne);
	        fichierPathologies.close();
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	}
	// Edition fichier pathologie
	public void editerFichierPathologie(String ID) {
		String ligne;
		ArrayList<String> sauvgardeFichier = new ArrayList<String>();
		
		Patient patient = this.retournerPatientID(ID);
		try {
			Scanner scanner = new Scanner(new File(this.pathologieDB));
			while (scanner.hasNextLine()) {
				ligne = scanner.nextLine();
				sauvgardeFichier.add(ligne);
			}
			scanner.close();
			
			try {
		    	PrintWriter fichierPathologies = new PrintWriter(this.pathologieDB);
		    	for(int i = 0; i < sauvgardeFichier.size(); i++) {
		    		if(ID.equals(sauvgardeFichier.get(i).substring(0, 36))) {
		    			fichierPathologies.println(patient.ID + ";" + patient.chaineTabPathologies() + ";" + patient.chaineTabAntecedent());
		    		} else {
				        fichierPathologies.println(sauvgardeFichier.get(i));
		    		}
		    	}
		    	fichierPathologies.close();
		      } catch (Exception e) {
		        e.printStackTrace();
		      }
			
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		}
	}
	// Charge le tableau de pathologie & antécédents d'un patient à l'id ID.
	public Patient chargerPathologieAntecedent(String ID) {
		String ligne;
		String[] element; 
		Patient patient = this.retournerPatientID(ID);
		
		ArrayList<String> listePathologies = new ArrayList<String>();
		ArrayList<String> listeAntecedents = new ArrayList<String>();
		
		try {
			Scanner scanner = new Scanner(new File(this.pathologieDB));
			while (scanner.hasNextLine()) {
				ligne = scanner.nextLine();

				element = ligne.split(";");
				if(element[0].equals(ID)) {
					String[] pathologies = element[1].split(",");
					String[] antecedents = element[2].split(",");
		
					for(int i = 0; i < pathologies.length; i++) {
						listePathologies.add(pathologies[i]);
					}
					for(int i = 0; i < antecedents.length; i++) {
						listeAntecedents.add(antecedents[i]);
					}
					patient.pathologies = listePathologies;
					patient.antecedents = listeAntecedents;
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		}
		return patient;
	}
	
	
	// -------------------------------------------------- 
	// Fonctions de recherche de patients dans la liste.
	// --------------------------------------------------
	// Retourne un object patient correspondant à un ID.
	public Patient retournerPatientID(String ID) {
		
		for(int i = 0; i < listePatient.size(); i++) {
			if(listePatient.get(i).ID.equals(ID)) {
				return listePatient.get(i);
			} 
		}
		return null;
	}
	// Retourne une liste d'object patient correspondant à un nom dans un array quelquonque.
	public ArrayList<Patient> rechercherPatientNOM(String Nom, ArrayList<Patient> FilteredArray) {
		ArrayList<Patient> idCorrespondant = new ArrayList<Patient>();
		
		for(int i = 0; i < FilteredArray.size(); i++) {
			String champ = FilteredArray.get(i).Nom.toLowerCase();
			if(champ.startsWith(Nom.toLowerCase())) {
				idCorrespondant.add(FilteredArray.get(i));
			} 
		}
		return idCorrespondant;
	}
	// Retourne une liste d'object patient correspondant à un prenom dans un array quelquonque.
	public ArrayList<Patient> rechercherPatientPRENOM(String Prenom, ArrayList<Patient> FilteredArray) {
		ArrayList<Patient> idCorrespondant = new ArrayList<Patient>();
		
		for(int i = 0; i < FilteredArray.size(); i++) {
			String champ = FilteredArray.get(i).Prenom.toLowerCase();
			if(champ.startsWith(Prenom.toLowerCase())) {
				idCorrespondant.add(FilteredArray.get(i));
			} 
		}
		return idCorrespondant;
	}	
	// Retourne une liste d'object patient correspondant à une date de naissance dans un array quelquonque.
	public ArrayList<Patient> rechercherPatientDATENAISSANCE(String dateNaissance, ArrayList<Patient> FilteredArray) {
		ArrayList<Patient> idCorrespondant = new ArrayList<Patient>();
		
		for(int i = 0; i < FilteredArray.size(); i++) {
			String champ = FilteredArray.get(i).dateNaissance.toLowerCase();
			if(champ.startsWith(dateNaissance)) {
				idCorrespondant.add(FilteredArray.get(i));
			} 
		}
		return idCorrespondant;
	}
	// Retourne une liste d'object patient correspondant à un numéro de sécurité sociale dans un array quelquonque.
	public ArrayList<Patient> rechercherPatientSECU(String numeroSecu, ArrayList<Patient> FilteredArray) {
		ArrayList<Patient> idCorrespondant = new ArrayList<Patient>();
		
		for(int i = 0; i < FilteredArray.size(); i++) {
			String champ = FilteredArray.get(i).numeroSecu.toLowerCase();
			if(champ.startsWith(numeroSecu)) {
				idCorrespondant.add(FilteredArray.get(i));
			} 
		}
		return idCorrespondant;
	}
	// Retourne une liste d'object patient correspondant à un numéro de téléphone dans un array quelquonque.
	public ArrayList<Patient> rechercherPatientTELEPHONE(String numeroTelephone, ArrayList<Patient> FilteredArray) {
		ArrayList<Patient> idCorrespondant = new ArrayList<Patient>();
		
		for(int i = 0; i < FilteredArray.size(); i++) {
			String champ = FilteredArray.get(i).numeroTelephone.toLowerCase();
			if(champ.startsWith(numeroTelephone)) {
				idCorrespondant.add(FilteredArray.get(i));
			} 
		}
		return idCorrespondant;
	}
}
