/**
 * @file    catProduto.c
 * @brief   Ficheiro que implementa o catálogo dos produtos
 */

#include "headers.h"

extern int global_NUM_PRODUTOS_TOTAL;
extern int global_NUM_PRODUTOS_VAL;

/**
 * @param array     Matrix que guarda arrays contendo a parte inteira dos códigos associados a cada par de letras
 * @param size      Tamanho de cada um dos arrays
 */
struct produtos {
    int * matrix[26][26];
    int size[26][26];
};


/**
 * @brief               Função que retorna o número de produtos começados por um determinado par de letras
 * @param produtos      Apontador para a estrutura dos produtos
 * @param letra1        Primeira letra do par
 * @param letra2        Segunda letra do par
 * @return              Número de produtos na estrutura dos produtos
 */
int getSizeProdutos(PRODUTOS produtos, char letra1, char letra2)
{
    return produtos->size[letra1-'A'][letra2-'A'];
}

/**
 * @brief           Função que retorna um array com a parte numérica dos produtos associados a um par de letras
 * @param produtos  Apontador para a estrutura dos produtos
 * @param letra1    Primeira letra do par
 * @param letra2    Segunda letra do par
 * @return          Array com a parte numérica dos produtos associados a um par de letras
 */
int* getArrayProdutos(PRODUTOS produtos, char letra1, char letra2)
{
    int pos1 = letra1 - 'A';
    int pos2 = letra2 - 'A';
    int n_bytes = produtos->size[pos1][pos2] * sizeof(int); /* número de bytes a ser copiado */
    int *array = malloc(n_bytes);
    memcpy(array, produtos->matrix[pos1][pos2], n_bytes);

    return array;
}

/**
 * @brief                   Função que inicializa a estrutura dos produtos e carrega a partir de um ficheiro
 * @param productsFilePath  Path para a localização do ficheiro com os produtos
 * @return                  Apontador para a estrutura dos produtos
 */
PRODUTOS initProdutos(char* productsFilePath)
{
    clock_t start = clock();
    int i, j;
    char codigo [10];
    int count_val = 0, count_tot = 0;
    PRODUTOS produtos = calloc(1, sizeof(struct produtos));

    FILE *f = fopen(productsFilePath, "r");
    printf("   A ler do ficheiro %s\n", productsFilePath);

    while (fgets(codigo, 10, f)) {
        strtok (codigo, "\r\n");
        if (insereProduto(produtos, codigo)) count_val++;
        count_tot++;
    }

    for (i = 0; i < 26; i++)
        for (j = 0; j < 26; j++)
            qsort(produtos->matrix[i][j], produtos->size[i][j], sizeof(int), myintcmp);

    fclose(f);

    printf("   Linhas lidas: %d\n", count_tot);
    printf("   Linhas validadas: %d\n", count_val);
    global_NUM_PRODUTOS_TOTAL = count_tot;
    global_NUM_PRODUTOS_VAL = count_val;

    clock_t end = clock();
    printf("   %f seg para guardar os produtos.\n\n", (double)(end-start) / CLOCKS_PER_SEC);

    return produtos;
}

/**
 * @brief           Função que insere um produto na estrutura dos produtos, caso este seja válido
 * @param produtos  Apontador para a estrutura dos produtos
 * @param codigo    Código do produto
 * @return          Retorna 1 caso o produto seja válido; 0 caso contrário
 */
int insereProduto(PRODUTOS produtos, char* codigo)
{
    int flag = 0;
    int pos1 = codigo[0] - 'A';
    int pos2 = codigo[1] - 'A';
    int aux_num = atoi(&codigo[2]);
    int aux_size;

    if (aux_num >= 1000 && aux_num <= 9999 &&
        pos1 >= 0 && pos1 <= 25 &&
        pos2 >= 0 && pos2 <= 25)
    {
        produtos->size[pos1][pos2]++;
        aux_size = produtos->size[pos1][pos2];
        produtos->matrix[pos1][pos2] = realloc(produtos->matrix[pos1][pos2],
                                               aux_size * sizeof(int));
        produtos->matrix[pos1][pos2][aux_size-1] = aux_num;
        flag = 1;
    }

    return flag;
}

/**
 * @brief           Função que procura um produto na estrutura dos produtos, dado um código de produto
 * @param produtos  Apontador para a estrutura dos produtos
 * @param codigo    Código de produto a ser pesquisado
 * @return          Retorna 1 em caso de sucesso e 0 caso contrário
 */
int searchProduto (PRODUTOS produtos, char * codigo)
{
    void* pointer = NULL;
    int pos1 = codigo[0] - 'A';
    int pos2 = codigo[1] - 'A';
    int num_produto = atoi(&codigo[2]);

    if (num_produto >= 1000 && num_produto <= 9999 &&
        pos1 >= 0 && pos1 <= 25 &&
        pos2 >= 0 && pos2 <= 25)
    {
        pointer = bsearch(&num_produto, produtos->matrix[pos1][pos2], produtos->size[pos1][pos2], sizeof(int), myintcmp);
    }

    return(pointer != NULL);
}

/**
 * @brief           Função que liberta a memória alocada para a estrutura dos produtos
 * @param produtos  Apontador para a estrutura dos produtos
 * @return          Retorna NULL, em caso de sucesso
 */
PRODUTOS freeProdutos (PRODUTOS produtos)
{
    int i,j;

    for (i = 0; i < 26; i++)
        for (j = 0; j < 26; j++)
            free(produtos->matrix[i][j]);
    free(produtos);

    return NULL;
}
