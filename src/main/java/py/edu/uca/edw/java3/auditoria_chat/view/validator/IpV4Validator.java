package py.edu.uca.edw.java3.auditoria_chat.view.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("py.edu.uca.edw.java3.auditoria_chat.view.validator.IpV4Validator")
public class IpV4Validator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value != null) {
			int puntos = 0;
			String ip = value.toString();
			for (int i = 0; i < ip.length(); i++) {
				if (ip.charAt(i) == '.') {
					puntos++;
				}
			}
			if (puntos != 3) {
				FacesMessage msg = new FacesMessage(
						"Validación de Nro. de IP con errores.",
						"El nro. de IP v4 es incorrecto.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}
		} else {
			FacesMessage msg = new FacesMessage(
					"Validación de Nro. de IP con errores.",
					"El nro. de IP No puede ser nulo.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

	}
}
