package io.connection;

import java.util.Date;

public class Persona {
	
	private String email;
	private String pass;
	private String nick;
	private Date fecha;
	private int score;
	
	
	public Persona(String email, String pass) {
		
		this.email = email;
		this.pass = pass;
		fecha = new Date();
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	

}
