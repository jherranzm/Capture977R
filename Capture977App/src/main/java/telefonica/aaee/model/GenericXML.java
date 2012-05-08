package telefonica.aaee.model;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


public class GenericXML {
	
	
	
	public String toXML(){
		String xmlString = "";
		
		try {
			JAXBContext jc = JAXBContext.newInstance( this.getClass() );
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			StringWriter sw = new StringWriter();

			m.marshal( this, sw );
			xmlString = sw.toString();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xmlString;
	}
}
