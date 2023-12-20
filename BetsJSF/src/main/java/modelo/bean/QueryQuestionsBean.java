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
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class QueryQuestionsBean {

	private Date fechaEvento;

	private BLFacade facade;

	private String fecha;

	private int numEvento;

	private String nomEvento;
	
	private List<SelectItem> selectEventos;
	
	private List<Event> listaEventos;
	
	private Event evActual; 
	
	private Float minBet;
	
	private String preguntaNueva;

	private Event eventoActual;

	public int getNumEvento() {
		return numEvento;
	}
	
	public void setNumEvento(int numEvento) {
		this.numEvento = numEvento;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public void onDateSelect(SelectEvent event) {
		 FacesContext.getCurrentInstance().addMessage(null,
		 new FacesMessage("Fecha escogida: "+event.getObject()));
		 this.setFecha(event.getObject().toString());
		 System.out.println(fecha);
	} 
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

	public String getNomEvento() {
		return nomEvento;
	}

	public void setNomEvento(String nomEvento) {
		this.nomEvento = nomEvento;
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
	
	public  QueryQuestionsBean()
	{
		facade = new BLFacadeImplementation();
		setNomEvento(null);
		fechaEvento = new Date();
		fechaEvento.setSeconds(0);
		fechaEvento.setMinutes(0);
		fechaEvento.setHours(0);
        
		this.guardarEventos();
		
	}
	
	public void guardarEventos()
	{
		System.out.println(fechaEvento);
		selectEventos = new ArrayList<>();
		listaEventos = facade.getEvents(fechaEvento);
		
		if(listaEventos.size()>1) {
			evActual = listaEventos.get(0);
			numEvento=evActual.getEventNumber();
			nomEvento=evActual.getDescription();
			System.out.println(evActual);
		}
		 
	}

	public String fechaToString() {
		return fechaEvento.toString();
	}
	public void printEvento()
	{
		
		for(Event eventos: listaEventos)
		{	
			if(eventos.toString().equals(this.nomEvento))
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
