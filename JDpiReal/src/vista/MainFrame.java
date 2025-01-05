package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import modelo.ImageProcessing;

/**
 *
 * @author André García Carrizoza
 */

public class MainFrame extends JFrame {

    private final JDesktopPane emergentPane;
    private ImageFrameHistory history = new ImageFrameHistory();
    private FrameImagen apuntador;

    public MainFrame() {
        setTitle("Procesamiento de Imágenes");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        emergentPane = new JDesktopPane();
        emergentPane.setOpaque(false);
        add(emergentPane, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuArchivo = new JMenu("Archivo");
        JMenu menuProcesar = new JMenu("Procesar Imagen");
        JMenu menuBrilloContraste = new JMenu("Brillo y Contraste");

        JMenuItem menuItemCargar = new JMenuItem("Cargar Imagen");
        JMenuItem menuItemExtraerRojo = new JMenuItem("Extraer Canal Rojo");
        JMenuItem menuItemExtraerAzul = new JMenuItem("Extraer Canal Azul");
        JMenuItem menuItemExtraerVerde = new JMenuItem("Extraer Canal Verde");
        JMenuItem menuItemEscalaGrises = new JMenuItem("Convertir a Escala de Grises");
        JMenuItem menuItemBrillo = new JMenuItem("Subir/Bajar Brillo");
        JMenuItem menuItemContraste = new JMenuItem("Subir/Bajar Contraste");

        menuArchivo.add(menuItemCargar);

        menuProcesar.add(menuItemExtraerRojo);
        menuProcesar.add(menuItemExtraerAzul);
        menuProcesar.add(menuItemExtraerVerde);
        menuProcesar.add(menuItemEscalaGrises);

        menuBrilloContraste.add(menuItemBrillo);
        menuBrilloContraste.add(menuItemContraste);

        menuBar.add(menuArchivo);
        menuBar.add(menuProcesar);
        menuBar.add(menuBrilloContraste);

        setJMenuBar(menuBar);

        setSize(800, 600);
        getContentPane().setBackground(new Color(240, 240, 240)); 
        
        menuArchivo.setFont(new Font("Arial", Font.BOLD, 14));
        menuProcesar.setFont(new Font("Arial", Font.BOLD, 14));
        menuBrilloContraste.setFont(new Font("Arial", Font.BOLD, 14));

        menuItemBrillo.addActionListener(v -> System.out.println(history.toString()));
        
        menuItemCargar.addActionListener(e -> {
            ImageProcessing.chargeImage(history);
            this.apuntador=history.get(0);
            history.getPrimero().getBoton().addActionListener(new ActionListener() {
                JButton ans = history.getPrimero().getBoton();
                
                @Override
                public void actionPerformed(ActionEvent e) {
                apuntador=history.get(history.getIndiceFromButton(ans)); 
                String cadena=apuntador.toString();
                System.out.println(cadena);
                }
            });
        });
        
        menuItemExtraerRojo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent v) {
                if (apuntador == null) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Seleccione una imagen.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                } else {
                    ImageProcessing.redScale(apuntador.getFilledImage().getBufferedImagen(), history);
                    System.out.println(history.toString());
                }
                
                history.getUltimo().getBoton().addActionListener(new ActionListener() {
                    JButton ans = history.getUltimo().getBoton();
                
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    apuntador=history.get(history.getIndiceFromButton(ans)); 
                    String cadena=apuntador.toString();
                    System.out.println(cadena);
                    }
                });
                
            }
        });
        
        menuItemExtraerVerde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent v) {
                if (apuntador == null) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Seleccione una imagen.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                } else {
                    ImageProcessing.greenScale(apuntador.getFilledImage().getBufferedImagen(), history);
                    System.out.println(history.toString());
                }
                
                history.getUltimo().getBoton().addActionListener(new ActionListener() {
                    JButton ans = history.getUltimo().getBoton();
                
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    apuntador=history.get(history.getIndiceFromButton(ans)); 
                    String cadena=apuntador.toString();
                    System.out.println(cadena);
                    }
                });
                
            }
        });
        
        menuItemExtraerAzul.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent v) {
                if (apuntador == null) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Seleccione una imagen.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                } else {
                    ImageProcessing.blueScale(apuntador.getFilledImage().getBufferedImagen(), history);
                    System.out.println(history.toString());
                }
                
                history.getUltimo().getBoton().addActionListener(new ActionListener() {
                    JButton ans = history.getUltimo().getBoton();
                
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    apuntador=history.get(history.getIndiceFromButton(ans)); 
                    String cadena=apuntador.toString();
                    System.out.println(cadena);
                    }
                });
                
            }
        });        
        
     // Descomentar cuando se implementen los métodos de procesamiento de imágenes
         //menuItemExtraerRojo.addActionListener(e -> ImageProcessing.redScale());
         //menuItemExtraerVerde.addActionListener(e -> ImageProcessing.greenScale());
         //menuItemExtraerAzul.addActionListener(e -> ImageProcessing.blueScale());
         //menuItemEscalaGrises.addActionListener(e -> ImageProcessing.grayScale());
         //menuItemBrillo.addActionListener(e -> ImageProcessing.brightness());
         //menuItemContraste.addActionListener(e -> ImageProcessing.contrast());
    }

}



