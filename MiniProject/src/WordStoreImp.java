public class WordStoreImp implements WordStore {
	private WordStorage[] wordStorage;
	private static int MAX_SIZE = 1000000;
	
	public WordStoreImp(int n) {
		wordStorage = new WordStorage[MAX_SIZE];
	}
	
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

	public void add(String word) {
		int key = getKey(word);
		if (wordStorage[key] == null) {
			wordStorage[key] = new WordStorage(word);
		} else if (wordStorage[key].word.equals(word)) {
			wordStorage[key].count++;
		} else {
			addHelperMethod(word, wordStorage[key]);
		}
	}

	private void addHelperMethod(String word, WordStorage currentWord) {
		if (!currentWord.word.equals(word) && (currentWord.leftNode == null || currentWord.rightNode == null)) {
			if (currentWord.word.compareTo(word) > 0) {
				currentWord.leftNode = new WordStorage(word);
			} else {
				currentWord.rightNode = new WordStorage(word);
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
		WordStorage wordFound = findWord(wordStorage[key], word);
		if (wordFound == null) {
			// word not found
			return 0;
		} else {
			return wordFound.count;
		}
	}

	private WordStorage findWord(WordStorage currentWord, String wordToFind) {
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
		WordStorage wordFound = findWord(wordStorage[key], word);
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
					WordStorage min = wordFound.rightNode;
					min = minValue(min);
					wordFound.word = min.word;
					wordFound.count = min.count;
					min = null;
				}
			}
		}
	}

	private WordStorage minValue(WordStorage word) {
		if (word.leftNode == null) {
			return word;
		} else {
			return minValue(word.leftNode);
		}
	}

	private class WordStorage {
		private String word;
		private int count;
		private WordStorage leftNode;
		private WordStorage rightNode;
		
		WordStorage(String w){
			word = w;
			count = 1;
			leftNode = null;
			rightNode = null;
		} 

	}
}


