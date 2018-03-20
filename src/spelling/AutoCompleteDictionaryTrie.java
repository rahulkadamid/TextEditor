package spelling;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
		if (word.length() == 0){
			return false;
		}
		
		if (isWord(word) == true){
			return false;
		}
		
		String lowerCaseWord = word.toLowerCase();
		int wordLength = lowerCaseWord.length();
		char[] letters = lowerCaseWord.toCharArray();
		TrieNode node = root;
					
		for (int i = 0; i < wordLength; i++){
			TrieNode newNode = node.insert(letters[i]);
			if (newNode == null){
				node = node.getChild(letters[i]);
			}else{
				node = newNode;
			}
		}
		
		node.setEndsWord(true);
		size++;
		return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size(){
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
	    
		String lowerCaseWord = s.toLowerCase();
		int length = s.length();
		
		if (length == 0){
			return false;
		}
		
		char[] letters = lowerCaseWord.toCharArray();
		TrieNode node = root;
		
		for (int j = 0; node != null && j < length; j++){
			node = node.getChild(letters[j]);
		}
		
		if (node == null){
			return false;
		}else if (node.endsWord()){
			return true;
		}
		return false;
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // This method implements the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 String lowerCaseWord = prefix.toLowerCase();
    	 int wordLength = lowerCaseWord.length();
 		 char[] letters = lowerCaseWord.toCharArray();
 		 TrieNode node = root;
 		 
 		 List<TrieNode> completionNodes = new LinkedList<TrieNode>();
 		 List<String> completions = new ArrayList<String>();
 					
 		 for (int j = 0; node != null && j < wordLength; j++){
			 node = node.getChild(letters[j]);
		 }
		
		 if (node == null){
			 return completions;
		 }
		 
		 completionNodes.add(node);
    	 
		 while (!completionNodes.isEmpty() && 
				 completions.size() != numCompletions){
			 TrieNode firstNode = completionNodes.remove(0);

			 if (firstNode.endsWord()){
				 completions.add(firstNode.getText());
			 }
			 for (Character c : firstNode.getValidNextCharacters()){
				 completionNodes.add(firstNode.getChild(c));
			 }
		 }
		 return completions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}