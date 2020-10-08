IDIR = ../include
PKG = `pkg-config --cflags --libs glib-2.0`
OBJDIR = obj
OBJS = $(OBJDIR)/main.o
OBJS += $(OBJDIR)/catProduto.o
OBJS += $(OBJDIR)/catCliente.o
OBJS += $(OBJDIR)/vendas.o
OBJS += $(OBJDIR)/auxs.o
OBJS += $(OBJDIR)/interface.o
OBJS += $(OBJDIR)/faturacao.o
OBJS += $(OBJDIR)/filial.o
OBJS += $(OBJDIR)/visual.o
OBJS += $(OBJDIR)/controlador.o

CFLAGS = -Wall -g -I$(IDIR) -ansi

program: $(OBJS)
	gcc $(CFLAGS) $(OBJS) $(PKG) -o program

$(OBJDIR)/%.o: %.c
	gcc $(CFLAGS) $(PKG) -c $< -o $@

clean:
	rm $(OBJS)
	rm program
