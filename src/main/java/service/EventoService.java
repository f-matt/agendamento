package service;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.EspacoFisico;
import model.Evento;

import org.primefaces.model.ScheduleEvent;

@Stateless
public class EventoService {

	@PersistenceContext(unitName = "agendamentoPU")
	private EntityManager entityManager;
	
	 @PersistenceContext
	 private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ScheduleEvent> getAll(EspacoFisico espacoFisico) {
		Query query = entityManager.createQuery("SELECT e FROM Evento e WHERE e.espacoFisico.id = :id");
		query.setParameter("id", espacoFisico.getId());
		return (List<ScheduleEvent>) query.getResultList();
	}

	public void save(ScheduleEvent evento) {
		
		if (evento.getId() == null)
			entityManager.persist(evento);
		else {
			entityManager.merge(evento);
		}
		
		entityManager.flush();
	}

	public void remove(ScheduleEvent evento) {
		entityManager.remove(entityManager.merge((Evento) evento));
	}
	
	public String getTimeZone() {
		return "Etc/GMT+2";
	}
	
	public ScheduleEvent find(Long id) {
		return entityManager.find(Evento.class, id);
	}

	public boolean findDate(Date startDate, Date endDate) {
		Query query = em.createQuery("SELECT e from Evento e WHERE :start BETWEEN e.startDate AND e.endDate");
		query.setParameter("start", startDate);
		//query.setParameter("end", endDate);
		List<Evento> lista = query.getResultList();
		System.out.println(lista.size());
		if (lista.size() > 0){
			return false;
		}
		else {
			return true;
		}
	}
	

}
