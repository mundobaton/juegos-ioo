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

}
