package org.qrencia.utils;

import org.qrencia .constants.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class ExcelHelperUtils
{
    public static String readValueFromExcel(String testDataReaderFilePath, String worksheetName, String rowName, String columnName) {
        String rowValue = null;
        try (FileInputStream testDataReaderInputStream = new FileInputStream(testDataReaderFilePath);
             XSSFWorkbook workbook = new XSSFWorkbook(testDataReaderInputStream);) {

            XSSFSheet sheet = workbook.getSheet(worksheetName);
            XSSFRow row = sheet.getRow(0);
            int columnNumber = -1;

            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(columnName))
                {   columnNumber = i;
                    break;
                }
            }
            int j = 0;
            int rowNumber = 0;
            for (int rowcount = 0; rowcount <= sheet.getLastRowNum(); rowcount++) {
                String prKey = sheet.getRow(rowcount).getCell(j).toString().trim();
                if (prKey.equalsIgnoreCase(rowName)) {
                    rowNumber = rowcount;
                    break;
                }
            }
            rowValue = sheet.getRow(rowNumber).getCell(columnNumber).getStringCellValue();

        } catch (FileNotFoundException fileNotFoundException) {
            log.error("A file not found exception occurred while reading the file - " + fileNotFoundException);
        } catch (IOException ioException) {
            log.error("An io exception occurred while reading the file - " + ioException);
        } catch (Exception exception) {
            log.error("A generic exception was caught while reading the file - " + exception);
        }
        return rowValue;
    }

    public static String getEndpointURL(String serviceName, String scenarioName) {

        String endpoint = null;
        try
        {
            String env = PropertyUtils.readProp("environment");
            log.info("The environment value fetched from properties file is - {}", env);
            if (Objects.isNull(env)) {
                log.error("Could not fetch the value of key - environment");
                return null;
            }
            if (env.equalsIgnoreCase("STAGE")) {
                endpoint = readValueFromExcel(CommonConstants.TEST_CASE_INVENTORY_FILE_PATH, serviceName, scenarioName, CommonConstants.STAGE_URL_KEYWORD);

            } else if (env.equalsIgnoreCase("QA")) {
                endpoint = readValueFromExcel(CommonConstants.TEST_CASE_INVENTORY_FILE_PATH, serviceName, scenarioName, CommonConstants.DEV_QA_URL_KEYWORD);
            }
        } catch (Exception e) {
            log.error("Could not fetch the URL endpoint due to exception - {}", e.getMessage());
        }

        log.info("The endpoint fetched is - {}", endpoint);
        return endpoint;

    }
}