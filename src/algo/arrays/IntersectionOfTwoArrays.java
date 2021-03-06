package algo.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * 
 * https://leetcode.com/problems/intersection-of-two-arrays/
 * 
 * 
 * Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Note:

Each element in the result must be unique.
The result can be in any order.
 
 */
public class IntersectionOfTwoArrays {

	
	 public int[] intersection(int[] nums1, int[] nums2) {
	        
	        Set<Integer> set1 = new HashSet<>();
	        
	        for(int num1 : nums1){
	            set1.add(num1);
	        }
	        
	        Set<Integer> resultSet = new HashSet<>();
	        
	        
	        for(int num2 : nums2){
	            
	            if(set1.contains(num2)){
	                    resultSet.add(num2);
	            }
	        }
	        
	        int[] result = new int[resultSet.size()];
	        int i = 0;
	        for (Integer num : resultSet) {
	            result[i++] = num;
	        }
	        
	        return result;
	    }
	 
	 /*
	  * https://leetcode.com/problems/intersection-of-two-arrays-ii/
	  * 
	  * Given two arrays, write a function to compute their intersection.

	 Example 1:

	 Input: nums1 = [1,2,2,1], nums2 = [2,2]
	 Output: [2,2]
	 Example 2:

	 Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
	 Output: [4,9]
	 Note:

	 Each element in the result should appear as many times as it shows in both arrays.
	 The result can be in any order.
	 Follow up:

	 What if the given array is already sorted? How would you optimize your algorithm?
	 What if nums1's size is small compared to nums2's size? Which algorithm is better?
	 What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?*/
	 
	 public int[] intersect2(int[] nums1, int[] nums2) {
	        
	        Map<Integer, Integer> map = new HashMap<>();
	        
	        for(int num1 : nums1){
	            map.put(num1, map.getOrDefault(num1, 0)+1);
	        }
	        
	        List<Integer> resultSet = new ArrayList<>();
	        
	        
	        for(int num2 : nums2){
	            
	            if(map.containsKey(num2) && map.get(num2) > 0){
	                resultSet.add(num2);
	                map.put(num2, map.get(num2)-1);
	            }
	        }
	        
	        int[] result = new int[resultSet.size()];
	        int i = 0;
	        for (Integer num : resultSet) {
	            result[i++] = num;
	        }
	        
	        return result;
	    }
	 
}
