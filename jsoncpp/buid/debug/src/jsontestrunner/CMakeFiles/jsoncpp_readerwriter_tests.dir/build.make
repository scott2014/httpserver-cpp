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

# Utility rule file for jsoncpp_readerwriter_tests.

# Include the progress variables for this target.
include src/jsontestrunner/CMakeFiles/jsoncpp_readerwriter_tests.dir/progress.make

src/jsontestrunner/CMakeFiles/jsoncpp_readerwriter_tests: bin/jsontestrunner_exe
src/jsontestrunner/CMakeFiles/jsoncpp_readerwriter_tests: bin/jsoncpp_test
	cd /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug/src/jsontestrunner && /usr/bin/python2 -B /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/src/jsontestrunner/../../test/runjsontests.py /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug/bin/jsontestrunner_exe /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/src/jsontestrunner/../../test/data

jsoncpp_readerwriter_tests: src/jsontestrunner/CMakeFiles/jsoncpp_readerwriter_tests
jsoncpp_readerwriter_tests: src/jsontestrunner/CMakeFiles/jsoncpp_readerwriter_tests.dir/build.make
.PHONY : jsoncpp_readerwriter_tests

# Rule to build all files generated by this target.
src/jsontestrunner/CMakeFiles/jsoncpp_readerwriter_tests.dir/build: jsoncpp_readerwriter_tests
.PHONY : src/jsontestrunner/CMakeFiles/jsoncpp_readerwriter_tests.dir/build

src/jsontestrunner/CMakeFiles/jsoncpp_readerwriter_tests.dir/clean:
	cd /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug/src/jsontestrunner && $(CMAKE_COMMAND) -P CMakeFiles/jsoncpp_readerwriter_tests.dir/cmake_clean.cmake
.PHONY : src/jsontestrunner/CMakeFiles/jsoncpp_readerwriter_tests.dir/clean

src/jsontestrunner/CMakeFiles/jsoncpp_readerwriter_tests.dir/depend:
	cd /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/src/jsontestrunner /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug/src/jsontestrunner /home/scott/cpp/httpserver-cpp/jsoncpp/jsoncpp/buid/debug/src/jsontestrunner/CMakeFiles/jsoncpp_readerwriter_tests.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : src/jsontestrunner/CMakeFiles/jsoncpp_readerwriter_tests.dir/depend

