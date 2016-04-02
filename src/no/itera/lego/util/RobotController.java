package no.itera.lego.util;

import lejos.robotics.RegulatedMotor;

/**
 * This interface is implemented and further documented in the EV3Controller.
 *
 * You can use the functions found in this interface to control the robot, any
 * additional (more advanced) helper functions can be implemented directly in
 * the ControlThread. See the backupAndTurnAround() function for an example.
 */
public interface RobotController {

    /**
     * You know how this works :)
     */
    void playBeep();

    /**
     * This function is the "most versatile" move function.
     * Go ahead and add other Speed constants if you'd like, although it
     * is probably not necessary.
     */
    void forward(Speed left, Speed right);

    /**
     * These functions moves the robot in the direction according to the
     * function name, you'd have to use the above function if you want to
     * control the speed of each of the engine's yourself
     */
    void forward();
    void forwardLeft();
    void forwardRight();
    void backward();
    void stop();

    /**
     * These functions will only move one of the robot's engines, resulting
     * in the robot rotating in-place
     */
    void rotateLeft();
    void rotateRight();

    /**
     * These four functions can be used to determine what direction the
     * robot is moving.
     * Note that it will take a little time before these functions give
     * the "expected" result, after a move-function has been initiated,
     * this is due to the nature of us calling the unblocking robot
     * control functions.
     */
    boolean isGoingBackward();
    boolean isGoingForward();
    boolean isGoingLeft();
    boolean isGoingRight();

    /**
     * You also have the ability to write your own move-functions by
     * accessing the robot's motors directly. This gives you more
     * fine grained control over the robot's actions - if time allows
     * for it :)
     */
    RegulatedMotor getMotorRight();
    RegulatedMotor getMotorLeft();

}
