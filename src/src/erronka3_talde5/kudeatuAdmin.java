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

public class kudeatuAdmin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private Connection conn;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    kudeatuAdmin frame = new kudeatuAdmin();
                    frame.setSize(800, 500);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public kudeatuAdmin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("Erregistroak - Admin");

        JScrollPane scrollPane = new JScrollPane();
        table = new JTable();
        scrollPane.setViewportView(table);

        JButton btnAtras = new JButton("Atras");
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                admin ventanaAdmin = new  admin();
                ventanaAdmin.setVisible(true);
                ventanaAdmin.setSize(getSize());
                ventanaAdmin.setLocationRelativeTo(null);
                ventanaAdmin.setVisible(true);
                dispose();
            }
        });

        JButton btnActualizarMota = new JButton("Actualizar Mota");
        JTextField txtIdLangilea = new JTextField();
        JTextField txtMota = new JTextField();

        btnActualizarMota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int idLangilea = Integer.parseInt(txtIdLangilea.getText());
                String mota = txtMota.getText();
                updateMota(idLangilea, mota);
                loadData();
            }
        });

        JButton btnActualizarPassword = new JButton("Actualizar Contraseña");
        JTextField txtIdLangileaPassword = new JTextField();
        JTextField txtNuevaContraseña = new JTextField();

        btnActualizarPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int idLangilea = Integer.parseInt(txtIdLangileaPassword.getText());
                String nuevaContraseña = txtNuevaContraseña.getText();
                updateContraseña(idLangilea, nuevaContraseña);
                loadData();
            }
        });

        JLabel lblIdLangilea = new JLabel("ID Langilea");
        JLabel lblMota = new JLabel("Nueva Mota");
        JLabel lblIdLangileaPassword = new JLabel("ID Langilea");
        JLabel lblNuevaContraseña = new JLabel("Nueva Contraseña");

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
                            .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addComponent(btnActualizarMota)
                                .addComponent(btnActualizarPassword))
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_contentPane.createSequentialGroup()
                                    .addComponent(lblIdLangilea)
                                    .addPreferredGap(ComponentPlacement.UNRELATED)
                                    .addComponent(lblMota))
                                .addGroup(gl_contentPane.createSequentialGroup()
                                    .addComponent(txtIdLangilea, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(txtMota, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_contentPane.createSequentialGroup()
                                    .addComponent(lblIdLangileaPassword)
                                    .addPreferredGap(ComponentPlacement.UNRELATED)
                                    .addComponent(lblNuevaContraseña))
                                .addGroup(gl_contentPane.createSequentialGroup()
                                    .addComponent(txtIdLangileaPassword, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(txtNuevaContraseña, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))))
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
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnActualizarMota)
                        .addComponent(lblIdLangilea)
                        .addComponent(lblMota))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(txtIdLangilea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMota, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnActualizarPassword)
                        .addComponent(lblIdLangileaPassword)
                        .addComponent(lblNuevaContraseña))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(txtIdLangileaPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNuevaContraseña, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(25, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);

        loadData();
    }

    private void updateMota(int idLangilea, String mota) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://192.168.15.130:3306/erronka3", "5taldea", "1@WMG2024");
            Statement stmt = conn.createStatement();
            String sql = "UPDATE langilea SET mota = '" + mota + "' WHERE id_langilea = " + idLangilea;
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateContraseña(int idLangilea, String nuevaContraseña) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://192.168.15.130:3306/erronka3", "5taldea", "1@WMG2024");
            Statement stmt = conn.createStatement();
            String sql = "UPDATE langilea SET pasahitza = '" + nuevaContraseña + "' WHERE id_langilea = " + idLangilea;
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://192.168.15.130:3306/erronka3", "5taldea", "1@WMG2024");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM langilea");

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
