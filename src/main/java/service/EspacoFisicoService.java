package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.EspacoFisico;

@Stateless
public class EspacoFisicoService {
	
	@PersistenceContext(unitName="agendamentoPU")
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<EspacoFisico> getAll() {
		Query query = entityManager.createQuery("SELECT ef FROM EspacoFisico ef ORDER BY ef.id");
		return (List<EspacoFisico>) query.getResultList(); 
	}
	
	public void save(EspacoFisico espacoFisico) {
		if (espacoFisico.getId() == null)
			entityManager.persist(espacoFisico);
		else
			entityManager.merge(espacoFisico);
	}
	
	public void remove(EspacoFisico espacoFisico) {
		entityManager.remove(entityManager.merge(espacoFisico));
	}
	
}
