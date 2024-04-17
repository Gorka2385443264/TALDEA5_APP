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

public class erregistroakLangilea extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private Connection conn;
    private JTextField txtAddMota;
    private JTextField txtAddPrezioa;
    private JTextField txtAddStock;
    private JTextField txtAddEgoera;
    private JTextField txtDeleteId;

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

        JLabel lblNewLabel = new JLabel("Erregistroak - Langilea");

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

        JButton btnAddBizikleta = new JButton("Añadir Bizikleta");
        txtAddMota = new JTextField();
        txtAddMota.setColumns(10);
        txtAddPrezioa = new JTextField();
        txtAddPrezioa.setColumns(10);
        txtAddStock = new JTextField();
        txtAddStock.setColumns(10);
        txtAddEgoera = new JTextField();
        txtAddEgoera.setColumns(10);

        btnAddBizikleta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String mota = txtAddMota.getText();
                double prezioa = Double.parseDouble(txtAddPrezioa.getText());
                int stock = Integer.parseInt(txtAddStock.getText());
                String egoera = txtAddEgoera.getText();
                addBizikleta(mota, prezioa, stock, egoera);
                loadData();
            }
        });

        JButton btnDeleteBizikleta = new JButton("Eliminar Bizikleta");
        txtDeleteId = new JTextField();
        txtDeleteId.setColumns(10);

        btnDeleteBizikleta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int idBizikleta = Integer.parseInt(txtDeleteId.getText());
                deleteBizikleta(idBizikleta);
                loadData();
            }
        });

        JLabel lblAddMota = new JLabel("Mota:");
        JLabel lblAddPrezioa = new JLabel("Prezioa:");
        JLabel lblAddStock = new JLabel("Stock:");
        JLabel lblAddEgoera = new JLabel("Egoera:");
        JLabel lblDeleteId = new JLabel("ID Bizikleta:");

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
                                .addComponent(btnAddBizikleta)
                                .addComponent(btnDeleteBizikleta))
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblAddMota)
                                .addComponent(lblAddPrezioa)
                                .addComponent(lblAddStock)
                                .addComponent(lblAddEgoera)
                                .addComponent(lblDeleteId))
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addComponent(txtAddMota, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtAddPrezioa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtAddStock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtAddEgoera, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDeleteId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
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
                        .addComponent(btnAddBizikleta)
                        .addComponent(lblAddMota)
                        .addComponent(txtAddMota, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblAddPrezioa)
                        .addComponent(txtAddPrezioa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblAddStock)
                        .addComponent(txtAddStock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblAddEgoera)
                        .addComponent(txtAddEgoera, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnDeleteBizikleta)
                        .addComponent(lblDeleteId)
                        .addComponent(txtDeleteId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(25, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);

        loadData();
    }

    private void addBizikleta(String mota, double prezioa, int stock, String egoera) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/erronka3", "root", "1WMG2023");
            Statement stmt = conn.createStatement();
            
            // Obtener el último ID de la tabla bizikleta
            ResultSet rs = stmt.executeQuery("SELECT MAX(id_bizikleta) FROM bizikleta");
            int lastId = 0;
            if (rs.next()) {
                lastId = rs.getInt(1);
            }
            rs.close();
            
            // Incrementar el último ID en uno para obtener el nuevo ID
            int newId = lastId + 1;
            
            // Insertar la nueva bicicleta con el nuevo ID
            String sql = "INSERT INTO bizikleta (id_bizikleta, mota, prezioa, stock, egoera) VALUES (" + newId + ", '" + mota + "', " + prezioa + ", " + stock + ", '" + egoera + "')";
            stmt.executeUpdate(sql);
            
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    private void deleteBizikleta(int idBizikleta) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/erronka3", "root", "1WMG2023");
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM bizikleta WHERE id_bizikleta = " + idBizikleta;
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
