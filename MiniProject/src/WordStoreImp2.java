

public class WordStoreImp2 implements WordStore {
	private WordStorage2[] wordStorage;
	private int size;
	private static int MAX_SIZE = 100000;
	
	public int getKey(String word) {
		char[] words = word.toCharArray();
		int key =0;
		for (int i=0; i<words.length; i++) {
			key = key + (int)words[i];
		}
		return key;
	}
	
	//What to do with N?
	public WordStoreImp2(int n) {
		size=0;
		wordStorage = new WordStorage2[MAX_SIZE];
		//Initialise all to null;
	}
	
	public void add(String word) {
		int key = getKey(word);
		//method that checks the key
		//if wordStorage[key] == null ==> add word
		//else if wordStorage[key] == word ==> incrase count
		//else if wordStorage[key] != word && linkedWord == word ==> increase count (use recursion to find linkedWord)
		//else if wordStorage[key] != word && linkedWord == null ==> add word to linkedWord  using recursion to find first linked null
		
		if (wordStorage[key] == null) {
			wordStorage[key] = new WordStorage2();
			wordStorage[key].word = word;
			wordStorage[key].count = 1;
			wordStorage[key].linkedWord = null;
			size++;
		} else if (wordStorage[key].word.equals(word)) {
			wordStorage[key].count++;
		} else {
			addHelperMethod(word, wordStorage[key]);
		}
	}
	
	private void addHelperMethod(String word, WordStorage2 currentWord) {
		if (!currentWord.word.equals(word) && currentWord.linkedWord == null) {
			currentWord.linkedWord = new WordStorage2();
			currentWord.linkedWord.word=word;
			currentWord.linkedWord.count=1;
			currentWord.linkedWord.linkedWord=null;
			size++;
		} else if (currentWord.word.equals(word)) {
			currentWord.count++;
		} else {
			addHelperMethod(word, currentWord.linkedWord);
		}
	}

	public int count(String word) {
		int key = getKey(word);
		WordStorage2 wordFound = findWord(wordStorage[key], word);
		if (wordFound == null) {
			//word not found
			return 0;
		} else {
			return wordFound.count;
		}
	}
	
	public WordStorage2 findWord(WordStorage2 currentWord, String wordToFind) {
		if (currentWord!=null && !currentWord.word.equals(wordToFind) && currentWord.linkedWord==null) {
			return null;
		} else if (currentWord==null) {
			return null;
		} else if (currentWord.word.equals(wordToFind)) {
			return currentWord;
		} else {
			return findWord(currentWord.linkedWord, wordToFind);
		}
	}
	
	public void remove(String word) {
		int key = getKey(word);
		WordStorage2 wordFound = findWord(wordStorage[key], word);
		if (wordFound == null) {
			//Word not found
		} else {
			wordFound.count--;
			if (wordFound.count==0) {
				if (wordFound.linkedWord==null) {
					wordFound=null;
				}else {
					wordFound = wordFound.linkedWord;
				}
				size--;
			}
		}
	}


class WordStorage2 {
	//Make these private and make getters and setters
	String word;
	int count;
	WordStorage2 linkedWord;
	
}

}
