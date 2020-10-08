package Models.Faturacao;

import Structs.ParStringInteger;

import java.io.Serializable;
import java.util.*;

/**
 * Classe ProdutosFilial que contem os dados das vendas
 */
public class ProdutosFilial implements Serializable
{
    int totalUnidades[];
    double totalFaturado[];
    int totalVendas[];

    /**
     * Construtor por omissão do ProdutosFilial
     */
    public ProdutosFilial()
    {
        this.totalUnidades = new int[12];
        this.totalFaturado = new double[12];
        this.totalVendas = new int[12];
    }

    /**
     * Construtor parametrizado do ProdutosFilial
     * @param totalUnidades     Array com o total de unidades por mês
     * @param totalFaturado     Array com o total faturado por mês
     * @param totalVendas       Array com o total de vendas por mês
     */
    public ProdutosFilial(int totalUnidades[], double totalFaturado[], int totalVendas[])
    {
        this.totalUnidades = Arrays.copyOf(totalUnidades, totalUnidades.length);
        this.totalFaturado = Arrays.copyOf(totalFaturado, totalFaturado.length);
        this.totalVendas = Arrays.copyOf(totalVendas, totalVendas.length);
    }

    /**
     * Construtor de cópia do ProdutosFilial
     * @param pf       ProdutosFilial a copiar
     */
    public ProdutosFilial(ProdutosFilial pf)
    {
        this.totalUnidades = pf.getArrayTotalUnidades();
        this.totalFaturado = pf.getArrayTotalFaturado();
        this.totalVendas = pf.getArrayTotalVendas();
    }

    /**
     * Getter do array de total de unidades por mês
     * @return  Array de inteiros com total de unidades por mês
     */
    public int[] getArrayTotalUnidades()
    {
        return Arrays.copyOf(this.totalUnidades, this.totalUnidades.length);
    }

    /**
     * Setter do array de total de unidades por mês
     * @param totalUnidades     Array de inteiros com total de unidades por mês
     */
    public void setArrayTotalUnidades(int[] totalUnidades)
    {
        this.totalUnidades = Arrays.copyOf(totalUnidades, totalUnidades.length);
    }

    /**
     * Getter do total de unidades da totalidade do ano faturado
     * @return  Inteiro com o total faturado no ano inteiro
     */
    public int getTotalUnidadesAno () {
        int total = 0;
        for (int mes = 0; mes < 12; mes ++) {
            total += this.totalUnidades[mes];
        }
        return total;
    }


    /**
     * Getter do array de total faturado por mês
     * @return  Array de Doubles com total faturado por mês
     */
    public double[] getArrayTotalFaturado()
    {
        return Arrays.copyOf(this.totalFaturado, this.totalFaturado.length);
    }

    /**
     * Setter do array de total faturado por mês
     * @param totalFaturado     Array de Doubles com total faturado por mês
     */
    public void setTotalFaturado(double[] totalFaturado)
    {
        this.totalFaturado = Arrays.copyOf(totalFaturado, totalFaturado.length);
    }

    /**
     * Getter do total de faturado num dado mês
     * @param mes  Mês que pretendemos obter o faturado
     * @return     Double com o total faturado no mês pedido
     */
    public double getTotalFaturadoMes (int mes) { return this.totalFaturado[mes-1]; }


    /**
     * Getter do array de total de vendas por mês
     * @return  Array de inteiros com total de vendas por mês
     */
    public int[] getArrayTotalVendas()
    {
        return Arrays.copyOf(this.totalVendas, this.totalVendas.length);
    }

    /**
     * Setter do array de total de vendas por mês
     * @param totalVendas     Array de inteiros com total de vendas por mês
     */
    public void setArrayTotalVendas(int[] totalVendas)
    {
        this.totalVendas = Arrays.copyOf(totalVendas, totalVendas.length);
    }

    /**
     * Getter do total de vendas num dado mês
     * @param mes  Mês que pretendemos obter o numero de Vendas
     * @return     Inteiro com o total de vendas no mês pedido
     */
    public int getTotalVendasMes (int mes) { return this.totalVendas[mes-1]; }


    /**
     * Função de equals do ProdutosFilial
     * @param o           Objeto ao qual queremos comparar o ProdutosFilial
     */
    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        ProdutosFilial pf = (ProdutosFilial) o;

        return Arrays.equals(this.totalUnidades, pf.getArrayTotalUnidades()) &&
                Arrays.equals(this.totalFaturado, pf.getArrayTotalFaturado()) &&
                Arrays.equals(this.totalVendas, pf.getArrayTotalVendas());
    }

    /**
     * Função que transforma o ProdutosFilial numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("UNIDADES:");
        for (int i : this.totalUnidades)
            sb.append(i).append(":");

        sb.append("\nFATURADO:");
        for (double i : this.totalUnidades)
            sb.append(i).append(":");

        sb.append("\nVENDAS:");
        for (int i : this.totalUnidades)
            sb.append(i).append(":");

        return sb.toString();
    }

    /**
     * Função que dá clone ao ProdutosFilial
     * @return           Cópia do ProdutosFilial
     */
    public ProdutosFilial clone()
    {
        return new ProdutosFilial(this);
    }

    /**
     * Função que formula um HashCode de cada ProdutosFilial
     * @return Inteiro que é o código Hash do ProdutosFilial
     */
    public int hashCode()
    {
        int hash = 7;
        long aux;

        for (int x : this.totalUnidades)
            hash = 37 * hash + x;
        for (double x : this.totalFaturado) {
            aux = Double.doubleToLongBits(x);
            hash = 37 * hash + (int)(aux ^ (aux >>> 32));
        }
        for (int x : this.totalVendas)
            hash = 37 * hash + x;

        return hash;
    }

    /**
     * Função que dá o total de unidades no ano inteiro
     * @return  Inteiro com o total de Unidades
     */
    public int getTotalUnidades()
    {
        return Arrays.stream(this.totalUnidades).sum();
    }

    /**
     * Função que dá o total faturado no ano inteiro
     * @return  Double com o total faturado
     */
    public double getTotalFaturado()
    {
        return Arrays.stream(this.totalFaturado).sum();
    }

    /**
     * Função que dá o total de vendas no ano inteiro
     * @return  Inteiro com o total de Vendas
     */
    public int getTotalVendas()
    {
        return Arrays.stream(this.totalVendas).sum();
    }

    /**
     * Função que dá o total de Unidades num intervalo de meses
     * @param inicio    Mes de inicio de estudo
     * @param fim       Mes de fim de estudo
     * @return  Inteiro com o total de Unidade
     */
    public int getTotalUnidades(int inicio, int fim)
    {
        int total = 0;

        for (int i = inicio-1; i < fim; i++) {
            total += this.totalUnidades[i];
        }

        return total;
    }

    /**
     * Função que dá o total faturado num intervalo de meses
     * @param inicio    Mes de inicio de estudo
     * @param fim       Mes de fim de estudo
     * @return  Double com o total faturado
     */
    public double getTotalFaturado(int inicio, int fim)
    {
        double total = 0;

        for (int i = inicio-1; i < fim; i++) {
            total += this.totalFaturado[i];
        }

        return total;
    }

    /**
     * Função que dá o total de Vendas num intervalo de meses
     * @param inicio    Mes de inicio de estudo
     * @param fim       Mes de fim de estudo
     * @return  Inteiro com o total de Vendas
     */
    public int getTotalVendas(int inicio, int fim)
    {
        int total = 0;

        for (int i = inicio-1; i < fim; i++) {
            total += this.totalVendas[i];
        }

        return total;
    }

    /**
     * Funcao que insere uma venda no ProdutosFilial
     * @param preco         Preco unitario da compra
     * @param quantidade    Quantidade da compra
     * @param mes           Mês da Compra
     */
    public void insereVenda(double preco, int quantidade, int mes)
    {
        this.totalUnidades[mes-1] += quantidade;
        this.totalFaturado[mes-1] += preco * quantidade;
        this.totalVendas[mes-1] ++;
    }

    /**
     * Função que verifica se existem alguams compras
     * @return      Booleano que mostra se existem compras neste ProdutosFilial ou não
     */
    public boolean temCompras()
    {
        return !(Arrays.equals(this.totalUnidades, new int[] {0,0,0,0,0,0,0,0,0,0,0,0}) &&
                Arrays.equals(this.totalFaturado, new double[] {0,0,0,0,0,0,0,0,0,0,0,0}) &&
                Arrays.equals(this.totalVendas, new int[] {0,0,0,0,0,0,0,0,0,0,0,0}));
    }

    /**
     * Funçao que retorna uma list com a faturacao por meses
     * @param listaMeses  Lista de doubles com o faturado em cada mes
     */
    public void faturacaoTotalTodosProdutos (List<Double> listaMeses)
    {
        for (int mes = 0; mes < 12; mes++) {
            listaMeses.set(mes, (listaMeses.get(mes) + this.totalFaturado[mes]));
        }
    }

    /***** QUERY 1E *****/
    /**
     * Funçao auxiliar da Query2.1 Estatística
     * @param lista Lista de inteiros que vamos agora preencher
     */
    public void preencheListaComprasMes(List<Integer> lista)
    {
        for (int mes = 0; mes < 12; mes++) {
            int nrVendas = lista.get(mes) + this.getTotalVendasMes(mes+1);
            lista.set(mes, nrVendas);
        }
    }

    /***** QUERY 2E *****/
    /**
     * Função auxiliar da Query2.2 Estatística
     * @param lista     Uma lista de listas de doubles que iremos agora preencher
     */
    public void preencheListaFaturadoMes(List<Double> lista)
    {
        for (int mes = 0; mes < 12; mes++) {
            double faturado = lista.get(mes) + this.getTotalFaturadoMes(mes+1);
            lista.set(mes, faturado);
        }
    }

    /***** QUERY 6 *****/
    /**
     * Função auxiliar da Query6
     * @param mapa  Mapa com keys que saõ os códigos de Produto e os values Pares String Integer que vamos preencher aqui
     */
    public void getProdsMaisCompradosTotal (Map<String, ParStringInteger> mapa, String produto)
    {
        mapa.get(produto).setInteiroSomaComAntigo(this.getTotalUnidadesAno());
    }

}
