package erronka3_talde5;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.*;

public class main extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField pregunta1;
    private JTextField pregunta2;
    private int idUsuario; // Variable para almacenar la ID del usuario
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
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM langilea WHERE korreoa = ? AND pasahitza = ?");
                pstmt.setString(1, correo);
                pstmt.setString(2, pass);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    // Obtener el ID del usuario que ha iniciado sesión
                    idUsuario = rs.getInt("id_langilea"); // Establece la ID del usuario
                    System.out.println("ID de usuario: " + idUsuario); // Imprimir la ID del usuario en la consola
                    // Abrir la ventana correspondiente según el tipo de usuario
                    String mota = rs.getString("mota");
                    switch (mota) {
                        case "langilea":
                            langilea ventanaLangilea = new langilea();
                            ventanaLangilea.setSize(800, 500); // Establecer tamaño predeterminado
                            ventanaLangilea.setLocationRelativeTo(null); // Centrar en la pantalla
                            ventanaLangilea.setVisible(true);
                            break;
                        case "mantenitze":
                            mantenitze ventanaMantenitze = new mantenitze();
                            ventanaMantenitze.setSize(800, 500); // Establecer tamaño predeterminado
                            ventanaMantenitze.setLocationRelativeTo(null); // Centrar en la pantalla
                            ventanaMantenitze.setVisible(true);
                            break;
                        case "admin":
                            admin ventanaAdmin = new admin(getIdUsuario()); // Pasa el idUsuario al constructor
                            ventanaAdmin.setSize(800, 500); // Establecer tamaño predeterminado
                            ventanaAdmin.setLocationRelativeTo(null); // Centrar en la pantalla
                            ventanaAdmin.setVisible(true);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "El valor de 'mota' no es válido: " + mota);
                            break;
                    }
                    dispose(); // Cerrar la ventana actual de inicio de sesión
                } else {
                    JOptionPane.showMessageDialog(null, "Correo electrónico o contraseña incorrectos.");
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
                .addGroup(GroupLayout.Alignment.TRAILING, gl_panel_1.createSequentialGroup()
                    .addContainerGap(194, Short.MAX_VALUE)
                    .addComponent(btnEnter)
                    .addGap(175))
                .addGroup(GroupLayout.Alignment.TRAILING, gl_panel_1.createSequentialGroup()
                    .addContainerGap(171, Short.MAX_VALUE)
                    .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(pregunta1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(pregunta2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(159))
        );
        gl_panel_1.setVerticalGroup(
            gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addGap(50)
                    .addComponent(pregunta1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(pregunta2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(27)
                    .addComponent(btnEnter)
                    .addContainerGap(91, Short.MAX_VALUE))
        );
        panel_1.setLayout(gl_panel_1);
    }

    // Getter para obtener el valor de la ID del usuario
    public int getIdUsuario() {
        return idUsuario;
    }

    // Setter para establecer el valor de la ID del usuario
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
