LIBS = ../lib/lib_mongoose.a
TARGET = httpserver
FLAGS = -lpthread

all : main.o
	g++ -o $(TARGET) main.o
main.o : ../include/Server.h ../include/WebController.h
    g++ -Wall main.cpp -o main.o $(LIBS) $(FLAGS)