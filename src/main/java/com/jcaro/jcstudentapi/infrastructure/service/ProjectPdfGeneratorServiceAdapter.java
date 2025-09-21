package com.jcaro.jcstudentapi.infrastructure.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jcaro.jcstudentapi.application.service.ProjectPdfGeneratorService;
import com.jcaro.jcstudentapi.domain.model.Student;
import com.jcaro.jcstudentapi.domain.model.StudentProject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProjectPdfGeneratorServiceAdapter implements ProjectPdfGeneratorService {


    @Override
    public ByteArrayInputStream generate(Student student, List<StudentProject> studentProjects) throws DocumentException, IOException {

        final Document document = new Document();
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);
        document.open();
        document.add(header(student));
        document.add(body(studentProjects));
        document.add(rubricsTable());
        document.addTitle("Student project score");
        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

    private static PdfPTable header(Student student) throws DocumentException, IOException {

        final PdfPTable tableHeader = new PdfPTable(2);
        tableHeader.setWidthPercentage(100);
        tableHeader.setWidths(new int[]{2, 2});


        final Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        final PdfPCell cellHeader = new PdfPCell(new Phrase("Scores " + student.name(), font));
        cellHeader.setVerticalAlignment(Element.ALIGN_TOP);
        cellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        cellHeader.setBorder(0);
        tableHeader.addCell(cellHeader);

        final PdfPCell cellHeader2 = new PdfPCell(new Phrase(""));
        cellHeader2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellHeader2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cellHeader2.setPaddingRight(5);
        final String imageFile = "src/main/resources/static/jc_student_crop.png";
        final Image img = Image.getInstance(imageFile);
        cellHeader2.setImage(img);
        cellHeader2.setFixedHeight(100);
        cellHeader2.setBorder(0);
        tableHeader.addCell(cellHeader2);


        return tableHeader;
    }

    private static PdfPTable body(List<StudentProject> studentProjects) {

        final List<String> headerScore = new ArrayList<>(List.of(
                "Code Quality",
                "Functionality",
                "Security",
                "Documentation",
                "Deployment",
                "Extra Features"
        ));
        final PdfPTable tableBody = new PdfPTable(headerScore.size());
        tableBody.setWidthPercentage(100);

        for (String header : headerScore) {
            PdfPCell headerCell = new PdfPCell(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, BaseColor.WHITE)));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            headerCell.setBackgroundColor(BaseColor.GRAY);
            tableBody.addCell(headerCell);
        }
        for (StudentProject studentProject : studentProjects) {


            tableBody.addCell(createBodyCell(String.valueOf(studentProject.codeQuality().ordinal())));
            tableBody.addCell(createBodyCell(String.valueOf(studentProject.functionality().ordinal())));
            tableBody.addCell(createBodyCell(String.valueOf(studentProject.security().ordinal())));
            tableBody.addCell(createBodyCell(String.valueOf(studentProject.documentation().ordinal())));
            tableBody.addCell(createBodyCell(String.valueOf(studentProject.deployment().ordinal())));
            tableBody.addCell(createBodyCell(String.valueOf(studentProject.extra().ordinal())));

            tableBody.setSpacingBefore(20f);
            tableBody.setSpacingAfter(50);
        }

        return tableBody;
    }

    private static PdfPCell createBodyCell(String result) {
        PdfPCell bodyCell;
        bodyCell = new PdfPCell(new Phrase(String.valueOf(result), FontFactory.getFont(FontFactory.HELVETICA, 6)));

        bodyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        bodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        bodyCell.setPadding(10);

        return bodyCell;
    }

    private static PdfPTable rubricsTable() {

        final ArrayList<String> textBody = new ArrayList<>();

        final ArrayList<String> textHeader = new ArrayList<>(List.of("Learning outcomes", "Domain", "0 - Incomplete", "1 - Fair", "2 - Good", "3 - Excellent"));

        textBody.addAll(List.of("Problem-solving & Core Functionality", "Conceptualization, Logic", "The core functionality of the project is not implemented. The solution doesn't address the main problem it was intended to solve, or the application is non-functional.", "The core functionality is partially implemented. The application works but has significant gaps or bugs. The solution only addresses a small part of the main problem.", "The core functionality is well-implemented. The application works as intended with minor bugs. The solution effectively addresses the main problem.", "The project's core functionality is fully implemented and robust. The application is highly reliable and performs its intended function exceptionally well. The solution demonstrates an advanced understanding of the problem space."));
        textBody.addAll(List.of("User Interface & Experience", "Design, Usability", "The user interface is confusing, difficult to navigate, or non-existent. There is no clear path for the user to interact with the application.", "The user interface is functional but lacks polish. Navigation is present but may be clunky or inconsistent. Some elements are not intuitive for the user.", "The user interface is clean and intuitive. Navigation is consistent and easy to follow. The user experience is generally smooth and well-considered.", "The user interface is well-designed, intuitive, and visually appealing. The user experience is seamless and delightful, demonstrating a deep understanding of user-centered design principles."));
        textBody.addAll(List.of("Code Quality & Best Practices", "Readability, Maintainability", "The code is disorganized, difficult to read, and lacks a consistent structure. There is no evidence of using standard naming conventions or formatting.", "The code has some organization but is inconsistent. Naming conventions are partially followed, but the code is still somewhat difficult to read or maintain.", "The code is clean, well-organized, and easy to read. Variables, functions, and files are named logically and follow a consistent structure. The code is modular and easy to maintain.", "The code is exceptionally clean, efficient, and highly modular. It adheres to all best practices and industry standards for readability and maintainability. The project structure is logical and easy to navigate."));
        textBody.addAll(List.of("Project Documentation & Presentation", "Communication, Clarity", "The project lacks any form of documentation. It's impossible to understand the project's purpose, setup, or usage without direct guidance.", "The documentation is incomplete or unclear. The README file is missing key information, or the instructions are difficult to follow. Diagrams (if included) are basic or inaccurate.", "The project has clear and comprehensive documentation. The README file provides sufficient information for setting up and using the application. Basic diagrams are included and mostly accurate.", "The project is exceptionally well-documented. The README file is detailed, professional, and includes clear instructions and diagrams. The documentation goes above and beyond to explain design choices and technical specifications."));
        textBody.addAll(List.of("Bonus: Advanced Concepts & Innovation", "Creativity, Complexity", "No advanced concepts or extra features were attempted.", "Some extra features or complex concepts were attempted but are incomplete or non-functional. They show an effort but do not add significant value to the project.", "Functional extra features or advanced concepts were successfully integrated, adding value to the application. These features show a solid understanding of topics beyond the basic requirements.", "Multiple advanced concepts or innovative features were implemented flawlessly. The additions show an exceptional understanding of complex topics and significantly enhance the project's value and uniqueness."));
        final PdfPTable rubricsTable = new PdfPTable(6);
        rubricsTable.setWidthPercentage(100);

        final Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        final PdfPCell titleRubrics = new PdfPCell(new Phrase("Rubrics", font));
        titleRubrics.setColspan(6);
        titleRubrics.setVerticalAlignment(Element.ALIGN_TOP);
        titleRubrics.setHorizontalAlignment(Element.ALIGN_LEFT);
        titleRubrics.setBorder(0);
        titleRubrics.setFixedHeight(50);
        rubricsTable.addCell(titleRubrics);

        textHeader.forEach(s -> {
            PdfPCell headerCell = new PdfPCell(new Phrase(s, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, BaseColor.WHITE)));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            headerCell.setBackgroundColor(BaseColor.GRAY);
            rubricsTable.addCell(headerCell);
        });
        textBody.forEach(strings -> {

            final PdfPCell headerCell = new PdfPCell(new Phrase(strings, FontFactory.getFont(FontFactory.HELVETICA, 6)));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);

            rubricsTable.addCell(headerCell);
        });
        rubricsTable.setSpacingBefore(20);
        return rubricsTable;
    }
}
