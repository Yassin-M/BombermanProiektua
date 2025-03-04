package org.SI.Bomberman;

import java.awt.EventQueue;

import Bista.IkusiBeharrekoa;

public class Main {

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

}
