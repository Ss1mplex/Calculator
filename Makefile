# Makefile for Calculator project

# Directories
SRC_DIR = src
BIN_DIR = bin
LIB_DIR = lib

# Java Compiler
JAVAC = javac
JAR = jar

# Compilation flags
JFLAGS = -cp $(SRC_DIR) -d $(BIN_DIR)

# List of source files
CLIENT_SOURCES = $(wildcard $(SRC_DIR)/Client/*.java)
SERVER_SOURCES = $(wildcard $(SRC_DIR)/Server/*.java)
MODEL_SOURCES = $(wildcard $(SRC_DIR)/model/*.java)

# List of compiled classes
CLIENT_CLASSES = $(CLIENT_SOURCES:$(SRC_DIR)/%.java=$(BIN_DIR)/%.class)
SERVER_CLASSES = $(SERVER_SOURCES:$(SRC_DIR)/%.java=$(BIN_DIR)/%.class)
MODEL_CLASSES = $(MODEL_SOURCES:$(SRC_DIR)/%.java=$(BIN_DIR)/%.class)

# Default target
all: client server

client: $(CLIENT_CLASSES)

run_client1: $(BIN_DIR)/Client
	java -cp $(BIN_DIR) Client < $(SRC_DIR)/Client/input_client1.txt

run_client2: $(BIN_DIR)/Client
	java -cp $(BIN_DIR) Client < $(SRC_DIR)/Client/input_client2.txt

run_client3: $(BIN_DIR)/Client
	java -cp $(BIN_DIR) Client < $(SRC_DIR)/Client/input_client3.txt

server: $(SERVER_CLASSES) $(MODEL_CLASSES)
	java -cp $(BIN_DIR) Server.Server
# Compile rule
$(BIN_DIR)/%.class: $(SRC_DIR)/%.java | $(BIN_DIR)
	$(JAVAC) $(JFLAGS) $<

# Create bin directory if not exists
$(BIN_DIR):
	mkdir -p $(BIN_DIR)

# Clean rule
clean:
	rm -rf $(BIN_DIR)

.PHONY: all client server clean
