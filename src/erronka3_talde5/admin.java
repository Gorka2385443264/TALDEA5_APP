package erronka3_talde5;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class admin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    admin frame = new admin();
                    frame.setSize(800, 500);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public admin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Admin ikuspegiak");
        lblNewLabel.setBounds(345, 14, 112, 13);
        contentPane.add(lblNewLabel);
        
        JButton btnAtras = new JButton("Atras");
        btnAtras.addActionListener(e -> {
            // Crear una instancia de la ventana principal
            main ventanaMain = new main();
            // Hacer visible la ventana principal
            ventanaMain.setVisible(true);
            // Cerrar la ventana actual de mantenitze
            dispose(); 
        });
        
        btnAtras.setBounds(10, 10, 76, 21);
        contentPane.add(btnAtras);
        
        JButton btnLangileaKudeatu = new JButton("Langileak Kudeatu");
        btnLangileaKudeatu.setBounds(47, 65, 152, 21);
        contentPane.add(btnLangileaKudeatu);
        btnLangileaKudeatu.addActionListener(e -> {
            erregistroakAdmin ventanaAdmin = new erregistroakAdmin();
            ventanaAdmin.setSize(getSize());
            ventanaAdmin.setLocationRelativeTo(null);
            ventanaAdmin.setVisible(true);
            dispose();
        });
        
        JButton btnMantenutzaileaKudeatu = new JButton("Mantenutzailea kudeatu");
        btnMantenutzaileaKudeatu.setBounds(47, 138, 196, 21);
        contentPane.add(btnMantenutzaileaKudeatu);
        btnMantenutzaileaKudeatu.addActionListener(e -> {
            erregistroakAdmin ventanaErreAdmin = new erregistroakAdmin();
            ventanaErreAdmin.setSize(getSize());
            ventanaErreAdmin.setLocationRelativeTo(null);
            ventanaErreAdmin.setVisible(true);
            dispose();
        });
        
        JButton btnNewButton = new JButton("Mantenitzaile ikuspegia");
        btnNewButton.setBounds(283, 138, 208, 21);
        contentPane.add(btnNewButton);
        btnNewButton.addActionListener(e -> {
            mantenitze ventanaMantenua = new mantenitze();
            ventanaMantenua.setSize(getSize());
            ventanaMantenua.setLocationRelativeTo(null);
            ventanaMantenua.setVisible(true);
            dispose();
        });
        
        JButton btnLangilea = new JButton("Langilea ikuspegia");
        btnLangilea.setBounds(313, 77, 162, 21);
        contentPane.add(btnLangilea);
        btnLangilea.addActionListener(e -> {
            langilea ventanaLangilea = new langilea();
            ventanaLangilea.setSize(getSize());
            ventanaLangilea.setLocationRelativeTo(null);
            ventanaLangilea.setVisible(true);
            dispose();
        });
    }
}
