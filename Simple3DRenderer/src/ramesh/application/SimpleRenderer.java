package ramesh.application;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class SimpleRenderer {

	List<Triangle> tris;
	
	public SimpleRenderer() {
		this.tris = new ArrayList<>();
		tris.add(new Triangle(new Vertex(100, 100, 100),
		                      new Vertex(-100, -100, 100),
		                      new Vertex(-100, 100, -100),
		                      Color.WHITE));
		tris.add(new Triangle(new Vertex(100, 100, 100),
		                      new Vertex(-100, -100, 100),
		                      new Vertex(100, -100, -100),
		                      Color.RED));
		tris.add(new Triangle(new Vertex(-100, 100, -100),
		                      new Vertex(100, -100, -100),
		                      new Vertex(100, 100, 100),
		                      Color.GREEN));
		tris.add(new Triangle(new Vertex(-100, 100, -100),
		                      new Vertex(100, -100, -100),
		                      new Vertex(-100, -100, 100),
		                      Color.BLUE));
	}
	
	List<Triangle> getNodes(){
		return this.tris;
	}
}

//3 co-ordinates
class Vertex {
	
	double x;
	double y;
	double z;
	
	public Vertex(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}

//traingle class
class Triangle {
	
    Vertex v1;
    Vertex v2;
    Vertex v3;
    Color color;
    
    Triangle(Vertex v1, Vertex v2, Vertex v3, Color color) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.color = color;
    }
}