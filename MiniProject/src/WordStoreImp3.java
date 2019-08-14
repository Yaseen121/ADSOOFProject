
public class WordStoreImp3 implements WordStore {
	private WordStorage3[] wordStorage;
	private static int MAX_SIZE = 1000000;

	private int getKey(String word) {
		//Original hash key formula 
		/*char[] words = word.toCharArray();
		int key = 0;
		for (int i = 0; i < word.length(); i++) {
			key = key + (int) words[i];
		}
		return key%MAX_SIZE;*/
		int key = word.hashCode()%MAX_SIZE;
		if (key<0) {
			key = key * -1;
		}
		return key;
	}

	public WordStoreImp3(int n) {
		wordStorage = new WordStorage3[MAX_SIZE];
	}

	// ComapreTo ==> 0 if equal strings
	// ==> <0 if arg is alphabetically before
	// ==> >0 if arg is alphabetically after

	public void add(String word) {
		int key = getKey(word);
		// method that checks the key
		// if wordStorage[key] == null ==> add word
		// else if wordStorage[key] == word ==> increase count
		// else if wordStorage[key] != word && linkedWord == word ==> increase count
		// (use recursion to find linkedWord)
		// else if wordStorage[key] != word && linkedWord == null ==> add word to
		// linkedWord using recursion to find first linked null

		if (wordStorage[key] == null) {
			wordStorage[key] = new WordStorage3();
			wordStorage[key].word = word;
			wordStorage[key].count = 1;
			wordStorage[key].leftNode = null;
			wordStorage[key].rightNode = null;
		} else if (wordStorage[key].word.equals(word)) {
			wordStorage[key].count++;
		} else {
			addHelperMethod(word, wordStorage[key]);
		}
	}

	private void addHelperMethod(String word, WordStorage3 currentWord) {
		if (!currentWord.word.equals(word) && (currentWord.leftNode == null || currentWord.rightNode == null)) {
			if (currentWord.word.compareTo(word) > 0) {
				currentWord.leftNode = new WordStorage3();
				currentWord.leftNode.word = word;
				currentWord.leftNode.count = 1;
				currentWord.leftNode.rightNode = null;
				currentWord.leftNode.leftNode = null;
			} else {
				currentWord.rightNode = new WordStorage3();
				currentWord.rightNode.word = word;
				currentWord.rightNode.count = 1;
				currentWord.rightNode.rightNode = null;
				currentWord.rightNode.leftNode = null;
			}
		} else if (currentWord.word.equals(word)) {
			currentWord.count++;
		} else {
			if (currentWord.word.compareTo(word) > 0 && currentWord.leftNode!=null) {
				addHelperMethod(word, currentWord.leftNode);
			} else if (currentWord.rightNode!=null){
				addHelperMethod(word, currentWord.rightNode);
			}
		}
	}

	public int count(String word) {
		int key = getKey(word);
		WordStorage3 wordFound = findWord(wordStorage[key], word);
		if (wordFound == null) {
			// word not found
			return 0;
		} else {
			return wordFound.count;
		}
	}

	private WordStorage3 findWord(WordStorage3 currentWord, String wordToFind) {
		if (currentWord == null) {
			return null;
		} else if (!currentWord.word.equals(wordToFind) && currentWord.leftNode == null
				&& currentWord.rightNode == null) {
			return null;
		} else if (currentWord.word.equals(wordToFind)) {
			return currentWord;
		} else {
			if (currentWord.word.compareTo(wordToFind) > 0) {
				return findWord(currentWord.leftNode, wordToFind);
			} else {
				return findWord(currentWord.rightNode, wordToFind);
			}
		}
	}

	public void remove(String word) {
		int key = getKey(word);
		WordStorage3 wordFound = findWord(wordStorage[key], word);
		if (wordFound == null) {
			// Word not found
		} else {
			wordFound.count--;
			if (wordFound.count == 0) {
				if (wordFound.leftNode == null && wordFound.rightNode == null) {
					wordFound = null;
				} else if (wordFound.leftNode == null) {
					wordFound = wordFound.rightNode;
				} else if (wordFound.rightNode == null) {
					wordFound = wordFound.leftNode;
				} else {
					WordStorage3 min = wordFound.rightNode;
					min = minValue(min);
					wordFound.word = min.word;
					wordFound.count = min.count;
					min = null;
				}
			}
		}
	}

	private WordStorage3 minValue(WordStorage3 word) {
		if (word.leftNode == null)
			return word;
		else
			return minValue(word.leftNode);
	}

	private class WordStorage3 {
		private String word;
		private int count;
		private WordStorage3 leftNode;
		private WordStorage3 rightNode;

	}
}
//For the test add different numbers of initial words and test the times for add/removing/checking 10 000 words