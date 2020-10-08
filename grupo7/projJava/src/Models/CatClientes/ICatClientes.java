package Models.CatClientes;

import java.io.Serializable;
import java.util.Set;

/**
 * Interface do Catálogo de Clientes
 */
public interface ICatClientes extends Serializable
{
    /**
     * Função que dá um get a um Set de Clientes
     * @return  Set de Interfaces ICliente
     */
    public Set<ICliente> getCatClientes();

    /**
     * Função que insere um ICliente
     * @param cliente  Interface ICliente que pretendemos inserir
     */
    public void insere(ICliente cliente);

    /**
     * Função que verifica se um ICliente se encontra num catálogo
     * @param cliente           Interface ICliente a verificar se existe no Catálogo
     * @return                  Booleano que mostra se cliente pertence ao catálogo ou não
     */
    public boolean procura(ICliente cliente);

    /**
     * Função que dá clone ao catálogo
     * @return           Cópia do ICatClientes
     */
    public ICatClientes clone();
}
