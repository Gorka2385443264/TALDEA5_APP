package erronka3_talde5;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class admin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin frame = new admin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public admin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("admin");
		lblNewLabel.setBounds(182, 10, 45, 13);
		contentPane.add(lblNewLabel);
		
	     JButton btnAtras = new JButton("Atras");
	        btnAtras.addActionListener(e -> {
	            // Crear una instancia de la ventana principal
	            main ventanaMain = new main();
	            // Hacer visible la ventana principal
	            ventanaMain.setVisible(true);
	            // Cerrar la ventana actual de mantenitze
	            dispose(); 
	        });
	        
	        
	        btnAtras.setBounds(10, 10, 59, 21);
		contentPane.add(btnAtras);
	}
}
