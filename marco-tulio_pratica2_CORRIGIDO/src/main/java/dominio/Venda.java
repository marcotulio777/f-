package dominio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;

import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private double valorTotal;


    private List<Produto> produtos;
    

    private Cliente cliente;

    public Venda(){
        this(0);
    }

    public Venda(double valorTotal){
        this.valorTotal = valorTotal;
    }

    public void setId(Long Id){
        this.Id = Id;
    }

    public Long getId(){
        return this.Id;
    }

    public void setValorTotal(double valorTotal){
        this.valorTotal = valorTotal;
    }

    public double getValorTotal(){
        return this.valorTotal;
    }

    public void setProdutos(List<Produto> produtos){
        this.produtos = produtos;
    }

    public List<Produto> getProdutos(){
        return this.produtos;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public Cliente getCliente(){
        return this.cliente;
    }

    @Override
    public String toString(){
        return "Venda[id="+ Id + ", valor total="+ valorTotal + "]";
    }
}
