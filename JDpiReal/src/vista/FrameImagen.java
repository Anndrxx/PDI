package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import modelo.ExtendedImage;

/**
 *
 * @author Saul De La O
 */
public class FrameImagen extends JFrame {
    private ExtendedImage filledImage;
    private PanelImagen panel;
    private JButton boton = new JButton("Seleccionar esta imagen");
            
    public FrameImagen(){
    }
    
    public FrameImagen(String path, String sArchivo, ImageFrameHistory history, boolean[] flag ) {
        ExtendedImage imagenCargada = new ExtendedImage(path, sArchivo);
        FrameImagen help = new FrameImagen();
        help.filledImage = imagenCargada;
        
        if(history.contiene(help)){
            JOptionPane.showMessageDialog(
            null,                         
            "La imagen que quieres cargar ya esta disponible.",      
            "Error",                     
            JOptionPane.ERROR_MESSAGE     
            );
            
            flag[0]= false;
            return;
        }
        
        this.filledImage = imagenCargada;
        int ancho = imagenCargada.getImagen().getWidth(null);
        int alto = imagenCargada.getImagen().getHeight(null);
        setTitle("Visor de imagen " + ancho + " x " + alto + " pixeles.");
        initComponents(imagenCargada.getImagen());
        
        FrameImagen frameCopia = this;
        
        this.addWindowListener(new WindowAdapter() {
            FrameImagen frameCopiaAuxiliar = frameCopia;
            @Override
            public void windowClosing(WindowEvent e) {
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                System.out.println("Cerrada");
                history.elimina(history.get(history.getIndiceFromButton(boton)));
            }
        });
    }   
    
    public FrameImagen(BufferedImage bufferedImagen, ImageFrameHistory history, boolean[] flag ) {
        ExtendedImage imagenProcesada = new ExtendedImage(bufferedImagen);
        FrameImagen help = new FrameImagen();
        help.filledImage = imagenProcesada;
        
        if(history.contiene(help)){
            JOptionPane.showMessageDialog(
            null,                         
            "La imagen que quieres cargar ya esta disponible",      
            "Error",                     
            JOptionPane.ERROR_MESSAGE     
            );
            flag[0] = false;
            return;
        }
        this.filledImage = imagenProcesada;
        int ancho = imagenProcesada.getImagen().getWidth(null);
        int alto = imagenProcesada.getImagen().getHeight(null);
        setTitle("Visor de imagen " + ancho + " x " + alto + " pixeles.");
        initComponents(imagenProcesada.getImagen());
    }
    
    public JButton getBoton(){
        return this.boton;
    }
    
    private void initComponents(Image imagen) {
        Container contenedor = this.getContentPane();
        contenedor.setLayout(new BorderLayout());
        
        panel = new PanelImagen(imagen);
        contenedor.add(panel, BorderLayout.CENTER);
        
        contenedor.add(boton, BorderLayout.SOUTH);
        
        this.setSize(imagen.getWidth(null), imagen.getHeight(null) + 50);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
    
    public boolean equals(FrameImagen auxiliar){
       boolean flag = this.filledImage.equals(auxiliar.filledImage);
       return flag;
    }
    
    @Override
    public String toString(){
      String cadena = this.filledImage.toString();
      return cadena; 
    }
    
    public ExtendedImage getFilledImage(){
        return this.filledImage;
    }
    
    public PanelImagen getPanelImagen(){
        return this.panel;
    }
}
