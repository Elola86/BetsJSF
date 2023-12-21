package modelo.bean;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Event;
import domain.Question;

public class QueryQuestionsBean {

	private Date fechaEvento;

	private BLFacade facade;

	private String numEvento;

	private String nombreEvento;
	
	private List<Event> listaEventos;
	
	private List<Question> listaPreguntas;

	public String getNumEvento() {
		return numEvento;
	}
	
	public void setNumEvento(String numEvento) {
		this.numEvento = numEvento;
	}
	
	public List<Event> getListaEventos() {
		return listaEventos;
	}

	public void setListaEventos(List<Event> listaEventos) {
		this.listaEventos = listaEventos;
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
		 
	}
	
	public void guardarPreguntas(Event evento) {
		numEvento=null;
		nombreEvento=null;
		listaPreguntas=null;
		if(listaEventos.size()>1) {
			numEvento=evento.getEventNumber().toString();            
            nombreEvento=evento.getDescription();
            
            this.setListaPreguntas(evento.getQuestions());
            System.out.println(listaPreguntas.toString());
            System.out.println(evento);
		}
	}
	
}
