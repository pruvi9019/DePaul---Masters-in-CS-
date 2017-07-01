package transportnetwork.factory;

import transportnetwork.impl.RouteLedgerImpl;

public class RouteLedger {

	public static RouteLedgerImpl createNewPathLog() {
		return new RouteLedgerImpl();
	}
}
