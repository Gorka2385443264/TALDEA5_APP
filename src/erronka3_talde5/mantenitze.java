package erronka3_talde5;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

public class mantenitze extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private int idUsuario; // Agregar un atributo para almacenar la ID del usuario


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    mantenitze frame = new mantenitze();
                    frame.setSize(800, 500);
                    frame.setLocationRelativeTo(null);
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
    /**
     * Create the frame.
     */
    
    
    public mantenitze(int idUsuario) { // Modificar el constructor para aceptar la ID del usuario
        this.idUsuario = idUsuario; // Almacenar la ID del usuario
        // Resto del constructor
    }
    public mantenitze() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        
        JLabel lblNewLabel = new JLabel("MANTENITZE");
        
        JButton btnmantenitzeBizikleta = new JButton("Bizikletak konpondu");
        btnmantenitzeBizikleta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear una instancia de la ventana konponduBizikletaMantenitze
                konponduBizikletaMantenitze ventanaKonponduBizikleta = new konponduBizikletaMantenitze(idUsuario);
                // Ajustar tamaño y posición de la ventana
                ventanaKonponduBizikleta.setSize(800, 500);
                ventanaKonponduBizikleta.setLocationRelativeTo(null);
                // Hacer visible la ventana konponduBizikletaMantenitze
                ventanaKonponduBizikleta.setVisible(true);
                // Cerrar la ventana actual de mantenitze
                setVisible(false);
            }
        });


        
        JButton btnAtras = new JButton("Atras");
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear una instancia de la ventana principal
                app ventanaMain = new app();
                // Hacer visible la ventana principal
                ventanaMain.setVisible(true);
                // Cerrar la ventana actual de mantenitze
                dispose(); 
            }
        });
        
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnAtras)
                    .addGap(95)
                    .addComponent(lblNewLabel)
                    .addContainerGap(209, Short.MAX_VALUE))
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(28)
                    .addComponent(btnmantenitzeBizikleta)
                    .addContainerGap(289, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewLabel)
                        .addComponent(btnAtras))
                    .addGap(57)
                    .addComponent(btnmantenitzeBizikleta)
                    .addContainerGap(147, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }

}
