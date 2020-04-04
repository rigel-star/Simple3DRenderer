package ramesh.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainRenderer implements ChangeListener {

	Graphics2D g2;
	SimpleRenderer sr = new SimpleRenderer();
	List<Triangle> tris;
	JSlider headingSlider;
	JSlider pitchSlider;
	JPanel renderPanel;
	
	public MainRenderer() {
		tris = sr.getNodes();
		
		JFrame frame = new JFrame();
        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());

        // slider to control horizontal rotation
        headingSlider = new JSlider(0, 360, 180);
        headingSlider.addChangeListener(this);
        pane.add(headingSlider, BorderLayout.SOUTH);

        // slider to control vertical rotation
        pitchSlider = new JSlider(SwingConstants.VERTICAL, -90, 90, 0);
        pitchSlider.addChangeListener(this);
        pane.add(pitchSlider, BorderLayout.EAST);

        // panel to display render results
        renderPanel = new JPanel() {
                public void paintComponent(Graphics g) {
                    g2 = (Graphics2D) g;
                    g2.setColor(Color.BLACK);
                    g2.fillRect(0, 0, getWidth(), getHeight());

                    g2.translate(getWidth() / 2, getHeight() / 2);
                    g2.setColor(Color.WHITE);
                    
                    //left to right rotation
                    double heading = Math.toRadians(headingSlider.getValue());
            		Matrix3 transform = new Matrix3(new double[] {
            		        Math.cos(heading), 0, -Math.sin(heading),
            		        0, 1, 0,
            		        Math.sin(heading), 0, Math.cos(heading)
            		    });
            		
            		//top to bottom rotation
            		double pitch = Math.toRadians(pitchSlider.getValue());
            		Matrix3 pitchTransform = new Matrix3(new double[] {
            		        1, 0, 0,
            		        0, Math.cos(pitch), Math.sin(pitch),
            		        0, -Math.sin(pitch), Math.cos(pitch)
            		    });
            		Matrix3 transform2 = transform.multiply(pitchTransform);

            		g2.translate(0, 0);
            		g2.setColor(Color.WHITE);
            		for (Triangle t : tris) {
            		    Vertex v1 = transform2.transform(t.v1);
            		    Vertex v2 = transform2.transform(t.v2);
            		    Vertex v3 = transform2.transform(t.v3);
            		    Path2D path = new Path2D.Double();
            		    path.moveTo(v1.x, v1.y);
            		    path.lineTo(v2.x, v2.y);
            		    path.lineTo(v3.x, v3.y);
            		    path.closePath();
            		    g2.draw(path);
            		}
                }
            };
        pane.add(renderPanel, BorderLayout.CENTER);

        frame.setSize(400, 400);
        frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		new MainRenderer();
		
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		
		if(e.getSource() == this.headingSlider) {
			//this.rotateMatrix();
			this.renderPanel.repaint();
		}
		else if(e.getSource() == this.pitchSlider) {
			//this.rotateMatrix();
			this.renderPanel.repaint();
		}
		
	}

}
