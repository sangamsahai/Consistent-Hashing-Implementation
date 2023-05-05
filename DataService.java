import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class DataService {

	// This map holds the mapping of <hashValue of the data node, data node>
	TreeMap<Integer, DataNode> dataNodeTreeMap = new TreeMap<Integer, DataNode>();

	// Node names need to be supplied, and this function adds those nodes to
	// 'dataNodeTreeMap'
	public DataService(ArrayList<String> dataNodeNames) {
		for (int i = 0; i < dataNodeNames.size(); i++) {
			this.dataNodeTreeMap.put(getHashValue(dataNodeNames.get(i)), new DataNode(dataNodeNames.get(i)));
		}
	}

	// Returns the data node(next node if we move clockwise) which needs to store
	// the data for the given key
	public DataNode getNextDataNode(String key) {
		for (Integer dataNodeHash : dataNodeTreeMap.keySet()) {
			if (getHashValue(key) < dataNodeHash) {
				return dataNodeTreeMap.get(dataNodeHash);
			}
		}
		return dataNodeTreeMap.firstEntry().getValue();
	}

	// This method adds new node and shifts the data within nodes accordingly
	public DataNode addNewNode(String newNodeName) {
		DataNode newNode = new DataNode(newNodeName);
		int newNodeHashValue = getHashValue(newNodeName);
		this.dataNodeTreeMap.put(newNodeHashValue, newNode);

		// Some data will need to be removed from this nextNode since newNodeName has
		// been added
		DataNode nextNode = getNextDataNode(newNodeName);

		ArrayList<String> keysToBeRemovedFromNextNode = new ArrayList<String>();

		// Iterating over nextNode data to see which data needs to be removed and added
		// to newNode
		for (Map.Entry<String, String> set : nextNode.getDataMapping().entrySet()) {
			String key = set.getKey();
			String value = set.getValue();
			if (getHashValue(key) <= newNodeHashValue) {
				// adding data in newNode
				newNode.getDataMapping().put(key, value);
				keysToBeRemovedFromNextNode.add(key);
			}
		}
		// removing data from nextNode
		for (int i = 0; i < keysToBeRemovedFromNextNode.size(); i++) {
			nextNode.getDataMapping().remove(keysToBeRemovedFromNextNode.get(i));
		}
		return newNode;
	}

	public boolean put(String key, String value) {
		try {
			DataNode node = getNextDataNode(key);
			node.getDataMapping().put(key, value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String get(String key) {
		DataNode node = getNextDataNode(key);
		return node.getDataMapping().get(key);
	}

	public int getHashValue(String str) {
		int sum = 0;
		for (int i = 0; i < str.length(); i++) {
			sum = sum + (int) str.charAt(i);
		}
		return sum % 50;
	}

}