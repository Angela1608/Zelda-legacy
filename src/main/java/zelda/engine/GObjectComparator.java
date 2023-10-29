package zelda.engine;

import java.util.Comparator;

public class GObjectComparator implements Comparator<GObject> {

	@Override
	public int compare(GObject firstObject, GObject secondObject) {
		return Integer.compare(firstObject.getZ(), secondObject.getZ());
	}
}
