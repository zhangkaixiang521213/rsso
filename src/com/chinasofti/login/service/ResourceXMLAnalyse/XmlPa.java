package com.chinasofti.login.service.ResourceXMLAnalyse;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;


public class XmlPa {
	@SuppressWarnings("unchecked")
	public static <T> T unMarshal(String xml, Class<T> clazz) {
		try {
			xml = xml.trim();
			JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] { clazz });
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xml);
			SAXParserFactory sax = SAXParserFactory.newInstance();
			sax.setNamespaceAware(false);
			XMLReader xmlReader = sax.newSAXParser().getXMLReader();
			Source source = new SAXSource(xmlReader, new InputSource(reader));
			return (T) unmarshaller.unmarshal(source);
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T unMarshal(InputStream is, Class<T> clazz) {
		try {
			JAXBContext jContext = JAXBContext.newInstance(new Class[] { clazz });
			Unmarshaller unMer = jContext.createUnmarshaller();
			SAXParserFactory sax = SAXParserFactory.newInstance();
			sax.setNamespaceAware(false);
			XMLReader xmlReader = sax.newSAXParser().getXMLReader();
			Source source = new SAXSource(xmlReader, new InputSource(is));
			return (T) unMer.unmarshal(source);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static List<ModuleConfig> getMenuList(File file) throws Exception {

		InputStream is = new FileInputStream(file);
		ApplicationConfig applicationConfig = XmlPa.unMarshal(is, ApplicationConfig.class);
		AppConfig appConfig=applicationConfig.getAppConfig();
		List<ModuleConfig> list=appConfig.getModuleConfig();
		
		return list;
		
	}

	public static void main(String[] args) throws Exception {

		InputStream is = new FileInputStream(new File(
				"F:\\Icss document\\architect.xml"));
		ApplicationConfig applicationConfig = XmlPa.unMarshal(is, ApplicationConfig.class);
		AppConfig appConfig=applicationConfig.getAppConfig();
		List<ModuleConfig> 		list=appConfig.getModuleConfig();
		
		System.out.println("111");
	}
}
