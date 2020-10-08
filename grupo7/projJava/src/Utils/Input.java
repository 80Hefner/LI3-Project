package Utils;

/**
 * Classe que abstrai a utilização da classe Scanner, escondendo todos os
 * problemas relacionados com excepções, e que oferece métodos simples e
 * robustos para a leitura de valores de tipos simples e String.
 *
 * É uma classe de serviços, como Math e outras de Java, pelo que devem ser
 * usados os métodos de classe implementados.
 *
 * Utilizável em BlueJ, NetBeans, CodeBlocks ou Eclipse.
 *
 * Utilização típica:  int x = Input.lerInt();
 *                     String nome = Input.lerString();
 *
 * @author F. Mário Martins
 * @version 1.0 (6/2006)
 */
import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Classe Input utilizada para receber Inputs do Utilizador
 */
public class Input
{

    /**
     * Métodos de Classe
     */

    /**
     * Função que lê uma String metida no input pelo Utilizador
     * @return      String obtida do input dado pelo Utilizador
     */
    public static String lerString()
    {
        Scanner input = new Scanner(in);
        boolean ok = false;
        String txt = "";
        while (!ok) {
            try {
                txt = input.nextLine();
                ok = true;
            } catch (InputMismatchException e) {
                out.println("Texto Invalido");
                out.print("Novo valor: ");
                input.nextLine();
            }
        }
        //input.close();
        return txt;
    }


    /**
     * Função que lê um Integer metido no input pelo Utilizador
     * @return      Integer obtido do input dado pelo Utilizador
     */
    public static int lerInt()
    {
        Scanner input = new Scanner(in);
        boolean ok = false;
        int i = 0;
        while (!ok) {
            try {
                i = input.nextInt();
                ok = true;
            } catch (InputMismatchException e) {
                out.println("Inteiro Invalido");
                out.print("Novo valor: ");
                input.nextLine();
            }
        }
        //input.close();
        return i;
    }


    /**
     * Função que lê um Double metido no input pelo Utilizador
     * @return      Double obtido do input dado pelo Utilizador
     */
    public static double lerDouble()
    {
        Scanner input = new Scanner(in);
        boolean ok = false;
        double d = 0.0;
        while (!ok) {
            try {
                d = input.nextDouble();
                ok = true;
            } catch (InputMismatchException e) {
                out.println("Valor real Invalido");
                out.print("Novo valor: ");
                input.nextLine();
            }
        }
        //input.close();
        return d;
    }

    /**
     * Função que lê um Float metido no input pelo Utilizador
     * @return      Float obtido do input dado pelo Utilizador
     */
    public static float lerFloat()
    {
        Scanner input = new Scanner(in);
        boolean ok = false;
        float f = 0.0f;
        while (!ok) {
            try {
                f = input.nextFloat();
                ok = true;
            } catch (InputMismatchException e) {
                out.println("Valor real Invalido");
                out.print("Novo valor: ");
                input.nextLine();
            }
        }
        //input.close();
        return f;
    }

    /**
     * Função que lê um Booleano metido no input pelo Utilizador
     * @return      Booleano obtido do input dado pelo Utilizador
     */
    public static boolean lerBoolean()
    {
        Scanner input = new Scanner(in);
        boolean ok = false;
        boolean b = false;
        while (!ok) {
            try {
                b = input.nextBoolean();
                ok = true;
            } catch (InputMismatchException e) {
                out.println("Booleano Invalido");
                out.print("Novo valor: ");
                input.nextLine();
            }
        }
        //input.close();
        return b;
    }

    /**
     * Função que lê um Short metido no input pelo Utilizador
     * @return      Short obtido do input dado pelo Utilizador
     */
    public static short lerShort()
    {
        Scanner input = new Scanner(in);
        boolean ok = false;
        short s = 0;
        while (!ok) {
            try {
                s = input.nextShort();
                ok = true;
            } catch (InputMismatchException e) {
                out.println("Short Invalido");
                out.print("Novo valor: ");
                input.nextLine();
            }
        }
        //input.close();
        return s;
    }
}
