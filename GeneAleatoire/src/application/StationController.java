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

	// Param�tre lambda de la fonction exp
	private double cadence = 2;
	
	// T de poisson
	private int T = 2;
	
	/**
	 * Ex�cute le programme
	 * 
	 * @param Evenement au clic du bouton "D�marrer"
	 */
	public void actionDemarrer(ActionEvent evt){

		System.out.println("\n bt D�marrer");
		clearChart();
		insertData();
	}
	
	
	/**
	 * Generation de valeurs suivant un processus de poisson
	 * @param lambda cadence 
	 * @param T intervalle de temps sur lequel va �tre g�n�r� les �v�nements
	 */
	public void generationPoisson(int lambda, int T) {
		// On va g�n�rer un nombre infini d'intervalle
		// Chaque intervalle de temps T g�n�rera un nombre d'�v�nement
		for(;;) {
		         	
		}
	}
	
	/**
	 * Ins�re des donn�es dans le graphe
	 */
	public void insertData(){
		
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		
		for(int i=0; i<20; i++){
			series.getData().add(new XYChart.Data<String, Number>("t"+i, 1));
			
		}
		
		barChart.getData().add(series);
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
		
		barChart.getData().clear();	
		lineChartExpo.getData().clear();
		lineChartPoisson.getData().clear();
		
		System.out.println("\n Donn�es vid�es");
	}
		
}
