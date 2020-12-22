package colorapp.audio;

import java.util.List;

import javafx.scene.media.AudioClip;

/**
 * Class which plays music in the background
 */
public class MusicPlayer implements Runnable {
    public MusicType musicType;
	public List<AudioClip> musicTypeCollection;
	public AudioPlayer player;
	public AudioClip nowPlayingMusic;
	public volatile boolean keepPlaying;

	public MusicPlayer(MusicType musicType, AudioPlayer player) {
        this.musicType = musicType;
		this.musicTypeCollection = player.musicClips.get(musicType);
		this.player = player;
		this.keepPlaying = true;
		nowPlayingMusic = player.getRandom(musicTypeCollection);
	}

	/**
	 * is this runnable going to continue playing music
	 * @return
	 */
	public boolean getKeepPlaying() {
		return this.keepPlaying;
	}

	/**
	 * stop playing music. this will make run-method exit and this runnable to die.
	 */
	public void stopMusic() {
		keepPlaying = false;
		nowPlayingMusic.stop();
	}

	@Override
	public void run() {
		do {
			System.out.println("now playing random " + this.musicType + " music in the background: " + nowPlayingMusic.getSource().substring(nowPlayingMusic.getSource().lastIndexOf("/") + 1));
			nowPlayingMusic.play();
			while (nowPlayingMusic.isPlaying()) {
				try {
					Thread.sleep(1000 * 1);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			nowPlayingMusic = player.getRandom(musicTypeCollection); // choose new random track
		} while (getKeepPlaying());
		
		// end of method here means this Runnable-class has finished its purpose in life and will be thrown to garbage.
	}
}