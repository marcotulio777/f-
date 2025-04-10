package aplicativo;

import java.util.ArrayList;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dominio.Cliente;
import dominio.Produto;
import dominio.Venda;

public class Principal {

    public static void main(String[] args) {

        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("aula-jpa");
            em = emf.createEntityManager();

            em.getTransaction().begin();

            Produto objP1 = new Produto("Teclado", 100.0);
            Produto objP2 = new Produto("Mouse", 50.0);
            Produto objP3 = new Produto("Monitor", 800.0);
            Produto objP4 = new Produto("Notebook", 3500.0);

            Cliente objC1 = new Cliente("João");
            Cliente objC2 = new Cliente("Maria");
            Cliente objC3 = new Cliente("Ana");
            Cliente objC4 = new Cliente("Marco Tulio");

            Venda objV1 = new Venda(objP1.getValor() + objP2.getValor());
            objV1.setCliente(objC1);
            objV1.setProdutos(Arrays.asList(objP1, objP2));

            Venda objV2 = new Venda(objP3.getValor());
            objV2.setCliente(objC2);
            objV2.setProdutos(Arrays.asList(objP3));

            Venda objV3 = new Venda(objP4.getValor());
            objV3.setCliente(objC3);
            objV3.setProdutos(Arrays.asList(objP4));

            Venda objV4 = new Venda(objP2.getValor() + objP3.getValor());
            objV4.setCliente(objC4);
            objV4.setProdutos(Arrays.asList(objP2, objP3));

            em.persist(objP1);
            em.persist(objP2);
            em.persist(objP3);
            em.persist(objP4);

            em.persist(objC1);
            em.persist(objC2);
            em.persist(objC3);
            em.persist(objC4);

            em.persist(objV1);
            em.persist(objV2);
            em.persist(objV3);
            em.persist(objV4);

            em.getTransaction().commit();

            // CONSULTAS
            Query consultaClientes = em.createQuery("SELECT c FROM Cliente c");
            ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) consultaClientes.getResultList();

            Query consultaProdutos = em.createQuery("SELECT p FROM Produto p");
            ArrayList<Produto> listaProdutos = (ArrayList<Produto>) consultaProdutos.getResultList();

            Query consultaVendas = em.createQuery("SELECT v FROM Venda v");
            ArrayList<Venda> listaVendas = (ArrayList<Venda>) consultaVendas.getResultList();

            for (Cliente c : listaClientes) {
                System.out.println("Cliente: " + c.getId() + " - " + c.getNome());
            }

            for (Produto p : listaProdutos) {
                if (p == null) {
                    System.out.println("Produto nulo encontrado.");
                } else {
                    System.out.println("Produto: " + p.getId() + " - " + p.getNome() + " - R$ " + p.getValor());
                }
            }

            for (Venda v : listaVendas) {
                if (v.getCliente() == null) {
                    System.out.println("Venda sem cliente!");
                } else {
                    System.out.println("Venda: " + v.getId() + " - Cliente: " +
                            v.getCliente().getNome() + " - Total: R$ " + v.getValorTotal());
                }
            }

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }
}
