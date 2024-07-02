package org.qrencia.tests;

import groovy.util.logging.Slf4j;
import io.restassured.response.Response;
import org.qrencia.constants.CommonConstants;
import org.qrencia.utils.ExcelHelperUtils;
import org.qrencia.utils.RestRequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.util.Objects;

public class LandingPageTest {

    private static final Logger log = LoggerFactory.getLogger(LandingPageTest.class);

    private String executionStatus=null;
    String scenarioName;
    String workSheetName;
    private RestRequestUtils restRequestUtils = new RestRequestUtils();

    @BeforeMethod
    public void checkExecutionStatus(Method method)
    {
        scenarioName=method.getName();
        workSheetName = this.getClass().getSimpleName();

        try
        {
            executionStatus = ExcelHelperUtils.readValueFromExcel(CommonConstants.TEST_CASE_INVENTORY_FILE_PATH, workSheetName, scenarioName, CommonConstants.EXECUTION_ROW_NAME);
            log.info("The execution status received after reading the file is - {}", executionStatus);
            if (Objects.isNull(executionStatus) || executionStatus.equalsIgnoreCase(CommonConstants.EXECUTION_FLAG_NO))
            {
                throw new SkipException("Execution status of the scenario is set to No. The test step scenario will not be executed.");
            }
        }
        catch(Exception genericException)
        {
            log.error("A generic exception was caught while checking execution status for method - {}. The exception is - {}",method.getName(), genericException.getMessage());
        }
    }

    @Test
    public void landingPageHappyPath()
    {
        String apiEndpointUrl = ExcelHelperUtils.getEndpointURL(workSheetName, scenarioName);
        log.info("API Endpoint for sending request is - {}",apiEndpointUrl);
        Response response = restRequestUtils.sendGetRequest(apiEndpointUrl);
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test
    public void getAllSkills()
    {
        String apiEndpointUrl = ExcelHelperUtils.getEndpointURL(workSheetName, scenarioName);
        log.info("API Endpoint for sending request is - {}",apiEndpointUrl);
        Response response = restRequestUtils.sendGetRequest(apiEndpointUrl);
        Assert.assertEquals(response.statusCode(),200);
    }

}
