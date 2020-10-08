package Models.CatClientes;

/**
 * Classe Cliente que implementa a interface ICliente
 */
public class Cliente implements ICliente
{
    /**
     * String que representa um Cliente (o seu código)
     */
    private String clientID;

    /**
     * Construtor por omissão do Cliente
     */
    public Cliente()
    {
        this.clientID = "";
    }

    /**
     * Construtor parametrizado do Cliente
     * @param clientID           String com o código de Clietne
     */
    public Cliente(String clientID)
    {
        this.clientID = clientID;
    }

    /**
     * Construtor de cópia do Cliente
     * @param cliente           Cliente a copiar
     */
    public Cliente(ICliente cliente)
    {
        this.clientID = cliente.getClientID();
    }

    /**
     * Função que transforma o Cliente numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(this.clientID);

        return sb.toString();
    }

    /**
     * Função de equals do Cliente
     * @param o           Objeto ao qual queremos comparar o Cliente
     */
    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        Cliente c = (Cliente) o;

        return this.clientID.equals(c.getClientID());
    }

    /**
     * Função que formula um HashCode de cada Clietne
     * @return Inteiro que é o código Hash do Cliente
     */
    public int hashCode()
    {
        int hash = 7;

        hash = 37 * hash + this.clientID.hashCode();

        return hash;
    }

    /**
     * Getter da String do Cliente
     * @return           String com um código de Cliente
     */
    @Override
    public String getClientID()
    {
        return this.clientID;
    }

    /**
     * Função que dá clone ao Clietne
     * @return           Cópia do Clietne
     */
    @Override
    public ICliente clone()
    {
        return new Cliente(this);
    }

    /**
     * Método que compara duas instâncias da classe Cliente
     * @param cliente   Cliente a ser comparado
     * @return          Resultado da comparação de 'this' com cliente
     */
    @Override
    public int compareTo(ICliente cliente)
    {
        return this.clientID.compareTo(cliente.getClientID());
    }
}
