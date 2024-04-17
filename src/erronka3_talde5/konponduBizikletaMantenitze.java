package erronka3_talde5;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class konponduBizikletaMantenitze extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private Connection conn;
    private JTextField textFieldId;
    private int idLangilea; // Variable para almacenar el ID del usuario

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    konponduBizikletaMantenitze frame = new konponduBizikletaMantenitze(1); // Aquí puedes proporcionar el ID del usuario que ha iniciado sesión
                    frame.setSize(800, 500);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public konponduBizikletaMantenitze(int idLangilea) { // Modificar el constructor para recibir el ID del usuario
    	 this.idLangilea = idLangilea;        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("Bizikleten Egoera Konpondu");

        JScrollPane scrollPane = new JScrollPane();
        table = new JTable();
        scrollPane.setViewportView(table);

        JLabel lblIdBizikleta = new JLabel("ID Bizikleta:");

        textFieldId = new JTextField();
        textFieldId.setColumns(10);

        JButton btnKonpondu = new JButton("Konpondu");
        btnKonpondu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idBizikleta = textFieldId.getText().trim();
                if (!idBizikleta.isEmpty()) {
                    try {
                        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/erronka3", "root", "1WMG2023");
                        Statement stmt = conn.createStatement();
                        String query = "UPDATE bizikleta SET egoera = 'Mantenimenduan' WHERE id_bizikleta = '" + idBizikleta + "'";
                        stmt.executeUpdate(query);
                        stmt.close();
                        
                        // Insertar en la tabla mantenua
                        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        String insertQuery = "INSERT INTO mantenua (id_langilea, id_bizikleta, data, egoera, deskripzioa) VALUES "
                            + "(" + idLangilea + ", '" + idBizikleta + "', '" + currentDate + "', 'Mantenitze', 'Descripción aquí')";
                        stmt = conn.createStatement();
                        stmt.executeUpdate(insertQuery);
                        stmt.close();
                        
                        conn.close();
                        System.out.println("Actualización e inserción exitosas");
                        loadData(); // Volver a cargar los datos después de la actualización
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    System.out.println("Por favor ingresa una ID de bicicleta válida.");
                }
            }
        });

        JButton btnAtras = new JButton("Atras");
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear una instancia de la ventana mantenitze
                mantenitze ventanaMantenitze = new mantenitze();
                // Ajustar tamaño y posición de la ventana
                ventanaMantenitze.setSize(800, 500);
                ventanaMantenitze.setLocationRelativeTo(null);
                // Hacer visible la ventana mantenitze
                ventanaMantenitze.setVisible(true);
                // Cerrar la ventana actual de konponduBizikletaMantenitze
                dispose();
            }
        });
        
        JButton btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear una instancia de la ventana trabajoMantenitze
                trabajoMantenitze ventanaTrabajoMantenitze = new trabajoMantenitze();
                // Ajustar tamaño y posición de la ventana
                ventanaTrabajoMantenitze.setSize(800, 500);
                ventanaTrabajoMantenitze.setLocationRelativeTo(null);
                // Hacer visible la ventana trabajoMantenitze
                ventanaTrabajoMantenitze.setVisible(true);
                // Cerrar la ventana actual de konponduBizikletaMantenitze
                dispose();
            }
        });

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addComponent(lblIdBizikleta)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(textFieldId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(btnKonpondu)
                            .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAtras)
                            .addGap(10))
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addComponent(lblNewLabel)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                            .addGap(246)
                            .addComponent(btnSiguiente)
                            .addGap(246)))
                    .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblNewLabel)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblIdBizikleta)
                        .addComponent(textFieldId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnKonpondu)
                        .addComponent(btnAtras))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(btnSiguiente)
                    .addContainerGap(34, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);

        loadData();
    }

    private void loadData() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/erronka3", "root", "1WMG2023");
            Statement stmt = conn.createStatement();
            String query = "SELECT id_bizikleta, egoera FROM bizikleta WHERE egoera IN ('Gaizki', 'Mantenimenduan')";
            ResultSet rs = stmt.executeQuery(query);

            DefaultTableModel model = new DefaultTableModel();
            table.setModel(model);

            model.addColumn("ID Bizikleta");
            model.addColumn("Egoera");

            while (rs.next()) {
                String id_bizikleta = rs.getString("id_bizikleta");
                String egoera = rs.getString("egoera");
                model.addRow(new Object[]{id_bizikleta, egoera});
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
