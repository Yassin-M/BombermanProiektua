package org.SI.Bomberman;

import java.awt.EventQueue;
import Eredua.*;
import Bista.*;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//IkusiBeharrekoa frame = new IkusiBeharrekoa();
					HasierakoPantaila elegir = new HasierakoPantaila();
					elegir.setVisible(true);
					//frame.setVisible(true);
					//Laberintoa.getNireLaberintoa().laberintoaSortuEmpty();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
