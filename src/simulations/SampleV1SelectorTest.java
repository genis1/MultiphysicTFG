package simulations;

import custom.objects.dimensions3.Polyhedron;
import custom.space.Euclidean3DInputOutput;
import external.Color;

import java.util.Collection;

public class SampleV1SelectorTest {

    public static void main(String[] args) {
        Euclidean3DInputOutput.read(SampleV1Simulator.SAMPLE_V1_INITIAL_MESH);
        testSelectR0();
        testSelectR1();
        testSelectR2();
        testSelectR3();
        testSelectR4();
        testSelectT1();
        testSelectT2();
        testSelectT3();
        testSelectT4();
        testSelectT5();
        testSelectT6();
        testSelectQ1();
        testSelectQ2();
        testSelectQ3();
        testSelectQ4();
        testSelectQ5();
        testSelectQ6();
        testSelectC1();
        testSelectC2();
        testSelectC3();
        testSelectC4();
        testSelectC5();
    }

    private static void testSelectR0() {
        Collection<Polyhedron> selected = SampleV1Constructor.selectR0();
        if (selected.size() == 60) System.out.println(Color.GREEN + "R0 selection successful" + Color.RESET);
        else System.out.println(Color.RED + "R0 selection failed" + Color.RESET);
    }

    private static void testSelectR1() {
        Collection<Polyhedron> selected = SampleV1Constructor.selectR1();
        if (selected.size() == 216) System.out.println(Color.GREEN + "R1 selection successful" + Color.RESET);
        else System.out.println(Color.RED + "R1 selection failed" + Color.RESET);
    }

    private static void testSelectR2() {
        Collection<Polyhedron> selected = SampleV1Constructor.selectR2();
        if (selected.size() == 30) System.out.println(Color.GREEN + "R2 selection successful" + Color.RESET);
        else System.out.println(Color.RED + "R2 selection failed" + Color.RESET);
    }

    private static void testSelectR3() {
        Collection<Polyhedron> selected = SampleV1Constructor.selectR3();
        if (selected.size() == 252) System.out.println(Color.GREEN + "R3 selection successful" + Color.RESET);
        else System.out.println(Color.RED + "R3 selection failed with " + selected.size() + Color.RESET);
    }

    private static void testSelectR4() {
        Collection<Polyhedron> selected = SampleV1Constructor.selectR4();
        if (selected.size() == 522) System.out.println(Color.GREEN + "R4 selection successful" + Color.RESET);
        else System.out.println(Color.RED + "R4 selection failed with " + selected.size() + Color.RESET);
    }

    private static void testSelectT1() {
        Collection<Polyhedron> selected = SampleV1Constructor.selectT1();
        if (selected.size() == 24) System.out.println(Color.GREEN + "T1 selection successful" + Color.RESET);
        else System.out.println(Color.RED + "T1 selection failed with " + selected.size() + Color.RESET);
    }

    private static void testSelectT2() {
        Collection<Polyhedron> selected = SampleV1Constructor.selectT2();
        if (selected.size() == 24) System.out.println(Color.GREEN + "T2 selection successful" + Color.RESET);
        else System.out.println(Color.RED + "T2 selection failed with " + selected.size() + Color.RESET);
    }

    private static void testSelectT3() {
        Collection<Polyhedron> selected = SampleV1Constructor.selectT3();
        if (selected.size() == 24) System.out.println(Color.GREEN + "T3 selection successful" + Color.RESET);
        else System.out.println(Color.RED + "T3 selection failed with " + selected.size() + Color.RESET);
    }

    private static void testSelectT4() {
        Collection<Polyhedron> selected = SampleV1Constructor.selectT4();
        if (selected.size() == 24) System.out.println(Color.GREEN + "T4 selection successful" + Color.RESET);
        else System.out.println(Color.RED + "T4 selection failed with " + selected.size() + Color.RESET);
    }

    private static void testSelectT5() {
        Collection<Polyhedron> selected = SampleV1Constructor.selectT5();
        if (selected.size() == 24) System.out.println(Color.GREEN + "T5 selection successful" + Color.RESET);
        else System.out.println(Color.RED + "T5 selection failed with " + selected.size() + Color.RESET);
    }

    private static void testSelectT6() {
        Collection<Polyhedron> selected = SampleV1Constructor.selectT6();
        if (selected.size() == 24) System.out.println(Color.GREEN + "T6 selection successful" + Color.RESET);
        else System.out.println(Color.RED + "T6 selection failed with " + selected.size() + Color.RESET);
    }

    private static void testSelectQ1() {
        Collection<Polyhedron> selected = SampleV1Constructor.selectQ1();
        if (selected.size() == 12) System.out.println(Color.GREEN + "Q1 selection successful" + Color.RESET);
        else System.out.println(Color.RED + "Q1 selection failed with " + selected.size() + Color.RESET);
    }

    private static void testSelectQ2() {
        Collection<Polyhedron> selected = SampleV1Constructor.selectQ2();
        if (selected.size() == 12) System.out.println(Color.GREEN + "Q2 selection successful" + Color.RESET);
        else System.out.println(Color.RED + "Q2 selection failed with " + selected.size() + Color.RESET);
    }

    private static void testSelectQ3() {
        Collection<Polyhedron> selected = SampleV1Constructor.selectQ3();
        if (selected.size() == 12) System.out.println(Color.GREEN + "Q3 selection successful" + Color.RESET);
        else System.out.println(Color.RED + "Q3 selection failed with " + selected.size() + Color.RESET);
    }

    private static void testSelectQ4() {
        Collection<Polyhedron> selected = SampleV1Constructor.selectQ4();
        if (selected.size() == 12) System.out.println(Color.GREEN + "Q4 selection successful" + Color.RESET);
        else System.out.println(Color.RED + "Q4 selection failed with " + selected.size() + Color.RESET);
    }

    private static void testSelectQ5() {
        Collection<Polyhedron> selected = SampleV1Constructor.selectQ5();
        if (selected.size() == 12) System.out.println(Color.GREEN + "Q5 selection successful" + Color.RESET);
        else System.out.println(Color.RED + "Q5 selection failed with " + selected.size() + Color.RESET);
    }

    private static void testSelectQ6() {
        Collection<Polyhedron> selected = SampleV1Constructor.selectQ6();
        if (selected.size() == 12) System.out.println(Color.GREEN + "Q6 selection successful" + Color.RESET);
        else System.out.println(Color.RED + "Q6 selection failed with " + selected.size() + Color.RESET);
    }

    private static void testSelectC1() {
        Collection<Polyhedron> selected = SampleV1Constructor.selectC1();
        if (selected.size() == 12) System.out.println(Color.GREEN + "C1 selection successful" + Color.RESET);
        else System.out.println(Color.RED + "C1 selection failed with " + selected.size() + Color.RESET);
    }

    private static void testSelectC2() {
        Collection<Polyhedron> selected = SampleV1Constructor.selectC2();
        if (selected.size() == 12) System.out.println(Color.GREEN + "C2 selection successful" + Color.RESET);
        else System.out.println(Color.RED + "C2 selection failed with " + selected.size() + Color.RESET);
    }

    private static void testSelectC3() {
        Collection<Polyhedron> selected = SampleV1Constructor.selectC3();
        if (selected.size() == 12) System.out.println(Color.GREEN + "C3 selection successful" + Color.RESET);
        else System.out.println(Color.RED + "C3 selection failed with " + selected.size() + Color.RESET);
    }

    private static void testSelectC4() {
        Collection<Polyhedron> selected = SampleV1Constructor.selectC4();
        if (selected.size() == 12) System.out.println(Color.GREEN + "C4 selection successful" + Color.RESET);
        else System.out.println(Color.RED + "C4 selection failed with " + selected.size() + Color.RESET);
    }

    private static void testSelectC5() {
        Collection<Polyhedron> selected = SampleV1Constructor.selectC5();
        if (selected.size() == 12) System.out.println(Color.GREEN + "C5 selection successful" + Color.RESET);
        else System.out.println(Color.RED + "C5 selection failed with " + selected.size() + Color.RESET);
    }


}
