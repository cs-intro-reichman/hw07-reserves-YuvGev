
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		// Your code goes here
		return str.substring(1);
	}

	public static char head(String str) {
		return str.charAt(0);
	}


	public static int levenshtein(String word1, String word2) {
		// Your code goes here
		if (word1.length() == 0) {
			return word2.length();
		}
		if (word2.length() == 0) {
			return word1.length();
		}
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		if (head(word1) == head(word2)) {
			return levenshtein(tail(word1), tail(word2));
		}
		int calculation = Math.min(Math.min(levenshtein(tail(word1), word2), levenshtein(word1, tail(word2))),
				levenshtein(tail(word1), tail(word2)));
		return 1 + calculation;
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		for (int i = 0; i < dictionary.length; i++){
			dictionary[i] = in.readLine();
		}

		// Your code here

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		// Your code goes here
		int distance;
		int min = 1000;
		String closeWord = "";
		for (int i = 0; i < dictionary.length; i++){
			distance = levenshtein(word, dictionary[i]);
			if (distance < min){
				min = distance;
				if (distance <= threshold){
					closeWord = dictionary[i];
				} else {
					closeWord = word;
				}
			}
		}
		return closeWord;
	}

}
