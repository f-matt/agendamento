package controller;
 
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
 
@ManagedBean
@ViewScoped
public class AgendaController implements Serializable {
 
    private static final long serialVersionUID = 1L;

	private ScheduleModel eventModel;
     
	private ScheduleEvent event = new DefaultScheduleEvent();
	
	private SimpleDateFormat formatador;
 
    @PostConstruct
    public void init() {
    	try {
	    	formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	        eventModel = new DefaultScheduleModel();
	        eventModel.addEvent(new DefaultScheduleEvent("Curso: Dev. Web em Java", formatador.parse("27/10/2015 16:00"), formatador.parse("27/10/2015 17:00")));
	        eventModel.addEvent(new DefaultScheduleEvent("Curso: Dev. Web em Java", formatador.parse("28/10/2015 16:00"), formatador.parse("28/10/2015 17:00")));
	        eventModel.addEvent(new DefaultScheduleEvent("Reunião: Ebserh", formatador.parse("29/10/2015 10:00"), formatador.parse("29/10/2015 12:00")));
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    }
     
    public ScheduleModel getEventModel() {
        return eventModel;
    }
     
    public ScheduleEvent getEvent() {
        return event;
    }
 
    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }
     
    public void addEvent(ActionEvent actionEvent) {
        if(event.getId() == null)
            eventModel.addEvent(event);
        else
            eventModel.updateEvent(event);
         
        event = new DefaultScheduleEvent();
    }
     
    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }
     
    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }
     
    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Data alterada", event.getDayDelta() + " dias, " +  event.getMinuteDelta() + "minutos");
         
        addMessage(message);
    }
     
    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Horário alterado", event.getDayDelta() + " dias, " +  event.getMinuteDelta() + "minutos");
         
        addMessage(message);
    }
     
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}