package modelo;

import java.awt.Color;
import java.awt.image.BufferedImage;
import vista.ImageFrameHistory;
import vista.FrameImagen;
/**
 *
 * @author André García Carrizoza
 */

public class ImageProcessing {
    
    public static void blueScale(BufferedImage input, ImageFrameHistory history){
        int alto = input.getHeight();
        int ancho = input.getWidth();
        int pixel;
        
        int [][] imagenInt = new int[alto][ancho];
        
        for(int y=0; y<alto; y++) {
            for(int x=0; x<ancho; x++) {
                pixel = input.getRGB(x,y);
                int azul  =  pixel & 0x000000ff;
                
                Color color = new Color(0, 0, azul);
                imagenInt[y][x] = color.getRGB();
        
            }  
        }
        
        boolean[] flag = {true};
        BufferedImage bufferedImage=convertirMatrizABuffered(imagenInt);
        FrameImagen frame = new FrameImagen(bufferedImage, history, flag);
                
        if(flag[0]){
            history.agregaFinal(frame);
        }
    }
    
    public static void greenScale(BufferedImage input, ImageFrameHistory history){
        int alto = input.getHeight();
        int ancho = input.getWidth();
        int pixel;
        
        int [][] imagenInt = new int[alto][ancho];
        
        for(int y=0; y<alto; y++) {
            for(int x=0; x<ancho; x++) {
                pixel = input.getRGB(x,y);
                int verde = (pixel & 0x0000ff00) >> 8;
               
                Color color = new Color(0, verde, 0);
                imagenInt[y][x] = color.getRGB();
        
            }  
        }
        boolean[] flag = {true};
        BufferedImage bufferedImage=convertirMatrizABuffered(imagenInt);
        FrameImagen frame = new FrameImagen(bufferedImage, history, flag);
                
        if(flag[0]){
            history.agregaFinal(frame);
        }
    }
    
    public static void redScale(BufferedImage input, ImageFrameHistory history){
        int alto = input.getHeight();
        int ancho = input.getWidth();
        int pixel;
        
        int [][] imagenInt = new int[alto][ancho];
        
        for(int y=0; y<alto; y++) {
            for(int x=0; x<ancho; x++) {
                pixel = input.getRGB(x,y);
                int rojo  = (pixel & 0x00ff0000) >> 16;

                Color color = new Color(rojo, 0, 0);
                imagenInt[y][x] = color.getRGB();
        
            }  
        }
        boolean[] flag = {true};
        BufferedImage bufferedImage=convertirMatrizABuffered(imagenInt);
        FrameImagen frame = new FrameImagen(bufferedImage, history, flag);
        
        
        if(flag[0]){
            history.agregaFinal(frame);
        }
    }
    
    public static void chargeImage(ImageFrameHistory history){
        ChooseFile imagenElegida = new ChooseFile();
        String path = imagenElegida.getPath() + "/";
        String archivoImagen = imagenElegida.getArchivoImagen();
        
        boolean[] flag = {true};
        FrameImagen lector = new FrameImagen(path, archivoImagen, history, flag);
        
        if(flag[0]){
            history.agregaInicio(lector);
            //String help = history.toString();
            //System.out.println(help);
        }
    }
    
    public static ExtendedImage contrast(int contrast, BufferedImage input){
        int alto = input.getHeight();
        int ancho = input.getWidth();
        int pixel;
        
        int [][] imagenInt = new int[alto][ancho];
        
        for(int y=0; y<alto;y++){
            for(int x=0;x<ancho;x++){
                pixel=input.getRGB(x, y);
                int rojo  = (pixel & 0x00ff0000) >> 16;
                int verde = (pixel & 0x0000ff00) >> 8;
                int azul  =  pixel & 0x000000ff;
                
                rojo = (rojo * contrast);
                if(rojo>255){
                    rojo=255;
                }else if(rojo<0){
                    rojo=0;
                }
                verde = (verde * contrast);
                if(verde>255){
                    verde=255;
                }else if(verde<0){
                    verde=0;
                }
                azul = (azul * contrast);
                if(azul>255){
                    azul=255;
                }else if(azul<0){
                    azul=0;
                }
                
                Color color = new Color(rojo, verde, azul);
                imagenInt[y][x] = color.getRGB();
                    
            }
        }
        
        BufferedImage bufferedImage=convertirMatrizABuffered(imagenInt);
        ExtendedImage IMAGE= new ExtendedImage(bufferedImage);
        return IMAGE;
    }
    
    public static ExtendedImage brightness(int brightness, BufferedImage input){
        int alto = input.getHeight();
        int ancho = input.getWidth();
        int pixel;
        
        int [][] imagenInt = new int[alto][ancho];
        
        for(int y=0; y<alto;y++){
            for(int x=0;x<ancho;x++){
                pixel=input.getRGB(x, y);
                int rojo  = (pixel & 0x00ff0000) >> 16;
                int verde = (pixel & 0x0000ff00) >> 8;
                int azul  =  pixel & 0x000000ff;
                
                rojo = (rojo + brightness);
                if(rojo>255){
                    rojo=255;
                }else if(rojo<0){
                    rojo=0;
                }
                verde = (verde + brightness);
                if(verde>255){
                    verde=255;
                }else if(verde<0){
                    verde=0;
                }
                azul = (azul + brightness);
                if(azul>255){
                    azul=255;
                }else if(azul<0){
                    azul=0;
                }
                
                Color color = new Color(rojo, verde, azul);
                imagenInt[y][x] = color.getRGB();
                    
            }
        }
        
        BufferedImage bufferedImage=convertirMatrizABuffered(imagenInt);
        ExtendedImage IMAGE= new ExtendedImage(bufferedImage);
        return IMAGE;
    }      
    
    public static void grayScale(BufferedImage input){
        int alto = input.getHeight();
        int ancho = input.getWidth();
        int pixel;
        
        int [][] imagenInt = new int[alto][ancho];
        
        for(int y=0; y<alto; y++) {
            for(int x=0; x<ancho; x++) {
                pixel = input.getRGB(x,y);
                int rojo  = (pixel & 0x00ff0000) >> 16;
                int verde = (pixel & 0x0000ff00) >> 8;
                int azul  =  pixel & 0x000000ff;
                               
                int gris = (rojo + verde + azul) / 3;
                
                if(gris>255) {
                    gris = 255;
                }
                if(gris<0) {
                    gris = 0;
                }
                Color color = new Color(gris, gris, gris);
                imagenInt[y][x] = color.getRGB();
        
            }  
        }
        BufferedImage bufferedImage=convertirMatrizABuffered(imagenInt);
        ExtendedImage IMAGE= new ExtendedImage(bufferedImage);
        return IMAGE;
    }
    
    
    public static BufferedImage convertirMatrizABuffered(int[][] matrizRGB) {
        int altura = matrizRGB.length;
        int ancho = matrizRGB[0].length;

        BufferedImage imagen = new BufferedImage(ancho, altura, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < ancho; x++) {
                // Cada valor en la matriz ya contiene el color en formato RGB (0xRRGGBB)
                int colorRGB = matrizRGB[y][x];
                imagen.setRGB(x, y, colorRGB);
            }
        }

        return imagen;
    }
}

