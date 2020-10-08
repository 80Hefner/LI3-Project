package Models.CatClientes;

import java.io.Serializable;

/**
 * Interface do Cliente
 */
public interface ICliente extends Comparable<ICliente>, Serializable
{
    /**
     * Getter da String de um Cliente
     * @return           String com um código de Cliente
     */
    public String getClientID();

    /**
     * Função que dá clone ao IClietne
     * @return           Cópia do IClietne
     */
    public ICliente clone();
}