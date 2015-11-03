package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Evento;

import org.primefaces.model.ScheduleEvent;

@Stateless
public class EventoService {

	@PersistenceContext(unitName = "agendamentoPU")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<ScheduleEvent> getAll() {
		Query query = entityManager.createQuery("SELECT e FROM Evento e");
		return (List<ScheduleEvent>) query.getResultList();
	}

	public void save(ScheduleEvent evento) {
		if (evento.getId() == null)
			entityManager.persist((Evento) evento);
		else
			entityManager.merge((Evento) evento);
	}

	public void remove(ScheduleEvent evento) {
		entityManager.remove(entityManager.merge((Evento) evento));
	}
	
	public String getTimeZone() {
		return "Etc/GMT+2";
	}
	

}
