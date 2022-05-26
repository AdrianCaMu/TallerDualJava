package nttdata.javat1.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import nttdata.javat1.dao.UserDAO;
import nttdata.javat1.utils.HashPasswd;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 * Vista de registro
 * 
 * @author Adrián Cámara Muñoz
 *
 */
public class RegisterView {

	// Propiedades
	private JFrame frmRegister;
	private JButton btnVolver;
	private JFrame parent;
	private JTextField textUsername;
	private JLabel lblUsername;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldConfirm;
	private JLabel lblPassword;
	private JLabel lblPasswordConfirm;
	private JButton btnRegister;
	private JButton btnRecuperar;
	private JLabel lblErrorMessage;
	private UserDAO usuarioDAO;
	private JLabel lblFondo;

	/**
	 * Create the application.
	 */
	public RegisterView(JFrame parent) {
		usuarioDAO = new UserDAO();
		this.parent = parent;
		initialize();
		frmRegister.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame. selecciona el icono de la ventana
	 */
	private void initialize() {
		frmRegister = new JFrame();
		frmRegister.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmRegister.setIconImage(Toolkit.getDefaultToolkit().getImage("assets/images/billarball.png"));
		configureUIComponents();
		configureListener();
	}

	/**
	 * configuración de los distintos elementos de la pantalla
	 */
	private void configureUIComponents() {
		frmRegister.setBounds(100, 100, 900, 750);
		frmRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegister.getContentPane().setLayout(null);

		btnVolver = new JButton("");
		btnVolver.setIcon(new ImageIcon("assets/images/Login.png"));
		btnVolver.setBounds(223, 555, 200, 80);
		btnVolver.setBorderPainted(false);
		btnVolver.setContentAreaFilled(false);
		btnVolver.setFocusPainted(false);
		btnVolver.setOpaque(false);
		frmRegister.getContentPane().add(btnVolver);

		textUsername = new JTextField();
		textUsername.setForeground(new Color(56, 109, 185));
		textUsername.setHorizontalAlignment(SwingConstants.CENTER);
		textUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
		textUsername.setBackground(Color.WHITE);
		textUsername.setBounds(481, 304, 180, 31);
		frmRegister.getContentPane().add(textUsername);
		textUsername.setColumns(10);

		lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setForeground(Color.DARK_GRAY);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblUsername.setBounds(290, 304, 159, 31);
		frmRegister.getContentPane().add(lblUsername);

		passwordField = new JPasswordField();
		passwordField.setForeground(new Color(56, 109, 185));
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 18));
		passwordField.setBackground(Color.WHITE);
		passwordField.setBounds(481, 353, 180, 31);
		frmRegister.getContentPane().add(passwordField);

		lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.DARK_GRAY);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblPassword.setBounds(290, 353, 159, 31);
		frmRegister.getContentPane().add(lblPassword);

		passwordFieldConfirm = new JPasswordField();
		passwordFieldConfirm.setForeground(new Color(56, 109, 185));
		passwordFieldConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		passwordFieldConfirm.setFont(new Font("Tahoma", Font.BOLD, 18));
		passwordFieldConfirm.setBackground(Color.WHITE);
		passwordFieldConfirm.setBounds(481, 404, 180, 31);
		frmRegister.getContentPane().add(passwordFieldConfirm);

		lblPasswordConfirm = new JLabel("Confirm Password");
		lblPasswordConfirm.setForeground(Color.DARK_GRAY);
		lblPasswordConfirm.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblPasswordConfirm.setBounds(172, 404, 277, 31);
		frmRegister.getContentPane().add(lblPasswordConfirm);

		btnRegister = new JButton("");
		btnRegister.setIcon(new ImageIcon("assets/images/Register.png"));
		btnRegister.setBounds(483, 555, 200, 80);
		btnRegister.setBorderPainted(false);
		btnRegister.setContentAreaFilled(false);
		btnRegister.setFocusPainted(false);
		btnRegister.setOpaque(false);
		frmRegister.getContentPane().add(btnRegister);

		lblErrorMessage = new JLabel("");
		lblErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorMessage.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setBounds(156, 460, 596, 42);
		frmRegister.getContentPane().add(lblErrorMessage);

		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon("assets/images/fondo.png"));
		lblFondo.setBounds(0, 0, 886, 731);
		frmRegister.getContentPane().add(lblFondo);
	}

	/**
	 * configuración de la activación de los botones
	 */
	private void configureListener() {

		// Enter para ir a Passwd desde correo
		textUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					passwordField.requestFocus();
				}
			}
		});

		// Enter para ir a confirmar passwd desde passwd
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					passwordFieldConfirm.requestFocus();
				}
			}
		});

		// Volver a iniciar sesión
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRegister.setVisible(false);
				parent.setVisible(true);
			}
		});

		// registrar nuevo usuario
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});

	}

	/**
	 * registrar nuevo usuario
	 */
	private void register() {
		String passwd = new String(passwordField.getPassword());
		String confirmPasswd = new String(passwordFieldConfirm.getPassword());
		String passwdCodified = HashPasswd.HashIt(passwd, "123456");
		String correo = textUsername.getText();
		boolean find = false;

		// comprueba si el correo ya existe, muestra error en ese caso
		boolean logicaCorrecto = usuarioDAO.login(correo, passwdCodified);
		if (!logicaCorrecto) {

			// comprueba si el campo correo esta vacio y muestra error en ese caso
			if (correo.equals("")) {
				lblErrorMessage.setText("ERROR: El usuario no puede estar vacio.");

				// comprueba que las contraseñas no esten vacias, muestra error en caso de
				// estarlas
			} else if (passwd.equals("") || confirmPasswd.equals("")) {
				lblErrorMessage.setText("ERROR: Las contraseñas no puede estar vacia.");

				// comprueba si las contraseñas coinciden, muestra error en caso de no coincidir
			} else if (!find && !passwd.equals(confirmPasswd)) {
				lblErrorMessage.setText("ERROR: Las contraseñas no coinciden.");

				// en caso de no existir el usuario y coincidir las contraseñas registra al
				// usuario, muestra mensaje de confirmacion del registro
			} else if (!find && passwd.equals(confirmPasswd)) {
				lblErrorMessage.setText("Registrado correctamente.");

				usuarioDAO.register(correo, passwdCodified);

			}

		} else {
			lblErrorMessage.setText("ERROR: Ya existe una cuenta con este nombre.");
		}
	}

}