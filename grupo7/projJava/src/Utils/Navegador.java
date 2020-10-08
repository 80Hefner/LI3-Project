package Utils;

import NewExceptions.*;

/**
 * Classe Navegador que permite navegar no sistema de paginação
 */
public class Navegador
{
    private int currentPage;
    public final int itemsPerPage;
    public final int totalPages;

    /**
     * Construtor parametrizado do Navegador
     * @param itemsPerPage  Número de intems por página
     * @param totalPages    Total de páginas
     */
    public Navegador(int itemsPerPage, int totalPages)
    {
        this.currentPage = 0;
        this.itemsPerPage = itemsPerPage;
        this.totalPages = totalPages;
    }

    /**
     * Função que passa para uma página á frente
     * @throws PageOutOfBoundsException     Caso nao consiga avançar porque página é a ultima
     */
    public void nextPage() throws PageOutOfBoundsException
    {
        if (this.currentPage == this.totalPages-1)
            throw new PageOutOfBoundsException(1);
        else
            this.currentPage++;
    }

    /**
     * Função que passa para uma página para trás
     * @throws PageOutOfBoundsException     Caso nao consiga retroceder porque página é a primeira
     */
    public void prevPage() throws PageOutOfBoundsException
    {
        if (this.currentPage == 0)
            throw new PageOutOfBoundsException(-1);
        else
            this.currentPage--;
    }

    /**
     * Função que dá a página do momento
     * @return      Integer com a página atual
     */
    public int getCurrentPage()
    {
        return this.currentPage;
    }

    /**
     * Função que dá set á pagina atual
     * @param currentPage                   Página que queremos introduzir
     * @throws PageOutOfBoundsException     Página que queremos ir fica fora das página que podemos navegar
     */
    public void setCurrentPage(int currentPage) throws PageOutOfBoundsException
    {
        if (currentPage < 0 || currentPage >= totalPages) throw new PageOutOfBoundsException(0);
        else this.currentPage = currentPage;
    }
}
