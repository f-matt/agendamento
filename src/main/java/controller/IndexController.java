package controller;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class IndexController {
	
	private String nome = "";
	
	private String mensagem = "";
	
	public void dizOla() {
		this.mensagem = "Olá " + this.nome + "!";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
