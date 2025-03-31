package Bista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Eredua.Laberintoa;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class HasierakoPantaila extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JButton btnClassic;
	private JButton btnSoft;
	private JButton btnEmpty;
	private Controler controler = null;

	public HasierakoPantaila() {
        setTitle("Hasiera");
		setIconImage(new ImageIcon(getClass().getResource("/Bista/irudiak/mainBomb.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getBtnClassic());
			panel.add(getBtnSoft());
			panel.add(getBtnEmpty());
		}
		return panel;
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
	
	private Controler getControler() {
		if(controler == null) {
			controler = new Controler();
		}
		return controler;
	}
	
	private class Controler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			IkusiBeharrekoa frame = new IkusiBeharrekoa();
			frame.setVisible(true);
			if(arg0.getSource().equals(btnClassic)) {
				Laberintoa.getNireLaberintoa().laberintoaSortuClassic();
			}else if(arg0.getSource().equals(btnEmpty)) {
				Laberintoa.getNireLaberintoa().laberintoaSortuEmpty();
			}else if(arg0.getSource().equals(btnSoft)){
				Laberintoa.getNireLaberintoa().laberintoaSortuSoft();
			}
			dispose();
		}
		
	}
}
