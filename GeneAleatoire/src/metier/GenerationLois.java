/*
 * 3iL promotion 2017
 * 23/04/2019 
 */
package metier;

import java.util.Random;

/**
 * Générateur de nombres aléatoires
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
	
	/** Nombre de valeurs que l'on va générer */
	public final static int NB_VALEUR = 1000;
	
	
	/**
	 * Génération de nombre aléatoire en suivant une loi uniforme
	 * @return  un nombre compris entre 0.0 et 1.0
	 */
	public static double loiUniforme() {
		// Création d'un objet ramdom afin de générer un nombre aléatoire
		Random rng = new Random();
			
		return rng.nextDouble();			
	}
	
	/**
	 * Génération de nombre aléatoire en suivant une loi exponentielle
	 * @param lambda paramètre de la loi exponentielle
	 * @return un nombre aléatoire 
	 */
	public static double loiExponentielle(double lambda) {
        // Génération d'un nombre aléatoire
		double x = loiUniforme();		
		
		return -(1/lambda) * Math.log(x);	
	}
	
	/**
	 * Génération de nombre aléatoire suivant une loi normale
	 * @return un nombre aléatoire
	 */
	public static double loiNormale() {
		// Initialisation de deux nombre suivant une loi uniforme
		double u = loiUniforme();
		double v = loiUniforme();
		
		return Math.sqrt(-2 * Math.log(u)) * Math.cos(2 * Math.PI * v);
	}
	
	/** Génération de nombre aléatoire suivant une loi de poisson discrete
	 * @param lambda paramètre de la loi exponentielle
	 * @param T interval de temps
	 */
	public static int loiPoisson(double lambda, double T) {				
		double expo = 0;
		int compteur;
		
		for (compteur = 0 ; expo < T ; compteur++) {
			expo += loiExponentielle(lambda);
		}
		return compteur;
	}
	
	/**
	 * Génération de nombre aléatoire suivant une loi de Weibull
	 * @param alpha 
	 * @param beta 
	 */
	public static double loiWeibull(double alpha, double beta) {
		// Génération d'un nombre aléatoire suivant une loi uniforme
		double y = loiUniforme();
		
		return Math.pow((-(Math.log(1-y)/Math.pow(alpha, beta))),1/beta);	
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
			System.out.println("NORMALE : " + loiNormale());
			
			res= loiNormale();
			save += res;
		}
		
		System.out.println("Moyenne : " + save / NB_VALEUR);
		
	}
}
