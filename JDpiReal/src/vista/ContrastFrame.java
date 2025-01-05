package vista;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.image.BufferedImage;

import modelo.ImageProcessing;

public class ContrastFrame extends JFrame {
    private JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 20, 0);
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
	
	public ContrastFrame(BufferedImage input) {

	    this.slider.setMajorTickSpacing(1);
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
	            BufferedImage ans = ImageProcessing.contrastAuxiliar(brightness, input);
	            imageLabel.setIcon(new ImageIcon(ans));
	        }
	    });
	    
	    this.proceso = JOptionPane.showConfirmDialog(this, panel,"Contraste", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    
	}
}
