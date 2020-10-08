/**
 * @file    interface.c
 * @brief   Ficheiro que contém as funções para responder às queries
 */

#include "headers.h"

struct sgv {
    PRODUTOS produtos;
    CLIENTES clientes;
    FATURACAO faturacao;
    FILIAL filial [3];
};

/*********************************SEARCHS***********************************************/
/**
 * @brief               Função que chama a searchProduto
 * @param sgv           Apontador para a estrutura SGV
 * @param productID     Código de produto a ser pesquisado
 * @return              1 caso encontre o produto; 0 caso contrário
 */
int searchProdutoSGV(SGV sgv, char* productID)
{
    return searchProduto(sgv->produtos, productID);
}

/**
 * @brief               Função que chama a searchCliente
 * @param sgv           Apontador para a estrutura SGV
 * @param clientID      Código de cliente a ser pesquisado
 * @return              1 caso encontre o cliente; 0 caso contrário
 */
int searchClienteSGV(SGV sgv, char* clientID)
{
    return searchCliente(sgv->clientes, clientID);
}

/*********************************INIT***********************************************/

/**
 * @brief   Função que inicializa a estrutura do sistema de gestão de Vendas
 * @return  Apontador para a estrutura inicializada
 */
SGV initSGV()
{
    SGV sgv = malloc(sizeof(struct sgv));

    return sgv;
}

/*********************************DESTROY***********************************************/

/**
 * @brief       Função que liberta a memória alocada para a estrutura SGV
 * @param sgv   Apontador para a estrutura SGV
 */
void destroySGV(SGV sgv)
{
    sgv->produtos = freeProdutos(sgv->produtos);
    sgv->clientes = freeClientes(sgv->clientes);
    sgv->faturacao = freeFaturacao(sgv->faturacao);
    sgv->filial[0] = freeFilial(sgv->filial[0]);
    sgv->filial[1] = freeFilial(sgv->filial[1]);
    sgv->filial[2] = freeFilial(sgv->filial[2]);
    free(sgv);
}

/*********************************LOAD***********************************************/

/**
 * @brief                   Função que inicializa os parâmetros da estrutura SGV, a partir de ficheiros
 * @param sgv               Apontador para a estrutura SGV
 * @param clientsFilePath   Path para a localização do ficheiro com os clientes
 * @param productsFilePath  Path para a localização do ficheiro com os produtos
 * @param salesFilePath     Path para a localização do ficheiro com as vendas
 * @return                  Apontador para a estrutura SGV, com os dados carregados
 */
SGV loadSGVFromFiles(SGV sgv,
     char* clientsFilePath,
     char* productsFilePath,
     char* salesFilePath
     )
{
    sgv->clientes = initClientes(clientsFilePath);
    sgv->produtos = initProdutos(productsFilePath);
    sgv->faturacao = initFaturacao(sgv->produtos);
    sgv->filial[0] = initFilial(sgv->clientes);
    sgv->filial[1] = initFilial(sgv->clientes);
    sgv->filial[2] = initFilial(sgv->clientes);
    loadVendas(sgv->produtos, sgv->clientes, sgv->faturacao, sgv->filial, salesFilePath);
/*
    printf("   Tamanho da estrutura dos clientes: %lu kBytes\n", getSizeStructClientes(sgv->clientes) / 1024);
    printf("   Tamanho da estrutura dos produtos: %lu kBytes\n", getSizeStructProdutos(sgv->produtos) / 1024);
    printf("   Tamanho da estrutura da filial 1: %lu kBytes\n", getSizeStructFilial(sgv->filial[0]) / 1024);
    printf("   Tamanho da estrutura da filial 2: %lu kBytes\n", getSizeStructFilial(sgv->filial[1]) / 1024);
    printf("   Tamanho da estrutura da filial 3: %lu kBytes\n", getSizeStructFilial(sgv->filial[2]) / 1024);
    printf("   Tamanho da estrutura da faturação: %lu kBytes\n\n", getSizeStructFaturacao(sgv->faturacao) / 1024);
*/
    return sgv;
}

/*********************************QUERY 2***********************************************/

/**
 * @brief           Função que retorna o número de produtos começados por uma dada letra
 * @param sgv       Apontador para a estrutura SGV
 * @param letter    Letra inicial dos produtos a ser procurados
 * @return          Array com os produtos começados por uma dada letra
 */
GPtrArray* getProductsStartedByLetter(SGV sgv, char letter)
{
    int i, j; /* i, j: índices para percorrer os arrays */
    char letter2; /* letter2: variável auxiliar para guardar a letra correspondente da iteração */
    int* array;
    int size;
    char codigo[7];
    GPtrArray* arrayProdutosComLetra = g_ptr_array_new();

    for (i = 0; i < 26; i++) { /* percorre a segunda letra dos produtos */
        letter2 = i + 'A';
        array = getArrayProdutos(sgv->produtos, letter, letter2);
        size = getSizeProdutos(sgv->produtos, letter, letter2);
        for (j = 0; j < size; j++) {
            sprintf(codigo, "%c%c%d", letter, letter2, array[j]);
            g_ptr_array_add(arrayProdutosComLetra, mystrdup(codigo));
        }
        free(array);
    }

    return arrayProdutosComLetra;
}

/*********************************QUERY 3**********************************************/

/**
 * @brief               Função que retorna o total de vendas e o total da faturação de um dado produto num dado mês
 * @param sgv           Apontador para a estrutura SGV
 * @param productID     Código do produto a ser procurado
 * @param month         Mês a procurar
 * @return              Estrutura com o total de vendas e o total da faturação de um dado produto num dado mês
 */
QUERY3AUX getProductSalesAndProfit(SGV sgv, char* productID, int month)
{
    QUERY3 Filial1N = getProductDataByMonthAndBranch(sgv->faturacao, productID, month, 'N', 1);
    QUERY3 Filial2N = getProductDataByMonthAndBranch(sgv->faturacao, productID, month, 'N', 2);
    QUERY3 Filial3N = getProductDataByMonthAndBranch(sgv->faturacao, productID, month, 'N', 3);
    QUERY3 Filial1P = getProductDataByMonthAndBranch(sgv->faturacao, productID, month, 'P', 1);
    QUERY3 Filial2P = getProductDataByMonthAndBranch(sgv->faturacao, productID, month, 'P', 2);
    QUERY3 Filial3P = getProductDataByMonthAndBranch(sgv->faturacao, productID, month, 'P', 3);

    QUERY3AUX auxiliar = malloc(sizeof(struct query3Aux));
    auxiliar->filial1N = Filial1N;
    auxiliar->filial2N = Filial2N;
    auxiliar->filial3N = Filial3N;
    auxiliar->filial1P = Filial1P;
    auxiliar->filial2P = Filial2P;
    auxiliar->filial3P = Filial3P;

    return auxiliar;
}

/*********************************QUERY 4**********************************************/

/**
 * @brief               Função que retorna os produtos que nunca foram comprados
 * @param sgv           Apontador para a estrutura SGV
 * @param branchID      Indicador da filial
 * @return              Estrutura com os produtos que nunca foram comprados
 */
BUFFER_Q4 getProductsNeverBought(SGV sgv, int branchID)
{
    BUFFER_Q4 buffer;
    buffer = getProductsNeverBoughtAndSize(sgv->faturacao, branchID);

    return buffer;
}

/*********************************QUERY 5**********************************************/

/**
 * @brief               Função que retorna uma lista ordenada de clientes que compraram em todas as filiais
 * @param sgv           Apontador para a estrutura SGV
 * @return              GTree com os clientes que compraram em todas as filiais
 */
GTree* getClientsOfAllBranches (SGV sgv)
{
    GTree* clientesFilial1 = getClientsWhoBoughtFromBranch(sgv->filial[0]);
    GTree* clientesFilial2 = getClientsWhoBoughtFromBranch(sgv->filial[1]);
    GTree* clientesFilial3 = getClientsWhoBoughtFromBranch(sgv->filial[2]);
    GTree* comparacao1     = g_tree_new_full(compareAVLStrings, NULL, freeAVLStrings, freeAVLStrings);
    GTree* comparacaoFinal = g_tree_new_full(compareAVLStrings, NULL, freeAVLStrings, freeAVLStrings);
    QUERY5 query5 = malloc(sizeof (struct query5));

    query5->comparar  = &clientesFilial1;
    query5->resultado = &comparacao1;
    g_tree_foreach(clientesFilial2, verSePresenteNoutraArvore, &query5);

    query5->comparar  = &comparacao1;
    query5->resultado = &comparacaoFinal;
    g_tree_foreach(clientesFilial3, verSePresenteNoutraArvore, &query5);

    g_tree_destroy(clientesFilial1);
    g_tree_destroy(clientesFilial2);
    g_tree_destroy(clientesFilial3);
    g_tree_destroy(comparacao1);
    free(query5);

    return comparacaoFinal;
}

/*********************************QUERY 6**********************************************/

/**
 * @brief               Função que retorna quantos produtos nunca foram comprados e quantos clientes nunca compraram
 * @param sgv           Apontador para a estrutura SGV
 * @return              Estrutura que guarda quantos produtos nunca foram comprados e quantos clientes nunca compraram
 */
QUERY6 getClientsAndProductsNeverBoughtCount(SGV sgv)
{
    int clientes_count = 0, produtos_count = 0;
    QUERY6 query6 = malloc(sizeof(struct query6));

    produtos_count = getProductsNeverBoughtCount(sgv->faturacao);
    clientes_count = getClientsNeverBoughtCount(sgv->filial);

    query6->nrProdutos = produtos_count;
    query6->nrClientes = clientes_count;

    return query6;
}


/*********************************QUERY 7**********************************************/

/**
 * @brief               Função que retorna uma estrutura com as quantidades de produtos comprados por um cliente dividido pelos diferentes meses e diferentes filiais
 * @param sgv           Apontador para a estrutura SGV
 * @param clienteID     Código do cliente a ser estudado
 * @return              Estrutura com as quantidades de produtos comprados por um cliente dividido pelos diferentes meses e diferentes filiais
 */
QUERY7 getProductsBoughtByClient(SGV sgv, char* clientID)
{
    int i;
    QUERY7 query7 = malloc(sizeof(struct query7));

    int quantidadesProdutoFilial1[12] = {0};
      getProductQuantityBoughtByClient(sgv->filial[0], clientID, quantidadesProdutoFilial1);
      for (i=0; i<12; i++) {
        query7->quantidadesProdutoFilial1[i] = quantidadesProdutoFilial1[i];
      }
    int quantidadesProdutoFilial2[12] = {0};
      getProductQuantityBoughtByClient(sgv->filial[1], clientID, quantidadesProdutoFilial2);
      for (i=0; i<12; i++) {
        query7->quantidadesProdutoFilial2[i] = quantidadesProdutoFilial2[i];
      }
    int quantidadesProdutoFilial3[12] = {0};
      getProductQuantityBoughtByClient(sgv->filial[2], clientID, quantidadesProdutoFilial3);
      for (i=0; i<12; i++) {
        query7->quantidadesProdutoFilial3[i] = quantidadesProdutoFilial3[i];
      }


    int quantidadesTotaisMes[12] = {0};
    int quantidadeTotal = 0;
    for (i=0; i<12; i++) {
      quantidadesTotaisMes[i] = quantidadesProdutoFilial1[i] + quantidadesProdutoFilial2[i] + quantidadesProdutoFilial3[i];
      quantidadeTotal += quantidadesTotaisMes[i];
      query7->quantidadesTotaisMes[i] = quantidadesTotaisMes[i];
    }
    query7->quantidadeTotal = quantidadeTotal;

    return query7;
}

/*********************************QUERY 8**********************************************/

/**
 * @brief               Função que retorna o total faturado e o número de vendas num dado intervalo de tempo
 * @param sgv           Apontador para a estrutura SGV
 * @param minMonth      Mês inicial
 * @param minMonth      Mês final
 * @return              Estrutura com o total faturado e o número de vendas num dado intervalo de tempo
 */
QUERY8 getSalesAndProfit(SGV sgv, int minMonth, int maxMonth)
{
    QUERY8 buffer;
    buffer = getAllProductsData(sgv->faturacao, minMonth, maxMonth);
    return buffer;
}

/*********************************QUERY 9**********************************************/

/**
 * @brief               Função que retorna os códigos de cliente que compraram um dado produto numa dada filial, distiguindo o modo da compra
 * @param sgv           Apontador para a estrutura SGV
 * @param productID     Código do produto a ser estudado
 * @param branch        Índice da filial a ser estudada
 * @return              Estrutura com os códigos de cliente que compraram um dado produto numa dada filial
 */
QUERY9 getProductBuyers(SGV sgv, char* productID, int branch)
{
    QUERY9 productBuyers;
    productBuyers = getClientsWhoBoughtAProductFromBranch(sgv->filial[branch-1], productID);
    return productBuyers;
}

/*********************************QUERY 10**********************************************/

/**
 * @brief               Função que retorna a lista dos produtos comprados por um dado cliente num dado mês, ordenada por ordem decrescente da quantidade
 * @param sgv           Apontador para a estrutura SGV
 * @param clientID      Código do cliente a ser estudado
 * @param month         Mês a ser estudado
 * @return              Array com os produtos comprados por um dado cliente num dado mês
 */
 GPtrArray* getClientFavoriteProducts(SGV sgv, char* clientID, int month)
{
    GPtrArray* array = g_ptr_array_new();
    getProductsBoughtByClientAndQuantity(sgv->filial[0], clientID, month, array);
    getProductsBoughtByClientAndQuantity(sgv->filial[1], clientID, month, array);
    getProductsBoughtByClientAndQuantity(sgv->filial[2], clientID, month, array);
    g_ptr_array_sort(array, comparaNodosQuery10);

    return array;
}

/*********************************QUERY 11**********************************************/

/**
 * @brief           Função que retorna a lista dos N produtos mais comprados
 * @param sgv       Apontador para a estrutura SGV
 * @param limit     Número de produtos a listar
 * @return          Estrutura com os N produtos mais comprados
 */
QUERRY11_AXU2 getTopSelledProducts(SGV sgv, int limit)
{
    QUERRY11_AXU2 resultado = malloc (sizeof (struct query11_aux2));

    GPtrArray* arrayTotais = getNTopSelledProducts(sgv->faturacao, limit);
    GPtrArray* clientesFilail1 = copiarPtrArrayQ11(arrayTotais);
    GPtrArray* clientesFilail2 = copiarPtrArrayQ11(arrayTotais);
    GPtrArray* clientesFilail3 = copiarPtrArrayQ11(arrayTotais);

    getClientsWhoBoughtAProductAndQuantity(sgv->filial[0], clientesFilail1);
    getClientsWhoBoughtAProductAndQuantity(sgv->filial[1], clientesFilail2);
    getClientsWhoBoughtAProductAndQuantity(sgv->filial[2], clientesFilail3);

    resultado->total     = arrayTotais;
    resultado->cliFilia1 = clientesFilail1;
    resultado->cliFilia2 = clientesFilail2;
    resultado->cliFilia3 = clientesFilail3;

    return resultado;
}


/*********************************QUERY 12**********************************************/

/**
 * @brief               Função que retorna a lista dos N produtos mais comprados por um dado cliente
 * @param sgv           Apontador para a estrutura SGV
 * @param clientID      Código do cliente a ser estudado
 * @param limit         Limite de numeros de produtos que queremos receber
 * @return              Array com os N produtos mais comprados por um dado cliente
 */
GPtrArray* getClientTopProfitProducts(SGV sgv, char* clientID, int limit)
{
    GPtrArray* listaProdutosEFaturado = g_ptr_array_new();
    getProductsBoughtByClientAndMoney(sgv->filial[0], clientID, listaProdutosEFaturado);
    getProductsBoughtByClientAndMoney(sgv->filial[1], clientID, listaProdutosEFaturado);
    getProductsBoughtByClientAndMoney(sgv->filial[2], clientID, listaProdutosEFaturado);
    g_ptr_array_sort(listaProdutosEFaturado, comparaNodosQuery12);

    return listaProdutosEFaturado;
}
