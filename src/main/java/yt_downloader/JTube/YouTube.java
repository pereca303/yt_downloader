package yt_downloader.JTube;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YouTube {

	private String watch_url; // video url
	private String watch_html; // html from wathc_url http requst

	private String js_url; // url parsed from watch_html
	private String js; // js code from js_url http request

	private boolean age_restricted; // is video age restricted

	private Stream streams; // available streams for particular video

	private String video_id; // video id parsed from watch_url

	public YouTube(String video_url) {

		// assume video_url is valid youTube video url
		// https://www.youtube.com/watch?v=Q9f6F2fPHSM
		// in format given above

		this.watch_url = video_url;
		this.video_id = video_url.substring(("https://www.youtube.com/watch?v=".length()),
				this.watch_url.length());
		System.out.println("Video url:" + this.watch_url);
		// System.out.println("Video id: " + this.video_id);

		try {

			URL url = new URL(this.watch_url);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// stream to read response
			BufferedReader buffReader = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));

			StringBuilder response = new StringBuilder();
			String responseLine;

			while ((responseLine = buffReader.readLine()) != null) {
				response.append(responseLine);
				response.append("\r");
			}

			buffReader.close();

			this.watch_html = response.toString();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		YouTube yt = new YouTube("https://www.youtube.com/watch?v=Q9f6F2fPHSM");
	}

}
