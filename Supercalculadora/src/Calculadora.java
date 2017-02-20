import java.util.Scanner;

public class Calculadora {

	public static Lista sumar(String num1, String num2) {
		Lista numero1 = rellenar(num1);
		Lista numero2 = rellenar(num2);
		
		return Lista.sumar(numero1, numero2);
	}
	
	public static Lista restar(String num1, String num2) {
		Lista numero1 = rellenar(num1);
		Lista numero2 = rellenar(num2);
		
		if (numero1.esMayorQue(numero2)) {
			return Lista.restar(numero1, numero2);
		} else if (numero2.esMayorQue(numero1)) {
			return Lista.restar(numero2, numero1);
		}
		
		Lista cero = new Lista();
		cero.agregar(0);
		
		return cero;
	}
	
	public static Lista multiplicar(String num1, String num2) {
		Lista numero1 = rellenar(num1);
		Lista numero2 = rellenar(num2);
		
		return Lista.multiplicar(numero1, numero2);
	}
	
	public static Lista[] dividir(String num1, String num2) {
		return null;
	}
	
	public static boolean esMayor(String num1, String num2) {
		Lista numero1 = rellenar(num1);
		Lista numero2 = rellenar(num2);
		
		return numero1.esMayorQue(numero2);
	}
	
	private static Lista rellenar(String numero) {
		Lista lista = new Lista();
		
		Scanner sc = new Scanner(numero);
		sc.useDelimiter("");
		
		while (sc.hasNext()) {
			lista.agregar(Integer.parseInt(sc.next()));
		}
		
		sc.close();
		
		return lista;
	}
}