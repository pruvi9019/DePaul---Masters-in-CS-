package transportnetwork.factory;

import transportnetwork.impl.ShortestPathImpl;

public class TransNetworkBranch {


	public static ShortestPathImpl createNewNetwork() {
		return new ShortestPathImpl();
	}

}
