package nttdata.javat1.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nttdata.javat1.utils.Constants;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

/**
 * Vista Menu
 * 
 * @author Adrián Cámara Muñoz
 *
 */
public class MenuView {
	
	private static final Logger LOG = LoggerFactory.getLogger(MenuView.class);


	// Fields
	private String username;
	private JFrame frmMenu;
	private JButton btnReturn;
	private JButton btnNewGame;
	private JButton btnScore;

	/**
	 * Create the application.
	 */
	public MenuView(String username) {
		this.username = username;
		initialize();
		frmMenu.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenu = new JFrame();
		configureUIComponents();
		configureListener();
	}

	/**
	 * configuración de los distintos elementos de la pantalla
	 */
	private void configureUIComponents() {
		
		LOG.info("Método: MenuView.configureUIComponents() | Inicio");

		frmMenu.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmMenu.setIconImage(Toolkit.getDefaultToolkit().getImage(Constants.ICON));
		frmMenu.setBounds(100, 100, 900, 750);
		frmMenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(null);

		btnScore = new JButton("");
		btnScore.setIcon(new ImageIcon("assets/images/ScoreTable.png"));
		btnScore.setOpaque(false);
		btnScore.setFocusPainted(false);
		btnScore.setContentAreaFilled(false);
		btnScore.setBorderPainted(false);
		btnScore.setBounds(321, 422, 274, 85);
		frmMenu.getContentPane().add(btnScore);

		btnReturn = new JButton("");
		btnReturn.setIcon(new ImageIcon("assets/images/LogginOff.png"));
		btnReturn.setBounds(321, 500, 274, 85);
		btnReturn.setBorderPainted(false);
		btnReturn.setContentAreaFilled(false);
		btnReturn.setFocusPainted(false);
		btnReturn.setOpaque(false);
		frmMenu.getContentPane().add(btnReturn);

		btnNewGame = new JButton("");
		btnNewGame.setIcon(new ImageIcon("assets/images/newGame.png"));
		btnNewGame.setBounds(321, 345, 274, 85);
		btnNewGame.setBorderPainted(false);
		btnNewGame.setContentAreaFilled(false);
		btnNewGame.setFocusPainted(false);
		btnNewGame.setOpaque(false);
		frmMenu.getContentPane().add(btnNewGame);

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon("assets/images/fondo.png"));
		lblFondo.setBounds(0, 0, 886, 731);
		frmMenu.getContentPane().add(lblFondo);
		
		LOG.info("Método: MenuView.configureUIComponents() | Fin");


	}

	/**
	 * configuración de la activacion de los botones
	 */
	private void configureListener() {
		
		LOG.info("Método: MenuView.configureListener() | Inicio");


		// comenzar partida
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMenu.dispose();
				new PinballView(username);
			}
		});

		// ver tabla de puntuaciones
		btnScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMenu.dispose();
				new ScoreView(frmMenu);
			}
		});

		// cerrar sesión y volver a pantalla de login
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMenu.dispose();
				new LoginView();
			}
		});
		
		LOG.info("Método: MenuView.configureListener() | Fin");

	}
}