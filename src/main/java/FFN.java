package main.java;

import main.java.activators.*;
import main.java.nn.FeedForward;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class FFN {
    private static final String FILENAME = "..\\..\\sample_data\\data.txt";

    public static void main (String[] args) {
        FeedForward ff = new FeedForward(16, 10, new int[]{22,22} , new SigmoidActivator());
        // ff.Train(inputs, outputs, 5);

        BufferedReader br = null;
		FileReader fr = null;
		try {
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
    }
}