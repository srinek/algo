package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//  https://leetcode.com/problems/longest-absolute-file-path/
/*
 * Suppose we abstract our file system by a string in the following manner:

The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

dir
    subdir1
    subdir2
        file.ext
The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:

dir
    subdir1
        file1.ext
        subsubdir1
    subdir2
        subsubdir2
            file2.ext
The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).

Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.

Note:
The name of a file contains at least a . and an extension.
The name of a directory or sub-directory will not contain a ..
Time complexity required: O(n) where n is the size of the input string.

Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.


 */
public class LongestAbsoluteFilePath {

	
	public static void main(String[] args) {
		LongestAbsoluteFilePath test = new LongestAbsoluteFilePath();
		//int maxLength =  test.lengthLongestPath("a\n\tbb\n\t\tb1\n\t\tb.txt\n\tccc\n\t\tc1\n\t\tc.txt");
		//int maxLength =  test.lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext");
		//int maxLength =  test.lengthLongestPath("a\n\tb\n\t\tc");
		int maxLength =  test.lengthLongestPath("dir\n    file.txt");
		//int maxLength =  test.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext");
		System.out.println(maxLength);
	}
	
	public int lengthLongestPath(String input) {
		input = replaceLevels(input, "\n", 0);
		return maxLength(input, 1);
	}
	
	public String replaceLevels(String input, String levelStr, int level){
		if(input.contains("\t")){
			input = input.replaceAll(levelStr+"\t", "l"+(level+1));
			return replaceLevels(input, "l"+(level+1), level+1);
		}
		else if(input.contains("\n")){
			input = input.replaceAll("\n", "l"+(level+1));
		}
		return input;
		
	}
	
	public int maxLength(String input,  int level){
		String[] sub = input.split("l"+level);
		int maxLength = 0;
		int temp = 0;
		if(sub.length == 1){ // split is returning whole input
			if(input.contains(".")){
				maxLength = input.trim().length();
			}
		}
		else{
			for(int i=0; i<sub.length; i++){
				
				if(i==0){
					maxLength += sub[i].length()+1; // add 1 for "/"
				}
				else{
					temp = Math.max(temp, maxLength(sub[i], level+1));
				}
			}
			if(temp == 0){
				return 0; // if temp 0 means , no files in this sub dir
			}
			maxLength += temp;
		}
		return maxLength;
	}
	
	/*
	public int lengthLongestPath(String input) {
	        
	        if(input == null){
	            return 0;
	        }
	        String splitBy = "\n\t";
	        Node root = null;
	        Stack<Node> s = new Stack<>();
	        s.push(new Node(input, splitBy));
	        while(!s.isEmpty()){
	            Node top = s.pop();
	            root = top;
	            String[] subDir = top.str.split(top.splitBy);
	            for(int k=0; k<subDir.length; k++){
	                if(root == null){
	                    root = new Node(subDir[k], top.splitBy);
	                }
	                else{
	                    Node cn = new Node(subDir[k], top.splitBy+"\t");
	                    root.addNode(cn);
	                    s.push(cn);
	                }
	            }
	            
	        }
	    }
	    
	    
	    
	    class Node{
	        
	        public String str;
	        public String splitBy;
	        public List<Node> nodes = new ArrayList<>();
	        
	        public Node(String str, String splitBy){
	            this.str = str;
	            this.splitBy = splitBy; 
	        }
	        
	        public void addNode(Node n){
	            nodes.add(n);
	        }
	    }*/
}
