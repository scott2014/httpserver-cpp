# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 2.8

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list

# Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug

# Include any dependencies generated for this target.
include src/lib_json/CMakeFiles/jsoncpp_lib.dir/depend.make

# Include the progress variables for this target.
include src/lib_json/CMakeFiles/jsoncpp_lib.dir/progress.make

# Include the compile flags for this target's objects.
include src/lib_json/CMakeFiles/jsoncpp_lib.dir/flags.make

src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_reader.cpp.o: src/lib_json/CMakeFiles/jsoncpp_lib.dir/flags.make
src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_reader.cpp.o: ../../src/lib_json/json_reader.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug/CMakeFiles $(CMAKE_PROGRESS_1)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_reader.cpp.o"
	cd /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug/src/lib_json && /usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/jsoncpp_lib.dir/json_reader.cpp.o -c /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/src/lib_json/json_reader.cpp

src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_reader.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/jsoncpp_lib.dir/json_reader.cpp.i"
	cd /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug/src/lib_json && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/src/lib_json/json_reader.cpp > CMakeFiles/jsoncpp_lib.dir/json_reader.cpp.i

src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_reader.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/jsoncpp_lib.dir/json_reader.cpp.s"
	cd /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug/src/lib_json && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/src/lib_json/json_reader.cpp -o CMakeFiles/jsoncpp_lib.dir/json_reader.cpp.s

src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_reader.cpp.o.requires:
.PHONY : src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_reader.cpp.o.requires

src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_reader.cpp.o.provides: src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_reader.cpp.o.requires
	$(MAKE) -f src/lib_json/CMakeFiles/jsoncpp_lib.dir/build.make src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_reader.cpp.o.provides.build
.PHONY : src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_reader.cpp.o.provides

src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_reader.cpp.o.provides.build: src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_reader.cpp.o

src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_value.cpp.o: src/lib_json/CMakeFiles/jsoncpp_lib.dir/flags.make
src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_value.cpp.o: ../../src/lib_json/json_value.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug/CMakeFiles $(CMAKE_PROGRESS_2)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_value.cpp.o"
	cd /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug/src/lib_json && /usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/jsoncpp_lib.dir/json_value.cpp.o -c /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/src/lib_json/json_value.cpp

src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_value.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/jsoncpp_lib.dir/json_value.cpp.i"
	cd /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug/src/lib_json && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/src/lib_json/json_value.cpp > CMakeFiles/jsoncpp_lib.dir/json_value.cpp.i

src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_value.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/jsoncpp_lib.dir/json_value.cpp.s"
	cd /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug/src/lib_json && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/src/lib_json/json_value.cpp -o CMakeFiles/jsoncpp_lib.dir/json_value.cpp.s

src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_value.cpp.o.requires:
.PHONY : src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_value.cpp.o.requires

src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_value.cpp.o.provides: src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_value.cpp.o.requires
	$(MAKE) -f src/lib_json/CMakeFiles/jsoncpp_lib.dir/build.make src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_value.cpp.o.provides.build
.PHONY : src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_value.cpp.o.provides

src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_value.cpp.o.provides.build: src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_value.cpp.o

src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_writer.cpp.o: src/lib_json/CMakeFiles/jsoncpp_lib.dir/flags.make
src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_writer.cpp.o: ../../src/lib_json/json_writer.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug/CMakeFiles $(CMAKE_PROGRESS_3)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_writer.cpp.o"
	cd /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug/src/lib_json && /usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/jsoncpp_lib.dir/json_writer.cpp.o -c /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/src/lib_json/json_writer.cpp

src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_writer.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/jsoncpp_lib.dir/json_writer.cpp.i"
	cd /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug/src/lib_json && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/src/lib_json/json_writer.cpp > CMakeFiles/jsoncpp_lib.dir/json_writer.cpp.i

src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_writer.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/jsoncpp_lib.dir/json_writer.cpp.s"
	cd /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug/src/lib_json && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/src/lib_json/json_writer.cpp -o CMakeFiles/jsoncpp_lib.dir/json_writer.cpp.s

src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_writer.cpp.o.requires:
.PHONY : src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_writer.cpp.o.requires

src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_writer.cpp.o.provides: src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_writer.cpp.o.requires
	$(MAKE) -f src/lib_json/CMakeFiles/jsoncpp_lib.dir/build.make src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_writer.cpp.o.provides.build
.PHONY : src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_writer.cpp.o.provides

src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_writer.cpp.o.provides.build: src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_writer.cpp.o

# Object files for target jsoncpp_lib
jsoncpp_lib_OBJECTS = \
"CMakeFiles/jsoncpp_lib.dir/json_reader.cpp.o" \
"CMakeFiles/jsoncpp_lib.dir/json_value.cpp.o" \
"CMakeFiles/jsoncpp_lib.dir/json_writer.cpp.o"

# External object files for target jsoncpp_lib
jsoncpp_lib_EXTERNAL_OBJECTS =

lib/libjsoncpp.a: src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_reader.cpp.o
lib/libjsoncpp.a: src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_value.cpp.o
lib/libjsoncpp.a: src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_writer.cpp.o
lib/libjsoncpp.a: src/lib_json/CMakeFiles/jsoncpp_lib.dir/build.make
lib/libjsoncpp.a: src/lib_json/CMakeFiles/jsoncpp_lib.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --red --bold "Linking CXX static library ../../lib/libjsoncpp.a"
	cd /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug/src/lib_json && $(CMAKE_COMMAND) -P CMakeFiles/jsoncpp_lib.dir/cmake_clean_target.cmake
	cd /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug/src/lib_json && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/jsoncpp_lib.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
src/lib_json/CMakeFiles/jsoncpp_lib.dir/build: lib/libjsoncpp.a
.PHONY : src/lib_json/CMakeFiles/jsoncpp_lib.dir/build

src/lib_json/CMakeFiles/jsoncpp_lib.dir/requires: src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_reader.cpp.o.requires
src/lib_json/CMakeFiles/jsoncpp_lib.dir/requires: src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_value.cpp.o.requires
src/lib_json/CMakeFiles/jsoncpp_lib.dir/requires: src/lib_json/CMakeFiles/jsoncpp_lib.dir/json_writer.cpp.o.requires
.PHONY : src/lib_json/CMakeFiles/jsoncpp_lib.dir/requires

src/lib_json/CMakeFiles/jsoncpp_lib.dir/clean:
	cd /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug/src/lib_json && $(CMAKE_COMMAND) -P CMakeFiles/jsoncpp_lib.dir/cmake_clean.cmake
.PHONY : src/lib_json/CMakeFiles/jsoncpp_lib.dir/clean

src/lib_json/CMakeFiles/jsoncpp_lib.dir/depend:
	cd /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/src/lib_json /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug/src/lib_json /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug/src/lib_json/CMakeFiles/jsoncpp_lib.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : src/lib_json/CMakeFiles/jsoncpp_lib.dir/depend

