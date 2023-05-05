import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/*
 To understand these tests, the below mapping of String and its hash value will help.
 "cloudJunky" - 14
 "codeCreature" - 38
 "dataMonster" - 4
 "sangam" - 31
 "xangam" - 36
 */
class DataServiceTest {

	/* Both the Strings("" and "") will be stores on the node "codeCreature" because it's
	 * hash value is 38 which is the next available node for Strings "sangam"(31) and "xangam"(36)
	 * */
	@Test
	void simpleGetAndSetOperation() {
		ArrayList<String> serverNames = new ArrayList<String>();
		serverNames.add("cloudJunky");
		serverNames.add("codeCreature");
		serverNames.add("dataMonster");
		DataService d = new DataService(serverNames);

		d.put("sangam", "yes boss");
		d.put("xangam", "no boss");

		assertEquals(d.get("sangam"), "yes boss");
		assertEquals(d.get("xangam"), "no boss");
	}

	
	/* Once we add the new node "ancientBeast"(hash value 33), the entry for "sangam"(hash value 31)
	 * will be shifted from "codeCreature"(hash value 38) and move to "ancientBeast"(hash value 33)
	 * */
	@Test
	void getAndSetAfterAddingNewNode() {
		ArrayList<String> serverNames = new ArrayList<String>();
		serverNames.add("cloudJunky");
		serverNames.add("codeCreature");
		serverNames.add("dataMonster");
		DataService d = new DataService(serverNames);

		d.put("sangam", "yes boss");
		d.put("xangam", "no boss");

		DataNode ancientBeast = d.addNewNode("ancientBeast");
		DataNode codeCreature = d.dataNodeTreeMap.get(d.getHashValue("codeCreature"));

		assertTrue(ancientBeast.getDataMapping().containsKey("sangam"));
		assertFalse(codeCreature.getDataMapping().containsKey("sangam"));
		assertTrue(codeCreature.getDataMapping().containsKey("xangam"));

		assertEquals(d.get("sangam"), "yes boss");
		assertEquals(d.get("xangam"), "no boss");
	}

}
