
public class WordStoreImp1 implements WordStore {
	
	private String[] words;
	private int[] count;
	private int size;
	private static int MAX_SIZE = 100000;
	
	public WordStoreImp1(int n) {
		size=0;
		words = new String[MAX_SIZE];
		count = new int[MAX_SIZE];
	}
	
	private int wordAlreadyIn(String word) {
		//Return index of word or -1 if not in array 
		for (int i=0; i<size; i++) {
			if (words[i].equals(word)) {
				return i;
			}
		}
		return -1;
	}
	
	public void add(String word) {
		int index = wordAlreadyIn(word);
		if (index!=-1) {
			count[index]++;
		} else {
			words[size]=word;
			count[size]=1;
			size++;
		}
	}

	public int count(String word) {
		int index = wordAlreadyIn(word);
		if (index!=-1) {
			return count[index];
		} else {
			return 0;
		}
	}

	public void remove(String word) {
		int index = wordAlreadyIn(word);
		if (index!=-1) {
			count[index]--;
			if (count[index]==0) {
				words[index]=words[size];
				count[index]=count[size];
				words[size]="";
				count[size]=0;
				size--;
			}
		}
	}

}
