package View;

import Comparators.ComparatorStringDouble;
import Structs.ParStringDouble;

import javax.swing.*;
import java.text.DecimalFormat;
import java.time.Month;
import java.util.List;

/**
 * Classe Query10_View Responsável pela view da Query10Frame
 */
public class Query10_View
{
    private Query10Frame window;
    private List<List<List<ParStringDouble>>> lista;
    int mes_atual;
    int filial_atual;

    /**
     * Construtor parametrizado da Query10_View
     * @param lista         Lista de Lista de Lista de Objects que pretendemos imprimir
     */
    public Query10_View(List<List<List<ParStringDouble>>> lista)
    {
        this.lista = lista;
        this.mes_atual = 0;
        this.filial_atual = 0;

        window = new Query10Frame("Resultados Query 10");
        window.setSize(400,200);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        window.getJumpToMes().addActionListener(e -> {
            int mes = Integer.parseInt(JOptionPane.showInputDialog("Insira um mês"));
            if (mes < 0 || mes > 12)
                JOptionPane.showMessageDialog(null, "Mês inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            else {
                mes_atual = mes - 1;
                printQuery();
            }
        });

        window.getJumpToFilial().addActionListener(e -> {
            int filial = Integer.parseInt(JOptionPane.showInputDialog("Insira uma filial"));
            if (filial < 0 || filial > lista.get(0).size())
                JOptionPane.showMessageDialog(null, "Filial inválida", "Erro", JOptionPane.ERROR_MESSAGE);
            else {
                filial_atual = filial - 1;
                printQuery();
            }
        });

        printQuery();
    }

    /**
     * Função que imprime uma a Query10
     */
    public void printQuery()
    {
        StringBuilder sb = new StringBuilder();
        DecimalFormat fmt = new DecimalFormat("0.00");
        List<ParStringDouble> lista_par = lista.get(mes_atual).get(filial_atual);
        lista_par.sort(new ComparatorStringDouble());

        sb.append("\n      MES = ").append(Month.of(mes_atual+1));
        sb.append("  |  FILIAL = ").append(filial_atual + 1).append("  \n");

        sb.append("  ┌─────────────┬────────────────────┐  \n");
        sb.append("  │   PRODUTO   │   TOTAL FATURADO   │  \n");

        for (int i = 0; i < lista_par.size(); i++) {
            String produto = lista_par.get(i).getStringKey();
            String faturado = fmt.format(lista_par.get(i).getInteiro());
            sb.append("  ├─────────────┼────────────────────┤  \n");
            sb.append("  │    ").append(produto).append("   │  ").append(faturado);
            sb.append(" ".repeat(18 - faturado.length())).append("│  \n");
        }
        sb.append("  └─────────────┴────────────────────┘  \n");

        window.print(sb.toString());
    }

}
