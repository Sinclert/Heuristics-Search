# Heuristic Search project

CLASSPATH=./libs/aima-core.jar
SOURCEPATH=./src
OUTPUT=./out

# Main rule
all: makeDirectory compileJavas

# Create output folder
makeDirectory:
	@ mkdir -p ${OUTPUT}

# Compile all the .java files
compileJavas:
	@ javac -cp ${CLASSPATH} -sourcepath ${SOURCEPATH} ./src/Actionn.java -d ${OUTPUT}
	@ javac -cp ${CLASSPATH} -sourcepath ${SOURCEPATH} ./src/ActionCost.java -d ${OUTPUT}
	@ javac -cp ${CLASSPATH} -sourcepath ${SOURCEPATH} ./src/AvailableActions.java -d ${OUTPUT}
	@ javac -cp ${CLASSPATH} -sourcepath ${SOURCEPATH} ./src/Execute.java -d ${OUTPUT}
	@ javac -cp ${CLASSPATH} -sourcepath ${SOURCEPATH} ./src/GoalFunction.java -d ${OUTPUT}
	@ javac -cp ${CLASSPATH} -sourcepath ${SOURCEPATH} ./src/Heuristic_1.java -d ${OUTPUT}
	@ javac -cp ${CLASSPATH} -sourcepath ${SOURCEPATH} ./src/Heuristic_2.java -d ${OUTPUT}
	@ javac -cp ${CLASSPATH} -sourcepath ${SOURCEPATH} ./src/Heuristic_3.java -d ${OUTPUT}
	@ javac -cp ${CLASSPATH} -sourcepath ${SOURCEPATH} ./src/ResultAction.java -d ${OUTPUT}
	@ javac -cp ${CLASSPATH} -sourcepath ${SOURCEPATH} ./src/State.java -d ${OUTPUT}
