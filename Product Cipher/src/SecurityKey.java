/*
 *This class is to set security key when the user provide the key, and using get method it can be used for the processing. 
 * */

public class SecurityKey {
	private static int seckey;
	
	
	public static int getSecurityKey() {
		System.out.println("key in get "+seckey);
		return seckey;
	}
	public static void setSecurityKey(int key) {
		System.out.println("key in set "+key);
		seckey=key;
	}
}
