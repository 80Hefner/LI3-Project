#ifndef INTERFACE_H
#define INTERFACE_H

typedef struct sgv *SGV;

int searchProdutoSGV(SGV sgv, char* productID);
int searchClienteSGV(SGV sgv, char* clientID);

SGV initSGV();
void destroySGV(SGV sgv);
SGV loadSGVFromFiles(SGV sgv,
     char* clientsFilePath,
     char* productsFilePath,
     char* salesFilePath
     );
GPtrArray* getProductsStartedByLetter(SGV sgv, char letter);
QUERY3AUX getProductSalesAndProfit(SGV sgv, char* productID, int month);
BUFFER_Q4 getProductsNeverBought(SGV sgv, int branchID);
GTree* getClientsOfAllBranches(SGV sgv);
QUERY6 getClientsAndProductsNeverBoughtCount(SGV sgv);
QUERY7 getProductsBoughtByClient(SGV sgv, char* clientID);
QUERY8 getSalesAndProfit(SGV sgv, int minMonth, int maxMonth);
QUERY9 getProductBuyers(SGV sgv, char* productID, int branch);
GPtrArray* getClientFavoriteProducts(SGV sgv, char* clientID, int month);
QUERRY11_AXU2 getTopSelledProducts(SGV sgv, int limit);
GPtrArray* getClientTopProfitProducts(SGV sgv, char* clientID, int limit);

#endif
