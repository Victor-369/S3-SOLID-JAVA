package old.tasca.s3.O;

import old.tasca.s3.O.src.Instrument;
import old.tasca.s3.O.src.InstrumentDrums;
import old.tasca.s3.O.src.InstrumentGuitar;
import old.tasca.s3.O.src.InstrumentPiano;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InstrumentTest {
    @Test
    void play_guitar_returnsGuitarMessage() {
        Instrument guitar = new InstrumentGuitar();

        String message = guitar.play();

        assertEquals("🎸 Strumming the guitar", message);
    }

    @Test
    void play_drums_returnsDrumsMessage() {
        Instrument drums = new InstrumentDrums();

        String message = drums.play();

        assertEquals("🥁 Beating the drums", message);
    }

    @Test
    void play_piano_returnsPianoMessage() {
        Instrument piano = new InstrumentPiano();

        String message = piano.play();

        assertEquals("🎹 Playing the piano", message);
    }
}
