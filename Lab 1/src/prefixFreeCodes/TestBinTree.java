package prefixFreeCodes;
import java.util.ArrayList;
import java.util.Arrays;

public class TestBinTree {

	public static void main(String[] args) {
		
		//data for testing constructor-convert:
		//1) Valid, sorted
		String[] d1={"0", "10", "11"};
		
		//2) Valid, unsorted
		String[] d9={"110","0", "111", "10"};//unsorted d2
		
		//3)InValid
		//3.1: Not binary. "Invalid argument!" expected
		String[] d10={"0", "109", "11b"}; //sorted
		
		//3.2: Duplicate codeword. “Prefix condition violated!” expected
		String[] d12={"1000", "0", "1000", "101"};
		
		//3.3: Not a prefix-free. “Prefix condition violated!” expected
		String[] d14={"0", "10", "11","01"};
		
		//data for testing encode and decode:
		//1) Valid sequence
		ArrayList<String> enc1= new ArrayList<>(Arrays.asList( new String[]{"c2", "c1", "c1","c0","c2","c2","c0"} ));
		String dec1 = "111010011110";
		
		
		/*** NEW TEST CASES****/
		String [] t1 = {"0", "10"};
		BinTree t1_bintree = new BinTree(t1);
		ArrayList<String> t1_encode= new ArrayList<>(Arrays.asList( new String[]{"c1", "c0", "c0","c0","c1","c0", "c0","c0", "c1"} ));
		String t1_decode = "100001000010a";
		
		
		String [] t2 = {}; // empty array
		BinTree t2_bintree = new BinTree(t2);
		
		String [] t3 = {"0", "1"}; // sorted
		BinTree t3_bintree = new BinTree(t3);
		ArrayList<String> t3_encode= new ArrayList<>(Arrays.asList( new String[]{"c0", "c1", "c0","c0"}));
		
		String [] t4 = {"1", "0"}; // unsorted
		BinTree t4_bintree = new BinTree(t4);
		ArrayList<String> t4_encode= new ArrayList<>(Arrays.asList( new String[]{"c0", "c1", "c0","c0"}));
		
		
		//----------------------------------- Test Starts Here------------------------------------
		System.out.println("---------------------**TEST BEGINS**--------------------------------" );
		//______________________________________________________________________________
		System.out.println("\nSECTION 1: Testing BinTree()" );
		
		// TEST 0
		System.out.println("--------Test 0: Valid, Empty Tree------------" );
		System.out.println("Output: " );
		t2_bintree.printTree();
		System.out.println("\nExpect: \n" );
		
		// TEST 1
		System.out.println("--------Test 1: Valid, Sorted------------" );
		BinTree a1 = new BinTree(d1);
		System.out.println("Output: " );
		a1.printTree();
		System.out.println("\nExpect: \nc0 I c1 I c2" );
			
		// TEST 2
		System.out.println("--------Test 2: Valid, Unsorted------------" );
		BinTree a9 = new BinTree(d9);
		System.out.println("Output: " );
		a9.printTree();
		System.out.println("\nExpect: \nc1 I c3 I c0 I c2" );
		
		// TEST 3
		System.out.println("--------Test 3: Invalid, Not binary------------" );
		try {
		BinTree a10 = new BinTree(d10);
		}
		catch (Exception e) {
			System.out.println("Message: "+e.getMessage());
		}
		
		// TEST 4
		System.out.println("--------Test 4------------" );
		try {
			BinTree a10 = new BinTree(d12);
		}
		catch (Exception e) {
			System.out.println("Message: "+e.getMessage());
		}
		
		// TEST 5
		System.out.println("--------Test 5------------" );
		try {
		BinTree a10 = new BinTree(d14);
		}
		catch (Exception e) {
			System.out.println("Message: "+e.getMessage());
		}
		
		
		System.out.println("----------------------------------------------------" );
		System.out.println("Testing BinTree() finished!" );
		System.out.println("----------------------------------------------------" );
		//______________________________________________________________________________
		System.out.println("\nSECTION 2: Testing height()" );
		
		// TEST 6
		System.out.println("--------Test 6------------" );
		int h1=a1.height();
		System.out.println("The height is: "+h1);
		//___________________________________________________________________
		System.out.println("----------------------------------------------------" );
		System.out.println("Testing height() finished!" );
		System.out.println("----------------------------------------------------" );
		
		
		//______________________________________________________________________________
		System.out.println("\nSECTION 3: Testing getCodewords()" );
		
		// TEST 7
		System.out.println("--------Test 7------------" );

		String lt1=a1.getCodewords().toString();
		System.out.println("Output: "+lt1);
		String d111=Arrays.toString(d1);
		System.out.println("Expect: "+d111);
		
		
		
		//___________________________________________________________________
		System.out.println("----------------------------------------------------" );
		System.out.println("Testing getCodewords() finished!" );
		System.out.println("----------------------------------------------------" );
		System.out.println("\nSECTION 4: Testing convert()" );
		
		// TEST 8
		System.out.println("--------Test 8------------" );
		String[] con1=a1.convert();
		System.out.println("Output: "+Arrays.toString(con1));
		String exp1="[null, I, c0, I, null, null, c1, c2]";
		System.out.println("Expect: "+exp1);
		
		
		
		//String[] strArray = arrayList.toArray(new String[arrayList.size()]);
		//___________________________________________________________________
		System.out.println("----------------------------------------------------" );
		System.out.println("Testing convert() finished!" );
		System.out.println("----------------------------------------------------" );
		System.out.println("\nSECTION 5: Testing encode()" );
		
		// TEST 9
		System.out.println("--------Test 9------------" );
		String encod1=a1.encode(enc1);
		System.out.println("Output: " +encod1);
		System.out.println("Expect: 111010011110");
		
		// TEST 10
		System.out.println("--------Test 10------------" );
		String t1_enc =t1_bintree.encode(t1_encode);
		System.out.println("Output: " +t1_enc);
		System.out.println("Expect: 100001000010");
		
		// TEST 11
		System.out.println("--------Test 11------------" );
		String t3_enc =t3_bintree.encode(t3_encode);
		System.out.println("Output: " +t3_enc);
		System.out.println("Expect: 0100");
				
		// TEST 12
		System.out.println("--------Test 12------------" );
		String t4_enc =t4_bintree.encode(t4_encode);
		System.out.println("Output: " +t4_enc);
		System.out.println("Expect: 1011");
		
		//___________________________________________________________________
		System.out.println("----------------------------------------------------" );
		System.out.println("Testing encode() finished!" );
		System.out.println("----------------------------------------------------" );
		System.out.println("\nSECTION 6: Testing decode()" );

		// TEST 13
		System.out.println("--------Test 13------------" );

		ArrayList<String> decod1=a1.decode(dec1);
		System.out.println("Output: " +decod1.toString());
		System.out.println("Expect: " +enc1.toString());
		
		// TEST 14
		System.out.println("--------Test 14------------" );
		try {
			ArrayList<String> t1_dec=t1_bintree.decode(t1_decode);
			System.out.println("Output: " +t1_dec.toString());
			System.out.println("Expect: " +t1_encode.toString());

		}
		catch (Exception e) {
			System.out.println("Message: "+e.getMessage());
		}
		
		
		//_________________________________________________________________________________________________________
		System.out.println("----------------------------------------------------" );
		System.out.println("Testing decode() finished!" );
		System.out.println("----------------------------------------------------" );
		System.out.println("\nSECTION 7: Testing toString()" );
		
		System.out.println("--------Test 13------------" );

		String s9=a9.toString();
		System.out.println("Output: " +s9);
		System.out.println("Expect: " +"0 10 110 111 ");
		
		
		System.out.println("\n-----------------------------Testing Done!-------------------------------------");

	}

}