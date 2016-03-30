package br.com.infowaypi.jheat.usage.reports;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthException;
import com.google.gdata.client.authn.oauth.OAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthUtil;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
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
	private static final String SCOPE = "https://spreadsheets.google.com/feeds";
	private static final String REDIRECT_URI = "";

	public void updateGoogleSpreadsheet(Object arg1)
			throws AuthenticationException, MalformedURLException, IOException, ServiceException {
		SpreadsheetService spreadsheetService = new SpreadsheetService("jheatbased-usage");
		spreadsheetService.setProtocolVersion(SpreadsheetService.Versions.V3);
		URL sheetURL = new URL(SCOPE);
		SpreadsheetFeed feed = spreadsheetService.getFeed(sheetURL, SpreadsheetFeed.class);
		SpreadsheetEntry sheet = feed.getEntries().iterator().next();
		List<WorksheetEntry> worksheets = sheet.getWorksheets();
		for (WorksheetEntry worksheetEntry : worksheets) {
			System.out.println(worksheetEntry.getTitle().getPlainText());
		}
	}

	public static void main(String[] args) throws IOException, ServiceException, OAuthException {
		// HttpTransport transport = new ApacheHttpTransport();
		// Credential credential =new
		// Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken("233529998695-3vm4mludumejiq7h6kolpd58ibarun8n.apps.googleusercontent.com");
		// HttpRequestFactory requestFactory =
		// transport.createRequestFactory(credential);
		// GenericUrl url = new
		// GenericUrl("https://spreadsheets.google.com/feeds/spreadsheets/**1kSWBugXpX9KisgMekl6IPsUCzJBsp-6_GptcFQe69O8");
		// HttpResponse response =
		// requestFactory.buildGetRequest(url).execute();
		// System.out.println(response.getContentType()+"
		// "+response.getContent());

		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(new NetHttpTransport(), new JacksonFactory(),
				CLIENT_ID, CLIENT_SECRET, Collections.singleton(SCOPE)).build();
		GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();
//		URLConnection connection = new URL(url.toString()).openConnection();
//		connection.
		System.out.println(url);
	}
	
	

	public void proceedAuthorization(Object arg1) {
		
	}

	protected AuthorizationCodeFlow initializeFlow() throws IOException {
		return new GoogleAuthorizationCodeFlow.Builder(new NetHttpTransport(), new JacksonFactory(),
				CLIENT_ID, CLIENT_SECRET, Collections.singleton(SCOPE)).build();
//						.setDataStoreFactory().setAccessType("offline").build();
	}

	public void update(Observable arg0, Object arg1) {

	}

}
