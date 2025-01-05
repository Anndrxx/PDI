package modelo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 *
 * @author André García Carrizoza
 */

public class ExtendedImage {
    private String path;
    private String nombreArchivo;
    private Image imagen;
    private BufferedImage bufferedImagen;
    private int tipo;
    
    public boolean equals(ExtendedImage auxiliar){
        int altoAuxiliar=auxiliar.bufferedImagen.getHeight();
        int anchoAuxiliar=auxiliar.bufferedImagen.getWidth();
        int altoOriginal=this.bufferedImagen.getHeight();
        int anchoOriginal=this.bufferedImagen.getWidth();
        
        if(altoAuxiliar!=altoOriginal || anchoAuxiliar!=anchoOriginal) return false;
        
        int pixel1;
        int pixel2;
        
        for(int y=0; y<altoOriginal; y++) {
            for(int x=0; x<anchoOriginal; x++) {
                pixel1 = auxiliar.bufferedImagen.getRGB(x,y);
                pixel2 = this.bufferedImagen.getRGB(x,y);
                if(pixel1!=pixel2)return false;
            }
        }
        
        return true;
    }
    
    @Override
    public String toString(){
      String cadena = String.format("path   : %s\n" +
                      "nombreArchivo   : %s\n" +
                      "imagen :   %s\n" +
                      "bufferedImagen     : %s\n" +
                      "tipo    : %d",
              path, nombreArchivo, imagen, bufferedImagen, tipo);
      return cadena; 
    }
    
    public ExtendedImage(BufferedImage bufferedImagen) {
        this.path=null;
        this.nombreArchivo=null;
        
        int ancho = bufferedImagen.getWidth();
        int alto = bufferedImagen.getHeight();
        this.imagen = bufferedImagen.getScaledInstance(ancho, alto, BufferedImage.SCALE_DEFAULT);
        this.bufferedImagen=bufferedImagen;
        this.tipo = this.bufferedImagen.getType();
    }
    
    public ExtendedImage(String path, String sArchivo) {
        this.imagen = null;
        this.bufferedImagen = null;
        this.path = path;
        this.nombreArchivo = sArchivo;
        try {
            String acceso = this.path + this.nombreArchivo;
            //DEBUG
            System.out.println("Acceso: " + acceso);
            File input = new File(acceso);
            bufferedImagen = ImageIO.read(input);
            tipo = bufferedImagen.getType();
        } catch(IOException ioe) {
            System.err.println(ioe);
        }
        int ancho = bufferedImagen.getWidth();
        int alto = bufferedImagen.getHeight();
        imagen = bufferedImagen.getScaledInstance(ancho, alto, BufferedImage.SCALE_DEFAULT);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    
    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public BufferedImage getBufferedImagen() {
        return bufferedImagen;
    }

    public void setBufferedImagen(BufferedImage bufferedImagen) {
        this.bufferedImagen = bufferedImagen;
    }
    
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    public int getAncho() {
        return imagen.getWidth(null);
    }
    public int getAlto() {
        return imagen.getHeight(null);
    }
    public int getAnchoBufferedImage() {
        return bufferedImagen.getWidth();
    }
    public int getAltoBufferedImage() {
        return bufferedImagen.getHeight();
    }
}
