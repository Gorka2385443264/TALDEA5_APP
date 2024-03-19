package erronka3_talde5;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class main extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField pregunta1;
    private JTextField pregunta2;

    private Connection conn;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    main frame = new main();
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
    public main() {
        // Establece la conexión con la base de datos
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/erronka3", "root", "1WMG2023");
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("Iniciar Sesion");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

        pregunta1 = new JTextField();
        pregunta1.setColumns(10);

        pregunta2 = new JTextField();
        pregunta2.setColumns(10);

        JButton btnEnter = new JButton("Enter");
        btnEnter.addActionListener(e -> {
            // Verificar las credenciales ingresadas por el usuario
            String correo = pregunta1.getText();
            String pass = pregunta2.getText();
            try {
            	
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM iniciarsesion WHERE korreoa = ? AND pasahitza = ?");
                pstmt.setString(1, correo);
                pstmt.setString(2, pass);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    // Si se encuentra una coincidencia, mostrar un mensaje de éxito
                    System.out.println("Inicio de sesión exitoso.");
                } else {
                    // Si no se encuentra coincidencia, mostrar un mensaje de error
                    System.out.println("Correo electrónico o contraseña incorrectos.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        contentPane.setLayout(new BorderLayout(0, 0));

        contentPane.add(lblNewLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        JPanel panel_1 = new JPanel();
        panel.add(panel_1, BorderLayout.CENTER);

        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
            gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(pregunta1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(pregunta2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEnter))
                    .addContainerGap(288, Short.MAX_VALUE))
        );
        gl_panel_1.setVerticalGroup(
            gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pregunta1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(pregunta2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(btnEnter)
                    .addContainerGap(156, Short.MAX_VALUE))
        );
        panel_1.setLayout(gl_panel_1);
    }
}
