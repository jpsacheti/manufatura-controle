package controle.dao;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import controle.modelos.ItemEntrada;
import controle.uteis.BigDecimalMorphiaConverter;
import controle.uteis.LocalDateConverter;
import controle.uteis.LocalDateTimeConverter;

public class MorphiaHelper {
	private static final Morphia morphia = new Morphia();
	private static final Datastore datastore;
	static {
		morphia.mapPackageFromClass(ItemEntrada.class);
		morphia.getMapper().getOptions().setStoreEmpties(true);
		morphia.getMapper().getOptions().setStoreNulls(true);
		morphia.getMapper().getConverters().addConverter(BigDecimalMorphiaConverter.class);
		morphia.getMapper().getConverters().addConverter(LocalDateTimeConverter.class);
		morphia.getMapper().getConverters().addConverter(LocalDateConverter.class);
		datastore = morphia.createDatastore(new MongoClient(), "controlemanufatura");
		datastore.ensureIndexes();
	}

	
	private MorphiaHelper() { //impede criação de instancias dessa classe
	}
	
	public static Datastore getDatastore() {
		return datastore;
	}
}
