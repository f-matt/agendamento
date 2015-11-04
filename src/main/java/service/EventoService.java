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
		else {
			Evento e = (Evento) evento;
			System.out.println("service: " + e.getStartDate());
			
			Evento e1 = entityManager.find(Evento.class, e.getIdDb());
			System.out.println("service (banco): " + e1.getStartDate());
			
			entityManager.merge(e);
			entityManager.flush();
			System.out.println("service: " + e.getStartDate());
		}
	}

	public void remove(ScheduleEvent evento) {
		entityManager.remove(entityManager.merge((Evento) evento));
	}
	
	public String getTimeZone() {
		return "Etc/GMT+2";
	}
	

}
