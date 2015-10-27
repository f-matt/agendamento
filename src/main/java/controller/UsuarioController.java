package controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Usuario;
import service.UsuarioService;

@ManagedBean
@ViewScoped
public class UsuarioController {
	
	@EJB
	private UsuarioService usuarioService;
	
	private List<Usuario> listaUsuario;
	
	private Usuario usuario;
	
	private Usuario usuarioSelecionado;
	
	private String mensagem = "espaco fisico";
	
	private String buttonValue = "Cadastrar";
	
	@PostConstruct
    public void init() {
		this.usuario = new Usuario();
		this.listaUsuario = usuarioService.getAll();
	}
	
	public void salvar() {
		usuarioService.save(usuario);
		listaUsuario = usuarioService.getAll();
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro cadastrado/atualizado com sucesso!", null));
	}
	
	public void preparaAtualizar() {
		buttonValue = "Atualizar";
		this.usuario = this.usuarioSelecionado;
	}
	
	public void remove() {
		System.out.println("remover");
		usuarioService.remove(usuarioSelecionado);
		listaUsuario = usuarioService.getAll();
	}
	
	public void limpar() {
		this.buttonValue = "Cadastrar";
		this.usuario = new Usuario();
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getButtonValue() {
		return buttonValue;
	}

	public void setButtonValue(String buttonValue) {
		this.buttonValue = buttonValue;
	}
	
}