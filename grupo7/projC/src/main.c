#include "headers.h"

int global_NUM_VENDAS_TOTAL;
int global_NUM_VENDAS_VAL;
int global_NUM_PRODUTOS_TOTAL;
int global_NUM_PRODUTOS_VAL;
int global_NUM_CLIENTES_TOTAL;
int global_NUM_CLIENTES_VAL;

int main () {

    char dataFolderPath[128];
    char clientsFilePath[128];
    char productsFilePath[128];
    char salesFilePath[128];

    strcpy(dataFolderPath, "../Dados/");
    strcpy(clientsFilePath, dataFolderPath);
    strcat(clientsFilePath, "Clientes.txt");
    strcpy(productsFilePath, dataFolderPath);
    strcat(productsFilePath, "Produtos.txt");
    strcpy(salesFilePath, dataFolderPath);
    strcat(salesFilePath, "Vendas_1M.txt");

    controlador(clientsFilePath, productsFilePath, salesFilePath);

    return 0;
}
