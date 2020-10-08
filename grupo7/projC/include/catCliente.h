#ifndef CATCLIENTE_H
#define CATCLIENTE_H

typedef struct clientes *CLIENTES;

int getSizeClientes (CLIENTES clientes, int index);
CLIENTES initClientes(char* clientsFilePath);
int insereCliente(CLIENTES clientes, char* codigo);
int searchCliente(CLIENTES clientes, char * codigo);
CLIENTES freeClientes(CLIENTES clientes);
int* getArrayClientes (CLIENTES clientes, char letra);

#endif
