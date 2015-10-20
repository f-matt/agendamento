package controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.EspacoFisico;
import service.EspacoFisicoService;

@ManagedBean
@ViewScoped
public class EspacoFisicoController {
	
	@EJB
	private EspacoFisicoService espacoFisicoService;
	
	private List<EspacoFisico> listaEspacoFisico;
	
	private EspacoFisico espacoFisico;
	
	private EspacoFisico espacoFisicoSelecionado;
	
	private String mensagem = "espaco fisico";
	
	private String buttonValue = "Cadastrar";
	
	@PostConstruct
    public void init() {
		this.espacoFisico = new EspacoFisico();
		this.listaEspacoFisico = espacoFisicoService.getAll();
	}
	
	public void salvar() {
		espacoFisicoService.save(espacoFisico);
		listaEspacoFisico = espacoFisicoService.getAll();
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro cadastrado/atualizado com sucesso!", null));
	}
	
	public void preparaAtualizar() {
		buttonValue = "Atualizar";
		this.espacoFisico = this.espacoFisicoSelecionado;
	}
	
	public void remove() {
		System.out.println("remover");
		espacoFisicoService.remove(espacoFisicoSelecionado);
		listaEspacoFisico = espacoFisicoService.getAll();
	}
	
	public void limpar() {
		this.buttonValue = "Cadastrar";
		this.espacoFisico = new EspacoFisico();
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

	public EspacoFisico getEspacoFisicoSelecionado() {
		return espacoFisicoSelecionado;
	}

	public void setEspacoFisicoSelecionado(EspacoFisico espacoFisicoSelecionado) {
		this.espacoFisicoSelecionado = espacoFisicoSelecionado;
	}

	public String getButtonValue() {
		return buttonValue;
	}

	public void setButtonValue(String buttonValue) {
		this.buttonValue = buttonValue;
	}
	
}