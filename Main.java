// region [Imports]
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;
// endregion [Imports]

public class Main {
	public static void main(String[] args) {
		Printers printer = new Printers();

		printer.header();
		Scanner sc = new Scanner(System.in);
		System.out.print(">>> ");
		int option = sc.nextInt();

		while(option == 1) {
			System.out.print("Memory Size: ");
			int memorySize = 1024;

			System.out.print("Number of Processes: ");
			int nProcess = 200;

			Logger logger = new Logger(nProcess);

			Memory memory = new Memory(memorySize / 2, memorySize / 2, logger, printer);

			List<Process> processes = generateRandomProcesses(nProcess, memorySize);

			Thread allocator = new Thread(new Allocator(memory, processes));

			long t1 = System.currentTimeMillis();

			try {
				allocator.start();
				
				while(allocator.isAlive()) {
					printer.clearScreen();
					memory.printMemory();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				allocator.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			memory.waitToExecuteAllProcess();
			
			logger.setExecutedTime(System.currentTimeMillis() - t1);

			printer.clearScreen();

			memory.printMemory();

			printer.summary(memory);

			printer.options(false);

			option = sc.nextInt();
		}

		sc.close();

		printer.clearScreen();

		printer.exit();
	}

	// region [Generate Random Processes]
	public static List<Process> generateRandomProcesses(int nProcess, int memorySize) {
		List<Process> processes = new ArrayList<Process>();
		Random random = new Random();
		Random randomTime = new Random(System.currentTimeMillis());
		int maxTime = 200;
		int mean = memorySize / 2;
		
		while (nProcess > 0) {
			int size = (int) random.nextInt(mean);
			String id = UUID.randomUUID().toString();
			long time = randomTime.nextInt(maxTime);
			if (size > 0 && size <= mean) {
				processes.add(new Process(id, size, time));
				nProcess -= 1;
			}
		}
		return processes;
	}
	// endregion [Generate Random Processes]
}
