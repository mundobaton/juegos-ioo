package uade.ioo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import uade.ioo.controller.Controlador;
import uade.ioo.exception.InvalidAccionException;
import uade.ioo.model.Juego;
import uade.ioo.model.Pieza;

public class RompecabezasFrame extends BaseViewFrame {

	private static final long serialVersionUID = -8819656602837330930L;
	private ButtonValue origen;
	private ButtonValue destino;

	public RompecabezasFrame(Controlador controller) {
		super(controller);
	}

	@Override
	protected void init() {
		this.setBounds(100, 100, 800, 600);
		Juego j = controller.getJuegoActual();
		List<Pieza> piezas = j.getNivelActual().getPiezas();
		Collections.shuffle(piezas);
		int width = 0, height = 0;
		for (int i = 0; i < piezas.size(); i++) {
			Pieza p = piezas.get(i);
			JButton button = new JButton();
			ImageIcon icon = new ImageIcon(getClass().getResource(p.getPath()));
			width = icon.getIconWidth();
			height = icon.getIconHeight();
			int offsetX = icon.getIconWidth() * i;
			button.setIcon(icon);
			button.setBounds(0 + offsetX, 20, width, height);
			ButtonValue bv = new ButtonValue(button, p.getNombre());
			button.addActionListener(new OriginActionListener(this, bv));
			add(button);
		}

		// Calculo la dimension de la matriz cuadrada
		int dim = (int) Math.sqrt(piezas.size());
		for (int i = 0; i < dim; i++) {
			for (int k = 0; k < dim; k++) {
				JButton button = new JButton();
				int offsetX = i * width;
				int offsetY = k * height;
				button.setBounds(200 + offsetX, 200 + offsetY, width, height);
				ButtonValue bv = new ButtonValue(button, k + "_" + i);
				button.addActionListener(new DestinationActionListener(this, bv));
				add(button);
			}
		}

		JButton volverButton = new JButton();
		volverButton.setText("Volver");
		volverButton.setBounds(650, 550, 130, 20);
		volverButton.addActionListener(new VolverActionListener());
		add(volverButton);
	}

	private void volver() {
		MenuPrincipalFrame menu = new MenuPrincipalFrame(controller);
		this.setVisible(false);
		menu.setVisible(true);
	}

	public class OriginActionListener implements ActionListener {
		private RompecabezasFrame frame;
		private ButtonValue bv;

		public OriginActionListener(RompecabezasFrame frame, ButtonValue bv) {
			this.frame = frame;
			this.bv = bv;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (frame.origen != null) {
				frame.origen.button.setEnabled(true);
			}
			this.frame.origen = bv;
			this.frame.origen.button.setEnabled(false);
		}
	}

	public class DestinationActionListener implements ActionListener {
		private RompecabezasFrame frame;
		private ButtonValue bv;

		public DestinationActionListener(RompecabezasFrame frame, ButtonValue bv) {
			this.frame = frame;
			this.bv = bv;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (frame.origen == null) {
				return;
			}
			if (frame.destino != null) {
				frame.destino.button.setEnabled(true);
			}
			this.frame.destino = bv;
			frame.destino.button.setEnabled(false);
			try {
				this.frame.controller.procesarAccion(origen.value, destino.value);
				this.frame.origen = null;
				this.frame.destino = null;
				if (this.frame.controller.nivelCompleto()) {

					// this.frame.controller
					// .actualizarRanking(this.frame.controller.getJuegoActual().getNivelActual().getNumero()
					// + 1);
					// Esto es temporal hasta que tengamos mas niveles cargados.
					this.frame.controller.actualizarRanking();
					JLabel label = new JLabel("Ganaste!!");
					label.setBounds(200, 500, 200, 20);
					this.frame.add(label);
				}
			} catch (InvalidAccionException iae) {
				frame.origen.button.setEnabled(true);
				frame.destino.button.setEnabled(true);
			}
		}
	}

	public class ButtonValue {
		private JButton button;
		private String value;

		public ButtonValue(JButton button, String value) {
			this.button = button;
			this.value = value;
		}

		public JButton getButton() {
			return button;
		}

		public String getValue() {
			return value;
		}
	}

	public class VolverActionListener implements ActionListener {

		public VolverActionListener() {
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			volver();
		}
	}
}
