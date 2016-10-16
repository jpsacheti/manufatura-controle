package controle.modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity("manufatura")
public class Manufatura {
	@Id
	private ObjectId codigo;
	@Reference
	private List<ItemEntrada> listEntrada = new ArrayList<>();
	@Reference
	private List<ItemSaida> listSaida = new ArrayList<>();
	private LocalDate date;

	public ObjectId getCodigo() {
		return codigo;
	}

	public void setCodigo(ObjectId codigo) {
		this.codigo = codigo;
	}

	public List<ItemEntrada> getListEntrada() {
		return listEntrada;
	}

	public void setListEntrada(List<ItemEntrada> listEntrada) {
		this.listEntrada = listEntrada;
	}

	public List<ItemSaida> getListSaida() {
		return listSaida;
	}

	public void setListSaida(List<ItemSaida> listSaida) {
		this.listSaida = listSaida;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public void adicionarEntrada(ItemEntrada item){
		if(listEntrada.contains(item)){
			listEntrada.get(listEntrada.indexOf(item)).getQuantidade().add(item.getQuantidade());
		} else{
			listEntrada.add(item);
		}
	}
	
	public void removerEntrada(ItemEntrada item){
		listEntrada.remove(item);
	}
	
	public void removeSaida(ItemSaida item){
		listSaida.remove(item);
	}
	
	public void removerEntrada(int item){
		listEntrada.remove(item);
	}
	
	public void removeSaida(int item){
		listSaida.remove(item);
	}
	
	public void adicionarSaida(ItemSaida item){
		if(listSaida.contains(item)){
			listSaida.get(listSaida.indexOf(item)).getQuantidade().add(item.getQuantidade());
		} else{
			listSaida.add(item);
		}
	}
}
