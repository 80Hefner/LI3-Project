/**
 * @file    visual.c
 * @brief   Ficheiro que contém as funções para que servem para a parte Visual do MVC
 */

#include "headers.h"

extern int global_NUM_VENDAS_TOTAL;
extern int global_NUM_VENDAS_VAL;
extern int global_NUM_PRODUTOS_TOTAL;
extern int global_NUM_PRODUTOS_VAL;
extern int global_NUM_CLIENTES_TOTAL;
extern int global_NUM_CLIENTES_VAL;

static int qDig(int n);
static void imprimeTabelaQuery2(char** array_str, int start_ind);
static void imprimeTabelaQuery4(char** array_str, int start_ind);
static void imprimeTabelaQuery5(char** array_str, int start_ind);
static gboolean Q5nodoAVLtoStr (gpointer key, gpointer value, gpointer data);
static void imprimeQuery11Aux (QUERRY11_AXU2 resultado, int current_page, int current_mode, int prod_per_page, int nr_pages, int limit);
static void imprimeQuery12Aux (GPtrArray* resultado, int current_page, int prod_per_page, int nr_pages, int limit, char* clientID);

const char* _meses[] = {"JANEIRO","FEVEREIRO","MARÇO","ABRIL","MAIO","JUNHO","JULHO","AGOSTO","SETEMBRO","OUTUBRO","NOVEMBRO","DEZEMBRO"};
const char print_opcao[] = " - QUAL OPÇÃO PRETENDE ESCOLHER: (Navegue no 'w' e no 's' e confirme no <ENTER>)                          ";
const char print_load[] = "Carregar a estrutura SGV (Catálogos, Filial e Faturação) a partir de ficheiros             ";
const char print_free[] = "Libertar a memória alocada para a estrutura SGV                                            ";
const char print_exit[] = "Sair do programa                                                                           ";
const char print_c2[] = "Lista e número de Produtos começados por uma dada letra                                     ";
const char print_c3[] = "Total de vendas e o total da faturação de um dado produto num dado mês                      ";
const char print_c4[] = "Lista ordenada e número de produtos que nunca foram comprados                               ";
const char print_c5[] = "Lista ordenada de clientes que realizaram compras em todas as filiais                       ";
const char print_c6[] = "Número de produtos nunca comprados e de clientes que nunca compraram                        ";
const char print_c7[] = "Tabela com número de produtos comprados (filial a filial) e (mês a mês), dado um Cliente    ";
const char print_c8[] = "Total faturado e número de vendas entre dois dados meses                                    ";
const char print_c9[] = "Lista dos códigos e número de Clientes que compraram um produto numa dada filial (N e P)    ";
const char print_c10[] = "Produtos comprados por um cliente num mês, ordenando descrescentemente pela quantidade      ";
const char print_c11[] = "Lista dos N produtos mais vendidos, nr Clientes e unidades vendidas por filial              ";
const char print_c12[] = "Dado um Cliente, lista dos N produtos em que este mais gastou dinheiro durante o ano        ";
const char print_c13[] = "Resultados da leitura dos ficheiros da Query1                                               ";
int _menu_posicaoSeta = -1;

/**
 * @brief    Função que dá set da cor da fonte dos próximos prints a vermelhor
 */
void color_red ()
{
  printf("\033[1;31m");
}

/**
* @brief    Função que dá set da fonte dos próximos prints a vermelhor a negrito
*/
void negrito ()
{
  printf("\033[1m");
}

/**
* @brief    Função que dá reset da boldness e tamanho da fonte dos próximos prints
*/
void reseta ()
{
  printf("\033[0m");
}

/**
* @brief    Função que esconde o cursor
*/
void hide_cursor ()
{
  printf("\x1B[?25l");
}

/**
* @brief    Função que mostra o cursor
*/
void show_cursor ()
{
  printf("\x1B[?25h");
}


/**
 * @brief       Função que calcula o número de digitos precisos para representar um dado inteiro
 * @param n     Inteiro do qual se pretende receber o número de digitos
 * @return      Número de dígitos do inteiro recebido
 */
static int qDig(int n) {
  int r = 0;
  if (n == 0)(r = 1);
  else {
    while (n != 0) {
      n = n / 10;
      r++;
    };
  };
  return r;
}

/**
 * @brief       Função que corre o menu do programa
 * @return      Opção do menu escolhida
 */
int menu()
{
    char teclaCarregada = '\0';

    while (teclaCarregada != '\r')
    {
        system("clear");
        imprimeLogo();
        imprimeMenuOpcoes();

        system("stty raw");
        system("stty -echo");
        scanf("%c", &teclaCarregada);
        system("stty cooked");
        system("stty echo");
        novaPosicaoSeta(teclaCarregada);
    }

    return _menu_posicaoSeta;
}


/**
 * @brief    Função que realiza um printf que serve como divisão entre diferentes textos
 */
void imprimeDivisao()
{
  printf("\n  ╾───╾───╾───╾───╾───╾───╾───╾───╾───╾───╾───╾───╾───╾───╾───╾──╼\n\n");
}


/**
 * @brief            Função auxiliar que imprime um número de espaços dados no stdout
 * @param quant      Número de espaços que pretende que se sejam imprimidos
 */
void imprimeEspacosQuantidade (int quant)
{
    while(quant>0) {
      printf(" ");
      quant--;
    }
}


/**
 * @brief     Função auxiliar que imprime o logótipo do projeto no stdout
 */
void imprimeLogo()
{
    printf("    ┌──────────────────────────────────────────────────────────────────────────────────────────────────────────┐\n");
    printf("    │                                                                                                          │\n");
    printf("    │"); color_red(); printf("                                        ███████╗ ██████╗ ██╗   ██╗                                        "); reseta(); printf("│\n");
    printf("    │"); color_red(); printf("                                        ██╔════╝██╔════╝ ██║   ██║                                        "); reseta(); printf("│\n");
    printf("    │"); color_red(); printf("                                        ███████╗██║  ███╗██║   ██║                                        "); reseta(); printf("│\n");
    printf("    │"); color_red(); printf("                                        ╚════██║██║   ██║╚██╗ ██╔╝                                        "); reseta(); printf("│\n");
    printf("    │"); color_red(); printf("                                        ███████║╚██████╔╝ ╚████╔╝                                         "); reseta(); printf("│\n");
    printf("    │"); color_red(); printf("                                        ╚══════╝ ╚═════╝   ╚═══╝                                          "); reseta(); printf("│\n");
    /*printf("    │                                                                                                          │\n");*/
    printf("    └──────────────────────────────────────────────────────────────────────────────────────────────────────────┘\n");
}

/**
 * @brief       Função auxiliar que imprime o menu de opções que podem ser escolhidas no stdout
 * @return      Posição do menu que usuário escolheu, que vai servir para chamar as funções necessários e requesitadas
 */
void imprimeMenuOpcoes()
{
        printf("    ┌──────────────────────────────────────────────────────────────────────────────────────────────────────────┐\n");
        printf("    │                                                                                                          │\n");
        printf("    │"); negrito(); printf("%s",print_opcao); reseta(); printf("│\n");
        printf("    │                                                                                                          │\n");
        printf("    │"); color_red(); imprimeSetaOuPonto(-1);printf("LOAD -> "); reseta(); if (_menu_posicaoSeta==-1)color_red(); printf("%s",print_load); reseta(); printf("│\n");
        printf("    │"); color_red(); imprimeSetaOuPonto(0);printf("FREE -> "); reseta(); if (_menu_posicaoSeta==0)color_red(); printf("%s",print_free); reseta(); printf("│\n");
        printf("    │"); color_red(); imprimeSetaOuPonto(1);printf("EXIT -> "); reseta(); if (_menu_posicaoSeta==1)color_red(); printf("%s",print_exit); reseta(); printf("│\n");
        printf("    │                                                                                                          │\n");
        printf("    │"); color_red(); imprimeSetaOuPonto(2);printf("C2 --> "); reseta(); if (_menu_posicaoSeta==2)color_red(); printf("%s",print_c2); reseta(); printf("│\n");
        printf("    │"); color_red(); imprimeSetaOuPonto(3);printf("C3 --> "); reseta(); if (_menu_posicaoSeta==3)color_red(); printf("%s",print_c3); reseta(); printf("│\n");
        printf("    │"); color_red(); imprimeSetaOuPonto(4);printf("C4 --> "); reseta(); if (_menu_posicaoSeta==4)color_red(); printf("%s",print_c4); reseta(); printf("│\n");
        printf("    │"); color_red(); imprimeSetaOuPonto(5);printf("C5 --> "); reseta(); if (_menu_posicaoSeta==5)color_red(); printf("%s",print_c5); reseta(); printf("│\n");
        printf("    │"); color_red(); imprimeSetaOuPonto(6);printf("C6 --> "); reseta(); if (_menu_posicaoSeta==6)color_red(); printf("%s",print_c6); reseta(); printf("│\n");
        printf("    │"); color_red(); imprimeSetaOuPonto(7);printf("C7 --> "); reseta(); if (_menu_posicaoSeta==7)color_red(); printf("%s",print_c7); reseta(); printf("│\n");
        printf("    │"); color_red(); imprimeSetaOuPonto(8);printf("C8 --> "); reseta(); if (_menu_posicaoSeta==8)color_red(); printf("%s",print_c8); reseta(); printf("│\n");
        printf("    │"); color_red(); imprimeSetaOuPonto(9);printf("C9 --> "); reseta(); if (_menu_posicaoSeta==9)color_red(); printf("%s",print_c9); reseta(); printf("│\n");
        printf("    │"); color_red(); imprimeSetaOuPonto(10);printf("C10 -> "); reseta(); if (_menu_posicaoSeta==10)color_red(); printf("%s",print_c10); reseta(); printf("│\n");
        printf("    │"); color_red(); imprimeSetaOuPonto(11);printf("C11 -> "); reseta(); if (_menu_posicaoSeta==11)color_red(); printf("%s",print_c11); reseta(); printf("│\n");
        printf("    │"); color_red(); imprimeSetaOuPonto(12);printf("C12 -> "); reseta(); if (_menu_posicaoSeta==12)color_red(); printf("%s",print_c12); reseta(); printf("│\n");
        printf("    │"); color_red(); imprimeSetaOuPonto(13);printf("C13 -> "); reseta(); if (_menu_posicaoSeta==13)color_red(); printf("%s",print_c13); reseta(); printf("│\n");
        printf("    │                                                                                                          │\n");
        printf("    └──────────────────────────────────────────────────────────────────────────────────────────────────────────┘\n");
}


/**
 * @brief                   Função que dada uma tecla Carrega, muda se possível a posição da seta no menu
 * @param teclaCarregada    Inteiro correspondeste á representação da tecla do keyboard primida num int
 * @return                  Nova posição da seta depois de uma certa tecla ser primida
 */
void novaPosicaoSeta (char teclaCarregada)
{
    if (teclaCarregada == 's' && _menu_posicaoSeta < 13) { /*baixo*/
      _menu_posicaoSeta++;
    }
    else if (teclaCarregada == 'w' && _menu_posicaoSeta > -1) { /*cima*/
      _menu_posicaoSeta--;
    }
}


/**
 * @brief                   Função que imprime no menu, na posição da seta uma seta, e nas outras um ponto
 * @param posicaoReal       Posicao de uma opcao do menu
 */
void imprimeSetaOuPonto (int posicaoReal)
{
    if(posicaoReal == _menu_posicaoSeta) {
        printf("    ➤  ");
    }
    else {
        printf("    •  ");
    }
}




/****************************IMPRIMIR QUERY 2**************************/
/**
 * @brief                   Função que imprime uma lista de codigos de produto e o numero total dos mesmo, do resultado obtido da Query2
 * @param arrayProdutos     Array onde estão contidos os códigos dos produtos recebidos
 */
void imprimeQuery2 (GPtrArray* arrayProdutos)
{
    char** array_str = (char**) arrayProdutos->pdata;
    int size_total = arrayProdutos->len;

    int prod_per_page = 28;
    int nr_pages = (double)(size_total / prod_per_page) + 0.99; /* arredondamento por excesso */
    int page_atual = 0;
    char teclaCarregada = 0;
    char letter = array_str[0][0];

    while(1) {
        system("clear");
        imprimeLogo();
        imprimeMenuOpcoes();
        imprimeDivisao();
        printf("   QUERY 2 -> Lista e número de Produtos começados por uma dada letra\n");
        negrito(); printf("\n\t\tLISTA DE PRODUTOS COMEÇADOS PELA LETRA %c\n", letter); reseta();

        imprimeTabelaQuery2(array_str, page_atual*prod_per_page);
        printf("     PAG %d/%d            Navegue no 'a' e no 'd' e saia no <ENTER>\n", page_atual+1, nr_pages);
        negrito(); printf("\n   %d produtos começados pela letra %c\n", size_total, letter); reseta();

        system("stty raw");
        system("stty -echo");
        scanf("%c", &teclaCarregada);
        system("stty cooked");
        system("stty echo");

        if (teclaCarregada == '\r') break;
        if (teclaCarregada == 'a' && page_atual > 0) page_atual--;
        else if (teclaCarregada == 'd' && page_atual < nr_pages-1) page_atual++;
    }
}

/**
 * @brief               Função que imprime a tabela referente à query 2
 * @param array_str     Array de strings a ser impresso
 * @param start_ind     Índice inicial de leitura do array
 */
static void imprimeTabelaQuery2(char** array_str, int start_ind)
{
    int i, pos = start_ind;

    printf("      ┌────────┬────────┬────────┬────────┬────────┬────────┬────────┐\n");
    printf("      │"); for (i=0; i<7; i++) printf(" %s │", array_str[pos++]);
    printf("\n      ├────────┼────────┼────────┼────────┼────────┼────────┼────────┤\n");
    printf("      │"); for (i=0; i<7; i++) printf(" %s │", array_str[pos++]);
    printf("\n      ├────────┼────────┼────────┼────────┼────────┼────────┼────────┤\n");
    printf("      │"); for (i=0; i<7; i++) printf(" %s │", array_str[pos++]);
    printf("\n      ├────────┼────────┼────────┼────────┼────────┼────────┼────────┤\n");
    printf("      │"); for (i=0; i<7; i++) printf(" %s │", array_str[pos++]);
    printf("\n      └────────┴────────┴────────┴────────┴────────┴────────┴────────┘\n");
}


/****************************IMPRIMIR QUERY 3**************************/
/**
 * @brief                   Função que imprime o resultado obtido da Query3
 * @param query3            Estrutura Query3 onde estão guardados os resultados necessários para responder a esta query
 * @param productID         Produto a ser estudado
 * @param month             Mês a ser estudado
 * @param option            Modo de impressão do resultado (global ou filial por filail)
 */
void imprimeQuery3(QUERY3AUX query3, char* productID, int month, char option)
{
    int sumVendas;
    float sumFaturacao;

    negrito();
    printf("\n   Dados do produto %s referentes a %s\n", productID, _meses[month-1]);
    printf("\tModo Normal:");
    reseta();

    if (option == '2'){
        printf("\n\t\tFilial 1 faturou %.2f em %d venda", query3->filial1N->faturado, query3->filial1N->vendas);
        if (query3->filial1N->vendas != 1) printf("s");
        printf("\n\t\tFilial 2 faturou %.2f em %d venda", query3->filial2N->faturado, query3->filial2N->vendas);
        if (query3->filial2N->vendas != 1) printf("s");
        printf("\n\t\tFilial 3 faturou %.2f em %d venda", query3->filial3N->faturado, query3->filial3N->vendas);
        if (query3->filial3N->vendas != 1) printf("s");
    }
    else {
        sumVendas = query3->filial1N->vendas + query3->filial2N->vendas + query3->filial3N->vendas;
        sumFaturacao = query3->filial1N->faturado + query3->filial2N->faturado + query3->filial3N->faturado;
        printf("\n\t\tFiliais faturaram %.2f em %d venda", sumFaturacao, sumVendas);
        if (sumVendas != 1) printf("s");
    }

    negrito();
    printf("\n\tModo Promoção:");
    reseta();

    if (option == '2'){
        printf("\n\t\tFilial 1 faturou %.2f em %d venda", query3->filial1P->faturado, query3->filial1P->vendas);
        if (query3->filial1P->vendas != 1) printf("s");
        printf("\n\t\tFilial 2 faturou %.2f em %d venda", query3->filial2P->faturado, query3->filial2P->vendas);
        if (query3->filial2P->vendas != 1) printf("s");
        printf("\n\t\tFilial 3 faturou %.2f em %d venda", query3->filial3P->faturado, query3->filial3P->vendas);
        if (query3->filial3P->vendas != 1) printf("s");
    }
    else {
        sumVendas = query3->filial1P->vendas + query3->filial2P->vendas + query3->filial3P->vendas;
        sumFaturacao = query3->filial1P->faturado + query3->filial2P->faturado + query3->filial3P->faturado;
        printf("\n\t\tFiliais faturaram %.2f em %d venda", sumFaturacao, sumVendas);
        if (sumVendas != 1) printf("s");
    }
    printf("\n");
}


/****************************IMPRIMIR QUERY 4**************************/
/**
 * @brief           Função que imprime o resultado obtido da Query4
 * @param buffer    Estrutura da Query4 onde estão guardados os resultados necessários para responder a esta query
 */
void imprimeQuery4 (BUFFER_Q4 buffer)
{
    char** array_str = (char**) buffer->productsID->pdata;
    int size_total = buffer->productsID->len;
    int branchID = buffer->branchID;

    int prod_per_page = 28;
    int nr_pages = (double)(size_total / prod_per_page) + 0.99; /* arredondamento por excesso */
    int page_atual = 0;
    char teclaCarregada = 0;

    while(1) {
        system("clear");
        imprimeLogo();
        imprimeMenuOpcoes();
        imprimeDivisao();
        printf("   QUERY 4 -> Lista ordenada e número dos produtos que nunca foram comprados\n");
        negrito();
        if (branchID == 0) printf("\n\t\tLISTA DE PRODUTOS NUNCA COMPRADOS\n");
        else printf("\n\t\tLISTA DE PRODUTOS NUNCA COMPRADOS PELA FILIAL %d\n", branchID);
        reseta();

        imprimeTabelaQuery4(array_str, page_atual*prod_per_page);
        printf("     PAG %d/%d            Navegue no 'a' e no 'd' e saia no <ENTER>\n", page_atual+1, nr_pages);
        negrito();
        if (branchID == 0) printf("\n   %d produtos nunca comprados\n", size_total);
        else printf("\n   %d produtos nunca comprados pela filial %d\n", size_total, branchID);
        reseta();

        system("stty raw");
        system("stty -echo");
        scanf("%c", &teclaCarregada);
        system("stty cooked");
        system("stty echo");

        if (teclaCarregada == '\r') break;
        if (teclaCarregada == 'a' && page_atual > 0) page_atual--;
        else if (teclaCarregada == 'd' && page_atual < nr_pages-1) page_atual++;
    }
}

/**
 * @brief               Função que imprime a tabela referente à query 4
 * @param array_str     Array de strings a ser impresso
 * @param start_ind     Índice inicial de leitura do array
 */
static void imprimeTabelaQuery4(char** array_str, int start_ind)
{
    int i, pos = start_ind;

    printf("      ┌────────┬────────┬────────┬────────┬────────┬────────┬────────┐\n");
    printf("      │"); for (i=0; i<7; i++) printf(" %s │", array_str[pos++]);
    printf("\n      ├────────┼────────┼────────┼────────┼────────┼────────┼────────┤\n");
    printf("      │"); for (i=0; i<7; i++) printf(" %s │", array_str[pos++]);
    printf("\n      ├────────┼────────┼────────┼────────┼────────┼────────┼────────┤\n");
    printf("      │"); for (i=0; i<7; i++) printf(" %s │", array_str[pos++]);
    printf("\n      ├────────┼────────┼────────┼────────┼────────┼────────┼────────┤\n");
    printf("      │"); for (i=0; i<7; i++) printf(" %s │", array_str[pos++]);
    printf("\n      └────────┴────────┴────────┴────────┴────────┴────────┴────────┘\n");
}


/****************************IMPRIMIR QUERY 5**************************/
/**
 * @brief                   Função que imprime o resultado obtido da Query5
 * @param comparacaoFinal   Estrutura onde estão guardados os resultados necessários para responder a esta query (códigos de Clientes)
 */
void imprimeQuery5 (GTree* comparacaoFinal)
{
    QUERY5_AUX query5_aux = malloc(sizeof(struct query5_aux));
    query5_aux->array = malloc(g_tree_nnodes(comparacaoFinal) * sizeof(char*));
    query5_aux->used = 0;
    g_tree_foreach(comparacaoFinal, Q5nodoAVLtoStr, &query5_aux);

    char** array_str = query5_aux->array;
    int size_total = query5_aux->used;

    int i;
    int prod_per_page = 32;
    int nr_pages = (double)(size_total / prod_per_page) + 0.99; /* arredondamento por excesso */
    int page_atual = 0;
    char teclaCarregada = 0;

    while(1) {
        system("clear");
        imprimeLogo();
        imprimeMenuOpcoes();
        imprimeDivisao();
        printf("   QUERY 5 -> Lista ordenada de clientes que realizaram compras em todas as filiais\n");
        negrito(); printf("\n\t\tLISTA DE CLIENTES QUE COMPRARAM EM TODAS AS FILIAIS\n"); reseta();

        imprimeTabelaQuery5(array_str, page_atual*prod_per_page);
        printf("     PAG %d/%d            Navegue no 'a' e no 'd' e saia no <ENTER>\n", page_atual+1, nr_pages);
        negrito(); printf("\n   %d produtos que compraram em todas as filiais\n", size_total); reseta();

        system("stty raw");
        system("stty -echo");
        scanf("%c", &teclaCarregada);
        system("stty cooked");
        system("stty echo");

        if (teclaCarregada == '\r') break;
        if (teclaCarregada == 'a' && page_atual > 0) page_atual--;
        else if (teclaCarregada == 'd' && page_atual < nr_pages-1) page_atual++;
    }

    for (i = 0; i < size_total; i++)
        free(array_str[i]);
    free(array_str);
    free(query5_aux);
}

static void imprimeTabelaQuery5(char** array_str, int start_ind)
{
    int i, pos = start_ind;

    printf("      ┌───────┬───────┬───────┬───────┬───────┬───────┬───────┬───────┐\n");
    printf("      │"); for (i=0; i<8; i++) printf(" %s │", array_str[pos++]);
    printf("\n      ├───────┼───────┼───────┼───────┼───────┼───────┼───────┼───────┤\n");
    printf("      │"); for (i=0; i<8; i++) printf(" %s │", array_str[pos++]);
    printf("\n      ├───────┼───────┼───────┼───────┼───────┼───────┼───────┼───────┤\n");
    printf("      │"); for (i=0; i<8; i++) printf(" %s │", array_str[pos++]);
    printf("\n      ├───────┼───────┼───────┼───────┼───────┼───────┼───────┼───────┤\n");
    printf("      │"); for (i=0; i<8; i++) printf(" %s │", array_str[pos++]);
    printf("\n      └───────┴───────┴───────┴───────┴───────┴───────┴───────┴───────┘\n");
}

/**
 * @brief           Função que guarda cada nodo da AVL num array de strings
 * @param key       Key para o nodo da AVL da Query5
 * @param value     Apontador para o nodo da AVL da Query5
 * @param data      Array de strings onde são guardados os clientes da AVL
 * @return          FALSE em caso de sucesso, de forma a continuar a percorrer a AVL
 */
static gboolean Q5nodoAVLtoStr (gpointer key, gpointer value, gpointer data)
{
    char* chave = (char*) value;
	QUERY5_AUX query5_aux = *((QUERY5_AUX*) data);
    char** array = query5_aux->array;
    int used = query5_aux->used;

	array[used] = mystrdup(chave);
    query5_aux->used++;

    return FALSE;
}


/****************************IMPRIMIR QUERY 6**************************/
/**
 * @brief                   Função que imprime o resultado obtido da Query6
 * @param query6            Estrutura onde estão guardados os resultados necessários para responder a esta query
 */
void imprimeQuery6 (QUERY6 query6)
{
    negrito();
    printf("\n\tProdutos que ninguém comprou: %d\n", query6->nrProdutos);
    printf("\tClientes que nunca compraram: %d\n", query6->nrClientes);
    reseta();
}


/****************************IMPRIMIR QUERY 7**************************/
/**
 * @brief                   Função que imprime o resultado como forma de tabela obtido da Query7
 * @param query7            Estrutura onde estão guardados os resultados necessários para responder a esta query
 */
void imprimeQuery7 (QUERY7 query7)
{
    int i;

    printf("\n┌───────────┬───────────────────────────────────────────────────────────────────────────────────────────────┐\n");

    printf("│ Mes       │  JAN  │  FEV  │  MAR  │  ABR  │  MAI  │  JUN  │  JUL  │  AGO  │  SET  │  OUT  │  NOV  │  DEZ  │");

    printf("\n├───────────┼───────────────────────────────────────────────────────────────────────────────────────────────┤");

    printf("\n│ Filial 1  │");
    for (i=0; i<12; i++) {
        printf("  %d",query7->quantidadesProdutoFilial1[i]);
        imprimeEspacosQuantidade(5 - (qDig( (int)query7->quantidadesProdutoFilial1[i]))); printf("│");
    }

    printf("\n│ Filial 2  │");
    for (i=0; i<12; i++) {
        printf("  %d",query7->quantidadesProdutoFilial2[i]);
        imprimeEspacosQuantidade(5 - (qDig( (int)query7->quantidadesProdutoFilial2[i]))); printf("│");
    }

    printf("\n│ Filial 3  │");
    for (i=0; i<12; i++) {
        printf("  %d",query7->quantidadesProdutoFilial3[i]);
        imprimeEspacosQuantidade(5 - (qDig( (int)query7->quantidadesProdutoFilial3[i]))); printf("│");
    }

    printf("\n├───────────┼───────────────────────────────────────────────────────────────────────────────────────────────┤");
    printf("\n│ Total     │");
    for (i=0; i<12; i++) {
        printf("  %d",query7->quantidadesTotaisMes[i]);
        imprimeEspacosQuantidade(5 - (qDig( (int)query7->quantidadesTotaisMes[i]))); printf("│");
    }

    printf("\n├───────────┴───────────────────────────┬───────────────────────────────────────────────────────────────────┘");
    printf("\n│ Quantidade Total -> %d\t\t",query7->quantidadeTotal); printf("│\n");
    printf("└───────────────────────────────────────┘\n");
}


/****************************IMPRIMIR QUERY 8**************************/
/**
 * @brief                   Função que imprime o resultado obtido da Query8
 * @param buffer            Estrutura onde estão guardados os resultados necessários para responder a esta query
 * @param minMonth          Mês mínimo do intervalo de tempo que queremos estudar
 * @param maxMonth          Mês máximo do intervalo de tempo que queremos estudar
 */
void imprimeQuery8 (QUERY8 buffer, int minMonth, int maxMonth)
{
    negrito();
    if (minMonth == maxMonth) {
        printf("\n\tTotal faturado em %s: %.2f\n", _meses[minMonth-1], buffer->faturado);
        printf("\tNúmero de vendas em %s: %d\n\n", _meses[minMonth-1], buffer->vendas);
    }
    else {
        printf("\n\tTotal faturado entre %s e %s: %.2f\n", _meses[minMonth-1], _meses[maxMonth-1], buffer->faturado);
        printf("\tNúmero de vendas entre %s e %s: %d\n\n", _meses[minMonth-1], _meses[maxMonth-1], buffer->vendas);
    }
    reseta();
}


/****************************IMPRIMIR QUERY 9**************************/
/**
 * @brief                   Função que imprime o resultado obtido da Query9
 * @param productBuyers     Estrutura onde estão guardados os resultados necessários para responder a esta query
 * @param productID         Código do Produto a ser estudado
 * @param branch            Número da filial a ser estudada
 */
void imprimeQuery9 (QUERY9 productBuyers, char* productID, int branch)
{
    gint i;

    negrito();
    printf("\n   Clientes que compraram o Produto %s na Filial %d:\n", productID, branch);
    printf("\n\tMODO N: %d Cliente(s)\n", productBuyers->productBuyersN->len);
    reseta();
    for (i=0; i<productBuyers->productBuyersN->len; i++) {
       printf("\t\t%s\n", (char*)productBuyers->productBuyersN->pdata[i]);
    }
    negrito();
    printf("\n\tMODO P: %d Cliente(s)\n", productBuyers->productBuyersP->len);
    reseta();
    for (i=0; i<productBuyers->productBuyersP->len; i++) {
       printf("\t\t%s\n ", (char*)productBuyers->productBuyersP->pdata[i]);
    }

}


/****************************IMPRIMIR QUERY 10**************************/
/**
 * @brief                   Função que imprime o resultado obtido da Query10
 * @param array             Estrutura onde estão guardados os resultados necessários para responder a esta query
 * @param clientID          Código do Cliente a ser estudado
 * @param month             Mês a ser estudado
 */
void imprimeQuery10 (GPtrArray* array, char* clientID, int month)
{
    int i;
    QUERY10 query10;

    negrito();
    printf("\n   Produtos comprados pelo cliente %s em %s:\n", clientID, _meses[month-1]);
    reseta();
    for (i = 0; i < array->len; i++) {
        query10 = (QUERY10) array->pdata[i];
        printf("\t%s -> %d unidades\n", query10->productID, query10->quantidade);
    }
}


/****************************IMPRIMIR QUERY 11**************************/
/**
 * @brief                   Função que imprime o resultado obtido da Query11
 * @param resultado         Estrutura onde estão guardados os resultados necessários para responder a esta query
 * @param limit             Número de produtos limite que queremos obter
 */
void imprimeQuery11 (QUERRY11_AXU2 resultado, int limit)
{
    int prod_per_page = 10;
    int nr_pages = limit / prod_per_page;
    nr_pages += (limit%prod_per_page == 0) ? 0 : 1;
    int current_page = 0, current_mode = 0;
    char teclaCarregada = 0;

    while(1) {
        system("clear");
        imprimeLogo();
        imprimeMenuOpcoes();
        imprimeDivisao();
        imprimeQuery11Aux(resultado, current_page, current_mode, prod_per_page, nr_pages, limit);

        system("stty raw");
        system("stty -echo");
        scanf("%c", &teclaCarregada);
        system("stty cooked");
        system("stty echo");

        if (teclaCarregada == '\r') break;
        if (teclaCarregada == 'a' && current_page > 0) current_page--;
        else if (teclaCarregada == 'd' && current_page < nr_pages-1) current_page++;
        else if (teclaCarregada == '0') current_mode = 0;
        else if (teclaCarregada == '1') current_mode = 1;
        else if (teclaCarregada == '2') current_mode = 2;
        else if (teclaCarregada == '3') current_mode = 3;
    }
}

/**
 * @brief                   Função auxiliar para imprimir o resultado obtido da query 11
 * @param array             Estrutura recebida da query 11
 * @param current_page      Página atual
 * @param current_mode      Modo atual
 * @param prod_per_page     Número de produtos apresentados por página
 * @param nr_pages          Número de páginas no total
 * @param limit             Número total de a ser apresentado
 */
static void imprimeQuery11Aux (QUERRY11_AXU2 resultado, int current_page, int current_mode, int prod_per_page, int nr_pages, int limit)
{
    int i;
    int ind = current_page * prod_per_page;
    GPtrArray* array;
    QUERY11 query11;
    char buffer[64];

    printf("   QUERY 11 -> Lista N produtos mais vendidos em todo o ano, número de clientes e unidades vendidas por filial\n");

    switch (current_mode) {
        case 0:
            array = resultado->total;
            negrito(); printf("\n\tLISTA DE PRODUTOS MAIS VENDIDOS EM TODO O ANO\n"); reseta();
            printf("\t┌──────────────┬─────────────────────┐\n");
            negrito();printf("\t│   PRODUTO    │  UNIDADES VENDIDAS  │\n");reseta();
            for (i = 0; i < prod_per_page && i < limit-prod_per_page*current_page; i++) {
                printf("\t├──────────────┼─────────────────────┤\n");
                query11 = (QUERY11) array->pdata[ind++];
                sprintf(buffer, "%d", query11->quantidade);
                printf("\t│   %s     │    %s unidades",query11->productID,buffer);imprimeEspacosQuantidade(8-strlen(buffer));printf("│\n");
            }
            printf("\t└──────────────┴─────────────────────┘\n");

            printf("     PAG %d/%d            Navegue entre páginas no 'a' e no 'd', entre filiais no {0,1,2,3} e saia no <ENTER>\n", current_page+1, nr_pages);
            break;
        case 1:
            array = resultado->cliFilia1;
            negrito(); printf("\n\tLISTA DE PRODUTOS MAIS VENDIDOS EM TODO O ANO - FILIAL 1\n"); reseta();
            printf("\t┌──────────────┬─────────────────────┬─────────────────┐\n");
            negrito(); printf("\t│   PRODUTO    │  UNIDADES VENDIDAS  │   NR CLIENTES   │\n");reseta();
            for (i = 0; i < prod_per_page && i < limit-prod_per_page*current_page; i++) {
                query11 = (QUERY11) array->pdata[ind++];
                printf("\t├──────────────┼─────────────────────┼─────────────────┤\n");
                sprintf(buffer, "%d", query11->quantidade);
                printf("\t│   %s     │    %s unidades",query11->productID,buffer);imprimeEspacosQuantidade(8-strlen(buffer));
                sprintf(buffer, "%d", query11->clientes);
                printf("│   %s clientes",buffer);imprimeEspacosQuantidade(5-strlen(buffer));printf("│\n");
            }
            printf("\t└──────────────┴─────────────────────┴─────────────────┘\n");

            printf("     PAG %d/%d            Navegue entre páginas no 'a' e no 'd', entre filiais no {0,1,2,3} e saia no <ENTER>\n", current_page+1, nr_pages);
            break;
        case 2:
            array = resultado->cliFilia2;
            negrito(); printf("\n\tLISTA DE PRODUTOS MAIS VENDIDOS EM TODO O ANO - FILIAL 2\n"); reseta();
            printf("\t┌──────────────┬─────────────────────┬─────────────────┐\n");
            negrito(); printf("\t│   PRODUTO    │  UNIDADES VENDIDAS  │   NR CLIENTES   │\n");reseta();
            for (i = 0; i < prod_per_page && i < limit-prod_per_page*current_page; i++) {
                query11 = (QUERY11) array->pdata[ind++];
                printf("\t├──────────────┼─────────────────────┼─────────────────┤\n");
                sprintf(buffer, "%d", query11->quantidade);
                printf("\t│   %s     │    %s unidades",query11->productID,buffer);imprimeEspacosQuantidade(8-strlen(buffer));
                sprintf(buffer, "%d", query11->clientes);
                printf("│   %s clientes",buffer);imprimeEspacosQuantidade(5-strlen(buffer));printf("│\n");
            }
            printf("\t└──────────────┴─────────────────────┴─────────────────┘\n");

            printf("     PAG %d/%d            Navegue entre páginas no 'a' e no 'd', entre filiais no {0,1,2,3} e saia no <ENTER>\n", current_page+1, nr_pages);
            break;
        case 3:
            array = resultado->cliFilia3;
            negrito(); printf("\n\tLISTA DE PRODUTOS MAIS VENDIDOS EM TODO O ANO - FILIAL 3\n"); reseta();
            printf("\t┌──────────────┬─────────────────────┬─────────────────┐\n");
            negrito(); printf("\t│   PRODUTO    │  UNIDADES VENDIDAS  │   NR CLIENTES   │\n");reseta();
            for (i = 0; i < prod_per_page && i < limit-prod_per_page*current_page; i++) {
                query11 = (QUERY11) array->pdata[ind++];
                printf("\t├──────────────┼─────────────────────┼─────────────────┤\n");
                sprintf(buffer, "%d", query11->quantidade);
                printf("\t│   %s     │    %s unidades",query11->productID,buffer);imprimeEspacosQuantidade(8-strlen(buffer));
                sprintf(buffer, "%d", query11->clientes);
                printf("│   %s clientes",buffer);imprimeEspacosQuantidade(5-strlen(buffer));printf("│\n");
            }
            printf("\t└──────────────┴─────────────────────┴─────────────────┘\n");

            printf("     PAG %d/%d            Navegue entre páginas no 'a' e no 'd', entre filiais no {0,1,2,3} e saia no <ENTER>\n", current_page+1, nr_pages);
            break;
    }
}


/****************************IMPRIMIR QUERY 12**************************/
/**
 * @brief                          Função que imprime o resultado obtido da Query12
 * @param listaProdutosEFaturado   Estrutura onde estão guardado os resultados necessários para responder a esta query
 * @param limit                    Número de produtos limite que queremos obter
 * @param clientID                 Código do cliente que estamos a estudar
 */
void imprimeQuery12 (GPtrArray* listaProdutosEFaturado, int limit, char* clientID)
{
    int prod_per_page = 10;
    if (limit > listaProdutosEFaturado->len) limit = listaProdutosEFaturado->len;
    int nr_pages = limit / prod_per_page;
    nr_pages += (limit%prod_per_page == 0) ? 0 : 1;
    int current_page = 0;
    char teclaCarregada = 0;

    while(1) {
        system("clear");
        imprimeLogo();
        imprimeMenuOpcoes();
        imprimeDivisao();
        imprimeQuery12Aux(listaProdutosEFaturado, current_page, prod_per_page, nr_pages, limit, clientID);

        system("stty raw");
        system("stty -echo");
        scanf("%c", &teclaCarregada);
        system("stty cooked");
        system("stty echo");

        if (teclaCarregada == '\r') break;
        if (teclaCarregada == 'a' && current_page > 0) current_page--;
        else if (teclaCarregada == 'd' && current_page < nr_pages-1) current_page++;
    }
}

/**
 * @brief                   Função auxiliar para imprimir o resultado obtido da query 12
 * @param array             Array recebido da query 12
 * @param current_page      Página atual
 * @param prod_per_page     Número de produtos apresentados por página
 * @param nr_pages          Número de páginas no total
 * @param limit             Número total de a ser apresentado
 * @param clientID          Código de cliente referente à query
 */
static void imprimeQuery12Aux (GPtrArray* array, int current_page, int prod_per_page, int nr_pages, int limit, char* clientID)
{
    int i;
    int ind = current_page * prod_per_page;
    QUERY12 query12;
    char buffer[64];

    printf("   QUERY 12 -> Dado um Cliente, recebe lista dos N produtos em que este mais gastou dinheiro durante o ano\n");

    if (limit == 1) {negrito(); printf("\n\tLISTA DO PRODUTO EM QUE O CLIENTE %s GASTOU MAIS DINHEIRO\n",clientID); reseta();}
    else {negrito(); printf("\n\tLISTA DOS %d PRODUTOS EM QUE O CLIENTE %s GASTOU MAIS DINHEIRO\n",limit,clientID); reseta();}

    printf("\t\t┌──────────────┬──────────────┐\n");
    negrito();printf("\t\t│   PRODUTO    │    GASTO     │\n");reseta();
    for (i = 0; i < prod_per_page && i < limit-prod_per_page*current_page; i++) {
        printf("\t\t├──────────────┼──────────────┤\n");
        query12 = (QUERY12) array->pdata[ind++];
        sprintf(buffer, "%.2f", query12->faturado);
        printf("\t\t│    %s    │   %s",query12->productID,buffer);imprimeEspacosQuantidade(11-strlen(buffer));printf("│\n");
    }
    printf("\t\t└──────────────┴──────────────┘\n");

    printf("     PAG %d/%d            Navegue entre páginas no 'a' e no 'd' e saia no <ENTER>\n", current_page+1, nr_pages);
}


/****************************IMPRIMIR QUERY 13**************************/
/**
 * @brief                    Função que imprime o resultado obtido da Query13
 * @param clientsFilePath    Localização do ficheiro onde estão contidos os cliente
 * @param productsFilePath   Localização do ficheiro onde estão contidos os produtos
 * @param salesFilePath      Localização do ficheiro onde estão contidas as vendas
 */
void imprimeQuery13 (char* clientsFilePath, char* productsFilePath, char* salesFilePath)
{
    printf("\n   Clientes lidos do ficheiro %s\n", clientsFilePath);
    printf("   Clientes lidos: %d\n", global_NUM_CLIENTES_TOTAL);
    printf("   Clientes validados: %d\n", global_NUM_CLIENTES_VAL);

    printf("\n   Produtos lidos do ficheiro %s\n", productsFilePath);
    printf("   Produtos lidos: %d\n", global_NUM_PRODUTOS_TOTAL);
    printf("   Produtos validados: %d\n", global_NUM_PRODUTOS_VAL);

    printf("\n   Vendas lidas do ficheiro %s\n", salesFilePath);
    printf("   Vendas lidas: %d\n", global_NUM_VENDAS_TOTAL);
    printf("   Vendas validadas: %d\n\n", global_NUM_VENDAS_VAL);
}
