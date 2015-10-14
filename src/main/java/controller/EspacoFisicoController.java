package controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import model.EspacoFisico;
import service.EspacoFisicoService;

@ManagedBean
public class EspacoFisicoController {
	
	@EJB
	private EspacoFisicoService espacoFisicoService;
	
	private List<EspacoFisico> listaEspacoFisico;
	
	private EspacoFisico espacoFisico;
	
	private String mensagem = "espaco fisico";
	
	@PostConstruct
    public void init() {
		this.espacoFisico = new EspacoFisico();
		this.listaEspacoFisico = espacoFisicoService.getAll();
	}
	
	public void cadastrar() {
		espacoFisicoService.save(espacoFisico);
		listaEspacoFisico = espacoFisicoService.getAll();
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro cadastrado com sucesso!", null));
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

	public EspacoFisico getEspacoFisico() {
		return espacoFisico;
	}

	public void setEspacoFisico(EspacoFisico espacoFisico) {
		this.espacoFisico = espacoFisico;
	}
	
}
