package application;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GeneratPDF {

    public static void generatePDF(Members personne) {
        String fileName = "output.txt"; // Nom du fichier texte de sortie

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(fileName))) {
            // Cr�ez le contenu du fichier texte en utilisant les informations de la personne
            String content = generatePDFContent(personne);

            // �crivez le contenu dans le fichier
            printWriter.println(content);

            System.out.println("Fichier texte g�n�r� avec succ�s.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generatePDFContent(Members personne) {
        // G�n�rez le contenu du fichier texte en utilisant les informations de la personne
        StringBuilder content = new StringBuilder();
        content.append("Nom: ").append(personne.getNom()).append("\n");
        content.append("Pr�nom: ").append(personne.getPrenom()).append("\n");
        content.append("�ge: ").append(personne.getAge()).append("\n");
        content.append("Genre: ").append(personne.getGender()).append("\n");
        content.append("T�l�phone: ").append(personne.getNumPhone()).append("\n");
        content.append("Dur�e: ").append(personne.getAmount()).append("\n");
        content.append("Date d'inscription: ").append(personne.getDuree()).append("\n");
        

        return content.toString();
    }
}