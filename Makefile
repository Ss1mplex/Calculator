# Makefile

# Directories
SRC_DIR := src
BIN_DIR := bin

# List of subdirectories
SUBDIRS := Client Server model

# Paths to source and object files
SRC_DIRS := $(addprefix $(SRC_DIR)/,$(SUBDIRS))
SRCS := $(foreach dir,$(SRC_DIRS),$(wildcard $(dir)/*.java))
OBJS := $(patsubst $(SRC_DIR)/%.java,$(BIN_DIR)/%.class,$(SRCS))

# Java compiler and options
JAVAC := javac
JAVAC_FLAGS := -d $(BIN_DIR)

# Targets
all: create_bin_dir $(OBJS)

create_bin_dir:
	mkdir -p $(BIN_DIR)

$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	$(JAVAC) $(JAVAC_FLAGS) $<

clean:
	rm -rf $(BIN_DIR)

.PHONY: all create_bin_dir clean
