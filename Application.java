
public class Application {

	public static void main(String[] args) {
		
		Cube44 cube = new Cube44();
		System.out.println(cube);
		System.out.println(cube.isSolved());
		cube.ZZinv();
		System.out.println(cube);
		//cube.executeSeq("F2MUF2");
		//cube.executeSeq("L'U2LUL'ULRU2 R' U' R U' R'");
		//cube.executeSeq("F'MU'F2MD2F2MUMD'F");
		//System.out.println(cube);
	}
	
	public static void testColors() {
		System.out.println("\033[0m BLACK");
        System.out.println("\033[31m █");
        System.out.println("\033[32m █");
        System.out.println("\033[33m YELLOW");
        System.out.println("\033[34m BLUE");
        System.out.println("\033[35m MAGENTA");
        System.out.println("\033[36m CYAN");
        System.out.println("\033[37m WHITE");
        System.out.println("\033[0m");
	}
	
	public static void testCube() {
		Cube44 cube = new Cube44();
		System.out.println(cube);
		cube.Finv();
		System.out.println(cube);
		cube.tdown(0);
		System.out.println(cube);
		cube.tdown(3);
		System.out.println(cube);
		cube.tdown(3);
		System.out.println(cube);
		cube.tdown(0);
		cube.tup(0);
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
