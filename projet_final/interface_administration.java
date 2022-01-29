package projet_final;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class interface_administration {
	
	static JFrame fenetre, fenetreEdition;
	static JPanel panelDroite, panelGauche, formulaire, labelFormulaire, champFormulaire, zoneBoutonFormulaire, zoneRecherche, 
	zoneConsultation, panelconsultationBouton, panelConsultationNom, panelConsultationPrenom, panelConsultationNaissance, 
	panelConsultationSecu, panelConsultationTelephone, panelLabelEdition, panelChampEdition, panelBoutonEdition;
	static JLabel labelChampNom, labelChampPrenom, labelChampNaissance, labelChampSecu, labelChampTelephone, labelConsultationNom, 
	labelConsultationPrenom, labelConsultationNaissance, labelConsultationSecu, labelConsultationTelephone, placeHolderConsultationNom, 
	placeHolderConsultationPrenom, placeHolderConsultationNaissance, placeHolderConsultationSecu, placeHolderConsultationTelephone,
	labelNomEdition, labelPrenomEdition, labelNaissanceEdition, labelNumeroSecuEdition, labelNumeroTelephoneEdition;
	static JTextField champNom, champPrenom, champNaissance, champSecu, champTelephone, champNomEdition, champPrenomEdition, 
	champNaissanceEdition, champNumeroSecuEdition, champNumeroTelephoneEdition;
	static TitledBorder titreFormulaire, titreConsultation, titreTableauRecherche;
	static Border bordureInterieur, bordureBouton;
	static JButton boutonAjouter, boutonAnnuler, boutonRechercher, boutonEditer, boutonSupprimer, validerEdition, annulerEdition; 
	static JTable table;
    static JScrollPane tableScrollable;
    static TableColumnModel columnModel;
    static TableModel model;
    
    static String[] Colonnes = {"ID", "Nom", "Prenom", "Date de Naissance", "Num�ro de s�curit� sociale", "Num�ro Telephone"};
   
	static patientManager gestionairePatient = new patientManager("src/patient_database.txt", "src/pathologies_database.txt");
	// Tableau de patient globale.
	static ArrayList<Patient> tableauPatients = gestionairePatient.retournerListePatient();
	// Tableau de patient filtre.
	static Object[][] tabRecherche;
	// Dernier ID s�lectionn�, ou dernier patient ajout�.  
	static String selectedID = null;
	
	public static void clearFormulaire() {
		champNom.setText("");
		champPrenom.setText("");
		champNaissance.setText("");
		champSecu.setText("");
		champTelephone.setText("");
	}
	// R�aplique le mod�le � partir des donn�es compl�tes.
	// R�initialise tabRecherche;
	public static void afficherTableComplete() {
		tabRecherche = gestionairePatient.retournerArrayString();
		model = new DefaultTableModel(tabRecherche, Colonnes);
		table.setModel(model);
		setColUUIDWidth();
	}
	// R�aplique le mod�le � partir de tabRecherche
	// d�s lors filtr�.
	public static void filtrerTable() {
		model = new DefaultTableModel(tabRecherche, Colonnes);
		table.setModel(model);
		setColUUIDWidth();
	}
	
	public static void setColUUIDWidth() {
		columnModel.getColumn(0).setPreferredWidth(190);
	}
	
	public static void misAJourPanelEdition() {
		Patient patient = gestionairePatient.retournerPatientID(selectedID);
		champNomEdition.setText(patient.Nom);
		champPrenomEdition.setText(patient.Prenom);
		champNaissanceEdition.setText(patient.dateNaissance);
		champNumeroSecuEdition.setText(patient.numeroSecu);
		champNumeroTelephoneEdition.setText(patient.numeroTelephone);
	}
	
	public static void main(String[] args) {
		
		// Cr�ation de la fen�tre principale
		fenetre = new JFrame("Interface Medecin");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setSize(1500, 800);
		fenetre.setLayout(new BorderLayout());
		fenetre.setLocationRelativeTo(null);
		
		// Cr�ation fen�tre �dition 
		fenetreEdition = new JFrame();
		fenetreEdition.setTitle("Edition Patient");
		fenetreEdition.setSize(600,400);
		fenetreEdition.setLayout(new BorderLayout());
		fenetreEdition.setLocationRelativeTo(null);
		
		// Cr�ation des pannels
		panelDroite = new JPanel(new GridLayout(1,0));
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
	    panelconsultationBouton = new JPanel(new GridLayout(0, 2, 10, 5));
	
		panelLabelEdition = new JPanel(new GridLayout(5,1,0,10));
		panelChampEdition = new JPanel(new GridLayout(5,1,0,10));
		panelBoutonEdition = new JPanel(new GridLayout(1,0, 10,0));
	    
		// Cr�ation des champs. 
		champNom = new JTextField();
		champPrenom = new JTextField();
		champNaissance = new JTextField();
		champSecu = new JTextField();
		champTelephone = new JTextField();
		
		champNomEdition = new JTextField();
		champPrenomEdition = new JTextField();
		champNaissanceEdition = new JTextField();
		champNumeroSecuEdition = new JTextField();
		champNumeroTelephoneEdition = new JTextField();
		
	    // Cr�ation des labels 
		labelChampNom = new JLabel("Nom");
		labelChampPrenom = new JLabel("Pr�nom");
		labelChampNaissance = new JLabel("Date de naissance");
		labelChampSecu = new JLabel("Num�ro de s�curit� sociale");
		labelChampTelephone = new JLabel("Num�ro de t�l�phone");
	    labelConsultationNom = new JLabel("Nom");
	    labelConsultationPrenom = new JLabel("Prenom");
	    labelConsultationNaissance = new JLabel("Date de naissance");
	    labelConsultationSecu = new JLabel("Num�ro de S�curit� Sociale");
	    labelConsultationTelephone = new JLabel("Num�ro de t�l�phone");
	    placeHolderConsultationNom = new JLabel("//");
	    placeHolderConsultationPrenom = new JLabel("//");
	    placeHolderConsultationNaissance = new JLabel("//");
	    placeHolderConsultationSecu = new JLabel("//");
	    placeHolderConsultationTelephone = new JLabel("//");
	    
		labelNomEdition = new JLabel("Nom");
		labelPrenomEdition = new JLabel("Prenom");
		labelNaissanceEdition = new JLabel("Date de naissance");
		labelNumeroSecuEdition = new JLabel("Num�ro de s�curit� social");
		labelNumeroTelephoneEdition = new JLabel("Num�ro de t�l�phone");
	    
		// Cr�ation des boutons
	    boutonAjouter = new JButton("Ajouter");
	    boutonAnnuler = new JButton("Annuler");
	    boutonRechercher= new JButton("Rechercher");
	    boutonEditer = new JButton("Editer");
	    boutonSupprimer = new JButton("Supprimer");
	    
	    validerEdition = new JButton("Valider");
	    annulerEdition = new JButton("Annuler");
	    
	    // Cr�ation de la table
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
	    
	    // Ajout des bordures 
	    bordureInterieur = BorderFactory.createEmptyBorder(15, 15, 15, 15);
	    bordureBouton = BorderFactory.createEmptyBorder(20,0,5,0);
	    titreTableauRecherche = BorderFactory.createTitledBorder("Cliquez pour selectionner un patient"); 
	    titreConsultation = BorderFactory.createTitledBorder("Information � propos du patient selectionn�");
	    titreFormulaire = BorderFactory.createTitledBorder("Formulaire d'ajout");
	    formulaire.setBorder(BorderFactory.createCompoundBorder(titreFormulaire, bordureInterieur));
		zoneRecherche.setBorder(BorderFactory.createCompoundBorder(titreTableauRecherche, bordureInterieur));
		zoneConsultation.setBorder(BorderFactory.createCompoundBorder(titreConsultation, bordureInterieur));
	    // zoneRecherche.setBorder(bordureInterieur);
	    panelconsultationBouton.setBorder(bordureBouton);
	    zoneBoutonFormulaire.setBorder(bordureBouton);
	    
	    panelBoutonEdition.setBorder(bordureInterieur);
	    panelChampEdition.setBorder(bordureInterieur);
	    panelLabelEdition.setBorder(bordureInterieur);
	    
	    // Style des pannels
	    formulaire.setPreferredSize(new Dimension(480,300));
	    
	    // Style des champs
		champNom.setColumns(20);
		champPrenom.setColumns(20);
		champNom.setColumns(20);
		champSecu.setColumns(20);
		champTelephone.setColumns(20);
	    
		champNomEdition.setColumns(30);
		champPrenomEdition.setColumns(30);
		champNaissanceEdition.setColumns(30);
		champNumeroSecuEdition.setColumns(30);
		champNumeroTelephoneEdition.setColumns(30);
		
		// Ajout des pannels dans la fen�tre
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
	    zoneBoutonFormulaire.add(boutonAjouter);
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
	    panelconsultationBouton.add(boutonEditer);
	    panelconsultationBouton.add(boutonSupprimer);
	    zoneConsultation.add(panelConsultationNom);
	    zoneConsultation.add(panelConsultationPrenom);
	    zoneConsultation.add(panelConsultationNaissance);
	    zoneConsultation.add(panelConsultationSecu);
	    zoneConsultation.add(panelConsultationTelephone);
	    zoneConsultation.add(panelconsultationBouton);
	    
	    // Ajoute dans panel droite
	    panelDroite.add(zoneRecherche);
	    panelDroite.add(zoneRecherche);
	    zoneRecherche.add(tableScrollable);
	    
	   // Ajout element fen�tre edition
		fenetreEdition.add(BorderLayout.CENTER, panelLabelEdition);
		fenetreEdition.add(BorderLayout.LINE_END, panelChampEdition);
		fenetreEdition.add(BorderLayout.PAGE_END, panelBoutonEdition);
		panelLabelEdition.add(labelNomEdition);
		panelLabelEdition.add(labelPrenomEdition);
		panelLabelEdition.add(labelNaissanceEdition);
		panelLabelEdition.add(labelNumeroSecuEdition);
		panelLabelEdition.add(labelNumeroTelephoneEdition);
		panelChampEdition.add(champNomEdition);
		panelChampEdition.add(champPrenomEdition);
		panelChampEdition.add(champNaissanceEdition);
		panelChampEdition.add(champNumeroSecuEdition);
		panelChampEdition.add(champNumeroTelephoneEdition);
		panelBoutonEdition.add(validerEdition);
		panelBoutonEdition.add(annulerEdition);
		
	    
		
	    boutonAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	
            	String nom = champNom.getText();
            	String prenom = champPrenom.getText();
            	String dateNaissance = champNaissance.getText();
            	String numeroSecu = champSecu.getText();
            	String numeroTelephone = champTelephone.getText();
           
            	ArrayList<String> tabErreur = new ArrayList<String>();           	
            	boolean validation = true; // � la moindre erreur -> false.
            	
            	if(!nom.isBlank() && !prenom.isBlank() && !dateNaissance.isBlank() && !numeroSecu.isBlank() && !numeroTelephone.isBlank()) {
            		// On v�rifie que la date est du bon format.
            		try {
                		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
                		formatDate.setLenient(false);
						formatDate.parse(dateNaissance);
						
						if(dateNaissance.length() != 10) {
							throw new ParseException("Format de date Invalide", 0);
						}
					} catch (ParseException e) {
						tabErreur.add("Format de date Invalide");
						validation = false; 
					}
            		
            		// On ne veut pas que le fichi� soit unloadable tout de m�me.
            		if(nom.contains(";")) {
            			tabErreur.add("Votre nom ne peut pas contenir de point virgule.");
						validation = false; 
            		}
            		if(prenom.contains(";")) {
            			tabErreur.add("Votre prenom ne peut pas contenir de point virgule.");
						validation = false; 
            		}
            		
            		// On v�rifie que le num�ro de s�curit� sociale est du bon format.
            		try {
            			Long.parseLong(numeroSecu);
                		if(numeroSecu.length() != 15) {
                			tabErreur.add("Votre num�ro de s�curit� sociale doit contenir 15 chiffres.");
    						validation = false; 
                		}
            		} catch (NumberFormatException e) {
            			tabErreur.add("Votre num�ro de s�curit� sociale ne doit contenir que des chiffres.");
						validation = false; 
            		}
           
            		
            		// Si pas d'erreurs
            		if(validation) {
            			// Confirmation de l'ajout
                		int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous ajouter le patient?", "Comfirmation ajout", JOptionPane.YES_NO_OPTION);
                		if (reponse == JOptionPane.YES_OPTION) {
                    		JOptionPane.showMessageDialog(fenetre, "Le Patient a �t� ajout�.", "Bravo!", JOptionPane.INFORMATION_MESSAGE);
                    		
                    		String UUID = gestionairePatient.ajouterPatient(nom, prenom, dateNaissance, numeroSecu, numeroTelephone);
                    		clearFormulaire();
                    		afficherTableComplete();
                		    placeHolderConsultationNom.setText(nom);
                		    placeHolderConsultationPrenom.setText(prenom);
                		    placeHolderConsultationNaissance.setText(dateNaissance);
                		    placeHolderConsultationSecu.setText(numeroSecu);
                		    placeHolderConsultationTelephone.setText(numeroTelephone);
                		    selectedID = UUID; // l'UUID selectionn� devient l'UUID du patient alors ajout�.
                		    misAJourPanelEdition();
                		} 
            		} 
            	} else {
        			tabErreur.add("Au moins l'un de vos champ est vide.");
        		}
            	
            	// Si erreurs
            	// On met tous les message en une seule chaine.
            	if(!tabErreur.isEmpty()) {
            		String msg = "";
            		for(int i = 0; i < tabErreur.size(); i++) {
            			msg = msg + "\n" + tabErreur.get(i); 
            		}
            		JOptionPane.showMessageDialog(fenetre, msg, "oops..", JOptionPane.WARNING_MESSAGE);
            		tabErreur.clear();
            	}
            }
        });
	    
	    boutonAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	clearFormulaire(); // Supprime le text dans les zones de texte
            	afficherTableComplete(); // Etat initial.
            }
        });
	    
	    boutonRechercher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	String nom = champNom.getText();
            	String prenom = champPrenom.getText();
            	String dateNaissance = champNaissance.getText();
            	String numeroSecu = champSecu.getText();
            	String numeroTelephone = champTelephone.getText();
            	
            	
            	// Petit syst�me de filtre simple.
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
	    	// L'on va r�cup�rer les informations � partir du model.
	    	public void mouseClicked(MouseEvent e) {
	    	int row = table.getSelectedRow();
	   
	    	String nom = model.getValueAt(row, 1).toString();
	    	String prenom = model.getValueAt(row, 2).toString();
	    	String dateNaissance = model.getValueAt(row, 3).toString();
	    	String numeroSecu = model.getValueAt(row, 4).toString();
	    	String numeroTelephone = model.getValueAt(row, 5).toString();
	    	
	    	// On met � jour le pannel de consultation.
		    placeHolderConsultationNom.setText(nom);
		    placeHolderConsultationPrenom.setText(prenom);
		    placeHolderConsultationNaissance.setText(dateNaissance);
		    placeHolderConsultationSecu.setText(numeroSecu);
		    placeHolderConsultationTelephone.setText(numeroTelephone);
		    selectedID = model.getValueAt(row, 0).toString();
		    
		    // On met � jour le pannel d'�dition alors cach�.
		    misAJourPanelEdition();
	    	}
	    });
	    
	    boutonSupprimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	
            	// Si un ID est selectionn�
            	// Sinon le bouton ne fait rien.
            	if(selectedID != null) {
            		int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer le patient s�lectionn�?", "Comfirmation de suppression", JOptionPane.YES_NO_OPTION);
            		if (reponse == JOptionPane.YES_OPTION) {
                		JOptionPane.showMessageDialog(fenetre, "Le Patient a �t� supprim�.", "Aurevoir :(", JOptionPane.INFORMATION_MESSAGE);
                		
                    	placeHolderConsultationNom.setText("");
            		    placeHolderConsultationPrenom.setText("");
            		    placeHolderConsultationNaissance.setText("");
            		    placeHolderConsultationSecu.setText("");
            		    placeHolderConsultationTelephone.setText("");
            		    
            		    // On recherche un objet � patient � partir de l'ID selectionn�.
            		    Patient patient = gestionairePatient.retournerPatientID(selectedID);
            		    gestionairePatient.supprimerPatient(patient);
            		    selectedID = null; // l'id devient null.
            		    fenetreEdition.setVisible(false); // on ferme naturellement la fen�tre d'�dition.
            		    
            		    // On reg�n�re la table pour r�-applique les filtres.
            		    afficherTableComplete();
            		    filtrerTable();
            		}
            	}
            }
        });
	    
	    
	    boutonEditer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	if(selectedID != null) {
            		fenetreEdition.setVisible(true);
            	}
            }
        });
	    
	    annulerEdition.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	fenetreEdition.setVisible(false);
            }
        });
	    
	    validerEdition.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	if(selectedID != null) {
            		String nom = champNomEdition.getText();
                	String prenom = champPrenomEdition.getText();
                	String dateNaissance = champNaissanceEdition.getText();
                	String numeroSecu = champNumeroSecuEdition.getText();
                	String numeroTelephone = champNumeroTelephoneEdition.getText();
            		
            		ArrayList<String> tabErreur = new ArrayList<String>();           	
                	boolean validation = true; // � la moindre erreur -> false.
                	
                	
                	if(!nom.isBlank() && !prenom.isBlank() && !dateNaissance.isBlank() && !numeroSecu.isBlank() && !numeroTelephone.isBlank()) {
                		// On v�rifie que la date est du bon format.
                		try {
                    		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
                    		formatDate.setLenient(false);
    						formatDate.parse(dateNaissance);
    						if(dateNaissance.length() != 10) {
    							throw new ParseException("Format de date Invalide", 0);
    						}
    					} catch (ParseException e) {
    						tabErreur.add("Format de date Invalide");
    						validation = false; 
    					}
                		// On v�rifie que le num�ro de s�curit� sociale est du bon format.
                		try {
                			Long.parseLong(numeroSecu);
                		} catch (NumberFormatException e) {
                			tabErreur.add("Votre num�ro de s�curit� sociale ne doit contenir que des chiffres.");
    						validation = false; 
                		}
                		
                		// Si pas d'erreurs
                		if(validation) {
                			// Confirmation de l'ajout
        	            	int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous editer le patient?", "Comfirmation d'�dition", JOptionPane.YES_NO_OPTION);
        	            	if (reponse == JOptionPane.YES_OPTION) {
        	            		Patient patient = gestionairePatient.retournerPatientID(selectedID);
        	            		gestionairePatient.editerPatient(patient, nom, prenom, dateNaissance, numeroSecu, numeroTelephone);
        	            		JOptionPane.showMessageDialog(fenetre, "Le Patient a �t� �dit�.", "Bravo!", JOptionPane.INFORMATION_MESSAGE);
        	            		
        	            		// On ferme la fen�tre
        	            		// On r�affiche le tableau
        	            		// Puis on r�applique les filtres
        	            		fenetreEdition.setVisible(false);
                    		    afficherTableComplete();
                    		    filtrerTable();
        	        		}
                    	}
                	} else {
            			tabErreur.add("Au moins l'un de vos champ est vide.");
            		}
                	
                	// Si erreurs
                	// On met tous les message en une seule chaine.
                	if(!tabErreur.isEmpty()) {
                		String msg = "";
                		for(int i = 0; i < tabErreur.size(); i++) {
                			msg = msg + "\n" + tabErreur.get(i); 
                		}
                		JOptionPane.showMessageDialog(fenetre, msg, "oops..", JOptionPane.WARNING_MESSAGE);
                		tabErreur.clear();
                	}
            	}
            }
        });
	    fenetre.setVisible(true);
	}
}
