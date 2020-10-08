#ifndef FILIAL_H
#define FILIAL_H

typedef struct filial *FILIAL;

FILIAL initFilial(CLIENTES clientes);
FILIAL freeFilial(FILIAL filial);
void insereVendaFilial(FILIAL filial, char* codProduto, char*clientID, char modo, float preco, int quantidade, int mes);

/* QUERY5 */
GTree* getClientsWhoBoughtFromBranch(FILIAL filial);
gboolean verSePresenteNoutraArvore (gpointer key, gpointer value, gpointer data);
/* QUERY6 */
int getClientsNeverBoughtCount(FILIAL* filiais);
/* QUERY7 */
void getProductQuantityBoughtByClient(FILIAL filial, char* clientID, int *quantidadesMes);
/* QUERY9 */
QUERY9 getClientsWhoBoughtAProductFromBranch(FILIAL filial, char* productID);
/* QUERY10 */
void getProductsBoughtByClientAndQuantity(FILIAL filial, char* clientID, int month, GPtrArray* array);
/* QUERY11 */
void getClientsWhoBoughtAProductAndQuantity(FILIAL filial, GPtrArray* array);
/* QUERY12 */
void getProductsBoughtByClientAndMoney(FILIAL filial, char* clientID, GPtrArray* array);

#endif
