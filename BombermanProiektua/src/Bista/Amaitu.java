package Bista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Eredua.Laberintoa;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Amaitu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblBerriroJolsatuNahi;
	private JPanel panel;
	private JButton btnBtnbai;
	private JButton btnEz;
	private Controler controler = null;

	public Amaitu() {
		setTitle("Game Over");
		setIconImage(new ImageIcon(getClass().getResource("/Bista/irudiak/onFire4.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getLblBerriroJolsatuNahi(), BorderLayout.NORTH);
		contentPane.add(getPanel(), BorderLayout.CENTER);
		
	}

	private JLabel getLblBerriroJolsatuNahi() {
		if (lblBerriroJolsatuNahi == null) {
			lblBerriroJolsatuNahi = new JLabel("Berriro jolsatu nahi duzu?");
			lblBerriroJolsatuNahi.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblBerriroJolsatuNahi;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			panel.add(getBtnBtnbai());
			panel.add(getBtnEz());
		}
		return panel;
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
	
	private Controler getControler(){
		if(controler==null) {
			controler = new Controler();
		}
		return controler;
	}
	
	private class Controler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btnBtnbai)) {
				dispose();
				Laberintoa.resetInstance();
	            IkusiBeharrekoa frame = new IkusiBeharrekoa();
	            frame.setVisible(true);
	            Laberintoa.getNireLaberintoa().laberintoaSortuClassic();
	            dispose();
			}
			if(e.getSource().equals(btnEz)) {
				System.exit(0);
			}
		}
	}
}
