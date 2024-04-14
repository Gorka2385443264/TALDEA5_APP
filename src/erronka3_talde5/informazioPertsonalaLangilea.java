package erronka3_talde5;

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

public class informazioPertsonalaLangilea extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private Connection conn;
    private JTextField txtHelbidea;
    private JTextField txtTelefonoa;
    private JTextField txtHerria;
    private JTextField txtIdHelbidea;
    private JTextField txtIdTelefonoa;
    private JTextField txtIdHerria;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    informazioPertsonalaLangilea frame = new informazioPertsonalaLangilea();
                    frame.setSize(800, 500);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public informazioPertsonalaLangilea() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("Informazio Pertsonala");

        JScrollPane scrollPane = new JScrollPane();
        table = new JTable();
        scrollPane.setViewportView(table);

        JButton btnAtras = new JButton("Atras");
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear una instancia de la ventana langilea
                langilea ventanaLangilea = new langilea();
                // Ajustar tamaño y posición de la ventana
                ventanaLangilea.setSize(800, 500);
                ventanaLangilea.setLocationRelativeTo(null);
                // Hacer visible la ventana langilea
                ventanaLangilea.setVisible(true);
                // Cerrar la ventana actual de informazioPertsonalaLangilea
                dispose();
            }
        });

        txtHelbidea = new JTextField();
        txtHelbidea.setColumns(10);

        txtTelefonoa = new JTextField();
        txtTelefonoa.setColumns(10);

        txtHerria = new JTextField();
        txtHerria.setColumns(10);

        txtIdHelbidea = new JTextField();
        txtIdHelbidea.setColumns(10);

        txtIdTelefonoa = new JTextField();
        txtIdTelefonoa.setColumns(10);

        txtIdHerria = new JTextField();
        txtIdHerria.setColumns(10);

        JButton btnAldatuHelbidea = new JButton("Aldatu Helbidea");
        btnAldatuHelbidea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para actualizar la dirección en la base de datos
                String helbidea = txtHelbidea.getText();
                String id = txtIdHelbidea.getText();
                updateHelbidea(id, helbidea);
                loadData();
            }
        });

        JButton btnAldatuTelefonoa = new JButton("Aldatu Telefonoa");
        btnAldatuTelefonoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para actualizar el teléfono en la base de datos
                String telefonoa = txtTelefonoa.getText();
                String id = txtIdTelefonoa.getText();
                updateTelefonoa(id, telefonoa);
                loadData();
            }
        });

        JButton btnAldatuHerria = new JButton("Aldatu Herria");
        btnAldatuHerria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para actualizar la ciudad en la base de datos
                String herria = txtHerria.getText();
                String id = txtIdHerria.getText();
                updateHerria(id, herria);
                loadData();
            }
        });

        JLabel lblHelbidea = new JLabel("Helbidea");
        JLabel lblTelefonoa = new JLabel("Telefonoa");
        JLabel lblHerria = new JLabel("Herria");
        JLabel lblIdHelbidea = new JLabel("ID Helbidea");
        JLabel lblIdTelefonoa = new JLabel("ID Telefonoa");
        JLabel lblIdHerria = new JLabel("ID Herria");

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addComponent(btnAtras)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(lblNewLabel))
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblHelbidea)
        						.addComponent(txtHelbidea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(txtIdHelbidea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblIdHelbidea)
        						.addComponent(btnAldatuHelbidea, Alignment.TRAILING))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        						.addGroup(gl_contentPane.createSequentialGroup()
        							.addGap(11)
        							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        								.addComponent(lblTelefonoa)
        								.addComponent(txtTelefonoa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(txtIdTelefonoa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(lblIdTelefonoa))
        							.addGap(18)
        							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        								.addComponent(lblHerria)
        								.addComponent(txtHerria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(txtIdHerria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(lblIdHerria)))
        						.addGroup(gl_contentPane.createSequentialGroup()
        							.addComponent(btnAldatuTelefonoa)
        							.addGap(18)
        							.addComponent(btnAldatuHerria)))))
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
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addComponent(lblHelbidea)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(txtHelbidea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(lblIdHelbidea)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(txtIdHelbidea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addComponent(lblHerria)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(txtHerria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(lblIdHerria)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(txtIdHerria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addComponent(lblTelefonoa)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(txtTelefonoa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(lblIdTelefonoa)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(txtIdTelefonoa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnAldatuHelbidea)
        				.addComponent(btnAldatuTelefonoa)
        				.addComponent(btnAldatuHerria))
        			.addContainerGap(25, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);

        loadData();
    }

    private void updateHelbidea(String id, String helbidea) {
        // Lógica para actualizar la dirección en la base de datos
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/erronka3", "root", "1WMG2023");
            Statement stmt = conn.createStatement();
            String sql = "UPDATE langilea SET helbidea = '" + helbidea + "' WHERE id_langilea = '" + id + "'";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void updateTelefonoa(String id, String telefonoa) {
        // Lógica para actualizar el teléfono en la base de datos
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/erronka3", "root", "1WMG2023");
            Statement stmt = conn.createStatement();
            String sql = "UPDATE langilea SET telefonoa = '" + telefonoa + "' WHERE id_langilea = '" + id + "'";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void updateHerria(String id, String herria) {
        // Lógica para actualizar la ciudad en la base de datos
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/erronka3", "root", "1WMG2023");
            Statement stmt = conn.createStatement();
            String sql = "UPDATE langilea SET herria = '" + herria + "' WHERE id_langilea = '" + id + "'";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void loadData() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/erronka3", "root", "1WMG2023");
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
