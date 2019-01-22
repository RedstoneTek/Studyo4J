import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.tek.studyo.Studyo;
import com.tek.studyo.entities.AuthenticatedUser;
import com.tek.studyo.entities.UserCredentials;

public class Test {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Studyo.initialize();
		
		UserCredentials credentials = new UserCredentials("lauthomas", "elephant");
		CompletableFuture<AuthenticatedUser> future = Studyo.authenticate(credentials);
		AuthenticatedUser user = future.get();
		
		System.out.println("Obtained session token: " + user.getSessionToken());
		
		Studyo.shutdown();
	}
	
}
