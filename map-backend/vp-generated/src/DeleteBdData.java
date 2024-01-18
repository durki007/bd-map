/**
 * Licensee: durki(Wrocław University of Science and Technology)
 * License Type: Academic
 */
import org.orm.*;
public class DeleteBdData {
	public void deleteTestData() throws PersistentException {
		PersistentTransaction t = BdPersistentManager.instance().getSession().beginTransaction();
		try {
			User user= (User)BdPersistentManager.instance().getSession().createQuery("From User").setMaxResults(1).uniqueResult();
			BdPersistentManager.instance().getSession().delete(user);
			
			Changeset changeset= (Changeset)BdPersistentManager.instance().getSession().createQuery("From Changeset").setMaxResults(1).uniqueResult();
			BdPersistentManager.instance().getSession().delete(changeset);
			
			HistoricWayData historicWayData= (HistoricWayData)BdPersistentManager.instance().getSession().createQuery("From HistoricWayData").setMaxResults(1).uniqueResult();
			BdPersistentManager.instance().getSession().delete(historicWayData);
			
			HistoricWayNode historicWayNode= (HistoricWayNode)BdPersistentManager.instance().getSession().createQuery("From HistoricWayNode").setMaxResults(1).uniqueResult();
			BdPersistentManager.instance().getSession().delete(historicWayNode);
			
			HistoricNodeData historicNodeData= (HistoricNodeData)BdPersistentManager.instance().getSession().createQuery("From HistoricNodeData").setMaxResults(1).uniqueResult();
			BdPersistentManager.instance().getSession().delete(historicNodeData);
			
			Way way= (Way)BdPersistentManager.instance().getSession().createQuery("From Way").setMaxResults(1).uniqueResult();
			BdPersistentManager.instance().getSession().delete(way);
			
			WayNode wayNode= (WayNode)BdPersistentManager.instance().getSession().createQuery("From WayNode").setMaxResults(1).uniqueResult();
			BdPersistentManager.instance().getSession().delete(wayNode);
			
			Node node= (Node)BdPersistentManager.instance().getSession().createQuery("From Node").setMaxResults(1).uniqueResult();
			BdPersistentManager.instance().getSession().delete(node);
			
			NodeType nodeType= (NodeType)BdPersistentManager.instance().getSession().createQuery("From NodeType").setMaxResults(1).uniqueResult();
			BdPersistentManager.instance().getSession().delete(nodeType);
			
			WayTag wayTag= (WayTag)BdPersistentManager.instance().getSession().createQuery("From WayTag").setMaxResults(1).uniqueResult();
			BdPersistentManager.instance().getSession().delete(wayTag);
			
			NodeTag nodeTag= (NodeTag)BdPersistentManager.instance().getSession().createQuery("From NodeTag").setMaxResults(1).uniqueResult();
			BdPersistentManager.instance().getSession().delete(nodeTag);
			
			WayType wayType= (WayType)BdPersistentManager.instance().getSession().createQuery("From WayType").setMaxResults(1).uniqueResult();
			BdPersistentManager.instance().getSession().delete(wayType);
			
			Key key= (Key)BdPersistentManager.instance().getSession().createQuery("From Key").setMaxResults(1).uniqueResult();
			BdPersistentManager.instance().getSession().delete(key);
			
			KeyNodeType keyNodeType= (KeyNodeType)BdPersistentManager.instance().getSession().createQuery("From KeyNodeType").setMaxResults(1).uniqueResult();
			BdPersistentManager.instance().getSession().delete(keyNodeType);
			
			KeyWayType keyWayType= (KeyWayType)BdPersistentManager.instance().getSession().createQuery("From KeyWayType").setMaxResults(1).uniqueResult();
			BdPersistentManager.instance().getSession().delete(keyWayType);
			
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
	}
	
	public static void main(String[] args) {
		try {
			DeleteBdData deleteBdData = new DeleteBdData();
			try {
				deleteBdData.deleteTestData();
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
