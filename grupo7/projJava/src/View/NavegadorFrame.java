package View;

import javax.swing.*;
import java.awt.*;

/**
 * Classe NavegadorFrame onde vamos ter o Navegador de Páginas
 */
public class NavegadorFrame extends JFrame
{
    private JTextArea displayArea;
    private JButton leftArrowButton;
    private JButton rightArrowButton;
    private JButton jumpToButton;

    /**
     * Construtor parametrizado do NavegadorFrame
     * @param title     String com o Título da Janela do Navegador
     */
    public NavegadorFrame(String title)
    {
        super(title);

        setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setFont(new Font("monospaced", Font.PLAIN, 14));
        leftArrowButton = new JButton("<-");
        rightArrowButton = new JButton("->");
        jumpToButton = new JButton("Jump to");

        Container c = getContentPane();
        c.add(displayArea, BorderLayout.NORTH);
        c.add(leftArrowButton, BorderLayout.WEST);
        c.add(rightArrowButton, BorderLayout.EAST);
        c.add(jumpToButton, BorderLayout.CENTER);
    }

    /**
     * Getter da DisplayArea
     * @return      DisplayArea obtida
     */
    public JTextArea getDisplayArea()
    {
        return displayArea;
    }

    /**
     * Setter da DisplayArea
     * @param displayArea   DisplayArea a colocar
     */
    public void setDisplayArea(JTextArea displayArea)
    {
        this.displayArea = displayArea;
    }

    /**
     * Getter do botão com a seta para a esquerda
     * @return      Botão com a seta para a esquerda obtido
     */
    public JButton getLeftArrowButton()
    {
        return leftArrowButton;
    }

    /**
     * Setter do botão com a seta para a esquerda
     * @param leftArrowButton   Botão com a seta para a esquerda a colocar
     */
    public void setLeftArrowButton(JButton leftArrowButton)
    {
        this.leftArrowButton = leftArrowButton;
    }

    /**
     * Getter do botão com a seta para a direita
     * @return      Botão com a seta para a direita obtido
     */
    public JButton getRightArrowButton()
    {
        return rightArrowButton;
    }

    /**
     * Setter do botão com a seta para a direita
     * @param rightArrowButton   Botão com a seta para a direita a colocar
     */
    public void setRightArrowButton(JButton rightArrowButton)
    {
        this.rightArrowButton = rightArrowButton;
    }

    /**
     * Getter da botão de saltar para uma página
     * @return      Botão de saltar para uma página obtido
     */
    public JButton getJumpToButton()
    {
        return jumpToButton;
    }

    /**
     * Setter da botão de saltar para uma página
     * @param jumpToButton   Botão de saltar para uma página a colocar
     */
    public void setJumpToButton(JButton jumpToButton)
    {
        this.jumpToButton = jumpToButton;
    }

    /**
     * Função que imprime um Object dado
     * @param o     Object a imprimir
     */
    public void print(Object o)
    {
        displayArea.setText(o.toString());
    }

}
