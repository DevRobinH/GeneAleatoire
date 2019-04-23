/**
 * G�n�ration de nombre al�atoire
 */
package metier;

import java.util.Random;

/**
 * 
 * @author micka�l
 */
public class GenerationLois {
	
	/** Nombre de valeurs � g�n�r� dans les diff�rentes lois */
	protected final static int NB_VALEUR = 1000;
	
	
	/**
	 * G�naration d'une loi uniforme
	 * @return 
	 */
	public static double LoiUniforme() {
		
		Random rng = new Random();
		
		double res = rng.nextDouble(); 
				
		return res;
			
	}
	
	/**
	 * G�n�ration d'une loi exponentielle
	 * @param a
	 * @return
	 */
	public static double LoiExponentielle(double l) {
			
		double res;
		
		double x = LoiUniforme();
		
		res = -(1/l) * Math.log(x);		
		
		return res;
		
	}
	
	/**
	 * G�n�ration d'une loi normale
	 * @param m est la moyenne de la loie normale
	 * @param s est l'�cart type de la loie normale
	 */
	public static double LoiNormale(double m, double s) {
		
		double centre_reduite;
		double res;
		
		double u = LoiUniforme();
		double v = LoiUniforme();
		
		centre_reduite = Math.sqrt(-2 * Math.log(u)) * Math.cos(2 * Math.PI * v);
		
		res = centre_reduite * s + m;
		
		return res;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		double res, save;
		
		res = save = 0;
		
		for(int i = 0 ; i<NB_VALEUR ; i++) {
			System.out.println("UNIFORME : " + LoiUniforme());
		}
		
		for(int i = 0 ; i<NB_VALEUR ; i++) {
			System.out.println("EXPONENTIELLE : " + LoiExponentielle(1));
		}
		
		for(int i = 0 ; i<NB_VALEUR ; i++) {
			System.out.println("NORMALE : " + LoiNormale(0,1));
			
			res= LoiNormale(4,1);
			save += res;
		}
		
		System.out.println("Moyenne : " + save / NB_VALEUR);
		
	}

}
