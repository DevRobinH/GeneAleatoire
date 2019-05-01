package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import metier.GenerationLois;

public class StationController {

	@FXML
	private Button btDemarrer;
	@FXML
	private Button btArreter;

	@FXML
	private TextField lambdaCadence;
	@FXML
	private TextField intervalle;
	@FXML
	private TextField jets;
	
	@FXML
	private Label lbMoyenneThPoisson;
	@FXML
	private Label lbMoyenneThExpo;
	@FXML
	private Label lbMoyenneObsPoisson;
	@FXML
	private Label lbMoyenneObsExpo;

	// Nombre de jets de simulation
	private int nbJets = 100;

	// Paramètre lambda de la fonction exp
	private int cadence = 2;

	// T de poisson
	private int T = 2;

	// Tableau contenant le nombre d'évènement tiré pour chaque simulation (jet)
	private int[] nbEvtJets;

	// Liste qui contiendra un tableau qui contiendra les évènement de chaque jet
	private ArrayList<double[]> valEvts = new ArrayList<>();

	// Moyenne théorique de la loi de Poisson
	private double moyenneThPoisson;

	// Moyenne observé loi de Poisson
	private double moyenneObsPoisson;

	// Moyenne théorique de la loi de Expo
	private double moyenneThExpo;
	
	// Moyenne théorique de la loi de Expo
	private double moyenneObsExpo;
	
	// Liste des moyennes obs poisson par interval
	private ArrayList<Double> listeMoyennesObsExpo = new ArrayList<>();
	
	/**
	 * Exécute le programme
	 * 
	 * @param Evenement au clic du bouton "Démarrer"
	 */
	public void actionDemarrer(ActionEvent evt){

		System.out.println("\n bt Démarrer");
		clearChart();
		recupIntervalle();
		recupLambdaCadence();
		recupNbJets();
		generationPoisson(cadence, T);
		insertData();
	}


	/**
	 * Generation de valeurs suivant un processus de poisson
	 * @param lambda cadence 
	 * @param T intervalle de temps sur lequel va être généré les évènements
	 */
	public void generationPoisson(int lambda, int T) {
		int nbEv; // nombre d'évènement sur l'interval
		double[] val; //tableau tampon contennat les valeurs d'un intervalle
		double somme = 0; // variable tampon pour le calcul des moyennes obs
		double sommeEcart = 0;
		double nbEvTotal= 0;
		this.nbEvtJets = new int[this.nbJets];
		// On va générer un nombre nbJets d'intervalle
		// Chaque intervalle de temps T génèrera un nombre d'èvènement
		for(int i = 0 ; i < this.nbJets ; i++) {
			// On simule un processus de poisson
			nbEv = GenerationLois.loiPoisson(lambda, T);
			//System.out.println("POISSON : " + nbEv);
			// On stock le nombre d'évènement dans un tableau
			this.nbEvtJets[i] = nbEv;
			val = new double[nbEv];
			// On stock dans une liste un tableau contenant les valeurs de chaque évènement
			for (int j = 0; j < GenerationLois.valPoisson.size(); j++) {
				val[j] = GenerationLois.valPoisson.get(j);
				//System.out.println(val[j]);
			}
			// On stock le tableau des valeurs dans une liste
			this.valEvts.add(val);
			// on stock les moyenne obs expo dans la liste
			listeMoyennesObsExpo.add(val[val.length-1]/nbEv);
			// incrementation de la somme
			somme += nbEv;
			sommeEcart += val[val.length-1];
			nbEvTotal += nbEv;
		}
		// Calcul des moyennes th
		this.moyenneThPoisson = lambda*T;
		this.moyenneThExpo = 1/(double)lambda;
		// Calcul des moyennes obs 
		this.moyenneObsPoisson = somme/this.nbJets;
		this.moyenneObsExpo = sommeEcart/nbEvTotal;
		
		System.out.println("Moyenne Expo th : " + this.moyenneThExpo);
		System.out.println("Moyenne Expo obs : " + this.moyenneObsExpo);
		System.out.println("Moyenne Poisson th : " + this.moyenneThPoisson);
		System.out.println("Moyenne Poisson obs : " + this.moyenneObsPoisson);
		
		/*for(int i = 0 ; i < listeMoyennesObsExpo.size(); i++) {
			System.out.println(listeMoyennesObsExpo.get(i));
		}*/

	}

	/**
	 * Insère des données dans le graphe
	 */
	public void insertData(){
		
		lbMoyenneThPoisson.setText(String.format("%.3f",this.moyenneThPoisson));
		lbMoyenneThExpo.setText(String.format("%.3f",this.moyenneThExpo));
		
		lbMoyenneObsPoisson.setText(String.format("%.3f",this.moyenneObsPoisson));
		lbMoyenneObsExpo.setText(String.format("%.3f",this.moyenneObsExpo));

		
	}

	/**
	 * Récupère le lambda et l'assigne variable global
	 */
	public void recupLambdaCadence(){

		// On récupère le champ de lambda
		String recup = lambdaCadence.getText();

		// Si le champ à récupérer n'est pas null
		if (!recup.equals("")){

			// On le récupère
			cadence = Integer.parseInt(recup);

			System.out.println("\nLambda:");
			System.out.println(this.cadence);				
		}
		else{
			System.out.println("\nErreur récupération Lambda");
		}
	}

	public void recupIntervalle(){

		// On récupère le champ de l'intervalle
		String recup = intervalle.getText();

		// Si le champ à récupérer n'est pas null
		if (!recup.equals("")){

			// On le récupère
			T = Integer.parseInt(recup);

			System.out.println("\nIntervalle:");
			System.out.println(this.T);				
		}
		else{
			System.out.println("\nErreur récupération Intervalle");
		}
	}

	/**
	 * Récupère le nombre de jets
	 */
	public void recupNbJets(){

		// On récupère le champ du nombre de jets
		String recup = jets.getText();

		// Si le champ à récupérer n'est pas null
		if (!recup.equals("")){

			// On le récupère
			this.nbJets = Integer.parseInt(recup);

			System.out.println("\nNombre de jets :");
			System.out.println(this.nbJets);
		}
		else{
			System.out.println("\nErreur récupération nombre de jets");
		}

	}

	/**
	 * Stoppe le programme
	 * 
	 * @param Evenement au clic du bouton "Arrêter"
	 */
	public void actionArreter(ActionEvent evt){

		System.out.println("\n bt Arrêter");
		clearChart();
	}

	/**
	 * Vide les données des graphes
	 */
	public void clearChart(){

		System.out.println("\n Données vidées");
	}

}
