package com.lexmark.util;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.core.io.Resource;

import com.lexmark.domain.Field;
import com.lexmark.domain.ListFieldDetail;
import com.lexmark.domain.TemplateInformation;
import com.lexmark.domain.Value;
import com.lexmark.framework.logging.LEXLogger;

public class ReadTemplateXml {
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(ReadTemplateXml.class);
	private Resource fileName;
	private TemplateInformation templateInformation;
	
	
	public Resource getFileName() {
		return fileName;
	}
	public void setFileName(Resource fileName) {
		LOGGER.debug("[in Set File name]");
		this.fileName = fileName;
		try{
			JAXBContext jaxbContext=null;
			
			jaxbContext = JAXBContext.newInstance(TemplateInformation.class);
			
			 
			Unmarshaller jaxbUnmarshaller=null;
			
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			InputStream is;
			
			is = fileName.getInputStream();
					
			TemplateInformation templateInformation = (TemplateInformation) jaxbUnmarshaller.unmarshal(is);
			LOGGER.debug("size ="+templateInformation.getField().size());
			/*for(Field field:templateInformation.getListFieldDetail().getField()){
				if(field.getConstraint()!=null){
					for(Value val:field.getConstraint().getValue()){
						LOGGER.debug("vakle="+val.getValue() +" CSV ="+val.getCsvValue());
					}
				}
			}*/
			LOGGER.debug("size ="+templateInformation.getField().get(0).getCsvHeader());
			setTemplateInformation(templateInformation);
		}catch(Exception exception){
			exception.printStackTrace();
			LOGGER.debug("Exception occured While reading xml"+fileName);
		}
		
	}
	
	public void setTemplateInformation(TemplateInformation templateInformation) {
		this.templateInformation = templateInformation;
	}
	public TemplateInformation getTemplateInformation() {
		return templateInformation;
	} 
	
	
}
