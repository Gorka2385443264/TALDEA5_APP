package erronka3_talde5;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class langilea extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    langilea frame = new langilea();
                    frame.setSize(800, 500);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public langilea() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("LANGILEA");

        JButton btnNewButton = new JButton("Inbentarioa");
        // Agrega ActionListener al botón "Erregistroak"
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear una instancia de la ventana erregistroakLangilea
                erregistroakLangilea ventanaErregistroak = new erregistroakLangilea();
                // Ajustar tamaño y posición de la ventana
                ventanaErregistroak.setSize(800, 500);
                ventanaErregistroak.setLocationRelativeTo(null);
                // Hacer visible la ventana erregistroakLangilea
                ventanaErregistroak.setVisible(true);
                // Cerrar la ventana actual de langilea
                dispose();
            }
        });

        JButton btnNewButton_1 = new JButton("Faktura");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear una instancia de la ventana fakturaLangilea
                fakturaLangilea ventanaFaktura = new fakturaLangilea();
                // Ajustar tamaño y posición de la ventana
  
                // Hacer visible la ventana fakturaLangilea
                ventanaFaktura.setVisible(true);
                // Cerrar la ventana actual de langilea
                dispose();
            }
        });

        JButton btnAtras = new JButton("Atras");
        btnAtras.addActionListener(e -> {
            // Crear una instancia de la ventana principal
        	app ventanaMain = new app();
            // Hacer visible la ventana principal
            ventanaMain.setVisible(true);
            // Cerrar la ventana actual de langilea
            dispose(); 
        });
        
        JButton btnInfoPErts = new JButton("Informazio Pertsonala");
        btnInfoPErts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear una instancia de la ventana informazioPertsonalaLangilea
                informazioPertsonalaLangilea ventanaInformazioPertsonala = new informazioPertsonalaLangilea();
                // Ajustar tamaño y posición de la ventana
                ventanaInformazioPertsonala.setSize(800, 500);
                ventanaInformazioPertsonala.setLocationRelativeTo(null);
                // Hacer visible la ventana informazioPertsonalaLangilea
                ventanaInformazioPertsonala.setVisible(true);
                // Cerrar la ventana actual de langilea
                dispose();
            }
        });
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addComponent(btnAtras, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGap(77)
        					.addComponent(btnNewButton)))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
        					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
        					.addGap(399))
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGap(206)
        					.addComponent(btnInfoPErts)
        					.addContainerGap())))
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(222)
        			.addComponent(btnNewButton_1)
        			.addContainerGap(375, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNewLabel)
        				.addComponent(btnAtras))
        			.addGap(43)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnInfoPErts)
        				.addComponent(btnNewButton))
        			.addGap(52)
        			.addComponent(btnNewButton_1)
        			.addGap(85))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
