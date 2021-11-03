package modelo;

import java.io.Serializable;

public class Pokemon implements Serializable{

	private static final long serialVersionUID = 1L;
	String nombre;
	int nivel;
	int vida;
	int ataque;
	int defensa;
	int ataqueEspecial;
	int defensaEspecial;
	int velocidad;

	public Pokemon() {
	}

	public Pokemon(String nombre, int nivel, int vida, int ataque, int defensa, int ataqueEspecial, int defensaEspecial,
				   int velocidad) {
		super();
		this.nombre = nombre;
		this.nivel = nivel;
		this.vida = vida;
		this.ataque = ataque;
		this.defensa = defensa;
		this.ataqueEspecial = ataqueEspecial;
		this.defensaEspecial = defensaEspecial;
		this.velocidad = velocidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public int getAtaqueEspecial() {
		return ataqueEspecial;
	}

	public void setAtaqueEspecial(int ataqueEspecial) {
		this.ataqueEspecial = ataqueEspecial;
	}

	public int getDefensaEspecial() {
		return defensaEspecial;
	}

	public void setDefensaEspecial(int defensaEspecial) {
		this.defensaEspecial = defensaEspecial;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	@Override
	public String toString() {
		return "Pokemon: \n nombre=" + nombre + ", nivel=" + nivel + ", vida=" + vida + ", ataque=" + ataque
				+ ", defensa=" + defensa + "]";
	}

}
