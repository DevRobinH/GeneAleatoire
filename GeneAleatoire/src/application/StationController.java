package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

	@FXML
	private Button btDemarrer;
	@FXML
	private Button btArreter;
	
	@FXML
	private Label lbDegres;
	@FXML
	private Label lbKhi2;

	@FXML
	private Label lbKhi2Theorique;

	@FXML
	private Label moyenneTheorique;
	@FXML
	private Label moyenneObservee;

	@FXML
	private TextField khiClasse1;
	@FXML
	private TextField khiClasse2;
	@FXML
	private TextField khiClasse3;
	@FXML
	private TextField khiClasse4;
	@FXML
	private TextField khiClasse5;
	@FXML
	private TextField khiClasse6;
	@FXML
	private TextField khiClasse7;
	@FXML
	private TextField khiClasse8;
	@FXML
	private TextField khiClasse9;
	@FXML
	private TextField khiClasse10;

	@FXML
	private LineChart lineChart;

	/** Nombre de jets de simulation*/
	private int nbJets = 100;

	/** Paramètre lambda de la fonction exp*/
	private double lambdaExp = 2;

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

		lbDegres.setText("null");
		lbKhi2.setText("null");
		lbKhi2Theorique.setText("null");
		moyenneTheorique.setText("null");
		moyenneObservee.setText("null");
		
		khiClasse1.setText(null);
		khiClasse2.setText(null);
		khiClasse3.setText(null);
		khiClasse4.setText(null);
		khiClasse5.setText(null);
		khiClasse6.setText(null);
		khiClasse7.setText(null);
		khiClasse8.setText(null);
		khiClasse9.setText(null);
		khiClasse10.setText(null);
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
		//tableau contenant le khi2 de chaque
		double khi[] = new double[10];
		// somme de toutes les valeurs générées
		double somme = 0;
		// total des khi2
		double totalKhi = 0;
		// Liste des valeurs générées
		List<Double> valAleas = new ArrayList<>();
		// moyenne des valeurs aléatoire créées
		double moyenne;
		//Espérance de la loi uniforme
		double esperance;

		//initialisation des champs texte de classe
		classe1.setText("0 à 0,1");
		classe2.setText("0,1 à 0,2");
		classe3.setText("0,2 à 0,3");
		classe4.setText("0,3 à 0,4");
		classe5.setText("0,4 à 0,5");
		classe6.setText("0,5 à 0,6");
		classe7.setText("0,6 à 0,7");
		classe8.setText("0,7 à 0,8");
		classe9.setText("0,8 à 0,9");
		classe10.setText("0,9 à 1,0");

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
		// calcul de la moyenne
		moyenne = somme/nbJets;
		// calcul de l'espérance
		Collections.sort(valAleas);
		esperance = (valAleas.get(0) + valAleas.get(valAleas.size()-1)) /2;
		System.out.println(moyenne);
		System.out.println(valAleas.get(0));
		System.out.println(valAleas.get(valAleas.size()-1));
		System.out.println(esperance);
		// obtention du n_th
		int n_th = this.nbJets/10;

		// Détermination du khi2 de chaque classe
		for(int i = 0; i < repartition.length; i++) {
			khi[i] = Math.pow((repartition[i]-n_th), 2) / n_th;
			totalKhi += khi[i];
		}

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

		// On va compléter les cases n_th de l'ihm
		khiClasse1.setText(Double.toString(khi[0]));
		khiClasse2.setText(Double.toString(khi[1]));
		khiClasse3.setText(Double.toString(khi[2]));
		khiClasse4.setText(Double.toString(khi[3]));
		khiClasse5.setText(Double.toString(khi[4]));
		khiClasse6.setText(Double.toString(khi[5]));
		khiClasse7.setText(Double.toString(khi[6]));
		khiClasse8.setText(Double.toString(khi[7]));
		khiClasse9.setText(Double.toString(khi[8]));
		khiClasse10.setText(Double.toString(khi[9]));

		// Ecriture du khi2_th
		lbKhi2.setText(Double.toString(Math.round(totalKhi)));
		lbKhi2Theorique.setText("16,92");

		// ecriture moyenne
		moyenneTheorique.setText(String.format("%.2f", esperance));
		moyenneObservee.setText(String.format("%.2f", moyenne));
		
		// ecriture degrès
		lbDegres.setText("9");
	}

	/**
	 * Génère des nombres aléatoire en suivant la loi Exponentielle
	 * 
	 * @param Evenement au clic du bouton "Loi Exponentielle"
	 */
	public void actionLoiExpo(ActionEvent evt){
		
		reinitialiser();
		
		System.out.println("\nAction loi Exponentielle");
		// tableau servant à obtenir n_obs
		int repartition[] = new int[8];
		//tableau contenant le khi2 de chaque
		double khi[] = new double[8];
		// somme de toutes les valeurs générées
		double somme = 0;
		// total des khi2
		double totalKhi = 0;
		// Liste des valeurs générées
		List<Double> valAleas = new ArrayList<>();
		// moyenne des valeurs aléatoire créées
		double moyenne;
		//Espérance de la loi uniforme
		double esperance;
		// tableau contenant les n_th
		int n_th[] = {9,8,14,12,18,16,9,12};

		//initialisation des champs texte de classe
		classe1.setText("0 à 0,05");
		classe2.setText("0,05 à 0,1");
		classe3.setText("0,1 à 0,2");
		classe4.setText("0,2 à 0,3");
		classe5.setText("0,3 à 0,5");
		classe6.setText("0,5 à 0,8");
		classe7.setText("0,8 à 1,1");
		classe8.setText("1,1 à 10");


		//génération des nombres aléatoires
		for(int i =0; i < this.nbJets; i++) {
			// Génération d'un nombre aléatoire
			double nbAlea = GenerationLois.loiExponentielle(lambdaExp);
			// determination de n_obs
			if (nbAlea < 0.05 ) {
				repartition[0]++;
			} else if (nbAlea < 0.1 ) {
				repartition[1]++;
			} else if (nbAlea < 0.2 ) {
				repartition[2]++;
			} else if (nbAlea < 0.3 ) {
				repartition[3]++; 
			} else if (nbAlea < 0.5 ) {
				repartition[4]++;
			} else if (nbAlea < 0.8 ) {
				repartition[5]++;
			} else if (nbAlea < 1.1 ) {
				repartition[6]++;
			} else  {
				repartition[7]++;
			}

			// ajout du nombre créé à la liste
			valAleas.add(nbAlea);
			// calcul de la somme
			somme += nbAlea;
		}
		// calcul de la moyenne
		moyenne = somme/nbJets;
		// calcul de l'espérance
		esperance = 1 / this.lambdaExp;
		System.out.println(lambdaExp);
		System.out.println(moyenne);
		System.out.println(esperance);

		// Détermination du khi2 de chaque classe
		for(int i = 0; i < repartition.length; i++) {
			khi[i] = Math.pow((repartition[i]- n_th[i]), 2) / n_th[i];
			totalKhi += khi[i];
		}

		// On va compléter les cases n_obs de l'ihm
		obs1.setText(Integer.toString(repartition[0]));
		obs2.setText(Integer.toString(repartition[1]));
		obs3.setText(Integer.toString(repartition[2]));
		obs4.setText(Integer.toString(repartition[3]));
		obs5.setText(Integer.toString(repartition[4]));
		obs6.setText(Integer.toString(repartition[5]));
		obs7.setText(Integer.toString(repartition[6]));
		obs8.setText(Integer.toString(repartition[7]));

		// On va compléter les cases n_th de l'ihm
		th1.setText("9");
		th2.setText("8");
		th3.setText("14");
		th4.setText("12");
		th5.setText("18");
		th6.setText("16");
		th7.setText("9");
		th8.setText("12");

		// On va compléter les cases n_th de l'ihm
		khiClasse1.setText(Double.toString(khi[0]));
		khiClasse2.setText(Double.toString(khi[1]));
		khiClasse3.setText(Double.toString(khi[2]));
		khiClasse4.setText(Double.toString(khi[3]));
		khiClasse5.setText(Double.toString(khi[4]));
		khiClasse6.setText(Double.toString(khi[5]));
		khiClasse7.setText(Double.toString(khi[6]));
		khiClasse8.setText(Double.toString(khi[7]));

		// Ecriture du khi2_th
		lbKhi2.setText(Double.toString(Math.round(totalKhi)));
		lbKhi2Theorique.setText("14,07");

		// ecriture moyenne
		moyenneTheorique.setText(String.format("%.2f", esperance));
		moyenneObservee.setText(String.format("%.2f", moyenne));

		// ecriture degrès
		lbDegres.setText("7");
	}

	/**
	 * Génère des nombres aléatoire en suivant la loi Normale
	 * 
	 * @param Evenement au clic du bouton "Loi Normale"
	 */
	public void actionLoiNormale(ActionEvent evt){

		// On vide au préalable les champs
		reinitialiser();
		System.out.println("\nAction loi Normale");
		// tableau servant à obtenir n_obs
		int repartition[] = new int[7];
		//tableau contenant le khi2 de chaque
		double khi[] = new double[7];
		// somme de toutes les valeurs générées
		double somme = 0;
		// total des khi2
		double totalKhi = 0;
		// Liste des valeurs générées
		List<Double> valAleas = new ArrayList<>();
		// moyenne des valeurs aléatoire créées
		double moyenne;
		//Espérance de la loi uniforme
		double esperance;
		// tableau contenant les n_th
		int n_th[] = {16,12,15,16,15,12,16};

		// initialisation des champs texte de classe
		classe1.setText("<- 1");
		classe2.setText("-1 à -0.6");
		classe3.setText("-0,6 à -0,2");
		classe4.setText("-0,2 à 0,2");
		classe5.setText("0,2 à 0,6");
		classe6.setText("0,6 à 1");
		classe7.setText("> 1");		

		//génération des nombres aléatoires
		for(int i =0; i < this.nbJets; i++) {
			// Génération d'un nombre aléatoire
			double nbAlea = GenerationLois.loiNormale();
			// determination de n_obs
			if (nbAlea < -1 ) {
				repartition[0]++;
			} else if (nbAlea < -0.6 ) {
				repartition[1]++;
			} else if (nbAlea < -0.2 ) {
				repartition[2]++;
			} else if (nbAlea < 0.2 ) {
				repartition[3]++; 
			} else if (nbAlea < 0.6 ) {
				repartition[4]++;
			} else if (nbAlea < 1 ) {
				repartition[5]++;
			} else {
				repartition[6]++;
			}

			// ajout du nombre créé à la liste
			valAleas.add(nbAlea);
			// calcul de la somme
			somme += nbAlea;
		}

		// calcul de la moyenne
		moyenne = somme/nbJets;
		// calcul de l'espérance
		esperance = 1 / this.lambdaExp;
		System.out.println(lambdaExp);
		System.out.println(moyenne);
		System.out.println(esperance);

		// Détermination du khi2 de chaque classe
		for(int i = 0; i < repartition.length; i++) {
			khi[i] = Math.pow((repartition[i]- n_th[i]), 2) / n_th[i];
			totalKhi += khi[i];
		}

		// On va compléter les cases n_obs de l'ihm
		obs1.setText(Integer.toString(repartition[0]));
		obs2.setText(Integer.toString(repartition[1]));
		obs3.setText(Integer.toString(repartition[2]));
		obs4.setText(Integer.toString(repartition[3]));
		obs5.setText(Integer.toString(repartition[4]));
		obs6.setText(Integer.toString(repartition[5]));
		obs7.setText(Integer.toString(repartition[6]));

		// On va compléter les cases n_th de l'ihm
		th1.setText("16");
		th2.setText("12");
		th3.setText("15");
		th4.setText("16");
		th5.setText("15");
		th6.setText("12");
		th7.setText("16");

		// On va compléter les cases n_th de l'ihm
		khiClasse1.setText(Double.toString(khi[0]));
		khiClasse2.setText(Double.toString(khi[1]));
		khiClasse3.setText(Double.toString(khi[2]));
		khiClasse4.setText(Double.toString(khi[3]));
		khiClasse5.setText(Double.toString(khi[4]));
		khiClasse6.setText(Double.toString(khi[5]));
		khiClasse7.setText(Double.toString(khi[6]));

		// ecriture moyenne
		moyenneTheorique.setText("0,00");
		moyenneObservee.setText(String.format("%.2f", moyenne));

		// Khi² théorique
		lbKhi2.setText(Double.toString(Math.round(totalKhi)));
		lbKhi2Theorique.setText("12,59");
		
		// ecriture degrès
		lbDegres.setText("6");
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

	
	/**
	 * Exécute le programme
	 * 
	 * @param Evenement au clic du bouton "Démarrer"
	 */
	public void actionDemarrer(ActionEvent evt){

		System.out.println("\n bt Démarrer");
	}
	
	/**
	 * Stoppe le programme
	 * 
	 * @param Evenement au clic du bouton "Arrêter"
	 */
	public void actionArreter(ActionEvent evt){

		System.out.println("\n bt Arrêter");
	}
	
}
