package old.tasca.s3.O.src;

public class InstrumentPlayer {
    public void play(String instrument) {
        if ("guitar".equals(instrument)) {
            System.out.println("🎸 Strumming the guitar");
        } else if ("drums".equals(instrument)) {
            System.out.println("🥁 Beating the drums");
        } else if ("piano".equals(instrument)) {
            System.out.println("🎹 Playing the piano");
        } else {
            System.out.println("🔇 Unknown instrument");
        }
    }

    public static void main(String[] args) {
        InstrumentPlayer player = new InstrumentPlayer();
        player.play("guitar");
        player.play("drums");
        player.play("piano");
    }
}
