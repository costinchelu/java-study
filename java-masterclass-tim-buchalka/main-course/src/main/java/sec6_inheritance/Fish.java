package sec6_inheritance;


public class Fish extends Animal {

	private int gills;
	private int eyes;
	private int fins;

	public Fish(String name, int size, int weight, int gills, int eyes, int fins) {

		super(name, 1, 1, size, weight);
		this.gills = gills;
		this.eyes = eyes;
		this.fins = fins;
	}

	private void rest() {

	}

	@Override
	public void move(int speed)
	{
		System.out.println("Fish is now moving:");
		swim(speed);
		super.move(speed);
	}

	private void moveMuscles() {
		System.out.println("	Fish is moving its muscles");
	}

	private void moveBackFin() {
		System.out.println("	Fish is moving its back fin");
	}

	private void swim(int speed) {
		moveMuscles();
		moveBackFin();
		System.out.println("	Fish is swimming with a speed of " + speed);
	}
	
}
