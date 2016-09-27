package uade.ioo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import uade.ioo.controller.Controlador;
import uade.ioo.exception.ValidationException;

public class NuevoUsuarioFrame extends BaseViewFrame {

	private static final long serialVersionUID = 8664114819649806499L;
	private final static String USER_NAME = "Nombre de usuario:";
	private final static String FIRST_NAME = "Nombre:";
	private final static String LAST_NAME = "Apellido:";
	private final static String BACK = "Volver";
	private final static String ACEPTAR = "Aceptar";
	private JTextField usuarioField;
	private JTextField nombreField;
	private JTextField apellidoField;

	public NuevoUsuarioFrame(Controlador controller) {
		super(controller);
	}

	protected void init() {
		JLabel usuarioLabel = new JLabel(USER_NAME);
		usuarioLabel.setBounds(20, 20, 250, 20);
		this.usuarioField = new JTextField();
		this.usuarioField.setBounds(270, 20, 200, 20);

		JLabel firstNameLabel = new JLabel(FIRST_NAME);
		firstNameLabel.setBounds(20, 40, 250, 20);
		this.nombreField = new JTextField();
		nombreField.setBounds(270, 40, 200, 20);

		JLabel lastNameLabel = new JLabel(LAST_NAME);
		lastNameLabel.setBounds(20, 60, 250, 20);
		this.apellidoField = new JTextField();
		apellidoField.setBounds(270, 60, 200, 20);

		JButton volverButton = new JButton(BACK);
		volverButton.setBounds(160, 80, 100, 30);
		volverButton.addActionListener(new VolverActionListener());

		JButton aceptarButton = new JButton(ACEPTAR);
		aceptarButton.setBounds(260, 80, 130, 30);
		aceptarButton.addActionListener(new AceptarActionListener(this));

		add(usuarioLabel);
		add(firstNameLabel);
		add(lastNameLabel);
		add(usuarioField);
		add(nombreField);
		add(apellidoField);
		add(volverButton);
		add(aceptarButton);
	}

	private void volver() {
		LoginFrame loginFrame = new LoginFrame(controller);
		this.setVisible(false);
		loginFrame.setVisible(true);
	}

	public class AceptarActionListener implements ActionListener {
		private NuevoUsuarioFrame nuevoUsuarioFrame;

		public AceptarActionListener(NuevoUsuarioFrame nuevoUsuarioFrame) {
			this.nuevoUsuarioFrame = nuevoUsuarioFrame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (nuevoUsuarioFrame.usuarioField.getText() != null
					&& !nuevoUsuarioFrame.usuarioField.getText().isEmpty()) {

				try {
					controller.crearUsuario(nuevoUsuarioFrame.usuarioField.getText(),
							nuevoUsuarioFrame.nombreField.getText(), nuevoUsuarioFrame.apellidoField.getText());
					volver();
				} catch (ValidationException ve) {
					JLabel usuarioYaExiste = new JLabel(ve.getMessage());
					usuarioYaExiste.setBounds(20, 100, 200, 20);
					nuevoUsuarioFrame.add(usuarioYaExiste);
				}
			}
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
