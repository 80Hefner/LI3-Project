package Structs;

import java.util.Objects;

/**
 * Classe ParStringInteger
 */
public class ParStringInteger {
    private String stringKey;
    private int inteiro;

    /**
     * Construtor por omissão do ParStringInteger
     */
    public ParStringInteger() {
        this.stringKey = "";
        this.inteiro = 0;
    }

    /**
     * Construtor de cópia do ParStringInteger
     * @param par           ParStringInteger a copiar
     */
    public ParStringInteger(ParStringInteger par) {
        this.stringKey = par.getStringKey();
        this.inteiro = par.getInteiro();
    }

    /**
     * Construtor parametrizado do ParStringInteger
     * @param stringKey     String que pretendemos colocar
     * @param inteiro       Integer que pretendemos colocar
     */
    public ParStringInteger(String stringKey, int inteiro) {
        this.stringKey = stringKey;
        this.inteiro = inteiro;
    }

    /**
     * Construtor parametrizado do NodoFatura
     * @param stringKey     String que pretendemos colocar
     */
    public ParStringInteger(String stringKey) {
        this.stringKey = stringKey;
        this.inteiro = 0;
    }

    /**
     * Getter da String do ParStringInteger
     * @return           String obtida
     */
    public String getStringKey() {
        return stringKey;
    }

    /**
     * Getter do Integer do ParStringInteger
     * @return           Integer obtido
     */
    public int getInteiro() {
        return inteiro;
    }

    /**
     * Setter da String do ParStringInteger
     * @param stringKey     String que pretendemos colocar no ParStringInteger
     */
    public void setStringKey(String stringKey) {
        this.stringKey = stringKey;
    }

    /**
     * Setter do Integer do ParStringInteger
     * @param inteiro     Integer que pretendemos colocar no ParStringInteger
     */
    public void setInteiro(int inteiro) {
        this.inteiro = inteiro;
    }

    /**
     * Função que encrementa ao inteiro do Par o inteiro
     * @param inteiro     Integer que pretendemos encrementar no ParStringInteger
     */
    public void setInteiroSomaComAntigo(int inteiro) {
        this.inteiro = this.inteiro + inteiro;
    }

    /**
     * Função que dá clone ao ParStringInteger
     * @return           Cópia do ParStringInteger
     */
    public ParStringInteger clone()
    {
        return new ParStringInteger(this);
    }


    /**
     * Função que formula um HashCode de cada ParStringInteger
     * @return Inteiro que é o código Hash do ParStringInteger
     */
    @Override
    public int hashCode() {
        return Objects.hash(stringKey);
    }


    /**
     * Função de equals do ParStringInteger
     * @param o           Objeto ao qual queremos comparar o ParStringInteger
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParStringInteger that = (ParStringInteger) o;
        return //inteiro == that.inteiro &&
                Objects.equals(stringKey, that.stringKey);
    }

    /**
     * Função que transforma o ParStringInteger numa String
     * @return           String resultante da função
     */
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ParStringInteger{");
        sb.append("stringKey='").append(stringKey).append('\'');
        sb.append(", inteiro=").append(inteiro);
        sb.append('}');
        return sb.toString();
    }
}