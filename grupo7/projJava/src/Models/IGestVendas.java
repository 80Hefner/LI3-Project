package Models;

import Models.CatClientes.*;
import Models.CatProdutos.*;
import Models.Faturacao.*;
import Models.Filial.*;
import NewExceptions.*;
import Structs.*;

import java.io.Serializable;
import java.util.*;

public interface IGestVendas extends Serializable
{
    public ICatProdutos getCatProdutos();
    public ICatClientes getCatClientes();
    public IFaturacao getFaturacao();
    public List<IFilial> getFiliais();
    public String getProdutos_src();
    public void setProdutos_src(String produtos_src);
    public String getClientes_src();
    public void setClientes_src(String clientes_src);
    public String getVendas_src();
    public void setVendas_src(String vendas_src);
    public int getNr_filiais();
    public void setNr_filiais(int nr_filiais);
    public int getNr_produtos();
    public void setNr_produtos(int nr_produtos);
    public int getNr_clientes();
    public void setNr_clientes(int nr_clientes);
    public int getNr_vendas();
    public void setNr_vendas(int nr_vendas);
    public int getNr_vendas_totais();
    public void setNr_vendas_totais(int nr_vendas_totais);
    public int getNr_vendas_valor0();
    public void setNr_vendas_valor0(int nr_vendas_valor0);

    public IGestVendas clone();
    public void insereProduto(IProduto produto);
    public void insereCliente(ICliente cliente);
    public void insereFaturacao(Venda venda);
    public void insereFaturacao(Venda venda, int listInt);
    public void insereNaFilial (Venda venda);
    public void insereNaFilial (Venda venda, int mapInt);

    public void initFaturacao(int nr_filiais);
    public void initFaturacao(int nr_filiais, int mapInt, int listInt);
    public void initFilial();
    public void initFilial(int listInt);

    public boolean searchProduto(IProduto produto);
    public boolean searchCliente(ICliente cliente);

    public int totalDeVendasErradas();
    public Set<String> getClientesCompraram();
    public Map.Entry< String, List<Integer>> querieEstatistica1();
    public List<Integer> getTotalComprasPorMes();
    public List<List<Double>> getTotalFaturadoPorMes();
    public List<List<Integer>> getTotalCompradoresPorMes();
    public List<String> getProdutosNuncaComprados();
    public List<List<Integer>> numeroVendasEClientesMes(int mes);
    public List<List<Double>> getComprasProdsGastoMes(String clienteID) throws ClienteInexistenteException;
    public List<List<Double>> getComprasClientesGastoMes(String produtoID) throws ProdutoInexistenteException;
    public List<ParStringInteger> getProdsClienteMaisComprados(String clienteID) throws ClienteInexistenteException;
    public List<Map.Entry<ParStringInteger, Integer>> getProdsMaisCompradosTotal(int limite);
    public List< List<ParStringDouble> > tresMaioresCompradoresPorFilial();
    public List<ParStringInteger> getClientesCompraramMaisProdutos(int limite);
    public List<ParStringDouble> getClientesMaisCompraramProduto(String produtoID, int limite) throws ProdutoInexistenteException;
    public List<List<List<ParStringDouble>>> faturacaoTotalTodosProdutos();
}
