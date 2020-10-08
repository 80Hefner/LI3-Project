#ifndef AUXS_H
#define AUXS_H

char* mystrdup (const char *str);
int mystrcmp(const void * pt1, const void * pt2);
int myintcmp(const void * pt1, const void * pt2);
void mytoupperstr(char* str);

void myg_free (gpointer data, gpointer user_data);
void myfreePointerArray (GPtrArray* array);

gboolean gconstpointerStrEquals (gconstpointer pt1, gconstpointer pt2);

gboolean query10EqualFunc (gconstpointer pt1, gconstpointer pt2);
gint comparaNodosQuery10 (gconstpointer pt1, gconstpointer pt2);

gint comparaNodosQuery11(gconstpointer pt1, gconstpointer pt2);
GPtrArray* copiarPtrArrayQ11 (GPtrArray* arrayACopair);
void Q11GCopyFunc (gpointer data, gpointer user_data);
gboolean Q11GEqualFunc (gconstpointer pt1, gconstpointer pt2);

gboolean query12EqualFunc (gconstpointer pt1, gconstpointer pt2);
gint comparaNodosQuery12 (gconstpointer pt1, gconstpointer pt2);

gint compareAVLStrings(gconstpointer p1, gconstpointer p2, gpointer user_data);
void freeAVLStrings(gpointer key);
#endif
