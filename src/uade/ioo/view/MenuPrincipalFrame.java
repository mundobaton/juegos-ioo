package uade.ioo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import uade.ioo.controller.Controlador;
import uade.ioo.model.Juego;

public class MenuPrincipalFrame extends BaseViewFrame {

	private static final long serialVersionUID = -7672262409856357016L;

	public MenuPrincipalFrame(Controlador controller) {
		super(controller);
	}

	protected void init() {
		List<Juego> juegos = this.controller.getJuegos();
		for (int i = 0; i < juegos.size(); i++) {
			int offsetY = 30 * i;
			Juego juego = juegos.get(i);
			JButton juegoButton = new JButton(juego.getNombre());
			juegoButton.setBounds(150, 60 + offsetY, 200, 30);
			juegoButton.addActionListener(new GameSelectActionListener(this));
			add(juegoButton);
		}

		JButton preferenciasButton = new JButton();
		preferenciasButton.setText("Preferencias");
		preferenciasButton.setBounds(20, 450, 130, 20);
		preferenciasButton.addActionListener(new PreferenciasActionListener());
		add(preferenciasButton);

		JButton rankingButton = new JButton();
		rankingButton.setText("Ranking");
		rankingButton.setBounds(160, 450, 130, 20);
		rankingButton.addActionListener(new RankingActionListener());
		add(rankingButton);

		JButton salirButton = new JButton();
		salirButton.setText("Salir");
		salirButton.setBounds(350, 450, 130, 20);
		salirButton.addActionListener(new SalirActionListener());
		add(salirButton);
	}

	private void salir() {
		this.controller.logout();
		LoginFrame login = new LoginFrame(controller);
		this.setVisible(false);
		login.setVisible(true);
	}

	public class GameSelectActionListener implements ActionListener {

		private MenuPrincipalFrame frame;

		public GameSelectActionListener(MenuPrincipalFrame frame) {
			this.frame = frame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String gameName = ((JButton) e.getSource()).getText();
			controller.cargarJuego(gameName);
			if (gameName.equals("rompecabezas")) {
				RompecabezasFrame rf = new RompecabezasFrame(controller);
				this.frame.setVisible(false);
				rf.setVisible(true);
			}
		}
	}

	public class SalirActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			salir();
		}
	}

	public class PreferenciasActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			showPreferencias();
		}

	}

	public class RankingActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			showRanking();
		}
	}

	private void showPreferencias() {
		PreferenciasFrame prefs = new PreferenciasFrame(controller);
		this.setVisible(false);
		prefs.setVisible(true);
	}

	private void showRanking() {
		RankingFrame ranking = new RankingFrame(controller);
		this.setVisible(false);
		ranking.setVisible(true);
	}
}
