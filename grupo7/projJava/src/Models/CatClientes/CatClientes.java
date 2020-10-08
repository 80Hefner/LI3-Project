package Models.CatClientes;

import Tests.GestVendasTest_Set;

import java.util.*;

/**
 * Classe Catalogo dos Clientes que implementa a interface ICatClientes
 */
public class CatClientes implements ICatClientes
{
    /**
     * Set de interfaces ICliente
     */
    private Set<ICliente> catalogo;

    /**
     * Construtor por omissão do Catálogo de Clientes
     */
    public CatClientes()
    {
        this.catalogo = new HashSet<>();
    }

    /**
     * Construtor parametrizado do Catálogo de Clientes
     * @param catalogo           Set de interfaces ICliente
     */
    public CatClientes(Set<ICliente> catalogo)
    {
        this.catalogo = new HashSet<>(catalogo);
    }

    /**
     * Construtor de cópia do Catálogo de Clientes
     * @param catalogo           Catálogo de clientes a copiar
     */
    public CatClientes(ICatClientes catalogo)
    {
        this.catalogo = catalogo.getCatClientes();
    }

    /**
     * Construtor que apenas define a estrutura do Catálogo de Clientes
     */
    public CatClientes(int setInt)
    {
        switch (setInt) {
            case GestVendasTest_Set.HASH_SET:
                this.catalogo = new HashSet<>();
                break;
            case GestVendasTest_Set.LINKED_HASH_SET:
                this.catalogo = new LinkedHashSet<>();
                break;
            case GestVendasTest_Set.TREE_SET:
                this.catalogo = new TreeSet<>();
                break;
        }
    }

    /**
     * Getter do set do catálogo de Clientes
     * @return           Set de interfaces ICliente
     */
    public Set<ICliente> getCatClientes()
    {
        return new HashSet<>(this.catalogo);
    }

    /**
     * Setter do set do catálogo de Clientes
     * @param catalogo           Set de interfaces ICliente
     */
    public void setCatClientes(Set<ICliente> catalogo)
    {
        this.catalogo = new HashSet<>(catalogo);
    }

    /**
     * Função de equals do catálogo de Clientes
     * @param o           Objeto ao qual queremos comparar o catálogo
     */
    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        CatClientes c = (CatClientes) o;

        return this.catalogo.equals(c.getCatClientes());
    }

    /**
     * Função que transforma o catálogo numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        this.catalogo.forEach(e -> sb.append(e).append(":"));

        return sb.toString();
    }

    /**
     * Função que dá clone ao catálogo
     * @return           Cópia do Catálogo de Clientes
     */
    public CatClientes clone()
    {
        return new CatClientes(this);
    }

    /**
     * Função que insere um ICliente no catálogo
     * @param cliente           Interface ICliente a adicionar ao Catálogo
     */
    public void insere(ICliente cliente)
    {
        this.catalogo.add(cliente.clone());
    }

    /**
     * Função que verifica se um ICliente se encontra no catálogo
     * @param cliente           Interface ICliente a verificar se existe no Catálogo
     * @return                  Booleano que mostra se cliente pertence ao catálogo ou não
     */
    public boolean procura(ICliente cliente)
    {
        return this.catalogo.contains(cliente);
    }
}
