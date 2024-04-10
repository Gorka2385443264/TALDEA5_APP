package erronka3_talde5;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

public class erregistroakLangilea extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    // Declarar la variable de conexión a la base de datos
    private Connection conn;
    private JButton btnAtras;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    erregistroakLangilea frame = new erregistroakLangilea();
                    frame.setSize(800,500);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public erregistroakLangilea() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();

        setContentPane(contentPane);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        
        JLabel lblNewLabel = new JLabel("Erregistroak");

        JScrollPane scrollPane = new JScrollPane();

        // Crear la tabla
        table = new JTable();

        // Asociar el JTable con el JScrollPane
        scrollPane.setViewportView(table);
        
        JButton btnAtras = new JButton("Atras");
        btnAtras.addActionListener(e -> {
            // Crear una instancia de la ventana principal
            main ventanaMain = new main();
            // Hacer visible la ventana principal
            ventanaMain.setVisible(true);
            // Cerrar la ventana actual de mantenitze
            dispose(); 
        });
        
        
        // Configurar el layout
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGap(20)
        					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(btnAtras, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
        					.addGap(94)
        					.addComponent(lblNewLabel)))
        			.addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNewLabel)
        				.addComponent(btnAtras))
        			.addGap(20)
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
        			.addContainerGap())
        );

        contentPane.setLayout(gl_contentPane);

        // Obtener datos de la base de datos y cargarlos en la tabla
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/erronka3", "root", "1WMG2023");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM langilea");

            // Obtener metadatos de la consulta
            DefaultTableModel model = new DefaultTableModel();

            // Agregar columnas al modelo
            int columnCount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(rs.getMetaData().getColumnLabel(i));
            }

            // Agregar filas al modelo
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            // Establecer el modelo en la tabla
            table.setModel(model);

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar la conexión a la base de datos en el bloque finally
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
