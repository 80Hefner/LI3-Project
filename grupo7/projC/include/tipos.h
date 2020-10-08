#ifndef TIPOS_H
#define TIPOS_H

typedef struct query3 {
    float faturado;
    int vendas;
}*QUERY3;

typedef struct query3Aux {
    QUERY3 filial1N;
    QUERY3 filial2N;
    QUERY3 filial3N;
    QUERY3 filial1P;
    QUERY3 filial2P;
    QUERY3 filial3P;
}*QUERY3AUX;

typedef struct buffer_q4 {
    int branchID;
    GPtrArray* productsID;
}*BUFFER_Q4;

typedef struct query5 {
    GTree** comparar;
    GTree** resultado;
}*QUERY5;

typedef struct query5_aux {
    char** array;
    int used;
}*QUERY5_AUX;

typedef struct query6 {
    int nrClientes;
    int nrProdutos;
}*QUERY6;

typedef struct query7 {
    int quantidadesProdutoFilial1[12];
    int quantidadesProdutoFilial2[12];
    int quantidadesProdutoFilial3[12];
    int quantidadesTotaisMes[12];
    int quantidadeTotal;
}*QUERY7;

typedef struct query8 {
    int mes1;
    int mes2;
    float faturado;
    int vendas;
}*QUERY8;

typedef struct query9 {
    char productID[7];
    GPtrArray* productBuyersN;
    GPtrArray* productBuyersP;
}*QUERY9;

typedef struct query10 {
    char productID[7];
    int quantidade;
}*QUERY10;

typedef struct query10_aux {
    GPtrArray* array;
    int month;
}*QUERY10_AUX;


typedef struct query11 {
    char productID[7];
    int clientes;
    int quantidade;
}*QUERY11;

typedef struct query11_aux {
    GPtrArray* array;
    int limit;
}*QUERY11_AUX;

typedef struct query11_aux2 {
    GPtrArray* total;
    GPtrArray* cliFilia1;
    GPtrArray* cliFilia2;
    GPtrArray* cliFilia3;
}*QUERRY11_AXU2;


typedef struct query12 {
    char productID[7];
    float faturado;
}*QUERY12;

#endif
