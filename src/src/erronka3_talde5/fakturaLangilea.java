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
import java.sql.*;

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
            generarPDF();
        });
        btnHacerFactura.setBounds(10, 482, 129, 23);
        contentPane.add(btnHacerFactura);

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
            ResultSet resultSet = statement.executeQuery("SELECT id_bezeroa, id_bizikleta, id_alokairua, prezioa, data FROM erronka3.alokairua");

            // Crear modelo de la tabla con los nombres de las columnas
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID Bezeroa");
            model.addColumn("ID Bizikleta");
            model.addColumn("ID Alokairua");
            model.addColumn("Prezioa");
            model.addColumn("Data");

            // Llenar modelo con datos de la base de datos
            while (resultSet.next()) {
                Object[] row = new Object[5];
                row[0] = resultSet.getObject("id_bezeroa");
                row[1] = resultSet.getObject("id_bizikleta");
                row[2] = resultSet.getObject("id_alokairua");
                row[3] = resultSet.getObject("prezioa");
                row[4] = resultSet.getObject("data");
                model.addRow(row);
            }

            // Asignar modelo a la tabla
            table.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar datos desde la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generarPDF() {
        try {
            final String pdfFileName = "factura.pdf";

            // Crear un nuevo documento PDF
            PDDocument document = new PDDocument();

            // Crear la página del documento
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            // Crear el flujo de contenido para escribir en el documento
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Definir la ruta del archivo de imagen del logo
            String logoPath = "logo.png";

            // Cargar el logo como un objeto de imagen
            PDImageXObject logoImage = PDImageXObject.createFromFile(logoPath, document);

            // Obtener las dimensiones del logo
            float logoWidth = logoImage.getWidth();
            float logoHeight = logoImage.getHeight();

            // Definir las coordenadas para colocar el logo centrado horizontalmente en la parte superior de la página
            float logoX = (page.getMediaBox().getWidth() - logoWidth) / 2;
            float logoY = page.getMediaBox().getHeight() - 50 - logoHeight;

            // Dibujar el logo en la página
            contentStream.drawImage(logoImage, logoX, logoY, logoWidth, logoHeight);

            // Definir la fuente y el tamaño del texto para el título (La factura de alquiler Nº)
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 32);

            // Centrar el título horizontalmente
            float titleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth("La factura de alquiler Nº") / 1000f * 32;
            float titleX = (page.getMediaBox().getWidth() - titleWidth) / 2;

            // Definir las coordenadas para comenzar a escribir el texto del título
            float titleY = logoY - 100;

            // Agregar el título al documento
            contentStream.beginText();
            contentStream.newLineAtOffset(titleX, titleY);
            contentStream.showText("La factura de alquiler Nº " + txtIdAlokairua.getText()); // Concatenar el número de id_alokairua
            contentStream.endText();

            // Dibujar la línea horizontal
            contentStream.moveTo(50, titleY - 20);
            contentStream.lineTo(page.getMediaBox().getWidth() - 50, titleY - 20);
            contentStream.stroke();

            // Definir la fuente y el tamaño del texto para los detalles (Cliente, Bicicleta, etc.)
            contentStream.setFont(PDType1Font.HELVETICA, 24);

            // Definir las coordenadas para comenzar a escribir el texto de los detalles
            float textStartX = 50;
            float textStartY = titleY - 150;

            // Obtener el número de factura (ID de alquiler) del campo de texto
            String idAlokairua = txtIdAlokairua.getText().trim();

            // Obtener el ID del cliente (id_bezeroa) asociado al ID de alquiler (id_alokairua) desde la base de datos
            String idBezeroa = "";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id_bezeroa FROM erronka3.alokairua WHERE id_alokairua = " + idAlokairua);
            if (resultSet.next()) {
                idBezeroa = resultSet.getString("id_bezeroa");

                // Obtener el nombre completo del cliente utilizando el ID del cliente obtenido
                String cliente = "";
                Statement statement2 = connection.createStatement();
                ResultSet resultSet2 = statement2.executeQuery("SELECT izena, abizena FROM erronka3.bezeroa WHERE id_bezeroa = " + idBezeroa);
                if (resultSet2.next()) {
                    String izena = resultSet2.getString("izena");
                    String abizena = resultSet2.getString("abizena");
                    cliente = izena + " " + abizena;
                }
                resultSet2.close();
                statement2.close();

                // Agregar el texto del cliente
                contentStream.beginText();
                contentStream.newLineAtOffset(textStartX, textStartY);
                contentStream.showText("Cliente: " + cliente);
                contentStream.endText();

                // Agregar espacio adicional
                textStartY -= 50;

                // Agregar el texto de la bicicleta
                contentStream.beginText();
                contentStream.newLineAtOffset(textStartX, textStartY);
                contentStream.showText("Bicicleta: Mota"); // Reemplazar "Mota" con la información real obtenida de la base de datos
                contentStream.endText();

                // Obtener el precio y la fecha del alquiler
                String prezioa = "";
                String data = "";
                ResultSet resultSet4 = statement.executeQuery("SELECT prezioa, data FROM erronka3.alokairua WHERE id_alokairua = " + idAlokairua);
                if (resultSet4.next()) {
                    prezioa = resultSet4.getString("prezioa");
                    data = resultSet4.getString("data");
                }
                resultSet4.close();

                // Agregar el texto de precio y fecha
                contentStream.beginText();
                contentStream.newLineAtOffset(textStartX, textStartY - 50);
                contentStream.showText("Prezioa: " + prezioa);
                contentStream.endText();

                // Agregar el texto de la fecha
                contentStream.beginText();
                contentStream.newLineAtOffset(textStartX, textStartY - 100);
                contentStream.showText("Data: " + data);
                contentStream.endText();
            }
            resultSet.close();
            statement.close();

            // Dibujar la línea horizontal
            contentStream.moveTo(50, textStartY - 150);
            contentStream.lineTo(page.getMediaBox().getWidth() - 50, textStartY - 150);
            contentStream.stroke();

            // Definir la fuente y el tamaño del texto para el agradecimiento ("Muchas Gracias!")
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 28);

            // Definir las coordenadas para comenzar a escribir el texto del agradecimiento
            float graciasX = (page.getMediaBox().getWidth() - PDType1Font.HELVETICA_BOLD.getStringWidth("Muchas Gracias!") / 1000f * 28) / 2;
            float graciasY = textStartY - 200;

            // Agregar el texto de agradecimiento al final del documento
            contentStream.beginText();
            contentStream.newLineAtOffset(graciasX, graciasY);
            contentStream.showText("Muchas Gracias!");
            contentStream.endText();

            // Cerrar el flujo de contenido
            contentStream.close();

            // Guardar el documento como archivo PDF
            document.save(pdfFileName);
            document.close();

            JOptionPane.showMessageDialog(null, "PDF generado correctamente", "PDF Generado", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el PDF", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    static class DatabaseConnection {
        private static final String DB_URL = "jdbc:mysql://192.168.15.130:3306/erronka3";
        private static final String USER = "5taldea";
        private static final String PASS = "1WMG2024";

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
