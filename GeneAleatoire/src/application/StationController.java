package application;

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
	
	/**
	 * R�initialise tous les champs
	 */
	public void reinitialiser(ActionEvent evt){
		
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
		
		// Vide les valeurs observ�es
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
		
		// Vide les valeurs th�oriques
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
	 * R�cup�re le nombre de jets
	 */
	public int recupNbJets(){
		
		// On r�cup�re le champ du nombre de jets
		String recup = jets.getText();
		
		int nbJetsChoisis = 0;
		
		// Si le champ � r�cup�rer n'est pas null
		if (recup != ""){
			
			// On le r�cup�re
			nbJetsChoisis = Integer.parseInt(recup);
			
			System.out.println("\nNombre de jets :");
			System.out.println(nbJetsChoisis);
			return nbJetsChoisis;
		}
		else{
			System.out.println("\nErreur r�cup�ration nombre de jets");
			return 0;
		}

	}
	
	/**
	 * R�cup�re lambda, le param�tre pour la loi exponentielle
	 */
	public int recupLambda(){
		
		// On r�cup�re le champ de lambda
		String recup = lambda.getText();
		
		int lambdaChoisi = 0;
		
		// Si le champ � r�cup�rer n'est pas null
		if (recup != ""){
			
			// On le r�cup�re
			lambdaChoisi = Integer.parseInt(recup);
			
			System.out.println("\nLambda:");
			System.out.println(lambdaChoisi);				
			
			return lambdaChoisi;
		}
		else{
			System.out.println("\nErreur r�cup�ration Lambda");
			return 0;
		}
	}
	
	/**
	 * D�finit le nombre de jets
	 * 
	 * @param Evenement au clic du bouton "Simuler"
	 */
	public void actionSimuler(ActionEvent evt){
		
		int nbJets = recupNbJets();
		int lambda = recupLambda();
		
		System.out.println("\nAction Simuler");
	}
	
	/**
	 * G�n�re des nombres al�atoire en suivant la loi Uniforme
	 * 
	 * @param Evenement au clic du bouton "Loi Uniforme"
	 */
	public void actionLoiUniforme(ActionEvent evt){
		System.out.println("\nAction loi uniforme");
	}
	
	/**
	 * G�n�re des nombres al�atoire en suivant la loi Exponentielle
	 * 
	 * @param Evenement au clic du bouton "Loi Exponentielle"
	 */
	public void actionLoiExpo(ActionEvent evt){
		System.out.println("\nAction loi Exponentielle");
	}
	
	/**
	 * G�n�re des nombres al�atoire en suivant la loi Normale
	 * 
	 * @param Evenement au clic du bouton "Loi Normale"
	 */
	public void actionLoiNormale(ActionEvent evt){
		System.out.println("\nAction loi Normale");
	}
	
	/**
	 * G�n�re des nombres al�atoire en suivant la loi Poisson
	 * 
	 * @param Evenement au clic du bouton "Loi Poisson"
	 */
	public void actionLoiPoisson(ActionEvent evt){
		System.out.println("\nAction loi Poisson");
	}
	

}
