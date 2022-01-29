package projet_final;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
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

public class interface_technicien {
	
	static JFrame fenetre, fenetreRapport;
	static JPanel panelDroite, panelGauche, formulaire, labelFormulaire, champFormulaire, zoneBoutonFormulaire, zoneRecherche, 
	zoneConsultation, panelconsultationBouton, panelConsultationNom, panelConsultationPrenom, panelConsultationNaissance, 
	panelConsultationSecu, panelConsultationTelephone, previewFicheMedicale, zoneGestionConsultation, containerBasDroit,
	zoneBoutonConsultation, zoneFichePathologie, zoneFicheAntecedents;
	static JLabel labelChampNom, labelChampPrenom, labelChampNaissance, labelChampSecu, labelChampTelephone, labelConsultationNom, 
	labelConsultationPrenom, labelConsultationNaissance, labelConsultationSecu, labelConsultationTelephone, placeHolderConsultationNom, 
	placeHolderConsultationPrenom, placeHolderConsultationNaissance, placeHolderConsultationSecu, placeHolderConsultationTelephone,
	labelPathologie, labelAntecedents;
	static JTextField champNom, champPrenom, champNaissance, champSecu, champTelephone, champNomEdition;
	static TitledBorder titreFormulaire, titreConsultation, titreTableauRecherche, titrePreview, titreGestionConsultation, titreRapport;
	static Border bordureInterieur, bordureBouton, bordureZoneFiche, marginLabel;
	static JButton boutonAnnuler, boutonRechercher, boutonExportFiche, octroyerProthese, consulterRapport; 
	static JTable table;
    static JScrollPane tableScrollable, ScrollerPathologie, ScrollerAntedecent, ScrollerRapport, ScrollerEditionPathologie,
    ScrollerEditionAntecedents, txtScrollable;
    static JTextArea txtRapport;
    static TableColumnModel columnModel;
    static TableModel model;
    static JComboBox<String> listeDeroulante;
    static DefaultComboBoxModel modelConsultationAnterieur;
    static String[] Colonnes = {"ID", "Nom", "Prenom", "Date de Naissance", "Numéro de sécurité sociale", "Numéro Telephone"};
    static JList listePathologie, listeAntecedent;
    static DefaultListModel modelPathologie, modelAntecedent;
    static JFileChooser choixFichier;
    
	static patientManager gestionairePatient = new patientManager("src/patient_database.txt", "src/pathologies_database.txt");
	static consultationManager gestionaireConsultation = new consultationManager("src/consultation_database.txt");
	// Tableau de patient globale.
	static ArrayList<Patient> tableauPatients = gestionairePatient.retournerListePatient();
	// Tableau de patient filtre.
	static Object[][] tabRecherche;
	// Dernier ID sélectionné
	static String selectedID = null;
	static ArrayList<Consultation> consultationPatientID = new ArrayList<Consultation>(); // liste d'object consultation consultation à l'ID choisi.
	static ArrayList<Consultation> consultationDemandantProthese;
	static Object[] consultationAnterieur = {}; // Summury des consultation antérieurs à l'ID choisi.
    
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
		consultationDemandantProthese = new ArrayList<Consultation>();
		
		consultationAnterieur = gestionaireConsultation.consulatationSummery(consultationPatientID); 
		for(int i = 0; i < consultationPatientID.size(); i++) {
			if (consultationPatientID.get(i).statutOctroit.equals("1")) {
				consultationDemandantProthese.add(consultationPatientID.get(i));
			}
		}
		consultationAnterieur = gestionaireConsultation.consulatationSummery(consultationDemandantProthese); // On affiche juste la date de consultation car c'est plus simple à lire.
		
		// Reconstruction du modèle pour le réapliquer.
	    modelConsultationAnterieur = new DefaultComboBoxModel(consultationAnterieur);
	    listeDeroulante.setModel(modelConsultationAnterieur);
	    
	    // On se positionne sur la dernier élement.
	    modelConsultationAnterieur.setSelectedItem(modelConsultationAnterieur.getElementAt(consultationAnterieur.length-1));
	}
	
	// Obtenir object consultation sélectionné dans le tableau consultationDemandantProthese.
	public static Consultation obtenirObjectConsultationSelection() {
    	listeDeroulante.getSelectedIndex();
    	int indiceConsultation = listeDeroulante.getSelectedIndex();
    	Consultation consultation = consultationDemandantProthese.get(indiceConsultation);
    	
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
		fenetre = new JFrame("Interface Technicien");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setSize(1500, 800);
		fenetre.setLayout(new BorderLayout());
		
		// Création fenetre rapport 
    	fenetreRapport = new JFrame("Rapport");
    	fenetreRapport.setSize(1000, 600);
		
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
	    zoneBoutonConsultation = new JPanel(new GridLayout(0,2, 10, 20));
	    zoneFichePathologie = new JPanel(new BorderLayout());
	    zoneFicheAntecedents = new JPanel(new BorderLayout());
	    
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
	    labelAntecedents = new JLabel("Antécédents:");;
	    
		// Création des boutons
	    boutonAnnuler = new JButton("Annuler");
	    boutonRechercher= new JButton("Rechercher");
	    boutonExportFiche = new JButton("Exporter fiche medicale");
	    octroyerProthese = new JButton("Octroyer Prothèse");
	    consulterRapport = new JButton("Consulter Rapport");
	    
	    
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
	    
	    // Fenetre Rapport
	    txtRapport = new JTextArea(0,0);
	    txtRapport.setLineWrap(true);
	    txtRapport.setWrapStyleWord(true);
    	txtScrollable = new JScrollPane(txtRapport, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	txtScrollable.setPreferredSize(new Dimension(fenetreRapport.getWidth() - 60, fenetreRapport.getHeight() -30));
    	fenetreRapport.getContentPane().add(txtScrollable);
    	txtRapport.setEditable(false);
    	
	    // Ajout des bordures 
	    bordureInterieur = BorderFactory.createEmptyBorder(15, 15, 15, 15);
	    bordureBouton = BorderFactory.createEmptyBorder(20,0,5,0);
	    bordureZoneFiche = BorderFactory.createEmptyBorder(0,10,15,10);
	    marginLabel = BorderFactory.createEmptyBorder(0,0,10,0);
	    titreTableauRecherche = BorderFactory.createTitledBorder("Cliquez pour selectionner un patient"); 
	    titreConsultation = BorderFactory.createTitledBorder("Information sur le patient");
	    titreFormulaire = BorderFactory.createTitledBorder("Recherche Patient");
	    titreRapport = BorderFactory.createTitledBorder("Rapport de la consultation");
	    titrePreview = BorderFactory.createTitledBorder("Pathologies & Antécédents");
	    titreGestionConsultation = BorderFactory.createTitledBorder("Gestion des consultations");
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
	    zoneBoutonConsultation.setBorder(bordureBouton);
	    txtScrollable.setBorder(BorderFactory.createCompoundBorder(titreRapport, BorderFactory.createEmptyBorder(5,5,5,5)));

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
	    zoneGestionConsultation.add(zoneBoutonConsultation);
	    zoneBoutonConsultation.add(octroyerProthese);
	    zoneBoutonConsultation.add(consulterRapport);
	    previewFicheMedicale.add(zoneFichePathologie);
	    previewFicheMedicale.add(zoneFicheAntecedents);    
	    zoneFichePathologie.add(BorderLayout.NORTH, labelPathologie);
	    zoneFicheAntecedents.add(BorderLayout.NORTH,labelAntecedents);
	    zoneFichePathologie.add(ScrollerPathologie);
	    zoneFicheAntecedents.add(ScrollerAntedecent);
	    

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
	    
	    octroyerProthese.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
        		if(selectedID != null) {
        			if(!consultationDemandantProthese.isEmpty()) {
            			Patient patient = gestionairePatient.retournerPatientID(selectedID);
            			Consultation consultation = obtenirObjectConsultationSelection();
                		// On change le statut
                		if(consultation.statutOctroit.equals("1")) {
                			int reponse = JOptionPane.showConfirmDialog(null, "Souhaitez-vous livrer la prothèse?\nVous ne pourrez-plus consulter le rapport ensuite.", "Vous vous apprêtez changer le statut de la demande.", JOptionPane.YES_NO_OPTION);
                    		if (reponse == JOptionPane.YES_OPTION) {
                			consultation.statutOctroit = "2";
                			JOptionPane.showMessageDialog(fenetre, "Prothèse livrée.", null, JOptionPane.INFORMATION_MESSAGE);
                			
                			gestionaireConsultation.actualiserFichierConsultation();
                			updaterListeConsultation(); // car on n'a plus de prothèse à octoryer
                    		}
                		}
        			}
        		}
            }
        });
	    
	    consulterRapport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	if(selectedID != null) {
            		if(!consultationDemandantProthese.isEmpty()) {

            			Patient patient = gestionairePatient.retournerPatientID(selectedID);
                    	Consultation consultation = obtenirObjectConsultationSelection();
            			Consultation tabConsultation[] = {consultation};
                    	String txt = exporterConsultation(patient, tabConsultation);
                    	
                    	txtRapport.setText(txt);
                    	txtRapport.setCaretPosition(0);
                    	fenetreRapport.setLocationRelativeTo(null);
                    	fenetreRapport.setVisible(true);
            		}
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
                    sauvegarderExport(txt, fenetreRapport);
    	    	}
            }
        });
	    
	    fenetre.setVisible(true);
	}
}
