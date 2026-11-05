package old.tasca.s3.O.src;

public class InstrumentPlayer {
    private final Instrument instrument;

    public InstrumentPlayer(Instrument instrument) {
        this.instrument = instrument;
    }

    public void playInstrument() {
        System.out.println(instrument.play());
    }

    public static void main(String[] args) {
        Instrument drums = new InstrumentDrums();
        Instrument piano = new InstrumentPiano();
        Instrument guitar = new InstrumentGuitar();

        new InstrumentPlayer(guitar).playInstrument();
        new InstrumentPlayer(drums).playInstrument();
        new InstrumentPlayer(piano).playInstrument();
    }
}
