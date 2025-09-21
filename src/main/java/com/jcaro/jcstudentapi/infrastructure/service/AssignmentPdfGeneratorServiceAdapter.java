package com.jcaro.jcstudentapi.infrastructure.service;

import com.itextpdf.text.*;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.jcaro.jcstudentapi.application.service.AssignmentPdfGeneratorService;
import com.jcaro.jcstudentapi.domain.model.Assignment;
import com.jcaro.jcstudentapi.domain.model.Student;
import com.jcaro.jcstudentapi.domain.model.StudentAssignment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@Service
@Slf4j
public class AssignmentPdfGeneratorServiceAdapter implements AssignmentPdfGeneratorService {


    @Override
    public ByteArrayInputStream generate(Student student,java.util.List<Assignment> courseAssignment,java.util.List<StudentAssignment> studentAssignment ) throws DocumentException, IOException {
        final Document document = new Document();
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);
        document.open();
        document.add(header(student));
        document.add(body(courseAssignment,studentAssignment));
        document.add(rubricsTable());
        document.addTitle("Student assignment score");
        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }


    private static PdfPTable header(Student student) throws DocumentException, IOException {

        final PdfPTable tableHeader = new PdfPTable(2);
        tableHeader.setWidthPercentage(100);
        tableHeader.setWidths(new int[]{2, 2});

        /*Notas  Cell*/
        final Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        final PdfPCell cellHeader = new PdfPCell(new Phrase("Scores " + student.name(), font));
        cellHeader.setVerticalAlignment(Element.ALIGN_TOP);
        cellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        cellHeader.setBorder(0);
        tableHeader.addCell(cellHeader);
        /*Imagen cell*/
        final PdfPCell cellHeader2 = new PdfPCell(new Phrase(""));
        cellHeader2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellHeader2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cellHeader2.setPaddingRight(5);
        final String imageFile = "src/main/resources/static/jc_student_crop.png";
        Image img = Image.getInstance(imageFile);
        cellHeader2.setImage(img);
        cellHeader2.setFixedHeight(100);
        cellHeader2.setBorder(0);
        tableHeader.addCell(cellHeader2);

        return tableHeader;
    }


    private static PdfPTable body(java.util.List<Assignment> courseAssignment,java.util.List<StudentAssignment> studentAssignment) {

        final PdfPTable tableBody = new PdfPTable(courseAssignment.size());
       tableBody.setWidthPercentage(100);
        for (Assignment assignment : courseAssignment) {
            final PdfPCell headerCell = new PdfPCell(new Phrase(assignment.name(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, BaseColor.WHITE)));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            headerCell.setBackgroundColor(BaseColor.GRAY);
            tableBody.addCell(headerCell);
        }
        for (Assignment assignment : courseAssignment) {
            PdfPCell bodyCell = new PdfPCell();
            for (StudentAssignment sa : studentAssignment
            ) {
                if (Objects.equals(sa.assignment().id(), assignment.id())) {
                    bodyCell = new PdfPCell(new Phrase(String.valueOf(sa.score().ordinal()), FontFactory.getFont(FontFactory.HELVETICA, 6)));
                }
            }

            bodyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            bodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bodyCell.setPadding(10);
            tableBody.addCell(bodyCell);
        }
        tableBody.setSpacingBefore(20f);
        tableBody.setSpacingAfter(50);
        return tableBody;
    }


    private static PdfPTable rubricsTable() {

        final ArrayList<String> textBody = new ArrayList<>();
        final ArrayList<String> textHeader = new ArrayList<>(java.util.List.of("Learning outcomes", "Domain", "0 - Incomplete", "1 - Fair", "2 - Good", "3 - Excellent"));
        textBody.addAll(java.util.List.of("Completed each of the mandatory iterations correctly.",
                "Server-side development, Client-side development, Databases, Software Architecture"
                , "Student has made little effort to complete the iterations, and work submitted is incomplete or incorrect. It is clear that student has not understood the concepts covered in the day.",
                "Student has made some effort to complete the iterations, but overall is either missing some of the mandatory iterations, or work submitted is only partially correct indicating student has not understood all of the concepts in the day."
                , "Student has completed all of the mandatory iterations are submitted and are correct, showing that core concepts from the day have been well understood and implemented.",
                "Student has completed all of the mandatory iterations correctly and gone the extra step to submit some or all of the bonus iterations. Student has shown they have clearly understood all of the main concepts of the day and taken the time to practice everything."));
        textBody.addAll(java.util.List.of("Commented code where appropriate", "Code Quality", "Student has made no effort to comment any of their code.", "Student has commented some code with room for improvement.", "Student has commented code sufficiently and to a good level across all of the iterations where necessary.", "Student has commented their code to an exceptional degree across all iterations and it would be clear to any developer what they have done and why."));
        textBody.addAll(java.util.List.of("Followed clean code principles and best practices where appropriate", "Code Quality", "Student has not followed clean code conventions. Indentation has not been implemented making code hard to read and naming best practices have not been followed.", "Student has made some effort to follow clean code principles but there is room for improvement. Code could be easier to read and naming of best practices could be improved.", "Student has followed clean code conventions and best practices very well. Code is easy to read with consistent formatting and structure.", "Student has followed best practices following clean code principles to a professional grade standard."));
        final PdfPTable rubricsTable = new PdfPTable(6);
        rubricsTable.setWidthPercentage(100);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        final PdfPCell titleRubrics = new PdfPCell(new Phrase("Rubrics", font));
        titleRubrics.setColspan(6);
        titleRubrics.setVerticalAlignment(Element.ALIGN_TOP);
        titleRubrics.setHorizontalAlignment(Element.ALIGN_LEFT);
        titleRubrics.setBorder(0);
        titleRubrics.setFixedHeight(50);
        rubricsTable.addCell(titleRubrics);

        textHeader.forEach(s -> {
            final PdfPCell headerCell = new PdfPCell(new Phrase(s, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, BaseColor.WHITE)));
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
