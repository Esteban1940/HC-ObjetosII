package negocio;

import java.util.GregorianCalendar;
import java.util.List;

import dao.ConsultaDao;
import datos.Consulta;
import datos.Paciente;

public class ConsultaABM {
	
	ConsultaDao dao=new ConsultaDao();
	
	//metodos
	
	//traer consulta por id
	
	public Consulta traerConsulta(int idConsulta)throws Exception{
		
		Consulta c= dao.traerConsulta(idConsulta);
		
		if(c==null) throw new Exception("Consulta no existe.");
		
	return c;
	}
	
	//traer consulta por dni paciente
	
	public Consulta traerConsulta(Paciente p)throws Exception{
		
		Consulta c= dao.traerConsulta(p.getDniPaciente());
		
		if(c==null) throw new Exception("Consulta no existe.");
	
	return c;
	}

	//modificar consulta
	@SuppressWarnings("null")
	public void modificar(Consulta c) throws Exception{
		
		if (c!=null){
			dao.modificarConsulta(c);
		}
			
		else{
			throw new Exception("Consulta id: " +c.getIdConsulta()+ " no existe para modificar.");
		}
	
	}

	//traer listado de consultas por fecha
	
	//Tengo q revisar la implmentacion de este metodo, para q reste las fechas!!
	public List<Consulta> traerConsulta(GregorianCalendar fechaDesde, GregorianCalendar fechaHasta){
		
	return dao.traerConsulta(fechaHasta, fechaHasta);
	}
}

