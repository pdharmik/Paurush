package com.lexmark.reportScheduler.server;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.lexmark.domain.ReportJob;

public class ReportJobsSplitter {
	private List<ReportJob> reportJobList;
	private List<ReportJob>  prioritizedList;
	private int queueNumber =1;
	private List<ReportJob>[] splittedQueues;
	public ReportJobsSplitter(List<ReportJob> reportJobList, int queueNumber) {
		if(reportJobList == null || reportJobList.size() < queueNumber) {
			throw new IllegalArgumentException("report job list should be not null and size greater than queueNumber");
		}
		this.reportJobList = reportJobList;
		this.queueNumber = queueNumber;
		prioritizedList = prioritize(this.reportJobList);
	}
	
	public  List<ReportJob> prioritize(List<ReportJob> originalList) {
		Collections.sort(originalList, new Comparator<ReportJob>() {

			public int compare(ReportJob left, ReportJob right) {
				if(left == null && right != null) {
					return 1;
				} else if(left != null && right == null) {
					return -1;
				} else if(left == null && right == null) {
					return 0;
				}
				if(left.getRunFrequency()== null && right.getRunFrequency() != null) {
					return 1;
				} else if(left.getRunFrequency()!=null && right.getRunFrequency()== null) {
					return -1;
				}
				if(left.getRunFrequency().getPriority()!= right.getRunFrequency().getPriority()) {
					return right.getRunFrequency().getPriority() - left.getRunFrequency().getPriority();
				} 
				return right.getOverdueMinutes() - left.getOverdueMinutes();
			}
			
		});
		return originalList;
	}
	
	public  List<ReportJob>[] split() {
		splittedQueues = (List<ReportJob>[])Array.newInstance(List.class, queueNumber);
		for(int i=0; i<queueNumber; i++ ) {
			splittedQueues[i] = new ArrayList<ReportJob>();
		}
		for(int i=0; i< prioritizedList.size(); i++) {
			int threadGroupId = i%queueNumber;
			prioritizedList.get(i).setThreadGroup(threadGroupId);
			splittedQueues[threadGroupId].add(prioritizedList.get(i));
		}
		return splittedQueues;
	}
}
