/**
 * Licensee: durki(Wrocław University of Science and Technology)
 * License Type: Academic
 */
import org.orm.*;
public class RetrieveAndUpdateBdData {
	public void retrieveAndUpdateTestData() throws PersistentException {
		PersistentTransaction t = BdPersistentManager.instance().getSession().beginTransaction();
		try {
			User user= (User)BdPersistentManager.instance().getSession().createQuery("From User").setMaxResults(1).uniqueResult();
			// Update the properties of the persistent object
			BdPersistentManager.instance().getSession().update(user);
			
			Changeset changeset= (Changeset)BdPersistentManager.instance().getSession().createQuery("From Changeset").setMaxResults(1).uniqueResult();
			// Update the properties of the persistent object
			BdPersistentManager.instance().getSession().update(changeset);
			
			HistoricWayData historicWayData= (HistoricWayData)BdPersistentManager.instance().getSession().createQuery("From HistoricWayData").setMaxResults(1).uniqueResult();
			// Update the properties of the persistent object
			BdPersistentManager.instance().getSession().update(historicWayData);
			
			HistoricWayNode historicWayNode= (HistoricWayNode)BdPersistentManager.instance().getSession().createQuery("From HistoricWayNode").setMaxResults(1).uniqueResult();
			// Update the properties of the persistent object
			BdPersistentManager.instance().getSession().update(historicWayNode);
			
			HistoricNodeData historicNodeData= (HistoricNodeData)BdPersistentManager.instance().getSession().createQuery("From HistoricNodeData").setMaxResults(1).uniqueResult();
			// Update the properties of the persistent object
			BdPersistentManager.instance().getSession().update(historicNodeData);
			
			Way way= (Way)BdPersistentManager.instance().getSession().createQuery("From Way").setMaxResults(1).uniqueResult();
			// Update the properties of the persistent object
			BdPersistentManager.instance().getSession().update(way);
			
			WayNode wayNode= (WayNode)BdPersistentManager.instance().getSession().createQuery("From WayNode").setMaxResults(1).uniqueResult();
			// Update the properties of the persistent object
			BdPersistentManager.instance().getSession().update(wayNode);
			
			Node node= (Node)BdPersistentManager.instance().getSession().createQuery("From Node").setMaxResults(1).uniqueResult();
			// Update the properties of the persistent object
			BdPersistentManager.instance().getSession().update(node);
			
			NodeType nodeType= (NodeType)BdPersistentManager.instance().getSession().createQuery("From NodeType").setMaxResults(1).uniqueResult();
			// Update the properties of the persistent object
			BdPersistentManager.instance().getSession().update(nodeType);
			
			WayTag wayTag= (WayTag)BdPersistentManager.instance().getSession().createQuery("From WayTag").setMaxResults(1).uniqueResult();
			// Update the properties of the persistent object
			BdPersistentManager.instance().getSession().update(wayTag);
			
			NodeTag nodeTag= (NodeTag)BdPersistentManager.instance().getSession().createQuery("From NodeTag").setMaxResults(1).uniqueResult();
			// Update the properties of the persistent object
			BdPersistentManager.instance().getSession().update(nodeTag);
			
			WayType wayType= (WayType)BdPersistentManager.instance().getSession().createQuery("From WayType").setMaxResults(1).uniqueResult();
			// Update the properties of the persistent object
			BdPersistentManager.instance().getSession().update(wayType);
			
			Key key= (Key)BdPersistentManager.instance().getSession().createQuery("From Key").setMaxResults(1).uniqueResult();
			// Update the properties of the persistent object
			BdPersistentManager.instance().getSession().update(key);
			
			KeyNodeType keyNodeType= (KeyNodeType)BdPersistentManager.instance().getSession().createQuery("From KeyNodeType").setMaxResults(1).uniqueResult();
			// Update the properties of the persistent object
			BdPersistentManager.instance().getSession().update(keyNodeType);
			
			KeyWayType keyWayType= (KeyWayType)BdPersistentManager.instance().getSession().createQuery("From KeyWayType").setMaxResults(1).uniqueResult();
			// Update the properties of the persistent object
			BdPersistentManager.instance().getSession().update(keyWayType);
			
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
	}
	
	public static void main(String[] args) {
		try {
			RetrieveAndUpdateBdData retrieveAndUpdateBdData = new RetrieveAndUpdateBdData();
			try {
				retrieveAndUpdateBdData.retrieveAndUpdateTestData();
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
