package modelo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

public class QueryQuestionsBean {


	private Date fechaEvento;

	private BLFacade facade;

	private String numEvento;

	private String nombreEvento;
	
	private List<Event> listaEventos;
	
	private Event evActual; 
	
	private List<Question> listaPreguntas;

	public String getNumEvento() {
		return numEvento;
	}
	
	public void setNumEvento(String numEvento) {
		this.numEvento = numEvento;
	}
	/*
	public void onDateSelect(SelectEvent event) {
		 FacesContext.getCurrentInstance().addMessage(null,
		 new FacesMessage("Fecha escogida: "+event.getObject()));
		 this.setFecha(event.getObject().toString());
		 System.out.println(fecha);
	} */
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

	public String getNombreEvento() {
		return nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

	public Date getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(Date fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public List<Question> getListaPreguntas() {
		return listaPreguntas;
	}

	public void setListaPreguntas(List<Question> listaPreguntas) {
		this.listaPreguntas = listaPreguntas;
	}

	public  QueryQuestionsBean()
	{
		facade = new BLFacadeImplementation();
		setNombreEvento(null);
		fechaEvento = new Date();
		fechaEvento.setSeconds(0);
		fechaEvento.setMinutes(0);
		fechaEvento.setHours(0);
        
		this.guardarEventos();
		
	}
	
	public void guardarEventos()
	{
		System.out.println(fechaEvento);
		
		listaEventos = facade.getEvents(fechaEvento);
		numEvento=null;
		nombreEvento=null;
		listaPreguntas=null;
		if(listaEventos.size()>1) {
			evActual = listaEventos.get(0);
			numEvento=evActual.getEventNumber().toString();            
            nombreEvento=evActual.getDescription();
            
            this.setListaPreguntas(evActual.getQuestions());
            System.out.println(listaPreguntas.toString());
            System.out.println(evActual);
		}
		 
	}

	public void printEvento()
	{
		
		for(Event eventos: listaEventos)
		{	
			if(eventos.toString().equals(this.nombreEvento))
			{
				evActual = eventos;
			}
		}
		System.out.println(evActual.toString());
		//System.out.println(eventoActual.getDescription());
		
	}
	
}
