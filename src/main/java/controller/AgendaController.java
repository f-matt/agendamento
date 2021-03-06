package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import model.EspacoFisico;
import model.Evento;

import org.primefaces.context.RequestContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import service.EventoService;

@ManagedBean
@ViewScoped
public class AgendaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private EventoService eventoService;
	
	@ManagedProperty(value = "#{espacoFisicoController.espacoFisicoSelecionado}")
	private EspacoFisico espacoFisico;

	private ScheduleModel eventModel;

	private ScheduleEvent evento = new Evento(espacoFisico);

	private TimeZone timezone;

	@PostConstruct
	public void init() {
		
		if (espacoFisico == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest origRequest = (HttpServletRequest) context.getExternalContext().getRequest();
			String contextPath = origRequest.getContextPath();
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + "/espaco-fisico.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
		
			// Busca o timezone do banco
			timezone = TimeZone.getTimeZone(eventoService.getTimeZone());
		
			// Cria o modelo de eventos
			eventModel = new DefaultScheduleModel();
			List<ScheduleEvent> listaEventos = eventoService.getAll(espacoFisico);

			for (ScheduleEvent se : listaEventos) {
				eventModel.addEvent(se);
			}
		}

	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public ScheduleEvent getEvent() {
		return evento;
	}

	public void setEvent(ScheduleEvent event) {
		this.evento = event;
	}

	public void addEvent(ActionEvent actionEvent) {	
		if(!eventoService.findDate((Evento) evento)){
			if (evento.getId() == null) {
				eventModel.addEvent(evento);
				eventoService.save(evento);
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Evento salvo com sucesso!", null));
			} else {
				eventModel.updateEvent(evento);
				eventoService.save(evento);
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Evento atualizado com sucesso!", null));
			}

			// Atualiza a agenda no frontend
			RequestContext.getCurrentInstance().execute("PF('myschedule').update()");
			evento = new Evento(espacoFisico);
			
		} else{
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Horário indisponível!", null));
		}
	}
	
	public void removeEvent() {
		eventModel.deleteEvent(evento);
		eventoService.remove(evento);
		
		// Atualiza a agenda no frontend
		RequestContext.getCurrentInstance().execute("PF('myschedule').update()");
	}

	public void onEventSelect(SelectEvent selectEvent) {
		evento = (ScheduleEvent) selectEvent.getObject();
	}

	public void onDateSelect(SelectEvent selectEvent) {
		evento = new Evento("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject(), espacoFisico);
	}

	public void onEventMove(ScheduleEntryMoveEvent event) {
		Evento ev = (Evento) event.getScheduleEvent();
		
		if(!eventoService.findDate(ev)) {
			eventoService.save(ev);
			eventModel.updateEvent(ev);
		} else {
			
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Horário indisponível!", null));
			
			// Cria o modelo de eventos
			eventModel.clear();
			List<ScheduleEvent> listaEventos = eventoService.getAll(espacoFisico);

			for (ScheduleEvent se : listaEventos) {
				eventModel.addEvent(se);
			}
		}
	}

	public void onEventResize(ScheduleEntryResizeEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Horário alterado", event.getDayDelta() + " dias, " + event.getMinuteDelta()
				+ "minutos");

		addMessage(message);
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public TimeZone getTimezone() {
		return timezone;
	}

	public void setTimezone(TimeZone timezone) {
		this.timezone = timezone;
	}

	public EspacoFisico getEspacoFisico() {
		return espacoFisico;
	}

	public void setEspacoFisico(EspacoFisico espacoFisico) {
		this.espacoFisico = espacoFisico;
	}
	
}