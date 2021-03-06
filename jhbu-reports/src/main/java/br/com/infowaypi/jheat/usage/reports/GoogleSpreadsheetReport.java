package br.com.infowaypi.jheat.usage.reports;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gdata.client.GoogleAuthTokenFactory;
import com.google.gdata.client.authn.oauth.OAuthException;
import com.google.gdata.client.http.HttpGDataRequest;
import com.google.gdata.client.http.HttpUrlConnectionSource;
import com.google.gdata.client.http.JdkHttpUrlConnectionSource;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

/**
 * Envia os dados para uma planilha google de acordo com a configuração do
 * cliente
 * 
 * @since 03/2016
 */
public class GoogleSpreadsheetReport implements UsageReportEngine {

	private static final String CLIENT_ID = "233529998695-3vm4mludumejiq7h6kolpd58ibarun8n.apps.googleusercontent.com";
	private static final String CLIENT_SECRET = "xmSIRhFt8GdUzchCbA4xEn9o";
	private static final String SPREADSHEET = "https://spreadsheets.google.com/feeds/worksheets/1kSWBugXpX9KisgMekl6IPsUCzJBsp-6_GptcFQe69O8/private/basic";
	private static final String SCOPE = "https://spreadsheets.google.com/feeds/worksheets/private/basic";
//			"https://spreadsheets.google.com/feeds";
	private static final String REDIRECT_URI = "";

	public void updateGoogleSpreadsheet(Object arg1) throws AuthenticationException, MalformedURLException, IOException,
			ServiceException, GeneralSecurityException {
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
		GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream("src/main/resources/jheatbased-usage-1846431178ca.json")).createScoped(Collections.singleton(SCOPE));
		
		SpreadsheetService spreadsheetService = new SpreadsheetService("jheatbased-usage");
		spreadsheetService.setProtocolVersion(SpreadsheetService.Versions.V3);
		HttpGDataRequest.Factory factory = new HttpGDataRequest.Factory();
		factory.setAuthToken(new GoogleAuthTokenFactory.UserToken(credential.getAccessToken()));
		spreadsheetService.setRequestFactory(factory);
		
		URL feedURL = new URL(SCOPE);
		SpreadsheetFeed feed = spreadsheetService.getFeed(feedURL, SpreadsheetFeed.class); 
		SpreadsheetEntry sheet = feed.getEntries().iterator().next();
		List<WorksheetEntry> worksheets = sheet.getWorksheets();
		for (WorksheetEntry worksheetEntry : worksheets) {
			System.out.println(worksheetEntry.getTitle().getPlainText());
		}
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException, ServiceException, OAuthException, GeneralSecurityException {
//		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//				new NetHttpTransport(), new JacksonFactory(), CLIENT_ID,
//				CLIENT_SECRET, Collections.singleton(SCOPE)).build();
//		 GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();
//		 url.setRedirectUri("http://localhost:8080/test");
//		System.out.println(url);
		
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
		GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream("src/main/resources/jheatbased-usage-1846431178ca.json")).createScoped(Collections.singleton(SCOPE));
		HttpGDataRequest.Factory factory = new HttpGDataRequest.Factory();
		factory.setAuthToken(new GoogleAuthTokenFactory.UserToken(credential.getAccessToken()));
//		factory.setConnectionSource(new JdkHttpUrlConnectionSource());
		
		SpreadsheetService service = new SpreadsheetService("jheatbased-usage");
		service.setProtocolVersion(SpreadsheetService.Versions.V3);
		service.setRequestFactory(factory);
		
		URL feedURL = new URL(SPREADSHEET);
		WorksheetFeed feed = service.getFeed(feedURL, WorksheetFeed.class); 
//		SpreadsheetEntry sheet = feed.getEntries().iterator().next();
//		List<WorksheetEntry> worksheets = sheet.getWorksheets();
		for (WorksheetEntry worksheetEntry : feed.getEntries()) {
			System.out.println(worksheetEntry.getTitle().getPlainText());
		}
	}

	private static String getStringFromInputStream(FileInputStream is) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	public void proceedAuthorization(Object arg1) {

	}

	public void update(Observable arg0, Object arg1) {

	}

}
