#ifndef VENDAS_H
#define VENDAS_H

void loadVendas(PRODUTOS produtos,
                CLIENTES clientes,
                FATURACAO faturacao,
                FILIAL filial[],
                char* salesFilePath);
int validaVenda (char* codigo, PRODUTOS produtos, CLIENTES clientes, FATURACAO faturacao, FILIAL filial[]);

#endif
