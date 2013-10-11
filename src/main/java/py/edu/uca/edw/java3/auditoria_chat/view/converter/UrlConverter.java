package py.edu.uca.edw.java3.auditoria_chat.view.converter;

import java.net.URI;
import java.net.URISyntaxException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("py.edu.uca.edw.java3.auditoria_chat.view.converter.UrlConverter")
public class UrlConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		StringBuilder url = new StringBuilder();

		if (!value.startsWith("http://", 0)) {
			url.append("http://");
		}
		url.append(value);

		try {
			new URI(url.toString());
		} catch (URISyntaxException e) {
			FacesMessage msg = new FacesMessage("Error converting URL",
					"Invalid URL format");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(msg);
		}
		return url.toString();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return "Ir a: " + value.toString();
	}

}
