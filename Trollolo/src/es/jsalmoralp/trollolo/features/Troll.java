package es.jsalmoralp.trollolo.features;

import java.util.ArrayList;

public class Troll {
	/*
	 * Constructor
	 */
	private String trolledPlayer;
	private String trollPlayer;
	public Troll(String trolledPlayer, String trollPlayer) {
		this.trolledPlayer = trolledPlayer;
		this.trollPlayer = trollPlayer;
	}
	
	/*
	 * Metodo del registro de los Trolls
	 */
	public ArrayList<Troll> trolls;
	public void registerTrolls() {
		trolls = new ArrayList<Troll>();
	}
	
	/*
	 * Metodos de interaccion con los Trolls
	 */
	public void addTroll(Troll troll) {
		trolls.add(troll);
	}
	public void removeTroll(String trollPlayer) {
		for (int i=0;i<trolls.size();i++) {
			if (trolls.get(i).getTrollPlayer().equals(trollPlayer)) {
				trolls.remove(i);
			}
		}
	}
	public boolean isPlayerTrolling(String trollPlayer) {
		for (int i=0;i<trolls.size();i++) {
			if (trolls.get(i).getTrollPlayer().equals(trollPlayer)) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Metodos de retorno de informacion
	 */
	public String getTrollPlayer() {
		return this.trollPlayer;
	}
	public String getTrolledPlayer() {
		return this.trolledPlayer;
	}
	public Troll getTroll(String trollPlayer) {
		for (int i=0;i<trolls.size();i++) {
			if (trolls.get(i).getTrollPlayer().equals(trollPlayer)) {
				return trolls.get(i);
			}
		}
		return null;
	}
}
