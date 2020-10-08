/**
 * @file    auxs.c
 * @brief   Ficheiro com as funções auxiliares
 */

#include "headers.h"


/*-----------------------------------STRDUP-------------------------------------*/

/**
 * @brief			Função que retorna uma cópia de uma string
 * @param str		String a ser copiada
 * @return			Cópia da string recebida
 */
char* mystrdup (const char *str)
{
	char* dup = malloc(strlen(str) + 1);
	if (dup)
		strcpy(dup, str);

	return dup;
}


/*-----------------------------------COMPARES-------------------------------------*/

/**
 * @brief			Função que compara duas strings
 * @param pt1		String 1
 * @param pt2		String 2
 * @return			0 caso as strings sejam iguais, >0 caso pt1>pt2 e <0 caso contrário
 */
int mystrcmp (const void * pt1, const void * pt2)
{
	const char * str1 = *(const char **) pt1;
	const char * str2 = *(const char **) pt2;

	return (strcmp(str1,str2));
}

/**
 * @brief			Função que compara dois inteiros
 * @param pt1		Inteiro 1
 * @param pt2		Inteiro 2
 * @return			Diferença entre pt1 e pt2
 */
int myintcmp (const void * pt1, const void * pt2)
{
	int num1 = *(int *) pt1;
	int num2 = *(int *) pt2;

	return (num1 - num2);
}


/*-----------------------------------TOUPPER-------------------------------------*/

/**
 * @brief			Função que recebe uma string e tranforma os carateres lowercase em uppercase
 * @param str		String recebida
 */
void mytoupperstr(char* str)
{
	int i;
	for (i = 0; i < strlen(str); i++)
		if (islower(str[i]))
			str[i] = toupper(str[i]);
}


/*-----------------------------------FREES-------------------------------------*/

/**
 * @brief				Função que liberta a memória de um gconstpointer
 * @param data			Apontador a ser libertado
 * @param user_data		User data passada à função
 */
void myg_free (gpointer data, gpointer user_data)
{
	free(data);
}

/**
 * @brief			Função que liberta totalmente um GPtrArray
 * @param array		GptrArray a ser libertado
 */
void myfreePointerArray (GPtrArray* array)
{
	g_ptr_array_foreach(array, myg_free, NULL);
	g_ptr_array_free(array, TRUE);
}


/*--------------------------------GCONSTPOINTER_STREQUALS-------------------------------------*/

/**
 * @brief			Função que testa se duas strings são iguais, recebendo gconstpointer
 * @param pt1		String 1
 * @param pt2		String 2
 * @return			TRUE caso as string sejam iguais; FALSE caso contrário
 */
gboolean gconstpointerStrEquals (gconstpointer pt1, gconstpointer pt2)
{
	char* str1 = (char*) pt1;
	char* str2 = (char*) pt2;
	int result = strcmp(str1,str2);
	gboolean gresult = (result == 0) ? TRUE : FALSE;

	return gresult;
}


/*-----------------------------------QUERY10-------------------------------------*/

/**
 * @brief			Função que testa se uma string é igual à string associada a um nodo QUERY10, recebendo gconstpointer
 * @param pt1		String 1
 * @param pt2		Nodo QUERY10
 * @return			TRUE caso as string sejam iguais; FALSE caso contrário
 */
gboolean query10EqualFunc (gconstpointer pt1, gconstpointer pt2)
{
	char* str1 = (char*) pt1;
	char* str2 = ((QUERY10) pt2)->productID;
	int result = strcmp(str1,str2);
	gboolean gresult = (result == 0) ? TRUE : FALSE;

	return gresult;
}

/**
 * @brief       Função que compara dois nodos QUERY10
 * @param pt1   Apontador para um primeiro nodo
 * @param pt    Apontador para um segundo nodo
 * @return      Resultado da comparação da quantidade associada a cada nodo
 */
gint comparaNodosQuery10 (gconstpointer pt1, gconstpointer pt2)
{
	QUERY10 nodo1 = *((QUERY10*) pt1);
	QUERY10 nodo2 = *((QUERY10*) pt2);
	gint result = (gint) (nodo2->quantidade - nodo1->quantidade);

	return result;
}


/*-----------------------------------QUERY11-------------------------------------*/

/**
 * @brief       Função que compara dois nodos QUERY10
 * @param pt1   Apontador para um primeiro nodo
 * @param pr2   Apontador para um segundo nodo
 * @return      Resultado da comparação da quantidade associada a cada nodo
 */
gint comparaNodosQuery11(gconstpointer pt1, gconstpointer pt2)
{
    QUERY11 nodo1 = *((QUERY11*) pt1);
	QUERY11 nodo2 = *((QUERY11*) pt2);
    gint result = (gint) (nodo2->quantidade - nodo1->quantidade);

    return result;
}

/**
 * @brief                 Função que cria uma cópia de um GPtrArray onde estão contidas nas posições structs da Query11
 * @param arrayACopair    GPtrArray que pretendemos que seja copiado
 * @return                Cópia do GPtrArray original
 */
GPtrArray* copiarPtrArrayQ11 (GPtrArray* arrayACopair)
{
	GPtrArray* novoArray = g_ptr_array_new();
	g_ptr_array_foreach(arrayACopair, Q11GCopyFunc, &novoArray);
	return novoArray;
}

/**
 * @brief               Função que cria uma cópia de uma struct Query11 e a põe a 0
 * @param data			Apontador para uma struct Query11
 * @param user_data		Apontador para o GPtrArray de Structs de query11
 */
void Q11GCopyFunc (gpointer data, gpointer user_data)
{
	QUERY11 novaQuery11 = malloc(sizeof(struct query11));
	QUERY11 queryAntiga = (QUERY11) data;
	GPtrArray* array = *((GPtrArray **) user_data);
	strcpy(novaQuery11->productID, queryAntiga->productID);
	novaQuery11->clientes   = 0;
	novaQuery11->quantidade = 0;
	g_ptr_array_add(array, novaQuery11);
}

/**
 * @brief			Função que testa se uma string é igual à string associada a um nodo QUERY11, recebendo gconstpointer
 * @param pt1		String 1
 * @param pt2		Nodo QUERY11
 * @return			TRUE caso as string sejam iguais; FALSE caso contrário
 */
gboolean Q11GEqualFunc (gconstpointer pt1, gconstpointer pt2)
{
	char* str1 = (char*) pt1;
	char* str2 = ((QUERY11) pt2)->productID;
	int result = strcmp(str1,str2);
	gboolean gresult = (result == 0) ? TRUE : FALSE;

	return gresult;
}


/*-----------------------------------QUERY12-------------------------------------*/

/**
 * @brief			Função que testa se uma string é igual à string associada a um nodo QUERY12, recebendo gconstpointer
 * @param pt1		String 1
 * @param pt2		Nodo QUERY12
 * @return			TRUE caso as string sejam iguais; FALSE caso contrário
 */
gboolean query12EqualFunc (gconstpointer pt1, gconstpointer pt2)
{
	char* str1 = (char*) pt1;
	char* str2 = ((QUERY12) pt2)->productID;
	int result = strcmp(str1,str2);
	gboolean gresult = (result == 0) ? TRUE : FALSE;

	return gresult;
}


/**
 * @brief       Função que compara dois nodos QUERY12
 * @param pt1   Apontador para um primeiro nodo
 * @param pt2   Apontador para um segundo nodo
 * @return      Resultado da comparação do faturado associada a cada nodo
 */
gint comparaNodosQuery12 (gconstpointer pt1, gconstpointer pt2)
{
	QUERY12 nodo1 = *((QUERY12*) pt1);
	QUERY12 nodo2 = *((QUERY12*) pt2);
	gint result = (gint) (nodo2->faturado - nodo1->faturado);

	return result;
}


/*-----------------------------------AVL STRINGS-------------------------------------*/

/**
 * @brief               Função que compara dois nodos de uma AVL de strings
 * @param a             Apontador para um primeiro nodo da AVL
 * @param b             Apontador para um segundo nodo da AVL
 * @param user_data     User data passada à função
 * @return              Strcmp dos códigos de produto dos nodos
 */
gint compareAVLStrings(gconstpointer p1, gconstpointer p2, gpointer user_data)
{
    char* str1 = (char*) p1;
    char* str2 = (char*) p2;
    return (gint) strcmp(str1,str2);

}

/**
* @brief           Função que liberta a memória alocada para uma key de um nodo de uma AVL de Strings
* @param key       Key do nodo da AVL da faturação
*/
void freeAVLStrings(gpointer key)
{
    char* string = (char*) key;
    free(string);
}
