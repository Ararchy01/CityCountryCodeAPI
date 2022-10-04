package alaland.CityCountryCodeAPI;

import java.io.FileInputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

/**
 * City Country Code API access point
 * 
 * @author Daichi Araki
 *
 */
public class App {
	private final static String JSON_FILE_NAME = "src/main/resources/citycountrycodeapi01-firebase-adminsdk-szg8u-b43e985c4e.json";
	private final static String URL = "https://citycountrycodeapi01.firebaseio.com";

	public static void main(String[] args) throws Exception {
		// Firebase Initialization
		FirebaseApp.initializeApp(FirebaseOptions.builder()
				.setCredentials(GoogleCredentials.fromStream(new FileInputStream(JSON_FILE_NAME))).setDatabaseUrl(URL)
				.build());

		final Firestore db = FirestoreClient.getFirestore();
		for (QueryDocumentSnapshot d : db.collection("cities").limit(20).get().get()
				.getDocuments()) {
			System.out.println(String.format("%-20s %-20s", d.getString("City"), d.getString("Country")));
		}
	}
}
