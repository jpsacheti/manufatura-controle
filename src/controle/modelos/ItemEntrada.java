package controle.modelos;

import java.math.BigDecimal;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

@Entity("itemEntrada")
public class ItemEntrada {
	@Id
	private ObjectId codigo;
	private MateriaPrima materiaPrima;
	@Property("quantidade")
	private BigDecimal quantidade;
	@Reference(lazy=false)
	private Manufatura manufatura;

	public ItemEntrada() {
	}

	public ItemEntrada(MateriaPrima materiaPrima, BigDecimal quantidade) {
		this.quantidade = Objects.requireNonNull(quantidade);
		this.materiaPrima = Objects.requireNonNull(materiaPrima);
	}

	public ObjectId getCodigo() {
		return codigo;
	}

	public void setCodigo(ObjectId codigo) {
		this.codigo = codigo;
	}

	public MateriaPrima getMateriaPrima() {
		return materiaPrima;
	}

	public void setMateriaPrima(MateriaPrima materiaPrima) {
		this.materiaPrima = Objects.requireNonNull(materiaPrima);
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = Objects.requireNonNull(quantidade);
	}

	@Override
	public String toString() {
		return quantidade.toString() + " - " + materiaPrima.getNome();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemEntrada other = (ItemEntrada) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public Manufatura getManufatura() {
		return manufatura;
	}

	public void setManufatura(Manufatura manufatura) {
		this.manufatura = manufatura;
	}
}
