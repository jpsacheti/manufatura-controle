package controle.dao;

import java.time.LocalDate;
import java.util.List;

import org.mongodb.morphia.query.Query;

import controle.modelos.Manufatura;

public class ManufaturaDao extends AbstractDao<Manufatura> {
	public ManufaturaDao(){
		super(Manufatura.class);
	}
	
	public List<Manufatura> pesquisarPorData(LocalDate dataBase, LocalDate dataTeto){
		Query<Manufatura> query = MorphiaHelper.getDatastore().createQuery(Manufatura.class);
		query.field("date").greaterThan(dataBase).field("date").lessThan(dataTeto);
		return query.asList();
	}
}
