package interfaces;

public interface Displayable {

	// Displays some of the contents of an object
	String display();

	default boolean needsUpdate() {
		return false;
	}

}