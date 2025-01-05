/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 *
 * @author André García Carrizoza
 */
public class ChooseFile {
    private JFileChooser chooser;
    private File file;
    private final FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images","jpg", "jpeg");
    private String path;
    private String archivoImagen;
    
    public ChooseFile(){
        this.chooser = new JFileChooser();
        this.chooser.setFileFilter(this.filter);
        int returnValue = this.chooser.showOpenDialog(this.chooser);
        
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            this.file = chooser.getSelectedFile();
            this.path=file.getParent();
            this.archivoImagen=file.getName();
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }
    }
    
    public File getFile(){
        return this.file;
    }
    
    public String getPath(){
        return this.path;
    }
    
    public String getArchivoImagen(){
        return this.archivoImagen;
    }
}
   
