package src.erronka3_talde5;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.GroupLayout;
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
import javax.swing.GroupLayout.Alignment;

public class erregistroakLangilea extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private Connection conn;
    private JTextField txtId;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    erregistroakLangilea frame = new erregistroakLangilea();
                    frame.setSize(800, 500);
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
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("Fakturhuottrhthjiohja - Langilea");

        JScrollPane scrollPane = new JScrollPane();
        table = new JTable();
        scrollPane.setViewportView(table);

        JButton btnAtras = new JButton("Atras");
        btnAtras.addActionListener(e -> {
            // Crear una instancia de la ventana langilea
            langilea ventanaLangilea = new langilea();
            // Ajustar tamaño y posición de la ventana
            ventanaLangilea.setSize(800, 500);
            ventanaLangilea.setLocationRelativeTo(null);
            // Hacer visible la ventana langilea
            ventanaLangilea.setVisible(true);
            // Cerrar la ventana actual de alokairuHistorialaLangilea
            dispose();
        });

        txtId = new JTextField();
        txtId.setColumns(10);

        JLabel lblId = new JLabel("ID Langilea:");//a

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addComponent(btnAtras)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(lblNewLabel))
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addComponent(lblId)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnAtras)
                        .addComponent(lblNewLabel))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblId)
                        .addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(66, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);

        loadData();
    }
    

    private void loadData() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://192.168.15.130:3306/erronka3", "5taldea", "1WMG2024");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM bizikleta");

            DefaultTableModel model = new DefaultTableModel();

            int columnCount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(rs.getMetaData().getColumnLabel(i));
            }

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            table.setModel(model);

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
