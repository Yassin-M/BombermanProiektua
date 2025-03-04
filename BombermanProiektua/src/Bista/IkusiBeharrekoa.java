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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
	private JLabel jokalariaLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IkusiBeharrekoa frame = new IkusiBeharrekoa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		addComponentListener(new ComponentAdapter() {
			   @Override
			   public void componentResized(ComponentEvent e) {
				   layeredPane.setSize(getWidth(), getHeight());
				   panel.setSize(getWidth(), getHeight() - (getInsets().top + getInsets().bottom));

				   panel.revalidate();
				   panel.repaint();
			   }
			  });
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(11, 17, 0, 0));
			panel.setOpaque(false);
			labirintoaBistaratu();
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
					case null:
						break;
				default:
					System.out.println("Errorea: hasieran "+Laberintoa.getNireLaberintoa().zerDago(i,j).toString()+" gelaxka aurkitu da." );
					break;
				}
				if(irudia!=null) {
					gelaxka.setIcon(new ImageIcon(Gelaxka.class.getResource(irudia)));	
				}
				if (i == 0 && j == 0) {
					jokalariaLabel = new JLabel();
                    jokalariaLabel.setIcon(new ImageIcon(Gelaxka.class.getResource("/Bista/irudiak/whitedown1.png")));
                    panel.add(jokalariaLabel);
				} else {
					panel.add(gelaxka);
				}
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
			JLabel fondoLabel = Fondoa();
			fondoLabel.setBounds(0, 0, getWidth(), getHeight());
			layeredPane.add(fondoLabel, new Integer(0));

			JPanel panel = getPanel();
			panel.setBounds(0, 0, getWidth(), getHeight());
			layeredPane.add(panel, new Integer(1));
			
		}
		return layeredPane;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Jokalaria && arg instanceof String) {
			Laberintoa lab = Laberintoa.getNireLaberintoa();
			Jokalaria jok = lab.getJokalaria();
			jokalariaLabel.setLocation(jok.getXposizioa(), jok.getYposizioa());
		}
		
	}
}