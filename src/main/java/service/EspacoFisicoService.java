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
		Query query = entityManager.createQuery("SELECT ef FROM EspacoFisico ef");
		return (List<EspacoFisico>) query.getResultList(); 
	}
	
	public void save(EspacoFisico espacoFisico) {
		entityManager.persist(espacoFisico);
	}
	
}
