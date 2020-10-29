package com.example.galgeleg;

import java.util.ArrayList;

public interface IGalgelogik {

    ArrayList<String> getBrugteBogstaver();

    String getSynligtOrd();

    String getOrdet();

    int getAntalForkerteBogstaver();

    boolean erSidsteBogstavKorrekt();

    boolean erSpilletVundet();

    boolean erSpilletTabt();

    boolean erSpilletSlut();

    void startNytSpil();

    void opdaterSynligtOrd();

    void gætBogstav(String bogstav);

    void logStatus();
}
