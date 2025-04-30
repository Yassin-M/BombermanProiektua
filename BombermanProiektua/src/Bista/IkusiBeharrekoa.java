package Bista;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLayeredPane;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Color;

@SuppressWarnings({"deprecation","removal"})

public class IkusiBeharrekoa extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLayeredPane layeredPane;
	private nireFondoa fondoLabel;
	private Controler controler=null;
	private JPanel pantailak;
	private CardLayout cardLayout;
	private JPanel contentPane_2;
	private JLabel lblZorionak;
	private JButton btnBerriroJolastu;
	private JButton btnItxi;
	private JLabel mensaje;
	private JLabel puntuazioaLabel;
	private JLabel imagenBomberman;
	private JPanel botonesPanel;
	
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
		setContentPane(getPantailak(true,0));
		BombermanKudeatzailea.getNireKudeatzaile().addObserver(this);
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
		public void keyTyped(KeyEvent e) {}

		@Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (unekoPantaila.equals("Hasiera")) {
                switch (key) {
                case KeyEvent.VK_LEFT:
                    switch(j) {
                    case "white":
                    	hasierakoPantaila.irudiaAldatu("/Bista/irudiak/inicioWhite.png");
                        j = "white";
                        break;
                    case "black":
                    	hasierakoPantaila.irudiaAldatu("/Bista/irudiak/inicioWhite.png");
                        j = "white";
                    	break;
                    case "blue":
                    	hasierakoPantaila.irudiaAldatu("/Bista/irudiak/inicioBlack.png");
                        j = "black";
                    	break;
                    case "red":
                    	hasierakoPantaila.irudiaAldatu("/Bista/irudiak/inicioBlue.png");
                        j = "blue";
                    	break;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                	switch(j) {
                    case "white":
                    	hasierakoPantaila.irudiaAldatu("/Bista/irudiak/inicioBlack.png");
                        j = "black";
                        break;
                    case "black":
                    	hasierakoPantaila.irudiaAldatu("/Bista/irudiak/inicioBlue.png");
                        j = "blue";
                    	break;
                    case "blue":
                    	hasierakoPantaila.irudiaAldatu("/Bista/irudiak/inicioRed.png");
                        j = "red";
                    	break;
                    case "red":
                    	hasierakoPantaila.irudiaAldatu("/Bista/irudiak/inicioRed.png");
                        j = "red";
                    	break;
                    }
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
						//j = "blue";
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
                        cardLayout.show(pantailak, "Laberintoa");
                        unekoPantaila = "Laberintoa";
                        if (lblZorionak != null) layeredPane.remove(lblZorionak);
						break;
                	}
            	
            }
            else if (unekoPantaila.equals("Laberintoa")){
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
			if(arg0.getSource().equals(btnBerriroJolastu)) {
				BombermanKudeatzailea.getNireKudeatzaile().laberintoaErreseteatu();
				BombermanKudeatzailea.getNireKudeatzaile().addObserver(IkusiBeharrekoa.this);
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				cardLayout.show(pantailak, "Hasiera");
				unekoPantaila = "Hasiera";
            	j = "white";
			}
			if(arg0.getSource().equals(btnItxi)) {
				System.exit(0);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {}
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
							} else if (BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().getJokalaria() instanceof JokBeltza) {
								irudia = "/Bista/irudiak/blackdown1.png";
							} else if (BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().getJokalaria() instanceof JokUrdina) {
								irudia = "/Bista/irudiak/bluedown1.png";
							} else if (BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().getJokalaria() instanceof JokGorria) {
								irudia = "/Bista/irudiak/reddown1.png";
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
			int puntuazioa = BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().getScore();
			Amaitu(irabazi,puntuazioa);
			//pantailak.add(Amaitu(irabazi),"Amaitu");
			cardLayout.show(pantailak, "Amaitu");
			unekoPantaila = "Amaitu";
			hasierakoPantaila.irudiaAldatu("/Bista/irudiak/inicioWhite.png");
			bombaJarrita = false;
		}
	}
	
	private JPanel getPantailak(boolean pIrabazi, int pPuntuazioa){
		if (pantailak == null) {
			cardLayout = new CardLayout();
			pantailak = new JPanel(cardLayout);
			pantailak.add(getLayeredPane_1(), "Laberintoa");
			pantailak.add(getHasierakoPantaila(), "Hasiera");
			pantailak.add(Amaitu(pIrabazi,pPuntuazioa), "Amaitu");
			cardLayout.show(pantailak, "Hasiera");
			this.unekoPantaila = "Hasiera";
		}
		return pantailak;
	}
	
	public JPanel Amaitu(boolean irabazi, int puntuazioa) {
		if (!irabazi) {
			try {
				Audio.getNireAudio();
				Audio.playSoinua("../BombermanProiektua/BombermanProiektua/src/Audioa/Brawl Stars OST - Lose.wav");
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if (contentPane_2 == null) {
			setBounds(100, 100, getWidth(), getHeight());
			// Panel principal con fondo
		    contentPane_2 = new JPanel() {
		        private static final long serialVersionUID = 1L;
		        private Image fondo = new ImageIcon(getClass().getResource("/Bista/irudiak/back2.png")).getImage();

		        @Override
		        protected void paintComponent(Graphics g) {
		            super.paintComponent(g);
		            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
		        }
		    };
			contentPane_2.setLayout(new BorderLayout(0, 0));
		    mensaje = new JLabel(irabazi ? "Zorionak! Irabazi duzu!" : "Animo! Berriz saia zaitez!");
		    mensaje.setHorizontalAlignment(SwingConstants.CENTER);
		    mensaje.setFont(new Font("Arial", Font.BOLD, 30));
		    mensaje.setForeground(irabazi ? Color.GREEN : Color.RED);
		    contentPane_2.add(mensaje, BorderLayout.NORTH);

		    JPanel centerPanel = new JPanel();
		    centerPanel.setLayout(new GridBagLayout());
		 	centerPanel.setOpaque(false);

		 	GridBagConstraints gbc = new GridBagConstraints();
		 	gbc.gridx = 0;
		 	gbc.gridy = 0;
		 	gbc.anchor = GridBagConstraints.CENTER;
		 	gbc.insets = new java.awt.Insets(10, 0, 10, 0);

		 	puntuazioaLabel = new JLabel("Puntuazioa: " + puntuazioa);
		 	puntuazioaLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		 	puntuazioaLabel.setForeground(Color.BLACK);
		    puntuazioaLabel.repaint();
		 	centerPanel.add(puntuazioaLabel, gbc);
		    puntuazioaLabel.repaint();

		 	
		 	imagenBomberman = new JLabel();
		 	ImageIcon icono = new ImageIcon(getClass().getResource(irabazi ? "/Bista/irudiak/bomber4.png" : "/Bista/irudiak/bomber3.png"));
		 	imagenBomberman.setIcon(new ImageIcon(icono.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
		 	gbc.gridy = 1;
		 	centerPanel.add(imagenBomberman, gbc);

		 	contentPane_2.add(centerPanel, BorderLayout.CENTER);

		    botonesPanel = new JPanel();
		    botonesPanel.setOpaque(false);
		    botonesPanel.setLayout(new GridLayout(1, 2, 10, 10));

		    btnBerriroJolastu = new JButton("Berriro jolastu");
		    btnBerriroJolastu.setFont(new Font("Arial", Font.BOLD, 20));
		    btnBerriroJolastu.setBackground(Color.WHITE);
		    btnBerriroJolastu.addActionListener(getControler());
		    botonesPanel.add(btnBerriroJolastu);

		    btnItxi = new JButton("Itxi");
		    btnItxi.setFont(new Font("Arial", Font.BOLD, 20));
		    btnItxi.setBackground(Color.WHITE);
		    btnItxi.addActionListener(getControler());
		    botonesPanel.add(btnItxi);

		    contentPane_2.add(botonesPanel, BorderLayout.SOUTH);
		} else {
			mensaje.setText(irabazi ? "Zorionak! Irabazi duzu!" : "Animo! Berriz saia zaitez!");
			mensaje.setForeground(irabazi ? Color.GREEN : Color.RED);
			puntuazioaLabel.setText("Puntuazioa: " + puntuazioa);
			ImageIcon icono = new ImageIcon(getClass().getResource(irabazi ? "/Bista/irudiak/bomber4.png" : "/Bista/irudiak/bomber3.png"));
			if (irabazi) imagenBomberman.setIcon(new ImageIcon(icono.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
			else imagenBomberman.setIcon(new ImageIcon(icono.getImage().getScaledInstance(75, 150, Image.SCALE_SMOOTH)));
		}
	    return contentPane_2;
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
