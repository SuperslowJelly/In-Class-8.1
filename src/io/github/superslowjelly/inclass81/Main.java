package io.github.superslowjelly.inclass81;

import java.util.*;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    // Error printing method, since the same error is printed more than once.
    private static void error() {
        System.out.print("\u001B[31mInvalid input!\u001B[0m ");
    }

    // Prompt the user for a string.
    private static String getString(String output) {
        System.out.print(output);
        String input = SCANNER.nextLine();
        if (!input.equals(""))
            return input;
        else {
            error();
            return getString(output);
        }
    }

    // Prompt the user for an int.
    private static int getInt(String output) {
        System.out.print(output);
        String input = SCANNER.nextLine();
        if (input.matches("[0-9]+"))
            return Integer.parseInt(input);
        else {
            error();
            return getInt(output);
        }
    }

    // Prompt the user for a double.
    private static Double getDouble(String output) {
        System.out.print(output);
        String input = SCANNER.nextLine();
        if (input.matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?"))
            return Double.parseDouble(input);
        else {
            error();
            return getDouble(output);
        }
    }

    // Prompt the user for an urgency level.
    private static UrgencyLevels getUrgency(String output) {
        System.out.print(output);
        String input = SCANNER.nextLine();
        try {
            return UrgencyLevels.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            error();
            return getUrgency(output);
        }
    }

    public static void main(String[] args) {
        // Manually entered patient:
        String patientName = getString("Please enter the patient's name: ");
        int patientAge = getInt("Please enter the patient's age: ");
        Double patientHeight = getDouble("Please enter the patient's height: "),
                patientWeight = getDouble("Please enter the patient's weight: ");
        UrgencyLevels patientUrgency = getUrgency("Please enter the patient's urgency level: ");
        MedicalInfo manualPatient = new MedicalInfo(patientName, patientAge, patientHeight, patientWeight);

        System.out.print("\nManually entered patient details:\n\t" + manualPatient + " Urgency Level: " + patientUrgency + "\n\n");

        // Preset patients:
        TreeMap<UrgencyLevels, HashSet<MedicalInfo>> patients = new TreeMap<>();
        patients.put(UrgencyLevels.LEVEL_1, new HashSet<>(Arrays.asList(
                new MedicalInfo("Steve", 78, 5.4, 9.3),
                new MedicalInfo("Sharon", 56, 4.6, 8.7))
        ));
        patients.put(UrgencyLevels.LEVEL_2, new HashSet<>(Arrays.asList(
                new MedicalInfo("Austin", 20, 6.3, 13.4),
                new MedicalInfo("Melinda", 38, 5.8, 9.3))
        ));
        patients.put(UrgencyLevels.LEVEL_3, new HashSet<>(Arrays.asList(
                new MedicalInfo("Fronk", 999, 12.3, 999.9),
                new MedicalInfo("Jenny", 19, 5.9, 13.4))
        ));
        patients.put(UrgencyLevels.LEVEL_4, new HashSet<>(Arrays.asList(
                new MedicalInfo("Jermaine", 60, 5.9, 12.6),
                new MedicalInfo("Hannah", 18, 6.5, 14.3))
        ));

        StringBuilder output = new StringBuilder();
        for (Map.Entry<UrgencyLevels, HashSet<MedicalInfo>> entry : patients.entrySet()) {
            output.append(entry.getKey().name()).append(":\n");
            for (MedicalInfo patient : entry.getValue())
                output.append('\t').append(patient).append('\n');
        }

        System.out.print(output);
    }

}
