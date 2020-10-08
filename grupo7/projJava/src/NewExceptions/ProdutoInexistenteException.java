package NewExceptions;

/**
 * Classe com Uma Excepetion de Produto Não existir
 */
public class ProdutoInexistenteException extends Exception
{
    /**
     * Função que emite a Exception de que Produto Não Existe
     */
    public ProdutoInexistenteException()
    {
        super();
    }

    /**
     * Função que transforma a Exception feita numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        return "Produto Inexistente";
    }
}
