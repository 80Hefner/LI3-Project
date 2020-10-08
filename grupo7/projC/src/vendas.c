/**
 * @file    vendas.c
 * @brief   Ficheiro que trata as vendas lidas do ficheiro
 */

#include "headers.h"

extern int global_NUM_VENDAS_TOTAL;
extern int global_NUM_VENDAS_VAL;

/**
 * @brief                   Função que carrega as estruturas da faturacao e das filiais, a partir de um ficheiro
 * @param produtos          Apontador para a estrutura dos produtos
 * @param clientes          Apontador para a estrutura dos clientes
 * @param faturacao         Apontador para a estrutura da faturacao
 * @param filial            Array de apontadores para as 3 estruturas das filiais
 * @param salesFilePath     Path para a localização do ficheiro com as vendas
 */
void loadVendas (PRODUTOS produtos, CLIENTES clientes, FATURACAO faturacao, FILIAL filial[], char* salesFilePath)
{
    clock_t start = clock();
    char *codigo = malloc (64 * sizeof(char));
    int count_val = 0, count_tot = 0;

    FILE *f = fopen(salesFilePath, "r");

    printf("   A ler do ficheiro %s\n", salesFilePath);

    while (fgets(codigo, 64, f)) {
        strtok (codigo, "\r\n");
        count_val += validaVenda(codigo, produtos, clientes, faturacao, filial);
        count_tot++;
    }

    fclose(f);
    free(codigo);

    printf("   Linhas lidas: %d\n", count_tot);
    printf("   Linhas validadas: %d\n", count_val);
    global_NUM_VENDAS_TOTAL = count_tot;
    global_NUM_VENDAS_VAL = count_val;

    clock_t end = clock();
    printf("   %f seg para guardar as vendas.\n\n", (double)(end-start) / CLOCKS_PER_SEC);
}

/**
 * @brief               Função que testa a validade de uma venda e insere a no SGV, caso seja válida
 * @param codigo        Codigo de uma venda
 * @param produtos      Apontador para a estrutura dos produtos
 * @param clientes      Apontador para a estrutura dos clientes
 * @param faturacao     Apontador para a estrutura da faturacao
 * @param filial        Array de apontadores para as 3 estruturas das filiais
 * @return              1 caso seja uma venda válida; 0 caso contrário
 */
int validaVenda (char* codigo, PRODUTOS produtos, CLIENTES clientes, FATURACAO faturacao, FILIAL filial[])
{
    char *productID, *clientID;
    int quantidade, mes, ind_filial;
    float preco;
    char modo;
    int check = 0;

    codigo = strtok (codigo, " ");
    productID = mystrdup(codigo);

    codigo = strtok (NULL, " ");
    preco = atof(codigo);

    codigo = strtok (NULL, " ");
    quantidade = atoi(codigo);

    codigo = strtok (NULL, " ");
    modo = codigo[0];

    codigo = strtok (NULL, " ");
    clientID = mystrdup(codigo);

    codigo = strtok (NULL, " ");
    mes = atoi (codigo);

    codigo = strtok (NULL, " ");
    ind_filial = atoi (codigo);

    if (searchProduto(produtos, productID)
     && preco >= 0 && preco < 1000
     && quantidade >= 1 && quantidade <= 200
     && (modo == 'N' || modo == 'P')
     && searchCliente(clientes, clientID)
     && mes >= 1 && mes <= 12
     && ind_filial >= 1 && ind_filial <= 3)
    {
        insereVendaFilial(filial[ind_filial-1], productID, clientID, modo, preco, quantidade, mes);
        insereFaturacao(faturacao, productID, preco, quantidade, modo, mes, ind_filial);
        check = 1;
    }

    free(productID);
    free(clientID);
    return check;
}
