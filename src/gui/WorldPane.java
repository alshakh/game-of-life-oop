package gui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.JPanel;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public class WorldPane extends javax.swing.JPanel {

	private final Viewport viewport;
	

	/**
	 * Creates new form WorldPane
	 *
	 * @param viewport
	 */
	public WorldPane(final Viewport viewport) {
		
		this.viewport = viewport;
		
		final JPanel thisObj = this;
		MouseAdapter ma = new MouseAdapter() {
			Point oldPos = null;
			
			@Override
			public void mouseClicked(MouseEvent me) {
				viewport.clicked(me.getPoint());
				thisObj.repaint();
			}
			
			@Override
			public void mouseDragged(MouseEvent me) {
				if(oldPos == null) {
					oldPos = me.getPoint();
				} else {
					Point newPos = me.getPoint();
					
					viewport.dragged(oldPos, me.getPoint());
					oldPos = me.getPoint();
				}
				thisObj.repaint();
			}
		
			@Override
			public void mouseWheelMoved(MouseWheelEvent me) {
				boolean direction;
				if(me.getWheelRotation()<0) {
					direction = Viewport.WHEEL_DOWN;
				} else {
					direction = Viewport.WHEEL_UP;
				}
				viewport.wheeled(me.getPoint(), me.getScrollAmount(), direction);
				thisObj.repaint();
			}
			@Override
			public void mouseReleased(MouseEvent me) {
				oldPos = null;
			}
		};
		addMouseListener(ma);
		addMouseWheelListener(ma);
		addMouseMotionListener(ma);
		
		//initComponents();
	}

	@Override
	public void paintComponent(Graphics g) {
		if(!viewport.sizeInited()) {
			viewport.setSize(this.getSize());
		}
		viewport.paintView(g);
	}

	/**
	 * This method is called from within the constructor to initialize the
	 * form. WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
                );
        }// </editor-fold>//GEN-END:initComponents

        // Variables declaration - do not modify//GEN-BEGIN:variables
        // End of variables declaration//GEN-END:variables
}
