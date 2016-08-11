# Heuristic Search

<a href=https://en.wikipedia.org/wiki/Heuristic_(computer_science)>Heuristic search</a> is a type of search in which we do not have total information about the problem space, but still we can came up with a valid solution, or in the best cases, an optimal solution. This type of search uses <b>heuristic functions</b> in order to evaluate the different possible actions and select the best one (lower cost).<br>
<br>
In this particular case, the search will be applied to some Warcraft 3 maps written in .txt files.

## How does it work?
As our search problem is going to consist of reaching a map point from a starting point, each possible action is going to be a movement. These movements will be chosen depending on the map type of terrain distribution (each of the terrains has a specific cost). <b>Our goal is to reduce this cost</b>.

The maps possible terrains are: <b>Grass (".")</b>, <b>Sand ("S")</b>, <b>Trees ("T")</b>, <b>Water ("W")</b>, <b>Walls ("@")</b>.

## What is in the repository?
Project contents are divided into 3 different folders: <i>libs</i>, <i>maps</i> and <i>src</i>.

### 1. libs:
It contains the library used to perform the heuristic search: <a href=https://github.com/aimacode/aima-java><b>AIMA</b></a>.

### 2. maps:
It contains all the different <i>Warcraft 3</i> maps that can be used by the heuristic search program. <b>Additional maps could be created</b> if their corresponding heights and widths are specified as it is done in the already available ones, and they only contain the valid symbols stated before.

### 3. src:
It contains the source code. Once it is compiled, another folder will be created <b>("<i>out</i>")</b> where compiled files will be stored.

## Usage:
In order to initiate a search, we need to compile the source code using the corresponding <i>Makefile</i>, and access the folder.

```shell
$ make
$ cd out
```

Finally, in order to execute the program we need to pass <b>5 different parameters</b>:

<b>A) Path to the map.</b><br>
<b>B) Search algorithm:</b> <a href=https://en.wikipedia.org/wiki/A*_search_algorithm><i>Astar</i></a>, <a href=https://en.wikipedia.org/wiki/Best-first_search><i>GBFS</i></a>, <a href=https://en.wikipedia.org/wiki/Breadth-first_search><i>Breadth</i></a> or <a href=https://en.wikipedia.org/wiki/Depth-first_search><i>Depth</i></a>.<br>
<b>C) Heuristic:</b> <i>Manhattan</i>, <i>Euclidean</i> or <i>Chebyshev</i>.<br>
<b>D) Initial position.</b> Format: <i>PositionX-PositionY</i><br>
<b>E) Final position.</b> Format: <i>PositionX-PositionY</i>

Example:
```shell
$ java -cp .:../libs/aima-core.jar Execute ../maps/riverrun.map Astar Manhattan 90-36 380-280
```

<b>The output path is provided in a file having the same name as the input map with the <i>".out"</i> extension.</b> The path is marked using a line of "X" symbols. Additionally, a file containing search statistics is generated.

## Requirements:
This implementation is designed to work in any system with almost any version of <a href=http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html>Java JDK</a> installed.

