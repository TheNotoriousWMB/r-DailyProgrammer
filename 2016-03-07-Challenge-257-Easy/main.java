import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;

public class main {
	public static final int CURR_YEAR = Calendar.getInstance().get(Calendar.YEAR);
	
	public static void main(String[] args) throws FileNotFoundException {
		String file = "presidents.csv";
		BufferedReader br = null;
		String line = "";
		String delim = ",";

		LinkedList<President> presidents = new LinkedList<President>();

		try {
			br = new BufferedReader(new FileReader(file));

			// Cancel out first line
			line = br.readLine();

			while ((line = br.readLine()) != null) {
				String[] data = line.split(delim);

				data[1].replaceAll("\\s", "");

				int birth = Integer
						.parseInt(data[1].substring(data[1].length() - 4));

				data[3].replaceAll("\\s", "");
				int death = 0;
				// Check for death year
				if (data[3].matches(".*\\d.*")) {
					death = Integer
							.parseInt(data[3].substring(data[3].length() - 4));
					presidents.add(new President(data[0], birth, death));
				} else
					presidents.add(new President(data[0], birth));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int min = CURR_YEAR;
		
		for (President p : presidents)
			if (p.getBirthYear() < min)
				min = p.getBirthYear();
		
		// Array of all years from the birth year of the oldest president to now 
		int[] years = new int[CURR_YEAR - min + 1];
		
		for (President p : presidents)
		{
			for (int i = p.getBirthYear() - min; i <= p.getDeathYear() - min; ++i)
				years[i]++;
		}
		
		System.out.println("Presidents Alive | Years");
		System.out.println("=================|================================");
		
		for (int i = 1; i <= getMax(years); ++i)
		{
			System.out.printf("%3d%15s", i, "|");
			for (int j = 0; j < years.length; ++j)
				if (years[j] == i)
					System.out.print((j + min) + ", ");
			System.out.println();
		}
	}
	
	public static int getMax(int[] years)
	{
		int max = 0;
		
		for (int i = 0; i < years.length; ++i)
			if (years[i] > max)
				max = years[i];
		
		return max;
	}
}
