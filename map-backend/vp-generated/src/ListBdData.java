/**
 * Licensee: durki(Wrocław University of Science and Technology)
 * License Type: Academic
 */
import org.orm.*;
public class ListBdData {
	private static final int ROW_COUNT = 100;
	
	public void listTestData() throws PersistentException {
		System.out.println("Listing User...");
		java.util.List lUserList = BdPersistentManager.instance().getSession().createQuery("From User").setMaxResults(ROW_COUNT).list();
		User[] users = (User[]) lUserList.toArray(new User[lUserList.size()]);
		int length = Math.min(users.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(users[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Changeset...");
		java.util.List lChangesetList = BdPersistentManager.instance().getSession().createQuery("From Changeset").setMaxResults(ROW_COUNT).list();
		Changeset[] changesets = (Changeset[]) lChangesetList.toArray(new Changeset[lChangesetList.size()]);
		length = Math.min(changesets.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(changesets[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing HistoricWayData...");
		java.util.List lHistoricWayDataList = BdPersistentManager.instance().getSession().createQuery("From HistoricWayData").setMaxResults(ROW_COUNT).list();
		HistoricWayData[] historicWayDatas = (HistoricWayData[]) lHistoricWayDataList.toArray(new HistoricWayData[lHistoricWayDataList.size()]);
		length = Math.min(historicWayDatas.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(historicWayDatas[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing HistoricWayNode...");
		java.util.List lHistoricWayNodeList = BdPersistentManager.instance().getSession().createQuery("From HistoricWayNode").setMaxResults(ROW_COUNT).list();
		HistoricWayNode[] historicWayNodes = (HistoricWayNode[]) lHistoricWayNodeList.toArray(new HistoricWayNode[lHistoricWayNodeList.size()]);
		length = Math.min(historicWayNodes.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(historicWayNodes[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing HistoricNodeData...");
		java.util.List lHistoricNodeDataList = BdPersistentManager.instance().getSession().createQuery("From HistoricNodeData").setMaxResults(ROW_COUNT).list();
		HistoricNodeData[] historicNodeDatas = (HistoricNodeData[]) lHistoricNodeDataList.toArray(new HistoricNodeData[lHistoricNodeDataList.size()]);
		length = Math.min(historicNodeDatas.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(historicNodeDatas[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Way...");
		java.util.List lWayList = BdPersistentManager.instance().getSession().createQuery("From Way").setMaxResults(ROW_COUNT).list();
		Way[] ways = (Way[]) lWayList.toArray(new Way[lWayList.size()]);
		length = Math.min(ways.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(ways[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing WayNode...");
		java.util.List lWayNodeList = BdPersistentManager.instance().getSession().createQuery("From WayNode").setMaxResults(ROW_COUNT).list();
		WayNode[] wayNodes = (WayNode[]) lWayNodeList.toArray(new WayNode[lWayNodeList.size()]);
		length = Math.min(wayNodes.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(wayNodes[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Node...");
		java.util.List lNodeList = BdPersistentManager.instance().getSession().createQuery("From Node").setMaxResults(ROW_COUNT).list();
		Node[] nodes = (Node[]) lNodeList.toArray(new Node[lNodeList.size()]);
		length = Math.min(nodes.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(nodes[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing NodeType...");
		java.util.List lNodeTypeList = BdPersistentManager.instance().getSession().createQuery("From NodeType").setMaxResults(ROW_COUNT).list();
		NodeType[] nodeTypes = (NodeType[]) lNodeTypeList.toArray(new NodeType[lNodeTypeList.size()]);
		length = Math.min(nodeTypes.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(nodeTypes[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing WayTag...");
		java.util.List lWayTagList = BdPersistentManager.instance().getSession().createQuery("From WayTag").setMaxResults(ROW_COUNT).list();
		WayTag[] wayTags = (WayTag[]) lWayTagList.toArray(new WayTag[lWayTagList.size()]);
		length = Math.min(wayTags.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(wayTags[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing NodeTag...");
		java.util.List lNodeTagList = BdPersistentManager.instance().getSession().createQuery("From NodeTag").setMaxResults(ROW_COUNT).list();
		NodeTag[] nodeTags = (NodeTag[]) lNodeTagList.toArray(new NodeTag[lNodeTagList.size()]);
		length = Math.min(nodeTags.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(nodeTags[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing WayType...");
		java.util.List lWayTypeList = BdPersistentManager.instance().getSession().createQuery("From WayType").setMaxResults(ROW_COUNT).list();
		WayType[] wayTypes = (WayType[]) lWayTypeList.toArray(new WayType[lWayTypeList.size()]);
		length = Math.min(wayTypes.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(wayTypes[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Key...");
		java.util.List lKeyList = BdPersistentManager.instance().getSession().createQuery("From Key").setMaxResults(ROW_COUNT).list();
		Key[] keys = (Key[]) lKeyList.toArray(new Key[lKeyList.size()]);
		length = Math.min(keys.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(keys[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing KeyNodeType...");
		java.util.List lKeyNodeTypeList = BdPersistentManager.instance().getSession().createQuery("From KeyNodeType").setMaxResults(ROW_COUNT).list();
		KeyNodeType[] keyNodeTypes = (KeyNodeType[]) lKeyNodeTypeList.toArray(new KeyNodeType[lKeyNodeTypeList.size()]);
		length = Math.min(keyNodeTypes.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(keyNodeTypes[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing KeyWayType...");
		java.util.List lKeyWayTypeList = BdPersistentManager.instance().getSession().createQuery("From KeyWayType").setMaxResults(ROW_COUNT).list();
		KeyWayType[] keyWayTypes = (KeyWayType[]) lKeyWayTypeList.toArray(new KeyWayType[lKeyWayTypeList.size()]);
		length = Math.min(keyWayTypes.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(keyWayTypes[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
	}
	
	public static void main(String[] args) {
		try {
			ListBdData listBdData = new ListBdData();
			try {
				listBdData.listTestData();
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
