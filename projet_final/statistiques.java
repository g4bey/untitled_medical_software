package projet_final;

import java.io.FileWriter;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class statistiques {
	
	static JFrame fenetre;
	static JPanel container;
	static JTextArea zoneStatisique;
	
	static patientManager gestionairePatient = new patientManager("src/patient_database.txt", "src/pathologies_database.txt");
	static consultationManager gestionaireConsultation = new consultationManager("src/consultation_database.txt");
	static String txt = "";
	
	public static void main(String[] args) {
		
		// Création de la fenêtre
		fenetre = new JFrame();
		fenetre.setTitle("Statistique utilisation");
		fenetre.setSize(400,400);
		container = new JPanel();
		zoneStatisique = new JTextArea();
		zoneStatisique.setDragEnabled(false);
		zoneStatisique.setLineWrap(true);
		zoneStatisique.setEditable(false);
		zoneStatisique.setLineWrap(true);
		zoneStatisique.setWrapStyleWord(true);
		fenetre.getContentPane().add(zoneStatisique);
		zoneStatisique.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		// Préparation des statistiques
		ArrayList<Consultation> listeConsultation = gestionaireConsultation.retournerListeConsultation();
		ArrayList<String> listeIDPatient = new ArrayList<String>();
		ArrayList<Patient> listePatient = gestionairePatient.retournerListePatient();
		ArrayList<Integer> nbConsultationParPatient = new ArrayList<Integer>();
		float patientTotal;
		float patientActif = listePatient.size();
		float patientSupprime = 0;
		float nbConsultation = listeConsultation.size();
		float problemeResolu = 0;
		float agePatientPlusJeune = 200;
		float agePatientPlusVieux = 0;
		float problemeEnAttente = 0;
		float ratioResoluAttente;
		float moyenneConsultationPatient;
		float moyenneAgePatient;
		float ratioResoluConsultation;
		
		for(int i = 0; i < listePatient.size(); i++) {
			listeIDPatient.add(listePatient.get(i).ID);
		}
	
		for(int i = 0; i < listeConsultation.size(); i++) {
			if(!listeIDPatient.contains(listeConsultation.get(i).IDPatient)) {
				patientSupprime++;
			} 
			
			
			if(listeConsultation.get(i).statutOctroit.equals("1")) {
				problemeEnAttente++;
			} else if(listeConsultation.get(i).statutOctroit.equals("2")) {
				problemeResolu++;
			}
		}
		
		float ageTotal = 0;
		for(int i = 0; i < listeIDPatient.size(); i ++) {
			int j = Integer.parseInt(listePatient.get(i).dateNaissance.substring(0, 2));
			int m = Integer.parseInt(listePatient.get(i).dateNaissance.substring(3, 5));
			int y = Integer.parseInt(listePatient.get(i).dateNaissance.substring(6, 10));
			nbConsultationParPatient.add(gestionaireConsultation.filtrerConsultationIDPatient(listeIDPatient.get(i)).size());
			
			LocalDate aujourdhui = LocalDate.now();
			LocalDate anniversaire = LocalDate.of(y, m, j);
			Period period = Period.between(anniversaire, aujourdhui);
			int agePatient = period.getYears();
			
			if(agePatientPlusJeune > agePatient) {
				agePatientPlusJeune = agePatient;
			}
			if(agePatientPlusVieux < agePatient) {
				agePatientPlusVieux = agePatient;
			}
			
			ageTotal = ageTotal + agePatient;
		}
		
		
		
		// Génération des statistiques
		patientTotal = patientActif + patientSupprime;
		ratioResoluAttente = problemeResolu / problemeEnAttente;
		moyenneAgePatient = ageTotal/patientActif;
		moyenneConsultationPatient = nbConsultation/patientTotal;
		ratioResoluConsultation = problemeResolu /nbConsultation;
		
		float a = 0;
		int x = 0;
		if(ratioResoluAttente > 0) {
			for(int j = 0; a < 1; j++) {
				a = ratioResoluAttente * j;
				x = j;
			}
		}
		float b = 0;
		int y = 0;
		if(ratioResoluConsultation > 0) {
			for(int k = 0; b < 1; k++) {
				b = ratioResoluConsultation * k;
				y = k;
			}
		}
		
		
		// Préparation du rapport
		txt = txt + "Nombre de dossier créés: " + String.valueOf(patientTotal);
		txt = txt + "\n";
		txt = txt + "Dont " + String.valueOf(patientActif) + " actif.";
		txt = txt + "\n";
		txt = txt + "Et " + String.valueOf(patientSupprime) + " supprimés.";
		txt = txt + "\n" + "\n" + "\n";
		txt = txt + String.valueOf(nbConsultation) + " consultations créées au total.";
		txt = txt + "\n";
		txt = txt + "Dont " + String.valueOf(problemeResolu) + " problèmes résolus.";
		txt = txt + "\n";
		txt = txt + "Et " + String.valueOf(problemeEnAttente) + " problèmes en attente.";
		txt = txt + "\n" + "\n" + "\n";
		txt = txt + "Age du patient plus jeune " + String.valueOf(agePatientPlusJeune) + " ans.";
		txt = txt + "\n";
		txt = txt + "Age du patient plus agé " + String.valueOf(agePatientPlusVieux) + " ans.";
		txt = txt + "\n";
		txt = txt + "En moyenne un patient a l'age de " + String.valueOf(moyenneAgePatient) + " ans.";
		txt = txt + "\n" + "\n" + "\n";
		txt = txt + String.valueOf(moyenneConsultationPatient) + " consultations par patient en moyenne.";
		txt = txt + "\n";
		txt = txt + String.valueOf(ratioResoluAttente) + " problèmes résolu par consultation attente.";
		txt = txt + "\n";
		txt = txt + "Et " + String.valueOf(ratioResoluAttente) + " problèmes résolu par problème en attente.";
		txt = txt + "\n";
		txt = txt + "C'est " + String.valueOf(x) + " problème en attente par problème résolu.";
		txt = txt + "\n";
		txt = txt + "Et " + String.valueOf(ratioResoluConsultation) + " consultation pour par problème résolu.";
		txt = txt + "\n";
		txt = txt + "C'est à dire " + String.valueOf(y) + " consultation pour résoudre un problème.";
		
		zoneStatisique.setText(txt);
		
		try {
	    	FileWriter fileWriter = new FileWriter("src/statistique.txt"); 
	    	fileWriter.write(txt);
	    	fileWriter.close();
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
		
		
		fenetre.setVisible(true);
	}

}
