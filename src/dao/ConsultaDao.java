package dao;

import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Consulta;
import datos.Paciente;

public class ConsultaDao {
	
	private static Session session;
	private Transaction tx;
	
	private void iniciaOperacion() throws HibernateException {
		
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he) throws HibernateException {
		
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso a datos", he);
	}

	//modificar
	public void modificarConsulta(Consulta objeto) throws HibernateException {
		
		try {
			iniciaOperacion();
			session.update(objeto);
			tx.commit();
		
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		
		} finally {
			session.close();
		}
	}

	//traer consulta por id
	public Consulta traerConsulta(int idConsulta) throws HibernateException {
		Consulta objeto = null;

		try {
			iniciaOperacion();
			objeto = (Consulta) session.get(Consulta.class, idConsulta);

		} finally {
			session.close();
		}
	
	return objeto;
	}
	
	//traer consulra por dni paciente
	public Consulta traerConsulta(Paciente p) throws HibernateException {
		
		Consulta objeto = null;
		
		try {
			iniciaOperacion();
			objeto = (Consulta) session.createQuery("from Paciente p where p.dniPaciente ="+ Integer.toString(p.getDniPaciente())).uniqueResult();

		} finally {
			session.close();
		}
	
	return objeto;
	}
	
	//traer lista de consulta por fecha
	@SuppressWarnings("unchecked")
	public List<Consulta> traerConsulta(GregorianCalendar fechaDesde, GregorianCalendar fechaHasta) throws HibernateException {
		
		List<Consulta> lista=null;

		try {
			iniciaOperacion();
			lista=session.createQuery("from Consulta c order by c.fechaDeAtencion asc").list();
		
		} finally {
			session.close();
		}

	return lista;
	}
	
}

