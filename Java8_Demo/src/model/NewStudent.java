package model;

import java.util.Optional;

public class NewStudent {

    private Optional<Goddess> goddess = Optional.empty();

    public NewStudent() {
    }

    public NewStudent(Optional<Goddess> goddess) {
        this.goddess = goddess;
    }


    public Optional<Goddess> getGoddess() {
        return goddess;
    }

    public void setGoddess(Optional<Goddess> goddess) {
        this.goddess = goddess;
    }

    @Override
    public String toString() {
        return "NewStudent{" +
                "goddess=" + goddess +
                '}';
    }
}
