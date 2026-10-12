#!/bin/sh
cd "$(dirname "$0")/src"
javac *.java && java Main
