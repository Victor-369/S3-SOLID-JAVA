package old.tasca.s3.O.src;

public class InstrumentPlayer {
    private final Instrument instrument;

    public InstrumentPlayer(Instrument instrument) {
        this.instrument = instrument;
    }

    public void playInstrument() {
        System.out.println(instrument.play());
    }
}
