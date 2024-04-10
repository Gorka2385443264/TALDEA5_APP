package erronka3_talde5;

import java.awt.EventQueue;

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

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    langilea frame = new langilea();
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
    public langilea() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("LANGILEA");

        JButton btnNewButton = new JButton("Erregistroak");

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
        
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                    .addComponent(btnAtras, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                    .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
                    .addGap(163))
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(29)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(btnNewButton_1)
                        .addComponent(btnNewButton))
                    .addContainerGap(312, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewLabel)
                        .addComponent(btnAtras))
                    .addGap(43)
                    .addComponent(btnNewButton)
                    .addGap(71)
                    .addComponent(btnNewButton_1)
                    .addContainerGap(87, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
