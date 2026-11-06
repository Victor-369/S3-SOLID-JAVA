package old.tasca.s3.I.src;

import old.tasca.s3.I.src.interfaces.MachineBasicActions;
import old.tasca.s3.I.src.interfaces.WashingMachineActions;

public class WashingMachine implements MachineBasicActions, WashingMachineActions {

    @Override
    public void turnOn() {
        System.out.println("WashingMachine is ON");
    }

    @Override
    public void turnOff() {
        System.out.println("WashingMachine is OFF");
    }

    @Override
    public void wash() {
        System.out.println("WashingMachine is washing clothes.");
    }
}
