package modelo.bean;


import java.util.ArrayList; 
import java.util.Date;
import java.util.List;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;


public class CreateQuestionsBean {
	
	private Date fechaEvento;
	
	private Object nomEventoActual;
	
	private List<SelectItem> selectEventos;
	
	private List<Event> listaEventos;
	
	Event evActual; 
	
	Float minBet;
	
	String preguntaNueva;

	private Event eventoActual;
	
	BLFacade facade;
	
	public List<Event> getListaEventos() {
		return listaEventos;
	}

	public void setListaEventos(List<Event> listaEventos) {
		this.listaEventos = listaEventos;
	}
	public Event getEvActual() {
		return evActual;
	}

	public void setEvActual(Event evActual) {
		this.evActual = evActual;
	}

	public Float getMinBet() {
		return minBet;
	}

	public void setMinBet(Float minBet) {
		this.minBet = minBet;
	}

	public String getPreguntaNueva() {
		return preguntaNueva;
	}

	public void setPreguntaNueva(String preguntaNueva) {
		this.preguntaNueva = preguntaNueva;
	}
	
	public Event getEventoActual() {
		return eventoActual;
	}

	public void setEventoActual(Event eventoActual) {
		this.eventoActual = eventoActual;
	}

	public Object getNomEventoActual() {
		return nomEventoActual;
	}

	public void setNomEventoActual(Object nomEventoActual) {
		this.nomEventoActual = nomEventoActual;
	}

	public Date getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(Date fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	
	public List<SelectItem> getSelectEventos() {
		return selectEventos;
	}

	public void setSelectEventos(List<SelectItem> selectEventos) {
		this.selectEventos = selectEventos;
	}

	
	
	
	public  CreateQuestionsBean()
	{
		facade = new BLFacadeImplementation();
		setNomEventoActual(null);
		fechaEvento = new Date();
		fechaEvento.setSeconds(0);
		fechaEvento.setMinutes(0);
		fechaEvento.setHours(0);
        guardarFecha();
        
		this.guardarEventos(fechaEvento);
		/**
		Event evento1 = new Event("Evento 1", new Date());
		Event evento2 = new Event("Evento 2", new Date());
       
        selectEventos = new ArrayList<>();
    
        SelectItem selectItem = new SelectItem(evento1, evento1.getDescription());
        SelectItem selectItem2 = new SelectItem(evento2, evento2.getDescription());
        selectEventos.add(selectItem);
        selectEventos.add(selectItem2);*/
	}
	
	public void guardarEventos(Date fechaEvento)
	{
		
		selectEventos = new ArrayList<>();
		listaEventos = facade.getEvents(fechaEvento);
		
		if(listaEventos.size()>1) {
			evActual = listaEventos.get(0);
			System.out.println(evActual);
		}
		for(Event a: listaEventos)
		{
			SelectItem selectItem = new SelectItem(a, a.getDescription());
			selectEventos.add(selectItem);
		}
		 
	}
	public void guardarFecha()
	{
		/**
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fecha escogida: "+event.getObject()));
	    fechaSeleccionada = event.getObject().toString();
		System.out.println(fechaSeleccionada);*/
		
		System.out.println(fechaEvento);
		this.guardarEventos(fechaEvento);
	}


	public void printEvento()
	{
		
		for(Event eventos: listaEventos)
		{	
			if(eventos.toString().equals(this.nomEventoActual.toString()))
			{
				evActual = eventos;
			}
		}
		System.out.println(evActual.toString());
		//System.out.println(eventoActual.getDescription());
		
	}
	
	public void crearPregunta()
	{
		System.out.println(this.evActual + this.preguntaNueva + this.minBet);
		
		try {
			facade.createQuestion(this.evActual, this.preguntaNueva, this.minBet);
		} catch (EventFinished e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (QuestionAlreadyExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
}