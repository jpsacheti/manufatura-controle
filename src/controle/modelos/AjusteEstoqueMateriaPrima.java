package controle.modelos;

import java.math.BigDecimal;
import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

@Entity("ajusteMateriaPrima")
public class AjusteEstoqueMateriaPrima {
	@Id
	private ObjectId codigo;
	@Reference(lazy=false)
	private MateriaPrima materiaPrima;
	@Property("quantidade")
	private BigDecimal quantidade;
	private Date dataHora;
	private boolean entrada;

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
		this.materiaPrima = materiaPrima;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public boolean isEntrada() {
		return entrada;
	}

	public void setEntrada(boolean entrada) {
		this.entrada = entrada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((dataHora == null) ? 0 : dataHora.hashCode());
		result = prime * result + (entrada ? 1231 : 1237);
		result = prime * result + ((materiaPrima == null) ? 0 : materiaPrima.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
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
		AjusteEstoqueMateriaPrima other = (AjusteEstoqueMateriaPrima) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (dataHora == null) {
			if (other.dataHora != null)
				return false;
		} else if (!dataHora.equals(other.dataHora))
			return false;
		if (entrada != other.entrada)
			return false;
		if (materiaPrima == null) {
			if (other.materiaPrima != null)
				return false;
		} else if (!materiaPrima.equals(other.materiaPrima))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AjusteEstoqueMateriaPrima [codigo=" + codigo + ", materiaPrima=" + materiaPrima + ", quantidade=" + quantidade + ", dataHora=" + dataHora + ", entrada=" + entrada + "]";
	}
	
}
