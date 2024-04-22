package src.erronka3_talde5;import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class fakturaLangilea extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private Connection connection;
    private JTextField txtIdAlokairua;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    fakturaLangilea frame = new fakturaLangilea();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public fakturaLangilea() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Botón para ir atrás
        JButton btnAtras = new JButton("Atras");
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear una instancia de la ventana langilea
                langilea ventanaLangilea = new langilea();
                // Hacer visible la ventana langilea
                ventanaLangilea.setVisible(true);
                // Cerrar la ventana actual de fakturaLangilea
                dispose();
            }
        });
        btnAtras.setBounds(10, 10, 89, 23);
        contentPane.add(btnAtras);

        // Etiqueta y campo de texto para ingresar la ID de alokairua
        JLabel lblIdAlokairua = new JLabel("ID de Alokairua:");
        lblIdAlokairua.setBounds(10, 454, 100, 14);
        contentPane.add(lblIdAlokairua);

        txtIdAlokairua = new JTextField();
        txtIdAlokairua.setBounds(120, 451, 86, 20);
        contentPane.add(txtIdAlokairua);
        txtIdAlokairua.setColumns(10);

        // JTable para mostrar la base de datos
        table = new JTable();
        table.setBounds(10, 44, 774, 365);
        contentPane.add(table);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 44, 774, 365);
        contentPane.add(scrollPane);

        // Botón para hacer factura
        JButton btnHacerFactura = new JButton("Hacer Factura");
        btnHacerFactura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para generar la factura
                generarFactura();
            }
        });
        btnHacerFactura.setBounds(10, 482, 129, 23);
        contentPane.add(btnHacerFactura);

        // Botón para cargar el archivo de texto
        JButton btnCargarArchivo = new JButton("Cargar Archivo");
        btnCargarArchivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para cargar el archivo de texto
                cargarArchivoTexto();
            }
        });
        btnCargarArchivo.setBounds(149, 482, 129, 23);
        contentPane.add(btnCargarArchivo);

        // Establecer conexión a la base de datos
        try {
            connection = DatabaseConnection.getConnection();
            cargarDatosTabla();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para cargar los datos de la base de datos en la JTable
    private void cargarDatosTabla() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM erronka3.alokairua");

            // Crear modelo de la tabla con los nombres de las columnas
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("id_bezeroa");
            model.addColumn("id_bizikleta");
            model.addColumn("id_alokairua");
            model.addColumn("prezioa");
            model.addColumn("data");

            // Llenar modelo con datos de la base de datos
            while (resultSet.next()) {
                Object[] row = new Object[5];
                for (int i = 0; i < 5; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                model.addRow(row);
            }

            // Asignar modelo a la tabla
            table.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar datos desde la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para generar la factura
    private void generarFactura() {
        // Obtener la ID de alokairua del campo de texto
        String idAlokairua = txtIdAlokairua.getText().trim();
        if (idAlokairua.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese la ID de alokairua", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Lógica para generar la factura usando la ID de alokairua
        // Por ahora, simplemente mostrar un mensaje
        JOptionPane.showMessageDialog(null, "Factura generada con éxito para la ID de alokairua: " + idAlokairua, "Factura Generada", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para cargar el archivo de texto
    private void cargarArchivoTexto() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("archivo.txt"));
            StringBuilder content = new StringBuilder();
            for (String line : lines) {
                content.append(line).append("\n");
            }
            JOptionPane.showMessageDialog(null, "Archivo cargado con éxito:\n\n" + content.toString(), "Archivo Cargado", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar el archivo de texto", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

// Clase de conexión a la base de datos separada
class DatabaseConnection {
    // Configuración de la conexión a la base de datos
    private static final String DB_URL = "jdbc:mysql://localhost:3306/erronka3";
    private static final String USER = "root";
    private static final String PASS = "1WMG2023";

    // Método para establecer la conexión a la base de datos
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            // Cargar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar el controlador JDBC", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return connection;
    }
}
