/**
 * @file    faturacao.c
 * @brief   Ficheiro que implementa o módulo da faturação
 */

#include "headers.h"

/*----------------------------------STRUCTS-------------------------------------*/

typedef struct int_np INT_NP;
typedef struct float_np FLOAT_NP;
typedef struct nodoFatura *NODO_FATURA;
typedef struct produtosFilial *PRODUTOS_FILIAL;


static NODO_FATURA initNodoFatura();

static gint compareKeyNodosFatura(gconstpointer p1, gconstpointer p2, gpointer user_data);

static void freeNodoFatura(gpointer nodo);
static void freeKeyNodoFatura(gpointer key);

static gboolean Q4TraverseFunc(gpointer key, gpointer value, gpointer data);
static gboolean Q6TraverseFunc(gpointer key, gpointer value, gpointer data);
static gboolean Q8TraverseFunc(gpointer key, gpointer value, gpointer data);
static gboolean Q11TraversFunc(gpointer key, gpointer value, gpointer data);

static int calculaTotalUnidadesN(PRODUTOS_FILIAL nodo);
static int calculaTotalUnidadesP(PRODUTOS_FILIAL nodo);
/*static float calculaTotalFaturadoN(PRODUTOS_FILIAL nodo);
static float calculaTotalFaturadoP(PRODUTOS_FILIAL nodo);*/
static int calculaTotalVendasN(PRODUTOS_FILIAL nodo);
static int calculaTotalVendasP(PRODUTOS_FILIAL nodo);

/**
 * @brief               Estrutura que guarda os dados da faturação
 * @param avlFatura     AVL que guarda os dados da faturação
 */
struct faturacao {
    GTree* avlFatura;
};

/**
 * @brief                   Estrutura que guarda o registo das vendas de um produto
 * @param produtosFilialN   Registo das vendas do produto no modo Normal
 * @param produtosFilialP   Registo das vendas do produto no modo Promoção
 */
struct nodoFatura { /* key = productID */
    PRODUTOS_FILIAL produtoFilial [3];
};

/**
 * @brief           Estrutura que guarda os valores totais de unidades vendidas ou vendas num dado mês
 * @param normal    Total de produtos comprados num dado mês no modo normal
 * @param promocao  Total de produtos comprados num dado mês no modo promoção
 */
struct int_np {
    int normal;
    int promocao;
};

/**
 * @brief           Estrutura que guarda os valores totais de faturação num dado mês
 * @param normal    Total de faturação num dado mês no modo normal
 * @param promocao  Total de faturação num dado mês no modo promoção
 */
struct float_np {
    float normal;
    float promocao;
};

/**
 * @brief                   Estrutura que guarda o registo das vendas de um produto, num dado modo e numa dada filial
 * @param totalUnidades     Unidades vendidas por cada mês
 * @param totalFaturado     Faturação total por cada mês
 * @param totalVendas       Quantidade de vendas por cada mês
 */
struct produtosFilial {
    INT_NP totalUnidades [12];
    FLOAT_NP totalFaturado [12];
    INT_NP totalVendas [12];
};

/*---------------------------------INIT-----------------------------------------*/

/**
 * @brief               Função que inicializa um nodo na AVL da faturação
 * @return              Apontador para o novo nodo
 */
static NODO_FATURA initNodoFatura()
{
    NODO_FATURA nodoFatura = malloc(sizeof(struct nodoFatura));

    nodoFatura->produtoFilial[0] = calloc(1, sizeof(struct produtosFilial));
    nodoFatura->produtoFilial[1] = calloc(1, sizeof(struct produtosFilial));
    nodoFatura->produtoFilial[2] = calloc(1, sizeof(struct produtosFilial));

    return nodoFatura;
}


/*---------------------------------COMPARE-----------------------------------------*/

/**
 * @brief               Função que compara duas keys de nodos da AVL da faturação
 * @param p1            Key do primeiro nodo da AVL a ser comparado
 * @param p2            Key do primeiro nodo da AVL a ser comparado
 * @param user_data     User_data passada à função
 * @return              Strcmp das keys dos nodos
 */
static gint compareKeyNodosFatura(gconstpointer p1, gconstpointer p2, gpointer user_data)
{
    char* str1 = (char*) p1;
    char* str2 = (char*) p2;
    return (gint) strcmp(str1,str2);
}


/*---------------------------------FREES-----------------------------------------*/

/**
 * @brief           Função que liberta a memória alocada para um nodo da AVL da faturação
 * @param nodo      Apontador para o nodo da AVL da faturação
 */
static void freeNodoFatura(gpointer nodo)
{
    NODO_FATURA nodoFatura = (NODO_FATURA) nodo;
    free(nodoFatura->produtoFilial[0]);
    free(nodoFatura->produtoFilial[1]);
    free(nodoFatura->produtoFilial[2]);
    free(nodoFatura);
}

/**
 * @brief           Função que liberta a memória alocada para um nodo da AVL da faturação
 * @param nodo      Key do nodo da AVL da faturação
 */
static void freeKeyNodoFatura(gpointer key)
{
    char* string = (char*) key;
    free(string);
}


/*---------------------------------TRAVERSE FUNC-----------------------------------------*/

/**
 * @brief           Função que verifica se o produto associado a um dado nodo nunca foi comprado
 * @param key       Key para o nodo da AVL da faturação
 * @param value     Apontador para o nodo da AVL da faturação
 * @param data      Apontador para uma estrutura BUFFER_Q4 para guardar os valores
 * @return          FALSE em caso de sucesso, de forma a continuar a percorrer a AVL
 */
static gboolean Q4TraverseFunc(gpointer key, gpointer value, gpointer data)
{
    NODO_FATURA nodoFatura = (NODO_FATURA) value;
    BUFFER_Q4 buffer = *((BUFFER_Q4*) data);
    int flag = 1;
    int i;
    char* productID = (char*) key;
    int branchID = buffer->branchID;

    if (branchID == 0) {
        for (i = 0; i < 3 && flag; i++) {
            if (calculaTotalVendasN(nodoFatura->produtoFilial[i]) != 0
             || calculaTotalVendasP(nodoFatura->produtoFilial[i]) != 0)
                flag = 0;
        }
    }
    else {
        if (calculaTotalVendasN(nodoFatura->produtoFilial[branchID-1]) != 0
         || calculaTotalVendasP(nodoFatura->produtoFilial[branchID-1]) != 0)
            flag = 0;
    }

    if (flag) {
        g_ptr_array_add(buffer->productsID, mystrdup(productID));
    }

    return FALSE;
}

/**
 * @brief           Função que verifica se o produto associado a um dado nodo nunca foi comprado
 * @param key       Key para o nodo da AVL da faturação
 * @param value     Apontador para o nodo da AVL da faturação
 * @param data      Apontador para um inteiro que acumula a contagem
 * @return          FALSE em caso de sucesso, de forma a continuar a percorrer a AVL
 */
static gboolean Q6TraverseFunc(gpointer key, gpointer value, gpointer data)
{
    NODO_FATURA nodoFatura = (NODO_FATURA) value;
    int* count = (int *) data;
    int flag = 1;
    int i;

    for (i = 0; i < 3 && flag; i++) {
        if (calculaTotalVendasN(nodoFatura->produtoFilial[i]) != 0
         || calculaTotalVendasP(nodoFatura->produtoFilial[i]) != 0)
            flag = 0;
    }

    if (flag) (*count)++;

    return FALSE;
}

/**
 * @brief           Função que incrementa uma estrutura com o total faturado e número de vendas num dado intervalo de tempo, por um dado produto
 * @param key       Key para o nodo da AVL da faturação
 * @param value     Apontador para o nodo da AVL da faturação
 * @param data      Apontador para uma estrutura QUERY8 para guardar os valores
 * @return          FALSE em caso de sucesso, de forma a continuar a percorrer a AVL
 */
static gboolean Q8TraverseFunc(gpointer key, gpointer value, gpointer data)
{
    QUERY8 buffer = *((QUERY8 *) data);
    NODO_FATURA nodo = (NODO_FATURA) value;
    int i, j;
    int mes1 = buffer->mes1;
    int mes2 = buffer->mes2;

    for (i = 0; i < 3; i++) {
        for (j = mes1-1; j < mes2; j++) {
            buffer->faturado += nodo->produtoFilial[i]->totalFaturado[j].normal;
            buffer->faturado += nodo->produtoFilial[i]->totalFaturado[j].promocao;
            buffer->vendas += nodo->produtoFilial[i]->totalVendas[j].normal;
            buffer->vendas += nodo->produtoFilial[i]->totalVendas[j].promocao;
        }
    }

    return FALSE;
}

/**
 * @brief           Função que insere o nodo num array, caso o seu parâmetro 'quantidade' seja maior que o do menor elemento do array
 * @param key       Key para o nodo da AVL da faturação
 * @param value     Apontador para o nodo da AVL da faturação
 * @param data      Apontador para uma estrutura QUERY11 para guardar os valores
 * @return          FALSE em caso de sucesso, de forma a continuar a percorrer a AVL
 */
static gboolean Q11TraversFunc(gpointer key, gpointer value, gpointer data)
{
    int filial;
    char* productID = (char*) key;
    NODO_FATURA nodoFatura = (NODO_FATURA) value;
    QUERY11_AUX query11_aux = (*(QUERY11_AUX*) data);
    GPtrArray* array = query11_aux->array;
    int limit = query11_aux->limit;
    QUERY11 query11;
    int nodoTotalUnidades = 0;

    for (filial = 0; filial < 3; filial++) {
        nodoTotalUnidades += calculaTotalUnidadesN(nodoFatura->produtoFilial[filial]);
        nodoTotalUnidades += calculaTotalUnidadesP(nodoFatura->produtoFilial[filial]);
    }

    query11 = (QUERY11) array->pdata[limit-1];

    if (array->len < limit) {
        QUERY11 new_query11 = malloc(sizeof(struct query11));
        strcpy(new_query11->productID, productID);
        new_query11->quantidade = nodoTotalUnidades;
        g_ptr_array_add(array, (gpointer) new_query11);
        g_ptr_array_sort(array, comparaNodosQuery11);
    }
    else if (query11->quantidade < nodoTotalUnidades) {
        QUERY11 new_query11 = malloc(sizeof(struct query11));
        strcpy(new_query11->productID, productID);
        new_query11->quantidade = nodoTotalUnidades;
        free(query11);
        g_ptr_array_remove_index_fast(array, limit-1);
        g_ptr_array_insert(array, limit-1, (gpointer) new_query11);
        g_ptr_array_sort(array, comparaNodosQuery11);
    }

    return FALSE;
}


/*-----------------------------------API--------------------------------------------*/

/**
 * @brief               Função que inicializa a estrutura da faturação
 * @param produtos      Apontador para a estrutura dos produtos
 * @return              Apontador para a estrutura da faturação
 */
FATURACAO initFaturacao(PRODUTOS produtos)
{
    FATURACAO faturacao = malloc(sizeof(struct faturacao));
    faturacao->avlFatura = g_tree_new_full(compareKeyNodosFatura, NULL, freeKeyNodoFatura, freeNodoFatura);

    int i, j, k;
    int* array;
    int size;
    char letter1, letter2;
    char codigo [7];
    NODO_FATURA nodo_aux;
    char* key;

    for (i = 0; i < 26; i++) {
        letter1 = i + 'A';
        for (j = 0; j < 26; j++) {
            letter2 = j + 'A';
            array = getArrayProdutos(produtos, letter1, letter2);
            size = getSizeProdutos(produtos, letter1, letter2);

            for (k = 0; k < size; k++) {
                sprintf(codigo, "%c%c%d", letter1, letter2, array[k]);
                nodo_aux = (gpointer) initNodoFatura();
                key = (gpointer) mystrdup(codigo);
                g_tree_insert(faturacao->avlFatura, key, nodo_aux);
            }
            free(array);
        }
    }

    return faturacao;
}

/**
 * @brief               Função que insere uma venda na estrutura da faturação
 * @param productID     Código do produto associado à venda a ser inserida
 * @param preco         Preço associado à venda a ser inserida
 * @param quantidade    Quantidade associada à venda a ser inserida
 * @param modo          Modo associado à venda a ser inserida
 * @param mes           Mês associado à venda a ser inserida
 * @param filial        Filial associada à venda a ser inserida
 */
void insereFaturacao(FATURACAO faturacao, char* productID, float preco, int quantidade, char modo, int mes, int filial)
{
    NODO_FATURA nodoFatura = (NODO_FATURA) g_tree_lookup(faturacao->avlFatura, productID);
    PRODUTOS_FILIAL produtos_filial = nodoFatura->produtoFilial[filial-1];

    if (modo == 'N') {
        produtos_filial->totalUnidades[mes-1].normal += quantidade;
        produtos_filial->totalFaturado[mes-1].normal += preco * quantidade;
        produtos_filial->totalVendas[mes-1].normal += 1;
    }
    else {
        produtos_filial->totalUnidades[mes-1].promocao += quantidade;
        produtos_filial->totalFaturado[mes-1].promocao += preco * quantidade;
        produtos_filial->totalVendas[mes-1].promocao += 1;
    }
}

/**
 * @brief               Função que liberta a memória alocada para a estrutura da faturação
 * @param faturacao     Apontador para a estrutura da faturação
 * @return              Retorna NULL, em caso de sucesso
 */
FATURACAO freeFaturacao(FATURACAO faturacao)
{
    g_tree_destroy(faturacao->avlFatura);
    free(faturacao);

    return NULL;
}


/*--------------------------------------QUERIES STUFF----------------------------------------------*/

/* QUERY3 */
/**
 * @brief               Função que retorna uma estrutura com os dados de um produto, dado um modo e uma filial
 * @param faturacao     Apontador para a estrutura da faturação
 * @param productID     Código do produto associado ao nodo
 * @param mes           Mês a ser procurado
 * @param modo          Modo a ser procurado
 * @param filial        Filial a ser procurada
 * @return              Estrutura com os dados de um produto
 */
QUERY3 getProductDataByMonthAndBranch(FATURACAO faturacao, char* productID, int mes, char modo, int filial)
{
    NODO_FATURA nodoFatura = (NODO_FATURA) g_tree_lookup(faturacao->avlFatura, productID);
    PRODUTOS_FILIAL produtos_filial =nodoFatura->produtoFilial[filial-1];
    QUERY3 query3 = malloc(sizeof(struct query3));

    if (modo == 'N') {
        query3->faturado = produtos_filial->totalFaturado[mes-1].normal;
        query3->vendas = produtos_filial->totalVendas[mes-1].normal;
    }
    else {
        query3->faturado = produtos_filial->totalFaturado[mes-1].promocao;
        query3->vendas = produtos_filial->totalVendas[mes-1].promocao;
    }

    return query3;
}

/* QUERY4 */
/**
 * @brief               Função que retorna uma estrutura com os códigos de produto nunca comprados numa dada filial
 * @param faturacao     Apontador para a estrutura da faturação
 * @param bracnhID      Filial a procurar
 * @return              Estrutra com os códigos de produto nunca comprados
 */
BUFFER_Q4 getProductsNeverBoughtAndSize(FATURACAO faturacao, int branchID)
{
    BUFFER_Q4 buffer = malloc(sizeof(struct buffer_q4));
    buffer->branchID = branchID;
    buffer->productsID = g_ptr_array_new();

    g_tree_foreach(faturacao->avlFatura, Q4TraverseFunc, &buffer);

    return buffer;
}

/* QUERY6 */
/**
 * @brief               Função que retorna a quantidade de produtos que nunca foram comprados
 * @param faturacao     Apontador para a estrutura da faturação
 * @return              Quantidade de produtos que nunca foram comprados
 */
int getProductsNeverBoughtCount(FATURACAO faturacao)
{
    int count = 0;

    g_tree_foreach(faturacao->avlFatura, Q6TraverseFunc, &count);

    return count;
}

/* QUERY8 */
/**
 * @brief               Função que retorna uma estrutura com o total faturado e número de vendas num dado intervalo de tempo
 * @param faturacao     Apontador para a estrutura da faturação
 * @param mes1          Mês inicial
 * @param mes2          Mês final
 * @return              Estrutura com o total faturado e número de vendas num dado intervalo de tempo
 */
QUERY8 getAllProductsData(FATURACAO faturacao, int mes1, int mes2)
{
    QUERY8 buffer = malloc(sizeof(struct query8));
    buffer->mes1 = mes1;
    buffer->mes2 = mes2;
    buffer->faturado = 0;
    buffer->vendas = 0;

    g_tree_foreach(faturacao->avlFatura, Q8TraverseFunc, &buffer);

    return buffer;
}

/* QUERY11 */
/**
 * @brief               Função que retorna um array de nodos QUERY11, ordenados pelo parâmetro 'quantidade', por ordem decrescente
 * @param faturacao     Apontador para a estrutura da faturação
 * @param limit         Número de produtos máximo
 * @return              Array de nodos QUERY11
 */
GPtrArray* getNTopSelledProducts(FATURACAO faturacao, int limit)
{
    GPtrArray* array = g_ptr_array_sized_new((guint) limit);
    QUERY11_AUX query11_aux = malloc(sizeof(struct query11_aux));
    query11_aux->array = array;
    query11_aux->limit = limit;

    g_tree_foreach(faturacao->avlFatura, Q11TraversFunc, &query11_aux);
    free(query11_aux);

    return array;
}


/*---------------------------------CALCULA TOTAIS-----------------------------------------*/

/**
 * @brief       Função que calcula o total de unidades vendidas de um produto numa dada filial no modo normal
 * @param nodo  Apontador para a estrutura que guarda os registos das vendas de um produto
 */
static int calculaTotalUnidadesN(PRODUTOS_FILIAL nodo)
{
    int i;
    int totalUnidades = 0;
    for (i = 0; i < 12; i++) {
        totalUnidades += nodo->totalUnidades[i].normal;
    }
    return totalUnidades;
}

/**
 * @brief       Função que calcula o total de unidades vendidas de um produto numa dada filial no modo promoção
 * @param nodo  Apontador para a estrutura que guarda os registos das vendas de um produto
 */
static int calculaTotalUnidadesP(PRODUTOS_FILIAL nodo)
{
    int i;
    int totalUnidades = 0;
    for (i = 0; i < 12; i++) {
        totalUnidades += nodo->totalUnidades[i].promocao;
    }
    return totalUnidades;
}

/**
 * @brief       Função que calcula o total faturado de um produto numa dada filial no modo normal
 * @param nodo  Apontador para a estrutura que guarda os registos das vendas de um produto
 */
/*static float calculaTotalFaturadoN(PRODUTOS_FILIAL nodo)
{
    int i;
    float totalFaturado = 0;
    for (i = 0; i < 12; i++) {
        totalFaturado += nodo->totalFaturado[i].normal;
    }
    return totalFaturado;
}*/

/**
 * @brief       Função que calcula o total faturado de um produto numa dada filial no modo promoção
 * @param nodo  Apontador para a estrutura que guarda os registos das vendas de um produto
 */
/*static float calculaTotalFaturadoP(PRODUTOS_FILIAL nodo)
{
    int i;
    float totalFaturado = 0;
    for (i = 0; i < 12; i++) {
        totalFaturado += nodo->totalFaturado[i].promocao;
    }
    return totalFaturado;
}*/

/**
 * @brief       Função que calcula o total de vendas de um produto numa dada filial no modo normal
 * @param nodo  Apontador para a estrutura que guarda os registos das vendas de um produto
 */
static int calculaTotalVendasN(PRODUTOS_FILIAL nodo)
{
    int i;
    int totalVendas = 0;
    for (i = 0; i < 12; i++) {
        totalVendas += nodo->totalVendas[i].normal;
    }
    return totalVendas;
}

/**
 * @brief       Função que calcula o total de vendas de um produto numa dada filial no modo promoção
 * @param nodo  Apontador para a estrutura que guarda os registos das vendas de um produto
 */
static int calculaTotalVendasP(PRODUTOS_FILIAL nodo)
{
    int i;
    int totalVendas = 0;
    for (i = 0; i < 12; i++) {
        totalVendas += nodo->totalVendas[i].promocao;
    }
    return totalVendas;
}
