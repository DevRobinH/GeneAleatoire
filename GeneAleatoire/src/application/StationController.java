package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class StationController {

	@FXML
	private BarChart<String, Number> barChart;
	
	@FXML
	private LineChart<Number, Number> lineChartExpo;
	
	@FXML
	private LineChart<Number, Number> lineChartPoisson;
	
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

	// Nombre de jets de simulation
	private int nbJets = 100;

	// Paramètre lambda de la fonction exp
	private double cadence = 2;
	
	// T de poisson
	private int T = 2;
	
	/**
	 * Exécute le programme
	 * 
	 * @param Evenement au clic du bouton "Démarrer"
	 */
	public void actionDemarrer(ActionEvent evt){

		System.out.println("\n bt Démarrer");
		clearChart();
		insertData();
	}
	
	
	/**
	 * Generation de valeurs suivant un processus de poisson
	 * @param lambda cadence 
	 * @param T intervalle de temps sur lequel va être généré les évènements
	 */
	public void generationPoisson(int lambda, int T) {
		// On va générer un nombre infini d'intervalle
		// Chaque intervalle de temps T génèrera un nombre d'èvènement
		for(;;) {
		         	
		}
	}
	
	/**
	 * Insère des données dans le graphe
	 */
	public void insertData(){
		
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		
		for(int i=0; i<20; i++){
			series.getData().add(new XYChart.Data<String, Number>("t"+i, 1));
			
		}
		
		barChart.getData().add(series);
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
		
		barChart.getData().clear();	
		lineChartExpo.getData().clear();
		lineChartPoisson.getData().clear();
		
		System.out.println("\n Données vidées");
	}
		
}
