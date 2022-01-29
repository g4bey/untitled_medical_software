package projet_final;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class interface_medecin {
	
	static JFrame fenetre, ZoneEdition;
	static JPanel panelDroite, panelGauche, formulaire, labelFormulaire, champFormulaire, zoneBoutonFormulaire, zoneRecherche, 
	zoneConsultation, panelconsultationBouton, panelConsultationNom, panelConsultationPrenom, panelConsultationNaissance, 
	panelConsultationSecu, panelConsultationTelephone, previewFicheMedicale, zoneGestionConsultation, containerBasDroit,
	zoneAjouterConsultation, zoneEditionSuppression, zoneFichePathologie, zoneFicheAntecedents, container, selectionPathologie,
	selectionAntecedents, zoneBoutonEdition;
	static JLabel labelChampNom, labelChampPrenom, labelChampNaissance, labelChampSecu, labelChampTelephone, labelConsultationNom, 
	labelConsultationPrenom, labelConsultationNaissance, labelConsultationSecu, labelConsultationTelephone, placeHolderConsultationNom, 
	placeHolderConsultationPrenom, placeHolderConsultationNaissance, placeHolderConsultationSecu, placeHolderConsultationTelephone,
	labelPathologie, labelAntecedents;
	static JTextField champNom, champPrenom, champNaissance, champSecu, champTelephone, champNomEdition;
	static TitledBorder titreFormulaire, titreConsultation, titreTableauRecherche, titrePreview, titreGestionConsultation,
	titreRapport, titreListePathologie, titreListeAntecedent;
	static Border bordureInterieur, bordureBouton, bordureZoneFiche, marginLabel;
	static JButton boutonAnnuler, boutonRechercher, boutonExportFiche, ajouterConsultation, supprimerConsultation, editerConsultation, 
	sauvegarderConsulation, reinitialiserConsultation, octroyerProthese, exporterConsultation; 
	static JTable table;
    static JScrollPane tableScrollable, ScrollerPathologie, ScrollerAntedecent, ScrollerRapport, ScrollerEditionPathologie,
    ScrollerEditionAntecedents;
    static TableColumnModel columnModel;
    static TableModel model;
    static JComboBox<String> listeDeroulante;
    static DefaultComboBoxModel modelConsultationAnterieur;
    static String[] Colonnes = {"ID", "Nom", "Prenom", "Date de Naissance", "Numéro de sécurité sociale", "Numéro Telephone"};
    static JList listePathologie, listeAntecedent;
    static DefaultListModel modelPathologie, modelAntecedent;
    static JTextArea rapportConsultation;
    static JFileChooser choixFichier;
    
	static patientManager gestionairePatient = new patientManager("src/patient_database.txt", "src/pathologies_database.txt");
	static consultationManager gestionaireConsultation = new consultationManager("src/consultation_database.txt");
	// Tableau de patient globale.
	static ArrayList<Patient> tableauPatients = gestionairePatient.retournerListePatient();
	// Tableau de patient filtre.
	static Object[][] tabRecherche;
	// Dernier ID sélectionné
	static String selectedID  = null;
	static ArrayList<Consultation> consultationPatientID = new ArrayList<Consultation>(); // liste d'object consultation consultation à l'ID choisi.
	static Object[] consultationAnterieur = {}; // Summury des consultation antérieurs à l'ID choisi.
    
    // Liste des pathologies possible
    static String[] choixPathologie = {"Pathologie A", "Pathologie B", "Pathologie C", "Pathologie D", "Pathologie E", "Pathologie F", "Pathologie G", "Pathologie H", "Pathologie I", "Pathologie J", "Pathologie K",
    		"Pathologie L", "Pathologie M", "Pathologie N", "Pathologie O", "Pathologie P", "Pathologie Q", "Pathologie R", "Pathologie S", "Pathologie T", "Pathologie U",
    		"Pathologie V", "Pathologie W", "Pathologie X", "Pathologie Y", "Pathologie Z", "Pathologie A1", "Pathologie B1", "Pathologie C1", "Pathologie D1", "Pathologie E1", "Pathologie F1", "Pathologie G1", "Pathologie H1", "Pathologie I1", "Pathologie J1", "Pathologie K1",
    		"Pathologie L1", "Pathologie M1", "Pathologie N1", "Pathologie O1", "Pathologie P1", "Pathologie Q1", "Pathologie R1"};
    // liste des JCheckBox associés aux pathologies possible
    static ArrayList<JCheckBox> checkboxPathologie =  new ArrayList<JCheckBox>();
    
    static String[] choixAntecedents = {"Antecedent A", "Antecedent B", "Antecedent C", "Antecedent D", "Antecedent E", "Antecedent F", "Antecedent G", "Antecedent H", "Antecedent I", "Antecedent J", "Antecedent K",
    		"Antecedent L", "Antecedent M", "Antecedent N", "Antecedent O", "Antecedent P", "Antecedent Q", "Antecedent R", "Antecedent S", "Antecedent T", "Antecedent U",
    		"Antecedent V", "Antecedent W", "Antecedent X", "Antecedent Y", "Antecedent Z", "Antecedent A1", "Antecedent B1", "Antecedent C1", "Antecedent D1", "Antecedent E1", "Antecedent F1", "Antecedent G1", "Antecedent H1", "Antecedent I1", "Antecedent J1", "Antecedent K1",
    		"Pathologie L1", "Pathologie M1", "Pathologie N1", "Pathologie O1", "Pathologie P1", "Pathologie Q1", "Pathologie R1"};
    // liste des JCheckBox associés aux pathologies possible
    static ArrayList<JCheckBox> checkboxAntecedents=  new ArrayList<JCheckBox>();
    
    
	
	public static void clearFormulaire() {
		champNom.setText("");
		champPrenom.setText("");
		champNaissance.setText("");
		champSecu.setText("");
		champTelephone.setText("");
	}
	// Réinitialise tabRecherche
	// Et ré-affiche la table de patient complète
	public static void afficherTableComplete() {
		tabRecherche = gestionairePatient.retournerArrayString();
		model = new DefaultTableModel(tabRecherche, Colonnes);
		table.setModel(model);
		setColUUIDWidth();
	}
	// Réaplique le modèle à partir de tabRecherche
	// dès lors filtré.
	public static void filtrerTable() {
		model = new DefaultTableModel(tabRecherche, Colonnes);
		table.setModel(model);
		setColUUIDWidth();
	}
	
	public static void setColUUIDWidth() {
		columnModel.getColumn(0).setPreferredWidth(190);
	}
	// Met à jour la liste déroulante de consultation en fonction de l'ID patient.
	public static void updaterListeConsultation() {
		consultationPatientID = gestionaireConsultation.filtrerConsultationIDPatient(selectedID); // On garde tous les objets consultation
		consultationAnterieur = gestionaireConsultation.consulatationSummery(consultationPatientID); // On affiche juste la date de consultation car c'est plus simple à lire.
		
		// Reconstruction du modèle pour le réapliquer.
	    modelConsultationAnterieur = new DefaultComboBoxModel(consultationAnterieur);
	    listeDeroulante.setModel(modelConsultationAnterieur);
	    
	    // On se positionne sur la dernier élement.
	    modelConsultationAnterieur.setSelectedItem(modelConsultationAnterieur.getElementAt(consultationAnterieur.length-1));
	}
	
	// Uncheck les checkbox.
	public static void uncheckCheckbox() {
		for(int i = 0;  i < checkboxPathologie.size(); i++) {
			checkboxPathologie.get(i).setSelected(false);
		}
		for(int i = 0;  i < checkboxAntecedents.size(); i++) {
			checkboxAntecedents.get(i).setSelected(false);
		}
	}
	// Charge les checkbox pour un patient donné.
	public static void chargerCheckbox(Patient patient) {
		for(int i = 0; i < patient.pathologies.size(); i++) {
			for(int j = 0; j < choixPathologie.length; j++) {
    			if(patient.pathologies.get(i).equals(choixPathologie[j])) {
    				checkboxPathologie.get(j).setSelected(true);
    			}
			}
		}
		for(int i = 0; i < patient.antecedents.size(); i++) {
			for(int j = 0; j < choixPathologie.length; j++) {
    			if(patient.antecedents.get(i).equals(choixAntecedents[j])) {
    				checkboxAntecedents.get(j).setSelected(true);
    			}
			}
		}	
	}
	// Obtenir object consultation sélectionné
	public static Consultation obtenirObjectConsultationSelection() {
    	listeDeroulante.getSelectedIndex();
    	int indiceConsultation = listeDeroulante.getSelectedIndex();
    	Consultation consultation = consultationPatientID.get(indiceConsultation);
    	
    	return consultation;
	}
	// Met à jour les listes de pathologies & antécédents au click sur un patient.
	public static void updaterjListPathologieAntecedent(String ID) {
	    Patient patient = gestionairePatient.chargerPathologieAntecedent(ID);
	    modelPathologie.removeAllElements();
	    modelAntecedent.removeAllElements();
	    for(int i = 0; i < patient.pathologies.size(); i++) {
	    	modelPathologie.addElement(patient.pathologies.get(i));
	    }
	    for(int i = 0; i < patient.antecedents.size(); i++) {
	    	modelAntecedent.addElement(patient.antecedents.get(i));
	    }
	    listePathologie.setModel(modelPathologie);
	    listeAntecedent.setModel(modelAntecedent);
	}
	// Génération d'un rapport de consultation / fiche médicale.
		public static String exporterConsultation(Patient patient, Consultation[] consultation) {
	    	String txt = "";
	    	String separator = "\n" + "-------------------------------------------------------------------------------" + "\n";
	    	
	    	// Génération du rapport
	    	txt = txt + "INFORMATION SUR LE PATIENT"+ "\n";
	    	txt = txt + "  +  " + "Nom: " + patient.Nom + "\n";
	    	txt = txt + "  +  " + "Prenom: " + patient.Prenom + "\n";
	    	txt = txt + "  +  " + "Date de naisse: " + patient.dateNaissance + "\n";
	    	txt = txt + "  +  " + "Numéro de sécurité sociale: " + patient.numeroSecu;
	    	txt = txt + separator;
	    	if(!patient.chaineTabPathologies().isBlank()) {
	    		txt = txt + "  +  " + patient.chaineTabPathologies();
	    	} else {
	           	txt = txt + "  +  " + "Pas de pathologie";
	    	}
	    	txt = txt + "\n";
	     	if(!patient.chaineTabAntecedent().isBlank()) {
	    		txt = txt + "  +  " + patient.chaineTabAntecedent();
	    	} else {
	           	txt = txt + "  +  " + "Pas d'antécédent";
	    	}
	     	
	     	txt = txt + separator + "\n" + "\n";
	     	for(int i = 0; i < consultation.length; i++) {
	        	txt = txt + "Rapport du " + consultation[i].date;
	        	txt = txt + separator;
	        	txt = txt + consultation[i].Rapport.replace("\\n\\", "\n");
	        	txt = txt + "\n" + "\n" + "\n";
	     	}
	    	
	    	return txt;
		}
		// sauvegarde fichier
		public static void sauvegarderExport(String txt, JFrame fenetre) {
		    choixFichier = new JFileChooser();
		    choixFichier.setDialogTitle("Où voulez-vous sauvegarder les données de consultation?");   
		     
		    int selectionFichier = choixFichier.showSaveDialog(fenetre);
		     
		    if (selectionFichier == JFileChooser.APPROVE_OPTION) {
		        File fichierChoisi = choixFichier.getSelectedFile();
		        
		        try {
			    	FileWriter fileWriter = new FileWriter(fichierChoisi.getAbsolutePath() + ".txt"); 
			    	fileWriter.write(txt);
			    	fileWriter.close();
			      } catch (Exception e) {
			        e.printStackTrace();
			      }
		    }
		}
		
	public static void main(String[] args) {
		
		// Création de la fenêtre principale
		fenetre = new JFrame("Interface Agent Administration");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setSize(1500, 800);
		fenetre.setLayout(new BorderLayout());
		fenetre.setLocationRelativeTo(null);
		fenetre.setVisible(true);
		
	    // Création fenêtre édition
	    ZoneEdition = new JFrame();
	    ZoneEdition.setTitle("Edition consultation");
	    ZoneEdition.setSize(1280, 720);
	    ZoneEdition.setLocationRelativeTo(null);

		// Création des pannels
		panelDroite = new JPanel(new GridLayout(2,0));
		panelGauche = new JPanel(new GridLayout(2,0));
		formulaire = new JPanel(new BorderLayout());
		labelFormulaire = new JPanel(new GridLayout(5,1,0,10));
		champFormulaire = new JPanel(new GridLayout(5,1,0,10));
		zoneBoutonFormulaire = new JPanel(new GridLayout(1,0, 10, 0));
	    zoneRecherche = new JPanel(new GridLayout(0,1));
	    zoneConsultation = new JPanel(new GridLayout(6, 1));
	    panelConsultationNom = new JPanel(new GridLayout(0,2));
	    panelConsultationPrenom = new JPanel(new GridLayout(0, 2));
	    panelConsultationNaissance = new JPanel(new GridLayout(0, 2));
	    panelConsultationSecu = new JPanel(new GridLayout(0, 2));
	    panelConsultationTelephone = new JPanel(new GridLayout(0, 2));
	    panelconsultationBouton = new JPanel(new GridLayout(1,0, 10, 0));
	    containerBasDroit = new JPanel(new GridLayout(1, 0));
	    previewFicheMedicale = new JPanel(new GridLayout(0, 1));
	    zoneGestionConsultation = new JPanel(new GridLayout(0, 1));
	    zoneAjouterConsultation = new JPanel(new GridLayout(1,0));
	    zoneEditionSuppression = new JPanel(new GridLayout(1,0, 10, 0));
	    zoneFichePathologie = new JPanel(new BorderLayout());
	    zoneFicheAntecedents = new JPanel(new BorderLayout());
	    container = new JPanel(new GridLayout(3,0));
	    selectionPathologie = new JPanel(new GridLayout(0, 9));
	    selectionAntecedents = new JPanel(new GridLayout(0, 9));
	    zoneBoutonEdition = new JPanel(new GridLayout(0, 4, 10, 0));
	    
	    //Création Jtext Area
	    rapportConsultation = new JTextArea(0,0);
	    rapportConsultation.setLineWrap(true);
	    rapportConsultation.setWrapStyleWord(true);
	    ScrollerRapport = new JScrollPane(rapportConsultation, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    
		// Création des champs. 
		champNom = new JTextField();
		champPrenom = new JTextField();
		champNaissance = new JTextField();
		champSecu = new JTextField();
		champTelephone = new JTextField();
		
	    // Création des labels 
		labelChampNom = new JLabel("Nom");
		labelChampPrenom = new JLabel("Prénom");
		labelChampNaissance = new JLabel("Date de naissance");
		labelChampSecu = new JLabel("Numéro de sécurité sociale");
		labelChampTelephone = new JLabel("Numéro de téléphone");
	    labelConsultationNom = new JLabel("Nom");
	    labelConsultationPrenom = new JLabel("Prenom");
	    labelConsultationNaissance = new JLabel("Date de naissance");
	    labelConsultationSecu = new JLabel("Numéro de Sécurité Sociale");
	    labelConsultationTelephone = new JLabel("Numéro de téléphone");
	    placeHolderConsultationNom = new JLabel("//");
	    placeHolderConsultationPrenom = new JLabel("//");
	    placeHolderConsultationNaissance = new JLabel("//");
	    placeHolderConsultationSecu = new JLabel("//");
	    placeHolderConsultationTelephone = new JLabel("//");
	    labelPathologie = new JLabel("Pathologies:");
	    labelAntecedents = new JLabel("Antécédents:");
	    
		// Création des boutons
	    boutonAnnuler = new JButton("Annuler");
	    boutonRechercher= new JButton("Rechercher");
	    boutonExportFiche = new JButton("Exporter fiche medicale");
	    ajouterConsultation = new JButton("Créér nouvelle consultation");
	    supprimerConsultation = new JButton("Supprimer consultation");
	    editerConsultation = new JButton("Editer consultation");
	    sauvegarderConsulation = new JButton("Sauvegarder");
	    reinitialiserConsultation = new JButton("Réinitialiser");
	    octroyerProthese = new JButton("Octroyer Prothese");
	    exporterConsultation = new JButton("Exporter détail consultation");
	    
	    
	    // Création de la table
	    tabRecherche = gestionairePatient.retournerArrayString();
	    model = new DefaultTableModel(tabRecherche, Colonnes);
	    table = new JTable(model);
	    tableScrollable = new JScrollPane(table);
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    table.setDefaultEditor(Object.class, null);
	    
	    // Style Table 
	    columnModel = table.getColumnModel();
	    table.setRowHeight(55);
	    setColUUIDWidth();
	    
	    // Initialisation déroulante consultation
	    listeDeroulante = new JComboBox();
	    modelConsultationAnterieur = new DefaultComboBoxModel(consultationAnterieur);
	    listeDeroulante.setModel(modelConsultationAnterieur);
	    listeDeroulante.setMaximumRowCount(10);
	    
	    // Création des JList Pathologies & Antécédents 
	    modelPathologie = new DefaultListModel();
	    modelAntecedent = new DefaultListModel();
	    listePathologie = new JList(modelPathologie);
	    listeAntecedent = new JList(modelAntecedent);
	    listePathologie.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    listePathologie.setLayoutOrientation(JList.VERTICAL);
	    listePathologie.setVisibleRowCount(-1);
	    listeAntecedent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    listeAntecedent.setLayoutOrientation(JList.VERTICAL);
	    listeAntecedent.setVisibleRowCount(-1);
	    ScrollerPathologie = new JScrollPane(listePathologie);
	    ScrollerAntedecent = new JScrollPane(listeAntecedent);
	    ScrollerEditionPathologie = new JScrollPane(selectionPathologie, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    ScrollerEditionAntecedents = new JScrollPane(selectionAntecedents, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    
	    // Ajout des bordures 
	    bordureInterieur = BorderFactory.createEmptyBorder(15, 15, 15, 15);
	    bordureBouton = BorderFactory.createEmptyBorder(20,0,5,0);
	    bordureZoneFiche = BorderFactory.createEmptyBorder(0,10,15,10);
	    marginLabel = BorderFactory.createEmptyBorder(0,0,10,0);
	    titreTableauRecherche = BorderFactory.createTitledBorder("Cliquez pour selectionner un patient"); 
	    titreConsultation = BorderFactory.createTitledBorder("Information sur le patient");
	    titreFormulaire = BorderFactory.createTitledBorder("Recherche Patient");
	    titrePreview = BorderFactory.createTitledBorder("Pathologies & Antécédents");
	    titreRapport = BorderFactory.createTitledBorder("Rapport de la consultation");
	    titreGestionConsultation = BorderFactory.createTitledBorder("Gestion des consultations");
	    titreListePathologie = BorderFactory.createTitledBorder("Pathologies");
	    titreListeAntecedent = BorderFactory.createTitledBorder("Antécédents");
	    formulaire.setBorder(BorderFactory.createCompoundBorder(titreFormulaire, bordureInterieur));
		zoneRecherche.setBorder(BorderFactory.createCompoundBorder(titreTableauRecherche, bordureInterieur));
		zoneConsultation.setBorder(BorderFactory.createCompoundBorder(titreConsultation, bordureInterieur));
	    // zoneRecherche.setBorder(bordureInterieur);
	    panelconsultationBouton.setBorder(bordureBouton);
	    zoneBoutonFormulaire.setBorder(bordureBouton);
	    previewFicheMedicale.setBorder(BorderFactory.createCompoundBorder(titrePreview, bordureInterieur));
	    zoneGestionConsultation.setBorder(BorderFactory.createCompoundBorder(titreGestionConsultation, BorderFactory.createEmptyBorder(55,20,90,20)));
	    listeDeroulante.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
	    zoneFicheAntecedents.setBorder(bordureZoneFiche);
	    zoneFichePathologie.setBorder(bordureZoneFiche);
	    labelAntecedents.setBorder(marginLabel);
	    labelPathologie.setBorder(marginLabel);
	    container.setBorder(bordureInterieur);
	    ScrollerRapport.setBorder(BorderFactory.createCompoundBorder(titreRapport, BorderFactory.createEmptyBorder(5,5,10,5)));
	    ScrollerEditionPathologie.setBorder(BorderFactory.createCompoundBorder(titreListePathologie, BorderFactory.createEmptyBorder(5,5,10,5)));
	    ScrollerEditionAntecedents.setBorder(BorderFactory.createCompoundBorder(titreListeAntecedent, BorderFactory.createEmptyBorder(5,5,10,5)));
	    zoneBoutonEdition.setBorder(bordureInterieur);
	    zoneEditionSuppression.setBorder(bordureBouton);
	    
	    // Style des pannels
	    formulaire.setPreferredSize(new Dimension(480,300));
	    
	    // Style des champs
		champNom.setColumns(20);
		champPrenom.setColumns(20);
		champNom.setColumns(20);
		champSecu.setColumns(20);
		champTelephone.setColumns(20);	
		
		// Ajout des pannels dans la fenêtre
		fenetre.getContentPane().add(panelDroite); 
		fenetre.getContentPane().add(BorderLayout.WEST, panelGauche);
		
		// Ajoute dans panel Gauche
		panelGauche.add(formulaire);
		formulaire.add(BorderLayout.CENTER, labelFormulaire);
		formulaire.add(BorderLayout.LINE_END, champFormulaire);
		formulaire.add(BorderLayout.PAGE_END, zoneBoutonFormulaire);
		labelFormulaire.add(labelChampNom);
		labelFormulaire.add(labelChampPrenom);
		labelFormulaire.add(labelChampNaissance);
		labelFormulaire.add(labelChampSecu);
		labelFormulaire.add(labelChampTelephone);
		champFormulaire.add(champNom);
		champFormulaire.add(champPrenom);
		champFormulaire.add(champNaissance);
		champFormulaire.add(champSecu);
		champFormulaire.add(champTelephone);
	    zoneBoutonFormulaire.add(boutonAnnuler);
	    zoneBoutonFormulaire.add(boutonRechercher);
	    
	    panelGauche.add(zoneConsultation);
	    panelConsultationNom.add(labelConsultationNom);
	    panelConsultationNom.add(placeHolderConsultationNom);
	    panelConsultationPrenom.add(labelConsultationPrenom);
	    panelConsultationPrenom.add(placeHolderConsultationPrenom);
	    panelConsultationNaissance.add(labelConsultationNaissance);
	    panelConsultationNaissance.add(placeHolderConsultationNaissance);
	    panelConsultationSecu.add(labelConsultationSecu);
	    panelConsultationSecu.add(placeHolderConsultationSecu);
	    panelConsultationTelephone.add(labelConsultationTelephone);
	    panelConsultationTelephone.add(placeHolderConsultationTelephone);
	    panelconsultationBouton.add(boutonExportFiche);
	    zoneConsultation.add(panelConsultationNom);
	    zoneConsultation.add(panelConsultationPrenom);
	    zoneConsultation.add(panelConsultationNaissance);
	    zoneConsultation.add(panelConsultationSecu);
	    zoneConsultation.add(panelConsultationTelephone);
	    zoneConsultation.add(panelconsultationBouton);
	    
	    // Ajoute dans panel en haut à droite
	    panelDroite.add(zoneRecherche);
	    zoneRecherche.add(tableScrollable);
	    
	    // Bas Droit. 
	    panelDroite.add(containerBasDroit);
	    containerBasDroit.add(previewFicheMedicale);
	    containerBasDroit.add(zoneGestionConsultation);
	    zoneGestionConsultation.add(listeDeroulante);
	    zoneGestionConsultation.add(zoneEditionSuppression);
	    zoneEditionSuppression.add(supprimerConsultation);
	    zoneEditionSuppression.add(editerConsultation);
	    zoneGestionConsultation.add(zoneAjouterConsultation);
	    zoneAjouterConsultation.add(ajouterConsultation);
	    zoneAjouterConsultation.setBorder(bordureBouton);
	    previewFicheMedicale.add(zoneFichePathologie);
	    previewFicheMedicale.add(zoneFicheAntecedents);    
	    zoneFichePathologie.add(BorderLayout.NORTH, labelPathologie);
	    zoneFicheAntecedents.add(BorderLayout.NORTH,labelAntecedents);
	    zoneFichePathologie.add(ScrollerPathologie);
	    zoneFicheAntecedents.add(ScrollerAntedecent);
	    ZoneEdition.add(BorderLayout.CENTER, container);
	    container.add(ScrollerRapport);
	    container.add(ScrollerEditionPathologie);
	    container.add(ScrollerEditionAntecedents);
	    ZoneEdition.add(BorderLayout.SOUTH,zoneBoutonEdition);
	    zoneBoutonEdition.add(sauvegarderConsulation);
	    zoneBoutonEdition.add(reinitialiserConsultation);
	    zoneBoutonEdition.add(exporterConsultation);
	    zoneBoutonEdition.add(octroyerProthese);
	    
	    // Création des checkbox Pathologies & Antécédent
	    for(int i = 0; i < choixPathologie.length; i++) {
	    	JCheckBox Button = new JCheckBox(choixPathologie[i]);
	    	checkboxPathologie.add(Button);
	    	selectionPathologie.add(Button);
	    }
	    for(int i = 0; i < choixAntecedents.length; i++) {
	    	JCheckBox Button = new JCheckBox(choixAntecedents[i]);
	    	checkboxAntecedents.add(Button);
	    	selectionAntecedents.add(Button);
	    }
	    

	    
	    boutonRechercher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	String nom = champNom.getText();
            	String prenom = champPrenom.getText();
            	String dateNaissance = champNaissance.getText();
            	String numeroSecu = champSecu.getText();
            	String numeroTelephone = champTelephone.getText();
            	
            	
            	// Petit système de filtre simple.
            	// Si le champ n'est pas vide, on va renvoyer tous les 
            	// elements dont le debut est identique.
            	// ensuite le filtre passe dans les autres filtres.
            	ArrayList<Patient> filteredTab = gestionairePatient.retournerListePatient();
            	if( !nom.isEmpty() ) {
            		filteredTab = gestionairePatient.rechercherPatientNOM(nom, filteredTab);
            	} 
            	
            	if( !prenom.isEmpty() ) {
            		filteredTab = gestionairePatient.rechercherPatientPRENOM(prenom, filteredTab);
            	} 
            	
            	if( !dateNaissance.isEmpty() ) {
            		filteredTab = gestionairePatient.rechercherPatientDATENAISSANCE(dateNaissance, filteredTab);
            	} 
            	
            	if( !numeroSecu.isEmpty() ) {
            		filteredTab = gestionairePatient.rechercherPatientSECU(numeroSecu, filteredTab);
            	} 
            	
            	if( !numeroTelephone.isEmpty() ) {
            		filteredTab = gestionairePatient.rechercherPatientTELEPHONE(numeroTelephone, filteredTab);
            	} 
            	
            	// On convertit en Objet[][]
            	tabRecherche = gestionairePatient.retournerArrayString(filteredTab);
            	filtrerTable();
            }
        });
	    
	    table.addMouseListener(new MouseAdapter() {
	    	// Lorsque l'on clique sur un ligne
	    	// L'on va récupérer les informations à partir du model.
	    	public void mouseClicked(MouseEvent e) {
	    	int row = table.getSelectedRow();
	   
	    	String nom = model.getValueAt(row, 1).toString();
	    	String prenom = model.getValueAt(row, 2).toString();
	    	String dateNaissance = model.getValueAt(row, 3).toString();
	    	String numeroSecu = model.getValueAt(row, 4).toString();
	    	String numeroTelephone = model.getValueAt(row, 5).toString();
	    	
	    	// On met à jour le pannel de consultation.
		    placeHolderConsultationNom.setText(nom);
		    placeHolderConsultationPrenom.setText(prenom);
		    placeHolderConsultationNaissance.setText(dateNaissance);
		    placeHolderConsultationSecu.setText(numeroSecu);
		    placeHolderConsultationTelephone.setText(numeroTelephone);
		    selectedID = model.getValueAt(row, 0).toString();
		    
		    // Mis à jour des JList
		    updaterjListPathologieAntecedent(selectedID);
		    
		    // On met à jour la liste de consultation
	    	updaterListeConsultation();
	    	}
	    });
	    
	    supprimerConsultation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	if(consultationPatientID.isEmpty()) {
            		System.out.println("Pas de consultation à supprimer.");
            	} else {
            		int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer la consultation sélectionnée?", "Comfirmation de suppression.", JOptionPane.YES_NO_OPTION);
            		if (reponse == JOptionPane.YES_OPTION) {
                		JOptionPane.showMessageDialog(fenetre, "La consultation a été supprimé.", "Woosh!", JOptionPane.INFORMATION_MESSAGE);
                		Consultation consultation = obtenirObjectConsultationSelection();
                		gestionaireConsultation.supprimerConsultation(consultation);
                		
                		// On met à jour la liste de consultation
                		updaterListeConsultation();
            		}
            	}     	
            }
        });
	    
	    ajouterConsultation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            		if(selectedID != null) {
                		int reponse = JOptionPane.showConfirmDialog(null, "Souhaitez-vous ajouter une nouvelle consultation?", "Comfirmation d'ajout.", JOptionPane.YES_NO_OPTION);
                		if (reponse == JOptionPane.YES_OPTION) {
                    		JOptionPane.showMessageDialog(fenetre, "Vous venez de crééer une nouvelle fiche de consultation", "Woosh!", JOptionPane.INFORMATION_MESSAGE);
                    		
                    		gestionaireConsultation.creerConsultation(selectedID);
                    		
                    		// On met à jour la liste de consultation
                    		updaterListeConsultation();
                		}
            		}    	
            }
        });
	    
	    editerConsultation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	// on vérifie qu'une consultation est bien selectionnée.
            	if(selectedID != null && !consultationPatientID.isEmpty()) {
            		uncheckCheckbox();
            		ZoneEdition.setVisible(true);
            		
            		Patient patient = gestionairePatient.chargerPathologieAntecedent(selectedID);
            		Consultation consultation = obtenirObjectConsultationSelection(); // consultation selectionnée
            		
            		// On charge les données
            		chargerCheckbox(patient);
            		rapportConsultation.setText(consultation.Rapport.replace("\\n\\", "\n"));
            		if(consultation.statutOctroit.equals("0")) { // si non-demandée.
            			octroyerProthese.setEnabled(true);
            		} else {
            			octroyerProthese.setEnabled(false);
            		}
            	}
            }
        });
	    
	    reinitialiserConsultation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	// charge les checkbox tels qu'elles étaient avant modification
            	uncheckCheckbox();
            	chargerCheckbox(gestionairePatient.retournerPatientID(selectedID));
            	Consultation consultation = obtenirObjectConsultationSelection();
            	rapportConsultation.setText(consultation.Rapport.replace("\\n\\", "\n"));
            }
        }); 
	    
	    sauvegarderConsulation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	int reponse = JOptionPane.showConfirmDialog(null, "Souhaitez-vous sauvegarder la consultation?", "Vous vous apprêtez à sauvegarder la consultation.", JOptionPane.YES_NO_OPTION);
        		if (reponse == JOptionPane.YES_OPTION) {;
            		String txt = rapportConsultation.getText();
            		if(txt.contains(";")) {
            			JOptionPane.showMessageDialog(fenetre, "Vous ne pouvez pas utiliser de point virugle ;", "Désolé.", JOptionPane.WARNING_MESSAGE);
            		} else {
                  		ZoneEdition.setVisible(false);
            			Consultation consultation = obtenirObjectConsultationSelection();
            			gestionaireConsultation.editerConsultation(consultation, txt);
            			Patient patient = gestionairePatient.retournerPatientID(selectedID);
            			
            			// Mis a jour du fichier pathologie
            			ArrayList<String> pathologies =  new ArrayList<String>();
            			ArrayList<String> antecedents =  new ArrayList<String>();
            			for(int i = 0; i < checkboxPathologie.size(); i++) {
            				if(checkboxPathologie.get(i).isSelected()) {
            					pathologies.add(checkboxPathologie.get(i).getText());
            				}
            			}
            			for(int i = 0; i < checkboxAntecedents.size(); i++) {
            				if(checkboxAntecedents.get(i).isSelected()) {
            					antecedents.add(checkboxAntecedents.get(i).getText());
            				}
            			}
            			patient.pathologies = pathologies; // On met à jour les informations du patient
            			patient.antecedents = antecedents; // On met à jour les informations du patient
            			gestionairePatient.editerFichierPathologie(selectedID); // On ré-écrit le fichier.
            			
                	    // Mis à jour des JList car des pathologies ou antécédents auraient pu être ajoutés.
            		    updaterjListPathologieAntecedent(selectedID);
            			
                		JOptionPane.showMessageDialog(fenetre, "La consultation a été sauvegardé.", "Hourra!!", JOptionPane.INFORMATION_MESSAGE);
                		
                		// On  évite que les checkbox restent selectionnés.
                		uncheckCheckbox();
            		}
        		}
            }
        });
	    
	    octroyerProthese.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	Patient patient = gestionairePatient.chargerPathologieAntecedent(selectedID);
        		Consultation consultation = obtenirObjectConsultationSelection();
        		
        		// On désactive le bouton.
        		if(consultation.statutOctroit.equals("0")) {
        			int reponse = JOptionPane.showConfirmDialog(null, "Souhaitez-vous octroyer une prothèse.", "N'oubliez-pas de noter vos recommandations dans le rapport.", JOptionPane.YES_NO_OPTION);
            		if (reponse == JOptionPane.YES_OPTION) {
        			consultation.statutOctroit = "1";
        			octroyerProthese.setEnabled(false);
        			JOptionPane.showMessageDialog(fenetre, "Prothèse demandée.", null, JOptionPane.INFORMATION_MESSAGE);
        			
        			gestionaireConsultation.actualiserFichierConsultation();
            		}
        		}
            }
        });
	    
	    boutonAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	clearFormulaire(); // Supprime le text dans les zones de texte
            	afficherTableComplete(); // Etat initial.
            }
        });
	    
	    exporterConsultation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	int reponse = JOptionPane.showConfirmDialog(null, "Attention, n'oublier de sauvegarder avant d'exporter.\nSouhaitez-vous toujours exporter?", "Attention!", JOptionPane.YES_NO_OPTION);
        		if (reponse == JOptionPane.YES_OPTION) {
           			Patient patient = gestionairePatient.retournerPatientID(selectedID);
                	Consultation consultation = obtenirObjectConsultationSelection();
        			Consultation tabConsultation[] = {consultation};
        			
                	String txt = exporterConsultation(patient, tabConsultation);
                	sauvegarderExport(txt, ZoneEdition);
        		}   
            }
        });
	    
	    boutonExportFiche.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
    	    	if(selectedID != null) {
               		Patient patient = gestionairePatient.retournerPatientID(selectedID);
            		Consultation tabConsultation[] = new Consultation[consultationPatientID.size()];
            		
               		for(int i = 0; i < consultationPatientID.size(); i++) {
               			tabConsultation[i] = consultationPatientID.get(i);
               		}

                    String txt = exporterConsultation(patient, tabConsultation);
                    sauvegarderExport(txt, ZoneEdition);
    	    	}
            }
        });
	    
	    fenetre.setVisible(true);
	}
}
