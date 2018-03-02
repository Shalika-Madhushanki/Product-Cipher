import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Decryption {
	private ArrayList<String> substEncriptedStr;
	int key;
	private String permuteCode = "3102";

	public Decryption(int key) {
		this.key = key;
	}

	/*
	 * given character is mapped to original alphabets which has shifted by i value.
	 * This value "i" varies for each character in the string.
	 */
	public char alphabetAD(char c, int i) {
		int ascii = (int) c;
		ascii = ascii + i;
		char character = (char) ascii;
		return character;
	}

	public String shiftNum(String s) {
		String Str = s;
		Str = Str.charAt(Str.length() - 1) + Str.substring(0, Str.length() - 1);

		return Str;
	}

	/*
	 * Substitution decryption is done under this method
	 */
	public ArrayList<String> SubstitutionDecryption() {

		FileHandle filehandler = new FileHandle();
		ArrayList<String> substEncriptedStr = filehandler.readFile("Encripted.txt");
		ArrayList<String> outputLst = new ArrayList<String>();

		for (int i = 0; i < substEncriptedStr.size(); i++) {
			String encriptedStr = "";
			String fileInput = "";
			fileInput = substEncriptedStr.get(i);
			for (int j = 0; j < fileInput.length(); j++) {
				char c = fileInput.charAt(j);
				int Intkey = key;
				int num1 = Intkey % 10;
				Intkey = Intkey / 10;
				int num2 = Intkey % 10;
				Intkey = Intkey / 10;
				int num3 = Intkey % 10;
				Intkey = Intkey / 10;
				int num4 = Intkey % 10;

				if (j % 3 == 0) {
					int ele0 = (num1 + num2 + num3 + num4) % 10;
					encriptedStr += alphabetAD(c, ele0);
				} else if (j % 3 == 1) {
					int ele1 = (num1 + num2 + num3 + num4) % 3;
					encriptedStr += alphabetAD(c, ele1);
				} else {
					int ele2 = (num1 + num2 + num3 + num4) % 7;
					encriptedStr += alphabetAD(c, ele2);
				}

			}
			System.out.println("Substitution decrypted Message : " + encriptedStr);
			outputLst.add(encriptedStr);
		}

		return outputLst;

	}

	public String permutation(String s) {
		String buildedStr = "@#$%";
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

		String s1 = buildedStr.replace(Character.toString(buildedStr.charAt(num1)), Character.toString(s.charAt(0)));
		String s2 = s1.replace(Character.toString(s1.charAt(num2)), Character.toString(s.charAt(1)));
		String s3 = s2.replace(Character.toString(s2.charAt(num3)), Character.toString(s.charAt(2)));
		String s4 = s3.replace(Character.toString(s3.charAt(num4)), Character.toString(s.charAt(3)));

		return s4;

	}

	/* This method does the Transposition encryption */
	public void TranspositionDecription() {

		ArrayList<String> inputLst = SubstitutionDecryption();
		ArrayList<String> outputLst = new ArrayList<String>();
		for (int y = 0; y < inputLst.size(); y++) {
			String transString = "";
			String substutedString = inputLst.get(y);
			for (int k = 0; k < ((substutedString.length()) / 4) + 1; k++) {
				String subStr = "";
				if (((substutedString.length()) % 4) == 0 && k == ((substutedString.length()) / 4)) {
					continue;
				}
				if (((k * 4) + 4) <= substutedString.length()) {
					subStr = substutedString.substring(k * 4, (k * 4) + 4);

				} else if (((k * 4) + 4) - substutedString.length() < 4) {
					subStr = substutedString.substring(k * 4, substutedString.length());
					for (int l = 0; l < ((k * 4) + 4) - substutedString.length(); l++) {
						subStr += " ";
					}
				}
				transString += permutation(subStr);
			}
			System.out.println("Transpositively Decrypted Message : " + transString);
			outputLst.add(transString);

		}
		FileHandle fhandler = new FileHandle();
		fhandler.writeFile("Output.txt", outputLst);
		String outputstr = "";
		for (int k = 0; k < outputLst.size(); k++) {
			String str = outputLst.get(k);
			outputstr += str + "\n";
		}
		JOptionPane.showMessageDialog(null, outputstr);

	}
}
