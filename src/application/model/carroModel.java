package application.model;

public class carroModel {
	String marca;
    String nome;
    String motor;
    String cor;
    int ano;
    String modelo;
    int carro = 1;
    boolean ligado = false;

	public carroModel (String marca, int carro, int ano, String cor, String motor, String modelo, String nome, boolean ligado) {
		this.marca = marca;
		this.carro = carro;
		this.ano = ano;
		this.cor = cor;
		this.motor = motor;
		this.modelo = modelo;
		this.nome = nome;
		this.ligado = ligado;
	}
}
