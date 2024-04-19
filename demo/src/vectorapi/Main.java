package vectorapi;

import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorSpecies;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main
{
    public static void main(String[] args)
    {
        int arrayLength = 512000000*4;
        float[] fa = new float[arrayLength];
        float[] fb = new float[arrayLength];
        float[] fc = new float[arrayLength];
        LocalDateTime taskStarted, taskCompleted;

        Random r = new Random();
        IntStream.range(0, arrayLength).forEach(i ->
        {
            fa[i] = r.nextFloat();
            fb[i] = r.nextFloat();
        });
        taskStarted = LocalDateTime.now();
        scalarComputation(fa, fb, fc);
        taskCompleted = LocalDateTime.now();

        System.out.format("\nTraditional scalar computation: %d milliseconds", ChronoUnit.MILLIS.between(taskStarted, taskCompleted));

        taskStarted = LocalDateTime.now();
        vectorComputation(fa, fb, fc);
        taskCompleted = LocalDateTime.now();

        System.out.format("\nVector computation: %d milliseconds", ChronoUnit.MILLIS.between(taskStarted, taskCompleted));

        //IntStream.range(0, arrayLength).forEach(i -> System.out.format("\n%f", fc[i]));

    }

    private static void scalarComputation(float[] a, float[] b, float[] c)
    {
        for (int i = 0; i < a.length; i++) {
            c[i] = (a[i] * a[i] + b[i] * b[i]) * -1.0f;
        }
    }

    private static void vectorComputation(float[] a, float[] b, float[] c)
    {
        //VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;
        VectorSpecies<Float> SPECIES = FloatVector.SPECIES_MAX;

        int i = 0;
        int upperBound = SPECIES.loopBound(a.length);
        for (; i < upperBound; i += SPECIES.length())
        {
            var va = FloatVector.fromArray(SPECIES, a, i);
            var vb = FloatVector.fromArray(SPECIES, b, i);
            var vc = va.mul(va)
                    .add(vb.mul(vb))
                    .neg();
            vc.intoArray(c, i);
        }

        System.out.format("\n%d items computed with vector api", i);
        // cleanup remaining items which did not align within the data-width
        for (; i < a.length; i++)
        {
            c[i] = (a[i] * a[i] + b[i] * b[i]) * -1.0f;
        }
    }

}
