package Bista;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import Eredua.*;

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
import java.awt.Graphics;
import javax.swing.JLayeredPane;
import java.awt.Font;
import java.awt.CardLayout;

@SuppressWarnings({"deprecation","removal"})

public class IkusiBeharrekoa extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLayeredPane layeredPane;
	private nireFondoa fondoLabel;
	private Controler controler=null;
	private JPanel pantailak;
	private CardLayout cardLayout;
	private JLabel lblBerriroJolsatuNahi;
	private JPanel panel_4;
	private JButton btnBtnbai;
	private JButton btnEz;
	private JPanel contentPane_2;
	private JLabel lblZorionak;
	
	private HasierakoPantaila hasierakoPantaila;
	private String unekoPantaila;
	private String j = "white";
	private String unekoMapa = "Classic";
	private boolean bombaJarrita = false;
	

	public IkusiBeharrekoa() {
		setBounds(100, 100, 666, 450);
        setTitle("Bomberman");
		setIconImage(new ImageIcon(getClass().getResource("/Bista/irudiak/whitewithbomb1.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setContentPane(getPantailak());
		BombermanKudeatzailea.getNireKudeatzaile().addObserver(this);
		addKeyListener(getControler());
		addComponentListener(getControler());
		setFocusable(true);
		try {
			Audio.getNireAudio().playSoinua("../BombermanProiektua/BombermanProiektua/src/Audioa/Judas Priest - Painkiller (Official Lyric Video).wav");
			Audio.getNireAudio().setBolumena(1.0f);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
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
		setSize(getWidth(), getHeight());
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
	
	private class Controler extends ComponentAdapter implements KeyListener, ActionListener {
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (unekoPantaila.equals("Hasiera")) {
                switch (key) {
                    case KeyEvent.VK_LEFT:
                        hasierakoPantaila.irudiaAldatu("/Bista/irudiak/inicioWhite.png");
                        j = "white";
                        break;
                    case KeyEvent.VK_RIGHT:
                        hasierakoPantaila.irudiaAldatu("/Bista/irudiak/inicioBlack.png");
                        j = "black";
                        break;
                    case KeyEvent.VK_SPACE:
                    	unekoPantaila = "Mapa";
                    	unekoMapa = "Classic";
                    	hasierakoPantaila.irudiaAldatu("/Bista/irudiak/"+j+"Classic.png");
                    	break;
                }
            } else if(unekoPantaila.equals("Mapa") ) {
                	switch (key) {
                	case KeyEvent.VK_LEFT:
                    	if(unekoMapa.equals("Classic")) {
                        hasierakoPantaila.irudiaAldatu("/Bista/irudiak/"+j+"Classic.png");
                        unekoMapa = "Classic";
                    	}else if(unekoMapa.equals("Soft")) {
                    		hasierakoPantaila.irudiaAldatu("/Bista/irudiak/"+j+"Classic.png");
                    		unekoMapa = "Classic";
                    	}else if(unekoMapa.equals("Empty")) {
                    		hasierakoPantaila.irudiaAldatu("/Bista/irudiak/"+j+"Soft.png");
                    		unekoMapa = "Soft";
                    	}
                        break;
                    case KeyEvent.VK_RIGHT:
                    	if(unekoMapa.equals("Classic")) {
                            hasierakoPantaila.irudiaAldatu("/Bista/irudiak/"+j+"Soft.png");
                            unekoMapa = "Soft";
                        	}else if(unekoMapa.equals("Soft")) {
                        		hasierakoPantaila.irudiaAldatu("/Bista/irudiak/"+j+"Empty.png");
                        		unekoMapa = "Empty";
                        	}else if(unekoMapa.equals("Empty")) {
                        		hasierakoPantaila.irudiaAldatu("/Bista/irudiak/"+j+"Empty.png");
                        		unekoMapa = "Empty";
                        	}
                        break;
					case KeyEvent.VK_SPACE:
						j = "blue";
						switch(unekoMapa) {
						case "Classic":
							BombermanKudeatzailea.getNireKudeatzaile().laberintoaSortu(1, j);
							break;
						case "Soft":
							BombermanKudeatzailea.getNireKudeatzaile().laberintoaSortu(2, j);
							break;
						case "Empty":
							BombermanKudeatzailea.getNireKudeatzaile().laberintoaSortu(3, j);
							break;
						}
						try {
							Audio.getNireAudio().setBolumena(0.75f);
						} catch (LineUnavailableException e1) {
							e1.printStackTrace();
						}
                        cardLayout.show(pantailak, "Laberintoa");
                        unekoPantaila = "Laberintoa";
                        if (lblZorionak != null) layeredPane.remove(lblZorionak);
						break;
                	}
            	
            }
            else {
                switch (key) {
                    case KeyEvent.VK_UP:
                    	bombaJarrita = false;
                        BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().jokalariaMugitu(jok -> jok.mugituGora(), jok -> jok.getIposizioa() > 0, -1, 0);
                        break;
                    case KeyEvent.VK_DOWN:
                    	bombaJarrita = false;
                    	BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().jokalariaMugitu(jok -> jok.mugituBehera(), jok -> jok.getIposizioa() < 10, 1, 0);
                        break;
                    case KeyEvent.VK_LEFT:
                    	bombaJarrita = false;
                    	BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().jokalariaMugitu(jok -> jok.mugituEzkerretara(), jok -> jok.getJposizioa() > 0, 0, -1);
                        break;
                    case KeyEvent.VK_RIGHT:
                    	bombaJarrita = false;
                    	BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().jokalariaMugitu(jok -> jok.mugituEskuinera(), jok -> jok.getJposizioa() < 16, 0, 1);
                        break;
                    case KeyEvent.VK_SPACE:
                    	if (!bombaJarrita) {
                    		bombaJarrita = true;
                        	BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().getJokalaria().bombaJarri();
                    	}
                        break;
                }
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
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource().equals(btnBtnbai)) {
				BombermanKudeatzailea.getNireKudeatzaile().laberintoaErreseteatu();
				BombermanKudeatzailea.getNireKudeatzaile().addObserver(IkusiBeharrekoa.this);
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				try {
					Audio.getNireAudio().playSoinua("../BombermanProiektua/BombermanProiektua/src/Audioa/Judas Priest - Painkiller (Official Lyric Video).wav");
					Audio.getNireAudio().setBolumena(1.0f);
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
				cardLayout.show(pantailak, "Hasiera");
            	j = "white";
            	try {
					Audio.getNireAudio().soinuaGelditu();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
			}
			if(arg0.getSource().equals(btnEz)) {
				System.exit(0);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof BombermanKudeatzailea && arg instanceof Integer) {
			Integer mapa = (Integer) arg;
			switch (mapa) {
				case 1:
					fondoLabel.setImage("/Bista/irudiak/stageBack1.png");
					break;
				case 2:
					fondoLabel.setImage("/Bista/irudiak/stageBack2.png");
					break;
				case 3:
					fondoLabel.setImage("/Bista/irudiak/stageBack3.png");
					break;
				default:
					break;
			}
			for (int i = 0; i < 11; i++) {
				for (int j = 0; j < 17; j++) {
					String irudia = null;
					switch (BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().zerDago(i,j)) {
			            case BLOKEBIGUNA:
			            	irudia = "/Bista/irudiak/soft1.png";
							break;
						case BLOKEGOGORRA:
							irudia = "/Bista/irudiak/hard1.png";
							break;
						case ETSAIA:
							if(BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().getEtsaia(i,j) instanceof EtsaiArrunta)
								irudia = "/Bista/irudiak/pass1.png";
						    else if(BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().getEtsaia(i,j) instanceof EtsaiPosoia) {
						    	irudia = "/Bista/irudiak/doria1.png";
						    }
							break;
						case JOKALARIA:
							if(BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().getJokalaria() instanceof JokZuria) {
								irudia = "/Bista/irudiak/whitedown1.png";
							}else {
								irudia = "/Bista/irudiak/blackdown1.png";
							}
							break;
						case null:
							break;
					default:
						System.out.println("Errorea: hasieran "+BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().zerDago(i,j).toString()+" gelaxka aurkitu da." );
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
		if (o instanceof BombermanKudeatzailea && arg instanceof Boolean) {
			boolean irabazi = (boolean) arg;
			if (irabazi) {
				lblZorionak = new JLabel("ZORIONAK!");
				lblZorionak.setFont(new Font("Dialog", Font.BOLD, 65));
				lblZorionak.setBounds(0, 0, getWidth(), getHeight());
				layeredPane.add(lblZorionak, new Integer(2));
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			cardLayout.show(pantailak, "Amaitu");
			hasierakoPantaila.irudiaAldatu("/Bista/irudiak/inicioWhite.png");
			unekoPantaila = "Hasiera";
		}
	}
	
	private JPanel getPantailak(){
		if (pantailak == null) {
			cardLayout = new CardLayout();
			pantailak = new JPanel(cardLayout);
			pantailak.add(getLayeredPane_1(), "Laberintoa");
			pantailak.add(getHasierakoPantaila(), "Hasiera");
			pantailak.add(Amaitu(), "Amaitu");
			cardLayout.show(pantailak, "Hasiera");
			this.unekoPantaila = "Hasiera";
		}
		return pantailak;
	}
	
	public JPanel Amaitu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, getWidth(), getHeight());
		contentPane_2 = new JPanel();
		contentPane_2.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_2.setLayout(new BorderLayout(0, 0));
		contentPane_2.add(getLblBerriroJolsatuNahi(), BorderLayout.NORTH);
		contentPane_2.add(getPanel_4(), BorderLayout.CENTER);
		return contentPane_2;
	}

	private JLabel getLblBerriroJolsatuNahi() {
		if (lblBerriroJolsatuNahi == null) {
			lblBerriroJolsatuNahi = new JLabel("Berriro jolastu nahi duzu?");
			lblBerriroJolsatuNahi.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblBerriroJolsatuNahi;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setLayout(new GridLayout(0, 1, 0, 0));
			panel_4.add(getBtnBtnbai());
			panel_4.add(getBtnEz());
		}
		return panel_4;
	}
	private JButton getBtnBtnbai() {
		if (btnBtnbai == null) {
			btnBtnbai = new JButton("Bai");
			btnBtnbai.addActionListener(getControler());
		}
		return btnBtnbai;
	}
	private JButton getBtnEz() {
		if (btnEz == null) {
			btnEz = new JButton("Ez");
			btnEz.addActionListener(getControler());
		}
		return btnEz;
	}
	
	public HasierakoPantaila getHasierakoPantaila() {
		if(hasierakoPantaila==null) {
			hasierakoPantaila = new HasierakoPantaila();
		}
		return hasierakoPantaila;
	}
	
	private class HasierakoPantaila extends JPanel { //esto es como lo de nireFondoa para que se reescale la imagne del principio
        private static final long serialVersionUID = 637219010381401442L;
		private Image imagen;

        public HasierakoPantaila() {
            imagen = new ImageIcon(getClass().getResource("/Bista/irudiak/inicioWhite.png")).getImage();
            setTitle("Bomberman");
            setIconImage(new ImageIcon(getClass().getResource("/Bista/irudiak/whitewithbomb1.png")).getImage());
        }

        public void irudiaAldatu(String p) {
            imagen = new ImageIcon(getClass().getResource(p)).getImage();
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
