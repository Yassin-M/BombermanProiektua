package Bista;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
	private JPanel contentPane;
	private JPanel panel_3;
	private JButton btnClassic;
	private JButton btnSoft;
	private JButton btnEmpty;
	private JPanel panelJokalariak;
	private JRadioButton rdbtnBeltza;
	private JRadioButton rdbtnZuria;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblBerriroJolsatuNahi;
	private JPanel panel_4;
	private JButton btnBtnbai;
	private JButton btnEz;
	private JPanel contentPane_2;

	public IkusiBeharrekoa() {
		setBounds(100, 100, 450, 300);
        setTitle("Bomberman");
		setIconImage(new ImageIcon(getClass().getResource("/Bista/irudiak/whitewithbomb1.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setContentPane(getPantailak());
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
	
	private class Controler extends ComponentAdapter implements KeyListener, ActionListener {
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
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String j;
			if(rdbtnZuria.isSelected()) {
				j = "zuria";
			}else {
				j = "beltza";
			}
			if(arg0.getSource().equals(btnClassic)) {
				Laberintoa.getNireLaberintoa().laberintoaSortuClassic(j);
				cardLayout.show(pantailak, "Laberintoa");
			}else if(arg0.getSource().equals(btnEmpty)) {
				Laberintoa.getNireLaberintoa().laberintoaSortuEmpty(j);
				cardLayout.show(pantailak, "Laberintoa");
			}else if(arg0.getSource().equals(btnSoft)){
				Laberintoa.getNireLaberintoa().laberintoaSortuSoft(j);
				cardLayout.show(pantailak, "Laberintoa");
			}
			
			if(arg0.getSource().equals(btnBtnbai)) {
				Laberintoa.resetInstance();
				Laberintoa.getNireLaberintoa().addObserver(IkusiBeharrekoa.this);
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				cardLayout.show(pantailak, "Hasiera");
			}
			if(arg0.getSource().equals(btnEz)) {
				System.exit(0);
			}
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
			cardLayout.show(pantailak, "Amaitu");
		}
	}
	
	private JPanel getPantailak() {
		if (pantailak == null) {
			cardLayout = new CardLayout();
			pantailak = new JPanel(cardLayout);
			pantailak.add(getLayeredPane_1(), "Laberintoa");
			pantailak.add(HasierakoPantaila(), "Hasiera");
			pantailak.add(Amaitu(), "Amaitu");
			cardLayout.show(pantailak, "Hasiera");
		}
		return pantailak;
	}
	
	public JPanel HasierakoPantaila() {
        setTitle("Hasiera");
		setIconImage(new ImageIcon(getClass().getResource("/Bista/irudiak/mainBomb.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel_3(), BorderLayout.CENTER);
		contentPane.add(getPanelJokalariak(), BorderLayout.SOUTH);
		return contentPane;
	}

	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.add(getBtnClassic());
			panel_3.add(getBtnSoft());
			panel_3.add(getBtnEmpty());
		}
		return panel_3;
	}
	private JButton getBtnClassic() {
		if (btnClassic == null) {
			btnClassic = new JButton("Classic");
			btnClassic.addActionListener(getControler());
		}
		return btnClassic;
	}
	private JButton getBtnSoft() {
		if (btnSoft == null) {
			btnSoft = new JButton("Soft");
			btnSoft.addActionListener(getControler());
		}
		return btnSoft;
	}
	private JButton getBtnEmpty() {
		if (btnEmpty == null) {
			btnEmpty = new JButton("Empty");
			btnEmpty.addActionListener(getControler());
		}
		return btnEmpty;
	}

	private JPanel getPanelJokalariak() {
		if (panelJokalariak == null) {
			panelJokalariak = new JPanel();
			panelJokalariak.add(getRdbtnZuria());
			panelJokalariak.add(getRdbtnBeltza());
			rdbtnZuria.setSelected(true);
		}
		return panelJokalariak;
	}
	private JRadioButton getRdbtnBeltza() {
		if (rdbtnBeltza == null) {
			rdbtnBeltza = new JRadioButton("Beltza");
			buttonGroup.add(rdbtnBeltza);
		}
		return rdbtnBeltza;
	}
	private JRadioButton getRdbtnZuria() {
		if (rdbtnZuria == null) {
			rdbtnZuria = new JRadioButton("Zuria");
			buttonGroup.add(rdbtnZuria);
		}
		return rdbtnZuria;
	}
	
	public JPanel Amaitu() {
		setTitle("Game Over");
		setIconImage(new ImageIcon(getClass().getResource("/Bista/irudiak/onFire4.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
	
}

