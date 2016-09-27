package uade.ioo.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ranking {

	private List<ItemRanking> items;

	public Ranking() {
		this.items = new ArrayList<ItemRanking>();
	}

	public List<ItemRanking> obtenerRankingPorJuego(String juegoId) {
		List<ItemRanking> result = new ArrayList<ItemRanking>();
		for (Iterator<ItemRanking> it = items.iterator(); it.hasNext();) {
			ItemRanking ir = it.next();
			if (ir.getJuego().equals(juegoId)) {
				result.add(ir);
			}
		}
		return result;
	}

	public List<ItemRanking> obtenerRankingPorUsuario(String usuarioId) {
		List<ItemRanking> result = new ArrayList<ItemRanking>();
		for (Iterator<ItemRanking> it = items.iterator(); it.hasNext();) {
			ItemRanking ir = it.next();
			if (ir.getUser().getId().equals(usuarioId)) {
				result.add(ir);
			}
		}
		return result;
	}

	public ItemRanking obtenerRankingPorUsuarioJuego(String usuarioId, String juegoId) {
		List<ItemRanking> rank = this.obtenerRankingPorUsuario(usuarioId);
		ItemRanking result = null;
		for (Iterator<ItemRanking> it = rank.iterator(); (it.hasNext() && result == null);) {
			ItemRanking aux = it.next();
			if (aux.getJuego().equals(juegoId)) {
				result = aux;
			}
		}
		return result;
	}

	public void actualizarRanking(Usuario user, Juego juego, Nivel nivel) {
		ItemRanking ir = getItemRanking(user, juego);
		if (ir == null) {
			ir = new ItemRanking(user, juego, nivel);
			this.items.add(ir);
		} else {
			if (ir.getNivel().getNumero() < nivel.getNumero()) {
				ir.setNivel(nivel);
			}
		}
	}

	private ItemRanking getItemRanking(Usuario user, Juego juego) {
		ItemRanking result = null;
		for (Iterator<ItemRanking> it = this.items.iterator(); (it.hasNext() && result == null);) {
			ItemRanking aux = it.next();
			if (aux.getJuego().getNombre().equals(juego.getNombre()) && aux.getUser().getId().equals(user.getId())) {
				result = aux;
			}
		}
		return result;
	}

	public void eliminarRankingUsuario(String usuarioId) {
		for (Iterator<ItemRanking> it = this.items.iterator(); it.hasNext();) {
			ItemRanking ir = it.next();
			if (ir.getUser().getId().equals(usuarioId)) {
				it.remove();
			}
		}
	}
}