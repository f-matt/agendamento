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

	@SuppressWarnings("unchecked")
	public List<ScheduleEvent> getAll(EspacoFisico espacoFisico) {
		Query query = entityManager.createQuery("SELECT e FROM Evento e WHERE e.espacoFisico.id = :id");
		query.setParameter("id", espacoFisico.getId());
		return (List<ScheduleEvent>) query.getResultList();
	}

	public void save(ScheduleEvent evento) {
		if (evento.getId() == null)
			entityManager.persist((Evento) evento);
		else {
			Evento e = (Evento) evento;
			System.out.println("service: " + e.getStartDate());
			System.out.println("service: " + e.getEndDate());

			entityManager.merge(e);
			
		}
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
	

}
