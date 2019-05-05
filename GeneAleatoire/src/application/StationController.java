package application;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import metier.GenerationLois;
import metier.LineChartWithMarkers;

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
	
	@FXML
	private VBox vb = new VBox();
	
	final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();
    
	@FXML
	LineChartWithMarkers<Number, Number> lc = new LineChartWithMarkers<Number, Number>(xAxis, yAxis);

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
	
	private int counter = 0;
	
	
	// Appel� au lancement de l'application
	@FXML
	private void initialize(){
		
		// Valeurs par d�faut de lambda, T et nbJets
		lambdaCadence.setText("2");
		intervalle.setText("10");
		jets.setText("5");
		
		// Le lineChart est ajout� dans la Vbox
		vb.getChildren().add(lc);
		
		/* Config du lineChart */
		
		// Pas de l�gende
		lc.setLegendVisible(false);
		
		// Pas de grille horizontales et verticales
		lc.setHorizontalGridLinesVisible(false);
		lc.setVerticalGridLinesVisible(false);
		
		// Plage sur l'axe des abscisses
		xAxis.setAutoRanging(false);
		xAxis.setUpperBound(10);
		xAxis.setLowerBound(0);
		
		// Plage sur l'axe des ordonn�es
		yAxis.setAutoRanging(false);
		yAxis.setUpperBound(1);
		yAxis.setLowerBound(0);
		
	}
	
	
	/**
	 * Ex�cute le programme
	 * 
	 * @param Evenement au clic du bouton "D�marrer"
	 */
	public void actionDemarrer(ActionEvent evt){

		System.out.println("\n bt D�marrer");
		
		// On vide le graphe
		clearChart();
		
		// R�cup�ration des param�tres saisis
		recupIntervalle();
		recupLambdaCadence();
		recupNbJets();
		
		// Nombre al�atoire suivant une loi de Poisson, avec param�tres
		generationPoisson(cadence, T);
		
		// Ins�re les donn�es dans le graphe
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

        
		// On boucle sur notre dictionnaire de donn�es
		for (int i=0; i<valEvts.size(); i++){
			for (int j=0; j<valEvts.get(i).length; j++){
				
				// On ins�re une ligne verticale de hauteur 1
				setVerticalBar(valEvts.get(i)[j], 1.00);
				
				System.out.println(valEvts.get(i)[j]);
				long value = (long)valEvts.get(i)[j];
				System.out.println(value);
				
		        try {
			
			        Thread.sleep(value);

		        } catch (InterruptedException ie)
		        {
		            Thread.currentThread().interrupt();
		        }	
			}
		}
	}

	/**
	 * Trace une barre verticale sur notre graphe
	 * @param x : valeur en abscisse
	 * @param y : valeur en ordonn�e
	 */
	public void setVerticalBar(double x, double y){
		
		// La barre est cr�e selon les param�tres x et y
		Data<Number, Number> verticalBar = new Data<>(x, y);
		
		// Ajout de la barre au graphe
	    lc.addVerticalValueMarker(verticalBar);
	    
	    // Personnalisation
	    Slider verticalBarSlider = new Slider(xAxis.getLowerBound(), xAxis.getUpperBound(), 0);
	    verticalBarSlider.setOrientation(Orientation.HORIZONTAL);
	    verticalBarSlider.setShowTickLabels(true);
	    verticalBarSlider.valueProperty().bindBidirectional(verticalBar.XValueProperty());
	    verticalBarSlider.minProperty().bind(xAxis.lowerBoundProperty());
	    verticalBarSlider.maxProperty().bind(xAxis.upperBoundProperty());
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

		// Supprime les barres du graphe
		lc.removeAllVerticalMarker();
		
		// Supprime les valeurs dans notre tableau
		for(int i=0; i<valEvts.size();i++){
			valEvts.remove(i);
		}
				
		System.out.println("\n Donn�es vid�es");
	}

}
