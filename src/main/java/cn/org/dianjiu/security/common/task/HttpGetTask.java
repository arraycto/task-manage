package cn.org.dianjiu.security.common.task;


import cn.org.dianjiu.security.common.util.HttpClientUtil;
import cn.org.dianjiu.security.dao.TTaskRecordsDao;
import cn.org.dianjiu.security.entity.HttpJobLogs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@DisallowConcurrentExecution
public class HttpGetTask implements Job {

    private static final Logger logger = LogManager.getLogger(HttpGetTask.class);

    @Autowired
    private TTaskRecordsDao tTaskRecordsDao;

    @Override
    public void execute(JobExecutionContext context) {
        JobDetail jobDetail = context.getJobDetail();
        String jobName = jobDetail.getKey().
        String jobName = jobDetail.getKey().getName();
        String jobGroup = jobDetail.getKey().getGroup();

        Map<String, Object> jobParamsMap = jobDetail.getJobDataMap();

        String requestType = (String) jobParamsMap.get(Constant.REQUEST_TYPE);
        String url = (String) jobParamsMap.get(Constant.URL);
        Map<String, Object> paramMap = (Map) jobParamsMap.get(Constant.PARAMS);

        HttpJobLogs httpJobLogs = new HttpJobLogs();
        httpJobLogs.setJobName(jobName);
        httpJobLogs.setJobGroup(jobGroup);
        httpJobLogs.setRequestType(requestType);
        httpJobLogs.setHttpUrl(url);
        if (null != paramMap && paramMap.size() > 0) {
            httpJobLogs.setHttpParams(paramMap.toString());
        }

        String result = HttpClientUtil.getMap(url, paramMap);
        httpJobLogs.setResult(result);

        logger.info("Success in execute [{}_{}]", jobName, jobGroup);

        httpJobLogsMapper.insertSelective(httpJobLogs);
    }

}
