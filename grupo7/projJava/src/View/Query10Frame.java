package View;

import javax.swing.*;
import java.awt.*;

/**
 * Classe Query10Frame onde vamos ter o a Query10
 */
public class Query10Frame extends JFrame
{
    JButton jumpToMes;
    JButton jumpToFilial;

    /**
     * Construtor parametrizado da Query10Frame
     * @param title     String com o Título da Janela da Query10Frame
     */
    public Query10Frame(String title)
    {
        super(title);

        setLayout(new GridLayout(1,2));

        jumpToMes = new JButton("Ir Para Mês");
        jumpToFilial = new JButton("Ir Para Filial");

        Container c = getContentPane();
        c.add(jumpToMes);
        c.add(jumpToFilial);
    }

    /**
     * Getter da botão de saltar para um mês
     * @return      Botão de saltar para um mês obtido
     */
    public JButton getJumpToMes()
    {
        return jumpToMes;
    }

    /**
     * Getter da botão de saltar para uma filial
     * @return      Botão de saltar para uma filial obtido
     */
    public JButton getJumpToFilial()
    {
        return jumpToFilial;
    }

    /**
     * Função que imprime um Object dado
     * @param o     Object a imprimir
     */
    public void print(Object o)
    {
        JList<String> jlist = new JList<>(o.toString().split("\n"));
        jlist.setFixedCellHeight(16);
        JScrollPane scrollPane = new JScrollPane(jlist, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(400,400));
        scrollPane.getViewport().getView().setFont(new Font("monospaced", Font.PLAIN, 14));

        JOptionPane.showMessageDialog(null, scrollPane, "Q10", JOptionPane.INFORMATION_MESSAGE);
    }
}
