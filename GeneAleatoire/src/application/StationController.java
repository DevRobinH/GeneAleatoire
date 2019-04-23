package application;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import metier.GenerationLois;

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
		// On vide au préalable les champs
		reinitialiser();
		System.out.println("\nAction loi uniforme");
        // tableau servant à obtenir n_obs
		int repartition[] = new int[10];
		
		// somme de toutes les valeurs générées
		int somme = 0;
		// Liste des valeurs générées
		List<Double> valAleas = new ArrayList<>();
		
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
		
		//génération des nombres aléatoires
		for(int i =0; i < this.nbJets; i++) {
			// Génération d'un nombre aléatoire
			double nbAlea = GenerationLois.loiUniforme();
			// determination de n_obs
			if (nbAlea < 0.1 ) {
				repartition[0]++;
			} else if (nbAlea < 0.2 ) {
				repartition[1]++;
			} else if (nbAlea < 0.3 ) {
				repartition[2]++;
			} else if (nbAlea < 0.4 ) {
				repartition[3]++; 
			} else if (nbAlea < 0.5 ) {
				repartition[4]++;
			} else if (nbAlea < 0.6 ) {
				repartition[5]++;
			} else if (nbAlea < 0.7 ) {
				repartition[6]++;
			} else if (nbAlea < 0.8 ) {
				repartition[7]++;
			} else if (nbAlea < 0.9 ) {
				repartition[8]++;
			} else {
				repartition[9]++;
			}
			
			// ajout du nombre créé à la liste
			valAleas.add(nbAlea);
			// calcul de la somme
			somme += nbAlea;
		}
		
		// obtention du n_th
		int n_th = this.nbJets/10;
		
		// On va compléter les cases n_obs de l'ihm
		obs1.setText(Integer.toString(repartition[0]));
		obs2.setText(Integer.toString(repartition[1]));
		obs3.setText(Integer.toString(repartition[2]));
		obs4.setText(Integer.toString(repartition[3]));
		obs5.setText(Integer.toString(repartition[4]));
		obs6.setText(Integer.toString(repartition[5]));
		obs7.setText(Integer.toString(repartition[6]));
		obs8.setText(Integer.toString(repartition[7]));
		obs9.setText(Integer.toString(repartition[8]));
		obs10.setText(Integer.toString(repartition[9]));
		
		// On va compléter les cases n_th de l'ihm
		th1.setText(Integer.toString(n_th));
		th2.setText(Integer.toString(n_th));
		th3.setText(Integer.toString(n_th));
		th4.setText(Integer.toString(n_th));
		th5.setText(Integer.toString(n_th));
		th6.setText(Integer.toString(n_th));
		th7.setText(Integer.toString(n_th));
		th8.setText(Integer.toString(n_th));
		th9.setText(Integer.toString(n_th));
		th10.setText(Integer.toString(n_th));
	}

	/**
	 * Génère des nombres aléatoire en suivant la loi Exponentielle
	 * 
	 * @param Evenement au clic du bouton "Loi Exponentielle"
	 */
	public void actionLoiExpo(ActionEvent evt){
		System.out.println("\nAction loi Exponentielle");
		//initialisation des champs texte de classe
		classe1.setText("0-0,05");
		classe2.setText("0,05-0,1");
		classe3.setText("0,1-0,2");
		classe4.setText("0,2-0,3");
		classe5.setText("0,3-0,5");
		classe6.setText("0,5-0,8");
		classe7.setText("0,8-1,1");
		classe8.setText("1,1-10");
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
