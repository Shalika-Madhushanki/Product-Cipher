import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Encryption {
	private ArrayList<String> inputString;
	private int key;
	private String permuteCode = "3102";
	

	/*
	 * given character is mapped to various alphabets shifted by i value. This value
	 * "i" varies for each character in the string.
	 */
	public char alphabetA(char c, int i) {
		int ascii = (int) c;
		ascii = ascii - i;
		char character = (char) ascii;
		return character;
	}

	public Encryption(int key) {
		this.key = key;
	}

	/*
	 * Substitution encryption is done under this method
	 */
	public void SubstitutionEncription(ArrayList<String> lst) {
		ArrayList<String> inputLst= TranspositionEncription(lst);
		ArrayList<String> outputLst= new ArrayList<String>(); 
		
		for (int x=0; x<inputLst.size(); x++) {
			String substString = "";
			String toSubstituteStr =inputLst.get(x);
			for (int j = 0; j < toSubstituteStr.length(); j++) {
				int Intkey = key;
				int num1 = Intkey % 10;
				Intkey = Intkey / 10;
				int num2 = Intkey % 10;
				Intkey = Intkey / 10;
				int num3 = Intkey % 10;
				Intkey = Intkey / 10;
				int num4 = Intkey % 10;
				char c = toSubstituteStr.charAt(j);
				/*
				 * According to the remainder of the division of the place value by 3 of the
				 * string, there are three cases in order to substitute 3 consecutive characters
				 */

				if (j % 3 == 0) {
					int ele0 = (num1 + num2 + num3 + num4) % 10;
					substString += alphabetA(c, ele0);
				} else if (j % 3 == 1) {
					int ele1 = (num1 + num2 + num3 + num4) % 3;
					substString += alphabetA(c, ele1);
				} else {
					int ele2 = (num1 + num2 + num3 + num4) % 7;
					substString += alphabetA(c, ele2);
				}
			}

			System.out.println("Substitution Encryption Message : " + substString);
			outputLst.add(substString);
		}
		
		
		FileHandle fhandler = new FileHandle();
		fhandler.writeFile("Encripted.txt", outputLst);
		JOptionPane.showMessageDialog(null, "Your message is successfully encrypted !");

	}

	/*
	 * This method is to interchange the characters in a block
	 */
	public String permutation(String s) {
		String buildedStr;

		int seckey = key;
		int keEle1 = seckey % 10;
		seckey = seckey / 10;
		int keEle2 = seckey % 10;
		seckey = seckey / 10;
		int keEle3 = seckey % 10;
		seckey = seckey / 10;
		int keEle4 = seckey % 10;
		int number = (keEle1 + keEle2 + keEle3 + keEle4) % 10;
		for (int i = 0; i < number; i++) {
			permuteCode = shiftNum(permuteCode);
		}

		int Intkey = Integer.parseInt(permuteCode);

		int num1 = Intkey % 10;
		Intkey = Intkey / 10;
		int num2 = Intkey % 10;
		Intkey = Intkey / 10;
		int num3 = Intkey % 10;
		Intkey = Intkey / 10;
		int num4 = Intkey % 10;

		char ele1 = s.charAt(num1);
		char ele2 = s.charAt(num2);
		char ele3 = s.charAt(num3);
		char ele4 = s.charAt(num4);

		buildedStr = String.valueOf(ele1) + String.valueOf(ele2) + String.valueOf(ele3) + String.valueOf(ele4);
		return buildedStr;

	}

	/*
	 * This method is to shift the elements in the code which is used to interchange
	 * elements in a block
	 */
	public String shiftNum(String s) {
		String Str = s;
		Str = Str.charAt(Str.length() - 1) + Str.substring(0, Str.length() - 1);
		return Str;
	}

	/* This method does the Transposition encryption */
	public ArrayList<String> TranspositionEncription(ArrayList<String> lst) {
		ArrayList<String> inputList= lst;
		ArrayList<String> outList= new ArrayList<String>();

		for(int t=0;t<inputList.size();t++ ) {
			String toTranspositionStr=inputList.get(t);
			String transpositionedStr = "";
			for (int k = 0; k < ((toTranspositionStr.length()) / 4) + 1; k++) {
				String subStr = "";
				if (((toTranspositionStr.length()) % 4) == 0 && k == ((toTranspositionStr.length()) / 4)) {
					continue;
				}
				if (((k * 4) + 4) <= toTranspositionStr.length()) {
					subStr = toTranspositionStr.substring(k * 4, (k * 4) + 4);

				} else if (((k * 4) + 4) - toTranspositionStr.length() < 4) {
					subStr = toTranspositionStr.substring(k * 4, toTranspositionStr.length());
					for (int l = 0; l < ((k * 4) + 4) - toTranspositionStr.length(); l++) {
						subStr += " ";
					}
				}
				transpositionedStr += permutation(subStr);
			}
			System.out.println("Transposition encripted Message : " + transpositionedStr);
			outList.add(transpositionedStr);
		
		}
		
		return outList;

	}
}
