package custom.space;

import custom.objects.dimensions3.TriangularPyramid;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class Euclidean3DInputOutput {

    public static final String root = "simulations/";
    public static final String extension = ".tsv";
    private static final String SEPARATOR = "_";

    public static double read(String string) {
        File file = findFile(string);
        return readFile(file);
    }

    public static double readFile(File file) {
        long startNano = System.nanoTime();
        System.out.println("Reading file " + file.getPath());


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            bufferedReader.lines().forEach(TriangularPyramid::parse);
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Finished reading file " + file.getPath() + " . It took " + ((System.nanoTime() - startNano) * 1e-9) + " seconds");

        String timeAsString = file.getName().split(SEPARATOR)[1].split(extension)[0];
        return Double.parseDouble(timeAsString);
    }

    /**
     * @param string file root name will return latest file, if time is specified it will return that time
     * @return file
     */
    private static File findFile(String string) {
        File rootFile = new File(root);

        File file = new File(root + string);
        if (!file.exists()) {
            file = Arrays.stream(Objects.requireNonNull(file.getParentFile().listFiles()))
                    .filter(file1 -> file1.getName().split(SEPARATOR)[0].equals(string))
                    .reduce((file1, file2) -> {
                        int isBigger = file1.getName().split(SEPARATOR)[1].compareTo(file2.getName());
                        if (isBigger > 0) return file1;
                        else if (isBigger < 0) return file2;
                        else throw new IllegalStateException("Duplicated file");
                    }).orElseThrow(() -> new IllegalArgumentException("File not found"));
        }
        return file;
    }

    public static void save(String string) {
        try {
            long startTime = System.nanoTime();
            Double time = Euclidean3DTimeStepper.getTime();
            if (time == null) throw new IllegalStateException("A simulation without time cannot be saved");
            File file = new File(root + string + SEPARATOR + time + extension);
            //noinspection ResultOfMethodCallIgnored
            file.getParentFile().mkdirs();
            boolean newFile = file.createNewFile();
            if (!newFile) throw new IllegalStateException("File already exists");
            FileWriter fileWriter = new FileWriter(file);
            Euclidean3DSpace.getPolyhedra().forEach(polyhedra -> {
                try {
                    fileWriter.write(polyhedra.toString() + "\r");
                } catch (IOException e) {
                    e.printStackTrace();

                }
            });
            fileWriter.flush();
            System.out.println("Finished saving file " + file.getPath() + " . It took " + ((System.nanoTime() - startTime) * 1e-9) + " seconds");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void delete(String string) {
        findFile(string).delete();
    }


}
