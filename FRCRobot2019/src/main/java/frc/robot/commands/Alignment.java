package main.java.frc.robot.commands;

import edu.wpi.first.wpilibj.Ultrasonic;
import frc.robot.RobotMap;
import frc.robot.Constants;

public class Alignment {
    Ultrasonic leftSensor = new Ultrasonic(RobotMap.alignmentLSensorIN,RobotMap.alignmentLSensorOUT);
    Ultrasonic middleSensor = new Ultrasonic(RobotMap.alignmentMSensorIN, RobotMap.alignmentMSensorOUT);
    Ultrasonic rightSensor = new Ultrasonic(RobotMap.alignmentRSensorIN, RobotMap.alignmentRSensorOUT);

    protected double maxRangeDeviation = 3;
    protected boolean isFinished = false;

    protected boolean checkSensors(){
        double sensorAverage = (leftSensor.getRangeInches()+middleSensor.getRangeInches()+rightSensor.getRangeInches())/3;
        boolean isAligned = true;
        if (Math.abs(leftSensor.getRangeInches()-sensorAverage) > maxRangeDeviation){
            isAligned = false;
        }; if (Math.abs(middleSensor.getRangeInches()-sensorAverage) > maxRangeDeviation){
            isAligned = false;
        }; if (Math.abs(rightSensor.getRangeInches()-sensorAverage) > maxRangeDeviation){
            isAligned = false;
        }
        return isAligned;
    }

    protected void initialize(){
        robot.system = robot.mode.AUTO_ALIGN;
    }

    protected void execute() {
        double rangeDifference = Math.abs(leftSensor.getRangeInches() - rightSensor.getRangeInches());
        if (leftSensor.getRangeInches()>rightSensor.getRangeInches()){
            robot.getRobotDrive().arcadeDrive(0, 1 * Constants.turnSpeed);
        } else if (leftSensor.getRangeInches()>rightSensor.getRangeInches()){
            robot.getRobotDrive().arcadeDrive(0, -1 * Constants.turnSpeed);
        }
        if (checkSensors()){
            isFinished = true;
        }
    }

    protected void end() {
        robot.system = robot.mode.HATCH;
    }

    protected void interrupted(){

    }

    protected boolean isFinished(){
        return isFinished;
    }


}