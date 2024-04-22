package src.erronka3_talde5;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class fakturaLangilea extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

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

        // JTable para mostrar la base de datos
        table = new JTable();
        table.setBounds(10, 44, 774, 365);
        contentPane.add(table);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 44, 774, 365);
        contentPane.add(scrollPane);

        // Cargar datos de la base de datos en el JTable
        cargarDatosTabla();

        scrollPane.setViewportView(table);

        // Botón para hacer factura
        JButton btnHacerFactura = new JButton("Hacer Factura");
        btnHacerFactura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes implementar la lógica para generar la factura
                // Por ahora, simplemente imprimir un mensaje
                System.out.println("Factura generada con éxito");
            }
        });
        btnHacerFactura.setBounds(10, 420, 129, 23);
        contentPane.add(btnHacerFactura);

        // Botón para cargar el archivo de texto
        JButton btnCargarArchivo = new JButton("Cargar Archivo");
        btnCargarArchivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes implementar la lógica para cargar el archivo de texto
                // Por ahora, simplemente imprimir un mensaje
                System.out.println("Archivo cargado con éxito");
            }
        });
        btnCargarArchivo.setBounds(149, 420, 129, 23);
        contentPane.add(btnCargarArchivo);
    }

    // Método para cargar los datos de la base de datos en el JTable
    private void cargarDatosTabla() {
        // Suponiendo que tienes una clase llamada DatabaseManager con un método para obtener los datos de la base de datos
        // Puedes adaptar este código según cómo estés accediendo a tu base de datos
        List<String> lines;
        try {
            lines = Files.readAllLines(new File("erronka3.alokairua").toPath());
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("id_bezeroa");
            model.addColumn("id_bizikleta");
            model.addColumn("id_alokairua");
            model.addColumn("prezioa");
            model.addColumn("data");

            for (String line : lines) {
                String[] parts = line.split(",");
                model.addRow(parts);
            }

            table.setModel(model);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public class DatabaseConnection {
        // Configuración de la conexión a la base de datos
        private static final String DB_URL = "jdbc:mysql://localhost:3306/tu_basedatos";
        private static final String USER = "tu_usuario";
        private static final String PASS = "tu_contraseña";

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
            }
            return connection;
        }
    }
}
