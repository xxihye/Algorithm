package kakao;

import java.util.ArrayList;

public class JustThatSong {
	
	public static void main(String[] args) {
		JustThatSong jts = new JustThatSong();
		System.out.println(jts.solution("ABCDEFG", new String[] {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
		System.out.println(jts.solution("CC#BCC#BCC#BCC#B", new String[] {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"}));
		System.out.println(jts.solution("ABC", new String[] {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
	}
	
	public String solution(String m, String[] musicinfos) {
		m = convertHalfSharp(m);
		
		ArrayList<Song> songs = new ArrayList<>();
		for(String musicinfo : musicinfos){
			String[] info = musicinfo.split(",");
			int playingTime = calTime(info[1]) - calTime(info[0]);
			String playing = getPlayingSheet(info[3], playingTime);
			
			if(playing.contains(m))
				songs.add(new Song(info[2], playingTime));
		}
		
		if(songs.isEmpty()) return "(None)";
		
		songs.sort(null);
		
		return songs.get(0).getName();
	}
	
	private String getPlayingSheet(String s, int playingTime) {
		if(s.contains("#"))
			s = convertHalfSharp(s);
		
		int length = s.length();
		if(length == playingTime) return s;
		else if(length > playingTime) return s.substring(0, playingTime);
		
		StringBuilder sb = new StringBuilder();
		int q = playingTime / length;
		
		return s.repeat(q) + s.substring(0, playingTime % length);
	}
	
	
	private String convertHalfSharp(String sheet){
		return sheet.replaceAll("C#", "c")
		  .replaceAll("D#", "d")
		  .replaceAll("F#", "f")
		  .replaceAll("G#", "g")
		  .replaceAll("A#", "a");
	}
	
	private static int calTime(String t) {
		return (t.charAt(0) - '0') *  600 + (t.charAt(1) - '0') * 60 + (t.charAt(3) - '0') * 10 + (t.charAt(4) - '0');
	}
	
	static class Song implements Comparable<Song>{
		String name;
		int time;
		
		public Song(String name, int time) {
			this.name = name;
			this.time = time;
		}
		
		public String getName() {
			return name;
		}
		
		@Override
		public int compareTo(Song o) {
			return o.time - this.time;
		}
	}
}
