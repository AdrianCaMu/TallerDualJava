package nttdata.javat1.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import nttdata.javat1.dao.ScoreDAO;
import nttdata.javat1.game.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * Vista de Netflix
 * 
 * @author Adrián Cámara Muñoz
 *
 */
public class ScoreView {

	// Propiedades
	private JFrame parent;
	private JFrame frmMenu;
	private JButton btnVolver;
	private JButton btnNewGame;
	private JLabel lblFondo;
	private JButton btnScore;
	private ScoreDAO scoreDAO;
	private JLabel lblScoreTable;

	/**
	 * Create the application.
	 */
	public ScoreView(JFrame parent) {
		this.parent = parent;
		this.scoreDAO = new ScoreDAO();
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
		printScore();
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
		
		lblScoreTable = new JLabel("New label");
		lblScoreTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoreTable.setBounds(10, 257, 866, 322);
		frmMenu.getContentPane().add(lblScoreTable);
		
		btnVolver = new JButton("");
		btnVolver.setIcon(new ImageIcon("assets/images/menu.png"));
		btnVolver.setBounds(332, 585, 274, 85);
		btnVolver.setBorderPainted(false);
		btnVolver.setContentAreaFilled(false);
		btnVolver.setFocusPainted(false);
		btnVolver.setOpaque(false);
		frmMenu.getContentPane().add(btnVolver);
		
		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon("assets/images/fondo.png"));
		lblFondo.setBounds(0, 0, 886, 731);
		frmMenu.getContentPane().add(lblFondo);

	}

	/**
	 * configuración de la activacion de los botones
	 */
	private void configureListener() {
		


		// cerrar sesión y volver a pantalla de menu
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMenu.dispose();
				parent.setVisible(true);
			}
		});
	}
	
	private void printScore(){
		String cadena = "<html><body><ol>";
		ArrayList<User> list = scoreDAO.getAll();

		// establecemos los campos de la serie en cuestión
		for(User u : list) {
			cadena += "<li><h1>" + u.getUsername() + ": " + u.getScore() + "</h1></li>";
		}
		
		cadena += "</ol></body></html>";
		
		lblScoreTable.setText(cadena);
		
	}
}