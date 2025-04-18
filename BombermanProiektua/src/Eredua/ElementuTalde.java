package Eredua;

import java.util.ArrayList;

public class ElementuTalde implements GelaxkaElementua {
	private ArrayList<GelaxkaElementua> elementuak;
	public ElementuTalde(ArrayList<GelaxkaElementua> pElementuak) {
		this.elementuak = pElementuak;
	}
	
	public Integer[] lortuEgoera() {
		Integer[] egoera = {0,0,0,0,0,0,0,0};
		for (GelaxkaElementua elementua : elementuak) {
			Integer[] elementuEgoera = elementua.lortuEgoera();
	        for (int i = 0; i < egoera.length; i++) {
	            egoera[i] += elementuEgoera[i];
	        }
		}
		return egoera;
	}
	
	public Bomba getBomba() {
		for (GelaxkaElementua elementua : elementuak) {
			if (elementua instanceof Bomba) {
				return (Bomba) elementua;
			}
		}
		return null;
	}
	
	public Blast getSua() {
		for (GelaxkaElementua elementua : elementuak) {
			if (elementua instanceof Blast) {
				return (Blast) elementua;
			}
		}
		return null;
	}
	
	public Etsaia getEtsaia() {
		for (GelaxkaElementua elementua : elementuak) {
			if (elementua instanceof Etsaia) {
				return (Etsaia) elementua;
			}
		}
		return null;
	}
	
	public void kenduTimer() {
		for (GelaxkaElementua elementua : elementuak) {
			if (elementua instanceof Blast) {
				((Blast) elementua).kenduTimer();
			}
			else if (elementua instanceof Bomba) {
				((Bomba) elementua).kenduTimer();
			}
			else if (elementua instanceof Etsaia) {
				((Etsaia) elementua).kenduTimer();
			}
		}
	}
}
