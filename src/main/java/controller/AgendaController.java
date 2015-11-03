package controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import model.Evento;
import service.EventoService;

@ManagedBean
@ViewScoped
public class AgendaController implements Serializable {

	private static final long serialVersionUID = 1L;

	private ScheduleModel eventModel;

	private ScheduleEvent evento = new Evento();

	private TimeZone timezone;

	@EJB
	private EventoService eventoService;

	@PostConstruct
	public void init() {
		// Busca o timezone do banco
		timezone = TimeZone.getTimeZone(eventoService.getTimeZone());
		
		// Cria o modelo de eventos
		eventModel = new DefaultScheduleModel();
		List<ScheduleEvent> listaEventos = eventoService.getAll();

		for (ScheduleEvent se : listaEventos) {
			eventModel.addEvent(se);
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
		if (evento.getId() == null) {
			eventModel.addEvent(evento);
			eventoService.save(evento);
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Evento salvo com sucesso!"));
		} else {
			eventModel.updateEvent(evento);
			eventoService.save(evento);
		}
		evento = new Evento();
	}

	public void onEventSelect(SelectEvent selectEvent) {
		evento = (ScheduleEvent) selectEvent.getObject();
		System.out.println("Evento: " + evento);
	}

	public void onDateSelect(SelectEvent selectEvent) {
		evento = new Evento("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
		System.out.println("Evento: " + evento);
	}

	public void onEventMove(ScheduleEntryMoveEvent event) {
		Date dtInicio = event.getScheduleEvent().getStartDate();
		Date dtFim = event.getScheduleEvent().getEndDate();
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(dtInicio);
		
		cal.add(Calendar.DAY_OF_MONTH, event.getDayDelta());
		cal.add(Calendar.MINUTE, event.getMinuteDelta());
		
		Date novaDtInicio = cal.getTime();
		
		cal.setTime(dtFim);
		
		cal.add(Calendar.DAY_OF_MONTH, event.getDayDelta());
		cal.add(Calendar.MINUTE, event.getMinuteDelta());
		
		Date novaDtFim = cal.getTime();
		
		Evento evento = (Evento) event.getScheduleEvent();
		
		evento.setStartDate(novaDtInicio);
		evento.setEndDate(novaDtFim);		
		
		eventoService.save(evento);
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

}