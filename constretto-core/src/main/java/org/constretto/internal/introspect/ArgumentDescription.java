package org.constretto.internal.introspect;

import org.constretto.annotation.Configuration;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Parameter name
 *
 * @author <a href=mailto:zapodot@gmail.com>Sondre Eikanger Kval&oslash;</a>
 */
public class ArgumentDescription {

    private final String name;
    private final List<Annotation> annotations;
    private final Class<?> type;

    public ArgumentDescription(final String name, final Annotation[] annotations, final Class<?> type) {
        this.name = name;

        this.annotations = annotations == null || annotations.length == 0 ? Collections.<Annotation>emptyList() : Arrays.asList(annotations);
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Annotation[] getAnnotations() {
        return annotations.toArray(new Annotation[]{});
    }

    public Class<?> getType() {
        return type;
    }

    public String constrettoConfigurationKeyCandidate() {
        Configuration configuration = findConfigurationParameter();
        if(configuration != null && ! configuration.value().isEmpty()) {
            return configuration.value();
        } else {
          return getName();
        }
    }

    private Configuration findConfigurationParameter() {
        for(Annotation annotation: annotations) {
            if(annotation instanceof Configuration) {
                return (Configuration) annotation;
            }
        }
        return null;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final ArgumentDescription that = (ArgumentDescription) o;

        if (annotations != null ? !annotations.equals(that.annotations) : that.annotations != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (annotations != null ? annotations.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ArgumentDescription{");
        sb.append("name='").append(name).append('\'');
        sb.append(", annotations=").append(annotations);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
