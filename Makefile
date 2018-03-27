JFLAGS = -g


all: classes

default: classes

classes: 
	javac Body.java ColliderGUI.java CollisionWorker.java ParallelCollision.java SequentialCollision.java Worker.java

clean:
	$(RM) *.class