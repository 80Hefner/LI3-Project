/**
 * @file    catCliente.c
 * @brief   Ficheiro que implementa o catálogo dos clientes
 */

#include "headers.h"

extern int global_NUM_CLIENTES_TOTAL;
extern int global_NUM_CLIENTES_VAL;

/**
 * @param array     Array que guarda arrays contendo a parte inteira dos códigos associados a cada letra
 * @param size      Tamanho de cada um dos arrays
 */
struct clientes {
    int * array[26];
    int size[26];
};


/**
 * @brief               Função que retorna o número de clientes começados por uma determinada letra
 * @param clientes      Apontador para a estrutura dos clientes
 * @param index         Índice da letra a procurar
 * @return              Número de clientes começados por uma determinada letra
 */
int getSizeClientes (CLIENTES clientes, int index) {
  return clientes->size[index];
}

/**
 * @brief           Função que retorna um array com a parte numérica dos clientes associados a uma letra
 * @param clientes  Apontador para a estrutura dos clientes
 * @param letra     Letra a procurar
 * @return          Array com a parte numérica dos clientes associados a uma letra
 */
int* getArrayClientes (CLIENTES clientes, char letra) {
    int pos = letra - 'A';
    int n_bytes = clientes->size[pos] * sizeof(int); /* número de bytes a ser copiado */
    int *array = malloc(n_bytes);
    memcpy(array, clientes->array[pos], n_bytes);

    return array;
}


/**
 * @brief                   Função que inicializa a estrutura dos clientes e carrega a partir de um ficheiro
 * @param clientsFilePath   Path para a localização do ficheiro com os clientes
 * @return                  Apontador para a estrutura dos clientes
 */
CLIENTES initClientes(char* clientsFilePath)
{
    clock_t start = clock();
    int i;
    char codigo [10];
    int count_val = 0, count_tot = 0;
    CLIENTES clientes = calloc(1, sizeof(struct clientes));

    FILE *f = fopen(clientsFilePath, "r");
    printf("   A ler do ficheiro %s\n", clientsFilePath);

    while (fgets(codigo, 10, f)) {
        strtok (codigo, "\r\n");
        if (insereCliente(clientes, codigo)) count_val++;
        count_tot++;
    }

    for (i = 0; i < 26; i++)
        qsort(clientes->array[i], clientes->size[i], sizeof(int), myintcmp);

    fclose(f);

    printf("   Linhas lidas: %d\n", count_tot);
    printf("   Linhas validadas: %d\n", count_val);
    global_NUM_CLIENTES_TOTAL = count_tot;
    global_NUM_CLIENTES_VAL = count_val;

    clock_t end = clock();
    printf("   %f seg para guardar os clientes.\n\n", (double)(end-start) / CLOCKS_PER_SEC);

    return clientes;
}

/**
 * @brief           Função que insere um cliente na estrutura dos clientes, caso este seja válido
 * @param clientes  Apontador para a estrutura dos clientes
 * @param codigo    Código do cliente
 * @return          Retorna 1 caso o cliente seja válido; 0 caso contrário
 */
int insereCliente(CLIENTES clientes, char* codigo)
{
    int flag = 0;
    int pos = codigo[0] - 65;
    int aux_num = atoi(&codigo[1]);
    int aux_size;

    if (aux_num >= 1000 && aux_num <= 5000 &&
        pos >= 0 && pos <= 25)
    {
        clientes->size[pos]++;
        aux_size = clientes->size[pos];
        clientes->array[pos] = realloc(clientes->array[pos], aux_size * sizeof(int));
        clientes->array[pos][aux_size-1] = aux_num;
        flag = 1;
    }

    return flag;
}

/**
 * @brief           Função que procura um cliente na estrutura dos clientes, dado um código de cliente
 * @param clientes  Apontador para a estrutura dos clientes
 * @param codigo    Código de cliente a ser pesquisado
 * @return          Retorna 1 em caso de sucesso e 0 caso contrário
 */
int searchCliente (CLIENTES clientes, char * codigo)
{
    void* pointer = NULL;
    int pos = codigo[0] - 'A';
    int num_cliente = atoi(&codigo[1]);

    if (num_cliente >= 1000 && num_cliente <= 5000 &&
        pos >= 0 && pos <= 25)
    {
        pointer = bsearch(&num_cliente, clientes->array[pos], clientes->size[pos], sizeof(int), myintcmp);
    }

    return(pointer != NULL);
}


/**
 * @brief           Função que liberta a memória alocada para a estrutura dos clientes
 * @param clientes  Apontador para a estrutura dos clientes
 * @return          Retorna NULL, em caso de sucesso
 */
CLIENTES freeClientes (CLIENTES clientes)
{
    int i;

    for(i = 0; i < 26; i++)
        free(clientes->array[i]);
    free(clientes);

    return NULL;
}
