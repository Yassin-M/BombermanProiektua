package Bista;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Eredua.*;
import Eredua.Jokalaria;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JLayeredPane;

public class IkusiBeharrekoa extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLayeredPane layeredPane;
	private JLabel fondoLabel;
	private Controler controler;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public IkusiBeharrekoa() {
		setBounds(100, 100, 450, 300);
        setTitle("Bomberman");
		setIconImage(new ImageIcon(getClass().getResource("/Bista/irudiak/whitewithbomb1.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getLayeredPane_1(), BorderLayout.CENTER);
		/*addComponentListener(new ComponentAdapter() {
			   @Override
			   public void componentResized(ComponentEvent e) {
				   layeredPane.setSize(getWidth(), getHeight());
				   panel.setSize(getWidth(), getHeight() - (getInsets().top + getInsets().bottom));
				   panel.revalidate();
				   panel.repaint();
				   fondoLabel.setSize(getWidth(), getHeight());
				   fondoLabel.repaint();
			   }
			  });*/
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(11, 17, 0, 0));
			panel.setOpaque(false);
			labirintoaBistaratu();
			addComponentListener(new ComponentAdapter() {
			   @Override
			   public void componentResized(ComponentEvent e) {  
				   panel.setSize(getWidth(), getHeight() - (getInsets().top + getInsets().bottom));
				   panel.revalidate();
				   panel.repaint();
			   }
			  });
			
		}
		return panel;
	}
	
	public void labirintoaBistaratu() {
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 17; j++) {
				String irudia = null;
				Gelaxka gelaxka = new Gelaxka(i,j);
				
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
	
	public JLabel Fondoa() {
        setSize(663, 429 + 38);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel fondoLabel = new JLabel() {
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

	private JLayeredPane getLayeredPane_1() {
		if (layeredPane == null) {
			layeredPane = new JLayeredPane();
			layeredPane.setLayout(null);
			fondoLabel = Fondoa();
			fondoLabel.setBounds(0, 0, getWidth(), getHeight());
			layeredPane.add(fondoLabel, new Integer(0));

			panel = getPanel();
			panel.setBounds(0, 0, getWidth(), getHeight());
			layeredPane.add(panel, new Integer(1));
			
			addComponentListener(new ComponentAdapter() {
			   @Override
			   public void componentResized(ComponentEvent e) {
				   fondoLabel.setSize(getWidth(), getHeight());
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
			Laberintoa.getNireLaberintoa().bombaJarri(Laberintoa.getNireLaberintoa().getJokalaria().getXposizioa(), Laberintoa.getNireLaberintoa().getJokalaria().getYposizioa(), true);
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
		if (o instanceof Jokalaria && arg instanceof String) {
			
		}
		
	}
	
	private class Gelaxka extends JLabel implements Observer{
		int lerroa;
		int zutabea;
		
		public Gelaxka(int pLerroa, int pZutabea) {
			this.lerroa = pLerroa;
			this.zutabea = pZutabea;
			this.setEnabled(true);
			addComponentListener(new ComponentAdapter() {
			    @Override
			    public void componentResized(ComponentEvent e) {
			        	setSize(getWidth(), getHeight());
			        	ImageIcon icon = (ImageIcon) getIcon();
						if (icon != null) {
							Image img = icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
							setIcon(new ImageIcon(img));
						}
			    }
			});
			addKeyListener(getControler());
			setFocusable(true);
			Laberintoa.getNireLaberintoa().getGelaxka(pLerroa, pZutabea).addObserver(this);
		}
		
		@Override
	    public void update(Observable o, Object arg) {
	        if (o instanceof Eredua.Gelaxka && arg instanceof int[]) {
	            int[] pos = (int[]) arg;
	            if (pos[0] == lerroa && pos[1] == zutabea) {
	                // Actualiza la gelaxka
	                String irudia = "/Bista/irudiak/whitedown1.png";
	                setIcon(new ImageIcon(Gelaxka.class.getResource(irudia)));
	            }
	        }
		if (o instanceof Eredua.Gelaxka) {
                	String irudia = "/Bista/irudiak/bomb1.png";
                	setIcon(new ImageIcon(Gelaxka.class.getResource(irudia)));
	        }
	    }
	}
}
