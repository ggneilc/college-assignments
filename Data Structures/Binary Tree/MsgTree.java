package hw4228;


public class MsgTree {
	public char payloadChar;
	public MsgTree left;
	public MsgTree right;
	private static int charIdx = 0; //for building tree
	
	
	//constructor for building the tree
	//we parse the string from the file in the decoder class
	public MsgTree(String encodingString) {
		
		//terminate at end of string
		if (charIdx >= encodingString.length())
			return;
		
		//creates msgtree node
		this.payloadChar = encodingString.charAt(charIdx);
		charIdx++;
		//we just made a leaf, go back to internal node
		if (this.payloadChar != '^')
			return;
		
		this.left = new MsgTree(encodingString);
		this.right = new MsgTree(encodingString);

		//System.out.println(this);
	}


	//constructor for single node with null children
	public MsgTree(char payloadChar) {
		this.payloadChar = payloadChar;
		left = null;
		right = null;
	}

	//preorder traversal that prints Character & bit code
	public static void printCodes(MsgTree root, String code){
		//base case -> we are at the leaf
		if (root.payloadChar != '^'){
			System.out.println(root.payloadChar + "   " + code);

            //after we return, we go back one sequence in the tree, 
            //meaning we have to undo the last bit added to 'code'
			code = code.substring(0, code.length()-1);
			return;
		}
		
        //add a 0 to the bit code if we go left, 1 if we go right
		printCodes(root.left, code += '0');
		printCodes(root.right, code += '1');
		
		
	}
	
	
	//starting the method starts at the root
	public void decode(MsgTree root, String bitString){
		//start at root
		MsgTree curr = root;
		
		//iterate through the bitstring
		for (int i=0; i<bitString.length()-1; i++){
			
			//if we are at an internal node, traverse
			if (curr.payloadChar == '^'){
				if (bitString.charAt(i) == '0')
					curr = curr.left;
				else
					curr = curr.right;
			}
			//if we are at a leaf, print char & go back to root
			//i will increment after this operation, but we do not
			//want to increase the index in the bitstring bc we are
			//not traversing so we counter i auto incrementing by 
			//decreasing by 1
			else {
				System.out.print(curr.payloadChar);
				curr = root;
				i--;
			}
		}
	}
	
	public String toString(){
		return "payload: "+payloadChar+" left: "+left.payloadChar+" right: "+right.payloadChar;
	}
}

