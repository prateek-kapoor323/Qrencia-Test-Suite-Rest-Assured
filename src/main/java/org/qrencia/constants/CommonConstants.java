package org.qrencia.constants;

import java.io.File;
import java.nio.file.Paths;

public class CommonConstants
{
    public static final String TEST_CASE_INVENTORY_FILE_PATH = System.getProperty("user.dir") + File.separator + Paths.get("src","test","resources") + File.separator + "test_cases_inventory.xlsx";
    public static final String CONFIG_PROPERTIES_FILE_PATH = System.getProperty("user.dir") + File.separator + Paths.get("src","test","resources","properties") + File.separator + "config.properties";
    public static final String STAGE_URL_KEYWORD = "STAGE_URL";
    public static final String DEV_QA_URL_KEYWORD="IntDev_URL";
    public static final String EXECUTION_FLAG_NO="No";
    public static final String EXECUTION_ROW_NAME = "Execute";
}
