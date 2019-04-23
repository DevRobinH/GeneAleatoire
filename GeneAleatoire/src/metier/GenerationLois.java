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
	 * Génaration de nombre aléatoire en suivant une loi uniforme
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
		
		return -(1/lambda) * Math.log(1 - x);	
	}
	
	/**
	 * Génération de nombre aléatoire suivant une loi normale
	 * @param m est la moyenne de la loi normale
	 * @param s est l'écart type de la loi normale
	 */
	public static double loiNormale(double m, double s) {
		
		double centreReduite;
		
		double u = loiUniforme();
		double v = loiUniforme();
		
		centreReduite = Math.sqrt(-2 * Math.log(u)) * Math.cos(2 * Math.PI * v);

		return centreReduite * s + m;
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
