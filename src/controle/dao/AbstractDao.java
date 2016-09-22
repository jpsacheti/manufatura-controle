package controle.dao;

import static controle.dao.MorphiaHelper.getDatastore;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;

public abstract class AbstractDao<E> {
	public Class<E> classe;

	public AbstractDao(Class<E> classe) {
		this.classe = classe;
	}

	public void cadastrar(E objeto) {
		getDatastore().save(objeto);
	}

	public void excluir(E objeto) {
		getDatastore().delete(objeto);
	}
	
	public void atualizar(E objeto){
		getDatastore().save(objeto);
	}
	
	public E encontrarPorId(ObjectId id){
		return getDatastore().get(classe, id);
	}

	public List<E> pesquisar(String propriedade, String valor) {
		Query<E> query = getDatastore().createQuery(classe);
		return query.field(propriedade).containsIgnoreCase(valor).asList();
	}
}
