package algo.arrays;

import java.util.Arrays;
import java.util.Stack;

/*
 * https://careercup.com/question?id=5687609083297792
 * https://www.geeksforgeeks.org/merging-intervals/
 */
//(1,4) ,  (6,8), (2,4), (7,9), (10,15)
public class TotalTimeCoveredByIntervals {
	
	public static void main(String[] args) {
		TotalTimeCoveredByIntervals timer = new TotalTimeCoveredByIntervals();
		
		TimeSlot[] intervals = new TimeSlot[]{new TimeSlot(1,4),  new TimeSlot(6,8), new TimeSlot(2,4), new TimeSlot(7,9), new TimeSlot(10,15)};
		System.out.println(timer.timeCoveredByIntervals(intervals));
	}

	public int timeCoveredByIntervals(TimeSlot[] intervals){
		
		int totalTime = 0;
		if(intervals.length == 0){
			return totalTime;
		}
		Arrays.sort(intervals);
		
		Stack<TimeSlot> stack = new Stack<>();
		stack.push(intervals[0]);
		
		for(int i=1; i<intervals.length; i++){
			TimeSlot top = stack.peek();
			if(top.getEnd() >= intervals[i].getStart()){
				if(top.end < intervals[i].getEnd()){
					top.end = intervals[i].getEnd();
				}
			}
			else{
				stack.push(intervals[i]);
			}
		}
		
		while(!stack.empty()){
			TimeSlot top = stack.pop();
			totalTime += top.getTotalTime();
		}
		return  totalTime;
	}
	
	
	private static class TimeSlot implements Comparable<TimeSlot>{
		
		private int start;
		private int end;
		
		private TimeSlot(int start, int end){
			this.start = start;
			this.end = end;
		}
		
		public int getStart() {
			return start;
		}
		public int getEnd() {
			return end;
		}
		
		public int getTotalTime(){
			return this.end - this.start;
		}

		@Override
		public int compareTo(TimeSlot o) {
			if(o == null){
				return 1;
			}
			return this.start - o.start;
		}
		
	}
	
}
