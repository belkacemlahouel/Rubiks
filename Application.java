
public class Application {

	public static void main(String[] args) {
		Cube44 cube = new Cube44();
		System.out.println(cube.toString());
		cube.tright(3);
		cube.tleft(3);
		cube.tright(3);
		cube.tleft(3);
		System.out.println(cube.toString());
	}
}
