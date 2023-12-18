package modelo.bean;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import domain.Event;


public class CreateQuestionsBean {
	private String fechaSeleccionada;
	private Event eventoActual;
	
	
	private List<SelectItem> selectEventos;
	
	public List<SelectItem> getSelectEventos() {
		return selectEventos;
	}

	public void setSelectEventos(List<SelectItem> selectEventos) {
		this.selectEventos = selectEventos;
	}

	public CreateQuestionsBean()
	{
		
		setEventoActual(null);
		
		Event evento1 = new Event("Evento 1", new Date());
		Event evento2 = new Event("Evento 2", new Date());
       
        selectEventos = new ArrayList<>();
    
        SelectItem selectItem = new SelectItem(evento1, evento1.getDescription());
        SelectItem selectItem2 = new SelectItem(evento2, evento2.getDescription());
        selectEventos.add(selectItem);
        selectEventos.add(selectItem2);
	}
	
	public void guardarFecha(SelectEvent event)
	{
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fecha escogida: "+event.getObject()));
	    fechaSeleccionada = event.getObject().toString();
		System.out.println(fechaSeleccionada);
	}



	public Event getEventoActual() {
		return eventoActual;
	}

	public void setEventoActual(Event eventoActual) {
		this.eventoActual = eventoActual;
	}
	
	
}