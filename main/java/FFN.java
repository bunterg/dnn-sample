package main.java;

import main.java.activators.*;
import main.java.nn.FeedForward;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.FileSystems;
public class FFN {
    private static final String FILENAME = "sample_data\\data.txt";

    public static void main (String[] args) {
        String file = new File(FILENAME).getAbsolutePath();
        Path path = Paths.get(FILENAME);
        BufferedReader br = null;
        FileReader fr = null;
        int inputSize = 256;
        int outputSize = 10;
        double[][] inputs;
        double[][] outputs;
        FeedForward ff = new FeedForward(inputSize, outputSize, new int[]{22,22} , new SigmoidActivator());
        // Path path = FileSystems.getDefault().getPath(FILENAME);
        // System.out.println(path);
        // BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
		try {
			fr = new FileReader(file);
            br = new BufferedReader(fr);
            int lineCount = (int) Files.lines(path).count();
            inputs = new double[lineCount][inputSize];
            outputs = new double[lineCount][outputSize];
            String sCurrentLine;
            int j = 0;
			// String sCurrentLine = br.readLine();
			while ((sCurrentLine = br.readLine()) != null) {
                String[] arg = sCurrentLine.split(" ");
                double[] input = new double[inputSize];
                double[] output = new double[outputSize];
                for (int i = 0; i < input.length; i++) {
                    input[i] = new Double(arg[i]);
                }
                for (int i = 0; i < output.length ; i++) {
                    output[i] = new Double(arg[i + inputSize]);
                }
                inputs[j] = input;
                outputs[j] = output;
                j++;
            }
            ff.Train(inputs, outputs, 5);
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