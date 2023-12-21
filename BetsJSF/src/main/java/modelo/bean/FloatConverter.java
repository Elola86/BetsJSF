package modelo.bean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("floatConverter")
public class FloatConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            throw new ConverterException("El valor no es un número flotante válido.", e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        if (value instanceof Float) {
            return String.valueOf(value);
        } else {
            throw new ConverterException("El objeto no es del tipo esperado para la conversión a Float.");
        }
    }
}