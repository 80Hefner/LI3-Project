#ifndef FATURACAO_H
#define FATURACAO_H

typedef struct faturacao *FATURACAO;

FATURACAO initFaturacao(PRODUTOS produtos);
void insereFaturacao(FATURACAO faturacao,
                     char* productID,
                     float preco,
                     int quantidade,
                     char modo,
                     int mes,
                     int filial);
FATURACAO freeFaturacao(FATURACAO faturacao);

/* QUERY3 */
QUERY3 getProductDataByMonthAndBranch(FATURACAO faturacao, char* productID, int mes, char modo, int filial);
/* QUERY4 */
BUFFER_Q4 getProductsNeverBoughtAndSize(FATURACAO faturacao, int branchID);
/* QUERY6 */
int getProductsNeverBoughtCount(FATURACAO faturacao);
/* QUERY8 */
QUERY8 getAllProductsData(FATURACAO faturacao, int mes1, int mes2);
/* QUERY11 */
GPtrArray* getNTopSelledProducts(FATURACAO faturacao, int limit);

#endif
