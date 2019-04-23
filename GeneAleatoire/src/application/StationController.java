package application;

import metier.GenerationLois;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class StationController {

	@FXML
	private TextField jets;
	@FXML
	private TextField lambda;

	@FXML
	private TextField classe1;
	@FXML
	private TextField classe2;
	@FXML
	private TextField classe3;
	@FXML
	private TextField classe4;
	@FXML
	private TextField classe5;
	@FXML
	private TextField classe6;
	@FXML
	private TextField classe7;
	@FXML
	private TextField classe8;
	@FXML
	private TextField classe9;
	@FXML
	private TextField classe10;

	@FXML
	private TextField obs1;
	@FXML
	private TextField obs2;
	@FXML
	private TextField obs3;
	@FXML
	private TextField obs4;
	@FXML
	private TextField obs5;
	@FXML
	private TextField obs6;
	@FXML
	private TextField obs7;
	@FXML
	private TextField obs8;
	@FXML
	private TextField obs9;
	@FXML
	private TextField obs10;

	@FXML
	private TextField th1;
	@FXML
	private TextField th2;
	@FXML
	private TextField th3;
	@FXML
	private TextField th4;
	@FXML
	private TextField th5;
	@FXML
	private TextField th6;
	@FXML
	private TextField th7;
	@FXML
	private TextField th8;
	@FXML
	private TextField th9;
	@FXML
	private TextField th10;

	@FXML
	private Button btUniforme;
	@FXML
	private Button btExpo;
	@FXML
	private Button btNormale;
	@FXML
	private Button btPoisson;

	/** Nombre de jets de simulation*/
	private int nbJets = 100;
	
	/** Paramètre lambda de la fonction exp*/
	private int lambdaExp = 2;

	/**
	 * Réinitialise tous les champs
	 */
	public void reinitialiser(){

		// Vide les classes
		classe1.setText(null);
		classe2.setText(null);
		classe3.setText(null);
		classe4.setText(null);
		classe5.setText(null);
		classe6.setText(null);
		classe7.setText(null);
		classe8.setText(null);
		classe9.setText(null);
		classe10.setText(null);

		// Vide les valeurs observées
		obs1.setText(null);
		obs2.setText(null);
		obs3.setText(null);
		obs4.setText(null);
		obs5.setText(null);
		obs6.setText(null);
		obs7.setText(null);
		obs8.setText(null);
		obs9.setText(null);
		obs10.setText(null);

		// Vide les valeurs théoriques
		th1.setText(null);
		th2.setText(null);
		th3.setText(null);
		th4.setText(null);
		th5.setText(null);
		th6.setText(null);
		th7.setText(null);
		th8.setText(null);
		th9.setText(null);
		th10.setText(null);
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
	 * Récupère lambda, le paramètre pour la loi exponentielle
	 */
	public void recupLambda(){

		// On récupère le champ de lambda
		String recup = lambda.getText();

		// Si le champ à récupérer n'est pas null
		if (!recup.equals("")){

			// On le récupère
			lambdaExp = Integer.parseInt(recup);

			System.out.println("\nLambda:");
			System.out.println(this.lambdaExp);				
		}
		else{
			System.out.println("\nErreur récupération Lambda");
		}
	}

	/**
	 * Définit le nombre de jets
	 * 
	 * @param Evenement au clic du bouton "Simuler"
	 */
	public void actionSimuler(ActionEvent evt){

		recupNbJets();
		recupLambda();

		System.out.println("\nAction Simuler");
	}

	/**
	 * Génère des nombres aléatoire en suivant la loi Uniforme
	 * 
	 * @param Evenement au clic du bouton "Loi Uniforme"
	 */
	public void actionLoiUniforme(ActionEvent evt){
		System.out.println("\nAction loi uniforme");

		//initialisation des champs texte de classe
		classe1.setText("0-0,1");
		classe2.setText("0,1-0,2");
		classe3.setText("0,2-0,3");
		classe4.setText("0,3-0,4");
		classe5.setText("0,4-0,5");
		classe6.setText("0,5-0,6");
		classe7.setText("0,6-0,7");
		classe8.setText("0,7-0,8");
		classe9.setText("0,8-0,9");
		classe10.setText("0,9-1,0");
	}

	/**
	 * Génère des nombres aléatoire en suivant la loi Exponentielle
	 * 
	 * @param Evenement au clic du bouton "Loi Exponentielle"
	 */
	public void actionLoiExpo(ActionEvent evt){
		
		// Tout les cases sont vidées
		reinitialiser();
		
		System.out.println("\nAction loi Exponentielle");
		
		// Initialisation des champs texte de classe
		classe1.setText("0-0,05");
		classe2.setText("0,05-0,1");
		classe3.setText("0,1-0,2");
		classe4.setText("0,2-0,3");
		classe5.setText("0,3-0,5");
		classe6.setText("0,5-0,8");
		classe7.setText("0,8-1,1");
		classe8.setText("1,1-10");
		
		// Initialisation des champs texte de n_th
		th1.setText("9");
		th2.setText("8");
		th3.setText("14");
		th4.setText("12");
		th5.setText("18");
		th6.setText("16");
		th7.setText("9");
		th8.setText("12");
		
		// Génération des n_obs
		
	}

	/**
	 * Génère des nombres aléatoire en suivant la loi Normale
	 * 
	 * @param Evenement au clic du bouton "Loi Normale"
	 */
	public void actionLoiNormale(ActionEvent evt){
		System.out.println("\nAction loi Normale");
		//initialisation des champs texte de classe
		classe1.setText("<- 1,0");
		classe2.setText("-1");
		classe3.setText("-0,6");
		classe4.setText("-0,2");
		classe5.setText("0,2");
		classe6.setText("0,6");
		classe7.setText("> 1");
	}

	/**
	 * Génère des nombres aléatoire en suivant la loi Poisson
	 * 
	 * @param Evenement au clic du bouton "Loi Poisson"
	 */
	public void actionLoiPoisson(ActionEvent evt){
		System.out.println("\nAction loi Poisson");
	}
	/**
	 * @return the nbJets
	 */
	public int getNbJets() {
		return nbJets;
	}

	/**
	 * @param nbJets the nbJets to set
	 */
	public void setNbJets(int nbJets) {
		this.nbJets = nbJets;
	}


}
