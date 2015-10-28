package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.primefaces.model.ScheduleEvent;

@Entity
@Table(name="eventos")
public class Evento implements ScheduleEvent, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long idDb;
	
	@Column(name = "id_schedule")
	private String id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	@Column(name = "end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	
	@Column(name = "all_day")
	private boolean allDay = false;
	
	@Column(name = "description")
	private String description;
	
	@Transient
	private String styleClass;
	
	@Transient
	private Object data;
    
	@Transient
	private boolean editable = true;
    
	public Evento() { 
		this.id = null;
	}
	
	public Evento(String title, Date startDate, Date endDate) {
		this.id = null;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Object getData() {
		return data;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public Date getStartDate() {
		return startDate;
	}

	@Override
	public Date getEndDate() {
		return endDate;
	}

	@Override
	public boolean isAllDay() {
		return allDay;
	}

	@Override
	public String getStyleClass() {
		return styleClass;
	}

	@Override
	public boolean isEditable() {
		return editable;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}
	
	public Long getIdDb() {
		return idDb;
	}

	public void setIdDb(Long idDb) {
		this.idDb = idDb;
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate + ", allDay=" + allDay + ", description="
				+ description + ", editable=" + editable + "]";
	}
	
}
