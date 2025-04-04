package Bista;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Eredua.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import java.awt.Graphics;
import javax.swing.JLayeredPane;
import java.awt.Font;

@SuppressWarnings({"deprecation","removal"})

public class IkusiBeharrekoa extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLayeredPane layeredPane;
	private nireFondoa fondoLabel;
	private Controler controler=null;

	public IkusiBeharrekoa() {
		setBounds(100, 100, 450, 300);
        setTitle("Bomberman");
		setIconImage(new ImageIcon(getClass().getResource("/Bista/irudiak/whitewithbomb1.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getLayeredPane_1(), BorderLayout.CENTER);
		Laberintoa.getNireLaberintoa().addObserver(this);
		addKeyListener(getControler());
		addComponentListener(getControler());
		setFocusable(true);
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(11, 17, 0, 0));
			panel.setOpaque(false);
		}
		return panel;
	}

	private class nireFondoa extends JLabel {

		private static final long serialVersionUID = 6874299854785398384L;
		private Image fondo = null;
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
        
		protected void setImage(String mapaMota) {
			fondo = new ImageIcon(getClass().getResource(mapaMota)).getImage();
		}
	}
	
	public nireFondoa Fondoa(String mapaMota) {
		setSize(663, 429 + 38);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    nireFondoa fondoLabel = new nireFondoa();
	    fondoLabel.setBounds(0, 0, getWidth(), getHeight());
	    fondoLabel.setOpaque(false);
        fondoLabel.setImage(mapaMota);
        
        return fondoLabel;
	}

	private JLayeredPane getLayeredPane_1() {
		if (layeredPane == null) {
			layeredPane = new JLayeredPane();
			layeredPane.setLayout(null);
			fondoLabel = Fondoa("/Bista/irudiak/stageBack1.png");
			fondoLabel.setBounds(0, 0, getWidth(), getHeight());
			layeredPane.add(fondoLabel, new Integer(0));

			panel = getPanel();
			panel.setBounds(0, 0, getWidth(), getHeight());
			layeredPane.add(panel, new Integer(1));
		}
		return layeredPane;
	}
	//KONTROLADOREA
	private Controler getControler() {
        if (controler == null) {
            controler = new Controler();
        }
        return controler;
	}
	
	private class Controler extends ComponentAdapter implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
	        switch (key) {
	            case KeyEvent.VK_UP:
	                Laberintoa.getNireLaberintoa().jokalariaMugituGora();
	                break;
	            case KeyEvent.VK_DOWN:
	                Laberintoa.getNireLaberintoa().jokalariaMugituBehera();
	                break;
	            case KeyEvent.VK_LEFT:
	                Laberintoa.getNireLaberintoa().jokalariaMugituEzkerretara();
	                break;
	            case KeyEvent.VK_RIGHT:
	                Laberintoa.getNireLaberintoa().jokalariaMugituEskuinera();
	                break;
	            case KeyEvent.VK_SPACE:
	            	Laberintoa.getNireLaberintoa().getJokalaria().bombaJarri();
	            	break;
	        }
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void componentResized(ComponentEvent e) {
            //panel
			panel.setSize(getWidth(), getHeight() - (getInsets().top + getInsets().bottom));
			panel.revalidate();
			panel.repaint();
			//fondoa
			fondoLabel.setSize(getWidth(), getHeight());
			fondoLabel.repaint();
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Laberintoa && arg instanceof Integer) {
			Integer mapa = (Integer) arg;
			switch (mapa) {
				case 1:
					fondoLabel.setImage("/Bista/irudiak/stageBack1.png");
					break;
				case 2:
					fondoLabel.setImage("/Bista/irudiak/stageBack3.png");
					break;
				case 3:
					fondoLabel.setImage("/Bista/irudiak/stageBack2.png");
					break;
				default:
					break;
			}
			for (int i = 0; i < 11; i++) {
				for (int j = 0; j < 17; j++) {
					String irudia = null;
					//ALDATU BEHAR - EZIN DA HEMENDIK EREDURA DEITU
					switch (Laberintoa.getNireLaberintoa().zerDago(i,j)) {
			            case BLOKEBIGUNA:
			            	irudia = "/Bista/irudiak/soft1.png";
							break;
						case BLOKEGOGORRA:
							irudia = "/Bista/irudiak/hard1.png";
							break;
						case ETSAIA:
							irudia = "/Bista/irudiak/pass1.png";
							break;
						case JOKALARIA:
							if(Laberintoa.getNireLaberintoa().getJokalaria() instanceof JokZuria) {
								irudia = "/Bista/irudiak/whitedown1.png";
							}else {
								irudia = "/Bista/irudiak/blackdown1.png";
							}
							break;
						case null:
							break;
					default:
						System.out.println("Errorea: hasieran "+Laberintoa.getNireLaberintoa().zerDago(i,j).toString()+" gelaxka aurkitu da." );
						break;
					}
					GelaxkaBista gelaxka = new GelaxkaBista(i,j,irudia);
					if(irudia!=null) {
						gelaxka.setIcon(new ImageIcon(Gelaxka.class.getResource(irudia)));	
					}
					panel.add(gelaxka);
				}
			}
		}
		if (o instanceof Laberintoa && arg instanceof Boolean) {
			boolean irabazi = (boolean) arg;
			if (irabazi) {
				JLabel lblZorionak = new JLabel("ZORIONAK!");
				lblZorionak.setFont(new Font("Dialog", Font.BOLD, 65));
				lblZorionak.setBounds(0, 0, 663, 418);
				layeredPane.add(lblZorionak, new Integer(2));
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			dispose();
			Amaitu as = new Amaitu();
			as.setVisible(true);
		}
	}
	
}

