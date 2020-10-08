package View;

import javax.swing.*;
import java.awt.*;

/**
 * Classe MainMenuFrame onde vamos ter o menu principal
 */
public class MainMenuFrame extends JFrame
{
    private JButton queryE1Button;
    private JButton queryE2Button;
    private JButton query1Button;
    private JButton query2Button;
    private JButton query3Button;
    private JButton query4Button;
    private JButton query5Button;
    private JButton query6Button;
    private JButton query7Button;
    private JButton query8Button;
    private JButton query9Button;
    private JButton query10Button;
    private JButton loadFromDisk;
    private JButton saveToDisk;
    private JButton loadButton;
    private JButton exitButton;

    /**
     * Construtor parametrizado do MainMenuFrame
     * @param title     String com o Título da Janela do Menu
     */
    public MainMenuFrame(String title)
    {
        super(title);

        setLayout(new GridLayout(8,2));

        queryE1Button = new JButton("Query Estatística 1");
        queryE2Button = new JButton("Query Estatística 2");
        query1Button = new JButton("Query 1");
        query2Button = new JButton("Query 2");
        query3Button = new JButton("Query 3");
        query4Button = new JButton("Query 4");
        query5Button = new JButton("Query 5");
        query6Button = new JButton("Query 6");
        query7Button = new JButton("Query 7");
        query8Button = new JButton("Query 8");
        query9Button = new JButton("Query 9");
        query10Button = new JButton("Query 10");
        loadFromDisk = new JButton("Load From Disk");
        saveToDisk = new JButton("Save To Disk");
        loadButton = new JButton("Load");
        exitButton = new JButton("Exit");

        Container c = getContentPane();
        c.add(queryE1Button);
        c.add(queryE2Button);
        c.add(query1Button);
        c.add(query2Button);
        c.add(query3Button);
        c.add(query4Button);
        c.add(query5Button);
        c.add(query6Button);
        c.add(query7Button);
        c.add(query8Button);
        c.add(query9Button);
        c.add(query10Button);
        c.add(loadFromDisk);
        c.add(saveToDisk);
        c.add(loadButton);
        c.add(exitButton);
    }

    /**
     * Getter do botão da query estatística 1
     * @return      Botão correspondente á query 1
     */
    public JButton getQueryE1Button()
    {
        return queryE1Button;
    }

    /**
     * Getter do botão da query estatística 2
     * @return      Botão correspondente á query 1
     */
    public JButton getQueryE2Button()
    {
        return queryE2Button;
    }

    /**
     * Getter do botão da query1
     * @return      Botão correspondente á query 1
     */
    public JButton getQuery1Button()
    {
        return query1Button;
    }

    /**
     * Getter do botão da query2
     * @return      Botão correspondente á query 2
     */
    public JButton getQuery2Button()
    {
        return query2Button;
    }

    /**
     * Getter do botão da query3
     * @return      Botão correspondente á query 3
     */
    public JButton getQuery3Button()
    {
        return query3Button;
    }

    /**
     * Getter do botão da query4
     * @return      Botão correspondente á query 4
     */
    public JButton getQuery4Button()
    {
        return query4Button;
    }

    /**
     * Getter do botão da query5
     * @return      Botão correspondente á query 5
     */
    public JButton getQuery5Button()
    {
        return query5Button;
    }

    /**
     * Getter do botão da query6
     * @return      Botão correspondente á query 6
     */
    public JButton getQuery6Button()
    {
        return query6Button;
    }

    /**
     * Getter do botão da query7
     * @return      Botão correspondente á query 7
     */
    public JButton getQuery7Button()
    {
        return query7Button;
    }

    /**
     * Getter do botão da query8
     * @return      Botão correspondente á query 8
     */
    public JButton getQuery8Button()
    {
        return query8Button;
    }

    /**
     * Getter do botão da query9
     * @return      Botão correspondente á query 9
     */
    public JButton getQuery9Button()
    {
        return query9Button;
    }

    /**
     * Getter do botão da query10
     * @return      Botão correspondente á query 10
     */
    public JButton getQuery10Button()
    {
        return query10Button;
    }

    /**
     * Getter do botão do load from disk
     * @return      Botão correspondente ao load from disk
     */
    public JButton getLoadFromDiskButton()
    {
        return loadFromDisk;
    }

    /**
     * Getter do botão do save to disk
     * @return      Botão correspondente ao save to disk
     */
    public JButton getSaveToDiskButton()
    {
        return saveToDisk;
    }

    /**
     * Getter do botão do load
     * @return      Botão correspondente ao load
     */
    public JButton getLoadButton()
    {
        return loadButton;
    }

    /**
     * Getter do botão de exit
     * @return      Botão correspondente ao exit
     */
    public JButton getExitButton()
    {
        return exitButton;
    }
}
