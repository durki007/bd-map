/**
 * Licensee: durki(Wrocław University of Science and Technology)
 * License Type: Academic
 */
import org.orm.*;
public class CreateBdDatabaseSchema {
	public static void main(String[] args) {
		try {
			ORMDatabaseInitiator.createSchema(BdPersistentManager.instance());
			BdPersistentManager.instance().disposePersistentManager();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
