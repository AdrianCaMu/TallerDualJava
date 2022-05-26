package nttdata.javat1.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import nttdata.javat1.dao.ScoreDAO;
import nttdata.javat1.dao.UserDAO;
import nttdata.javat1.game.Game;

import javax.swing.JButton;
import java.awt.event.ActionListener;
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
public class PinballView {

	// Propiedades
	private String correo;
	private JFrame frmGame;
	private Game game;
	private JButton btnLeftPaddle;
	private JButton btnRightPaddle;
	private JLabel lblScore;
	private JLabel lblInfo;
	private JComboBox cbPower;
	private JButton btnNewBall;
	private JLabel lblRound;
	private JLabel lblFondo;
	private JLabel lblStatus;
	private JButton btnNewGame;
	private JButton btnGoMenu;
	private ScoreDAO scoreDAO;
	private UserDAO userDAO;


	/**
	 * Create the application.
	 */
	public PinballView(String correo) {
		this.correo = correo;
		this.game = new Game();
		this.scoreDAO = new ScoreDAO();
		this.userDAO = new UserDAO();
		initialize();
		frmGame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGame = new JFrame();
		configureUIComponents();
		configureListener();
	}

	/**
	 * configuración de los distintos elementos de la pantalla
	 */
	private void configureUIComponents() {
		frmGame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmGame.setIconImage(Toolkit.getDefaultToolkit().getImage("assets/images/billarball.png"));
		frmGame.setBounds(100, 100, 900, 750);
		frmGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGame.getContentPane().setLayout(null);
		
		btnGoMenu = new JButton("");
		btnGoMenu.setIcon(new ImageIcon("assets/images/menu.png"));
		btnGoMenu.setBounds(285, 608, 282, 62);
		btnGoMenu.setBorderPainted(false);
		btnGoMenu.setContentAreaFilled(false);
		btnGoMenu.setFocusPainted(false);
		btnGoMenu.setOpaque(false);
		frmGame.getContentPane().add(btnGoMenu);
		btnGoMenu.setVisible(false);
		
		btnNewGame = new JButton("");
		btnNewGame.setIcon(new ImageIcon("assets/images/newGame.png"));
		btnNewGame.setBounds(285, 536, 282, 62);
		btnNewGame.setBorderPainted(false);
		btnNewGame.setContentAreaFilled(false);
		btnNewGame.setFocusPainted(false);
		btnNewGame.setOpaque(false);
		frmGame.getContentPane().add(btnNewGame);
		btnNewGame.setVisible(false);
		
		lblStatus = new JLabel("");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblStatus.setBounds(197, 345, 474, 62);
		frmGame.getContentPane().add(lblStatus);

		btnLeftPaddle = new JButton("Left Paddle");
		btnLeftPaddle.setBounds(285, 608, 112, 62);
		frmGame.getContentPane().add(btnLeftPaddle);
		btnLeftPaddle.setVisible(false);

		btnRightPaddle = new JButton("Right Paddle");
		btnRightPaddle.setBounds(455, 608, 112, 62);
		frmGame.getContentPane().add(btnRightPaddle);
		btnRightPaddle.setVisible(false);

		lblScore = new JLabel("Score: 0");
		lblScore.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblScore.setBounds(127, 305, 228, 37);
		frmGame.getContentPane().add(lblScore);

		lblInfo = new JLabel(game.Welcome());
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblInfo.setBounds(197, 397, 474, 129);
		frmGame.getContentPane().add(lblInfo);

		cbPower = new JComboBox();
		cbPower.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbPower.setModel(new DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));
		cbPower.setBounds(407, 608, 38, 62);
		frmGame.getContentPane().add(cbPower);
		cbPower.setVisible(false);

		btnNewBall = new JButton("New Ball (" + (game.getBallList().size() - game.getRound()) + ")");
		btnNewBall.setBounds(371, 536, 112, 62);
		frmGame.getContentPane().add(btnNewBall);

		lblRound = new JLabel("Round: 0");
		lblRound.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRound.setBounds(127, 256, 225, 50);
		frmGame.getContentPane().add(lblRound);
		
		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon("assets/images/fondo.png"));
		lblFondo.setBounds(0, 0, 886, 731);
		frmGame.getContentPane().add(lblFondo);


	}

	/**
	 * configuración de la activacion de los botones
	 */
	private void configureListener() {
		
		btnGoMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGame.dispose();
				new MenuView(correo);

			}
		});
		
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGame.dispose();
				new PinballView(correo);
			}
		});
		
		btnLeftPaddle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usePaddle(1);
			}
		});

		btnRightPaddle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usePaddle(2);
			}
		});

		btnNewBall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.startGame();
				startRound();

			}
		});

	}
	
	protected void usePaddle(int paddle) {	
		
		if (game.usePaddle(paddle)) {
			lblStatus.setText("Bola golpeada!");
			lblInfo.setText(game.getPoints());
		} else {
			lblStatus.setText("Casi! Suerte en el siguiente golpe!");
			finishRound();
		}
		rePaint();
		
	}

	protected void finishRound() {
		if(!game.getIsFinished()) {
			btnNewBall.setText("New Ball (" + (game.getBallList().size() - game.getRound()) + ")");
			btnNewBall.setVisible(true);
			lblInfo.setText(game.Welcome());

		}else {
			lblInfo.setText("Se acabaron las bolas, gracias por jugar!");
			btnGoMenu.setVisible(true);
			btnNewGame.setVisible(true);
			btnGoMenu.setVisible(true);
			btnNewGame.setVisible(true);
		}
		
		btnLeftPaddle.setVisible(false);
		btnRightPaddle.setVisible(false);
		cbPower.setVisible(false); 

	}

	protected void startRound() {
		
		if(!game.getIsFinished()) {
			btnNewBall.setVisible(false);
			lblInfo.setText("<html><body>Elige la potencia del 1 al 5 <br> Usa una de las palas (derecha o izquierda)</body></html>");
			btnLeftPaddle.setVisible(true);
			btnRightPaddle.setVisible(true);
			cbPower.setVisible(true);
		}else {
			lblInfo.setText("Se acabaron las bolas, gracias por jugar!");
			btnNewBall.setVisible(false);
			scoreDAO.newscore(userDAO.idUser(correo), game.getScore());
			btnGoMenu.setVisible(true);
			btnNewGame.setVisible(true);
		}
		
	}
	
	protected void rePaint() {
		lblRound.setText("Round: " + game.getRound());
		lblScore.setText("Score: " + game.getScore());
	}
}