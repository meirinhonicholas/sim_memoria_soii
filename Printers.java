// region [Imports]
import java.text.SimpleDateFormat;
// endregion [Imports]

public class Printers {
	public void clearScreen() {   
		System.out.print("\033[H\033[2J");   
		System.out.flush();   
	}

	public void header(){
		System.out.println("+=============================================================+");
		System.out.println("|              Catholic University of Santos                  |");
		System.out.println("|                                                             |");
		System.out.println("|                     University Assigment                    |");
		System.out.println("|              Class/Subject: Operating Systems               |");
		System.out.println("|                        Semester: 6                          |");
		System.out.println("|                                                             |");
		System.out.println("|                        Developed by:                        |");
		System.out.println("|                   Arthur Silveira Chaves                    |");
		System.out.println("|                   Beatriz Jonas Justino                     |");
		System.out.println("|                  Nicholas Meirinho Perez                    |");
		System.out.println("|                  Rafael Santos de Oliveira                  |");
		System.out.println("|                   Oscar Alves Capella Neto                  |");
		System.out.println("|          Luiz Otavio Bissiato Pinheiro da Silva             |");
		System.out.println("|                                                             |");
		System.out.println("+=============================================================+");
		System.out.println("|                                                             |");
		System.out.println("|                      Application goal:                      |");
		System.out.println("|      Solve a allocation problem with First Fit Algorithm    |");
		System.out.println("|                                                             |");
		System.out.println("+=============================================================+");
		options();
		System.out.println();
	}

	public void exit() {
		System.out.println("+=============================================================+");
		System.out.println("|                                                             |");
		System.out.println("|                  Goodbye see you later!!                    |");
		System.out.println("|                                                             |");
		System.out.println("+=============================================================+");
	}

	public void options() {
		options(true);
	}
	
	public void options(boolean header) {
		if (header) {
			System.out.println("|    1 - Run First Fit Algorithm                              |");
			System.out.println("|    0 - Exit                                                 |");
			System.out.println("+=============================================================+");
		} else {
			System.out.println("+=============================================================+");
			System.out.println("|    1 - Run First Fit Algorithm                              |");
			System.out.println("|    0 - Exit                                                 |");
			System.out.println("+=============================================================+");
		}
	}

	public void table(int rows, int columns, Frame[][] frames){
		int size;
		if (rows > 50) {
			size = 50;
		} else { size = rows; }
		System.out.println("+=============================================================+");
		System.out.println("|        Memory State (max: 2500 processes will be show)      |");
		System.out.println("+=============================================================+");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (frames[i][j].isAvailable()) {
					System.out.print("|    ");
				} else {
					System.out.print("| " + frames[i][j].getProcess().substring(0, 2) + " ");
				}
			}
			System.out.print("|\n");
		}
		System.out.println("+=============================================================+");
		System.out.println();
		System.out.println();
	}

	public void summary(Memory memory){
		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss.SSS");
		String timeFormatted = sdf.format(memory.getLogger().getExecutedTime());
		String meanTimeFormatted = sdf.format(memory.getLogger().getMeanTimePerProcess());
		System.out.println("+=============================================================+");
		System.out.println("|                          Summary                            |");
		System.out.println("+=============================================================+");
		System.out.println("Execution time (mm:ss.SSS): " + timeFormatted);
		System.out.println("Mean time of each Process (mm:ss.SSS): " + meanTimeFormatted);
		System.out.println("Multiprogram Level: " + memory.getLogger().getMultiprogramLevel());
		System.out.println("Unrunned Processes: " + memory.getLogger().getUnrunnedProcess());
		System.out.println("Runned Processes: " + memory.getLogger().getRunnedProcess());
		System.out.println("Memory Partitions at the end: " + memory.getLogger().getPartitions(memory.getLastView()));
		System.out.println();
		System.out.println();
	}
}
