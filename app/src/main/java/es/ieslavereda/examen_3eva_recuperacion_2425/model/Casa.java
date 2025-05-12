package es.ieslavereda.examen_3eva_recuperacion_2425.model;

import es.ieslavereda.examen_3eva_recuperacion_2425.R;

public enum Casa {
    ARRYN(R.mipmap.ic_arryn, "Arryn"),
    BARATHEON(R.mipmap.ic_baratheon, "Baratheon"),
    GREYJOY(R.mipmap.ic_greyjoy, "Greyjoy"),
    LANNISTER(R.mipmap.ic_lannister, "Lannister"),
    MARTELL(R.mipmap.ic_martell, "Martell"),
    STARK(R.mipmap.ic_stark, "Stark"),
    TARGARYEN(R.mipmap.ic_targaryen, "Targaryen"),
    TULLY(R.mipmap.ic_tully, "Tully");

    private int escudo;
    private String casa;

    Casa(int escudo, String casa){
        this.casa=casa;
        this.escudo=escudo;
    }

    public int getEscudo() {
        return escudo;
    }

    public String getCasa() {
        return casa;
    }

    public static Casa devolverCasa(String casa){
        for (Casa cas:Casa.values())
            if (cas.getCasa().equals(casa))
                return cas;

        return null;
    }
}
