package View;

import NewExceptions.PageOutOfBoundsException;
import Utils.Navegador;

import javax.swing.*;
import java.util.List;

/**
 * Classe Navegador_View Responsável pela view do Navegador de páginas
 */
public class Navegador_View
{
    public static final int ITEMS_PER_PAGE = 49;
    private Navegador navegador;
    private List<Object> lista;
    private NavegadorFrame window;
    private String descricao;

    /**
     * Construtor parametrizado do Navegador_View
     * @param navegador     Navegador que pretendemos imprimir
     * @param lista         Lista de Objects que pretendemos imprimir
     * @param descricao     Descrição da Janela do Navegador
     */
    public Navegador_View(Navegador navegador, List<Object> lista, String descricao)
    {
        this.navegador = navegador;
        this.lista = lista;
        this.descricao = descricao;

        window = new NavegadorFrame("Navegador");
        window.setSize(600,450);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.getLeftArrowButton().setEnabled(false);

        window.getLeftArrowButton().addActionListener(e -> {
            try {
                if (navegador.getCurrentPage() == navegador.totalPages-1) window.getRightArrowButton().setEnabled(true);
                navegador.prevPage();
                if (navegador.getCurrentPage() == 0) window.getLeftArrowButton().setEnabled(false);
                printQuery();
            } catch (PageOutOfBoundsException exception) {
                JOptionPane.showMessageDialog(null, exception, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        window.getRightArrowButton().addActionListener(e -> {
            try {
                if (navegador.getCurrentPage() == 0) window.getLeftArrowButton().setEnabled(true);
                navegador.nextPage();
                if (navegador.getCurrentPage() == navegador.totalPages-1) window.getRightArrowButton().setEnabled(false);
                printQuery();
            } catch (PageOutOfBoundsException exception) {
                JOptionPane.showMessageDialog(null, exception, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        window.getJumpToButton().addActionListener(e -> {
            try {
                try {
                    int x = Integer.parseInt(JOptionPane.showInputDialog("Saltar para a página"));
                    navegador.setCurrentPage(x-1);
                } catch(NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Input inválido", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                printQuery();
            } catch (PageOutOfBoundsException exception) {
                JOptionPane.showMessageDialog(null, exception, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        printQuery();
    }

    /**
     * Getter do Navegador
     * @return      Navegador obtido
     */
    public Navegador getNavegador()
    {
        return navegador;
    }

    /**
     * Setter do Navegador
     * @param navegador       Navegador a colocar
     */
    public void setNavegador(Navegador navegador)
    {
        this.navegador = navegador;
    }

    /**
     * Getter do NavegadorFrame
     * @return      NavegadorFrame obtido
     */
    public NavegadorFrame getWindow()
    {
        return window;
    }

    /**
     * Setter do NavegadorFrame
     * @param window       NavegadorFrame a colocar
     */
    public void setWindow(NavegadorFrame window)
    {
        this.window = window;
    }

    /**
     * Função que imprime uma qualquer Query que devolva uma List de Objects, imprimindo-a sob a forma de paginação
     */
    public void printQuery()
    {
        StringBuilder sb = new StringBuilder();
        //int total_linhas = navegador.itemsPerPage / 7;
        int total_linhas = 0, items_por_linha = 0;
        int ind_inicial = navegador.getCurrentPage() * navegador.itemsPerPage;
        int total_items = lista.size();
        int items_per_page;
        if (navegador.getCurrentPage() == navegador.totalPages - 1)
            items_per_page = total_items - ind_inicial;
        else
            items_per_page = navegador.itemsPerPage;

        for (int i = 7; i > 0; i--) {
            if (navegador.itemsPerPage % i == 0 ) {
                if (total_items - ind_inicial < items_per_page)
                    total_linhas = (total_items - ind_inicial) / i + 1;
                else
                    total_linhas = navegador.itemsPerPage / i;

                items_por_linha = i;
                break;
            }
        }

        sb.append("\n\n                    ").append(descricao).append("\n");
        sb.append("\n      ┌"); for (int k = 0; k < items_por_linha - 1; k++) sb.append("────────┬");
        sb.append("────────┐\n");
        //sb.append("      ┌────────┬────────┬────────┬────────┬────────┬────────┬────────┐\n");
        for (int i = 0; i < total_linhas-1; i++) {
            int j;
            sb.append("      │"); for (j = 0; j < items_por_linha && ind_inicial < total_items; j++) sb.append(" ").append(lista.get(ind_inicial++).toString()).append(" │");
            sb.append("\n      ├"); for (int k = 0; k < j-1; k++) sb.append("────────┼");
            sb.append("────────┤\n");
            //sb.append("\n      ├────────┼────────┼────────┼────────┼────────┼────────┼────────┤\n");
        }
        int j;
        sb.append("      │"); for (j = 0; j < items_por_linha && ind_inicial < total_items; j++) sb.append(" ").append(lista.get(ind_inicial++).toString()).append(" │");
        sb.append("\n      └"); for (int k = 0; k < j-1; k++) sb.append("────────┴");
        sb.append("────────┘\n");
        //sb.append("\n      └────────┴────────┴────────┴────────┴────────┴────────┴────────┘\n");
        sb.append("                                                      PAG ").append(navegador.getCurrentPage()+1).append("/").append(navegador.totalPages).append("\n");

        window.print(sb.toString());
    }
}
