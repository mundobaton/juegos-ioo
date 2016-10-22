package uade.ioo.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import uade.ioo.controller.Controlador;
import uade.ioo.model.ItemRanking;

public class RankingFrame extends BaseViewFrame {

	private static final long serialVersionUID = -5893442933042434736L;

	public RankingFrame(Controlador controller) {
		super(controller);
	}

	@Override
	protected void init() {

		JLabel usuarioTitle = new JLabel("Usuario");
		usuarioTitle.setBounds(53, 20, 130, 20);
		usuarioTitle
				.setFont(new Font(usuarioTitle.getFont().getFontName(), Font.BOLD, usuarioTitle.getFont().getSize()));
		usuarioTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(usuarioTitle);

		JLabel juegoTitle = new JLabel("Juego");
		juegoTitle.setBounds(183, 20, 130, 20);
		juegoTitle.setFont(new Font(juegoTitle.getFont().getFontName(), Font.BOLD, juegoTitle.getFont().getSize()));
		juegoTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(juegoTitle);

		JLabel nivelTitle = new JLabel("Nivel");
		nivelTitle.setBounds(313, 20, 130, 20);
		nivelTitle.setFont(new Font(nivelTitle.getFont().getFontName(), Font.BOLD, nivelTitle.getFont().getSize()));
		nivelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(nivelTitle);

		List<ItemRanking> rankingOrdenado = this.controller.getRankingPorJuego();
		int index = 1;
		int offset = 40;
		for (Iterator<ItemRanking> it = rankingOrdenado.iterator(); it.hasNext();) {
			ItemRanking item = it.next();
			JLabel usuarioLabel = new JLabel(item.getUser().getId());
			usuarioLabel.setHorizontalAlignment(SwingConstants.CENTER);
			usuarioLabel.setBounds(53, (index * 20) + offset, 130, 20);

			JLabel juegoLabel = new JLabel(item.getJuego().getNombre());
			juegoLabel.setHorizontalAlignment(SwingConstants.CENTER);
			juegoLabel.setBounds(183, (index * 20) + offset, 130, 20);

			JLabel nivelLabel = new JLabel(Integer.toString(item.getNivel().getNumero()));
			nivelLabel.setHorizontalAlignment(SwingConstants.CENTER);
			nivelLabel.setBounds(313, (index * 20) + offset, 130, 20);

			add(usuarioLabel);
			add(juegoLabel);
			add(nivelLabel);

			index++;
		}

		JButton volverButton = new JButton();
		volverButton.setText("Volver");
		volverButton.setBounds(400, 440, 100, 30);
		volverButton.addActionListener(new VolverActionListener());
		add(volverButton);
	}

	private void volver() {
		MenuPrincipalFrame menu = new MenuPrincipalFrame(controller);
		this.setVisible(false);
		menu.setVisible(true);
	}

	public class VolverActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			volver();
		}
	}
}
