package colorapp.audio;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javafx.scene.media.AudioClip;

/**
 * Class which handles playing music and sounds
 */
public class AudioPlayer {

	private String menu_music1 = "396024__patricklieberkind__rhythmic-game-menu-ambience.wav";
	private String menu_music2 = "516912__xythe__chill-tune-for-a-game.wav";
	private String menu_music3 = "396458__patricklieberkind__futuristic-rhythmic-game-ambience.wav";

	private String loop_music1 = "517542__hyperbrid__rush-clint-soundtrack.wav";
	private String loop_music2 = "465771__tolerabledruid6__8-bit-moonlight-sonata-music-loop.wav";
	private String loop_music3 = "251461__joshuaempyre__arcade-music-loop.wav";
	private String loop_music4 = "524743__foolboymedia__don-t-give-up-now.wav";
	private String loop_music5 = "335361__cabled-mess__little-happy-tune-22-10.wav";

	private String answer_good = "431329__someguy22__8-bit-powerup.wav";
	private String answer_ok = "412168__screamstudio__arcade-game-over.wav";
	private String answer_bad = "368591__josepharaoh99__retro-explosion.mp3";

	private String game_over_victory = "514154__edwardszakal__game-music.mp3";
	private String game_over_failure = "439890__simonbay__lushlife-gameover.wav";

	private List<AudioClip> answerGood;
	private List<AudioClip> answerOk;
	private List<AudioClip> answerBad;

	protected Map<MusicType, List<AudioClip>> musicClips;

	private MusicPlayer musicPlayer;

	private Random random = new Random();

	public AudioPlayer() {
		musicClips = new HashMap<MusicType, List<AudioClip>>();
		{
			{
				var menuMusic = new ArrayList<AudioClip>();
				{
					URL resource = getClass().getResource(menu_music1);
					AudioClip audioClip = new AudioClip(resource.toString());
					menuMusic.add(audioClip);
				}
				{
					URL resource = getClass().getResource(menu_music2);
					AudioClip audioClip = new AudioClip(resource.toString());
					menuMusic.add(audioClip);
				}
				{
					URL resource = getClass().getResource(menu_music3);
					AudioClip audioClip = new AudioClip(resource.toString());
					menuMusic.add(audioClip);
				}
				musicClips.put(MusicType.Menu, menuMusic);
			}

			{
				var loopMusic = new ArrayList<AudioClip>();
				{
					Arrays.asList(loop_music1, loop_music2, loop_music3, loop_music4, loop_music5).stream()
							.forEach(x -> loopMusic.add(new AudioClip(getClass().getResource(x).toString())));
				}
				musicClips.put(MusicType.Game, loopMusic);
			}

			{
				var gameOverVictory = Arrays.asList(new AudioClip(getClass().getResource(game_over_victory).toString()));
				musicClips.put(MusicType.GameOverVictory, gameOverVictory);
			}

			{
				var gameOverFailure = Arrays.asList(new AudioClip(getClass().getResource(game_over_failure).toString()));
				musicClips.put(MusicType.GameOverFailure, gameOverFailure);
			}
		}

		answerGood = Arrays.asList(new AudioClip(getClass().getResource(answer_good).toString()));
		answerOk = Arrays.asList(new AudioClip(getClass().getResource(answer_ok).toString()));

		AudioClip tooLoud = new AudioClip(getClass().getResource(answer_bad).toString());
		tooLoud.setVolume(0.2);

		answerBad = Arrays.asList(tooLoud);
	}

	/**
	 * Returns random AudioClip from given collection
	 * @param clips
	 * @return 
	 */
	public AudioClip getRandom(List<AudioClip> clips) {
		int x = random.nextInt(clips.size());
		return clips.get(x);
	}

	/**
	 * Play sound of given type
	 * @param soundType
	 */
	public void playSound(SoundType soundType) {
		AudioClip soundToPlay;
		switch (soundType) {
			case AnswerGood:
				soundToPlay = getRandom(answerGood);
				break;
			case AnswerOk:
				soundToPlay = getRandom(answerOk);
				break;
			case AnswerBad:
				soundToPlay = getRandom(answerBad);
				break;
			default:
				throw new RuntimeException("tuntematon SoundType");
		}
		soundToPlay.play();
	}

	/**
	 * start playing music of given type
	 * @param musicType
	 */
	public void startMusic(MusicType musicType) {
		stopMusic();
		musicPlayer = new MusicPlayer(musicType, this);
		Thread thread = new Thread(musicPlayer, "BackgroundMusicPlayer");
		thread.setDaemon(true);
		thread.start();		
	}

	/**
	 * stop playing music
	 */
	public void stopMusic() {
		if (null != musicPlayer) {
			musicPlayer.stopMusic();
			musicPlayer = null;	// just to clarify that this is trash now. will be replaced anyway.
		}		
	}

	// test
	public static void main(String[] args) {
		System.out.println("ALUSSA.. . .  .   .    .    .");
		Scanner s = new Scanner(System.in);
		AudioPlayer player = new AudioPlayer();

		{
			System.out.println("Starting background menuMusic.");
			player.startMusic(MusicType.Menu);
			s.nextLine();
			player.startMusic(MusicType.Game);
			s.nextLine();
			player.startMusic(MusicType.GameOverFailure);
			s.nextLine();
			player.startMusic(MusicType.GameOverVictory);
			s.nextLine();
			System.out.println("Stopping music.");
			player.stopMusic();
		}
		{
			s.nextLine();
			System.out.println("Starting background gameMusic.");
			player.startMusic(MusicType.Game);
			s.nextLine();
			System.out.println("Stopping music.");
			player.stopMusic();
		}

		System.out.println("LOPUSSA.");
	}

}
