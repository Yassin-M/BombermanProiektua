package Bista;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Eredua.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

@SuppressWarnings({"deprecation","removal"})

public class IkusiBeharrekoa extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLayeredPane layeredPane;
	private JLabel fondoLabel;
	private Controler controler=null;
	private JLabel puntuazioa;
	private JPanel panel_1;
	private JPanel panel_2;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public IkusiBeharrekoa() {
		setBounds(100, 100, 800, 600);
		setTitle("Bomberman");
		setIconImage(new ImageIcon(getClass().getResource("/Bista/irudiak/whitewithbomb1.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getJPanel_1(), BorderLayout.NORTH);
		getContentPane().add(getLayeredPane_1(), BorderLayout.CENTER);
		Laberintoa.getNireLaberintoa().addObserver(this);
		addKeyListener(getControler());
		setFocusable(true);
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(11, 17, 0, 0));
			panel.setOpaque(false);
			addComponentListener(new ComponentAdapter() {
			   @Override
			   public void componentResized(ComponentEvent e) {  
				   panel.setSize(getWidth(),layeredPane.getHeight());
				   panel.revalidate();
				   panel.repaint();
			   }
			  });
			
		}
		return panel;
	}
	
	public JLabel Fondoa() {
        setSize(663, 429 + 38);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel fondoLabel = new JLabel() {
            private static final long serialVersionUID = 2028779831537105750L;
			private Image fondo = new ImageIcon(getClass().getResource("/Bista/irudiak/stageBack1.png")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                fondoLabel.setSize(getWidth(), getHeight());
                fondoLabel.repaint();
            }
        });
        
        fondoLabel.setBounds(0, 0, getWidth(), getHeight());
        fondoLabel.setOpaque(false);
        return fondoLabel;
	}
	
	private JLabel getPuntuazioa() {
		if (puntuazioa == null) {
			puntuazioa = new JLabel("Puntuazioa: 0");
		}
		return puntuazioa;
	}
	
	private JPanel getJPanel_1() {
		if (panel_1==null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new GridLayout(1, 1, 0, 0));
			panel_1.setPreferredSize(new Dimension(getWidth(), getHeight()/20));
			panel_1.setSize(panel_1.getPreferredSize());
			JLabel puntuazioa = getPuntuazioa();
			panel_1.add(puntuazioa);
			addComponentListener(new ComponentAdapter() {
                   @Override
                   public void componentResized(ComponentEvent e) {
                	   panel_1.setPreferredSize(new Dimension(getWidth(), getHeight()/20));
                	   panel_1.setSize(panel_1.getPreferredSize());
                	   puntuazioa.setSize(panel_1.getPreferredSize());
                       panel_1.revalidate();
                       panel_1.repaint();
                   }
			});
		}
		return panel_1;
	}

	private JLayeredPane getLayeredPane_1() {
		if (layeredPane == null) {
			layeredPane = new JLayeredPane();
			layeredPane.setLayout(null);
			layeredPane.setPreferredSize(new Dimension(getWidth(),getHeight()*19/20));
			layeredPane.setSize(layeredPane.getPreferredSize());
			//layeredPane.setSize(getWidth(), getContentPane().getHeight()*11/12);
			fondoLabel = Fondoa();
			layeredPane.add(fondoLabel, new Integer(0));
			
			panel = getPanel();
			layeredPane.add(panel, new Integer(2));
			
			addComponentListener(new ComponentAdapter() {
			   @Override
			   public void componentResized(ComponentEvent e) {
				   layeredPane.setPreferredSize(new Dimension(getWidth(),getHeight()*19/20));
				   layeredPane.setSize(layeredPane.getPreferredSize());
				   fondoLabel.setSize(getWidth(), getHeight()*19/20);
				   fondoLabel.repaint();
			   }
			  });
			
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
	
	private class Controler implements KeyListener {
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
		
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Laberintoa) {
			for (int i = 0; i < 11; i++) {
				for (int j = 0; j < 17; j++) {
					String irudia = null;
					GelaxkaBista gelaxka = new GelaxkaBista(i,j);
					
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
							irudia = "/Bista/irudiak/whitedown1.png";
							break;
						case null:
							break;
					default:
						System.out.println("Errorea: hasieran "+Laberintoa.getNireLaberintoa().zerDago(i,j).toString()+" gelaxka aurkitu da." );
						break;
					}
					if(irudia!=null) {
						gelaxka.setIcon(new ImageIcon(Gelaxka.class.getResource(irudia)));	
					}
					panel.add(gelaxka);
				}
			}
		}
	}
}

