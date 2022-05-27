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
import nttdata.javat1.utils.Constants;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Vista Pinball
 * 
 * @author Adrián Cámara Muñoz
 *
 */
public class PinballView {

	private static final Logger LOG = LoggerFactory.getLogger(PinballView.class);

	// Fields
	private String username;
	private JFrame frmGame;
	private Game game;
	private JButton btnLeftPaddle;
	private JButton btnRightPaddle;
	private JLabel lblScore;
	private JLabel lblInfo;
	private JComboBox cbPower;
	private JButton btnNewBall;
	private JLabel lblRound;
	private JLabel lblStatus;
	private JButton btnNewGame;
	private JButton btnGoMenu;
	private ScoreDAO scoreDAO;
	private UserDAO userDAO;

	/**
	 * Create the application.
	 */
	public PinballView(String username) {
		this.username = username;
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
		
		LOG.info("Método: PinballView.configureUIComponents() | Inicio");

		frmGame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmGame.setIconImage(Toolkit.getDefaultToolkit().getImage("assets/images/billarball.png"));
		frmGame.setBounds(100, 100, 900, 750);
		frmGame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
		lblStatus.setFont(new Font(Constants.FAMILY, Font.PLAIN, 18));
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
		lblScore.setFont(new Font(Constants.FAMILY, Font.BOLD, 20));
		lblScore.setBounds(127, 305, 228, 37);
		frmGame.getContentPane().add(lblScore);

		lblInfo = new JLabel(game.Welcome());
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setFont(new Font(Constants.FAMILY, Font.PLAIN, 18));
		lblInfo.setBounds(197, 397, 474, 129);
		frmGame.getContentPane().add(lblInfo);

		cbPower = new JComboBox();
		cbPower.setFont(new Font(Constants.FAMILY, Font.PLAIN, 20));
		cbPower.setModel(new DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));
		cbPower.setBounds(407, 608, 38, 62);
		frmGame.getContentPane().add(cbPower);
		cbPower.setVisible(false);

		btnNewBall = new JButton("New Ball (" + (game.getBallList().size() - game.getRound()) + ")");
		btnNewBall.setBounds(371, 536, 112, 62);
		frmGame.getContentPane().add(btnNewBall);

		lblRound = new JLabel("Round: 0");
		lblRound.setFont(new Font(Constants.FAMILY, Font.BOLD, 20));
		lblRound.setBounds(127, 256, 225, 50);
		frmGame.getContentPane().add(lblRound);

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon("assets/images/fondo.png"));
		lblFondo.setBounds(0, 0, 886, 731);
		frmGame.getContentPane().add(lblFondo);

		LOG.info("Método: PinballView.configureUIComponents() | Fin");

	}

	/**
	 * configuración de la activacion de los botones
	 */
	private void configureListener() {
		
		LOG.info("Método: PinballView.configureListener() | Inicio");


		// volver al menu
		btnGoMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGame.dispose();
				new MenuView(username);

			}
		});

		// empezar juego nuevo
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGame.dispose();
				new PinballView(username);
			}
		});

		// usar pala izquierda
		btnLeftPaddle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usePaddle(1);
			}
		});

		// usar pala derecha
		btnRightPaddle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usePaddle(2);
			}
		});

		// lanzar bola nueva
		btnNewBall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.startGame();
				startRound();

			}
		});
		
		LOG.info("Método: PinballView.configureListener() | Fin");


	}

	/**
	 * Utilizar pala y comprobar si se golpea la bola
	 * 
	 * @param paddle 1 para pala izq, 2 para pala der
	 */
	protected void usePaddle(int paddle) {
		LOG.info("Método: PinballView.usePaddle() | Inicio");

		if (game.usePaddle(paddle)) {
			lblStatus.setText("Bola golpeada!");
			lblInfo.setText(game.getPoints());
			LOG.info("Método: PinballView.usePaddle() | Bola golpeada");
		} else {
			lblStatus.setText("Casi! Suerte en el siguiente golpe!");
			LOG.info("Método: PinballView.usePaddle() | Bola no golpeada");
			finishRound();
		}
		rePaint();
		LOG.info("Método: PinballView.usePaddle() | Fin");


	}

	/**
	 * Ronda finalizada, mostrar y ocultar los elementos correspondientes
	 */
	protected void finishRound() {
		LOG.info("Método: PinballView.finishRound() | Inicio");
		
		if (!game.getIsFinished()) {
			btnNewBall.setText("New Ball (" + (game.getBallList().size() - game.getRound()) + ")");
			btnNewBall.setVisible(true);
			lblInfo.setText(game.Welcome());
			LOG.info("Método: PinballView.finishRound() | mostrar nueva bola");

		} else {
			lblInfo.setText("Se acabaron las bolas, gracias por jugar!");
			btnGoMenu.setVisible(true);
			btnNewGame.setVisible(true);
			btnGoMenu.setVisible(true);
			btnNewGame.setVisible(true);
			LOG.info("Método: PinballView.finishRound() | juego finalizado");

		}

		btnLeftPaddle.setVisible(false);
		btnRightPaddle.setVisible(false);
		cbPower.setVisible(false);
		LOG.info("Método: PinballView.finishRound() | Fin");

	}

	/**
	 * Ronda empezada, mostrar y ocultar los elementos correspondientes
	 */
	protected void startRound() {
		LOG.info("Método: PinballView.startRound() | Inicio");

		if (!game.getIsFinished()) {
			btnNewBall.setVisible(false);
			lblInfo.setText(
					"<html><body>Elige la potencia del 1 al 5 <br> Usa una de las palas (derecha o izquierda)</body></html>");
			btnLeftPaddle.setVisible(true);
			btnRightPaddle.setVisible(true);
			cbPower.setVisible(true);
			LOG.info("Método: PinballView.startRound() | bola lanzada");

		} else {
			lblInfo.setText("Se acabaron las bolas, gracias por jugar!");
			btnNewBall.setVisible(false);
			scoreDAO.newscore(userDAO.idUser(username), game.getScore());
			btnGoMenu.setVisible(true);
			btnNewGame.setVisible(true);
			LOG.info("Método: PinballView.startRound() | juego finalizado");

		}
		
		LOG.info("Método: PinballView.startRound() | Fin");


	}

	/*
	 * pintar ronda y puntuación
	 */
	protected void rePaint() {
		LOG.info("Método: PinballView.rePaint() | Inicio");

		lblRound.setText("Round: " + game.getRound());
		lblScore.setText("Score: " + game.getScore());
		
		LOG.info("Método: PinballView.rePaint() | Fin");

	}
}