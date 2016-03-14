import java.util.Calendar;

public class President {
	private String name;
	private int birthYear;
	private int deathYear;

	public President(String n, int b) {
		this.name = n;
		this.birthYear = b;
		this.deathYear = Calendar.getInstance().get(Calendar.YEAR);
	}

	public President(String n, int b, int d) {
		this.name = n;
		this.birthYear = b;
		this.deathYear = d;
	}

	@Override
	public String toString() {
		return (this.name + ", born: " + this.birthYear + " died: " + this.deathYear);
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public int getDeathYear() {
		return deathYear;
	}

	public void setDeathYear(int deathYear) {
		this.deathYear = deathYear;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
