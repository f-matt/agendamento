package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Usuario;

@Stateless
public class UsuarioService {
	
	@PersistenceContext(unitName="agendamentoPU")
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getAll() {
		Query query = entityManager.createQuery("SELECT u FROM Usuario u ORDER BY u.id");
		return (List<Usuario>) query.getResultList(); 
	}
	
	public void save(Usuario usuario) {
		if (usuario.getId() == null)
			entityManager.persist(usuario);
		else
			entityManager.merge(usuario);
	}
	
	public void remove(Usuario usuario) {
		entityManager.remove(entityManager.merge(usuario));
	}
	
}
