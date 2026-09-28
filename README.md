# AdmitX – AI-Assisted MHT CET CAP Counselling System

AdmitX is a JavaFX desktop application that simulates the major stages of an MHT CET CAP counselling workflow for students and counsellors. The project connects the student application process, document verification, merit management, preference filling, CAP seat allotment, betterment rounds, admission confirmation, notices, grievances, reports, and an AI-assisted help experience.

## Technology Stack

- Java 17
- JavaFX 21
- Maven
- Firebase Firestore
- Cloudinary document/image storage
- OpenAI-assisted chatbot service

## Student Module

- Student registration and login
- Dashboard with application progress
- Personal, address, academic, university and reservation details
- Document upload and preview
- Application submission and status tracking
- Document correction and re-upload when requested by counsellor
- Provisional/final merit information
- College search
- Preference filling and option-form locking
- CAP Round 1, Round 2 and Round 3 results
- Freeze / Betterment / Reject decisions where applicable
- Final admission confirmation
- Notices, profile and help centre

## Counsellor Module

- Counsellor dashboard
- Student/application management
- Document-by-document verification
- Correction request / re-verification workflow
- College and seat management
- Merit list management
- Option-form management
- CAP Round 1 management
- CAP Round 2 betterment management
- CAP Round 3 final allotment management
- Reports
- Notices and grievance management

## Application Verification Flow

1. Student completes the application and uploads documents.
2. Student submits the application.
3. Counsellor reviews each uploaded document.
4. Verified documents are marked `Verified`.
5. If a document is invalid, it is marked `Rejected` and the application becomes `Correction Required`.
6. Student opens Document Corrections and re-uploads only rejected documents.
7. Corrected documents return to `Pending` verification.
8. Student resubmits the corrected application.
9. Counsellor re-verifies corrected documents.
10. The complete application becomes `Verified` only when every uploaded document is verified.

## CAP Flow

1. Counsellor prepares a new CAP cycle.
2. Verified students lock their option forms.
3. Round 1 processes students using CET percentile, preference order, cutoff and available seats.
4. Round 1 is published and students choose the available decision.
5. Betterment requests continue to Round 2.
6. Round 2 processes upgrades and safely releases a previous seat only when a better seat is allotted.
7. Eligible betterment cases continue to Round 3.
8. Round 3 produces the final allotment.
9. Student accepts the final admission.

## Project Structure

```text
src/main/java/com/admitx/
├── config/       Firebase and external-service configuration
├── controller/   UI-to-data controllers
├── dao/          Firestore persistence and counselling data logic
├── model/        Application domain models
├── service/      Service integrations
├── util/         Background task utilities
└── view/         JavaFX student and counsellor screens
```

## Run the Project

Requirements: JDK 17+, Maven, JavaFX-compatible desktop environment, and the project service configuration already used by the application.

From the project directory:

```bash
mvn clean javafx:run
```

The Maven JavaFX plugin starts:

```text
com.admitx.MainApp
```

## Recommended End-to-End Test

Create a new student account, complete every application section, upload documents, submit the application, verify documents from the counsellor side, test one document correction and resubmission, verify the corrected application, generate/publish merit data, lock preferences, run and publish CAP Round 1, test Freeze/Betterment/Reject decisions, complete Round 2 and Round 3, and finally confirm admission.

## Notes

- Firestore operations that can take time are intended to run through the project's asynchronous task utilities so the JavaFX UI remains responsive.
- CAP seat allotment requires matching college/branch records, valid available seats, verified applications, locked preferences and a valid CET percentile.
- `Start New CAP Cycle` should be used before running a completely new counselling cycle so old allotment data is cleared and intake is restored.
