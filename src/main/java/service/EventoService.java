package service;

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
		
		Evento e = (Evento) evento;
		
		if (e.getIdDb() == null)
			entityManager.persist(e);
		else {
			entityManager.merge(e);
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

	@SuppressWarnings("unchecked")
	public boolean findDate(Evento evento) {
		
		Query query = entityManager.createQuery(
				"SELECT e from Evento e WHERE (:start BETWEEN e.startDate AND e.endDate) OR "
				+ "(:end BETWEEN e.startDate AND e.endDate) AND e.espacoFisico.id = :id");
		
		query.setParameter("start", evento.getStartDate());
		query.setParameter("end", evento.getEndDate());
		query.setParameter("id", evento.getEspacoFisico().getId());
		
		List<Evento> lista = (List<Evento>) query.getResultList();
		
		if (lista.size() > 0)
			return true;
		else
			return false;
	}

}
