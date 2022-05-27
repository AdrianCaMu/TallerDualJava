package nttdata.javat1.ui;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import nttdata.javat1.utils.Constants;
import nttdata.javat1.utils.HashPasswd;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nttdata.javat1.T1MainACM;
import nttdata.javat1.dao.UserDAO;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

/**
 * Vista de Login
 * 
 * @author Adrián Cámara Muñoz
 *
 */
public class LoginView {

	private static final Logger LOG = LoggerFactory.getLogger(LoginView.class);

	// Fields
	private JFrame frmLogin;
	private JTextField tfUser;
	private JButton btnLogin;
	private JButton btnRegister;
	private JPasswordField pfPassword;
	private JLabel lblErrorMessage;
	private UserDAO userDAO;

	/**
	 * Create the application.
	 */
	public LoginView() {
		userDAO = new UserDAO();
		initialize();
		this.frmLogin.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		configureUIComponents();
		configureListener();
	}

	/**
	 * configuración de los distintos elementos de la pantalla
	 */
	private void configureUIComponents() {
		
		LOG.info("Método: LoginView.configureUIComponents() | Inicio");

		frmLogin.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(Constants.ICON));
		frmLogin.setBounds(100, 100, 900, 750);
		frmLogin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);

		this.btnLogin = new JButton("");
		btnLogin.setIcon(new ImageIcon("assets/images/Login.png"));
		btnLogin.setBounds(482, 525, 200, 85);
		btnLogin.setBorderPainted(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setFocusPainted(false);
		btnLogin.setOpaque(false);
		frmLogin.getContentPane().add(btnLogin);

		btnRegister = new JButton("");
		btnRegister.setIcon(new ImageIcon("assets/images/Register.png"));
		btnRegister.setBackground(Color.WHITE);
		btnRegister.setBounds(224, 525, 200, 85);
		btnRegister.setBorderPainted(false);
		btnRegister.setContentAreaFilled(false);
		btnRegister.setFocusPainted(false);
		btnRegister.setOpaque(false);
		frmLogin.getContentPane().add(btnRegister);

		tfUser = new JTextField();
		tfUser.setFont(new Font(Constants.FAMILY, Font.BOLD, 15));
		tfUser.setHorizontalAlignment(SwingConstants.CENTER);
		tfUser.setForeground(new Color(56, 109, 185));
		tfUser.setBackground(Color.WHITE);
		tfUser.setBounds(458, 328, 179, 38);
		frmLogin.getContentPane().add(tfUser);
		tfUser.setColumns(10);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.DARK_GRAY);
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setFont(new Font(Constants.FAMILY, Font.BOLD, 30));
		lblUsername.setBounds(235, 325, 159, 38);
		frmLogin.getContentPane().add(lblUsername);

		JLabel lblPasswd = new JLabel("Password");
		lblPasswd.setForeground(Color.DARK_GRAY);
		lblPasswd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPasswd.setFont(new Font(Constants.FAMILY, Font.BOLD, 30));
		lblPasswd.setBounds(235, 369, 162, 38);
		frmLogin.getContentPane().add(lblPasswd);

		pfPassword = new JPasswordField();
		pfPassword.setFont(new Font(Constants.FAMILY, Font.BOLD, 15));
		pfPassword.setHorizontalAlignment(SwingConstants.CENTER);
		pfPassword.setForeground(new Color(56, 109, 185));
		pfPassword.setBackground(Color.WHITE);
		pfPassword.setBounds(458, 376, 179, 38);
		frmLogin.getContentPane().add(pfPassword);

		lblErrorMessage = new JLabel("");
		lblErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorMessage.setFont(new Font(Constants.FAMILY, Font.BOLD, 16));
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setBounds(235, 410, 402, 38);
		frmLogin.getContentPane().add(lblErrorMessage);

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon("assets/images/fondo.png"));
		lblFondo.setBounds(0, 0, 886, 731);
		frmLogin.getContentPane().add(lblFondo);
	
		LOG.info("Método: LoginView.configureUIComponents() | Fin");

	}
	

	/**
	 * configuración de la activación de los botones
	 */
	private void configureListener() {
		
		LOG.info("Método: LoginView.configureListener() | Inicio");

		// boton login para acceder al pinball
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkLogin();
			}
		});

		// Enter al poner user para ir al campo password
		tfUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					pfPassword.requestFocus();
				}
			}
		});

		// Enter al poner password para acceder a la vista de pinball
		pfPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					checkLogin();
				}
			}
		});

		// vamos a la pagina de registro para crear una cuenta nueva
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogin.setVisible(false);
				new RegisterView(frmLogin);
			}
		});
		
		LOG.info("Método: LoginView.configureListener() | Fin");

	}

	/**
	 * comprueba si el usuario y la password son correctos
	 */
	private void checkLogin() {
		
		LOG.info("Método: LoginView.checkLogin() | Inicio");

		String username = tfUser.getText();
		String passwd = new String(pfPassword.getPassword());
		String passwdCodified = HashPasswd.hashIt(passwd, "123456");

		try {

			boolean logicaCorrecto = userDAO.login(username, passwdCodified);
			if (logicaCorrecto) {
				LOG.info("Método: LoginView.checkLogin() | Login correcto");
				frmLogin.dispose();
				new MenuView(username);
			} else {
				lblErrorMessage.setText("ERROR: correo o contraseña incorrectos.");
				LOG.debug("Método: LoginView.checkLogin() | Login fallido");

			}

		} catch (Exception e) {
			lblErrorMessage.setText("ERROR: correo o contraseña incorrectos.");
			LOG.error("Método: LoginView.checkLogin() | Login fallido");

		}
		
		LOG.info("Método: LoginView.checkLogin() | Fin");


	}
}