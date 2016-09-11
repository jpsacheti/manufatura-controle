package controle.dao;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import controle.modelos.ItemEntrada;

public class MorphiaHelper {
	private static final Morphia morphia = new Morphia();
	private static final Datastore datastore;
	static {
		morphia.mapPackageFromClass(ItemEntrada.class);
		morphia.getMapper().getOptions().setStoreEmpties(true);
		morphia.getMapper().getOptions().setStoreNulls(true);
		datastore = morphia.createDatastore(new MongoClient(), "controle-manufatura");
		datastore.ensureIndexes();
	}

	
	private MorphiaHelper() { //impede criação de instancias dessa classe
	}
	
	public static Datastore getDatastore() {
		return datastore;
	}
}
