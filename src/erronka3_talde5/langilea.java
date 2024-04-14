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

        JButton btnAtras = new JButton("Atras");
        btnAtras.addActionListener(e -> {
            // Crear una instancia de la ventana principal
            main ventanaMain = new main();
            // Hacer visible la ventana principal
            ventanaMain.setVisible(true);
            // Cerrar la ventana actual de langilea
            dispose(); 
        });
        
        JButton btnNewButton_2 = new JButton("Alokairu historiala");
        
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
        
        JButton btnNewButton_4 = new JButton("Ordainketa Historiala");
        
        JButton btnNewButton_5 = new JButton("Katalogoa");
        
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(btnAtras, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addGap(29)
                            .addComponent(btnNewButton)))
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addPreferredGap(ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                            .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
                            .addGap(399))
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                .addGroup(gl_contentPane.createSequentialGroup()
                                    .addGap(30)
                                    .addComponent(btnInfoPErts)
                                    .addGap(27))
                                .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                    .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnNewButton_5)
                                    .addGap(49)))
                            .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addComponent(btnNewButton_4)
                                .addComponent(btnNewButton_2))
                            .addGap(233))))
                .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                    .addGap(38)
                    .addComponent(btnNewButton_1)
                    .addContainerGap(559, Short.MAX_VALUE))
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
                        .addComponent(btnNewButton)
                        .addComponent(btnNewButton_2)
                        .addComponent(btnInfoPErts))
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                            .addComponent(btnNewButton_1)
                            .addContainerGap())
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addGap(52)
                            .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                .addComponent(btnNewButton_4)
                                .addComponent(btnNewButton_5))
                            .addContainerGap())))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
