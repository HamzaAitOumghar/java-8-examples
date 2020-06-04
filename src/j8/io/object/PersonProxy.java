package j8.io.object;

import java.io.Serializable;

public class PersonProxy implements Serializable {

    private String name;

    public PersonProxy(String name) {
        this.name = name;
    }

    public PersonProxy() {
    }

    @Override
    public String  toString() {
        return "PersonProxy{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
