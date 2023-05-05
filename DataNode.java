import java.util.HashMap;

public class DataNode {
	
	private String nodeName;
	private HashMap<String,String> dataMapping = new HashMap<String,String>();
	
	public DataNode(String nodeName)
	{
		this.nodeName = nodeName;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public HashMap<String, String> getDataMapping() {
		return dataMapping;
	}

	public void setDataMapping(HashMap<String, String> dataMapping) {
		this.dataMapping = dataMapping;
	}


}
