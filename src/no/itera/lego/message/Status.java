package no.itera.lego.message;

import no.itera.lego.color.Color;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Status implements Message {

    public static final String TYPE = "status";

    private final boolean simulate;
    /**
     * this is set to true when the server activates a match
     */
    public final boolean isActive;
    /**
     * the current color of your robot
     */
    public final Color target;
    /**
     * the current positions on the colored section of the map of all robots
     * 0: you
     * 1: your teammate
     * 2: opponent one
     * 3: opponent two
     */
    public final List<Color> colors;

    public Status(boolean simulate, boolean isActive, Color target, List<Color> colors) {
        this.simulate = simulate;
        this.isActive = isActive;
        this.target = target;
        this.colors = colors;
    }

    public static Status createTestingStatus(boolean isActive, Color target, final Color currentColor){
        List<Color> colors = Arrays.asList(
                currentColor,
                Color.BLUE,
                Color.WHITE,
                Color.YELLOW);
        return new Status(false, isActive, target, colors);
    }

    public static Status fromJson(JSONObject object) {
        boolean simulate = (boolean) object.get("simulate");

        boolean isActive = (boolean) object.get("isActive");

        Color target = Color.valueOf((String) object.get("target"));

        JSONArray jsonColorsArray = (JSONArray) object.get("status");

        List<Color> colors = new ArrayList<>();
        for (Object objColor : jsonColorsArray) {
            colors.add(Color.valueOf((String) objColor));
        }
        return new Status(simulate, isActive, target, colors);
    }

    public boolean isSimulation() {
        return simulate;
    }

    public String toJson() {
        throw new UnsupportedOperationException(String.format("Cannot convert message type '%s' to JSON", TYPE));
    }

    @Override
    public String toString() {
        return String.format("%s{%s, %s, %s, %s}",
                getClass().getSimpleName(),
                simulate,
                isActive,
                target,
                colors);
    }

}
