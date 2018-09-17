
public class Body {
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	
	//create new body with parameters asdfgh, each corresponding to body variable
	public Body(double a,double s,double d,double f,double g,String h) {
		myXPos=a;
		myYPos=s;
		myXVel=d;
		myYVel=f;
		myMass=g;
		myFileName=h;
	}
	//create new body that's a copy of another body 'a'
	public Body(Body a) {
		myXPos=a.getX();
		myYPos=a.getY();
		myXVel=a.getXVel();
		myYVel=a.getYVel();
		myMass=a.getMass();
		myFileName=a.getName();
	}
	
	public double getX() {	//gets private x pos
		return myXPos;
	}
	public double getY() {	//gets private y pos
		return myYPos;
	}
	public double getXVel() {	//gets private x vel
		return myXVel;
	}
	public double getYVel() {	//gets private y vel
		return myYVel;
	}
	public double getMass() {	//gets private mass
		return myMass;
	}
	public String getName() {	//gets private name
		return myFileName;
	}
	
	//calculates distance between this body and body 'a'
	public double calcDistance(Body a) {
		double dx=a.getX()-myXPos;
		dx*=dx;
		double dy=a.getY()-myYPos;
		dy*=dy;
		double r=dx+dy;
		return Math.sqrt(r);
	}
	//calculates force exerted onto the body by body 'a'
	public double calcForceExertedBy(Body a) {
		double g=(6.67*Math.pow(10, -11));
		double m=(a.getMass()*myMass)/(calcDistance(a)*calcDistance(a));
		return g*m;
	}
	//calculates horizontal force exerted onto the body by body 'a'
	public double calcForceExertedByX(Body a) {
		double f=calcForceExertedBy(a);
		double dx=a.getX()-myXPos;
		return f*(dx/calcDistance(a));
	}
	//calculates vertical force exerted onto the body by body 'a'
	public double calcForceExertedByY(Body a) {
		double f=calcForceExertedBy(a);
		double dy=a.getY()-myYPos;
		return f*(dy/calcDistance(a));
	}
	//calculates net horizontal force exerted onto the body by body 'a'
	public double calcNetForceExertedByX(Body[] bodies) {
		double sum=0;
		for(Body b:bodies) {
			if(!b.equals(this)) {
				sum+=calcForceExertedByX(b);}
		} return sum;
	}
	//calculates net vertical force exerted onto the body by body 'a'
	public double calcNetForceExertedByY(Body[] bodies) {
		double sum=0;
		for(Body b:bodies) {
			if(!b.equals(this)) {
				sum+=calcForceExertedByY(b);}
		} return sum;
	}
	//mutator to update the body's position and velocity
	public void update(double dt, double xf, double yf) {
		double ax=xf/myMass;
		double ay=yf/myMass;
		double nvx=myXVel+dt*ax;
		double nvy=myYVel+dt*ay;
		double nx=myXPos+dt*nvx;
		double ny=myYPos+dt*nvy;
		myXPos=nx;
		myYPos=ny;
		myXVel=nvx;
		myYVel=nvy;
	}
	//creates multiple body objects with movement and connects them to visual files
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
	
	

}
