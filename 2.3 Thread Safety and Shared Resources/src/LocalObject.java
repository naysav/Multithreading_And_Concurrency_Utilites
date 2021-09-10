public class LocalObject {
	private int field = 0;

	public int getField() {
		return field;
	}

	public void setField(int field) {
		this.field = field;
	}

	public void doubleField() {
		this.field *= 2;
		System.out.println("Doubled Field: " + this.field);
	}
}
