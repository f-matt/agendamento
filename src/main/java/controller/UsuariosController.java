package controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.EspacoFisico;
import model.Usuarios;
import service.EspacoFisicoService;
import service.UsuariosService;

@ManagedBean
@ViewScoped
public class UsuariosController {
	
	@EJB
	private UsuariosService usuariosService;
	
	private List<Usuarios> listaUsuarios;
	
	private Usuarios usuarios;
	
	private Usuarios usuariosSelecionado;
	
	private String mensagem = "Usuários";
	
	private String buttonValue = "Cadastrar";
	
	@PostConstruct
    public void init() {
		this.usuarios = new Usuarios();
		this.listaUsuarios = usuariosService.getAll();
	}
	
	public void salvar() {
		usuariosService.save(usuarios);
		listaUsuarios = usuariosService.getAll();
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro cadastrado/atualizado com sucesso!", null));
	}
	
	public void preparaAtualizar() {
		buttonValue = "Atualizar";
		this.usuarios = this.usuariosSelecionado;
	}
	
	public void remove() {
		System.out.println("remover");
		usuariosService.remove(usuariosSelecionado);
		listaUsuarios = usuariosService.getAll();
	}
	
	public void limpar() {
		this.buttonValue = "Cadastrar";
		this.usuarios = new Usuarios();
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public UsuariosService getUsuariosService() {
		return usuariosService;
	}

	public void setUsuariosService(UsuariosService usuariosService) {
		this.usuariosService = usuariosService;
	}

	public List<Usuarios> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuarios> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public Usuarios getUsuariosSelecionado() {
		return usuariosSelecionado;
	}

	public void setUsuariosSelecionado(Usuarios usuariosSelecionado) {
		this.usuariosSelecionado = usuariosSelecionado;
	}

	public String getButtonValue() {
		return buttonValue;
	}

	public void setButtonValue(String buttonValue) {
		this.buttonValue = buttonValue;
	}
	
}