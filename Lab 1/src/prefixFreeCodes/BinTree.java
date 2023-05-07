package prefixFreeCodes;
import java.util.ArrayList;
import java.lang.String;
import java.lang.Math;

/**** Written by OLUKEMI ODUJINRIN ****/
/**** COMPENG 3SM4 Winter 2023 ****/

public class BinTree {
	
	/**********************************************PRIVATE METHODS**************************************************/
	private TNode root; //instance field, referencing variable type TNode
	
	private boolean isPrefixFree(String[] a) {
		
		for (int i = 0; i < a.length; i++) { // i represents current selected codeword, for example a[0]
			int i_len = a[i].length(); // store the length of selected codeword
			for (int x = 0; x < a.length; x++) { // x represents the rest of the codewords in the array
				int x_len = a[x].length();
				if (i != x) { // don't compare selected codeword with itself
					if (i_len > x_len) {
						// we know this comparison is prefix free
					} else {
						if (a[i].equals(a[x].substring(0, i_len))) {
							return false;
						}
					}
					
				}
			}
		}
		
		return true;
	}
	
	private boolean isBinaryString(String[] a) {
		
		for (int i = 0; i < a.length; i++) { // length: # of elements in array
			for (int b = 0; b < a[i].length(); b++) { // length(): # of characters in string
				if (a[i].charAt(b) != '0' && a[i].charAt(b) != '1') { //if a string element has a character that is not 0 or 1
					return false; 
				}
			}
		}
		
		return true;
	}
	
	
	private int getHeight(TNode t) {
		
		if(t == null) { // if the root is null, height is -1
			return -1;
		} else {
			if (getHeight(t.right) > getHeight(t.left)) { // if the height to the right is > than the left
				return getHeight(t.right) + 1;
			} else {
				return getHeight(t.left) + 1; // increment by 1 i.e height++
			}
		}
		
	}
	
	private String[] getConvert(TNode t, int i, String[] arr) {
		
			if (t.left != null) { // if there is a node on the left
				getConvert(t.left, 2*i, arr); // convert left node, pass string array
			} else if (2*i < Math.pow(2, getHeight(t) + 1)) { // (max # of nodes in a tree with height h)
				arr[2*i] = null; // set to null only if it fits in the array (its array position is less than array size)
			}
			
			if (t.right != null) { // if there is a node on the right
				getConvert(t.right, 2*i + 1, arr); // convert right node, pass string array
			} else if (2*i + 1 < Math.pow(2, getHeight(t) + 1)) { // (max # of nodes in a tree with height h)
				arr[2*i + 1] = null; // set to null only if it fits in the array (its array position is less than array size)
			}
			
			if (t.data == null) { // if an internal node
				arr[i] = "I";
			} else { // if leaf, alpha symbol
				arr[i] = t.data;
			}
			
		return arr; // return array
	}
	
	private ArrayList<String> getMyCodewords(TNode t, String c, ArrayList<String> codes) { // takes the current node and the current code
		
		if (t.left != null) { // if there is a node on the left
			getMyCodewords(t.left, c+"0", codes); // go left and add '0' to the current code
		}
		if (t.right != null) { // if there is a node on the right
			getMyCodewords(t.right, c+"1", codes); // go right and add '1' to the current code
		}
		if (t.data != null) { // leaf and end of codeword
			codes.add(c+t.data); // add path & symbol as a string to array list (i.e 1100101c3) - to easily implement encode as well 
		}
		
		return codes;	
	}
	
	private void printTree(TNode t) {
		
		if (t != null) { // if not at the end of tree
			printTree(t.left); // print nodes on the left
			if (t.data == null) { // if the data is null
				System.out.print("I "); // print I to represent internal node
			} else {
				System.out.print(t.data + " "); // print data
			}
			printTree(t.right); // print nodes on the right
		}
	}
	
	/**********************************************PUBLIC METHODS**************************************************/
	public BinTree(String[] a) throws IllegalArgumentException {
		
		if (!isBinaryString(a)) { // if any element in string array is not a binary string
			throw new IllegalArgumentException("Invalid argument!");
			
		} else if (!isPrefixFree(a)) { // if not a prefix free code
			throw new IllegalArgumentException("Prefix condition violated!");
			
		} else { //create binary tree of codeword
			if (a.length == 0) { // empty array
				this.root = null; // null
			} else {	
				this.root = new TNode(null, null, null);
				for (int i = 0; i < a.length; i++) { // loop through string array
					TNode t = this.root; // set root to dummy variable t
					for (int b = 0; b < a[i].length(); b++) { // go through each bit in specified codeword
						if (a[i].charAt(b) == '0') {  // if the bit is 0
							if (t.left == null) { // if there is no node on the left
								if (b == a[i].length()-1) { // check if leaf (last bit in codeword)
									t.left = new TNode("c" + i, null, null);
								} else { // internal node
									t.left = new TNode(null, null, null);
									t = t.left; //  move t to the newly created left node
								}
							} else {// there is a node (internal)
								t = t.left;
							}
						} else if (a[i].charAt(b) == '1') { // if the bit is 1
							if (t.right == null){ // if there is no node on the left
								if (b == a[i].length()-1) { // check if leaf (last bit in codeword)
									t.right = new TNode("c" + i, null, null);
								} else {
									t.right = new TNode(null, null, null); 
									t = t.right; // move t to the newly created right node
								}
							} else {
								t = t.right; // there is a node (internal)
							}
						}

					}
				}
			}
				
		}
	}
	
	public void printTree() {
		printTree(this.root); // call printTree()
	}
	
	public int height() {

		if (this.root == null) { // if tree is empty
			return -1;
		} 
		
		return getHeight(this.root); // call private method getHeight()
	
	}
	
	public ArrayList<String> getCodewords() {

		String c = "";
		ArrayList<String> codewords = new ArrayList<String>(); // pass an empty array list to recursively append to

		
		codewords = getMyCodewords(this.root, c, codewords); // reassign the codewords (bits+symbol) from private method getMyCodewords()
		
		ArrayList<String> myCodewords = new ArrayList<String>(); // this will be used to store the extracted codewords (bits)
		
		// i.e codewords.get() = 010c2, bit stream: 010, alpha symbol: c2 (this is to cover the case the array passed to create the bin tree is unsorted
		for (int i = 0; i < codewords.size(); i++) { // got through codewords (bits+symbol)
			int index_c = codewords.get(i).indexOf('c'); // use the index of c to determine where to stop the substring
			myCodewords.add(codewords.get(i).substring(0,index_c)); // extract the bit stream only and append
		}
				
		return myCodewords;
		
	}
	
	public String[] convert() {
		String arr[] = {}; // define empty array
	
		if (this.root == null) { // if empty tree
			return arr; // return empty string array
		}
		arr = new String[(int) Math.pow(2, getHeight(this.root) + 1)]; //allocate memory space for max # of nodes in a tree with height h
		arr[0] = null; // first element is null
		int i = 1; // start to insertion at index 1
	
		return getConvert(this.root, i, arr); // call and return getConvert()
		
	}
	
	public String encode(ArrayList<String> a) {
	
		String bitstream = "";
		
	    ArrayList<String> codewords = new ArrayList<String>();
	    codewords = getMyCodewords(this.root, "", codewords); // get codewords in lexicographical order
		
		if (this.root == null) { // if tree is empty
			return "";
		} else {
			// i.e codewords.get(0) = 010c2, bit stream: 010, alpha symbol: c2 (this is to cover the case the array passed to create the bin tree is unsorted
			for (int i = 0; i < a.size(); i++) { // get each alpha symbol
				for (int x = 0; x < codewords.size(); x++) { // search through codewords
					int index_c = codewords.get(x).indexOf('c'); // get the index of c - this will help compare the symbols
					if (codewords.get(x).substring(index_c).equals(a.get(i))) { // if the symbol in a matches the one in the codewords array
						bitstream += codewords.get(x).substring(0, index_c); // add just the bit stream associated with it - similar to getcodewords()
					}
				}
			}
		}
		
		return bitstream;
	
	}
	
	public ArrayList<String> decode(String s) throws IllegalArgumentException{
		
		ArrayList<String> sequence = new ArrayList<String>();
		TNode t = this.root;
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '0'|| s.charAt(i) == '1') {
				if (s.charAt(i) == '0' && t.left != null) { // if the bit is 0 and there is a left node
					if (t.left.data != null) { // if a leaf
						sequence.add(t.left.data);
						t = this.root; // go back to the root
					} else { // if internal node
						if (i == s.length()-1) {
							throw new IllegalArgumentException("Sequence can't be parsed!");
						} else {
							t = t.left;  // go left
						}
					}
				} else if (s.charAt(i) == '1' && t.right != null){ // if the bit is 1 and there is a right node
					if (t.right.data != null) { // if a leaf
						sequence.add(t.right.data);
						t = this.root; // go back to the root
					} else { // if internal node
						if (i == s.length()-1) { // invalid sequence
							throw new IllegalArgumentException("Sequence can't be parsed!");
						} else {
							t = t.right; // go right
						}
					}
				} else { // there's no node
					throw new IllegalArgumentException("Sequence can't be parsed!");
				}
			} else {
				throw new IllegalArgumentException("Sequence can't be parsed!"); // if not binary
			}
		}
		
		
		return sequence;
		
	}
	
	public String toString() {
		
		String code = "";
		
		if (this.root == null) { // if empty
			return ""; // return an empty string
		}
		
		ArrayList<String> codewords = getCodewords();
		
		for (int i = 0; i < codewords.size(); i++) {
			code += codewords.get(i) + " "; // at to code, should already be ordered
		}
	
		return code;
	}

	public static void main(String[] args) {
		
		// USED TO TEST INDIVIDUAL METHODS :)
		/*
		String [] codewords = {"0", "10", "11"};
		BinTree myBinTree = new BinTree(codewords);
		ArrayList<String> enc1= new ArrayList<>(Arrays.asList( new String[]{"c2", "c1", "c1","c0","c2","c2","c0"} ));
		String dec1 = "1101001a11101";
		
		
		String [] codewords1 = {"00", "01", "100", "101"};
		BinTree myBinTree1 = new BinTree(codewords1);
		ArrayList<String> enc11= new ArrayList<>(Arrays.asList( new String[]{"c3", "c3", "c2","c0","c1","c3","c0"} ));
		String dec11 = "110100111101";

		
		System.out.println("\nSECTION 3: Testing getCodewords()" );
		
		// TEST 7
		System.out.println("--------Test 7------------" );

		String lt1=myBinTree1.getCodewords().toString();
		System.out.println("Output: "+lt1);
		String d111=Arrays.toString(codewords1);
		System.out.println("Expect: "+d111);
		
		
		System.out.println("\nTesting decode()" );
		System.out.println("----------------------------------------------------" );
		try {
			ArrayList<String> decod1= myBinTree.decode(dec1);
			System.out.println("Output: " + decod1.toString());
			System.out.println("Expect: " + enc1.toString());

		}
		catch (Exception e) {
			System.out.println("Message: "+e.getMessage());
		}
		*/
		
	}

}

