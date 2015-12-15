use DC_Scheuchzer



Declare CONVERT(datetime, '2010-07-16 01:00:00.000') datetime
 
Declare CONVERT(datetime, '2010-07-16 01:00:00.000') datetime
 
Declare @int_mch_id int
set CONVERT(datetime, '2010-07-16 01:00:00.000') = CONVERT(datetime, '2010-07-16 01:00:00.000')
set CONVERT(datetime, '2010-07-16 01:00:00.000') = CONVERT(datetime, '2010-07-16 04:15:00.000')
set @int_mch_id = 215
select * from  TME_Activity_Machine
	WHERE	(dbo.COM_HoursInPeriod(dtm_act_start, dtm_act_end, CONVERT(datetime, '2010-07-16 01:00'), CONVERT(datetime, '2010-07-16 04:15')) > 0 OR
	(dtm_act_start > CONVERT(datetime, '2010-07-16 01:00') AND dtm_act_end < CONVERT(datetime, '2010-07-16 04:15')) OR
	(dtm_act_start > CONVERT(datetime, '2010-07-16 01:00')   AND dtm_act_end < CONVERT(datetime, '2010-07-16 04:15'))) AND
	(int_machine_id = 215) AND
	int_act_status < 30
	order by dtm_act_start
	
	
	
	select * from  TME_Activity_Machine
    order by int_act_mch_id desc
    
    select * from  TME_Activity_Machine  WHERE	(dbo.COM_HoursInPeriod(dtm_act_start, dtm_act_end, CONVERT(datetime, ' 2010-07-16 01:00'), CONVERT(datetime, ' 2010-07-16 15:00')) > 0 OR  (dtm_act_start > CONVERT(datetime, ' 2010-07-16 01:00') AND dtm_act_end < CONVERT(datetime, ' 2010-07-16 15:00')) OR  (dtm_act_start > CONVERT(datetime, ' 2010-07-16 01:00')   
    AND dtm_act_end < CONVERT(datetime, ' 2010-07-16 15:00'))) 
    AND  (int_machine_id =  215) AND int_act_status < 30 order by dtm_act_start
    
    
    
    