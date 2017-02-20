public class Lista {

	private Nodo inicio;
	
	public Lista() {
	
	}
	
	public Lista(String numero) {
		
	}
	
	public boolean estaVacia() {
		return inicio == null;
	} 
	
	public static Lista sumar(Lista num1, Lista num2) {
		Lista resultado = new Lista();
		resultado.agregarInicio(0);
		
		equilibrar(num1, num2);
		
		Nodo aux1 = num1.getFinal();
		Nodo aux2 = num2.getFinal();
		
		while (aux1 != null && aux2 != null) {
			int suma = aux1.getDato() + aux2.getDato();
			
			if (resultado.inicio.getDato() == 1) {
				if (suma >= 9) {
					resultado.inicio.setDato(suma - 9);
					resultado.agregarInicio(1);
				} else {
					resultado.inicio.setDato(suma + 1);
					resultado.agregarInicio(0);
				}
				
			}
			else {
				if (suma >= 10) {
					resultado.inicio.setDato(suma - 10);
					resultado.agregarInicio(1);
				}
				else if (suma < 10 && suma >= 0) {
					resultado.inicio.setDato(suma);
					resultado.agregarInicio(0);
				} else {
					resultado.inicio.setDato(suma + 10);
					aux1.getAnterior().setDato(aux1.getAnterior().getDato() - 1);
					resultado.agregarInicio(0);
				}
			}

			aux1 = aux1.getAnterior();
			aux2 = aux2.getAnterior();
		}
		
		resultado.trim();
		return resultado;
	}
	
	public static Lista restar(Lista num1, Lista num2) {
		equilibrar(num1, num2);
	
		num2.cambiarSignos();

		return Lista.sumar(num1, num2);		
	}
	
	public static Lista multiplicar(Lista num1, Lista num2) {
		Nodo aux1 = num1.getFinal();
		Nodo aux2 = num2.getFinal();
		
		Lista resultado = new Lista();
		resultado.agregar(0);
		
		Lista parcial = new Lista();
		parcial.agregar(0);
		
		int ciclos = 0;
		
		while (aux1 != null) {
			while (aux2 != null) {
				int multiplicacion = aux1.getDato() * aux2.getDato();
				
				if (multiplicacion < 10) {
					parcial.inicio.setDato(parcial.inicio.getDato() + multiplicacion);
					parcial.agregarInicio(0);
				} else {
					parcial.inicio.setDato(parcial.inicio.getDato() + multiplicacion % 10); 
					parcial.agregarInicio((multiplicacion % 100) / 10);
				}
				
				aux2 = aux2.getAnterior();
			}
			
			for (int i = 0; i < ciclos; i++) {
				parcial.agregar(0);
			}
			
			equilibrar(parcial, resultado);
			
			resultado = sumar(parcial, resultado);
			
			parcial = new Lista();
			parcial.agregar(0);
			
			ciclos++;
			aux1 = aux1.getAnterior();
			aux2 = num2.getFinal();
		}
		
		resultado.trim();
		return resultado;
	}
	
	private static void equilibrar(Lista num1, Lista num2) {
		while (num1.tamano() > num2.tamano()) {
			num2.agregarInicio(0);
		}
		
		while (num2.tamano() > num1.tamano()) {
			num1.agregarInicio(0);
		}
		
	}
	
	//agregar al final
	public void agregar(int numero) {
		Nodo nuevo = new Nodo(numero);
		
		if (estaVacia()) {
			inicio = nuevo;
			return;
		}
		
		Nodo aux = inicio;
		while (aux.getSiguiente() != null) {
			aux = aux.getSiguiente();
		}
		
		aux.setSiguiente(nuevo);
		nuevo.setAnterior(aux);
	}
	
	public void agregarInicio(int numero) {
		Nodo nuevo = new Nodo(numero);
		
		if (!estaVacia()) {
			nuevo.setSiguiente(inicio);
			inicio.setAnterior(nuevo);
		}
		
		inicio = nuevo;
	}
	
	public int tamano() {
		int elementos = 0;
		
		Nodo aux = inicio;
		while (aux != null) {
			elementos++;
			aux = aux.getSiguiente();
		}
		
		return elementos;
	}
	
	private Nodo getFinal() {
		Nodo aux = inicio;
		
		while (aux != null && aux.getSiguiente() != null) {
			aux = aux.getSiguiente();
		}
		
		return aux;
	}
	
	//elimina ceros a la izquierda
	private void trim() {
		Nodo aux = inicio;
		while (aux != null && aux.getDato() == 0) {
			eliminarInicio();
			aux = aux.getSiguiente();
		}
		
	}
	
	public boolean esMayorQue(Lista otro) {
		equilibrar(this, otro);
		
		Nodo aux = inicio;
		Nodo aux2 = otro.inicio;
		
		while (aux != null && aux2 != null) {
			if (aux.getDato() != aux2.getDato()) {
				return aux.getDato() > aux2.getDato();
			}
			
			aux = aux.getSiguiente();
			aux2 = aux2.getSiguiente();
		}
		
		return false;
	}
	
	private void eliminarInicio() {
		if(inicio.getSiguiente() != null) {
			inicio.getSiguiente().setAnterior(null);
		}
		
		inicio = inicio.getSiguiente();
	}
	
	public void cambiarSignos() {
		Nodo aux = inicio;
		
		while (aux != null) {
			aux.setDato(aux.getDato() * -1);
			aux = aux.getSiguiente();
		}
	}
	
	@Override
	public String toString() {
		String str = "";
		
		Nodo aux = inicio;
		while (aux != null) {
			str += aux.getDato();
			aux = aux.getSiguiente();
		}
		
		return str;
	}
}