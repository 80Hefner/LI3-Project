#ifndef VISUAL_H
#define VISUAL_H

void color_red ();
void negrito ();
void reseta ();
void hide_cursor ();
void show_cursor ();

int menu();

void imprimeDivisao();
void imprimeLogo();
void imprimeMenuOpcoes();
void novaPosicaoSeta (char teclaCarregada);
void imprimeSetaOuPonto (int posicaoReal);

void imprimeQuery2  (GPtrArray* arrayProdutos);
void imprimeQuery3  (QUERY3AUX query3, char* productID, int month, char option);
void imprimeQuery4  (BUFFER_Q4 buffer);
void imprimeQuery5  (GTree* comparacaoFinal);
void imprimeQuery6  (QUERY6 query6);
void imprimeQuery7  (QUERY7 query7);
void imprimeQuery8  (QUERY8 buffer, int minMonth, int maxMonth);
void imprimeQuery9  (QUERY9 productBuyers, char* productID, int branch);
void imprimeQuery10 (GPtrArray* array, char* clientID, int month);
void imprimeQuery11 (QUERRY11_AXU2 resultado, int limit);
void imprimeQuery12 (GPtrArray* listaProdutosEFaturado, int limit, char* clienteID);
void imprimeQuery13 (char* clientsFilePath, char* productsFilePath, char* salesFilePath);

#endif
