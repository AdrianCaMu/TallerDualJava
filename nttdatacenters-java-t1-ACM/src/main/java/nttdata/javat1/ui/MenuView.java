package nttdata.javat1.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;


/**
 * Vista de Netflix
 * 
 * @author Adrián Cámara Muñoz
 *
 */
public class MenuView {

	// Propiedades
	private String correo;
	private JFrame frmMenu;
	private JButton btnVolver;
	private JButton btnNewGame;
	private JLabel lblFondo;
	private JButton btnScore;

	/**
	 * Create the application.
	 */
	public MenuView(String correo) {
		this.correo = correo;
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
		frmMenu.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmMenu.setIconImage(Toolkit.getDefaultToolkit().getImage("assets/images/billarball.png"));
		frmMenu.setBounds(100, 100, 900, 750);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(null);
		
		btnScore = new JButton("");
		btnScore.setIcon(new ImageIcon("assets/images/ScoreTable.png"));
		btnScore.setOpaque(false);
		btnScore.setFocusPainted(false);
		btnScore.setContentAreaFilled(false);
		btnScore.setBorderPainted(false);
		btnScore.setBounds(321, 422, 274, 85);
		frmMenu.getContentPane().add(btnScore);
		
		btnVolver = new JButton("");
		btnVolver.setIcon(new ImageIcon("assets/images/LogginOff.png"));
		btnVolver.setBounds(321, 500, 274, 85);
		btnVolver.setBorderPainted(false);
		btnVolver.setContentAreaFilled(false);
		btnVolver.setFocusPainted(false);
		btnVolver.setOpaque(false);
		frmMenu.getContentPane().add(btnVolver);
		
		btnNewGame = new JButton("");
		btnNewGame.setIcon(new ImageIcon("assets/images/newGame.png"));
		btnNewGame.setBounds(321, 345, 274, 85);
		btnNewGame.setBorderPainted(false);
		btnNewGame.setContentAreaFilled(false);
		btnNewGame.setFocusPainted(false);
		btnNewGame.setOpaque(false);
		frmMenu.getContentPane().add(btnNewGame);
		
		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon("assets/images/fondo.png"));
		lblFondo.setBounds(0, 0, 886, 731);
		frmMenu.getContentPane().add(lblFondo);

	}

	/**
	 * configuración de la activacion de los botones
	 */
	private void configureListener() {
		
		//comenzar partida
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMenu.dispose();
				new PinballView(correo);
			}
		});
		
		//ver tabla de puntuaciones
		btnScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMenu.dispose();
				new ScoreView(frmMenu);
			}
		});

		// cerrar sesión y volver a pantalla de login
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMenu.dispose();
				new LoginView();
			}
		});
	}
}