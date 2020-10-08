/**
 * @file    filial.c
 * @brief   Ficheiro que implementa o módulo das filiais
 */

#include "headers.h"

typedef struct int_np INT_NP;
typedef struct float_np FLOAT_NP;
typedef struct nodoFilial *NODO_FILIAL;
typedef struct produtosCliente *PRODUTOS_CLIENTE;


static NODO_FILIAL initNodoFilial();
static PRODUTOS_CLIENTE initProdutosClientes();

static gint compareKeyNodosFilial(gconstpointer p1, gconstpointer p2, gpointer user_data);
static gint compareKeyProdutosCliente(gconstpointer p1, gconstpointer p2, gpointer user_data);

static void freeNodoFilial(gpointer nodoFilial);
static void freeKeyNodoFilial(gpointer key);

static gboolean Q5TraverseFunc(gpointer key, gpointer value, gpointer data);
static gboolean Q6TraverseFunc(gpointer key, gpointer value, gpointer data);
static gboolean Q7TraverseFunc(gpointer key, gpointer value, gpointer data);
static gboolean Q9TraverseFunc(gpointer key, gpointer value, gpointer data);
static gboolean Q10TraverseFunc(gpointer key, gpointer value, gpointer data);
static gboolean Q11TraverseFunc(gpointer key, gpointer value, gpointer data);
static gboolean Q11TraverseFunc2(gpointer key, gpointer value, gpointer data);
static gboolean Q12TraverseFunc(gpointer key, gpointer value, gpointer data);

static float calculaTotalFaturadoProduto(PRODUTOS_CLIENTE relacao);
static int calculaTotalQuantidadeProduto(PRODUTOS_CLIENTE relacao);
static int calculaTotalQuantidadeProdutoN(PRODUTOS_CLIENTE relacao);
static int calculaTotalQuantidadeProdutoP(PRODUTOS_CLIENTE relacao);


/**
 * @brief               Estrutura que guarda os dados das diferentes filiais
 * @param nodosFilial   AVL que guarda os dados da filial
 */
struct filial {
    GTree* nodosFilial;
};

/**
 * @brief                       Estrutura que guarda o registo dos produtos comprados por um cliente
 * @param produtosComprados     Registo dos produtos comprados pelo cliente
 */
struct nodoFilial { /* key = clientID */
    GTree* produtosComprados;
};

/**
 * @brief           Estrutura que guarda os valores totais de produtos comprados num dado mês
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
 * @brief                           Estrutura que guarda o registo das vendas de um produto, para um dado cliente
 * @param totalProdutosComprados    Total de produtos comprados por mês
 * @param dinheiroGasto             Total de dinheiro gasto por mês
 */
struct produtosCliente { /* key = productID */
    INT_NP   totalProdutosComprados[12];
    FLOAT_NP dinheiroGasto[12];
};


/*---------------------------------INITS-----------------------------------------*/

/**
 * @brief       Função que inicializa um nodo na AVL da filial
 * @return      Apontador para o nodo criado
 */
static NODO_FILIAL initNodoFilial()
{
    NODO_FILIAL novoNodo = malloc(sizeof(struct nodoFilial));

    novoNodo->produtosComprados = g_tree_new_full(compareKeyProdutosCliente, NULL, freeKeyNodoFilial, g_free);

    return novoNodo;
}

/**
 * @brief       Função que inicializa uma Struct da relação de um produto com um Cliente
 * @return      Apontador para o novo nodo
 */
static PRODUTOS_CLIENTE initProdutosClientes()
{
    int i;
    PRODUTOS_CLIENTE nodo = malloc(sizeof(struct produtosCliente));
    for (i=0; i<12; i++) {
        nodo->totalProdutosComprados[i].normal = 0;
        nodo->totalProdutosComprados[i].promocao = 0;
        nodo->dinheiroGasto[i].normal = 0;
        nodo->dinheiroGasto[i].promocao = 0;
    }

    return nodo;
}


/*---------------------------------COMPARES-----------------------------------------*/

/**
 * @brief               Função que compara dois nodos da AVL da filial
 * @param p1            Apontador para um primeiro nodo da AVL
 * @param p2            Apontador para um segundo nodo da AVL
 * @param user_data     User data passada à função
 * @return              Strcmp dos códigos de produto dos nodos
 */
static gint compareKeyNodosFilial(gconstpointer p1, gconstpointer p2, gpointer user_data)
{
    char* str1 = (char*) p1;
    char* str2 = (char*) p2;
    return (gint) strcmp(str1,str2);

}

/**
 * @brief               Função que compara dois nodos da AVL da filial
 * @param p2            Apontador para um primeiro nodo da AVL
 * @param p2            Apontador para um segundo nodo da AVL
 * @param user_data     User data passada à função
 * @return              Strcmp dos códigos de produto dos nodos
 */
static gint compareKeyProdutosCliente(gconstpointer p1, gconstpointer p2, gpointer user_data)
{
    char* str1 = (char*) p1;
    char* str2 = (char*) p2;
    return (gint) strcmp(str1,str2);

}


/*---------------------------------FREES-----------------------------------------*/

/**
 * @brief               Função que liberta a memória alocada para um nodo da AVL da filial
 * @param nodoFilial    Apontador para o nodo da AVL da filial a libertar
 */
static void freeNodoFilial (gpointer nodoFilial)
{
    NODO_FILIAL nodo = (NODO_FILIAL) nodoFilial;
    if (nodo->produtosComprados != NULL)
        g_tree_destroy(nodo->produtosComprados);
    free(nodo);
}

/**
* @brief           Função que liberta a memória alocada para uma key de um nodo da AVL da filial
* @param nodo      Key do nodo da AVL da filial
*/
static void freeKeyNodoFilial(gpointer key)
{
    char* string = (char*) key;
    free(string);
}


/*---------------------------------TRAVERSE FUNCS-----------------------------------------*/

/**
 * @brief           Função que verifica se o cliente associado a um dado nodo já realizou alguma compra e, se sim, o insere numa GTREE
 * @param key       Key para o nodo da AVL da filial
 * @param value     Apontador para o nodo da AVL da filial
 * @param data      Apontador para uma GTREE vão sendo inseridos os códigos de cliente que realizaram pelo menos uma compra
 * @return          FALSE em caso de sucesso, de forma a continuar a percorrer a AVL
 */
static gboolean Q5TraverseFunc (gpointer key, gpointer value, gpointer data)
{
    char* clientID = (char*) key;
    NODO_FILIAL nodoFilial = (NODO_FILIAL) value;
    GTree* clientes_filial = *((GTree **) data);

    if (g_tree_nnodes(nodoFilial->produtosComprados) != 0) {
        g_tree_insert(clientes_filial, mystrdup(clientID), mystrdup(clientID));
    }

    return FALSE;
}

/**
 * @brief           Função que verifica os clientes comuns a duas GTrees e os insere numa terceira
 * @param key       Key para o nodo da AVL de Strings
 * @param value     Apontador para o nodo da AVL de Strings
 * @param data      Apontador para uma estrutura QUERY5 que possui uma GTREE vão sendo inseridos os códigos de cliente comuns a duas GTrees em estudo
 * @return          FALSE em caso de sucesso, de forma a continuar a percorrer a AVL
 */
gboolean verSePresenteNoutraArvore (gpointer key, gpointer value, gpointer data)
{
    char* codCliente = (char*) value;
    QUERY5 query5 = *((QUERY5 *) data);
    GTree* comparar = *(query5->comparar);
    char* aux;

    aux = (char*) g_tree_lookup(comparar, (gconstpointer)codCliente);
    if (aux != NULL) {
      g_tree_insert(*(query5->resultado), mystrdup(codCliente), mystrdup(codCliente));
    }

    return FALSE;
}


/**
 * @brief           Função que verifica se o cliente associado a um dado nodo nunca comprou
 * @param key       Key para o nodo da AVL da filial
 * @param value     Apontador para o nodo da AVL da filial
 * @param data      Apontador para um GPtrArray que guarda os códigos de cliente que nunca compraram numa dada filial
 * @return          FALSE em caso de sucesso, de forma a continuar a percorrer a AVL
 */
static gboolean Q6TraverseFunc(gpointer key, gpointer value, gpointer data)
{
    char* clientID = (char*) key;
    NODO_FILIAL nodoFilial = (NODO_FILIAL) value;
    GPtrArray* clientes_filial = *((GPtrArray **) data);

    if (g_tree_nnodes(nodoFilial->produtosComprados) == 0) {
        g_ptr_array_add(clientes_filial, mystrdup(clientID));
    }

    return FALSE;
}

/**
 * @brief               Função que incrementa um array de inteiros com o número de vendas de um certo produto para um certo cliente
 * @param key           Key para o nodo da AVL dos produtos_cliente
 * @param value         Apontador para o nodo da AVL dos produtos_cliente
 * @param user_data     Apontador para um array de inteiros para guardar os valores
 * @return              FALSE em caso de sucesso, de forma a continuar a percorrer a AVL
 */
static gboolean Q7TraverseFunc(gpointer key, gpointer value, gpointer data)
{
    int i;
    PRODUTOS_CLIENTE nodo = (PRODUTOS_CLIENTE) value;
    int* quantidadesMes = *((int **) data);

    for (i = 0; i < 12; i++) {
        quantidadesMes[i] += nodo->totalProdutosComprados[i].normal;
        quantidadesMes[i] += nodo->totalProdutosComprados[i].promocao;
    }

    return FALSE;
}

/**
 * @brief           Função que verifica se o cliente associado a um dado nodo já comprou um dado produto e, se sim, o insere o codigoCliente num GPtrArray (diferenciando entre compra N e P) )
 * @param key       Key para o nodo da AVL da filial
 * @param value     Apontador para o nodo da AVL da filial
 * @param data      Apontador para uma estrutura QUERY9 onde vão sendo inseridos os códigos de cliente que realizaram compras de um dado produto (entre um GPtrArray de compras N e um de compras P)
 * @return          FALSE em caso de sucesso, de forma a continuar a percorrer a AVL
 */
static gboolean Q9TraverseFunc (gpointer key, gpointer value, gpointer data)
{
    char* clientID = (char*) key; /* key do nodo da filial */
    NODO_FILIAL nodoFilial = (NODO_FILIAL) value; /* nodo da filial */
    QUERY9 compradoresProduto = *((QUERY9 *) data); /* onde guardar os clientes */
    PRODUTOS_CLIENTE aux;

    aux = (PRODUTOS_CLIENTE) g_tree_lookup(nodoFilial->produtosComprados, compradoresProduto->productID);
    if (aux != NULL) {
        if(calculaTotalQuantidadeProdutoN(aux) != 0)
            g_ptr_array_add(compradoresProduto->productBuyersN, mystrdup(clientID));
        if(calculaTotalQuantidadeProdutoP(aux) != 0)
            g_ptr_array_add(compradoresProduto->productBuyersP, mystrdup(clientID));
    }

    return FALSE;
}

/**
 * @brief           Função que incrementa uma estrutura com o número de unidades vendidas de um dado produto a um dado cliente, num dado mês
 * @param key       Key para o nodo da AVL dos produtos_cliente
 * @param value     Apontador para o nodo da AVL dos produtos_cliente
 * @param data      Apontador para uma estrutura QUERY10_AUX
 * @return          FALSE em caso de sucesso, de forma a continuar a percorrer a AVL
 */
static gboolean Q10TraverseFunc(gpointer key, gpointer value, gpointer data)
{

    char* productID = (char*) key;
    PRODUTOS_CLIENTE nodo = (PRODUTOS_CLIENTE) value;
    QUERY10_AUX query10_aux = *((QUERY10_AUX*) data);
    GPtrArray* array = query10_aux->array;
    int month = query10_aux->month;
    guint index;
    QUERY10 query10;

    if (nodo->totalProdutosComprados[month-1].normal != 0 || nodo->totalProdutosComprados[month-1].promocao != 0) {
        if (g_ptr_array_find_with_equal_func(array, productID, query10EqualFunc, &index) == TRUE) {
            query10 = array->pdata[index];
            query10->quantidade += nodo->totalProdutosComprados[month-1].normal;
            query10->quantidade += nodo->totalProdutosComprados[month-1].promocao;
        }
        else {
            query10 = malloc(sizeof(struct query10));
            strcpy(query10->productID, productID);
            query10->quantidade = nodo->totalProdutosComprados[month-1].normal;
            query10->quantidade += nodo->totalProdutosComprados[month-1].promocao;
            g_ptr_array_add(array, query10);
        }
    }

    return FALSE;
}

/**
 * @brief           Função que verifica se o cliente associado a um dado nodo comprou um dado produto
 * @param key       Key para o nodo da AVL da filial
 * @param value     Apontador para o nodo da AVL da filial
 * @param data      Apontador para um GPtrArray que guarda os clientes que compraram um dado produto
 * @return          FALSE em caso de sucesso, de forma a continuar a percorrer a AVL
 */
static gboolean Q11TraverseFunc (gpointer key, gpointer value, gpointer data)
{
    NODO_FILIAL nodo = (NODO_FILIAL) value;

    g_tree_foreach(nodo->produtosComprados, Q11TraverseFunc2, data);

    return FALSE;
}

/**
 * @brief           Função que verifica se um dado produto se encontra numa dada GTree
 * @param key       Key para o nodo PRODUTOS_CLIENTE
 * @param value     Apontador para o nodo PRODUTOS_CLIENTE
 * @param data      Apontador para um GPtrArray que guarda os clientes que compraram um dado produto
 * @return          FALSE em caso de sucesso, de forma a continuar a percorrer a AVL
 */
static gboolean Q11TraverseFunc2 (gpointer key, gpointer value, gpointer data)
{
    char* productID = (char*) key;
    PRODUTOS_CLIENTE nodo = (PRODUTOS_CLIENTE) value;
    GPtrArray* array = *((GPtrArray**) data);
    QUERY11 query11;
    guint index;

    if (g_ptr_array_find_with_equal_func(array, productID, Q11GEqualFunc, &index) == TRUE) {
        query11 = array->pdata[index];
        query11->clientes++;
        query11->quantidade += calculaTotalQuantidadeProduto(nodo);
    }

    return FALSE;
}

/**
 * @brief           Função que incrementa uma estrutura com o total faturado com um dado produto a um dado cliente
 * @param key       Key para o nodo da AVL dos produtos_cliente
 * @param value     Apontador para o nodo da AVL dos produtos_cliente
 * @param data      Apontador para um GPtrArray
 * @return          FALSE em caso de sucesso, de forma a continuar a percorrer a AVL
 */
static gboolean Q12TraverseFunc(gpointer key, gpointer value, gpointer data)
{
    char* productID = (char*) key;
    PRODUTOS_CLIENTE nodo = (PRODUTOS_CLIENTE) value;
    GPtrArray* array = *((GPtrArray**) data);
    guint index;
    QUERY12 query12;

    if (g_ptr_array_find_with_equal_func(array, productID, query12EqualFunc, &index) == TRUE) {
        query12 = array->pdata[index];
        query12->faturado += calculaTotalFaturadoProduto(nodo);
    }
    else {
        query12 = malloc(sizeof(struct query12));
        strcpy(query12->productID, productID);
        query12->faturado = calculaTotalFaturadoProduto(nodo);
        g_ptr_array_add(array, query12);
    }

    return FALSE;
}


/*---------------------------------CALCULA TOTAIS-----------------------------------------*/

/**
 * @brief               Função que dado uma relação de produtos cliente, soma o total faturado em todo o ano (soma de todas as posições do faturado em cada mês)
 * @param relacao       Apontador para a struct que guarda a relação de um produto com um dado cliente
 * @return              Total faturado por um produto com um dado cliente
 */
static float calculaTotalFaturadoProduto (PRODUTOS_CLIENTE relacao)
{
    int i;
    float faturadoTotal = 0;
    for (i=0; i<12; i++) {
        faturadoTotal += relacao->dinheiroGasto[i].normal;
        faturadoTotal += relacao->dinheiroGasto[i].promocao;
    }
    return faturadoTotal;
}

/**
 * @brief               Função que dado uma relação de produtos cliente, soma o total da quantidade comprada em todo o ano (soma de todas as posições da quantidade comprada em cada mês)
 * @param relacao       Apontador para a struct que guarda a relação de um produto com um dado cliente
 * @return              Quantidade total vendida de um produto a um dado cliente
 */
static int calculaTotalQuantidadeProduto (PRODUTOS_CLIENTE relacao)
{
    int i;
    int quantidadeTotal = 0;
    for (i=0; i<12; i++) {
        quantidadeTotal += relacao->totalProdutosComprados[i].normal;
        quantidadeTotal += relacao->totalProdutosComprados[i].promocao;
    }
    return quantidadeTotal;
}

/**
 * @brief               Função que dado uma relação de produtos cliente, soma o total da quantidade comprada em todo o ano no modo normal(soma de todas as posições da quantidade comprada em cada mês)
 * @param relacao       Apontador para a struct que guarda a relação de um produto com um dado cliente
 * @return              Quantidade total vendida de um produto a um dado cliente, no modo normal
 */
static int calculaTotalQuantidadeProdutoN (PRODUTOS_CLIENTE relacao)
{
    int i;
    int quantidadeTotal = 0;
    for (i=0; i<12; i++) {
        quantidadeTotal += relacao->totalProdutosComprados[i].normal;
    }
    return quantidadeTotal;
}

/**
 * @brief               Função que dado uma relação de produtos cliente, soma o total da quantidade comprada em todo o ano no modo promoção(soma de todas as posições da quantidade comprada em cada mês)
 * @param relacao       Apontador para a struct que guarda a relação de um produto com um dado cliente
 * @return              Quantidade total vendida de um produto a um dado cliente, no modo promoção
 */
static int calculaTotalQuantidadeProdutoP (PRODUTOS_CLIENTE relacao)
{
    int i;
    int quantidadeTotal = 0;
    for (i=0; i<12; i++) {
        quantidadeTotal += relacao->totalProdutosComprados[i].promocao;
    }
    return quantidadeTotal;
}


/*---------------------------------API-----------------------------------------*/

/**
 * @brief               Função que inicializa a estrutura da filial
 * @param clientes      Apontador para a estrutura dos clientes
 * @return              Apontador para a estrutura da filial
 */
FILIAL initFilial(CLIENTES clientes)
{
    FILIAL filial = malloc(sizeof(struct filial));
    filial->nodosFilial = g_tree_new_full(compareKeyNodosFilial,NULL,freeKeyNodoFilial,freeNodoFilial);

    int i, j;
    int* arrayNumeros;
    int tamanhoArray;
    char letra;
    char codigo[6];
    NODO_FILIAL nodo_aux;
    char* key;

    for (i=0; i<26; i++) {
      letra = i + 'A';
      arrayNumeros = getArrayClientes(clientes,letra);
      tamanhoArray = getSizeClientes (clientes,i);

      for (j=0; j<tamanhoArray; j++) {
          sprintf(codigo,"%c%d", letra, arrayNumeros[j]);
          nodo_aux = (gpointer) initNodoFilial();
          key = (gpointer) mystrdup(codigo);
          g_tree_insert(filial->nodosFilial, key, nodo_aux);
      }

      free(arrayNumeros);
    }

    return filial;
}

/**
 * @brief            Função que liberta a memória alocada para a estrutura de uma filial
 * @param filial     Apontador para a estrutura de uma filial
 * @return           Retorna NULL, em caso de sucesso
 */
FILIAL freeFilial(FILIAL filial)
{
    g_tree_destroy(filial->nodosFilial);
    free(filial);

    return NULL;
}

/**
 * @brief               Função que insere uma venda na estrutura da faturação
 * @param filial        Apontador para a filial onde se deseja inserir a venda
 * @param productID     Código do produto associado à venda a ser inserida
 * @param clientID      Código do cliente associado à venda a ser inserida
 * @param modo          Modo associado à venda a ser inserida
 * @param preco         Preço associado à venda a ser inserida
 * @param quantidade    Quantidade associada à venda a ser inserida
 * @param mes           Mês associado à venda a ser inserida
 */
void insereVendaFilial (FILIAL filial, char* productID, char*clientID, char modo, float preco, int quantidade, int mes)
{
    NODO_FILIAL nodo = (NODO_FILIAL) g_tree_lookup(filial->nodosFilial, clientID);
    PRODUTOS_CLIENTE prods_cliente;

    prods_cliente = (PRODUTOS_CLIENTE) g_tree_lookup(nodo->produtosComprados, productID);

    if (prods_cliente != NULL) {
        if (modo == 'N') {
            prods_cliente->totalProdutosComprados[mes-1].normal += quantidade;
            prods_cliente->dinheiroGasto[mes-1].normal += ((float)quantidade) * preco;
        }
        else {
            prods_cliente->totalProdutosComprados[mes-1].promocao += quantidade;
            prods_cliente->dinheiroGasto[mes-1].promocao += ((float)quantidade) * preco;
        }
    }
    else {
        prods_cliente = initProdutosClientes();
        if (modo == 'N') {
            prods_cliente->totalProdutosComprados[mes-1].normal += quantidade;
            prods_cliente->dinheiroGasto[mes-1].normal += ((float)quantidade) * preco;
        }
        else {
            prods_cliente->totalProdutosComprados[mes-1].promocao += quantidade;
            prods_cliente->dinheiroGasto[mes-1].promocao += ((float)quantidade) * preco;
        }
        g_tree_insert(nodo->produtosComprados, mystrdup(productID), prods_cliente);
    }

}


/*--------------------------------------QUERIES STUFF----------------------------------------------*/

/* QUERY5 */
/**
 * @brief               Função que retorna um GPtrArray de codigos de Cliente que compraram numa dada filial
 * @param filial        Apontador para a filial onde procurar os clientes que realizaram compra
 * @return              GPtrArray de codigos de Cliente (char* -> Strings)
 */
GTree* getClientsWhoBoughtFromBranch (FILIAL filial)
{
    GTree* clientesCompraram = g_tree_new_full(compareAVLStrings, NULL, freeAVLStrings, freeAVLStrings);
    g_tree_foreach(filial->nodosFilial, Q5TraverseFunc, &clientesCompraram);

    return clientesCompraram;
}


/* QUERY6 */
/**
 * @brief               Função que retorna a quantidade de clientes que nunca compraram
 * @param filiais       Apontador para as estruturas das filiais
 * @return              Quantidade de clientes que nunca compraram
 */
int getClientsNeverBoughtCount(FILIAL* filiais)
{
    int i;
    int count = 0;
    guint index;
    GPtrArray* clientes_filial1 = g_ptr_array_new();
    g_tree_foreach(filiais[0]->nodosFilial, Q6TraverseFunc, &clientes_filial1);
    GPtrArray* clientes_filial2 = g_ptr_array_new();
    g_tree_foreach(filiais[1]->nodosFilial, Q6TraverseFunc, &clientes_filial2);
    GPtrArray* clientes_filial3 = g_ptr_array_new();
    g_tree_foreach(filiais[2]->nodosFilial, Q6TraverseFunc, &clientes_filial3);

    for (i = 0; i < clientes_filial1->len; i++) {
        if (g_ptr_array_find_with_equal_func(clientes_filial2, clientes_filial1->pdata[i], gconstpointerStrEquals, &index) == TRUE
         && g_ptr_array_find_with_equal_func(clientes_filial3, clientes_filial1->pdata[i], gconstpointerStrEquals, &index) == TRUE)
        {
            count++;
        }
    }

    myfreePointerArray(clientes_filial1);
    myfreePointerArray(clientes_filial2);
    myfreePointerArray(clientes_filial3);

    return count;
}


/* QUERY7 */
/**
 * @brief                  Função que incrementa num array de inteiros inicialmente com todas as posições a zero com as quantidades compradas de um dado cliente numa dada filial
 * @param filial           Apontador para a filial onde procurar os clientes que realizaram compra
 * @param clientID         Codigo do Cliente que pretendemos estudar
 * @param quantidadesMes   Apontador para um array de inteiros onde vão sendo acrescentadas as quantidades de produtos compradas (este array começa com todas as posições a 0)
 */
void getProductQuantityBoughtByClient (FILIAL filial, char* clientID, int *quantidadesMes)
{
    NODO_FILIAL nodo = (NODO_FILIAL) g_tree_lookup(filial->nodosFilial, clientID);

    g_tree_foreach(nodo->produtosComprados, Q7TraverseFunc, &quantidadesMes);

}


/* QUERY9 */
/**
 * @brief               Função que retorna uma estrutura QUERY9 onde se encontram codigos de Cliente que compraram um certo produto numa dada filial
 * @param filial        Apontador para a filial onde procurar os clientes que realizaram compra
 * @param productID     Código de produto a ser estudado
 * @return              QUERY9 onde possui codigos de Cliente que compraram o produto dado (char* -> Strings)
 */
QUERY9 getClientsWhoBoughtAProductFromBranch(FILIAL filial, char* productID)
{
    QUERY9 compradoresProduto = malloc (sizeof(struct query9));
    strcpy(compradoresProduto->productID, productID);
    compradoresProduto->productBuyersN = g_ptr_array_new();
    compradoresProduto->productBuyersP = g_ptr_array_new();

    g_tree_foreach(filial->nodosFilial, Q9TraverseFunc, &compradoresProduto);

    return compradoresProduto;
}


/* QUERY10 */
/**
 * @brief               Função que preenche um GPtrArray com os produtos associados a um cliente, bem como a quantidade comprada de cada um
 * @param filial        Apontador para a filial onde procurar os produtos
 * @param clientID      Código de cliente a estudar
 * @param month         Mês a estudar
 * @param array         Apontador para o GPtrArray a preencher
 */
void getProductsBoughtByClientAndQuantity(FILIAL filial, char* clientID, int month, GPtrArray* array)
{
    NODO_FILIAL nodoFilial = (NODO_FILIAL) g_tree_lookup(filial->nodosFilial, clientID);
    QUERY10_AUX query10_aux = malloc(sizeof(struct query10_aux));
    query10_aux->array = array;
    query10_aux->month = month;

    g_tree_foreach(nodoFilial->produtosComprados, Q10TraverseFunc, &query10_aux);

    array = query10_aux->array;
    free(query10_aux);
}


/* QUERY11 */
/**
 * @brief               Função que preenche um GPtrArray com as quantidades e o número de clientes de um dado produto
 * @param filial        Apontador para a filial onde procurar os produtos
 * @param array         Apontador para o GPtrArray a preencher
 */
void getClientsWhoBoughtAProductAndQuantity(FILIAL filial, GPtrArray* array)
{
    g_tree_foreach(filial->nodosFilial, Q11TraverseFunc, &array);
}


/* QUERY12 */
/**
 * @brief               Função que preenche um GPtrArray com os produtos associados a um cliente, bem como o faturado por cada produto num ano inteiro
 * @param filial        Apontador para a filial onde procurar os produtos
 * @param clientID      Código de cliente a estudar
 * @param array         Apontador para o GPtrArray a preencher
 */
void getProductsBoughtByClientAndMoney(FILIAL filial, char* clientID, GPtrArray* array)
{
    NODO_FILIAL nodoFilial = (NODO_FILIAL) g_tree_lookup(filial->nodosFilial, clientID);

    g_tree_foreach(nodoFilial->produtosComprados, Q12TraverseFunc, &array);
}
