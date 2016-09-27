package uade.ioo.model;

import java.util.Iterator;
import java.util.List;

import uade.ioo.exception.InvalidAccionException;

public abstract class Juego {

	private String nombre;
	protected Nivel nivelActual;
	private List<Nivel> niveles;

	public Juego(String nombre, List<Nivel> niveles) {
		this.nombre = nombre;
		this.niveles = niveles;
	}

	public void cargarNivel(int nivel) {
		Nivel n = null;
		for (Iterator<Nivel> it = niveles.iterator(); (it.hasNext() && n == null);) {
			Nivel aux = it.next();
			if (aux.getNumero() == nivel) {
				n = aux;
			}
		}
		this.nivelActual = n;
	}

	public String getNombre() {
		return nombre;
	}

	public void procesarAccion(String origen, String destino) throws InvalidAccionException {
		if (esAccionValida(origen, destino)) {
			this.nivelActual.decrementarMovimientosDisponibles();
		} else {
			throw new InvalidAccionException(origen, destino);
		}
	}

	public abstract boolean esAccionValida(String origen, String destino);

	public Nivel getNivelActual() {
		return nivelActual;
	}

	public boolean nivelCompleto() {
		return this.nivelActual.nivelCompleto();
	}

}
