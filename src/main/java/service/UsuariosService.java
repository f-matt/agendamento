package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.EspacoFisico;
import model.Usuarios;

@Stateless
public class UsuariosService {
	
	@PersistenceContext(unitName="agendamentoPU")
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Usuarios> getAll() {
		Query query = entityManager.createQuery("SELECT ef FROM Usuarios ef ORDER BY ef.id");
		return (List<Usuarios>) query.getResultList(); 
	}
	
	public void save(Usuarios usuarios) {
		if (usuarios.getId() == null)
			entityManager.persist(usuarios);
		else
			entityManager.merge(usuarios);
	}
	
	public void remove(Usuarios usuarios) {
		entityManager.remove(entityManager.merge(usuarios));
	}
	
}
