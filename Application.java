
/**
 * main application
 * modify in main algorithms calls
 */

public class Application {

	public static void main(String[] args) {
		
		testAnnealing();
		//testAnnealing2();

		//test solution...
		Cube44 cube = new Cube44();
		cube.executeSeq("MRzMUz'BB'F2UMDMLMDUB2MRL'MR2B2MDML2MD2MD2U2Lx'ML'MDUU2BMU'MD'x'MD2B2MD'MULMU2U2RU'xU'z'U2MDD'z'F'R'");
		System.out.println(cube);
		cube.executeSeq("L'y'LMLMLD'ML'ML'x'zzMR B' MU B MR'zzxx'zzR'DLD'RDL'D'zzxxy'MR2B2MR'U2MR'U2B2MR'B2MRB2MR'B2MR2B2yx'L'MLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2Lx'yzML'U2MR'D2MRU2MR'D2MRMLz'y'xzyyMDMR'MD'MRyyz'xy'MDMR'MD'MRyx'F'R U2 R' U' R U' R' L' U2 L U L' U LFMRU'LUMRU'L'UMR'MR'zzML2MR'D2MRUMR'D2MRU'ML2zzMRMR'R'UMRU'RUMR'U'MRMR'xz'U'L'U MR U'LU MR'zx'xzMR2 U' ML2 U MR2 U' ML2 Uz'x'yzzML MR' D ML' D' MR D ML D' ML'zzy'MLML'U MR U' ML U MR' U'ML'yzMRMULMU'MR'MUL'MU'z'y'yzzML MR' D ML' D' MR D ML D' ML'zzy'F'R'FU2F'RFR'U2RF'Fyz'z R U2 R' U' R U' R' L' U2 L U L' U L z'zy'z'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMR2R2MF2F2 U2MR2U2 MF2F2MR2R2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzD'y'MLMRMD2ML'U2 ML' U'L'U ML U'LMLMDML'MD'MFMUMF'MBMDMB'xzzF' MD' Fzzx'xy'U'L'U MR U'LU MR'yx'xzzR'B'MR'D2MR'D2MR'D2MR'D2MR'F2MLB2ML'F2MLB2ML'BRzzx'MDMB' MU' MD MB U2 MB' MU MD' MB U2MD'yyML MR' D ML' D' MR D ML D' ML'yyy'zML MR' D' MR D ML' D' MR' D MRz'yz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yMD2F'FRzR'yyB'R2U'RUMRU'R'UR'L'ML'DyyML'RMLx'zzD'F'BMBL'MU'R'MULMU'RMU FB'MB'zzxML'ML2MLzMLMR'R'UML'U'RUMLU'ML'MRz'x'y'z'F'R'MU'RU2R'MURU2Fzyxz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzz'yyMLU'MRD2MR'UML' U2 MLU'MRD2MR'UML' U2yyzMR'ML2MRU'B'BUB'yzMBU'MB'DMBUMB'z'y'ML'ML2MLFR2U'MB'DMBUMB'D'MBR2F'MF'R2U2MR'D'MRU2MR'D MRR RMFyzzF'R'MU'RU2R'MURU2Fzzy'y'zMLMR'R'UML'U'RUMLU'ML'MRz'yD'R'B'MR'D2MR'D2MR'D2MR'D2MR'F2MLB2ML'F2MLB2ML'BRDyzzR2MLU'MB'D'MBUMB'DMBML'R2zzy'yzzMF'F'MD'L'MDR'MD'LMDRMFFzzy'");
		System.out.println(cube);

		testTabu();
		
		//testShuffle();
		//testParseSeq();
		//testRandom();
	}
	
	public static void testAnnealing2() {
		Cube44 cube = new Cube44();
		
		cube.shuffle();
		System.out.println(cube);
		
		cube = SolutionAnnealing.solve(cube, 1);
		System.out.println(cube);
	}
	
	public static void testAnnealing() {
		Cube44 cube = new Cube44();
		System.out.println(cube);
		
		cube.shuffle();
		System.out.println(cube);
		
		cube = Annealing.solve2(cube, 0);
		System.out.println(cube);
		
		//cube = Annealing.solve2(cube, 1);
		//System.out.println(cube);
		
		/*cube = Annealing.solve2(cube, 2);
		System.out.println(cube);*/
	}
	
	public static void testShuffle() {
		Cube44 cube = new Cube44();
		
		System.out.println(cube);
		System.out.println("Shuffle : " +cube.shuffle1(50));
		
		System.out.println(cube);
	}
	
	public static void testRandom() {
		Cube44 cube = new Cube44();
		Algorithme tabou = new Algorithme();
		cube.shuffle();
		Cube44 res = new Cube44( tabou.randomAlgo(cube));
		
		System.out.println(cube);
		System.out.println(res);
	}
	
	public static void testTabu() {
		Cube44 cube = new Cube44();
		Algorithme tabou = new Algorithme();
		System.out.println(cube);
		System.out.println("Shuffle : " +cube.shuffle1(50));
		Cube44 res = new Cube44( tabou.tabuSearch(cube));
		
		System.out.println(cube);
		System.out.println(res);
	}
	
	public static void testParseSeq() {
		Cube44 cube = new Cube44();
		System.out.println(cube);
		cube.executeSeq("F2MUF2");
		cube.executeSeq("L'U2LUL'ULRU2 xx'xR' U' R U' R'");
		cube.executeSeq("F'MU'F2MD2F2MUMD'F");
		cube.executeSeq("MRB'R2U'RUMRU'R'UR'L'ML'DMR2B'R2U'RUMRU'R'UR'L'ML'D");
		System.out.println(cube);
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
