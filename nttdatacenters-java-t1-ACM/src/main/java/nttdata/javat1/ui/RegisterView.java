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
import javax.swing.WindowConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nttdata.javat1.dao.UserDAO;
import nttdata.javat1.utils.Constants;
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
	
	private static final Logger LOG = LoggerFactory.getLogger(RegisterView.class);

	// Fields
	private JFrame frmRegister;
	private JButton btnReturn;
	private JFrame parent;
	private JTextField textUsername;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldConfirm;
	private JButton btnRegister;
	private JLabel lblErrorMessage;
	private UserDAO userDAO;

	/**
	 * Create the application.
	 */
	public RegisterView(JFrame parent) {
		userDAO = new UserDAO();
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
		frmRegister.setIconImage(Toolkit.getDefaultToolkit().getImage(Constants.ICON));
		configureUIComponents();
		configureListener();
	}

	/**
	 * configuración de los distintos elementos de la pantalla
	 */
	private void configureUIComponents() {
		
		LOG.info("Método: RegisterView.configureUIComponents() | Inicio");
		
		frmRegister.setBounds(100, 100, 900, 750);
		frmRegister.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frmRegister.getContentPane().setLayout(null);

		btnReturn = new JButton("");
		btnReturn.setIcon(new ImageIcon("assets/images/Login.png"));
		btnReturn.setBounds(223, 555, 200, 80);
		btnReturn.setBorderPainted(false);
		btnReturn.setContentAreaFilled(false);
		btnReturn.setFocusPainted(false);
		btnReturn.setOpaque(false);
		frmRegister.getContentPane().add(btnReturn);

		textUsername = new JTextField();
		textUsername.setForeground(new Color(56, 109, 185));
		textUsername.setHorizontalAlignment(SwingConstants.CENTER);
		textUsername.setFont(new Font(Constants.FAMILY, Font.BOLD, 18));
		textUsername.setBackground(Color.WHITE);
		textUsername.setBounds(481, 304, 180, 31);
		frmRegister.getContentPane().add(textUsername);
		textUsername.setColumns(10);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setForeground(Color.DARK_GRAY);
		lblUsername.setFont(new Font(Constants.FAMILY, Font.BOLD, 30));
		lblUsername.setBounds(290, 304, 159, 31);
		frmRegister.getContentPane().add(lblUsername);

		passwordField = new JPasswordField();
		passwordField.setForeground(new Color(56, 109, 185));
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font(Constants.FAMILY, Font.BOLD, 18));
		passwordField.setBackground(Color.WHITE);
		passwordField.setBounds(481, 353, 180, 31);
		frmRegister.getContentPane().add(passwordField);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.DARK_GRAY);
		lblPassword.setFont(new Font(Constants.FAMILY, Font.BOLD, 30));
		lblPassword.setBounds(290, 353, 159, 31);
		frmRegister.getContentPane().add(lblPassword);

		passwordFieldConfirm = new JPasswordField();
		passwordFieldConfirm.setForeground(new Color(56, 109, 185));
		passwordFieldConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		passwordFieldConfirm.setFont(new Font(Constants.FAMILY, Font.BOLD, 18));
		passwordFieldConfirm.setBackground(Color.WHITE);
		passwordFieldConfirm.setBounds(481, 404, 180, 31);
		frmRegister.getContentPane().add(passwordFieldConfirm);

		JLabel lblPasswordConfirm = new JLabel("Confirm Password");
		lblPasswordConfirm.setForeground(Color.DARK_GRAY);
		lblPasswordConfirm.setFont(new Font(Constants.FAMILY, Font.BOLD, 30));
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
		lblErrorMessage.setFont(new Font(Constants.FAMILY, Font.BOLD, 18));
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setBounds(156, 460, 596, 42);
		frmRegister.getContentPane().add(lblErrorMessage);

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon("assets/images/fondo.png"));
		lblFondo.setBounds(0, 0, 886, 731);
		frmRegister.getContentPane().add(lblFondo);
		
		LOG.info("Método: RegisterView.configureUIComponents() | Fin");

	}

	/**
	 * configuración de la activación de los botones
	 */
	private void configureListener() {
		
		LOG.info("Método: RegisterView.configureListener() | Inicio");


		// Enter para ir a Passwd desde username
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
		btnReturn.addActionListener(new ActionListener() {
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
		
		LOG.info("Método: RegisterView.configureListener() | Fin");


	}

	/**
	 * registrar nuevo usuario
	 */
	private void register() {
		
		LOG.info("Método: RegisterView.register() | Inicio");

		String passwd = new String(passwordField.getPassword());
		String confirmPasswd = new String(passwordFieldConfirm.getPassword());
		String passwdCodified = HashPasswd.hashIt(passwd, "123456");
		String username = textUsername.getText();

		// comprueba si el correo ya existe, muestra error en ese caso
		boolean logicaCorrecto = userDAO.usernameFind(username);
		if (!logicaCorrecto) {

			if(checkRegister(passwd, confirmPasswd, passwdCodified)) {
				userDAO.register(username, passwdCodified);
				LOG.info("Método: RegisterView.register() | Register correcto");
			}

		} else {
			lblErrorMessage.setText("ERROR: Ya existe una cuenta con este nombre.");
			LOG.debug("Método: RegisterView.register() | Register fallido");

		}
		
		LOG.info("Método: RegisterView.register() | Fin");

	}

	/**
	 * confirmar register
	 * @param passwd contraseña 
	 * @param confirmPasswd contraseña repetida
	 * @param username nombre usuario
	 * @return true si se puede registrar, false si no.
	 */
	private boolean checkRegister(String passwd, String confirmPasswd, String username) {
		
		LOG.info("Método: RegisterView.checkRegister() | Inicio");
		
		boolean valido = false;

		// comprueba si el campo correo esta vacio y muestra error en ese caso
		if (username.equals("")) {
			lblErrorMessage.setText("ERROR: El usuario no puede estar vacio.");

			// comprueba que las contraseñas no esten vacias, muestra error en caso de
			// estarlas
		} else if (passwd.equals("") || confirmPasswd.equals("")) {
			lblErrorMessage.setText("ERROR: Las contraseñas no puede estar vacia.");

			// comprueba si las contraseñas coinciden, muestra error en caso de no coincidir
		} else if (!passwd.equals(confirmPasswd)) {
			lblErrorMessage.setText("ERROR: Las contraseñas no coinciden.");

			// en caso de no existir el usuario y coincidir las contraseñas registra al
			// usuario, muestra mensaje de confirmacion del registro
		} else {
			lblErrorMessage.setText("Registrado correctamente.");
			valido = true;
		}
		
		LOG.info("Método: RegisterView.checkRegister() | Fin");

		return valido;
	}

}