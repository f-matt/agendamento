package controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import model.EspacoFisico;
import service.EspacoFisicoService;

@ManagedBean
public class EspacoFisicoController {
	
	@EJB
	private EspacoFisicoService espacoFisicoService;
	
	private List<EspacoFisico> listaEspacoFisico;
	
	private String mensagem = "espaco fisico";
	
	@PostConstruct
    public void init() {
		this.listaEspacoFisico = espacoFisicoService.getAll();
	}
	
	public void dizOla() {
		System.out.println(this.listaEspacoFisico);
		this.mensagem = espacoFisicoService.toString();
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public EspacoFisicoService getEspacoFisicoService() {
		return espacoFisicoService;
	}

	public void setEspacoFisicoService(EspacoFisicoService espacoFisicoService) {
		this.espacoFisicoService = espacoFisicoService;
	}

	public List<EspacoFisico> getListaEspacoFisico() {
		return listaEspacoFisico;
	}

	public void setListaEspacoFisico(List<EspacoFisico> listaEspacoFisico) {
		this.listaEspacoFisico = listaEspacoFisico;
	}
	
}
