package Bista;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Eredua.BlokeBiguna;
import Eredua.BlokeGogorra;
import Eredua.Bomba;
import Eredua.Etsaia;
import Eredua.Laberintoa;
import Eredua.Sua;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Graphics;

public class IkusiBeharrekoa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel label;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getLabel());
		getContentPane().add(getPanel(), BorderLayout.SOUTH);
		labirintoaBistaratu();
        getContentPane().add(Fondoa());
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(11, 17, 0, 0));
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
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			label.setIcon(new ImageIcon(IkusiBeharrekoa.class.getResource("/Bista/irudiak/stageBack1.png")));
		}
		return label;
	}
	
	public JLabel Fondoa() {
        setTitle("Fondo con JLabel - paintComponent");
        setSize(800, 600);
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

        fondoLabel.setBounds(0, 0, getWidth(), getHeight());
        return fondoLabel;
   }
}