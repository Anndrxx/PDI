package vista;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.image.BufferedImage;

import modelo.ImageProcessing;

/**
*
* @author André García Carrizoza
*/

public class BrightnessFrame extends JFrame {
	
    private JSlider slider = new JSlider(JSlider.HORIZONTAL, -256, 256, 0);
    private JLabel imageLabel;
    private JPanel panel = new JPanel();
    private int proceso;

    public int getProceso() {
    	return this.proceso;
    }

    public JPanel getPanel() {
    	return this.panel;
    }
    
    public JSlider getSlider() {
    	return this.slider;
    }
    
    public JLabel getImageLabel() {
    	return this.imageLabel;
    }
	
	public BrightnessFrame(BufferedImage input) {

	    this.slider.setMajorTickSpacing(50);
	    this.slider.setMinorTickSpacing(10);
	    this.slider.setPaintTicks(true);
	    this.slider.setPaintLabels(true);

	    this.imageLabel = new JLabel(new ImageIcon(input));
	    
	    this.panel.setLayout(new BorderLayout());
	    this.panel.add(slider, BorderLayout.SOUTH);
	    this.panel.add(imageLabel, BorderLayout.CENTER);
	    
	    
	    slider.addChangeListener(new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent e) {
	            int brightness = slider.getValue();
	            BufferedImage ans = ImageProcessing.brightnessAuxiliar(brightness, input);
	            imageLabel.setIcon(new ImageIcon(ans));
	        }
	    });
	    
	    this.proceso = JOptionPane.showConfirmDialog(this, panel,"Brillo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    
	}

}
