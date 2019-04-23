/*
 * 3iL promotion 2017
 * 23/04/2019 
 */
package metier;

import java.util.Random;

/**
 * G�n�rateur de nombres al�atoires
 * <ul>
 *     <li>Loi uniforme</li>
 *     <li>Loi exponentielle</li>
 *     <li>Loi normale</li>
 *     <li>Loi de Poisson</li>
 * </ul>
 * @author Quentin MS, Florent Mamprin, Robin Henry
 * @version 1.0
 */
public class GenerationLois {
	
	/** Nombre de valeurs que l'on va g�n�rer */
	public final static int NB_VALEUR = 1000;
	
	
	/**
	 * G�naration de nombre al�atoire en suivant une loi uniforme
	 * @return  un nombre compris entre 0.0 et 1.0
	 */
	public static double loiUniforme() {
		// Cr�ation d'un objet ramdom afin de g�n�rer un nombre al�atoire
		Random rng = new Random();
			
		return rng.nextDouble();			
	}
	
	/**
	 * G�n�ration de nombre al�atoire en suivant une loi exponentielle
	 * @param lambda param�tre de la loi exponentielle
	 * @return un nombre al�atoire 
	 */
	public static double loiExponentielle(double lambda) {
        // G�n�ration d'un nombre al�atoire
		double x = loiUniforme();		
		
		return -(1/lambda) * Math.log(1 - x);	
	}
	
	/**
	 * G�n�ration de nombre al�atoire suivant une loi normale
	 * @param m est la moyenne de la loi normale
	 * @param s est l'�cart type de la loi normale
	 */
	public static double loiNormale(double m, double s) {
		// Initialisation de deux nombre suivant une loi uniforme
		double u = loiUniforme();
		double v = loiUniforme();
		
		return Math.sqrt(-2 * Math.log(u)) * Math.cos(2 * Math.PI * v);
	}
	
	/** G�n�ration de nombre al�atoire suivant une loi de poisson discrete
	 * @param lambda 
	 * @param T 
	 * @return res
	 */
	public static int loiPoisson(double lambda, double T) {				
		double res;
		double expo = 0;
		int compteur;
		
		for (compteur = 0 ; expo < T ; compteur++) {
			expo += loiExponentielle(lambda);
		}
		
		return compteur;
	}
	
	/**
	 * G�n�ration de nombre al�atoire suivant une loi de Weibull
	 * @param alpha 
	 * @param beta 
	 * @return res
	 */
	public static double loiWeibull(double alpha, double beta) {
		
		double res;
		
		double y = loiUniforme();
		
		res = Math.pow((-(Math.log(1-y)/Math.pow(alpha, beta))),1/beta);
		return res;	
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		double res, save;
		
		res = save = 0;
		
		for(int i = 0 ; i<NB_VALEUR ; i++) {
			System.out.println("UNIFORME : " + loiUniforme());
		}
		
		for(int i = 0 ; i<NB_VALEUR ; i++) {
			System.out.println("EXPONENTIELLE : " + loiExponentielle(1));
		}
		
		for(int i = 0 ; i<NB_VALEUR ; i++) {
			System.out.println("NORMALE : " + loiNormale(0,1));
			
			res= loiNormale(4,1);
			save += res;
		}
		
		System.out.println("Moyenne : " + save / NB_VALEUR);
		
	}
}
