/**
 * Licensee: durki(Wrocław University of Science and Technology)
 * License Type: Academic
 */
import org.orm.*;
public class CreateBdData {
	public void createTestData() throws PersistentException {
		PersistentTransaction t = BdPersistentManager.instance().getSession().beginTransaction();
		try {
			User user = new User();			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : changeset, role, password, email, username
			BdPersistentManager.instance().getSession().save(user);
			
			Changeset changeset = new Changeset();			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : historicWayData, historicNodeData, user, creation_date
			BdPersistentManager.instance().getSession().save(changeset);
			
			HistoricWayData historicWayData = new HistoricWayData();			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : historicWayNode, way, changeset, version, timestamp, name
			BdPersistentManager.instance().getSession().save(historicWayData);
			
			HistoricWayNode historicWayNode = new HistoricWayNode();			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : historicWayData, node2, node1, version
			BdPersistentManager.instance().getSession().save(historicWayNode);
			
			HistoricNodeData historicNodeData = new HistoricNodeData();			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : historicWayNode1, historicWayNode, node, changeset, version, timestamp, pos_y, pos_x
			BdPersistentManager.instance().getSession().save(historicNodeData);
			
			Way way = new Way();			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : wayTag, wayNode, historicWayData, wayType, is_blocked, timestamp, name
			BdPersistentManager.instance().getSession().save(way);
			
			WayNode wayNode = new WayNode();			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : node2, node1, way
			BdPersistentManager.instance().getSession().save(wayNode);
			
			Node node = new Node();			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : nodeTag, wayNode1, wayNode, historicNodeData, nodeType, timestamp, is_blocked, pos_y, pos_x
			BdPersistentManager.instance().getSession().save(node);
			
			NodeType nodeType = new NodeType();			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : keyNodeType, node, type
			BdPersistentManager.instance().getSession().save(nodeType);
			
			WayTag wayTag = new WayTag();			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : key, way, version
			BdPersistentManager.instance().getSession().save(wayTag);
			
			NodeTag nodeTag = new NodeTag();			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : key, node, version
			BdPersistentManager.instance().getSession().save(nodeTag);
			
			WayType wayType = new WayType();			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : keyWayType, way, type
			BdPersistentManager.instance().getSession().save(wayType);
			
			Key key = new Key();			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : nodeTag, wayTag, keyWayType, keyNodeType, value
			BdPersistentManager.instance().getSession().save(key);
			
			KeyNodeType keyNodeType = new KeyNodeType();			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : key, nodeType
			BdPersistentManager.instance().getSession().save(keyNodeType);
			
			KeyWayType keyWayType = new KeyWayType();			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : key, wayType
			BdPersistentManager.instance().getSession().save(keyWayType);
			
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			CreateBdData createBdData = new CreateBdData();
			try {
				createBdData.createTestData();
			}
			finally {
				BdPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
