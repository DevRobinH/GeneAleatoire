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

	// Param�tre lambda de la fonction exp
	private int cadence = 2;

	// T de poisson
	private int T = 2;

	// Tableau contenant le nombre d'�v�nement tir� pour chaque simulation (jet)
	private int[] nbEvtJets;

	// Liste qui contiendra un tableau qui contiendra les �v�nement de chaque jet
	private ArrayList<double[]> valEvts = new ArrayList<>();

	// Moyenne th�orique de la loi de Poisson
	private double moyenneThPoisson;

	// Moyenne observ� loi de Poisson
	private double moyenneObsPoisson;

	// Moyenne th�orique de la loi de Expo
	private double moyenneThExpo;
	
	// Moyenne th�orique de la loi de Expo
	private double moyenneObsExpo;
	
	// Liste des moyennes obs poisson par interval
	private ArrayList<Double> listeMoyennesObsExpo = new ArrayList<>();
	
	/**
	 * Ex�cute le programme
	 * 
	 * @param Evenement au clic du bouton "D�marrer"
	 */
	public void actionDemarrer(ActionEvent evt){

		System.out.println("\n bt D�marrer");
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
	 * @param T intervalle de temps sur lequel va �tre g�n�r� les �v�nements
	 */
	public void generationPoisson(int lambda, int T) {
		int nbEv; // nombre d'�v�nement sur l'interval
		double[] val; //tableau tampon contennat les valeurs d'un intervalle
		double somme = 0; // variable tampon pour le calcul des moyennes obs
		double sommeEcart = 0;
		double nbEvTotal= 0;
		this.nbEvtJets = new int[this.nbJets];
		// On va g�n�rer un nombre nbJets d'intervalle
		// Chaque intervalle de temps T g�n�rera un nombre d'�v�nement
		for(int i = 0 ; i < this.nbJets ; i++) {
			// On simule un processus de poisson
			nbEv = GenerationLois.loiPoisson(lambda, T);
			//System.out.println("POISSON : " + nbEv);
			// On stock le nombre d'�v�nement dans un tableau
			this.nbEvtJets[i] = nbEv;
			val = new double[nbEv];
			// On stock dans une liste un tableau contenant les valeurs de chaque �v�nement
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
	 * Ins�re des donn�es dans le graphe
	 */
	public void insertData(){
		
		lbMoyenneThPoisson.setText(String.format("%.3f",this.moyenneThPoisson));
		lbMoyenneThExpo.setText(String.format("%.3f",this.moyenneThExpo));
		
		lbMoyenneObsPoisson.setText(String.format("%.3f",this.moyenneObsPoisson));
		lbMoyenneObsExpo.setText(String.format("%.3f",this.moyenneObsExpo));

		
	}

	/**
	 * R�cup�re le lambda et l'assigne variable global
	 */
	public void recupLambdaCadence(){

		// On r�cup�re le champ de lambda
		String recup = lambdaCadence.getText();

		// Si le champ � r�cup�rer n'est pas null
		if (!recup.equals("")){

			// On le r�cup�re
			cadence = Integer.parseInt(recup);

			System.out.println("\nLambda:");
			System.out.println(this.cadence);				
		}
		else{
			System.out.println("\nErreur r�cup�ration Lambda");
		}
	}

	public void recupIntervalle(){

		// On r�cup�re le champ de l'intervalle
		String recup = intervalle.getText();

		// Si le champ � r�cup�rer n'est pas null
		if (!recup.equals("")){

			// On le r�cup�re
			T = Integer.parseInt(recup);

			System.out.println("\nIntervalle:");
			System.out.println(this.T);				
		}
		else{
			System.out.println("\nErreur r�cup�ration Intervalle");
		}
	}

	/**
	 * R�cup�re le nombre de jets
	 */
	public void recupNbJets(){

		// On r�cup�re le champ du nombre de jets
		String recup = jets.getText();

		// Si le champ � r�cup�rer n'est pas null
		if (!recup.equals("")){

			// On le r�cup�re
			this.nbJets = Integer.parseInt(recup);

			System.out.println("\nNombre de jets :");
			System.out.println(this.nbJets);
		}
		else{
			System.out.println("\nErreur r�cup�ration nombre de jets");
		}

	}

	/**
	 * Stoppe le programme
	 * 
	 * @param Evenement au clic du bouton "Arr�ter"
	 */
	public void actionArreter(ActionEvent evt){

		System.out.println("\n bt Arr�ter");
		clearChart();
	}

	/**
	 * Vide les donn�es des graphes
	 */
	public void clearChart(){

		System.out.println("\n Donn�es vid�es");
	}

}
