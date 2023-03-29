# Programmentwurf - AutoChef
Name: Schirmer, Oliver \
Martrikelnummer: XXX

Name: Müller, Luca \
Martrikelnummer: XXX

Abgabedatum: 28. Mai 2023

## Allgemeine Anmerkungen
- es darf nicht auf andere Kapitel als Leistungsnachweis verwiesen werden (z.B. in der Form “XY wurde schon in Kapitel 2 behandelt, daher hier keine Ausführung”)
- alles muss in UTF-8 codiert sein (Text und Code)
- sollten mündliche Aussagen den schriftlichen Aufgaben widersprechen, gelten die schriftlichen Aufgaben (ggf. an Anpassung der schriftlichen Aufgaben erinnern!)
- alles muss ins Repository (Code, Ausarbeitung und alles was damit zusammenhängt)
- die Beispiele sollten wenn möglich vom aktuellen Stand genommen werden
  - finden sich dort keine entsprechenden Beispiele, dürfen auch ältere Commits unter Verweis auf den Commit verwendet werden 
  - Ausnahme: beim Kapitel “Refactoring” darf von vorne herein aus allen Ständen frei gewählt werden (mit Verweis auf den entsprechenden Commit)
- falls verlangte Negativ-Beispiele nicht vorhanden sind, müssen entsprechend mehr Positiv-Beispiele gebracht werden
  - Achtung: werden im Code entsprechende Negativ-Beispiele gefunden, gibt es keine Punkte für die zusätzlichen Positiv-Beispiele
  - Beispiel: “Nennen Sie jeweils eine Klasse, die das SRP einhält bzw. verletzt.”
  - -> Antwort: Es gibt keine Klasse, die SRP verletzt, daher hier 2 Klassen, die SRP einhalten: [Klasse 1], [Klasse 2]
  - -> Bewertung: falls im Code tatsächlich keine Klasse das SRP verletzt: volle Punktzahl ODER falls im Code mind. eine Klasse SRP verletzt: halbe Punktzahl
- verlangte Positiv-Beispiele müssen gebracht werden
- Code-Beispiel = Code in das Dokument kopieren

## 1. Einführung
### Übersicht über die Applikation
AutoChef ist eine Anwendung zur einfachen und effizienten Verwaltung und Erstellung von Essensplänen, sowie dazugehöriger Einkaufslisten. Zugrunde liegt dabei eine Datenbank an Rezepten. Diese ist beliebig erweiterbar durch eine Chefkoch (chefkoch.de)-Integration. Diese ermöglicht es, einen Link zu einem Rezept auf Chefkoch an die Anwendung zu übergeben, woraufhin diese das entsprechende Rezept herunterlädt und persistiert. Diese Datenbank an Rezepten, sowie jeweilige Rezept-Details, können jederzeit eingesehen werden. Hauptfunktion ist jedoch die Generierung von Essensplänen. Dafür kann der Nutzer einen Zeitraum, sowie die Anzahl an Personen je Mahlzeit angeben und die Anwendung generiert anhand der Rezept-Datenbank, einen zufälligen Essensplan. Für diesen Essensplan wird ebenfalls eine Einkaufsliste generiert, die an die Anzahl an Personen angepasst ist. Mit diesen Funktionalitäten ist es einfach möglich, seine Woche kulinarisch zu planen und die Einkaufliste für den Wocheneinkauf zu erstellen.

*[Was macht die Applikation? Wie funktioniert sie? Welches Problem löst sie/welchen Zweck hat sie?]*

### Wie startet man die Applikation?
Bei AutoChef handelt es sich um eine CLI-Anwendung, geschrieben in Java 19. Zum Starten wird daher lediglich ein Desktop-Rechner mit Java 19 aufwärts benötigt. Die Anwendung kann dann über ein Konsolenfenster mit dem Befehl java -jar AutoChef.jar gestartet werden.

*[Wie startet man die Applikation? Welche Voraussetzungen werden benötigt? Schritt-für-Schritt-Anleitung]*

### Wie testet man die Applikation?
Nach dem Start der Anwendung wird der Nutzer durch einen intuitiven Dialog-Prozess begrüßt und geleitet. Der Nutzer interagiert dabei mit der Anwendung mittels des Konsolenfensters. Über dieses werden sowohl Informationen ausgegeben, als auch Eingaben vom Nutzer eingeholt. Der Dialog-Prozess ist so gestaltet, dass der Nutzer in der Regel mehrere nummerierte Optionen zur Auswahl hat und nur die Nummer der gewünschten Option eingeben und mit Enter absenden muss. Für den Import von Rezepten muss der Nutzer sich zuvor ein Rezept von Chefkoch aussuchen und im entsprechenden Dialog-Prozess-Schritt den dazugehörigen Link einfügen. Zu Beginn der Nutzung ist die Rezept-Datenbank noch leer, weshalb es sich anbietet anfangs ein paar Rezepte zu importieren. Erst danach können die Funktionen der Rezept-Anzeige und Essensplan-Generierung sinnvoll genutzt werden.

Hinweis: Zur Persistierung der Rezepte erstellt die Anwendung im aktuellen Arbeitsverzeichnis (des Konsolenfensters) einen persistence-Ordner. Falls der Nutzer keine Rechte hat im aktuellen Arbeitsverzeichnis Ordner & Dateien zu erstellen, sowie in diese zu schreiben, kann das Programm nicht ordnungsgemäß arbeiten und terminiert. Versuche in dem Fall die Anwendung mit erhöhten Berechtigungen zu starten oder in einem Arbeitsverzeichnis mit Schreibzugriff auszuführen.

*[Wie testet man die Applikation? Welche Voraussetzungen werden benötigt? Schritt-für-Schritt-Anleitung]*

## 2. Clean Architecture
### Was ist Clean Architecture?
Clean Architecture ist eine Architektur- und Designphilosophie, die darauf abzielt, komplexe Softwaresysteme in leicht verständliche, wartbare und erweiterbare Komponenten zu unterteilen. Es wurde von Robert C. Martin entwickelt und basiert auf den Prinzipien der SOLID-Prinzipien.

Im Wesentlichen sieht Clean Architecture vor, dass eine Software in mehrere Schichten unterteilt wird, wobei jede Schicht eine klare Abhängigkeitshierarchie aufweist und nur von der nächstgelegenen Schicht abhängt. Die äußerste Schicht ist die Benutzerschnittstelle, die direkt mit dem Benutzer interagiert, gefolgt von einer oder mehreren Schichten mit Geschäftslogik, Datenzugriff und Infrastruktur.

Durch diese Aufteilung kann jede Schicht unabhängig von den anderen Schichten getestet und gewartet werden, was zu einer höheren Flexibilität und Skalierbarkeit des gesamten Systems führt. Darüber hinaus ist es einfacher, Änderungen an einem Teil des Systems vorzunehmen, ohne Auswirkungen auf den Rest des Systems zu haben.

Clean Architecture ermutigt auch zur Verwendung von Schnittstellen, um die Abhängigkeiten zwischen den Komponenten zu minimieren. Durch die Verwendung von Schnittstellen können verschiedene Implementierungen ausgetauscht werden, ohne dass dies Auswirkungen auf den Rest des Systems hat.

Zusammenfassend lässt sich sagen, dass Clean Architecture eine Methode ist, um große, komplexe Softwaresysteme in einfachere, leichter zu wartende Komponenten aufzuteilen, indem eine klare Abhängigkeitshierarchie zwischen den Komponenten eingeführt wird.

*[allgemeine Beschreibung der Clean Architecture in eigenen Worten]*

### Analyse der Dependency Rule
*[(1 Klasse, die die Dependency Rule einhält und eine Klasse, die die Dependency Rule verletzt);   jeweils UML der Klasse und Analyse der Abhängigkeiten in beide Richtungen (d.h., von wem hängt die Klasse ab und wer hängt von der Klasse ab) in Bezug auf die Dependency Rule]*

Positiv-Beispiel: Dependency Rule
Negativ-Beispiel: Dependency Rule

### Analyse der Schichten
*[jeweils 1 Klasse zu 2 unterschiedlichen Schichten der Clean-Architecture: jeweils UML der Klasse (ggf. auch zusammenspielenden Klassen), Beschreibung der Aufgabe, Einordnung mit Begründung in die Clean-Architecture]*

Schicht: [Name]
Schicht: [Name]

## 3. SOLID
### Analyse Single-Responsibility-Principle (SRP)
*[jeweils eine Klasse als positives und negatives Beispiel für SRP;  jeweils UML der Klasse und Beschreibung der Aufgabe bzw. der Aufgaben und möglicher Lösungsweg des Negativ-Beispiels (inkl. UML)]*

Positiv-Beispiel
Negativ-Beispiel

### Analyse Open-Closed-Principle (OCP)
*[jeweils eine Klasse als positives und negatives Beispiel für OCP;  jeweils UML der Klasse und Analyse mit Begründung, warum das OCP erfüllt/nicht erfüllt wurde – falls erfüllt: warum hier sinnvoll/welches Problem gab es? Falls nicht erfüllt: wie könnte man es lösen (inkl. UML)?]*

Positiv-Beispiel
Negativ-Beispiel

### Analyse Liskov-Substitution- (LSP), Interface-Segreggation- (ISP), Dependency-Inversion-Principle (DIP)
*[jeweils eine Klasse als positives und negatives Beispiel für entweder LSP oder ISP oder DIP);  jeweils UML der Klasse und Begründung, warum man hier das Prinzip erfüllt/nicht erfüllt wird]* \
*[Anm.: es darf nur ein Prinzip ausgewählt werden; es darf NICHT z.B. ein positives Beispiel für LSP und ein negatives Beispiel für ISP genommen werden]*

Positiv-Beispiel
Negativ-Beispiel

## 4. Weitere Prinzipien
### Analyse GRASP: Geringe Kopplung
*[jeweils eine bis jetzt noch nicht behandelte Klasse als positives und negatives Beispiel geringer Kopplung; jeweils UML Diagramm mit zusammenspielenden Klassen, Aufgabenbeschreibung und Begründung für die Umsetzung der geringen Kopplung bzw. Beschreibung, wie die Kopplung aufgelöst werden kann]*

Positiv-Beispiel
Negativ-Beispiel

### Analyse GRASP: Hohe Kohäsion
*[eine Klasse als positives Beispiel hoher Kohäsion; UML Diagramm und Begründung, warum die Kohäsion hoch ist]
Don’t Repeat Yourself (DRY)* \
*[ein Commit angeben, bei dem duplizierter Code/duplizierte Logik aufgelöst wurde; Code-Beispiele (vorher/nachher); begründen und Auswirkung beschreiben]*

## 5. Unit Tests
### 10 Unit Tests
*[Nennung von 10 Unit-Tests und Beschreibung, was getestet wird]*
| Unit Test        | Beschreibung |
|------------------|--------------|
| *Klasse#Methode* |              |
|                  |              |
|                  |              |
|                  |              |

### ATRIP: Automatic
*[Begründung/Erläuterung, wie ‘Automatic’ realisiert wurde]*

### ATRIP: Thorough
*[jeweils 1 positives und negatives Beispiel zu ‘Thorough’; jeweils Code-Beispiel, Analyse und Begründung, was professionell/nicht professionell ist]*

### ATRIP: Professional
*[jeweils 1 positives und negatives Beispiel zu ‘Professional’; jeweils Code-Beispiel, Analyse und Begründung, was professionell/nicht professionell ist]*

### Code Coverage
*[Code Coverage im Projekt analysieren und begründen]*

### Fakes und Mocks
*[Analyse und Begründung des Einsatzes von 2 Fake/Mock-Objekten; zusätzlich jeweils UML Diagramm der Klasse]*

## 6. Domain Driven Design
### Ubiquitous Language
*[4 Beispiele für die Ubiquitous Language; jeweils Bezeichung, Bedeutung und kurze Begründung, warum es zur Ubiquitous Language gehört]*
| Bezeichnung | Bedeutung | Begründung |
|-------------|-----------|------------|
|             |           |            |
|             |           |            |
|             |           |            |
|             |           |            |

### Entities
*[UML, Beschreibung und Begründung des Einsatzes einer Entity; falls keine Entity vorhanden: ausführliche Begründung, warum es keines geben kann/hier nicht sinnvoll ist]*

### Value Objects
*[UML, Beschreibung und Begründung des Einsatzes eines Value Objects; falls kein Value Object vorhanden: ausführliche Begründung, warum es keines geben kann/hier nicht sinnvoll ist]*

### Repositories
*[UML, Beschreibung und Begründung des Einsatzes eines Repositories; falls kein Repository vorhanden: ausführliche Begründung, warum es keines geben kann/hier nicht sinnvoll ist]*

### Aggregates
*[UML, Beschreibung und Begründung des Einsatzes eines Aggregates; falls kein Aggregate vorhanden: ausführliche Begründung, warum es keines geben kann/hier nicht sinnvoll ist]*

## 7. Refactoring
### Code Smells
*[jeweils 1 Code-Beispiel zu 2 Code Smells aus der Vorlesung; jeweils Code-Beispiel und einen möglichen Lösungsweg bzw. den genommen Lösungsweg beschreiben (inkl. (Pseudo-)Code)]*

### 2 Refactorings
*[2 unterschiedliche Refactorings aus der Vorlesung anwenden, begründen, sowie UML vorher/nachher liefern; jeweils auf die Commits verweisen]*

## 8. Entwurfsmuster
*[2 unterschiedliche Entwurfsmuster aus der Vorlesung (oder nach Absprache auch andere) jeweils sinnvoll einsetzen, begründen und UML-Diagramm]*

Entwurfsmuster: [Name]
Entwurfsmuster: [Name]
