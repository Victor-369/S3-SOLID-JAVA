package old.tasca.s3.I.src;

import old.tasca.s3.I.src.interfaces.AirConditionerActions;
import old.tasca.s3.I.src.interfaces.MachineBasicActions;

public class AirConditioner implements MachineBasicActions, AirConditionerActions {

    @Override
    public void turnOn() {
        System.out.println("AirConditioner is ON");
    }

    @Override
    public void turnOff() {
        System.out.println("AirConditioner is OFF");
    }

    @Override
    public void heat() {
        System.out.println("AirConditioner is heating.");
    }

    @Override
    public void cool() {
        System.out.println("AirConditioner is cooling.");
    }
}

