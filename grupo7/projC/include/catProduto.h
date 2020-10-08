#ifndef CATPRODUTO_H
#define CATPRODUTO_H

typedef struct produtos *PRODUTOS;

int getSizeProdutos(PRODUTOS produtos, char letra1, char letra2);
int* getArrayProdutos(PRODUTOS produtos, char letra1, char letra2);
PRODUTOS initProdutos(char* productsFilePath);
int insereProduto(PRODUTOS produtos, char* codigo);
int searchProduto(PRODUTOS produtos, char * codigo);
PRODUTOS freeProdutos(PRODUTOS produtos);

#endif
