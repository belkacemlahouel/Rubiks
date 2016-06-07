
public class Application {

	public static void main(String[] args) {
		Cube44 cube = new Cube44();
		System.out.println(cube.toString());
		cube.tup(2);
		cube.tdown(2);
		System.out.println(cube.toString());
	}
}
