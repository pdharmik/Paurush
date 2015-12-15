package com.lexmark.services.exporter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lexmark.domain.FilterPreferenceList;
import com.lexmark.util.BusinessObjectUtil;
enum FilterValues{
	UserId("userId"),EmailAddress("emailAddress"),InstalledAddressId("addressFilter.installedAddressId"),City("addressFilter.city"),Country("addressFilter.country"),State("addressFilter.state"),Province("addressFilter.province"),ZipCode("addressFilter.zipCode"),
	SiteId("addressFilter.site"),Site("addressFilter.siteText"),BuildingId("addressFilter.building"),Building("addressFilter.buildingText"),
	FloorId("addressFilter.floor"),Floor("addressFilter.floorText"),ZoneId("addressFilter.zone"),Zone("addressFilter.zoneText"),AddressLine1("addressFilter.addressLine1"),AddressLine2("addressFilter.addressLine2"),
	BuildingType("addressFilter.buildingType"),
	AssetSerialNumber("deviceFilter.assetSerialNumber"),IPAddress("deviceFilter.iPAddress"),ProductModel("deviceFilter.productModel"),ProductName("deviceFilter.productName"),InstallStartDate("deviceFilter.installStartDate"),
	InstallEndDate("deviceFilter.installEndDate"),/*ProductModelType("deviceFilter.productModelType"),*/ProductType("deviceFilter.productType"),ProductSeries("deviceFilter.productSeries"),
	ProductBrand("deviceFilter.productBrand"),CostCenter("deviceFilter.costCenter"),DepartmentName("deviceFilter.departmentName"),CustomerAssetTag("deviceFilter.customerAssetTag"),HostName("deviceFilter.hostName"),
	/*AssetLifeCycle("deviceFilter.assetLifeCycle"),DevicePhase("deviceFilter.devicePhase"),*/AssetMeterSource("deviceFilter.assetMeterSource"),/*LXKAssetTag("deviceFilter.lXKAssetTag"),
	HardWareStatus("deviceFilter.hardwareStatus"),MDMID("deviceFilter.mdmID"),*/SRNumber("requestFilter.sRNumber"),SRType("requestFilter.sRType"),WebStatus("requestFilter.webStatus"),
	SRCreatedStartDate("requestFilter.sRCreatedStartDate"),SRCreatedEndDate("requestFilter.sRCreatedEndDate"),HelpDeskRefNum("requestFilter.helpDeskRefNum"),SRArea("requestFilter.sRArea"),
	SRSubArea("requestFilter.sRSubArea"),/*SRStatus("requestFilter.sRStatus"),SRSubStatus("requestFilter.sRSubStatus"),SRSource("requestFilter.sRSource"),*/
	Default_Preference("isPreference");
	  String fieldNameValues;
	  FilterValues(String fieldName) {
		  fieldNameValues = fieldName;
	   }
	  String getFieldNameValues() {
	      return fieldNameValues;
	   } 
}
public class CreateExcel {
	private static Logger LOGGER = LogManager.getLogger(CreateExcel.class);
	
	
	@SuppressWarnings("deprecation")
	public boolean createExcelReport(List<FilterPreferenceList> filterPrefList, ResourceResponse response) {
	
		//Blank workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
         
        //Create a blank sheet
        HSSFSheet sheet = workbook.createSheet("Preferences");
        sheet.protectSheet("");
        int rownum = 0;
        LOGGER.debug("list size--- " + filterPrefList.size());
        HSSFRow row = sheet.createRow(rownum++);
        int cellnum = 0;
        for (FilterValues obj : FilterValues.values()){
        	HSSFCell cell = row.createCell(cellnum++);
        	cell.setCellValue((String)obj.toString());
        }
        
        for(FilterPreferenceList fl:filterPrefList)
        {
            row = sheet.createRow(rownum++);
            cellnum = 0;
            
            
            for (FilterValues obj : FilterValues.values())
            {
            	HSSFCell cell = row.createCell(cellnum++);   
            	cell.setCellValue(BusinessObjectUtil.formatColumn(fl,(String)obj.getFieldNameValues(),null));
            }
        }
        try
        {
            //Write the workbook in file system
            //FileOutputStream out = new FileOutputStream(new File("E:\\Preference.xls"));
        	OutputStream opStream=null;
        	response.setProperty("Content-disposition", "attachment; filename=\"Preference.xls\"");
    		response.setContentType("application/xls");
        	try {
    			opStream=response.getPortletOutputStream();
    			LOGGER.debug("In AfterGettingOutput");
    			workbook.write(opStream);
    		} catch (IOException e) {
    			LOGGER.debug("Exception"+e.getMessage()); 
    			LOGGER.debug("In IOException");
    		} finally {
    	        if (opStream != null) {
    	            try {
    	            	opStream.flush();
    	            	opStream.close();
    	            } catch (IOException e) {
    	            	LOGGER.error("error occured "+e.getMessage());
    	            }
    	        }
    		}
    		LOGGER.exit();
            /*
            
            workbook.write(out);
            out.close();*/
    		//LOGGER.debug("File written successfully on disk.");
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

	}

}
