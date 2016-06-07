
public class Application {

	public static void main(String[] args) {
		Cube44 cube = new Cube44();
		System.out.println(cube);
		cube.tright(3);
		System.out.println(cube);
		cube.tdown();
		System.out.println(cube);
	}
	
	public static void testFace() {
		Face44 face = new Face44(0);
		int x = 0;
		
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 4; ++j) {
				face.elems[i][j] = x++%10;
			}
		}
		
		System.out.println(face);
		face.turn(true);
		System.out.println(face);
		face.turn(false);
		System.out.println(face);
	}
}
