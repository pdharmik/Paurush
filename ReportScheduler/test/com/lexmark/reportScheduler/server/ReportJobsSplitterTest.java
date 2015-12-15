package com.lexmark.reportScheduler.server;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.lexmark.domain.ReportJob;
import com.lexmark.enums.ReportRunFrequencyEnum;

public class ReportJobsSplitterTest extends TestCase {

	private ReportJobsSplitter target;
	
	@Before
	public void setup() {
	}

	@Test
	public void testPrioritize() {
		List<ReportJob> list = createJobList();
		target = new ReportJobsSplitter(list, 3);
		
		List<ReportJob>  result = target.prioritize(list);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(ReportRunFrequencyEnum.ONDEMAND, result.get(0).getRunFrequency());
		Assert.assertEquals(19, result.get(0).getOverdueMinutes());
		Assert.assertEquals(ReportRunFrequencyEnum.MONTHLY, result.get(19).getRunFrequency());
		Assert.assertEquals(0, result.get(19).getOverdueMinutes());
	}

	private List<ReportJob> createJobList() {
		List<ReportJob> list = new ArrayList<ReportJob>();
		for(int i=0; i<20; i++) {
			ReportJob job = new ReportJob();
			if(i < 5) {
				job.setRunFrequency(ReportRunFrequencyEnum.MONTHLY);
			} else if(i<10) {
				job.setRunFrequency(ReportRunFrequencyEnum.WEEKLY);
			} else if(i < 15) {
				job.setRunFrequency(ReportRunFrequencyEnum.DAILY);
			} else {
				job.setRunFrequency(ReportRunFrequencyEnum.ONDEMAND);
			}
			job.setOverdueMinutes(i);
			list.add(job);
		}
		return list;
	}

	@Test
	public void testSplit() {
		
		List<ReportJob> list = createJobList();
		target = new ReportJobsSplitter(list, 3);
		
		List<ReportJob>[] result = target.split();
		Assert.assertNotNull(result);
		Assert.assertEquals(3, result.length);
		Assert.assertEquals(7, result[0].size());
		Assert.assertEquals(7, result[1].size());
		Assert.assertEquals(6, result[2].size());
	
		Assert.assertEquals(ReportRunFrequencyEnum.ONDEMAND, result[0].get(0).getRunFrequency());
		Assert.assertEquals(ReportRunFrequencyEnum.ONDEMAND, result[0].get(1).getRunFrequency());
		Assert.assertEquals(ReportRunFrequencyEnum.DAILY, result[0].get(2).getRunFrequency());

		Assert.assertEquals(19, result[0].get(0).getOverdueMinutes());
		Assert.assertEquals(16, result[0].get(1).getOverdueMinutes());
		Assert.assertEquals(13, result[0].get(2).getOverdueMinutes());
		
	}

}
