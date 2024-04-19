package erronka3_talde5;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

public class trabajoMantenitze extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private Connection conn;
    private JTextField textFieldEgoera;
    private JTextField textFieldDeskripzioa;
    private JTextField textFieldIdMantenua;
    private int idUsuario;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    int idUsuario = 1; // ID de ejemplo
                    trabajoMantenitze frame = new trabajoMantenitze(idUsuario);
                    frame.setSize(800, 500);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public trabajoMantenitze(int idUsuario) {
        this.idUsuario = idUsuario;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("Mantenitze LANA");

        JScrollPane scrollPane = new JScrollPane();
        table = new JTable();
        scrollPane.setViewportView(table);

        JLabel lblEgoera = new JLabel("Egoera:");

        textFieldEgoera = new JTextField();
        textFieldEgoera.setColumns(10);

        JLabel lblDeskripzioa = new JLabel("Deskripzioa:");

        textFieldDeskripzioa = new JTextField();
        textFieldDeskripzioa.setColumns(10);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarMantenitze());

        JLabel lblIdMantenua = new JLabel("ID Mantenua:");

        textFieldIdMantenua = new JTextField();
        textFieldIdMantenua.setColumns(10);

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addComponent(lblEgoera)
                            .addGap(18)
                            .addComponent(textFieldEgoera, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(36)
                            .addComponent(lblDeskripzioa)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(textFieldDeskripzioa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(18)
                            .addComponent(btnGuardar)
                            .addPreferredGap(ComponentPlacement.RELATED, 138, Short.MAX_VALUE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addComponent(lblNewLabel)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(lblIdMantenua)
                            .addGap(18)
                            .addComponent(textFieldIdMantenua, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED, 310, Short.MAX_VALUE)))
                    .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(27)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblNewLabel)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                            .addComponent(lblIdMantenua)
                            .addComponent(textFieldIdMantenua, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                            .addComponent(lblEgoera)
                            .addComponent(lblDeskripzioa)
                            .addComponent(textFieldDeskripzioa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardar))
                        .addComponent(textFieldEgoera, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(104, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);

        loadData();
    }

    private void loadData() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/erronka3", "root", "1WMG2023");
            String query = "SELECT id_mantenua, id_langilea, id_bizikleta, data, egoera, deskripzioa FROM mantenua";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                DefaultTableModel model = new DefaultTableModel();
                table.setModel(model);
                model.addColumn("ID Mantenua");
                model.addColumn("ID Langilea");
                model.addColumn("ID Bizikleta");
                model.addColumn("Data");
                model.addColumn("Egoera");
                model.addColumn("Deskripzioa");

                while (rs.next()) {
                    int id_mantenua = rs.getInt("id_mantenua");
                    int id_langilea = rs.getInt("id_langilea");
                    int id_bizikleta = rs.getInt("id_bizikleta");
                    String data = rs.getString("data");
                    String egoera = rs.getString("egoera");
                    String deskripzioa = rs.getString("deskripzioa");
                    model.addRow(new Object[]{id_mantenua, id_langilea, id_bizikleta, data, egoera, deskripzioa});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void guardarMantenitze() {
        String idMantenua = textFieldIdMantenua.getText().trim();
        String egoera = textFieldEgoera.getText().trim();
        String deskripzioa = textFieldDeskripzioa.getText().trim();
        if (!idMantenua.isEmpty() && !egoera.isEmpty() && !deskripzioa.isEmpty()) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/erronka3", "root", "1WMG2023");
                String updateQuery = "UPDATE mantenua SET egoera = ?, deskripzioa = ? WHERE id_mantenua = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                    pstmt.setString(1, egoera);
                    pstmt.setString(2, deskripzioa);
                    pstmt.setInt(3, Integer.parseInt(idMantenua));
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Actualización exitosa en la base de datos.");
                        loadData(); // Recargar los datos en la tabla después de la actualización
                    } else {
                        System.out.println("No se pudo actualizar el registro en la base de datos.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Por favor ingresa valores válidos para ID Mantenua, Egoera y Deskripzioa.");
        }
    }
}
