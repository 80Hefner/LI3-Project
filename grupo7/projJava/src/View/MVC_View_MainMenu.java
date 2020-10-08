package View;

import Structs.ParStringDouble;
import Structs.ParStringInteger;
import Utils.Navegador;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.time.Month;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

/**
 * Classe MVC_View_MainMenu Responsável pela view do MainMenu
 */
public class MVC_View_MainMenu implements IMVC_View
{
    private MainMenuFrame window;

    /**
     * Construtor por omissão do MVC_View_MainMenu
     */
    public MVC_View_MainMenu()
    {
        window = new MainMenuFrame("Main Menu");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(500,500);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    /**
     * Getter do MainMenuFrame
     * @return      MainMenuFrame obtido
     */
    public MainMenuFrame getWindow()
    {
        return window;
    }

    /**
     * Setter do MainMenuFrame
     * @return      MainMenuFrame a colocar
     */
    public void setWindow(MainMenuFrame window)
    {
        this.window = window;
    }

    /**
     * Getter do botão da query estatística 1
     * @return      Botão correspondente á query 1
     */
    public JButton getQueryE1Button()
    {
        return window.getQueryE1Button();
    }

    /**
     * Getter do botão da query estatística 2
     * @return      Botão correspondente á query 1
     */
    public JButton getQueryE2Button()
    {
        return window.getQueryE2Button();
    }

    /**
     * Getter do botão da query1
     * @return      Botão correspondente á query 1
     */
    public JButton getQuery1Button()
    {
        return window.getQuery1Button();
    }

    /**
     * Getter do botão da query2
     * @return      Botão correspondente á query 2
     */
    public JButton getQuery2Button()
    {
        return window.getQuery2Button();
    }

    /**
     * Getter do botão da query3
     * @return      Botão correspondente á query 3
     */
    public JButton getQuery3Button()
    {
        return window.getQuery3Button();
    }

    /**
     * Getter do botão da query4
     * @return      Botão correspondente á query 4
     */
    public JButton getQuery4Button()
    {
        return window.getQuery4Button();
    }

    /**
     * Getter do botão da query5
     * @return      Botão correspondente á query 5
     */
    public JButton getQuery5Button()
    {
        return window.getQuery5Button();
    }

    /**
     * Getter do botão da query6
     * @return      Botão correspondente á query 6
     */
    public JButton getQuery6Button()
    {
        return window.getQuery6Button();
    }

    /**
     * Getter do botão da query7
     * @return      Botão correspondente á query 7
     */
    public JButton getQuery7Button()
    {
        return window.getQuery7Button();
    }

    /**
     * Getter do botão da query8
     * @return      Botão correspondente á query 8
     */
    public JButton getQuery8Button()
    {
        return window.getQuery8Button();
    }

    /**
     * Getter do botão da query9
     * @return      Botão correspondente á query 9
     */
    public JButton getQuery9Button()
    {
        return window.getQuery9Button();
    }

    /**
     * Getter do botão da query10
     * @return      Botão correspondente á query 10
     */
    public JButton getQuery10Button()
    {
        return window.getQuery10Button();
    }

    /**
     * Getter do botão do load from disk
     * @return      Botão correspondente ao load from disk
     */
    public JButton getLoadFromDiskButton()
    {
        return window.getLoadFromDiskButton();
    }

    /**
     * Getter do botão do save to disk
     * @return      Botão correspondente ao save to disk
     */
    public JButton getSaveToDiskButton()
    {
        return window.getSaveToDiskButton();
    }

    /**
     * Getter do botão do load
     * @return      Botão correspondente ao load
     */
    public JButton getLoadButton()
    {
        return window.getLoadButton();
    }

    /**
     * Getter do botão de exit
     * @return      Botão correspondente ao exit
     */
    public JButton getExitButton()
    {
        return window.getExitButton();
    }

    /**
     * Função que determinar se os botões das queries estão disponíveis ou não
     * @param bool      Booleano que defini disponibilidade das queries
     */
    public void setQueryButtonsEnable(boolean bool)
    {
        window.getQueryE1Button().setEnabled(bool);
        window.getQueryE2Button().setEnabled(bool);
        window.getQuery1Button().setEnabled(bool);
        window.getQuery2Button().setEnabled(bool);
        window.getQuery3Button().setEnabled(bool);
        window.getQuery4Button().setEnabled(bool);
        window.getQuery5Button().setEnabled(bool);
        window.getQuery6Button().setEnabled(bool);
        window.getQuery7Button().setEnabled(bool);
        window.getQuery8Button().setEnabled(bool);
        window.getQuery9Button().setEnabled(bool);
        window.getQuery10Button().setEnabled(bool);
    }

    /**
     * Função que determinar se os botões dos loads e save estão disponíveis
     * @param status      Booleano que indica se o model se encontra loaded
     */
    public void setLoadedStatus(boolean status)
    {
        window.getLoadButton().setEnabled(!status);
        window.getLoadFromDiskButton().setEnabled(!status);
        window.getSaveToDiskButton().setEnabled(status);
    }

    /**
     * Função que imprime um Object dado
     * @param o     Object a imprimir
     */
    public void print(Object o)
    {
        JOptionPane.showMessageDialog(null, o.toString(), "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Função que imprime um Erro dado
     * @param o     Erro a imprimir
     */
    public void printError(Object o)
    {
        JOptionPane.showMessageDialog(null, o.toString(), "Erro", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Função que imprime a query estatística 1
     * @param resultado     Resultado da querie que pretendemos imprimir
     */
    public void printQueryE1(Map.Entry<String, List<Integer>> resultado)
    {
        List<Integer> lista = resultado.getValue();
        StringBuilder sb = new StringBuilder();

        sb.append("Vendas lidas do ficheiro:\n").append(resultado.getKey()).append("\n");
        sb.append("--------------------------------------\n");
        sb.append("Total de vendas lidas: ").append(lista.get(0)).append("\n");
        sb.append("Total de vendas válidas: ").append(lista.get(1)).append("\n");
        sb.append("Total de vendas inválidas: ").append(lista.get(2)).append("\n");
        sb.append("Total de vendas de valor 0: ").append(lista.get(3)).append("\n");
        sb.append("--------------------------------------\n");
        sb.append("Total de produtos: ").append(lista.get(4)).append("\n");
        sb.append("Total de produtos comprados: ").append(lista.get(5)).append("\n");
        sb.append("Total de produtos não comprados: ").append(lista.get(6)).append("\n");
        sb.append("--------------------------------------\n");
        sb.append("Total de clientes: ").append(lista.get(7)).append("\n");
        sb.append("Total de clientes que compraram: ").append(lista.get(8)).append("\n");
        sb.append("Total de clientes que não compraram: ").append(lista.get(9)).append("\n");

        print(sb.toString());
    }

    /**
     * Função que imprime a query estatística 2
     * @param resultado1     1º Resultado da querie que pretendemos imprimir
     * @param resultado2     2º Resultado da querie que pretendemos imprimir
     * @param resultado3     3º Resultado da querie que pretendemos imprimir
     */
    public void printQueryE2(List<Integer> resultado1, List<List<Double>> resultado2, List<List<Integer>> resultado3)
    {
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < 12; i++)
            sb1.append("    ").append(Month.of(i+1)).append(" -> ").append(resultado1.get(i)).append("\n");
        JScrollPane scrollPane1 = new JScrollPane(new JList<>(sb1.toString().split("\n")), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane1.setPreferredSize(new Dimension(200,300));
        JOptionPane.showMessageDialog(null, scrollPane1, "Total de compras", JOptionPane.INFORMATION_MESSAGE);

        StringBuilder sb2 = new StringBuilder();
        DecimalFormat fmt2 = new DecimalFormat("0.00");
        for (int i = 0; i < resultado2.size(); i++) {
            sb2.append("-----------------------------\n");
            if (i == resultado2.size()-1)
                sb2.append("TOTAL FILIAIS\n");
            else
                sb2.append("FILIAL ").append(i+1).append("\n");
            for (int j = 0; j < 12; j++)
                sb2.append("    ").append(Month.of(j+1)).append(" -> ").append(fmt2.format(resultado2.get(i).get(j))).append("\n");
        }
        JScrollPane scrollPane2 = new JScrollPane(new JList<>(sb2.toString().split("\n")), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setPreferredSize(new Dimension(300,300));
        JOptionPane.showMessageDialog(null, scrollPane2, "Total de faturação", JOptionPane.INFORMATION_MESSAGE);

        StringBuilder sb3 = new StringBuilder();
        for (int i = 0; i < resultado3.size(); i++) {
            sb3.append("-----------------------------\n");
            sb3.append("FILIAL ").append(i+1).append("\n");
            for (int j = 0; j < 12; j++)
                sb3.append("    ").append(Month.of(j+1)).append(" -> ").append(resultado3.get(i).get(j)).append("\n");
        }
        JScrollPane scrollPane3 = new JScrollPane(new JList<>(sb3.toString().split("\n")), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane3.setPreferredSize(new Dimension(300,300));
        JOptionPane.showMessageDialog(null, scrollPane3, "Total de clientes distintos que compraram", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Função que imprime a query 1
     * @param resultado     Resultado da querie que pretendemos imprimir
     */
    public void printQuery1(List<String> resultado)
    {
        navegador(new ArrayList<>(resultado), "PRODUTOS NUNCA COMPRADOS - " + resultado.size());
    }

    /**
     * Função que imprime a query 2
     * @param resultado     Resultado da querie que pretendemos imprimir
     */
    public void printQuery2(List<List<Integer>> resultado)
    {
        StringBuilder sb = new StringBuilder();

        for (int fil = 0; fil < resultado.size(); fil++) {
            sb.append("Filial ").append(fil+1).append(": ").append("Número de Vendas = ").append(resultado.get(fil).get(0)).append( "  |  Número de Clientes Distintos = ").append(resultado.get(fil).get(1)).append("\n");
        }

        print(sb.toString());
    }

    /**
     * Função que imprime a query 3
     * @param resultado     Resultado da querie que pretendemos imprimir
     * @param codCliente    Código do Cliente que estamos a estudar na query
     */
    public void printQuery3(List<List<Double>> resultado, String codCliente)
    {
        StringBuilder sb = new StringBuilder();
        DecimalFormat fmt = new DecimalFormat("0.00");

        sb.append("Total de Produtos Distintos comprados pelo Cliente:\n");
        for (int i = 0; i < 12; i++) {
            double res = resultado.get(0).get(i);
            int resInt = (int) res;
            sb.append("    ").append(Month.of(i+1)).append(" -> ").append(resInt).append("\n");
        }
        sb.append("--------------------------------------------------\n");
        sb.append("Total de Compras efetuadas pelo Cliente:\n");
        for (int i = 0; i < 12; i++) {
            double res = resultado.get(1).get(i);
            int resInt = (int) res;
            sb.append("    ").append(Month.of(i+1)).append(" -> ").append(resInt).append("\n");
        }
        sb.append("--------------------------------------------------\n");
        sb.append("Total Gasto pelo Cliente:\n");
        for (int i = 0; i < 12; i++) {
            sb.append("    ").append(Month.of(i+1)).append(" -> ").append(fmt.format(resultado.get(2).get(i))).append("\n");
        }

        JScrollPane scrollPane = new JScrollPane(new JList<>(sb.toString().split("\n")), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(400,500));
        JOptionPane.showMessageDialog(null, scrollPane, "Informações da Query 3 para o Cliente " + codCliente, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Função que imprime a query 4
     * @param resultado     Resultado da querie que pretendemos imprimir
     * @param codProduto    Código do Produto que estamos a estudar na query
     */
    public void printQuery4(List<List<Double>> resultado, String codProduto)
    {
        StringBuilder sb = new StringBuilder();
        DecimalFormat fmt = new DecimalFormat("0.00");

        sb.append("\n");
        for (int i = 0; i < 12; i++) {
            double res = resultado.get(0).get(i);
            int resInt = (int) res;
            sb.append("    ").append(Month.of(i+1)).append(" -> ").append(resInt).append("\n");
        }
        sb.append("--------------------------------------------------\n");
        sb.append("Total de vezes que o Produto foi comprado:\n");
        for (int i = 0; i < 12; i++) {
            double res = resultado.get(1).get(i);
            int resInt = (int) res;
            sb.append("    ").append(Month.of(i+1)).append(" -> ").append(resInt).append("\n");
        }
        sb.append("--------------------------------------------------\n");
        sb.append("Total Faturado com o Produto:\n");
        for (int i = 0; i < 12; i++) {
            sb.append("    ").append(Month.of(i+1)).append(" -> ").append(fmt.format(resultado.get(2).get(i))).append("\n");
        }

        JScrollPane scrollPane = new JScrollPane(new JList<>(sb.toString().split("\n")), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(450,500));
        JOptionPane.showMessageDialog(null, scrollPane, "Informações da Query 4 para o Produto " + codProduto, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Função que imprime a query 5
     * @param resultado     Resultado da querie que pretendemos imprimir
     * @param codCliente    Código do Cliente que estamos a estudar na query
     */
    public void printQuery5(List<ParStringInteger> resultado, String codCliente)
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Produtos mais comprados pelo Cliente:\n");

        Consumer<ParStringInteger> action = v -> sb.append("    ").append(v.getStringKey()).append(" -> ").append(v.getInteiro()).append(" vezes\n");
        resultado.forEach(action);

        JScrollPane scrollPane = new JScrollPane(new JList<>(sb.toString().split("\n")), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(350,300));
        JOptionPane.showMessageDialog(null, scrollPane, "Informações da Query 5 para o Cliente " + codCliente, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Função que imprime a query 6
     * @param resultado     Resultado da querie que pretendemos imprimir
     * @param limite        Limite que estamos a estudar
     */
    public void printQuery6(List<Map.Entry<ParStringInteger, Integer>> resultado, int limite)
    {
        StringBuilder sb = new StringBuilder();

        sb.append("┌─────────────┬─────────────────────┬────────────────────┐\n")
          .append("│   PRODUTO   │  UNIDADES VENDIDAS  │ CLIENTES DISTINTOS │\n");

        for (int index = 0; index < resultado.size() ; index++) {
            sb.append("├─────────────┼─────────────────────┼────────────────────┤\n");
            sb.append("│   ").append(resultado.get(index).getKey().getStringKey()).append("    │");
            sb.append(auxQueriesEspaços(resultado.get(index).getKey().getInteiro(), 21, 9));
            sb.append(auxQueriesEspaços(resultado.get(index).getValue(), 20, 9)).append("\n");
        }

        sb.append("└─────────────┴─────────────────────┴────────────────────┘\n");

        JList jlist = new JList<>(sb.toString().split("\n"));
        jlist.setFixedCellHeight(16);
        JScrollPane scrollPane = new JScrollPane(jlist, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(500,500));
        scrollPane.getViewport().getView().setFont(new Font("monospaced", Font.PLAIN, 14));
        JOptionPane.showMessageDialog(null, scrollPane, "Informações da Query 6", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Função auxiliar que devolve uma string com o numero pretendido e espaços dos dois lados
     * @param nrCoisas      Inteiro que queremos imprimir no meio dos espaços brancos
     * @param nrTotal       Nr de espaços brancos que podemos utilizar
     * @param desvio        Nr de espcaos brancos que queremos deixar antes de imprimir o Integer
     * @return              String com o Integer pedido e os espaços certos brancos atrás e á frente do mesmo
     */
    public String auxQueriesEspaços (int nrCoisas, int nrTotal, int desvio)
    {
        StringBuilder sb = new StringBuilder();
        int tamanho = String.valueOf(nrCoisas).length();
        for (int i=0; i<desvio; i++) {
            sb.append(" ");
        }
        sb.append(nrCoisas);
        for (int i=desvio+tamanho; i<nrTotal; i++) {
            sb.append(" ");
        }
        sb.append("│");
        return sb.toString();

    }

    /**
     * Função auxiliar que devolve uma string com o numero pretendido e espaços dos dois lados
     * @param nrCoisas      Double que queremos imprimir no meio dos espaços brancos
     * @param nrTotal       Nr de espaços brancos que podemos utilizar
     * @param desvio        Nr de espcaos brancos que queremos deixar antes de imprimir o Double
     * @return              String com o Double pedido e os espaços certos brancos atrás e á frente do mesmo
     */
    public String auxQueriesEspaçosDouble (double nrCoisas, int nrTotal, int desvio)
    {
        StringBuilder sb = new StringBuilder();

        DecimalFormat fmt = new DecimalFormat("0.00");
        String faturado = fmt.format(nrCoisas);

        int tamanho = faturado.length();
        for (int i=0; i<desvio; i++) {
            sb.append(" ");
        }
        sb.append(faturado);

        for (int i=desvio+tamanho; i<nrTotal; i++) {
            sb.append(" ");
        }
        sb.append("│");
        return sb.toString();

    }

    /**
     * Função que imprime a query 7
     * @param resultado     Resultado da querie que pretendemos imprimir
     */
    public void printQuery7(List< List<ParStringDouble> > resultado)
    {
        StringBuilder sb = new StringBuilder();
        DecimalFormat fmt = new DecimalFormat("0.00");

        for (int fil = 0; fil < resultado.size(); fil++) {
            sb.append("FILIAL ").append(fil+1).append(":\n");

            for (int i = 0; i < 3; i++) {
                sb.append("    Cliente ").append(resultado.get(fil).get(i).getStringKey()).append(" gastou ");
                sb.append(fmt.format(resultado.get(fil).get(i).getInteiro()));
                sb.append("\n");
            }
            if (fil < resultado.size() - 1) sb.append("------------------------------------------\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(), "Três maiores compradores por filial", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Função que imprime a query 8
     * @param resultado     Resultado da querie que pretendemos imprimir
     * @param limite        Limite que estamos a estudar
     */
    public void printQuery8(List<ParStringInteger> resultado, int limite)
    {
        StringBuilder sb = new StringBuilder();

        sb.append("┌─────────────┬──────────────────────┐\n")
          .append("│   CLIENTE   │  PRODUTOS DISTINTOS  │\n");

        for (int index = 0; index < resultado.size() ; index++) {
            sb.append("├─────────────┼──────────────────────┤\n");
            sb.append("│    ").append(resultado.get(index).getStringKey()).append("    │");
            sb.append(auxQueriesEspaços(resultado.get(index).getInteiro(), 22, 10)).append("\n");
        }

        sb.append("└─────────────┴──────────────────────┘\n");

        JList jlist = new JList<>(sb.toString().split("\n"));
        jlist.setFixedCellHeight(16);
        JScrollPane scrollPane = new JScrollPane(jlist, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(325,500));
        scrollPane.getViewport().getView().setFont(new Font("monospaced", Font.PLAIN, 14));
        JOptionPane.showMessageDialog(null, scrollPane, "Informações da Query 8", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Função que imprime a query 9
     * @param resultado     Resultado da querie que pretendemos imprimir
     * @param codProduto    Código do Produto que estamos a estudar na query
     * @param limite        Limite que estamos a estudar
     */
    public void printQuery9(List<ParStringDouble> resultado, String codProduto, int limite)
    {
        StringBuilder sb = new StringBuilder();

        sb.append("CLIENTES QUE MAIS COMPRARAM POR QUANTIDADE\n" +
                  "  E O SEU FATURADO PARA O PRODUTO ").append(codProduto).append("  \n");
        sb.append("  ┌─────────────┬──────────────────────┐  \n")
                .append("  │   CLIENTE   │    TOTAL FATURADO    │  \n");

        for (int index = 0; index < resultado.size() ; index++) {
            sb.append("  ├─────────────┼──────────────────────┤  \n");
            sb.append("  │    ").append(resultado.get(index).getStringKey()).append("    │  ");
            sb.append(auxQueriesEspaçosDouble(resultado.get(index).getInteiro(), 20, 5)).append("  \n");
        }

        sb.append("  └─────────────┴──────────────────────┘  \n");

        JList<String> jlist = new JList<>(sb.toString().split("\n"));
        jlist.setFixedCellHeight(16);
        JScrollPane scrollPane = new JScrollPane(jlist, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(365,500));
        scrollPane.getViewport().getView().setFont(new Font("monospaced", Font.PLAIN, 14));
        JOptionPane.showMessageDialog(null, scrollPane, "Informações da Query 8", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Função que imprime a query 10
     * @param resultado     Resultado da querie que pretendemos imprimir
     */
    public void printQuery10(List<List<List<ParStringDouble>>> resultado)
    {
        Query10_View q10_view = new Query10_View(resultado);
    }


    /**
     * Função que cria um navegador de páginas que vá percorrer uma list
     * @param lista         List que Navegador de Páginas vai percorrer
     * @param descricao     Descrição da Janela do Navegador
     */
    public void navegador(List<Object> lista, String descricao)
    {
        int items_per_page = Navegador_View.ITEMS_PER_PAGE;
        Navegador navegador = new Navegador(items_per_page,
                (int)((double)lista.size() / (double)items_per_page + 0.999));
        Navegador_View navegador_view = new Navegador_View(navegador, lista, descricao);
    }
}
