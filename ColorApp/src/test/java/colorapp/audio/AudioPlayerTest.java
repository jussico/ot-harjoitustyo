package colorapp.audio;

import org.junit.Test;

public class AudioPlayerTest {

    @Test
    public void createAudioPlayer() {
        new AudioPlayer();
    }

    @Test
    public void testGetRandom() {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.getRandom(audioPlayer.musicClips.get(MusicType.Menu));
    }

    @Test
    public void testPlaySound() {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.playSound(SoundType.AnswerBad);
        audioPlayer.playSound(SoundType.AnswerOk);
        audioPlayer.playSound(SoundType.AnswerGood);
    }
    
    @Test
    public void testStartMusic() {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.startMusic(MusicType.Game);
    }

    @Test
    public void testStopMusic() {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.startMusic(MusicType.Game);
        audioPlayer.stopMusic();
    }


}
