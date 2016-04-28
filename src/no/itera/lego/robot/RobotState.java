package no.itera.lego.robot;

import java.util.concurrent.CountDownLatch;

import no.itera.lego.color.Color;
import no.itera.lego.message.Status;

public class RobotState {

    /**
     * SET THE FOLLOWING VALUES
     *
     * HOST:
     *      Enter the server ip address that the robot should connect to
     * name:
     *      The name of your robot
     */
    public static final String HOST = null;  // e.g. "192.168.43.254";
    public final String name = null;         // e.g. "YOUR ROBOT NAME";

    /**
     * READ ONLY
     * This is the last status of the game received from the server,
     * it contains information about your robot, as well as the position
     * of the other robots on the map
     */
    public Status lastStatus;
    /**
     * READ ONLY
     * This is your robot's last read color, i.e. your position on the map.
     * It is set automatically from the sensor thread
     * Note that this color will be slightly more  up to date than your
     * color in lastStatus (lastStatus.color.get(0)), although it shouldn't
     * matter much
     */
    public Color lastColor = Color.UNDEFINED;
    /**
     * READ ONLY
     * This is your robot's last read distance, the distance seams to vary
     * between these values (note that these values are indicative, and may
     * vary slightly with your robot):
     *
     * Constants:
     * INFINITY: no object in front
     * 0:  object is very close
     *
     * Range between 50 and 5:
     * 50: object is approximately 50-40 cm ahead
     * 5:  object is approximately 9-6 cm ahead
     */
    public float lastDistance;
    /**
     * you use the robotController to control your robot
     * it contains a few helper functions that let you control the robot with
     * _non blocking_ function calls, which means that the robot will keep
     * driving in the specified direction until you tell it to do something else
     */
    public final RobotController robotController;

    /**
     * READ ONLY
     * This flag is used in the while loops. It should not be modified
     */
    public boolean shouldRun;

    // you don't need to worry about these fields
    public CountDownLatch latch;
    public boolean webSocketOpen;
    public boolean webSocketConnecting;

    public RobotState(RobotController robotController) {
        if (HOST == null || name == null) {
            throw new RuntimeException("Please specify the server IP address and your robot's name!");
        }
        this.robotController = robotController;
        shouldRun = true;
        lastStatus = Status.createTestingStatus(false, Color.RED, Color.WHITE);
    }

    @Override
    public String toString() {
        return String.format("%s{%s, %s, %s, %s, %s, %s, %s}",
                getClass().getSimpleName(),
                shouldRun,
                lastStatus,
                lastColor,
                lastDistance,
                webSocketOpen,
                webSocketConnecting,
                robotController);
    }

}
