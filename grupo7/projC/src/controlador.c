/**
 * @file    controlador.c
 * @brief   Ficheiro que contém as funções para que servem para a parte do controlador do MVC
 */

#include "headers.h"

extern int global_NUM_VENDAS_TOTAL;
extern int global_NUM_VENDAS_VAL;
extern int global_NUM_PRODUTOS_TOTAL;
extern int global_NUM_PRODUTOS_VAL;
extern int global_NUM_CLIENTES_TOTAL;
extern int global_NUM_CLIENTES_VAL;

static void esperaQueContinue();

/*********************************CONTROLADOR**********************************************/
/**
 * @brief                    Função que serve como controlador no modelo MVC
 * @param clientsFilePath    Localização do ficheiro onde estão contidos os clientes
 * @param productsFilePath   Localização do ficheiro onde estão contidos os produtos
 * @param salesFilePath      Localização do ficheiro onde estão contidas as vendas
 */
void controlador(char* clientsFilePath, char* productsFilePath, char* salesFilePath)
{
  clock_t start, end;
  int loaded = 0;
  SGV sgv = NULL;
  int escolha;
  char aux_string[64], aux_string2[64];
  hide_cursor();

  while(1){
      system("clear");
      escolha = menu();

      if (escolha == 1){ /* EXIT */
          if (loaded == 1){
              clock_t start = clock();
              destroySGV(sgv);
              clock_t end = clock();
              printf("\n   %f seg para dar free.\n", (double)(end-start) / CLOCKS_PER_SEC);
          }
          show_cursor();
          exit(0);
      }
      if (escolha == 0){ /* FREE */
          if (loaded == 0){
              printf("   Dados não carregados.\n\n");
              esperaQueContinue();
              continue;
          }
          clock_t start = clock();
          destroySGV(sgv);
          clock_t end = clock();
          printf("\n   %f seg para dar free.\n", (double)(end-start) / CLOCKS_PER_SEC);
          loaded = 0;
          printf("   Dados libertados.\n\n");
          esperaQueContinue();
      }
      if (escolha == -1){ /* LOAD */
          if (loaded == 1){
              printf("   Dados já carregados.\n\n");
              esperaQueContinue();
              continue;
          }
          imprimeDivisao();
          sgv = initSGV();
          sgv = loadSGVFromFiles(sgv, clientsFilePath, productsFilePath, salesFilePath);
          loaded = 1;
          printf("   Dados carregados.\n");
          esperaQueContinue();
      }
      if (escolha == 2){
          if (loaded != 1){
              printf("   Dados não carregados.\n\n");
              esperaQueContinue();
              continue;
          }
          imprimeDivisao();
          printf("   QUERY 2 -> Lista e número de Produtos começados por uma dada letra\n");
          show_cursor();

          do {
              printf("\tLetra que produtos começam: ");
              fgets(aux_string, 64, stdin);
              aux_string[0] = toupper(aux_string[0]);
          } while(!isalpha(aux_string[0]));
          hide_cursor();

          start = clock();
          GPtrArray* arrayProdutos = getProductsStartedByLetter(sgv, aux_string[0]);
          end = clock();

          imprimeQuery2(arrayProdutos);
          myfreePointerArray(arrayProdutos);

          printf("\n   %f seg para executar a Query 2\n\n", (double)(end-start) / CLOCKS_PER_SEC);
          esperaQueContinue();
      }
      if (escolha == 3){
          if (loaded != 1){
              printf("   Dados não carregados.\n\n");
              esperaQueContinue();
              continue;
          }

          imprimeDivisao();
          printf("   QUERY 3 -> Total de vendas e o total da faturação de um dado produto num dado mês\n");
          show_cursor();
          char productID[64];
          int month;

          do {
              printf("\tProduto que pretende estudar: ");
              fgets(productID, 64, stdin);
              strtok(productID, "\r\n");
              mytoupperstr(productID);
          } while(!searchProdutoSGV(sgv,productID));

          do {
              printf("\tMês que pretende estudar: ");
              fgets(aux_string, 64, stdin);
              if (aux_string[0] == '\n') continue;
              month = atoi(strtok(aux_string, "\r\n"));
          } while(month < 1 || month > 12);

          do {
              printf("\tModo de apresentação do resultado: 1->Total filiais; 2->Filial a filial: ");
              fgets(aux_string2, 64, stdin);
          } while(aux_string2[0] != '1' && aux_string2[0] != '2');
          hide_cursor();

          start = clock();
          QUERY3AUX query3 = getProductSalesAndProfit(sgv, productID, month);
          end = clock();

          imprimeQuery3(query3, productID, month, aux_string2[0]);
          free(query3->filial1N);
          free(query3->filial2N);
          free(query3->filial3N);
          free(query3->filial1P);
          free(query3->filial2P);
          free(query3->filial3P);
          free(query3);

          printf("\n   %f seg para executar a Query 3\n\n", (double)(end-start) / CLOCKS_PER_SEC);
          esperaQueContinue();
      }
      if (escolha == 4){
          if (loaded != 1){
              printf("   Dados não carregados.\n\n");
              esperaQueContinue();
              continue;
          }

          imprimeDivisao();
          printf("   QUERY 4 -> Lista ordenada e número dos produtos que nunca foram comprados\n");
          show_cursor();
          int branchID;

          do {
              printf("\tFilial que pretende estudar (0 caso pretenda os resultados globais): ");
              fgets(aux_string, 64, stdin);
              if (aux_string[0] == '\n') continue;
              branchID = atoi(strtok(aux_string, "\r\n"));
          } while(branchID < 0 || branchID > 3);
          hide_cursor();

          start = clock();
          BUFFER_Q4 buffer = getProductsNeverBought(sgv, branchID);
          end = clock();

          imprimeQuery4(buffer);
          myfreePointerArray(buffer->productsID);
          free(buffer);

          printf("\n   %f seg para executar a Query 4\n\n", (double)(end-start) / CLOCKS_PER_SEC);
          esperaQueContinue();
      }
      if (escolha == 5) {
          if (loaded != 1){
              printf("   Dados não carregados.\n\n");
              esperaQueContinue();
              continue;
          }

          imprimeDivisao();
          printf("   QUERY 5 -> Lista ordenada de clientes que realizaram compras em todas as filiais\n");

          start = clock();
          GTree* clientesTodasFiliais = getClientsOfAllBranches(sgv);
          end = clock();

          imprimeQuery5(clientesTodasFiliais);
          g_tree_destroy(clientesTodasFiliais);

          printf("\n   %f seg para executar a Query 5\n\n", (double)(end-start) / CLOCKS_PER_SEC);
          esperaQueContinue();
      }
      if (escolha == 6) {
          if (loaded != 1){
              printf("   Dados não carregados.\n\n");
              esperaQueContinue();
              continue;
          }

          imprimeDivisao();
          printf("   QUERY 6 -> Número de produtos nunca comprados e de clientes que nunca compraram\n");

          start = clock();
          QUERY6 query6 = getClientsAndProductsNeverBoughtCount(sgv);
          end = clock();

          imprimeQuery6(query6);
          free(query6);

          printf("\n   %f seg para executar a Query 6\n\n", (double)(end-start) / CLOCKS_PER_SEC);
          esperaQueContinue();
      }
      if (escolha == 7) {
          if (loaded != 1){
              printf("   Dados não carregados.\n\n");
              esperaQueContinue();
              continue;
          }

          imprimeDivisao();
          printf("   QUERY 7 -> Tabela com número de produtos comprados (filial a filial) e (mês a mês), dado um Cliente\n");
          show_cursor();
          char clientID[64];

          do {
              printf("\tCliente que pretende estudar: ");
              fgets(clientID, 64, stdin);
              strtok(clientID, "\r\n");
              mytoupperstr(clientID);
          } while(!searchClienteSGV(sgv,clientID));
          hide_cursor();

          start = clock();
          QUERY7 query7 = getProductsBoughtByClient(sgv, clientID);
          end = clock();

          imprimeQuery7(query7);
          free(query7);

          printf("\n   %f seg para executar a Query 7\n\n", (double)(end-start) / CLOCKS_PER_SEC);
          esperaQueContinue();
      }
      if (escolha == 8) {
          if (loaded != 1){
              printf("   Dados não carregados.\n\n");
              esperaQueContinue();
              continue;
          }

          imprimeDivisao();
          printf("   QUERY 8 -> Total faturado e número de vendas num dado intervalo de tempo, entre meses dados\n");
          show_cursor();
          int month1, month2;

          do {
              printf("\tEscolha o mês de começo do intervalo de tempo a estudar: ");
              fgets(aux_string, 64, stdin);
              if (aux_string[0] == '\n') continue;
              month1 = atoi(strtok(aux_string, "\r\n"));
          } while(month1 < 1 || month1 > 12);

          do {
              printf("\tEscolha o mês de término do intervalo de tempo a estudar: ");
              fgets(aux_string, 64, stdin);
              if (aux_string[0] == '\n') continue;
              month2 = atoi(strtok(aux_string, "\r\n"));
          } while(month2 < month1 || month2 > 12);
          hide_cursor();

          start = clock();
          QUERY8 query8 = getSalesAndProfit(sgv, month1, month2);
          end = clock();

          imprimeQuery8(query8, month1, month2);
          free(query8);

          printf("\n   %f seg para executar a Query 8\n\n", (double)(end-start) / CLOCKS_PER_SEC);
          esperaQueContinue();
      }
      if (escolha == 9) {
          if (loaded != 1){
              printf("   Dados não carregados.\n\n");
              esperaQueContinue();
              continue;
          }

          imprimeDivisao();
          printf("   QUERY 9 -> Lista dos códigos e número de Clientes que compraram um dado produto numa dada filial (N e P)\n");
          show_cursor();
          char productID[64];
          int branchID;

          do {
              printf("\tProduto que pretende estudar: ");
              fgets(productID, 64, stdin);
              strtok(productID, "\r\n");
              mytoupperstr(productID);
          } while(!searchProdutoSGV(sgv,productID));

          do {
              printf("\tFilial que pretende estudar: ");
              fgets(aux_string, 64, stdin);
              if (aux_string[0] == '\n') continue;
              branchID = atoi(strtok(aux_string, "\r\n"));
          } while(branchID < 1 || branchID > 3);
          hide_cursor();

          start = clock();
          QUERY9 productBuyers = getProductBuyers(sgv, productID, branchID);
          end = clock();

          imprimeQuery9(productBuyers, productID, branchID);
          myfreePointerArray(productBuyers->productBuyersN);
          myfreePointerArray(productBuyers->productBuyersP);
          free(productBuyers);

          printf("\n   %f seg para executar a Query 9\n\n", (double)(end-start) / CLOCKS_PER_SEC);
          esperaQueContinue();
      }
      if (escolha == 10) {
          if (loaded != 1){
              printf("   Dados não carregados.\n\n");
              esperaQueContinue();
              continue;
          }

          imprimeDivisao();
          printf("   QUERY 10 -> Lista de produtos comprados por um dado cliente num dado mês, ordenada decrescentemente pela quantidade\n");
          show_cursor();
          char clientID[64];
          int month;

          do {
              printf("\tCliente que pretende estudar: ");
              fgets(clientID, 64, stdin);
              strtok(clientID, "\r\n");
              mytoupperstr(clientID);
          } while(!searchClienteSGV(sgv,clientID));

          do {
              printf("\tMês que pretende estudar: ");
              fgets(aux_string, 64, stdin);
              if (aux_string[0] == '\n') continue;
              month = atoi(strtok(aux_string, "\r\n"));
          } while(month < 1 || month > 12);
          hide_cursor();

          start = clock();
          GPtrArray* array = getClientFavoriteProducts(sgv, clientID, month);
          end = clock();

          imprimeQuery10 (array, clientID, month);
          myfreePointerArray(array);

          printf("\n   %f seg para executar a Query 10\n\n", (double)(end-start) / CLOCKS_PER_SEC);
          esperaQueContinue();
      }
      if (escolha == 11) {
          if (loaded != 1){
              printf("   Dados não carregados.\n\n");
              esperaQueContinue();
              continue;
          }

          imprimeDivisao();
          printf("   QUERY 11 -> Lista N produtos mais vendidos em todo o ano, número de clientes e unidades vendidas por filial\n");
          show_cursor();
          int limit;

          do {
              printf("\tLimite de produtos que pretende estudar: ");
              fgets(aux_string, 64, stdin);
              if (aux_string[0] == '\n') continue;
              limit = atoi(strtok(aux_string, "\r\n"));
          } while(limit < 1 || limit > global_NUM_PRODUTOS_VAL);
          hide_cursor();

          start = clock();
          QUERRY11_AXU2 resultado = getTopSelledProducts(sgv, limit);
          end = clock();


          imprimeQuery11(resultado, limit);
          myfreePointerArray(resultado->total);
          myfreePointerArray(resultado->cliFilia1);
          myfreePointerArray(resultado->cliFilia2);
          myfreePointerArray(resultado->cliFilia3);
          free(resultado);

          printf("\n   %f seg para executar a Query 11\n\n", (double)(end-start) / CLOCKS_PER_SEC);
          esperaQueContinue();
      }
      if (escolha == 12) {
          if (loaded != 1){
              printf("   Dados não carregados.\n\n");
              esperaQueContinue();
              continue;
          }

          imprimeDivisao();
          printf("   QUERY 12 -> Dado um Cliente, recebe lista dos N produtos em que este mais gastou dinheiro durante o ano\n");
          show_cursor();
          int limit;
          char clientID[64];

          do {
              printf("\tCliente que pretende estudar: ");
              fgets(clientID, 64, stdin);
              strtok(clientID, "\r\n");
              mytoupperstr(clientID);
          } while(!searchClienteSGV(sgv,clientID));

          do {
              printf("\tLimite de produtos que pretende estudar: ");
              fgets(aux_string, 64, stdin);
              if (aux_string[0] == '\n') continue;
              limit = atoi(strtok(aux_string, "\r\n"));
          } while(limit < 1 || limit > global_NUM_PRODUTOS_VAL);
          hide_cursor();

          start = clock();
          GPtrArray* listaProdutosEFaturado = getClientTopProfitProducts(sgv, clientID, limit);
          end = clock();

          imprimeQuery12(listaProdutosEFaturado, limit, clientID);
          myfreePointerArray(listaProdutosEFaturado);

          printf("\n   %f seg para executar a Query 12\n\n", (double)(end-start) / CLOCKS_PER_SEC);
          esperaQueContinue();
      }
      if (escolha == 13) {
          if (loaded != 1){
              printf("Dados não carregados.\n\n");
              continue;
          }

          imprimeDivisao();
          printf("   QUERY 13 -> Resultados da leitura dos ficheiros da Query1 / load\n");

          imprimeQuery13 (clientsFilePath, productsFilePath, salesFilePath);
          esperaQueContinue();
      }
  }
}

/*********************************FUNÇÕES AUXILIARES**********************************************/
/**
 * @brief     Função que serve como forma de espera ativa até que o usuário pressionar alguma tecla
 */
static void esperaQueContinue() {
    system("stty raw");
    system("stty -echo");
    printf("   Pressione <ENTER> para continuar...   ");
    while(getchar() != '\r');
    system("stty cooked");
    system("stty echo");
}
