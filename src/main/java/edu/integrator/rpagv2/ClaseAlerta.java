package edu.integrator.rpagv2;

public class ClaseAlerta {
    public int id;
    public String name, icon_name;
    public int icon;

    public ClaseAlerta(int id, String name, int icon, String icon_name) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.icon_name = icon_name;
    }
}
