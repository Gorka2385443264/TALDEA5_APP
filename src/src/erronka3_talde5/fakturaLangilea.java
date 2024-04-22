package src.erronka3_talde5;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

public class fakturaLangilea extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private Connection connection;
    private JTextField txtIdAlokairua;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                fakturaLangilea frame = new fakturaLangilea();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
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

        JButton btnAtras = new JButton("Atrás");
        btnAtras.addActionListener(e -> {
            // Regresar a la ventana anterior
            dispose();
        });
        btnAtras.setBounds(10, 10, 89, 23);
        contentPane.add(btnAtras);

        JLabel lblIdAlokairua = new JLabel("ID de Alokairua:");
        lblIdAlokairua.setBounds(10, 454, 100, 14);
        contentPane.add(lblIdAlokairua);

        txtIdAlokairua = new JTextField();
        txtIdAlokairua.setBounds(120, 451, 86, 20);
        contentPane.add(txtIdAlokairua);
        txtIdAlokairua.setColumns(10);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 44, 774, 365);
        contentPane.add(scrollPane);
        scrollPane.setViewportView(table);

        JButton btnHacerFactura = new JButton("Hacer Factura");
        btnHacerFactura.addActionListener(e -> {
            generarFactura();
        });
        btnHacerFactura.setBounds(10, 482, 129, 23);
        contentPane.add(btnHacerFactura);

        JButton btnGenerarPdf = new JButton("Generar PDF");
        btnGenerarPdf.addActionListener(e -> {
            generarPDF();
        });
        btnGenerarPdf.setBounds(288, 482, 129, 23);
        contentPane.add(btnGenerarPdf);

        try {
            connection = DatabaseConnection.getConnection();
            cargarDatosTabla();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDatosTabla() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM erronka3.alokairua");

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("id_bezeroa");
            model.addColumn("id_bizikleta");
            model.addColumn("id_alokairua");
            model.addColumn("prezioa");
            model.addColumn("data");

            while (resultSet.next()) {
                Object[] row = new Object[5];
                for (int i = 0; i < 5; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                model.addRow(row);
            }

            table.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar datos desde la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generarFactura() {
        String idAlokairua = txtIdAlokairua.getText().trim();
        if (idAlokairua.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese la ID de alokairua", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(null, "Factura generada con éxito para la ID de alokairua: " + idAlokairua, "Factura Generada", JOptionPane.INFORMATION_MESSAGE);
    }
    private void generarPDF() {
        try {
            String pdfFileName = "factura2.pdf";

            // Crear documento y página
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            // Obtener el contenido de la página
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Cargar el logo de la empresa como una imagen
            PDImageXObject logo = PDImageXObject.createFromFile("logo.png", document);

            // Obtener la posición y tamaño del logo
            float logoWidth = logo.getWidth();
            float logoHeight = logo.getHeight();

            // Posicionar el logo en la parte superior central del PDF
            float pageWidth = page.getMediaBox().getWidth();
            float logoX = (pageWidth - logoWidth) / 2;
            float logoY = page.getMediaBox().getHeight() - logoHeight - 50;
            contentStream.drawImage(logo, logoX, logoY, logoWidth, logoHeight);

            // Obtener el ID de alokairua del campo de texto
            String idAlokairua = txtIdAlokairua.getText().trim();

            // Texto "La factura de tu alquiler Nº" con el ID de alquiler
            String facturaText = "La factura de tu alquiler Nº " + idAlokairua;
            float textWidth = PDType1Font.HELVETICA.getStringWidth(facturaText) / 1000 * 12;
            float textX = (pageWidth - textWidth) / 2;
            float textY = logoY - 50;

            // Escribir el texto "La factura de tu alquiler Nº"
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(textX, textY);
            contentStream.showText(facturaText);
            contentStream.endText();

            // Dibujar la tabla debajo del texto de la factura
            float tableStartX = 50;
            float tableStartY = textY - 50;

            for (int i = 0; i < table.getColumnCount(); i++) {
                String columnName = table.getColumnName(i);
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                contentStream.newLineAtOffset(tableStartX + (i * 100), tableStartY);
                contentStream.showText(columnName);
                contentStream.endText();
            }

            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    String cellValue = table.getValueAt(i, j).toString();
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 10);
                    contentStream.newLineAtOffset(tableStartX + (j * 100), tableStartY - ((i + 1) * 20));
                    contentStream.showText(cellValue);
                    contentStream.endText();
                }
            }

            contentStream.close();

            // Guardar y cerrar el documento
            document.save(pdfFileName);
            document.close();

            JOptionPane.showMessageDialog(null, "PDF generado correctamente", "PDF Generado", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el PDF", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    static class DatabaseConnection {
        private static final String DB_URL = "jdbc:mysql://localhost:3306/erronka3";
        private static final String USER = "root";
        private static final String PASS = "1WMG2023";

        public static Connection getConnection() throws SQLException {
            Connection connection = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al cargar el controlador JDBC", "Error", JOptionPane.ERROR_MESSAGE);
            }
            return connection;
        }
    }
}
