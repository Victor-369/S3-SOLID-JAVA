package old.tasca.s3.I;

import old.tasca.s3.I.src.AirConditioner;
import old.tasca.s3.I.src.interfaces.AirConditionerActions;
import old.tasca.s3.I.src.interfaces.MachineBasicActions;
import old.tasca.s3.I.src.interfaces.WashingMachineActions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class AirConditionerTest {
    @Test
    void airConditioner_implementsOnlyNeededInterfaces() {
        AirConditioner machine = new AirConditioner();

        assertInstanceOf(MachineBasicActions.class, machine);
        assertInstanceOf(AirConditionerActions.class, machine);
        assertFalse(machine instanceof WashingMachineActions);
    }
}
