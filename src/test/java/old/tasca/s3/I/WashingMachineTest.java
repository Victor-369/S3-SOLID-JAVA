package old.tasca.s3.I;

import old.tasca.s3.I.src.WashingMachine;
import old.tasca.s3.I.src.interfaces.AirConditionerActions;
import old.tasca.s3.I.src.interfaces.MachineBasicActions;
import old.tasca.s3.I.src.interfaces.WashingMachineActions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WashingMachineTest {
    @Test
    void washingMachine_implementsOnlyNeededInterfaces() {
        WashingMachine machine = new WashingMachine();

        assertInstanceOf(MachineBasicActions.class, machine);
        assertInstanceOf(WashingMachineActions.class, machine);
        assertFalse(machine instanceof AirConditionerActions);
    }
}
