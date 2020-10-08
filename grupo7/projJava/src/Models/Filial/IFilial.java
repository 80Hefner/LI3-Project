package Models.Filial;

import Models.CatClientes.ICatClientes;
import Models.Venda;
import Structs.*;

import java.io.Serializable;
import java.util.*;

public interface IFilial extends Serializable
{
    /**
     * Getter do Map da Filial
     * @return           Map com os dados da Filial
     */
    public Map<String,NodoFilial> getFilial();

    /**
     * Função que inicia a Filial com todos os Clientes e todos os valores a 0
     * @param catClientes   Catalogo com todos os Clientes a introduzir
     */
    public void initFilial (ICatClientes catClientes);

    /**
     * Função que inicia a Filial com todos os Clientes e todos os valores a 0
     * @param catClientes   Catalogo com todos os Clientes a introduzir
     * @param mapInt        Identificador dos maps a ser criados
     */
    public void initFilial (ICatClientes catClientes, int mapInt);

    /**
     * Função que insere uma venda na Filial
     * @param venda         Venda com os dados que queremos introduzir na faturacao
     */
    public void insereFilial(Venda venda);

    /**
     * Função que insere uma venda na Filial
     * @param venda         Venda com os dados que queremos introduzir na faturacao
     * @param mapInt        Identificador dos maps a serem criados
     */
    public void insereFilial(Venda venda, int mapInt);

    public void getClientesCompraram(Set<String> listaClientesCompraram);
    public void getTotalCompradoresPorMes (List<Set<String>> listClientesDistintos);
    public int numeroVendasEClientesMes (Set<String> listClientesDistintos, int mes);
    public void getComprasProdsGastoMes (List< List<Double> > lista, String clientID, List<Set<String>> listaProdsNaoRepetidos);
    public void getComprasClientesGastoMes (List< List<Double> > lista, String productID, List<Set<String>> listaProdsNaoRepetidos);
    public void getProdsClienteMaisComprados (List<ParStringInteger> mapa, String clienteID);
    public void getProdsMaisCompradosTotal (List<ParStringInteger> lista, List<Set<String>> clientesNaoRepetidos);
    public void tresMaioresCompradoresPorFilial (Set<ParStringDouble> mapa);
    public void getClientesCompraramMaisProdutos (Map<String, Set<String>> mapaClientesESeusProdutos);
    public void getClientesMaisCompraramProduto (String produtoID, Map<String, ParStringInteger> mapa);
    public void getFaturadoPorClientes (List<ParStringDouble> lista, String productID);

    /**
     * Função que dá clone à Filial
     * @return         Cópia da Filial
     */
    public IFilial clone();

}
