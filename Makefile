JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = Body.java ColliderGUI.java CollisionWorker.java ParallelCollision.java SequentialCollision.java Worker.java

all: classes

default: classes

classes: 
	JC Body.java ColliderGUI.java CollisionWorker.java ParallelCollision.java SequentialCollision.java Worker.java

clean:
	$(RM) *.class