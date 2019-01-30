/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.*;
import frc.robot.commands.*;

/**
 * Add your docs here.
 */
public class I2CDataSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Robot robot;
  private OI oi;
  private Robot.Mode system;
  private I2C colorSensor1;
  private I2C colorSensor2;
  private I2C colorSensor3;

  public I2CDataSubsystem(OI oi, Robot robot, Robot.Mode system) {
    this.oi = oi;
    this.robot = robot;
    this.system = system;
  }

  @Override
  public void initDefaultCommand() {}

    public double getColorMovement() {
        if (system == Robot.Mode.HATCH) {
            colorSensor1 = new I2C(I2C.Port.kOnboard, RobotMap.colorSensor1PortHatch);
            colorSensor1.write(0x80 | 0x00, 0b00000011);
            colorSensor2 = new I2C(I2C.Port.kOnboard, RobotMap.colorSensor2PortHatch);
            colorSensor2.write(0x80 | 0x00, 0b00000011);
            colorSensor3 = new I2C(I2C.Port.kOnboard, RobotMap.colorSensor3PortHatch);
            colorSensor3.write(0x80 | 0x00, 0b00000011);
        }
        c1 = colorSensor1.read()
    }
}
