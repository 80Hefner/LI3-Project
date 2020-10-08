package NewExceptions;

/**
 * Classe com Uma Excepetion de Pagina fora do alcance
 */
public class PageOutOfBoundsException extends Exception
{
    int id;

    /**
     * Função que emite a Exception da Página, depenendo do tipo do argumento que recebemos
     * @param x     Parametro que mostra se a exception é por ser fora de alcance, ou por ser a ultima pagina ou primeira pagina e não poder ultrapassar esses valores
     */
    public PageOutOfBoundsException(int x)
    {
        this.id = x;
    }

    /**
     * Função que transforma a Exception feita numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        if (this.id < 0)
            return "\nPrimeira página. Impossível retroceder mais.\n";
        else if (this.id > 0)
            return "\nÚltima página. Impossível avançar mais.\n";
        else
            return "\nÍndice inválido.\n";
    }
}
