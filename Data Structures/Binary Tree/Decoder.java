package hw4228;

import java.util.Scanner;
import java.io.File;

public class Decoder {
    public String encodingString = "";
	public String bitString = "";
	public MsgTree root;
	
	
	//creating the encoding schema & bit string
	public Decoder(String inputFileName){
        try {
            File file = new File(inputFileName);
            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()){
                String line = scan.nextLine();

                //we are reading the bitstring
                if (line.charAt(0) == '1' || line.charAt(0) == '0'){
                    bitString += line;
                }
                //we are reading the encoding scheme
                else {
                    //we have already read a line -> there was a \n
                    if (encodingString != ""){
                        encodingString += '\n';
                    }
                    encodingString += line;
                }
            }

            scan.close();

        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
       		//System.out.println("String: "+encodingString);
            //System.out.println("BitString: "+bitString);

		root = new MsgTree(encodingString);
	}

    public static void main(String[] args){
	 //INPUT FILE:
	 Scanner scan = new Scanner(System.in);
     System.out.println("Enter file: ");
	 String fileName = scan.next();

	 //automatically constructs MsgTree when decoding file
	 Decoder decoder = new Decoder(fileName);
	 System.out.println("");

	 //print output of printCodes
	 System.out.println("charater   code");
	 System.out.println("-----------------");
	 MsgTree.printCodes(decoder.root, "");
     System.out.println("");
     
	 //print output of decode()
	 System.out.println("MESSAGE:");
	 decoder.root.decode(decoder.root, decoder.bitString);
	
	 scan.close();
	}
}
