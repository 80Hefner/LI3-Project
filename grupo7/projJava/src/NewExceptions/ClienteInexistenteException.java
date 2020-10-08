package NewExceptions;

/**
 * Classe com Uma Excepetion de Cliente Não existir
 */
public class ClienteInexistenteException extends Exception
{
    /**
     * Função que emite a Exception de que cliente Não Existe
     */
    public ClienteInexistenteException()
    {
        super();
    }

    /**
     * Função que transforma a Exception feita numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        return "Cliente Inexistente";
    }
}
